package com.apigateway.service.impl;

import com.apigateway.entity.ApiDefinition;
import com.apigateway.entity.ApiKey;
import com.apigateway.proxy.HttpForwarder;
import com.apigateway.repo.ApiDefinitionRepo;
import com.apigateway.repo.ApiKeyRepo;
import com.apigateway.service.GatewayService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class GatewayServiceImpl implements GatewayService {

    private final ApiDefinitionRepo apiDefinitionRepository;
    private final ApiKeyRepo apiKeyRepository;
    private final HttpForwarder httpForwarder;

    @Override
    public ResponseEntity<byte[]> handle(HttpServletRequest request) throws IOException {

        String apiKeyValue = request.getHeader("X-API-KEY");
        if (apiKeyValue == null || apiKeyValue.isEmpty()) {
            throw new RuntimeException("Missing API key");
        }

        String uri = request.getRequestURI();
        String path = uri.substring("/gateway/".length());
//        String apiName = path.split("/")[0];

        ApiKey apiKey =
                apiKeyRepository.findByKey(apiKeyValue)
                        .orElseThrow(() -> new RuntimeException("Invalid API key"));

        if (!apiKey.getStatus().equalsIgnoreCase("ACTIVE")) {
            throw new RuntimeException("API key inactive");
        }

        if (apiKey.getExpires_at() != null &&
                apiKey.getExpires_at().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("API key expired");
        }

        ApiDefinition apiDefinition =
                apiDefinitionRepository.findByApiName(apiKeyValue)
                        .orElseThrow(() -> new RuntimeException("API not found or disabled"));

        if (!apiKey.getApi_definition_id().equals(apiDefinition.getId())) {
            throw new RuntimeException("API key not allowed for this API");
        }

        return httpForwarder.forward(request, apiDefinition.getBase_url());
    }
}
