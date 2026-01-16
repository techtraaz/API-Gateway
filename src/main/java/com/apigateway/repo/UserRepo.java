package com.apigateway.repo;

import com.apigateway.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepo extends JpaRepository<User, String> {


}
