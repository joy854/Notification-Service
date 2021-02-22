package com.joy.NotificationService.services.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.joy.NotificationService.model.request.ImiConnectApi.ApiRequest;
import com.joy.NotificationService.model.response.ExternalApiResponse;
import com.joy.NotificationService.services.SmsApiService;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.conn.ConnectTimeoutException;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.*;

import java.net.SocketTimeoutException;
import java.time.Duration;
import java.util.concurrent.TimeoutException;

@Service
@Slf4j
public class SmsApiServiceImpl implements SmsApiService {

    @Override
    public ExternalApiResponse smsSend(ApiRequest apiRequest) throws Exception{
//        System.out.println(apiRequest);
        String url = "https://api.imiconnect.in/resources/v1/messaging";
        String key = "93ceffda-5941-11ea-9da9-025282c394f2";
        RestTemplate restTemplate = new RestTemplateBuilder()
                .rootUri(url)
                .setConnectTimeout(Duration.ofMillis(5000))
                .defaultHeader("key", key)
                .defaultHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .build();
        try {
            ExternalApiResponse response = restTemplate.postForObject(url, apiRequest, ExternalApiResponse.class);
//            System.out.println(response);
            return response;
        }
        catch (ResourceAccessException ex){
//            if(ex.getCause())
            log.error("Timeout");
            throw new TimeoutException("SmsApi Timeout");
        }
//        catch (HttpServerErrorException ex) {
//
//            if(ex.getRawStatusCode()== HttpStatus.GATEWAY_TIMEOUT.value()) {
//                log.error("Timeout");
//                throw new TimeoutException("SmsApi Timeout");
//            }
//        }
//        catch (HttpClientErrorException ex){
//            throw new TimeoutException("client");
//        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
            throw new Exception("Error");
        }


//            String response = restTemplate.postForObject(url,apiRequest,String.class);
//            ObjectMapper mapper = new ObjectMapper();
//            JsonNode rootNode = mapper.readTree(response);
//            JsonNode jsonNode = rootNode.path("response");
//            JsonNode transid = rootNode.at("/response/transid");


//            if(transid.toString().length()>0){
//                return mapper.treeToValue(jsonNode, ExternalApiResponse.class).toString();
//            }else{
//                return jsonNode.get(0).toString();
//            }
//        } catch (Exception ex){
//            ex.printStackTrace();
//        }
//        return null;
    }
}
