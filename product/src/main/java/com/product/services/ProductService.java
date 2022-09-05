package com.product.services;

import com.product.models.Product;

import java.util.List;

public interface ProductService {
    String createProduct(Double amount,Double interest,Integer tenure);
    List<Product> findEligible(Double amount);
}
