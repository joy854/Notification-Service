package com.joy.NotificationService.repository;

import com.joy.NotificationService.io.entity.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<MessageEntity,Integer> {
}
