package com.joy.NotificationService.io.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity(name = "sms_requests")
public class MessageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String phone_number;

    @Column(nullable = false)
    private String message;

    @Column(nullable = false)
    private String status;

    private String failure_code;

    private String failure_comments;

    @Column(nullable = false)
    private Date created_at=new Date();

    private Date updated_at;
}

