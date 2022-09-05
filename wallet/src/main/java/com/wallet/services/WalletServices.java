package com.wallet.services;

public interface WalletServices {
    String createWallet(String customer_account_number);
    String creditWallet(String account_number, Double amount);
    String debitWallet(String account_number, Double amount);
}
