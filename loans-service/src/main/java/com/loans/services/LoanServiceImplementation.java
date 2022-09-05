package com.loans.services;

import com.loans.enums.Status;
import com.loans.models.Loans;
import com.loans.repositories.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class LoanServiceImplementation implements LoanService{

    final LoanRepository loanRepository;
    Status status;



    @Override
    public String activateLoan(String account_number, Double amount, Integer tenure) {
        Loans loan = new Loans();
        loan.setAccount_number(account_number);
        loan.setDate(LocalDateTime.now());
        loan.setStatus(status.ACTIVE);
        loan.setTenure(tenure);
        loan.setAmount(amount);
        loanRepository.save(loan);
        return "Loan Initiated";
    }
}
