package com.example.library.controller;

import com.example.library.model.Book;
import com.example.library.model.Loan;
import com.example.library.model.User;
import com.example.library.service.BookService;
import com.example.library.service.LoanService;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/loans")
public class LoanController {

    private final LoanService loanService;
    private final BookService bookService;

    public LoanController(LoanService loanService, BookService bookService) {
        this.loanService = loanService;
        this.bookService = bookService;
    }

    // GET /loans - List semua pinjaman
    @GetMapping
    public List<Loan> getAllLoans() {
        return loanService.findAll();
    }

    // POST /loans/borrow/{bookId} - Pinjam buku
    @PostMapping("/borrow/{bookId}")
    public String borrowBook(@PathVariable Long bookId, HttpSession session) {
        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null || !"MEMBER".equalsIgnoreCase(currentUser.getRole())) {
            return "Unauthorized. Only members can borrow books.";
        }

        Optional<Book> bookOpt = bookService.findById(bookId);
        if (bookOpt.isEmpty() || !bookOpt.get().isAvailable()) {
            return "Book not available.";
        }

        Book book = bookOpt.get();
        book.setAvailable(false);
        bookService.save(book);

        Loan loan = new Loan();
        loan.setBook(book);
        loan.setMember(currentUser);
        loan.setLoanDate(LocalDate.now());
        loan.setReturned(false);
        loanService.save(loan);

        return "Book borrowed successfully.";
    }

    // POST /loans/return/{loanId} - Kembalikan buku
    @PostMapping("/return/{loanId}")
    public String returnBook(@PathVariable Long loanId, HttpSession session) {
        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null) {
            return "Unauthorized.";
        }

        Optional<Loan> loanOpt = loanService.findById(loanId);
        if (loanOpt.isEmpty()) {
            return "Loan not found.";
        }

        Loan loan = loanOpt.get();
        if (!loan.getMember().getId().equals(currentUser.getId()) && !"ADMIN".equalsIgnoreCase(currentUser.getRole())) {
            return "You are not authorized to return this loan.";
        }

        loan.setReturned(true);
        loan.setReturnDate(LocalDate.now());
        loanService.save(loan);

        Book book = loan.getBook();
        book.setAvailable(true);
        bookService.save(book);

        return "Book returned successfully.";
    }
}
