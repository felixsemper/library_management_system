package com.example.library.service;

import com.example.library.model.User;
import com.example.library.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Proses autentikasi login
    public User authenticate(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }

    // Proses registrasi user baru
    public String register(User newUser) {
        if (userRepository.existsByUsername(newUser.getUsername())) {
            return "Username already taken.";
        }
        newUser.setRole("MEMBER");
        userRepository.save(newUser);
        return "Registration successful.";
    }
}
