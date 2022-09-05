package com.loans.controllers;

import com.loans.services.LoanServiceImplementation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class LoanController {
    final LoanServiceImplementation loanServiceImplementation;

    @PostMapping("/activate")
    public String activate(@RequestParam String account_number,@RequestParam Double amount,@RequestParam Integer tenure){
        loanServiceImplementation.activateLoan(account_number, amount, tenure);
        return "Loan Activated";
    }
}
