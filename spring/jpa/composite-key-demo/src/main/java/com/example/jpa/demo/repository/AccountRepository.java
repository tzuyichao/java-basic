package com.example.jpa.demo.repository;

import com.example.jpa.demo.model.Account;
import com.example.jpa.demo.model.AccountId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface AccountRepository extends CrudRepository<Account, AccountId> {
    @Query("SELECT account FROM Account account WHERE account.accountNumber = :accountNumber")
    Collection<Account> findByAccountNumber(String accountNumber);
}
