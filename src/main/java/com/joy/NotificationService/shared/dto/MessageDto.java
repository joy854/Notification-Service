package com.joy.NotificationService.shared.dto;

import lombok.Data;

import java.util.Date;

@Data
public class MessageDto {

    private Integer id;

    private String phone_number;

    private String message;

    private String status;

    private String failure_code;

    private String failure_comments;

    private Date created_at=new Date();

    private Date updated_at;
}

