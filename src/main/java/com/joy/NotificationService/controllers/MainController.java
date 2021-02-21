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
import com.joy.NotificationService.util.MessageStatus;
import com.joy.NotificationService.util.exceptions.InvalidRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Object> send(@RequestBody Message msg) {

        if(msg.getMessage().trim().isEmpty()){
            throw new InvalidRequestException("Message cannot be empty");
        }
        if(msg.getPhone_number().trim().isEmpty()){
            return new ResponseEntity<>(new MessageModelResponse(new ErrorResponse("INVALID_REQUEST","phone_number is mandatory")), HttpStatus.BAD_REQUEST);
        }
        MessageDto msgDto = messageService.storeRequest(msg);
        if (msgDto!=null) {
            DataResponse data = new DataResponse();
            data.setRequest_id(msgDto.getId());
            data.setComments("Successfully Sent");
            MessageModelResponse returnValue = new MessageModelResponse(data);
            return new ResponseEntity<>(returnValue,HttpStatus.OK);
        }
//        ErrorResponse error = new ErrorResponse();
//        error.setCode("Error...");
//        error.setMessage("Server down...");
//        MessageModelResponse returnValue = new MessageModelResponse(error);
        return new ResponseEntity<>(new MessageModelResponse(new ErrorResponse("Bad Service","Server Down")),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("v1/blacklist")
    public ResponseEntity<Object> addNumbers(@RequestBody BlackListNumbers blackListNumbers){
        if(blackListNumbers.getPhone_numbers().isEmpty()){
            return new ResponseEntity<>(new ErrorResponse("INVALID_REQUEST","No number to add"),HttpStatus.BAD_REQUEST);
        }
        blackListService.saveNumber(blackListNumbers);
        BlackListAddResponse returnValue=new BlackListAddResponse();
        returnValue.setData("Successfully Blacklisted");

        return new ResponseEntity<>(returnValue,HttpStatus.OK);
    }

    @DeleteMapping("v1/blacklist")
    public ResponseEntity<Object> deleteNumber(@RequestBody BlackListNumber blackListNumber){
        if(blackListNumber.getPhone_number().isEmpty())
            return new ResponseEntity<>(new ErrorResponse("INVALID_REQUEST","phone_number cannot be empty"),HttpStatus.BAD_REQUEST);
        boolean temp=blackListService.deleteNumber(blackListNumber.getPhone_number());
        BlackListAddResponse returnValue=new BlackListAddResponse();
        if(temp){
            returnValue.setData("Successfully Deleted");
            return new ResponseEntity<>(returnValue,HttpStatus.OK);
        }

        returnValue.setData("Error: Number not Blacklisted...");
        return new ResponseEntity<>(returnValue,HttpStatus.BAD_REQUEST);
    }

    @GetMapping("v1/blacklist")
    public List<BlackListDto> getAll(){
        List<BlackListDto> returnValue=blackListService.getAllNumbers();
        return returnValue;
    }

    @GetMapping( path = "v1/sms/{id}",produces = {MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Object> getMessageDetail(@PathVariable Integer id){

        MessageDto messageDto=messageService.getMessageDetail(id);
        System.out.println(messageDto);

        if(messageDto!=null){
            MessageDetailResponse returnValue=new MessageDetailResponse();
            returnValue.setData(messageDto);
            return new ResponseEntity<>(returnValue,HttpStatus.OK);
        }
        return new ResponseEntity<>(new MessageModelResponse(
                new ErrorResponse("INVALID_REQUEST","request_id not found")),HttpStatus.BAD_REQUEST);
    }

    @GetMapping ("/v1/message/{text}")
    public List<EsDto> getAllByText(@PathVariable String text, @RequestParam(value = "page",defaultValue = "0") int page,
                                    @RequestParam(value = "size",defaultValue = "2") int size){
        return elasticSearchService.findByMessage(text,page,size);
    }

    @PostMapping("v1/date")
    public List<EsDto> getAllBetweenDate(@RequestBody ElasticSearchInput elasticSearchInput,
                                         @RequestParam(value = "page",defaultValue = "0") int page,
                                         @RequestParam(value = "size",defaultValue = "2") int size){
        return elasticSearchService.findByDate(elasticSearchInput,page,size);
    }

    @GetMapping("v1/elastic")
    public List<EsDto> getAllElastic(){
        return elasticSearchService.findAll();
    }
}
