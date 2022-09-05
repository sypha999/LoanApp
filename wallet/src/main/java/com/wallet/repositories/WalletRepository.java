package com.wallet.repositories;

import com.wallet.models.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface WalletRepository extends JpaRepository<Wallet,Long> {



    // @Query(value = "select * from wallet where account_number=?",nativeQuery = true)
    Optional <Wallet> findWalletByAccount(String account_number);
}
