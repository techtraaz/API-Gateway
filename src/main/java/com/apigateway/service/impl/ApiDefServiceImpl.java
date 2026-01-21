package com.apigateway.service.impl;

import com.apigateway.dto.ApiDefDto;
import com.apigateway.dto.ResponseBean;
import com.apigateway.entity.ApiDefinition;
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
    public ResponseEntity<ResponseBean> addNewApi(ApiDefDto apiDef){

        ApiDefinition apiDefinition = ApiDefinition.builder()
                .id(UUID.randomUUID().toString())
                .apiName(apiDef.getApiName())
                .user_id(apiDef.getUserId())
                .base_url(apiDef.getBaseUrl())
                .enabled(apiDef.isEnabled())
                .created_at(LocalDateTime.now())
                .build();

        ApiDefinition savedApi = apiDefRepo.save(apiDefinition);
        ResponseBean response = new ResponseBean();
        response.setStatus("success");
        response.setData(savedApi);
        response.setMessage("Api definition added");

        return ResponseEntity.ok(response);

    }

    @Override
    public ResponseEntity<ResponseBean> getApiById(String id) {

        Optional<ApiDefinition> apiDefinition = apiDefRepo.findById(id);

        if (apiDefinition.isPresent()) {
            ResponseBean response = new ResponseBean();
            response.setData(apiDefinition.get());
            response.setMessage("API found");
            response.setStatus("SUCCESS");

            return ResponseEntity.ok(response);
        } else {
            ResponseBean response = new ResponseBean();
            response.setMessage("API not found");
            response.setStatus("NOT_FOUND");

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }


    @Override
    public ResponseEntity<ResponseBean> getApiByName(String name){

        Optional<ApiDefinition> apiDefinition = apiDefRepo.findByApiName(name);

        if(apiDefinition.isPresent()){
            ResponseBean response = new ResponseBean();
            response.setData(apiDefinition.get());
            response.setMessage("API found");
            response.setStatus("SUCCESS");
            return  ResponseEntity.ok(response);

        }else{
            ResponseBean response = new ResponseBean();
            response.setMessage("API not found");
            response.setStatus("NOT_FOUND");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

    }

    @Override
    public ResponseEntity<ResponseBean> getAllApi(){
        List<ApiDefinition> apiDefinitions = apiDefRepo.findAll();
        ResponseBean response = new ResponseBean();
        response.setStatus("SUCCESS");
        response.setData(apiDefinitions);
        response.setMessage("All api definitions found");

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ResponseBean> deleteApiById(String id){
        apiDefRepo.deleteById(id);
        ResponseBean response = new ResponseBean();
        response.setStatus("SUCCESS");
        response.setMessage("Deleted API definition");
        return ResponseEntity.ok(response);

    }

}
