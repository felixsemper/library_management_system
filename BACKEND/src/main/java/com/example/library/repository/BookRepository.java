package com.example.library.repository;

import com.example.library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    // Cari buku berdasarkan judul (opsional)
    List<Book> findByTitleContainingIgnoreCase(String title);

    // Cari semua buku berdasarkan status ketersediaan
    List<Book> findByAvailable(boolean available);
}
