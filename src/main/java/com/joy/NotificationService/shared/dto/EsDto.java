package com.joy.NotificationService.shared.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.Id;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EsDto {

    private Integer id;
    private String phone_number;
    private String message;
    private Date createdAt = new Date();


    public EsDto(MessageDto message) {

        this.id = message.getId();
        this.phone_number = message.getPhone_number();
        this.message = message.getMessage();
        this.createdAt = message.getCreated_at();

    }

}
