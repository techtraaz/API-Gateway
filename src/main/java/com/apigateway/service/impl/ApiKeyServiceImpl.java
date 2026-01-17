package com.apigateway.service.impl;

import com.apigateway.dto.ApiKeyDto;
import com.apigateway.entity.ApiKey;
import com.apigateway.repo.ApiKeyRepo;
import com.apigateway.service.ApiKeyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApiKeyServiceImpl implements ApiKeyService {

    private final ApiKeyRepo apiKeyRepo;

    @Override
    public ApiKey createApiKey(ApiKeyDto apiKeyDto){

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
