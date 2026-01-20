package com.apigateway.dto;

import lombok.Data;

@Data
public class ResponseBean {

    private String status;
    private String message;
    private String data;

}
