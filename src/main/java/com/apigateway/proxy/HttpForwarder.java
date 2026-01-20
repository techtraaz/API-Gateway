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
        System.out.println("Method: " + request.getMethod());
        System.out.println("targetUrl: " + targetUrl);


        byte[] body = StreamUtils.copyToByteArray(request.getInputStream());

        HttpHeaders headers = new HttpHeaders();
        headers.set("channel","ruchith");
        headers.setContentType(MediaType.APPLICATION_JSON);



        HttpEntity<byte[]> httpEntity = new HttpEntity<>(body,headers);

        return restTemplate.exchange(
                targetUrl,
                method,
                httpEntity,
                byte[].class
        );
    }

    private String buildTargetUrl(HttpServletRequest request, String targetBaseUrl) {

        String uri = request.getRequestURI(); // /gateway/person/all
        String pathAfterGateway = uri.substring("/gateway".length()); // /person/all

        String query = request.getQueryString();

        if (query != null) {
            return targetBaseUrl + pathAfterGateway + "?" + query;
        }

        return targetBaseUrl + pathAfterGateway;
    }




}
