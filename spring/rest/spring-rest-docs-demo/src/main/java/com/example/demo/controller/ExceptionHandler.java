package com.example.demo.controller;

import com.example.demo.dto.APIResponse;
import com.example.demo.service.UserQueryException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler({UserQueryException.class})
    @ResponseBody
    public APIResponse handleUserQueryException(UserQueryException e, HttpServletResponse response) {
        switch (e.getStatus()) {
            case NOT_FOUND -> response.setStatus(HttpStatus.NOT_FOUND.value());
        }
        return APIResponse.builder().message(e.getMessage()).build();
    }
}
