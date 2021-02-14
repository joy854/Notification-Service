package com.joy.NotificationService.repository;

import com.joy.NotificationService.io.entity.EsEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface EsRepository extends ElasticsearchRepository<EsEntity,Integer> {
}
