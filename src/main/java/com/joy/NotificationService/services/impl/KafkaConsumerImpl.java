package com.joy.NotificationService.services.impl;

import com.joy.NotificationService.services.KafkaConsumer;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerImpl implements KafkaConsumer {

    @Override
//    @KafkaListener(topics = "notification.send_sms", groupId = "group_id"
//            ,containerFactory = "userKafkaListenerFactory")
    @KafkaListener(topics = "notification.send_sms", groupId = "group_id")
    public void consume(String id) {
        System.out.println("HIiiiiiiiii "+ id);
    }
}
