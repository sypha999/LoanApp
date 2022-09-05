package com.product.services;

import com.product.exceptions.CustomException;
import com.product.models.Product;
import com.product.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductServiceImplementation implements ProductService{

    final ProductRepository productRepository;

    @Override
    public String createProduct(Double amount, Double interest, Integer tenure) {
        Product product = new Product();
        product.setLoan_amount(amount);
        product.setInterest_rate(interest);
        product.setTenure(tenure);
        productRepository.save(product);
        return "Product created successfully";
    }

    @Override
    public List<Product> findEligible(Double amount) {
        List<Product> pro = productRepository.findByEligibility(amount);
        if(pro.isEmpty()) throw new CustomException("You are not eligible at the moment please check back later");
        return pro;
    }
}
