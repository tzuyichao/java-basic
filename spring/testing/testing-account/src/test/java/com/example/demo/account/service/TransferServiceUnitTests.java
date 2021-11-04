package com.example.demo.account.service;

import com.example.demo.account.domain.Account;
import com.example.demo.account.repository.AccountRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class TransferServiceUnitTests {
    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private TransferService transferService = new TransferServiceImpl(this.accountRepository);

    @Test
    @DisplayName("Test the amount is transferred from one account to another if no exceptions occurs.")
    public void moneyTransferHappyFlow() {
        Account sender = new Account();
        sender.setId(1);
        sender.setAmount(new BigDecimal(1000));

        Account destination = new Account();
        destination.setId(2);
        destination.setAmount(new BigDecimal(1000));

        given(accountRepository.findById(sender.getId()))
                .willReturn(Optional.of(sender));
        given(accountRepository.findById(destination.getId()))
                .willReturn(Optional.of(destination));

        transferService.transferMoney(sender.getId(), destination.getId(), new BigDecimal(100));

        verify(accountRepository)
                .changeAmount(1, new BigDecimal(900));
        verify(accountRepository)
                .changeAmount(2, new BigDecimal(1100));
    }

    @Test
    public void moneyTransferDestinationAccountNotFoundFlow() {
        Account sender = new Account();
        sender.setId(1);
        sender.setAmount(new BigDecimal(1000));

        given(accountRepository.findById(sender.getId()))
                .willReturn(Optional.of(sender));
        given(accountRepository.findById(2L))
                .willReturn(Optional.empty());

        assertThrows(AccountNotFoundException.class, () -> {
            transferService.transferMoney(1, 2, new BigDecimal(100));
        });

        verify(accountRepository, never())
                .changeAmount(anyLong(), any());
    }
}
