package com.customer.services;

import com.product.models.Product;

import java.util.List;

public interface CustomerService {

    String createCustomer(String phone_number,String first_name,String last_name,String password,Integer max);
    String login(String phone_number,String password);
    void logout();
    String selectProduct (Integer product_id);
    List<Product> viewEligible();

}
