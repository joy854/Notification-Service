package com.joy.NotificationService.services;

import com.joy.NotificationService.io.entity.BlackListEntity;
import com.joy.NotificationService.model.request.BlackListNumbers;
import com.joy.NotificationService.repository.BlackListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlackListServiceImpl implements BlackListService {

    @Autowired
    BlackListRepository blackListRepository;

    @Override
    public void saveNumber(BlackListNumbers blackListNumbers) {
        for(String number: blackListNumbers.getPhone_numbers()){
            BlackListEntity blackListEntity=new BlackListEntity();
            blackListEntity.setBlacklistNumber(number);

            blackListRepository.save(blackListEntity);
        }
    }
}
