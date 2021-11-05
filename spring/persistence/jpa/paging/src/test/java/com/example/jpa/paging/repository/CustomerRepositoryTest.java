package com.example.jpa.paging.repository;

import com.example.jpa.paging.model.Customer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class CustomerRepositoryTest {
    @Autowired
    private CustomerRepository customerRepository;

    @BeforeEach
    void setup() {
        Customer customer1 = new Customer();
        customer1.setFirstName("John");
        customer1.setLastName("Doe");
        customerRepository.save(customer1);
        Customer customer2 = new Customer();
        customer2.setFirstName("Jane");
        customer2.setLastName("Doe");
        customerRepository.save(customer2);
        Customer customer3 = new Customer();
        customer3.setFirstName("Doe");
        customer3.setLastName("Long");
        customerRepository.save(customer3);
        Customer customer4 = new Customer();
        customer4.setFirstName("John");
        customer4.setLastName("Dave");
        customerRepository.save(customer4);
        Customer customer5 = new Customer();
        customer5.setFirstName("Doe");
        customer5.setLastName("Dave");
        customerRepository.save(customer5);
    }

    @AfterEach
    void cleanup() {
        customerRepository.deleteAll();
    }

    @Test
    void find_by_name() {
        Pageable pageable = PageRequest.of(0,2, Sort.Direction.DESC,"id");
        Page<Customer> resultPage = customerRepository.findByName("Doe", pageable);
        assertThat(resultPage)
                .isNotNull()
                .satisfies(resultIterator -> {
                    resultIterator.forEach(customer -> {
                        System.out.println(customer.toString());
                    });
                });
        while(resultPage.hasNext()) {
            Pageable nextPageable = resultPage.nextPageable();
            System.out.println(nextPageable.toString());
            resultPage = customerRepository.findByName("Doe", nextPageable);
            for(Customer customer : resultPage.getContent()) {
                System.out.println(customer.toString());
            }
        }
    }

    @Test
    void find_by_name2() {
        Pageable pageable = PageRequest.of(0,2, Sort.Direction.DESC,"id");
        Page<Customer> resultPage = customerRepository.findByName("Doe", pageable);
        assertThat(resultPage)
                .isNotNull()
                .satisfies(resultIterator -> {
                    resultIterator.forEach(customer -> {
                        System.out.println(customer.toString());
                    });
                });
        Pageable nextPageable = PageRequest.of(1,2, Sort.Direction.DESC,"id");
        System.out.println(nextPageable.toString());
        resultPage = customerRepository.findByName("Doe", nextPageable);
        for(Customer customer : resultPage.getContent()) {
            System.out.println(customer.toString());
        }
    }
}
