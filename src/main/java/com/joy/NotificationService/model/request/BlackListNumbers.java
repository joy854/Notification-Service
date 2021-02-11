package com.joy.NotificationService.model.request;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class BlackListNumbers {
    ArrayList<String>phone_numbers;
}
