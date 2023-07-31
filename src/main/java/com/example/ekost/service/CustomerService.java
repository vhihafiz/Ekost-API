package com.example.ekost.service;

import com.example.ekost.entity.Customer;
import com.example.ekost.model.Request.CustomerRequest;
import com.example.ekost.model.Response.CustomerResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {
    CustomerResponse createCustomer(CustomerRequest request);
    List<Customer> getAll();

    Customer getById(String id);

    void updateCustomer(Customer customer);

    void deleteCustomer(String id);
}
