package com.apigateway.controller;


import com.apigateway.dto.ApiKeyDto;
import com.apigateway.dto.ResponseBean;
import com.apigateway.entity.ApiKey;
import com.apigateway.service.ApiKeyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/apikey")
@RequiredArgsConstructor
public class ApiKeyController {

    private final ApiKeyService apiKeyService;

    @PostMapping("/add")
    public ResponseEntity<ResponseBean> addNewApiKey(@RequestBody ApiKeyDto apiKeyDto) {
        ApiKey apikey = apiKeyService.createApiKey(apiKeyDto);
        ResponseBean responseBean = new ResponseBean();
        responseBean.setStatus("success");
        responseBean.setMessage("API key has been saved successfully");
        responseBean.setData(apikey);
        return ResponseEntity.ok(responseBean);
    }



    @GetMapping("/get/{apiKey}")
    public ResponseEntity<ResponseBean> getApiKey(@PathVariable("apiKey") String apiKey){
        ResponseBean responseBean = new ResponseBean();
        responseBean.setStatus("success");
        responseBean.setMessage("Retrieved API key");
        responseBean.setData(apiKeyService.getApiKeyByValue(apiKey));
        return ResponseEntity.ok(responseBean);
    }


    @GetMapping("/get/all")
    public ResponseEntity<ResponseBean> getAllApiKey(){
        ResponseBean responseBean = new ResponseBean();
        responseBean.setStatus("success");
        responseBean.setMessage("Retrieved all API keys");
        responseBean.setData(apiKeyService.getAllApiKeys());
        return ResponseEntity.ok(responseBean);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseBean> deleteApiKey(@PathVariable("id") String id){
        ResponseBean responseBean = new ResponseBean();
        responseBean.setStatus("success");
        responseBean.setMessage("Deleted API key");
        responseBean.setData(apiKeyService.deleteApiKey(id));
        return ResponseEntity.ok(responseBean);
    }


    @PutMapping("/update")
    public ResponseEntity<ResponseBean> updateApiKey(@RequestBody ApiKeyDto apiKeyDto){
        ResponseBean responseBean = new ResponseBean();
        responseBean.setStatus("success");
        responseBean.setMessage("Updated API key");
        responseBean.setData(apiKeyService.updateApiKey(apiKeyDto));
        return ResponseEntity.ok(responseBean);
    }




}
