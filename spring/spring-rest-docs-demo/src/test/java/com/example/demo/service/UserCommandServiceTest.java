package com.example.demo.service;

import com.example.demo.dto.UserCreateCommandDto;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class UserCommandServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private UserCommandService userCommandService = new UserCommandServiceImpl(userRepository, modelMapper);

    @Test
    void test_create_given_null_command() {
        assertThrows(IllegalArgumentException.class, () -> {
            userCommandService.create(null);
        });
    }

    @Test
    void test_create() {
        final String userName = "Test";
        final String userEmail = "test@mail.com";
        UserCreateCommandDto createCommand = new UserCreateCommandDto();
        createCommand.setName(userName);
        createCommand.setEmail(userEmail);

        User user = new User();
        user.setName(userName);
        user.setEmail(userEmail);

        given(modelMapper.map(createCommand, User.class)).willReturn(user);

        assertThat(userCommandService)
                .isNotNull()
                .satisfies(userCommandService1 -> {
                    userCommandService1.create(createCommand);
                });

    }
}
