package com.apigateway.dto;

import lombok.Data;

@Data
public class ApiDefDto {

    private String apiName;
    private String baseUrl;
    private String userId;
    private boolean enabled;

}
