package com.apigateway.controller;

import com.apigateway.service.ApiDefService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/apidef")
@RequiredArgsConstructor
public class ApiDefController {

    private final ApiDefService apiDefService;



}
