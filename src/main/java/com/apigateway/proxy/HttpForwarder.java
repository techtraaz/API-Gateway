package com.apigateway.proxy;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Enumeration;

@Component
public class HttpForwarder {

    private final RestTemplate restTemplate;

    public HttpForwarder(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<byte[]> forward(HttpServletRequest request, String targetBaseUrl) throws IOException {

        HttpMethod method = HttpMethod.valueOf(request.getMethod());

        String targetUrl = buildTargetUrl(request, targetBaseUrl);

        byte[] body = StreamUtils.copyToByteArray(request.getInputStream());

        HttpEntity<byte[]> httpEntity = new HttpEntity<>(body);

        return restTemplate.exchange(
                targetUrl,
                method,
                httpEntity,
                byte[].class
        );
    }

    private String buildTargetUrl(HttpServletRequest request, String targetBaseUrl) {

        String Uri = request.getRequestURI();

        String query = request.getQueryString();

        String finalUrl;

        if (query != null) {
            finalUrl = targetBaseUrl + Uri + query;
        }else{
            finalUrl = targetBaseUrl + Uri;
        }

        return finalUrl;

    }



}
