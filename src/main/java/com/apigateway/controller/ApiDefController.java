package com.apigateway.controller;

import com.apigateway.dto.ApiDefDto;
import com.apigateway.dto.ResponseBean;
import com.apigateway.entity.ApiDefinition;
import com.apigateway.service.ApiDefService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apidef")
@RequiredArgsConstructor
public class ApiDefController {

    private final ApiDefService apiDefService;

    @PostMapping("/define")
    public ResponseEntity<ResponseBean> defineApi(@RequestBody ApiDefDto apiDef) {

        ApiDefinition apiDefinition = apiDefService.addNewApi(apiDef);
        ResponseBean  responseBean = new ResponseBean();
        responseBean.setData(apiDefinition);
        responseBean.setStatus("SUCCESS");
        responseBean.setMessage("New Api Definition added successfully");
        return ResponseEntity.ok(responseBean);

    }

    @GetMapping("/get/{apiKey}")
    public ResponseEntity<ResponseBean> getApiById(@PathVariable String apiKey) {

        ResponseBean  responseBean = new ResponseBean();
        responseBean.setStatus("SUCCESS");
        responseBean.setMessage("Get Api By ID successfully");
        responseBean.setData(apiDefService.getApiById(apiKey));
        return ResponseEntity.ok(responseBean);


    }

    @GetMapping("/get/{apiName}")
    public ResponseEntity<ResponseBean> getApiByName(@PathVariable String apiName) {
        ApiDefinition apiDefinition =  apiDefService.getApiByName(apiName);

        if(apiDefinition == null) {
            ResponseBean  responseBean = new ResponseBean();
            responseBean.setStatus("ERROR");
            responseBean.setMessage("Api Definition not found");
            return ResponseEntity.ok(responseBean);
        }

        ResponseBean  responseBean = new ResponseBean();
        responseBean.setData(apiDefinition);
        responseBean.setStatus("SUCCESS");
        responseBean.setMessage("Api Definition found");
        return ResponseEntity.ok(responseBean);

    }

    @GetMapping("/get/all")
    public ResponseEntity<ResponseBean> getAllApi() {
        List<ApiDefinition> apiDefinitions = apiDefService.getAllApi();
        if(apiDefinitions.isEmpty()) {
            ResponseBean responseBean = new ResponseBean();
            responseBean.setStatus("ERROR");
            responseBean.setMessage("No Api Definitions found");
            return ResponseEntity.ok(responseBean);
        }

        ResponseBean  responseBean = new ResponseBean();
        responseBean.setData(apiDefinitions);
        responseBean.setStatus("SUCCESS");
        responseBean.setMessage("Api Definitions found");
        return ResponseEntity.ok(responseBean);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseBean> deleteApi(@PathVariable String id) {
        return ResponseEntity.ok(apiDefService.deleteApiById(id));
    }



}
