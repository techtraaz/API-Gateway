package com.apigateway.controller;

import com.apigateway.proxy.HttpForwarder;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
public class GatewayController {

    private final HttpForwarder forwarder;

    public GatewayController(HttpForwarder forwarder) {
        this.forwarder = forwarder;
    }

    @RequestMapping("/**")
    public ResponseEntity<byte[]> proxy(HttpServletRequest request) throws IOException {

        System.out.println("============== INCOMING REQUEST =================");

        String targetBaseUrl = "http://localhost:2323";

        return forwarder.forward(request, targetBaseUrl);
    }
}
