package com.product.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@RequiredArgsConstructor
@Entity
@Data
public class Product {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double loan_amount;

    @Column(nullable = false)
    private Double interest_rate;

    @Column(nullable = false)
    private Integer tenure;
}
