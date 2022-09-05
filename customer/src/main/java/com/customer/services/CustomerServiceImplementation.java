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


@Data
@Service
@RequiredArgsConstructor
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

            restTemplate.postForObject("http://localhost:8086/api/v1/wallet/create?account_number={account_number}",null,String.class,phone_number);

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


        Boolean product = restTemplate.postForObject("http://localhost:8081/api/v1/products/find/{product_id}",null,Boolean.class,product_id);

        if(!product){
            throw  new CustomException("product does not exist");
        }
        else{
            Product product1 = restTemplate.getForObject("http://localhost:8081/api/v1/products/{product_id}",Product.class,product_id);
                restTemplate.postForObject("http://localhost:8086/api/v1/wallet/credit/{account}?amount={amount}",null,String.class,httpSession.getAttribute("phone_number"),product1.getLoan_amount());
                restTemplate.postForObject("http://localhost:8084/api/v1/loans/activate?account_number={account_number}&amount={amount}&tenure={tenure}",null,String.class,httpSession.getAttribute("phone_number"),product1.getLoan_amount(),product1.getTenure());

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
