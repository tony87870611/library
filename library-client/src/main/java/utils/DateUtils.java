package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;

public class DateUtils {

    // String轉Date方法，支持自定義格式
    public static Optional<Date> string2Date(String str, String pattern) {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        try {
            return Optional.of(formatter.parse(str));
        } catch (ParseException e) {
            // 可以选择记录日志或抛出自定义异常
            System.err.println("Invalid date format: " + str);
        }
        return Optional.empty();
    }

    // Date轉String方法，支持自定義日期格式
    public static String date2String(Date date, String pattern) {
        LocalDateTime localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return localDateTime.format(formatter);
    }
}
