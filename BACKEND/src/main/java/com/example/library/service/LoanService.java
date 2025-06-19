package com.example.library.service;

import com.example.library.model.Loan;
import com.example.library.model.User;
import com.example.library.repository.LoanRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoanService {

    private final LoanRepository loanRepository;

    public LoanService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    // Ambil semua peminjaman
    public List<Loan> findAll() {
        return loanRepository.findAll();
    }

    // Simpan atau update peminjaman
    public Loan save(Loan loan) {
        return loanRepository.save(loan);
    }

    // Cari peminjaman berdasarkan ID
    public Optional<Loan> findById(Long id) {
        return loanRepository.findById(id);
    }

    // Hapus peminjaman
    public void delete(Long id) {
        loanRepository.deleteById(id);
    }

    // Ambil semua pinjaman yang belum dikembalikan
    public List<Loan> findUnreturned() {
        return loanRepository.findByReturnedFalse();
    }

    // Ambil semua pinjaman berdasarkan user
    public List<Loan> findByMember(User member) {
        return loanRepository.findByMember(member);
    }
}
