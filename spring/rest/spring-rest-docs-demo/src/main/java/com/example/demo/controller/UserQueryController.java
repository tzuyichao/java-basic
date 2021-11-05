package com.example.demo.controller;

import com.example.demo.dto.UserQueryDto;
import com.example.demo.service.UserQueryException;
import com.example.demo.service.UserQueryService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserQueryController {
    private UserQueryService userQueryService;

    public UserQueryController(UserQueryService userQueryService) {
        this.userQueryService = userQueryService;
    }

    @GetMapping("/{id}")
    public UserQueryDto findById(@PathVariable("id") long id) throws UserQueryException {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(userQueryService.findById(id), UserQueryDto.class);
    }
}
