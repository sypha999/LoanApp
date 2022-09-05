package com.product.repositories;

import com.product.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    @Query(value = "select * from product where loan_amount<= ?",nativeQuery = true)
    List<Product> findByEligibility(Double amount);
}
