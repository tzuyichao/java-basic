package com.example.demo.service;

import com.example.demo.model.User;

public interface UserQueryService {
    User findById(long id) throws UserQueryException;
}
