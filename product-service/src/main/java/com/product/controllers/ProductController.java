package com.product.controllers;

import com.product.models.Product;
import com.product.repositories.ProductRepository;
import com.product.services.ProductServiceImplementation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class ProductController {

    final ProductServiceImplementation productServiceImplementation;
    final ProductRepository productRepository;

    @PostMapping("/create")
    public String createProduct(@RequestParam Double amount,@RequestParam  Double interest,@RequestParam  Integer tenure){

        productServiceImplementation.createProduct(amount, interest, tenure);
        return "product created";
    }

    @GetMapping("/eligible")
    public List<Product> getEligible(@RequestParam Double amount){
        return productServiceImplementation.findEligible(amount);
    }

    @PostMapping("/find/{product_id}")
    public Boolean findProduct(@PathVariable Integer product_id){
       return productRepository.findById(Long.valueOf(product_id)).isPresent();
    }

    @GetMapping("/{product_id}")
    public Product getProduct(@PathVariable Integer product_id){
        return productRepository.findById(Long.valueOf(product_id)).get();
    }
}
