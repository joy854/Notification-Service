package com.joy.NotificationService.services.impl;
import com.joy.NotificationService.services.KafkaProducer;
import com.joy.NotificationService.shared.dto.MessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerImpl implements KafkaProducer {

    @Autowired
    private KafkaTemplate<String, Integer> kafkaTemplate;

    public static final String TOPIC = "notification.send_sms";

    public void sendMessageId(Integer message) throws Exception {
        try{
            kafkaTemplate.send(TOPIC, message);
        }
      catch (Exception ex){
//            throw new Exception(ex.getMessage());
      }
    }
}
