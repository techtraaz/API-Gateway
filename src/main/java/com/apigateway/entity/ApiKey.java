package com.apigateway.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Table(name = "api_keys")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiKey {

    @Id
    private String id;

    private String key_value;
    private String user_id;
    private String status;
    private LocalDateTime expires_at;
    private LocalDateTime created_at;


}
