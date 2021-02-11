package com.joy.NotificationService.services;

import com.joy.NotificationService.model.request.BlackListNumbers;

public interface BlackListService {
    public void saveNumber(BlackListNumbers blackListNumbers);
}
