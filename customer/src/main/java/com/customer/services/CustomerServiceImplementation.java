package com.customer.services;

import com.customer.exceptions.CustomException;
import com.customer.models.Customer;
import com.customer.repositories.CustomerRepository;
import com.product.models.Product;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;
import java.util.*;

@RequiredArgsConstructor
@Data
@Service
public class CustomerServiceImplementation implements CustomerService{

    final CustomerRepository customerRepository;
    final HttpSession httpSession;
    final RestTemplate restTemplate;

    @Override
    public String createCustomer(String phone_number, String first_name, String last_name, String password, Integer max) {
        Optional <Customer> customerExist = customerRepository.findByPhone(phone_number);
        if(customerExist.isPresent()) throw new CustomException("Customer already exist");
        else{
            Customer customer = new Customer();
            customer.setFirstName(first_name);
            customer.setLastName(last_name);
            customer.setPassword(password);
            customer.setPhone(phone_number);
            customer.setMax(max); //would be hardcoded upon testing
            customerRepository.save(customer);
        }
        return "Registration Successful";
    }

    @Override
    public String login(String phone_number, String password) {
        Optional <Customer> validCred = customerRepository.findByPhoneAndPassword(phone_number,password);
        if(validCred.isEmpty()) throw new CustomException("Invalid Username or Password");
        else{
            Customer customer = validCred.get();
            httpSession.setAttribute("phone_number",customer.getPhone());
            httpSession.setAttribute("first_name",customer.getFirstName());
            httpSession.setAttribute("last_name",customer.getLastName());
            httpSession.setAttribute("max",customer.getMax());
        }
        return "Login Successful";
    }

    @Override
    public void logout() {
        httpSession.invalidate();
    }

    @Override
    public String selectProduct(Integer product_id) {

        Boolean product = restTemplate.getForObject("http://localhost:8081/api/v1/products/find?product_id={product_id}", Boolean.class,product_id);

        if(Boolean.FALSE.equals(product)){
            throw  new CustomException("product does not exist");
        }

        else{

        }
        return "Loan Activated";
    }

    @Override
    public List<Product> viewEligible() {

        List products = restTemplate.getForObject(
                "http://localhost:8081/api/v1/products/eligible?amount={amount}", List.class,httpSession.getAttribute("max")
        );
        return products;
    }
}
