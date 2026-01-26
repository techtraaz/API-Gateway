package com.apigateway.service;

import com.apigateway.dto.ApiDefDto;
import com.apigateway.dto.ResponseBean;
import com.apigateway.entity.ApiDefinition;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ApiDefService {

    ApiDefinition addNewApi(ApiDefDto apiDef);
    ApiDefinition getApiById(String id);
    ApiDefinition getApiByName(String name);
    List<ApiDefinition> getAllApi();
    ResponseBean deleteApiById(String id);

}
