package com.example.spring.aop.service;

import com.example.spring.aop.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserProfileServiceImpl implements UserProfileService {
    private static final Logger log = LoggerFactory.getLogger(UserProfileServiceImpl.class);

    public UserProfileServiceImpl() {
        log.debug("CONSTRUCTOR Called.");
    }

    @Override
    public User getUser(String accessToken) {
        return null;
    }
}
