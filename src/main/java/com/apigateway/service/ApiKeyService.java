package com.apigateway.service;

import com.apigateway.dto.ApiKeyDto;
import com.apigateway.dto.ResponseBean;
import com.apigateway.entity.ApiKey;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface ApiKeyService {

    ResponseEntity<ResponseBean> createApiKey(ApiKeyDto apiKeyDto);
    ResponseEntity<ResponseBean> getApiKeyByValue(String apikey);
    ResponseEntity<ResponseBean> getAllApiKeys();
    ResponseEntity<ResponseBean> deleteApiKey(String id);
    ResponseEntity<ResponseBean> updateApiKey(ApiKeyDto apiKeyDto);

}
