package com.joy.NotificationService.services;

import com.joy.NotificationService.model.request.ImiConnectApi.ApiRequest;
import com.joy.NotificationService.model.response.ExternalApiResponse;

public interface SmsApiService {
    public ExternalApiResponse smsSend(ApiRequest apiRequest);
}
