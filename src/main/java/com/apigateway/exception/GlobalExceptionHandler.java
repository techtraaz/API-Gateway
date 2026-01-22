package com.apigateway.exception;

import com.apigateway.dto.ResponseBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ResponseBean> handleRuntimeException(RuntimeException e){

        ResponseBean responseBean = new ResponseBean();
        responseBean.setStatus("500");
        responseBean.setMessage(e.getMessage());
        responseBean.setData("");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseBean);

    }

}
