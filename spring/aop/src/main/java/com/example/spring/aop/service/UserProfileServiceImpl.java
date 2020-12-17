package com.example.spring.aop.service;

import com.example.spring.aop.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserProfileServiceImpl implements UserProfileService {
    @Override
    public User getUser(String accessToken) {
        return null;
    }
}
