package com.apigateway.service.impl;

import com.apigateway.dto.ApiDefDto;
import com.apigateway.dto.ResponseBean;
import com.apigateway.entity.ApiDefinition;
import com.apigateway.exception.NoDataFoundException;
import com.apigateway.repo.ApiDefinitionRepo;
import com.apigateway.service.ApiDefService;
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
public class ApiDefServiceImpl implements ApiDefService {

    private final ApiDefinitionRepo apiDefRepo;

    @Override
    public ApiDefinition addNewApi(ApiDefDto apiDef){

        ApiDefinition apiDefinition = ApiDefinition.builder()
                .id(UUID.randomUUID().toString())
                .apiName(apiDef.getApiName())
                .user_id(apiDef.getUserId())
                .base_url(apiDef.getBaseUrl())
                .enabled(apiDef.isEnabled())
                .created_at(LocalDateTime.now())
                .build();

        ApiDefinition savedApi = apiDefRepo.save(apiDefinition);
        return  savedApi;

    }

    @Override
    public ApiDefinition getApiById(String id) {

       return apiDefRepo.findById(id)
               .orElseThrow(()->new NoDataFoundException("No API found with id " + id));

    }


    @Override
    public ApiDefinition getApiByName(String name){

        return apiDefRepo.findByApiName(name).
                orElseThrow(()->new NoDataFoundException("No API found with name " + name));

    }

    @Override
    public List<ApiDefinition> getAllApi(){

        List<ApiDefinition> apiDefinitions = apiDefRepo.findAll();
        return apiDefinitions;
    }

    @Override
    public ResponseBean deleteApiById(String id){

        ApiDefinition apiDefinition = apiDefRepo.findById(id).
                orElseThrow(()->new NoDataFoundException("No API found with id " + id));

        apiDefRepo.delete(apiDefinition);
        ResponseBean responseBean = new ResponseBean();
        responseBean.setMessage("Success");
        responseBean.setMessage("Successfully deleted API");
        responseBean.setData(apiDefinition);
        return responseBean;

    }

}
