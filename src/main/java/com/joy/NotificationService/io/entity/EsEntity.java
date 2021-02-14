package com.joy.NotificationService.io.entity;

import com.joy.NotificationService.model.request.Message;
import com.joy.NotificationService.shared.dto.MessageDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.Id;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Document(indexName = "sms")
public class EsEntity {

//    @Id
//    private String id;

    @Id
    private Integer id;
    private String phoneNumber;
    private String message;
    private Date createdAt = new Date();


    public EsEntity(MessageDto message) {

        this.id = message.getId();
        this.phoneNumber = message.getPhone_number();
        this.message = message.getMessage();
        this.createdAt = message.getCreated_at();

    }

}