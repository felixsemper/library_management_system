package com.example.library.controller;

import com.example.library.model.User;
import com.example.library.service.AuthService;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // POST /auth/register
    @PostMapping("/register")
    public String register(@RequestBody User user) {
        return authService.register(user);
    }

    // POST /auth/login
    @PostMapping("/login")
    public String login(@RequestBody User loginData, HttpSession session) {
        User user = authService.authenticate(loginData.getUsername(), loginData.getPassword());
        if (user == null) {
            return "Invalid credentials.";
        }
        session.setAttribute("user", user);
        return "Login successful as " + user.getRole();
    }

    // POST /auth/logout
    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "Logged out.";
    }
}
