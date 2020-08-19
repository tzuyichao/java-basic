package com.example.demo.dto;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

@ToString
@Data
public class UserCreateCommandDto {
    @NotEmpty
    private String name;
    @NotEmpty
    private String email;
}
