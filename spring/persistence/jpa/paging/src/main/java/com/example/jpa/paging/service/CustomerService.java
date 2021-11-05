package com.example.jpa.paging.service;

import com.example.jpa.paging.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface CustomerService {
    Page<Customer> findByName(String name, PageRequest pageRequest);
}
