package com.example.jpa.demo.repository;

import com.example.jpa.demo.model.Account;
import com.example.jpa.demo.model.AccountId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<Account, AccountId> {
}
