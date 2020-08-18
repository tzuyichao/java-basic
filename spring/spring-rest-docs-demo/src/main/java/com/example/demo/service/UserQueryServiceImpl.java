package com.example.demo.service;

import com.example.demo.model.User;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class UserQueryServiceImpl implements UserQueryService {
    private Map<Long, User> userPool = new ConcurrentHashMap<>();

    public UserQueryServiceImpl() {
        User user1 = new User();
        user1.setId(1);
        user1.setName("John Doe");
        user1.setEmail("john.doe@mail.com");

        User user2 = new User();
        user1.setId(3);
        user1.setName("Jane Doe");
        user1.setEmail("jane.doe@mail.com");

        userPool.put(user1.getId(), user1);
        userPool.put(user2.getId(), user2);
    }

    @Override
    public User findById(long id) throws UserQueryException {
        if(userPool.containsKey(id)) {
            return userPool.get(id);
        }
        throw new UserQueryException(UserQueryException.ErrorStatus.NOT_FOUND);
    }
}
