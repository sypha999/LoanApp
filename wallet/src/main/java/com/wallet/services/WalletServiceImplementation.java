package com.wallet.services;

import com.wallet.exceptions.CustomException;
import com.wallet.models.Wallet;
import com.wallet.repositories.WalletRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Data
@RequiredArgsConstructor
@Service
public class WalletServiceImplementation implements WalletServices{

    @Autowired
    WalletRepository walletRepository;

    @Override
    public String createWallet(String account_number) {

        Optional <Wallet> walletExist = walletRepository.findWalletByAccount(account_number);
        if(walletExist.isPresent()) throw new CustomException("You already have an existing wallet");
        else {Wallet wallet = new Wallet();
        wallet.setAccount(account_number);
        wallet.setAmount(0.00);
        walletRepository.save(wallet);}
        return "Wallet created successfully";
    }

    @Override
    public String creditWallet(String account_number,Double amount) {

        Optional<Wallet> walletToCredit =  walletRepository.findWalletByAccount(account_number);
        if (walletToCredit.isEmpty()) throw new CustomException("Account does not exist");
        else{
            Wallet wallet=walletToCredit.get();
            wallet.setAmount(amount);
            walletRepository.save(wallet);
        }
        return "Wallet Credited";
    }

    @Override
    public String debitWallet(String account_number,Double amount) {

        Optional <Wallet> walletToDebit =  walletRepository.findWalletByAccount(account_number);
        if (walletToDebit.isEmpty()) throw new CustomException("Account does not exist");
        else{
            Wallet wallet=walletToDebit.get();
            if(wallet.getAmount()<amount) throw new CustomException("Insufficient funds");
            wallet.setAmount(wallet.getAmount()-amount);
            walletRepository.save(wallet);
        }

        return "Wallet Debited";
    }
}
