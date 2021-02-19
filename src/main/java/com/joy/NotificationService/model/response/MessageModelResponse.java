package com.joy.NotificationService.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MessageModelResponse {
    private DataResponse data;
    private ErrorResponse error;
    public MessageModelResponse(DataResponse data){
        this.data=data;
    }
    public MessageModelResponse(ErrorResponse error){
        this.error=error;
    }
}