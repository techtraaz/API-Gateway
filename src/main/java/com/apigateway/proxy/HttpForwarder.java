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
import java.util.HashMap;
import java.util.Map;

@Component
public class HttpForwarder {

    private final RestTemplate restTemplate;

    public HttpForwarder(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<byte[]> forward(HttpServletRequest request, String targetBaseUrl) throws IOException {

        HttpMethod method = HttpMethod.valueOf(request.getMethod());

        String targetUrl = buildTargetUrl(request, targetBaseUrl);
        System.out.println("Method: " + request.getMethod());
        System.out.println("targetUrl: " + targetUrl);


        byte[] body = StreamUtils.copyToByteArray(request.getInputStream());

        HttpHeaders headers = new HttpHeaders();

        Map<String, String> incomingHeaders = extractHeaders(request);
        incomingHeaders.forEach(headers::set);


        HttpEntity<byte[]> requestEntity;

        if(incomingHeaders.containsKey("custom-header-present")) {
            requestEntity = new HttpEntity<>(body,headers);
        }else{
            requestEntity = new HttpEntity<>(body);
        }

        return restTemplate.exchange(
                targetUrl,
                method,
                requestEntity,
                byte[].class
        );
    }

    private String buildTargetUrl(HttpServletRequest request, String targetBaseUrl) {

        String uri = request.getRequestURI();
        String pathAfterGateway = uri.substring("/gateway".length());

        String query = request.getQueryString();

        if (query != null) {
            return targetBaseUrl + pathAfterGateway + "?" + query;
        }

        return targetBaseUrl + pathAfterGateway;
    }


    private Map<String,String> extractHeaders(HttpServletRequest request) {
        Map<String,String> headerMap = new HashMap<>();
        Enumeration<String> headerNames = request.getHeaderNames();

        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String headerValue = request.getHeader(headerName);
            headerMap.put(headerName,headerValue);
        }

        return headerMap;

    }



}
