package com.feelcolor.website.java8;

import java.text.ParseException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtil {
    public static final ZoneId ZONE = ZoneId.systemDefault();

    public static final String DEFAULT_FMT_STR = "yyyy-MM-dd HH:mm:ss";
    public static final DateTimeFormatter DEFAULT_FMT = DateTimeFormatter.ofPattern(DEFAULT_FMT_STR);

    public static final String YMD_FMT_STR = "yyyy-MM-dd";
    public static final DateTimeFormatter YMD_FMT = DateTimeFormatter.ofPattern(YMD_FMT_STR);

    public static final String HMS_FMT_STR = "HH:mm:ss";
    private static final DateTimeFormatter HMS_FMT = DateTimeFormatter.ofPattern(HMS_FMT_STR);

    public static final String SLASH_DATE_FMT_STR = "yyyy/MM/dd HH:mm:ss";
    private static final DateTimeFormatter SLASH_DATE_FMT = DateTimeFormatter.ofPattern(SLASH_DATE_FMT_STR);

    /**
     * LocalDateTime 转 Date
     * 
     * @param localDateTime
     * @return
     */
    public static Date toDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZONE).toInstant());
    }

    /**
     * Date 转 LocalDateTime
     * 
     * @param date
     * @return
     */
    public static LocalDateTime toLocalDateTime(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZONE);
    }

    /**
     * Date 转 LocalDate
     * 
     * @param date
     * @return
     */
    public static LocalDate toLocalDate(Date date) {
        return toLocalDateTime(date).toLocalDate();
    }

    /**
     * Date 转 LocalTime
     * 
     * @param date
     * @return
     */
    public static LocalTime toLocalTime(Date date) {
        return toLocalDateTime(date).toLocalTime();
    }

    /**
     * 当前时间
     * 
     * @return
     */
    public static Date now() {
        return new Date();
    }

    /**
     * 返回yyyy-MM-dd HH:mm:ss格式化字符串
     * 
     * @param date
     * @return
     */
    public static String format(Date date) {
        return toLocalDateTime(date).format(DEFAULT_FMT);
    }

    /**
     * yyyy-MM-dd HH:mm:ss字符串转时间
     * 
     * @param date
     * @return
     * @throws ParseException
     */
    public static Date parse(String date) throws ParseException {
        return toDate(LocalDateTime.parse(date, DEFAULT_FMT));
    }

    /**
     * 返回yyyy-MM-dd格式化字符串
     * 
     * @param date
     * @return
     */
    public static String ymdFormat(Date date) {
        return toLocalDateTime(date).format(YMD_FMT);
    }

    /**
     * yyyy-MM-dd字符串转时间
     * 
     * @param date
     * @return
     * @throws ParseException
     */
    public static Date ymdParse(String date) throws ParseException {
        return toDate(LocalDateTime.parse(date, YMD_FMT));
    }

    /**
     * 返回HH:mm:ss格式化字符串
     * 
     * @param date
     * @return
     */
    public static String hmsFormat(Date date) {
        return toLocalDateTime(date).format(HMS_FMT);
    }

    /**
     * HH:mm:ss字符串转时间
     * 
     * @param date
     * @return
     * @throws ParseException
     */
    public static Date hmsParse(String date) throws ParseException {
        return toDate(LocalDateTime.parse(date, HMS_FMT));
    }

    /**
     * 返回yyyy/MM/dd HH:mm:ss格式化字符串
     * 
     * @param date
     * @return
     */
    public static String slashFormat(Date date) {
        return toLocalDateTime(date).format(SLASH_DATE_FMT);
    }

    /**
     * yyyy/MM/dd HH:mm:ss字符串转时间
     * 
     * @param date
     * @return
     * @throws ParseException
     */
    public static Date slashParse(String date) throws ParseException {
        return toDate(LocalDateTime.parse(date, SLASH_DATE_FMT));
    }

    /**
     * 获取date 00:00:00
     * 
     * @param date
     * @return
     */
    public static Date zeroOfDay(Date date) {
        return toDate(LocalDateTime.of(toLocalDate(date), LocalTime.MIN));
    }

    /**
     * 获取date 23:59:59.999
     * 
     * @param date
     * @return
     */
    public static Date endOfDay(Date date) {
        return toDate(LocalDateTime.of(toLocalDate(date), LocalTime.MAX));
    }

    /**
     * date日期增加days
     * 
     * @param date
     * @param days
     * @return
     */
    public static Date plusDays(Date date, long days) {
        return toDate(toLocalDateTime(date).plusDays(days));
    }

    /**
     * date日期减少days
     * 
     * @param date
     * @param days
     * @return
     */
    public static Date minusDays(Date date, long days) {
        return toDate(toLocalDateTime(date).minusDays(days));
    }

    /**
     * date月份增加month
     * 
     * @param date
     * @param months
     * @return
     */
    public static Date plusMohths(Date date, long months) {
        return toDate(toLocalDateTime(date).plusMonths(months));
    }

    /**
     * date月份减少month
     * 
     * @param date
     * @param months
     * @return
     */
    public static Date minusMohths(Date date, long months) {
        return toDate(toLocalDateTime(date).minusMonths(months));
    }

    /**
     * date年份增加year
     * 
     * @param date
     * @param years
     * @return
     */
    public static Date plusYears(Date date, long years) {
        return toDate(toLocalDateTime(date).plusYears(years));
    }

    /**
     * date年份减少years
     * 
     * @param date
     * @param years
     * @return
     */
    public static Date minusYears(Date date, long years) {
        return toDate(toLocalDateTime(date).minusYears(years));
    }

    /**
     * 获取date中的年份
     * 
     * @param date
     * @return
     */
    public static int getYear(Date date) {
        return toLocalDateTime(date).getYear();
    }

    /**
     * 获取date中的 月份
     * 
     * @param date
     * @return
     */
    public static int getMonth(Date date) {
        return toLocalDateTime(date).getDayOfMonth();
    }

    /**
     * 获取date中的 星期数
     * 
     * @param date
     * @return
     */
    public static int getDayOfWeek(Date date) {
        return toLocalDateTime(date).getDayOfWeek().getValue();
    }

    /**
     * 获取date中的 小时
     * 
     * @param date
     * @return
     */
    public static int getHour(Date date) {
        return toLocalDateTime(date).getHour();
    }

    /**
     * 获取date中的 分钟
     * 
     * @param date
     * @return
     */
    public static int getMinute(Date date) {
        return toLocalDateTime(date).getMinute();
    }

    /**
     * 获取date中的 秒数
     * 
     * @param date
     * @return
     */
    public static int getSecond(Date date) {
        return toLocalDateTime(date).getSecond();
    }

    /**
     * 返回昨天日期
     * 
     * @return
     */
    public static Date yesterday() {
        return minusDays(now(), 1);
    }

    /**
     * 返回明天日期
     * 
     * @return
     */
    public static Date tomorrow() {
        return plusDays(now(), 1);
    }

    /**
     * 判断date1 是否在 date2 之前
     * 
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isBefore(Date date1, Date date2) {
        return toLocalDateTime(date1).isBefore(toLocalDateTime(date2));
    }

    /**
     * 判断date1 是否在 date2 之后
     * 
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isAfter(Date date1, Date date2) {
        return toLocalDateTime(date1).isAfter(toLocalDateTime(date2));
    }

    /**
     * 返回某月包含几天
     * 
     * @param year
     * @param month
     * @return
     */
    public static int daysOfMonth(int year, int month) {
        YearMonth yearMonth = YearMonth.of(year, month);
        return yearMonth.lengthOfMonth();
    }

    /**
     * 判断是否是闰年
     * 
     * @param date
     * @return
     */
    public static boolean isLeapYear(Date date) {
        return toLocalDate(date).isLeapYear();
    }

    /**
     * date1 月 date2 相差的天数
     * 
     * @param date1
     * @param date2
     * @return
     */
    public static int periodOfDays(Date date1, Date date2) {
        Period period = Period.between(toLocalDate(date1), toLocalDate(date2));
        return period.getDays();
    }

    /**
     * date1 与 date2 相差的月份
     * 
     * @param date1
     * @param date2
     * @return
     */
    public static int periodOfMonth(Date date1, Date date2) {
        Period period = Period.between(toLocalDate(date1), toLocalDate(date2));
        return period.getMonths();
    }

    /**
     * date1 与 date2 相差的年份
     * 
     * @param date1
     * @param date2
     * @return
     */
    public static int periodOfYear(Date date1, Date date2) {
        Period period = Period.between(toLocalDate(date1), toLocalDate(date2));
        return period.getYears();
    }

    public static void main(String[] args) throws ParseException {

    }

}
