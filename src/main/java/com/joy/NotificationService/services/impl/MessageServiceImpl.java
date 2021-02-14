package com.joy.NotificationService.services.impl;

import com.joy.NotificationService.io.entity.MessageEntity;
import com.joy.NotificationService.model.request.Message;
import com.joy.NotificationService.repository.MessageRepository;
import com.joy.NotificationService.services.KafkaProducer;
import com.joy.NotificationService.services.MessageService;
import com.joy.NotificationService.shared.dto.MessageDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    MessageRepository repo;

    @Autowired
    KafkaProducer producer;

    public MessageDto storeRequest(Message msg){
        MessageDto returnValue=new MessageDto();
        MessageEntity messageEntity=new MessageEntity();
        messageEntity.setMessage(msg.getMessage());
        messageEntity.setPhone_number(msg.getPhone_number());
        messageEntity.setStatus("Pending");
        MessageEntity msgEntity= repo.save(messageEntity);
        if(msgEntity==null)
            return null;
        producer.sendMessageId(messageEntity.getId());
        BeanUtils.copyProperties(messageEntity,returnValue);
        return returnValue;
    }

    @Override
    public MessageDto getMessageDetail(Integer id) {
        MessageEntity msgDetail=repo.findById(id).orElse(null);
        MessageDto returnValue =new MessageDto();
        BeanUtils.copyProperties(msgDetail,returnValue);
        return returnValue;
    }

}
