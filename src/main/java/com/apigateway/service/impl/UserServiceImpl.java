package com.apigateway.service.impl;

import com.apigateway.dto.UserDto;
import com.apigateway.entity.User;
import com.apigateway.repo.UserRepo;
import com.apigateway.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.expression.spel.ast.OpAnd;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    @Override
    public User createUser(UserDto user){

        User newUser = User.builder()
                .id(UUID.randomUUID().toString().substring(0, 10))
                .name(user.getName())
                .password(user.getPassword())
                .email(user.getEmail())
                .createdAt(LocalDateTime.now())
                .build();

        return userRepo.save(newUser);

    }

    @Override
    public User getUserById(String id){
        Optional<User> user = userRepo.findById(id);

        if(user.isPresent()){
            return user.get();
        }

        return null;

    }

    @Override
    public List<User> getAllUsers(){
        return userRepo.findAll();
    }

    @Override
    public void deleteUser(String id){
        userRepo.deleteById(id);
    }

}
