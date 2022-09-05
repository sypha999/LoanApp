package com.customer.controller;

import com.customer.services.CustomerServiceImplementation;
import com.product.models.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class CustomerController {
    final CustomerServiceImplementation customerServiceImplementation;

    @GetMapping("/eligible")
    public List<Product> viewEligible(){
        return customerServiceImplementation.viewEligible();
    }

    @PostMapping("/register")
    public String register(@RequestParam String phone_number,@RequestParam String first_name,@RequestParam String last_name,@RequestParam String password,@RequestParam Integer max){
        customerServiceImplementation.createCustomer(phone_number,first_name,last_name,password,max);
        return "registration successful";
    }

    @GetMapping("/logout")
    public void logout(){
        customerServiceImplementation.logout();
    }

    @PostMapping("/login")
    public String login(@RequestParam String phone_number,@RequestParam String password){
        customerServiceImplementation.login(phone_number, password);
        return "login successful";
    }

    @PostMapping("/select")
    public String select(@RequestParam Integer product_id){
        customerServiceImplementation.selectProduct(product_id);
        return "loan activated";
    }

}
