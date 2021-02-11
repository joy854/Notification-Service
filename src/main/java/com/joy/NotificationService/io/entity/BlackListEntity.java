package com.joy.NotificationService.io.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity(name="blacklist")
public class BlackListEntity {
    @Id
    private String blacklistNumber;
}

