package com.loans.services;

public interface LoanService {

    String activateLoan(String account_number, Double amount, Integer tenure);
}
