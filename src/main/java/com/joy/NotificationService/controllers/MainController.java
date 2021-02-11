package com.joy.NotificationService.controllers;

import com.joy.NotificationService.model.request.Message;
import com.joy.NotificationService.model.response.DataResponse;
import com.joy.NotificationService.model.response.MessageModelResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    @PostMapping("v1/sms/send")
    public MessageModelResponse send(@RequestBody Message msg){
        DataResponse data=new DataResponse();
        data.setRequest_id(234);
        data.setComments("hiii");
        MessageModelResponse returnValue=new MessageModelResponse(data);

        return returnValue;
    }
}
