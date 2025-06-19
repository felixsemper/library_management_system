package com.example.library.controller;

import com.example.library.model.User;
import com.example.library.service.MemberService;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // GET /members - hanya admin bisa akses
    @GetMapping
    public List<User> getAllMembers(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null || !"ADMIN".equalsIgnoreCase(user.getRole())) {
            throw new RuntimeException("Unauthorized access");
        }
        return memberService.findAllMembers();
    }

    // POST /members - tambah member baru (admin)
    @PostMapping
    public User addMember(@RequestBody User newUser, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null || !"ADMIN".equalsIgnoreCase(user.getRole())) {
            throw new RuntimeException("Unauthorized access");
        }
        newUser.setRole("MEMBER");
        return memberService.save(newUser);
    }

    // DELETE /members/{id} - hapus member (admin)
    @DeleteMapping("/{id}")
    public String deleteMember(@PathVariable Long id, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null || !"ADMIN".equalsIgnoreCase(user.getRole())) {
            return "Unauthorized";
        }
        memberService.delete(id);
        return "Member deleted";
    }
}
