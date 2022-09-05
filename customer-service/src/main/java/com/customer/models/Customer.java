package com.customer.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@RequiredArgsConstructor
public class Customer {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true)
    private String phone; //used as the account number

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String password;  //not using Spring security or Hashing of password  since it's a mini micro service

    @Column(nullable = false)
    private String lastName;

    private Integer max=0; // hardcoded for each user

    //No need for BVNs and emails since it's a mini micro service.
}
