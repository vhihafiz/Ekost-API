package com.example.ekost.service.impl;

import com.example.ekost.ConnectionUtil;
import com.example.ekost.entity.Customer;
import com.example.ekost.model.Request.CustomerRequest;
import com.example.ekost.model.Response.CustomerResponse;
import com.example.ekost.repository.CustomerRepository;
import com.example.ekost.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    @Override
    public CustomerResponse createCustomer(CustomerRequest request) {
        Customer customer = Customer.builder()
                .name(request.getName())
                .email(request.getEmail())
                .address(request.getAddress())
                .build();
        customerRepository.saveAndFlush(customer);
        return CustomerResponse.builder()
                .name(customer.getName())
                .email(customer.getEmail())
                .address(customer.getAddress())
                .build();
    }

    @Override
    public List<Customer> getAll() {
        List<Customer> customers = customerRepository.findAll();
        List<Customer> allCustomer = new ArrayList<>();
        for (Customer customer : customers) {
            allCustomer.add(Customer.builder()
                    .id(customer.getId())
                    .name(customer.getName())
                    .email(customer.getEmail())
                    .address(customer.getAddress())
                    .build());
        }
        return allCustomer;
    }

    @Override
    public Customer getById(String id) {
        Optional<Customer> customer = customerRepository.findBy(id);
        if(customer.isPresent()){
            return customer.get();
        } else
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id customer not found");
        }
    }

    @Override
    public void updateCustomer(Customer customer) {
        getById(customer.getId());
        customerRepository.updateCustomer(customer.getId(), customer.getName(), customer.getAddress(), customer.getEmail());
    }

    @Override
    public void deleteCustomer(String id) {
        Customer customer = getById(id);
        customerRepository.deleteCustomer(customer.getId());
    }
}
