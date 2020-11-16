package com.example.jpa.demo.repository;

import com.example.jpa.demo.model.Account;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class AccountRepositoryTest {
    @Autowired
    private AccountRepository accountRepository;

    @Test
    void injectRepositoryAreNotNull() {
        assertThat(accountRepository)
                .isNotNull();
    }

    @Test
    void test_id_1() {
        Account account = new Account();
        account.setAccountNumber("001");
        account.setAccountType("social");
        account.setAccountName("terence.chao");
        account.setActivated(false);
        accountRepository.save(account);

        Account accountB = new Account();
        accountB.setAccountNumber("001");
        accountB.setAccountType("social");
        accountB.setAccountName("john.doe");
        accountB.setActivated(false);
        accountRepository.save(accountB);

        for(Account acc : accountRepository.findAll()) {
            System.out.println(acc.toString());
        }
    }
}
