package com.apigateway.service;

import com.apigateway.dto.ApiDefDto;
import com.apigateway.dto.ResponseBean;
import com.apigateway.entity.ApiDefinition;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ApiDefService {

    ResponseEntity<ResponseBean> addNewApi(ApiDefDto apiDef);
    ResponseEntity<ResponseBean> getApiById(String id);
    ResponseEntity<ResponseBean> getApiByName(String name);
    ResponseEntity<ResponseBean> getAllApi();
    ResponseEntity<ResponseBean> deleteApiById(String id);

}
