package com.apigateway.service.impl;

import com.apigateway.dto.ApiKeyDto;
import com.apigateway.dto.ResponseBean;
import com.apigateway.entity.ApiKey;
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
    public ResponseEntity<ResponseBean> createApiKey(ApiKeyDto apiKeyDto){

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
        ResponseBean responseBean = new ResponseBean("SUCCESS","Api Key Saved",savedKey);
        return ResponseEntity.ok(responseBean);

    }

    @Override
    public ResponseEntity<ResponseBean> getApiKeyByValue(String apikeyReq){

        Optional<ApiKey> apiKey = apiKeyRepo.findById(apikeyReq);
        ApiKey apikey = null ;
        if(apiKey.isPresent()){
            apikey =  apiKey.get();
            ResponseBean responseBean = new ResponseBean("SUCESS","Api Key",apikey);
            return ResponseEntity.ok(responseBean);
        }

        ResponseBean responseBean = new ResponseBean("FAILURE","Api Key not found","");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseBean);

    }

    @Override
    public ResponseEntity<ResponseBean> getAllApiKeys(){

        List<ApiKey> apiKeys = apiKeyRepo.findAll();

        ResponseBean responseBean = new ResponseBean("SUCCESS","Api Keys",apiKeys);

        return ResponseEntity.ok(responseBean);

    }


    @Override
    public ResponseEntity<ResponseBean> deleteApiKey(String id){

        apiKeyRepo.deleteById(id);
        ResponseBean responseBean = new ResponseBean("SUCCESS","Api Key Deleted",id);
        return ResponseEntity.ok(responseBean);

    }


    @Override
    public ResponseEntity<ResponseBean> updateApiKey(ApiKeyDto apiKeyDto){

        Optional <ApiKey> apiKey = apiKeyRepo.findByKey(apiKeyDto.getKey());

        ResponseBean responseBean = new ResponseBean("SUCCESS","Api Key Updated",apiKey.get());
        return ResponseEntity.ok(responseBean);

    }


}
