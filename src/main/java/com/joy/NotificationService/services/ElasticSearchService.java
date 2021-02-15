package com.joy.NotificationService.services;

import com.joy.NotificationService.model.request.ElasticSearchInput;
import com.joy.NotificationService.shared.dto.EsDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ElasticSearchService {
//    public void save(EsDto esModel);
    public List<EsDto> findByMessage(String messageText,int page,int size);
    public List<EsDto> findByDate(ElasticSearchInput esInput,int page,int size);
    public List<EsDto> findAll();
}
