package com.joy.NotificationService.services;

import com.joy.NotificationService.model.request.Message;
import com.joy.NotificationService.shared.dto.MessageDto;

public interface MessageService {
    public MessageDto storeRequest(Message msg) throws Exception;
    public MessageDto getMessageDetail(Integer id);
}
