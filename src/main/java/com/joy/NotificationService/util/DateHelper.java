package com.joy.NotificationService.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
//@NoArgsConstructor
//@AllArgsConstructor
@Component
public class DateHelper {
    public long DateConverter(String stringDate)  {
        SimpleDateFormat df=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = null;
        try {
            date = df.parse(stringDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long epoch = date.getTime();

        return epoch;
    }
}
