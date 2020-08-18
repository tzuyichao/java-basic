package com.example.demo.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class UserQueryServiceTest {
    private UserQueryService userQueryService = new UserQueryServiceImpl();

    @Test
    void find_user() throws UserQueryException {
        assertThat(userQueryService.findById(3))
                .isNotNull()
                .satisfies(user -> {
                   assertThat(user.getId()).isEqualTo(3);
                });
    }

    @Test
    void find_not_found_user() {
        assertThrows(UserQueryException.class, () -> {
            userQueryService.findById(10000);
        });
    }
}
