package com.example.library.service;

import com.example.library.model.Book;
import com.example.library.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // Ambil semua buku
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    // Simpan buku baru atau update
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    // Cari buku berdasarkan ID
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    // Hapus buku
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }

    // Cari buku berdasarkan judul (opsional)
    public List<Book> searchByTitle(String title) {
        return bookRepository.findByTitleContainingIgnoreCase(title);
    }
}
