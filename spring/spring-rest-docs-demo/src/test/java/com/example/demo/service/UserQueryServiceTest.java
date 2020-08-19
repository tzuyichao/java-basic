package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class UserQueryServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserQueryService userQueryService = new UserQueryServiceImpl(userRepository);

    @Test
    void find_user() throws UserQueryException {
        User user3 = new User();
        user3.setId(3L);
        user3.setName("John Doe");
        user3.setEmail("john.doe@mail.com");
        given(userRepository.findById(3L)).willReturn(Optional.of(user3));

        assertThat(userQueryService.findById(3))
                .isNotNull()
                .satisfies(user -> {
                   assertThat(user.getId()).isEqualTo(3);
                });
    }

    @Test
    void find_not_found_user() {
        given(userRepository.findById(10000L)).willReturn(Optional.empty());

        assertThrows(UserQueryException.class, () -> {
            userQueryService.findById(10000);
        });
    }
}
