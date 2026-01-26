package com.apigateway.service;

import com.apigateway.dto.ApiKeyDto;
import com.apigateway.dto.ResponseBean;
import com.apigateway.entity.ApiKey;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface ApiKeyService {

    ApiKey createApiKey(ApiKeyDto apiKeyDto);
    ApiKey getApiKeyByValue(String apikey);
    List<ApiKey> getAllApiKeys();
    ApiKey deleteApiKey(String id);
    ResponseEntity<ResponseBean> updateApiKey(ApiKeyDto apiKeyDto);

}
