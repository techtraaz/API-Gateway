package com.apigateway.service;

import com.apigateway.dto.UserDto;
import com.apigateway.entity.User;

import java.util.List;

public interface UserService {

    User createUser(UserDto user);
    User getUserById(String id);
    List<User> getAllUsers();
    void deleteUser(String id);

}
