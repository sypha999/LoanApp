package com.loans.models;

import com.loans.enums.Status;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Entity
@Data
public class Loans {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    private LocalDateTime date;

    @Enumerated(EnumType.STRING)
    private Status status;

    private String account_number;

    private Double amount;

    private Integer tenure;
}
