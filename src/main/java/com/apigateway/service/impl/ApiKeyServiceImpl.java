package com.apigateway.service.impl;

import com.apigateway.dto.ApiKeyDto;
import com.apigateway.dto.ResponseBean;
import com.apigateway.entity.ApiKey;
import com.apigateway.exception.NoDataFoundException;
import com.apigateway.repo.ApiKeyRepo;
import com.apigateway.service.ApiKeyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ApiKeyServiceImpl implements ApiKeyService {

    private final ApiKeyRepo apiKeyRepo;

    @Override
    public ApiKey createApiKey(ApiKeyDto apiKeyDto){

        ApiKey apiKey = ApiKey.builder()
                .id(UUID.randomUUID().toString())
                .api_definition_id(apiKeyDto.getApi_definition_id())
                .status(apiKeyDto.getStatus())
                .created_at(LocalDateTime.now())
                .expires_at(apiKeyDto.getExpires_at())
                .key(apiKeyDto.getKey())
                .created_at(LocalDateTime.now())
                .build();

        ApiKey savedKey = apiKeyRepo.save(apiKey);
        return savedKey;

    }

    @Override
    public ApiKey getApiKeyByValue(String apikeyReq){

        ApiKey apiKey = apiKeyRepo.findById(apikeyReq).
                orElseThrow(() -> new NoDataFoundException("No API key found for given key : " + apikeyReq));

        return apiKey;

    }

    @Override
    public List<ApiKey> getAllApiKeys(){

        List<ApiKey> apiKeys = apiKeyRepo.findAll();
        return apiKeys;

    }


    @Override
    public ApiKey deleteApiKey(String id){

        ApiKey existingKey = apiKeyRepo.findById(id).
                orElseThrow(() -> new NoDataFoundException("Api Key not found"));

        apiKeyRepo.deleteById(id);
        return existingKey;

    }


    @Override
    public ResponseEntity<ResponseBean> updateApiKey(ApiKeyDto apiKeyDto){

        Optional <ApiKey> apiKey = apiKeyRepo.findByKey(apiKeyDto.getKey());

        ResponseBean responseBean = new ResponseBean("SUCCESS","Api Key Updated",apiKey.get());
        return ResponseEntity.ok(responseBean);

    }


}
