package com.joy.NotificationService.shared.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty("phone_number")
    private String phoneNumber;

    private String message;
    private Date createdAt = new Date();


    public EsDto(MessageDto message) {

        this.id = message.getId();
        this.phoneNumber = message.getPhone_number();
        this.message = message.getMessage();
        this.createdAt = message.getCreated_at();

    }

}
