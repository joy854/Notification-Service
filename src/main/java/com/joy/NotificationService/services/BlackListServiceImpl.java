package com.joy.NotificationService.services;

import com.joy.NotificationService.io.entity.BlackListEntity;
import com.joy.NotificationService.model.request.BlackListNumbers;
import com.joy.NotificationService.repository.BlackListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BlackListServiceImpl implements BlackListService {

    @Autowired
    BlackListRepository blackListRepository;

    @Override
    public void saveNumber(BlackListNumbers blackListNumbers) {
        for(String number: blackListNumbers.getPhone_numbers()){
            BlackListEntity blackListEntity=new BlackListEntity();
            blackListEntity.setBlacklistNumber(number);
            BlackListEntity alreadyPres=blackListRepository.findById(number).orElse(null);
            if(alreadyPres==null)
                blackListRepository.save(blackListEntity);
        }
    }

    @Override
    public boolean deleteNumber(String number) {
        BlackListEntity alreadyPresent=blackListRepository.findById(number).orElse(null);
        if(alreadyPresent!=null){
            BlackListEntity numberToBeDeleted=new BlackListEntity();
            numberToBeDeleted.setBlacklistNumber(number);
            blackListRepository.delete(numberToBeDeleted);
            return true;
        }
        return false;
    }
}
