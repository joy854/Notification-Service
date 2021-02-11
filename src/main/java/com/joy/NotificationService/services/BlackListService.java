package com.joy.NotificationService.services;

import com.joy.NotificationService.model.request.BlackListNumbers;
import com.joy.NotificationService.shared.dto.BlackListDto;

import java.util.List;

public interface BlackListService {
    public void saveNumber(BlackListNumbers blackListNumbers);
    public boolean deleteNumber(String number);
    public List<BlackListDto> getAllNumbers();
}
