package com.joy.NotificationService.services.impl;

import com.joy.NotificationService.io.entity.BlackListEntity;
import com.joy.NotificationService.io.entity.MessageEntity;
import com.joy.NotificationService.model.request.ImiConnectApi.ApiRequest;
import com.joy.NotificationService.model.request.ImiConnectApi.Channels;
import com.joy.NotificationService.model.request.ImiConnectApi.Destination;
import com.joy.NotificationService.model.request.ImiConnectApi.Sms;
import com.joy.NotificationService.repository.BlackListRepository;
import com.joy.NotificationService.repository.MessageRepository;
import com.joy.NotificationService.services.KafkaConsumer;
import com.joy.NotificationService.services.SmsApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class KafkaConsumerImpl implements KafkaConsumer {

    @Autowired
    BlackListRepository blackListRepository;

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    SmsApiService smsApiService;

    @Override
//    @KafkaListener(topics = "notification.send_sms", groupId = "group_id"
//            , containerFactory = "userKafkaListenerFactory")
    @KafkaListener(topics = "notification.send_sms", groupId = "group_id")
    public void consume(Integer id) {
        System.out.println("Consumed id: " + id);
        BlackListEntity blackListEntity = blackListRepository.findById(id.toString()).orElse(null);
        MessageEntity messageEntity = messageRepository.findById(id).orElse(null);

        if (blackListEntity == null) {
            List<String> phoneNumber = new ArrayList<>();
            phoneNumber.add(messageEntity.getPhone_number());
            Sms sms = Sms.builder()
                    .text(messageEntity.getMessage())
                    .build();
            Channels channels = Channels.builder()
                    .sms(sms)
                    .build();
            Destination destination = Destination.builder()
                    .msisdn(phoneNumber)
                    .correlationId(messageEntity.getId())
                    .build();

            List<Destination> destinationList = new ArrayList<>();
            destinationList.add(destination);

            ApiRequest apiRequest = ApiRequest.builder()
                    .channels(channels).deliveryChannel("sms")
                    .destination(destinationList)
                    .build();

            String response=smsApiService.smsSend(apiRequest);
            System.out.println(response);
        } else {

        }
    }
}
