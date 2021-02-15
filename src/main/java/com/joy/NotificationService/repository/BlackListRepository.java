package com.joy.NotificationService.repository;


import com.joy.NotificationService.io.entity.BlackListEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlackListRepository extends JpaRepository<BlackListEntity,String> {
//    public void save(String number);
//    public List<BlackListEntity> getAll();
}
