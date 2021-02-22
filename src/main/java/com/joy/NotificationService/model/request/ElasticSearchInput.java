package com.joy.NotificationService.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ElasticSearchInput {

    private String startDate;
    private String endDate;
    private String phoneNumber;

}
