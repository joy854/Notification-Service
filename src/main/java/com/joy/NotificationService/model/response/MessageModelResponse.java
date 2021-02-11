package com.joy.NotificationService.model.response;

import lombok.Data;

@Data
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