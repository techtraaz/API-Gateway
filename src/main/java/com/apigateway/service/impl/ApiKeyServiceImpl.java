package com.apigateway.service.impl;

import com.apigateway.dto.ApiKeyDto;
import com.apigateway.entity.ApiKey;
import com.apigateway.repo.ApiKeyRepo;
import com.apigateway.service.ApiKeyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ApiKeyServiceImpl implements ApiKeyService {

    private final ApiKeyRepo apiKeyRepo;

    @Override
    public ApiKey createApiKey(ApiKeyDto apiKeyDto){

        ApiKey apiKey = ApiKey.builder()
                .id(UUID.randomUUID().toString())
                .user_id(apiKeyDto.getUser_id())
                .status(apiKeyDto.getStatus())
                .expires_at(apiKeyDto.getExpires_at())
                .key(apiKeyDto.getKey())
                .created_at(LocalDateTime.now())
                .build();

        return apiKeyRepo.save(apiKey);

    }

    @Override
    public ApiKey getApiKeyByValue(String apikey){

    }

    @Override
    List<ApiKey> getAllApiKeys(){

    }


    @Override
    void deleteApiKey(String id){

    }


    @Override
    ApiKey updateApiKey(ApiKeyDto apiKeyDto){

    }


}
