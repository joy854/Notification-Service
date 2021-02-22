package com.joy.NotificationService.repository;

import com.joy.NotificationService.io.entity.EsEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EsRepository extends ElasticsearchRepository<EsEntity,Integer> {
    Page<EsEntity> findByMessage(String text, Pageable pageable);
    Page<EsEntity> findAllByPhoneNumberAndCreatedAtBetween(String phoneNumber,long startEpoch,long endEpoch, Pageable pageable);
//    List<EsEntity> findAll();
}
