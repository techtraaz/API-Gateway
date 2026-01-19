package com.apigateway.service;

import com.apigateway.dto.ApiKeyDto;
import com.apigateway.entity.ApiKey;

import java.util.List;


public interface ApiKeyService {

    ApiKey createApiKey(ApiKeyDto apiKeyDto);
    ApiKey getApiKeyByValue(String apikey);
    List<ApiKey> getAllApiKeys();
    void deleteApiKey(String id);
//    ApiKey updateApiKey(ApiKeyDto apiKeyDto);

}
