package com.example.library.repository;

import com.example.library.model.Loan;
import com.example.library.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {

    // Temukan semua peminjaman oleh user tertentu
    List<Loan> findByMember(User member);

    // Temukan semua peminjaman yang belum dikembalikan
    List<Loan> findByReturnedFalse();
}
