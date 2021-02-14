package com.joy.NotificationService.services.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.joy.NotificationService.model.request.ImiConnectApi.ApiRequest;
import com.joy.NotificationService.model.response.ExternalApiResponse;
import com.joy.NotificationService.services.SmsApiService;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SmsApiServiceImpl implements SmsApiService {

    @Override
    public String smsSend(ApiRequest apiRequest) {
        System.out.println(apiRequest);
        String url = "https://api.imiconnect.in/resources/v1/messaging";
        String key = "93ceffda-5941-11ea-9da9-025282c394f2";
        RestTemplate restTemplate = new RestTemplateBuilder()
                .rootUri(url)
                .defaultHeader("key", key)
                .defaultHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .build();
        try{
            String response = restTemplate.postForObject(url,apiRequest,String.class);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(response);
            JsonNode jsonNode = rootNode.path("response");
            JsonNode transid = rootNode.at("/response/transid");

            if(transid.toString().length()>0){
                return mapper.treeToValue(jsonNode, ExternalApiResponse.class).toString();
            }else{
                return jsonNode.get(0).toString();
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return "null";
    }
}
