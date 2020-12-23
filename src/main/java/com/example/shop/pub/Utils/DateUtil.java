package com.example.shop.pub.Utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

        private static Logger logger = LoggerFactory.getLogger(DateUtil.class);
        public static final String TIME_FORMATTER = "yyyy-MM-dd HH:mm:ss";
        public static final long MS = 1L;
        public static final long SECOND_MS = 1000L;
        public static final long MINUTE_MS = 60000L;
        public static final long HOUR_MS = 3600000L;
        public static final long DAY_MS = 86400000L;

    public DateUtil() {
    }

        public static long now() {
        return (new Date()).getTime();
    }

        public static String now(String format) {
        return format(new Date(), format);
    }

        public static String nowTime() {
        return format(new Date(), "yyyy-MM-dd HH:mm:ss");
    }

        public static String nowStr() {
        return Long.toString((new Date()).getTime());
    }

        public static long nowSecond() {
        return (new Date()).getTime() / 1000L;
    }

        public static String nowSecondStr() {
        return Long.toString((new Date()).getTime() / 1000L);
    }

        public static String format(Date date, String format) {
        return (new SimpleDateFormat(format)).format(date);
    }

        public static Date parse(String dateString, String format) {
        try {
            return (new SimpleDateFormat(format)).parse(dateString);
        } catch (ParseException var3) {
            logger.error("Parse " + dateString + " with format " + format + " error!", var3);
            return null;
        }
    }

        public static Date getOffsiteDate(Date date, int calendarField, int offsite) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(calendarField, offsite);
        return cal.getTime();
    }

        public static long dateDiff(Date subtrahend, Date minuend, long diffField) {
        long diff = minuend.getTime() - subtrahend.getTime();
        return diff / diffField;
    }

        public static String getIntervalTimeDesc(Date startTime, Date endTime) {
        long num = Math.abs(endTime.getTime() - startTime.getTime()) / 1000L;
        long seconds = num % 60L;
        long minutes = (num - seconds) / 60L % 60L;
        long hours = (num - minutes * 60L - seconds) / 3600L % 24L;
        long days = (num - hours * 24L - minutes * 60L - seconds) / 3600L / 24L;
        return String.format("%d天%02d时%02d分%02d秒", days, hours, minutes, seconds);
    }

        public static void main(String[] args) {
        Date startTime = parse("2017-04-16 09:45:00", "yyyy-MM-dd HH:mm:ss");
        Date endTime = parse("2017-04-19 18:36:44", "yyyy-MM-dd HH:mm:ss");
        System.out.println(getIntervalTimeDesc(startTime, endTime));
    }

}
