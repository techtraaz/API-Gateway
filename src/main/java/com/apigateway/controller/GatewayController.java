package com.apigateway.controller;

import com.apigateway.service.GatewayService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
@RequestMapping("/gateway")
@RequiredArgsConstructor
public class GatewayController {

   private final GatewayService gatewayService;


    @RequestMapping("/**")
    public ResponseEntity<byte[]> proxy(HttpServletRequest request) throws IOException {

        System.out.println("============== INCOMING REQUEST =================");

        return gatewayService.handle(request);

    }
}
