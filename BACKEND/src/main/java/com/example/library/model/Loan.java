package com.example.library.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Loan {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne private User member;
    @ManyToOne private Book book;

    private LocalDate loanDate;
    private LocalDate returnDate;
    private boolean returned;
}
