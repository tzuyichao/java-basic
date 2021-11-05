package com.example.jpa.paging.service;

import com.example.jpa.paging.model.Customer;
import com.example.jpa.paging.repository.CustomerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Page<Customer> findByName(String name, PageRequest pageRequest) {
        return null;
    }
}
