package com.example.library.service;

import com.example.library.model.User;
import com.example.library.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    private final UserRepository userRepository;

    public MemberService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Ambil semua user (biasanya role MEMBER)
    public List<User> findAllMembers() {
        return userRepository.findAll().stream()
                .filter(user -> "MEMBER".equalsIgnoreCase(user.getRole()))
                .toList();
    }

    // Simpan user baru
    public User save(User user) {
        return userRepository.save(user);
    }

    // Cari user berdasarkan ID
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    // Hapus user
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
