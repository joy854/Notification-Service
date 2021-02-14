package com.joy.NotificationService.model.request.ImiConnectApi;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Destination {
    private List<String> msisdn;

    @JsonProperty("correlationid")
    private Integer correlationId;
}
