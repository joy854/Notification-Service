package com.joy.NotificationService.services;

import com.joy.NotificationService.io.entity.BlackListEntity;
import com.joy.NotificationService.model.request.BlackListNumbers;
import com.joy.NotificationService.repository.BlackListRepository;
import com.joy.NotificationService.shared.dto.BlackListDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BlackListServiceImpl implements BlackListService {

    @Autowired
    BlackListRepository blackListRepository;

    @Override
    @CacheEvict(value = "blacklist",allEntries = true)
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
    @CacheEvict(value = "blacklist",allEntries = true)
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

    @Override
    @Cacheable(value="blacklist")
    public List<BlackListDto> getAllNumbers() {
        System.out.println("In getallnos");
        List<BlackListEntity> blackListEntities=blackListRepository.findAll();
        List<BlackListDto> returnValue=new ArrayList<>();

        for(BlackListEntity blackListEntity: blackListEntities){
            BlackListDto objDto=new BlackListDto();
            BeanUtils.copyProperties(blackListEntity,objDto);
            returnValue.add(objDto);
        }
        return returnValue;
    }
}
