package com.apigateway.service.impl;

import com.apigateway.dto.ApiDefDto;
import com.apigateway.entity.ApiDefinition;
import com.apigateway.repo.ApiDefinitionRepo;
import com.apigateway.service.ApiDefService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class ApiDefServiceImpl implements ApiDefService {

    private final ApiDefinitionRepo apiDefRepo;

    @Override
    public ApiDefinition addNewApi(ApiDefDto apiDef){

        ApiDefinition apiDefinition = ApiDefinition.builder()
                .id(UUID.randomUUID().toString())
                .api_name(apiDef.getApiName())
                .user_id(apiDef.getUserId())
                .base_url(apiDef.getBaseUrl())
                .enabled(apiDef.isEnabled())
                .created_at(LocalDateTime.now())
                .build();

        ApiDefinition savedApi = apiDefRepo.save(apiDefinition);
        return savedApi;

    }

    @Override
    public ApiDefinition getApiById(String id){
        Optional<ApiDefinition> apiDefinition = apiDefRepo.findById(id);
        return apiDefinition.orElse(null);
    }

    @Override
    public ApiDefinition getApiByName(String name){
        Optional<ApiDefinition> apiDefinition = apiDefRepo.findByApi_name(name);
        return apiDefinition.orElse(null);
    }

    @Override
    public List<ApiDefinition> getAllApi(){
        List<ApiDefinition> apiDefinitions = apiDefRepo.findAll();
        return apiDefinitions;
    }

    @Override
    public void deleteApiById(String id){
        apiDefRepo.deleteById(id);
    }

}
