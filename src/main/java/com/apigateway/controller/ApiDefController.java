package com.apigateway.controller;

import com.apigateway.dto.ApiDefDto;
import com.apigateway.dto.ResponseBean;
import com.apigateway.entity.ApiDefinition;
import com.apigateway.service.ApiDefService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/apidef")
@RequiredArgsConstructor
public class ApiDefController {

    private final ApiDefService apiDefService;

    @PostMapping("/define")
    public ResponseEntity<ResponseBean> defineApi(@RequestBody ApiDefDto apiDef) {
        return apiDefService.addNewApi(apiDef);
    }

    @GetMapping("/get/{apiKey}")
    public ResponseEntity<ResponseBean> getApiById(@PathVariable String apiKey) {
        return apiDefService.getApiById(apiKey);
    }

    @GetMapping("/get/{apiName}")
    public ResponseEntity<ResponseBean> getApiByName(@PathVariable String apiName) {
        return apiDefService.getApiByName(apiName);
    }

    @GetMapping("/get/all")
    public ResponseEntity<ResponseBean> getAllApi() {
        return apiDefService.getAllApi();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseBean> deleteApi(@PathVariable String id) {
        return apiDefService.deleteApiById(id);
    }



}
