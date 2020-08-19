package com.example.demo.service;

import com.example.demo.dto.UserCreateCommandDto;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import lombok.extern.java.Log;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import static org.springframework.util.Assert.notNull;

@Log
@Service
public class UserCommandServiceImpl implements UserCommandService {
    private UserRepository userRepository;
    private ModelMapper modelMapper;

    public UserCommandServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void create(UserCreateCommandDto userCreateCommandDto) {
        notNull(userCreateCommandDto, "input argument can not be null");
        User user = modelMapper.map(userCreateCommandDto, User.class);
        log.info(user.toString());
        userRepository.save(user);
    }
}
