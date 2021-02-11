package com.joy.NotificationService.controllers;

import com.joy.NotificationService.model.request.BlackListNumbers;
import com.joy.NotificationService.model.request.Message;
import com.joy.NotificationService.model.response.BlackListAddResponse;
import com.joy.NotificationService.model.response.DataResponse;
import com.joy.NotificationService.model.response.ErrorResponse;
import com.joy.NotificationService.model.response.MessageModelResponse;
import com.joy.NotificationService.services.BlackListService;
import com.joy.NotificationService.services.MessageService;
import com.joy.NotificationService.shared.dto.MessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @Autowired
    MessageService messageService;

    @Autowired
    BlackListService blackListService;

    @PostMapping("v1/sms/send")
    public MessageModelResponse send(@RequestBody Message msg) {
        MessageDto msgDto = messageService.storeRequest(msg);
        if (msgDto != null) {
            DataResponse data = new DataResponse();
            data.setRequest_id(msgDto.getId());
            data.setComments("Successfully Sent");
            MessageModelResponse returnValue = new MessageModelResponse(data);
            return returnValue;
        }
        ErrorResponse error = new ErrorResponse();
        error.setCode("Error...");
        error.setMessage("Server down...");
        MessageModelResponse returnValue = new MessageModelResponse(error);
        return returnValue;
    }

    @PostMapping("v1/blacklist")
    public BlackListAddResponse addNumbers(@RequestBody BlackListNumbers blackListNumbers){
        blackListService.saveNumber(blackListNumbers);
        BlackListAddResponse returnValue=new BlackListAddResponse();
        returnValue.setData("Successfully Blacklisted");
        return returnValue;
    }
}
