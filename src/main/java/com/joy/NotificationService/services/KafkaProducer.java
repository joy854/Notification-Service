package com.joy.NotificationService.services;


import com.joy.NotificationService.shared.dto.MessageDto;

public interface KafkaProducer {

    public void sendMessageId(Integer message) throws Exception;

}
