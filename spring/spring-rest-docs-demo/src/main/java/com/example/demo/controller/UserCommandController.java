package com.example.demo.controller;

import com.example.demo.dto.APIResponse;
import com.example.demo.dto.UserCreateCommandDto;
import com.example.demo.service.UserCommandService;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Log
@RestController
@RequestMapping("/api/user")
public class UserCommandController {
    private UserCommandService userCommandService;

    public UserCommandController(UserCommandService userCommandService) {
        this.userCommandService = userCommandService;
    }

    @PostMapping("")
    public APIResponse createCommand(@RequestBody @Valid UserCreateCommandDto createCommand, HttpServletResponse response) {
        log.info(createCommand.toString());
        userCommandService.create(createCommand);
        response.setStatus(HttpServletResponse.SC_CREATED);
        return APIResponse.builder().message("create success").build();
    }
}
