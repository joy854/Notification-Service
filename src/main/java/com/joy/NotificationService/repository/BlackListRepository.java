package com.joy.NotificationService.repository;


import com.joy.NotificationService.io.entity.BlackListEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlackListRepository extends JpaRepository<BlackListEntity,String> {
//    public void save(String number);
//    public List<BlackListEntity> getAll();
}
