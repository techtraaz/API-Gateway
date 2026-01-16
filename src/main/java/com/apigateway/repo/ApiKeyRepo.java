package com.apigateway.repo;

import com.apigateway.entity.ApiKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApiKeyRepo extends JpaRepository<ApiKey, String> {

    Optional<ApiKey> findById(String apiKey);

    Optional<ApiKey> findByKey(String key);


}
