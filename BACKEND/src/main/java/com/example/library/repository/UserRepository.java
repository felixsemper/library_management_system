package com.example.library.repository;

import com.example.library.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Login: cari berdasarkan username dan password
    User findByUsernameAndPassword(String username, String password);

    // Cek apakah username sudah digunakan
    boolean existsByUsername(String username);
}
