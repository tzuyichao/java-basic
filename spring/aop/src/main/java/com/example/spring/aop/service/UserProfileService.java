package com.example.spring.aop.service;

import com.example.spring.aop.model.User;

public interface UserProfileService {
    User getUser(String accessToken);
}
