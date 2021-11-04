package com.example.demo.account.repository;

import com.example.demo.account.domain.Account;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.math.BigDecimal;
import java.util.List;

public interface AccountRepository extends CrudRepository<Account, Long> {
    @Query("select * from account where name = :name")
    List<Account> findAccountsByName(String name);

    @Modifying
    @Query("update account set amount = :amount where id = :id")
    void changeAmount(long id, BigDecimal amount);
}
