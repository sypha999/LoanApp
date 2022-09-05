package com.wallet.controllers;

import com.wallet.repositories.WalletRepository;
import com.wallet.services.WalletServiceImplementation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class WalletController {


    final WalletServiceImplementation walletServiceImplementation;

    @PostMapping("/create")
    public String create(@RequestParam String account_number){
        walletServiceImplementation.createWallet(account_number);
        return "wallet created";
    }

    @PostMapping("/credit/{account_number}")
    public String credit(@PathVariable String account_number, @RequestParam Double amount){
        walletServiceImplementation.creditWallet(account_number, amount);
        return  "wallet credited";
    }

    @PostMapping("/debit/{account_number}")
    public String debit(@PathVariable String account_number, @RequestParam Double amount){
        walletServiceImplementation.creditWallet(account_number, amount);
        return  "wallet debited";
    }

}
