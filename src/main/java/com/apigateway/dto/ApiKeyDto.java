package com.apigateway.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApiKeyDto {

    private String key;
    private String user_id;
    private String status;
    private LocalDateTime expires_at;
    private LocalDateTime created_at;

}
