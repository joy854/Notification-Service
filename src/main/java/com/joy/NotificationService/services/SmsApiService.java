package com.joy.NotificationService.services;

import com.joy.NotificationService.model.request.ImiConnectApi.ApiRequest;

public interface SmsApiService {
    public String smsSend(ApiRequest apiRequest);
}
