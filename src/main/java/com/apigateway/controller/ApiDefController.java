package com.apigateway.controller;

import com.apigateway.dto.ApiDefDto;
import com.apigateway.entity.ApiDefinition;
import com.apigateway.service.ApiDefService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/apidef")
@RequiredArgsConstructor
public class ApiDefController {

    private final ApiDefService apiDefService;

    @PostMapping("/define")
    public ApiDefinition defineApi(@RequestBody ApiDefDto apiDef) {
        return apiDefService.addNewApi(apiDef);
    }



}
