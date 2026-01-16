package com.apigateway.repo;

import com.apigateway.entity.ApiKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApiKeyRepo extends JpaRepository<ApiKey, String> {



}
