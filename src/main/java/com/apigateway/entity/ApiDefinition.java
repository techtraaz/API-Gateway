package com.apigateway.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Table(name = "api_definitions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiDefinition {

    @Id
    private String id;

    private String api_name;
    private String base_url;
    private String user_id;
    private boolean enabled;
    private LocalDateTime created_at;

}
