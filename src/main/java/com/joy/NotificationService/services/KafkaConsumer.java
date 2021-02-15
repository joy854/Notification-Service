package com.joy.NotificationService.services;

import com.joy.NotificationService.shared.dto.MessageDto;

public interface KafkaConsumer {
    public void consume(Integer messageDto);
}
