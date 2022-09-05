package com.customer.repositories;

import com.customer.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {

    Optional <Customer> findByPhone(String phone_number);
    Optional <Customer>findByPhoneAndPassword(String phone_number, String password);

}
