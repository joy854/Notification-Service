package com.joy.NotificationService.services.impl;

import com.joy.NotificationService.io.entity.EsEntity;
import com.joy.NotificationService.model.request.ElasticSearchInput;
import com.joy.NotificationService.repository.EsRepository;
import com.joy.NotificationService.services.ElasticSearchService;
import com.joy.NotificationService.shared.dto.EsDto;
import com.joy.NotificationService.util.DateHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ElasticSearchServiceImpl implements ElasticSearchService {

    @Autowired
    EsRepository esRepository;

    @Autowired
    DateHelper dateHelper;

//    @Override
//    public void save(EsDto esModel) {
//
//    }

    @Override
    public List<EsDto> findByMessage(String messageText) {
        Page<EsEntity>entities= esRepository.findByMessage(messageText, PageRequest.of(0,2));
        List<EsEntity> entitiesList=entities.getContent();
        List<EsDto> esDtos=new ArrayList<>();
        for(EsEntity entity: entitiesList){
            EsDto esDto=new EsDto();
            BeanUtils.copyProperties(entity,esDto);
            esDtos.add(esDto);
        }
        return esDtos;
    }

    @Override
    public List<EsDto> findByDate(ElasticSearchInput esInput) {
        long startEpoch = dateHelper.DateConverter(esInput.getStartDate());
        long endEpoch= dateHelper.DateConverter((esInput.getEndDate()));
        System.out.println(startEpoch);
        List<EsDto>esDtos=new ArrayList<>();

        Iterable<EsEntity> entities=esRepository.findAllByCreatedAtBetween(startEpoch,endEpoch, PageRequest.of(0,100));

        for(EsEntity entity:entities){
            EsDto esDto=new EsDto();
            BeanUtils.copyProperties(entity,esDto);
            esDtos.add(esDto);
        }

        return esDtos;
    }

    @Override
    public List<EsDto> findAll(){
        Iterable<EsEntity> entities= esRepository.findAll();
        List<EsDto> esDtos=new ArrayList<>();
        for(EsEntity entity: entities){
            EsDto esDto=new EsDto();
            BeanUtils.copyProperties(entity,esDto);
            esDtos.add(esDto);
        }
        return esDtos;
    }
}
