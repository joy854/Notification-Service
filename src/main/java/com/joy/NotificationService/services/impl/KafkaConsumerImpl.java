package com.joy.NotificationService.services.impl;

import com.joy.NotificationService.io.entity.BlackListEntity;
import com.joy.NotificationService.io.entity.EsEntity;
import com.joy.NotificationService.io.entity.MessageEntity;
import com.joy.NotificationService.model.request.ImiConnectApi.ApiRequest;
import com.joy.NotificationService.model.request.ImiConnectApi.Channels;
import com.joy.NotificationService.model.request.ImiConnectApi.Destination;
import com.joy.NotificationService.model.request.ImiConnectApi.Sms;
import com.joy.NotificationService.repository.BlackListRepository;
import com.joy.NotificationService.repository.EsRepository;
import com.joy.NotificationService.repository.MessageRepository;
import com.joy.NotificationService.services.KafkaConsumer;
import com.joy.NotificationService.services.SmsApiService;
import com.joy.NotificationService.shared.dto.MessageDto;
import com.joy.NotificationService.util.MessageStatus;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
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
    EsRepository esRepository;

    @Autowired
    SmsApiService smsApiService;

    @Override
//    @KafkaListener(topics = "notification.send_sms", groupId = "group_id"
//            , containerFactory = "userKafkaListenerFactory")
    @KafkaListener(topics = "notification.send_sms", groupId = "group_id")
    public void consume(Integer messageDto) {
//        System.out.println("Consumer " + messageDto);
        MessageEntity messageEntity = messageRepository.findById(messageDto).orElse(null);
        BlackListEntity blackListEntity = blackListRepository.findById(messageEntity.getPhone_number()).orElse(null);

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

//          String response=smsApiService.smsSend(apiRequest);
//          System.out.println(response);
            messageEntity.setStatus(MessageStatus.SUCCESS);
            messageRepository.save(messageEntity);

            EsEntity entity=new EsEntity();
            entity.setCreatedAt(messageEntity.getCreated_at());
            BeanUtils.copyProperties(messageEntity,entity);
            esRepository.save(entity);


        } else {
            System.out.println("in blacklist");
            messageEntity.setStatus(MessageStatus.FAILED);
            messageEntity.setFailure_comments("Number in Blacklist");
            messageEntity.setFailure_code("403");
            messageRepository.save(messageEntity);

        }
    }
}
