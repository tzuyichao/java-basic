package com.example.jpa.paging.repository;

import com.example.jpa.paging.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.persistence.QueryHint;

import static org.hibernate.jpa.QueryHints.HINT_COMMENT;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {
    @QueryHints(value={@QueryHint(name=HINT_COMMENT, value="a query for pageable")})
    @Query("select c from Customer c where c.firstName=:name or c.lastName=:name")
    Page<Customer> findByName(@Param("name") String name, Pageable pageable);
}
