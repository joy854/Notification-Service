package com.joy.NotificationService.controllers;

import com.joy.NotificationService.model.request.BlackListNumber;
import com.joy.NotificationService.model.request.BlackListNumbers;
import com.joy.NotificationService.model.request.ElasticSearchInput;
import com.joy.NotificationService.model.request.Message;
import com.joy.NotificationService.model.response.*;
import com.joy.NotificationService.services.BlackListService;
import com.joy.NotificationService.services.ElasticSearchService;
import com.joy.NotificationService.services.MessageService;
import com.joy.NotificationService.shared.dto.BlackListDto;
import com.joy.NotificationService.shared.dto.EsDto;
import com.joy.NotificationService.shared.dto.MessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MainController {

    @Autowired
    MessageService messageService;

    @Autowired
    BlackListService blackListService;

    @Autowired
    ElasticSearchService elasticSearchService;

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

    @DeleteMapping("v1/blacklist")
    public BlackListAddResponse deleteNumber(@RequestBody BlackListNumber blackListNumber){
        boolean temp=blackListService.deleteNumber(blackListNumber.getPhone_number());
        BlackListAddResponse returnValue=new BlackListAddResponse();
        if(temp){
            returnValue.setData("Successfully Deleted");
        }
        else{
            returnValue.setData("Error: Number not Blacklisted...");
        }
        return returnValue;
    }

    @GetMapping("v1/blacklist")
    public List<BlackListDto> getAll(){
        List<BlackListDto> returnValue=blackListService.getAllNumbers();
        return returnValue;
    }

    @GetMapping("/v1/sms/{id}")
    public MessageDetailResponse getMessageDetail(@PathVariable Integer id){
        MessageDto messageDto=messageService.getMessageDetail(id);
        MessageDetailResponse returnValue=new MessageDetailResponse();
        returnValue.setData(messageDto);
        return returnValue;
    }

    @GetMapping ("/v1/message/{text}")
    public List<EsDto> getAllByText(@PathVariable String text){
        return elasticSearchService.findByMessage(text);
    }

    @GetMapping("v1/date")
    public List<EsDto> getAllBetweenDate(@RequestBody ElasticSearchInput elasticSearchInput){
        return elasticSearchService.findByDate(elasticSearchInput);
    }

    @GetMapping("v1/elastic")
    public List<EsDto> getAllElastic(){
        return elasticSearchService.findAll();
    }
}
