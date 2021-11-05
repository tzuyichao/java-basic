package com.example.demo.service;

import com.example.demo.dto.UserCreateCommandDto;

public interface UserCommandService {
    void create(UserCreateCommandDto userCreateCommandDto);
}
