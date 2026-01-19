package com.apigateway.controller;


import com.apigateway.dto.ApiKeyDto;
import com.apigateway.entity.ApiKey;
import com.apigateway.service.ApiKeyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/apikey")
@RequiredArgsConstructor
public class ApiKeyController {

    private final ApiKeyService apiKeyService;

    @PostMapping("/add")
    public ApiKey addNewApiKey(@RequestBody ApiKeyDto apiKeyDto) {
        return apiKeyService.createApiKey(apiKeyDto);
    }

}
