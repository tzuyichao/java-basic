package com.example.demo;

import com.example.demo.dto.UserQueryDto;
import com.example.demo.model.User;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.assertj.core.api.Assertions.assertThat;

public class ModelMapperTest {
    ModelMapper modelMapper = new ModelMapper();

    @Test
    void test_simple_match() {
        final long userId = 10;
        final String userName = "John Doe";
        final String userEmail = "john.doe@mail.com";

        User user = new User();
        user.setId(userId);
        user.setName(userName);
        user.setEmail(userEmail);

        UserQueryDto userQueryDto = modelMapper.map(user, UserQueryDto.class);

        assertThat(userQueryDto)
                .isNotNull()
                .satisfies(actual -> {
                    assertThat(actual.getId()).isEqualTo(userId);
                    assertThat(actual.getName()).isEqualTo(userName);
                    assertThat(actual.getEmail()).isEqualTo(userEmail);
                });
    }

}
