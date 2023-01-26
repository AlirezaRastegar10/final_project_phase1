package ir.maktab.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

public class DateFormatter {

    public String formatter(Long dateAndTime) {
        String DATE_FORMATTER = "yyyy-MM-dd HH:mm";
        LocalDateTime newTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(dateAndTime), TimeZone.getDefault().toZoneId());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);
        return newTime.format(formatter);
    }
}
