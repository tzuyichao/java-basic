package com.example.demo.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
public class APIResponse {
    @Getter
    private String message;
}
