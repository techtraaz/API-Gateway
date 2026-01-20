package com.apigateway.service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface GatewayService {

    ResponseEntity<byte[]> handle(HttpServletRequest request)throws IOException;

}
