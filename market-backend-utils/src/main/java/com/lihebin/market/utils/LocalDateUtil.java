package com.lihebin.market.utils;

import org.springframework.util.Assert;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: some desc
 * @author: lihebin
 * @email: include_lihebin@163.com
 * @date: 2021/3/27 4:11 下午
 */
public class LocalDateUtil {

    /**
     * 默认 zoneId
     */
    private static final ZoneId DEFAULT_ZONE_ID = ZoneId.systemDefault();
    private static final ZoneOffset DEFAULT_ZONE_OFFSET = DEFAULT_ZONE_ID.getRules().getOffset(Instant.now());

    /**
     * 时间格式（yyyy-MM-dd）
     */
    public static final String DATE_PATTERN = "yyyy-MM-dd";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);
    /**
     * 时间格式（yyyy-MM-dd HH:mm:ss）
     */
    public static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);

    private static final Map<String, DateTimeFormatter> DATE_TIME_FORMATTER_MAP = new HashMap<>(2);

    static {
        DATE_TIME_FORMATTER_MAP.put(DATE_PATTERN, DATE_FORMATTER);
        DATE_TIME_FORMATTER_MAP.put(DATE_TIME_PATTERN, DATE_TIME_FORMATTER);
    }

    /**
     * 从全局缓存中拿 pattern 对应的 formatter 或者新建
     *
     * @param pattern pattern
     * @return pattern 对应的 formatter
     */
    private static DateTimeFormatter getFormatter(String pattern) {
        return DATE_TIME_FORMATTER_MAP.getOrDefault(pattern, DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 日期格式化为指定格式的字符串
     *
     * @param date    日期
     * @param pattern 格式，如：DateUtils.DATE_PATTERN
     * @return 返回指定格式字符串时间
     */
    public static String format(Date date, String pattern) {
        Assert.notNull(date, "传入的日期不可以为 [null]");
        Assert.hasText(pattern, "PATTERN: [" + pattern + "] 参数非法");
        return formatLocalDateTime(toLocalDateTime(date), pattern);
    }

    /**
     * LocalDate 类型的日期格式化为指定格式的字符串
     *
     * @param localDate LocalDate 类型的日期
     * @param pattern   格式，如：DateUtils.DATE_PATTERN
     * @return 返回指定格式字符串时间
     */
    public static String formartLocalDate(LocalDate localDate, String pattern) {
        Assert.notNull(localDate, "传入的日期不可以为 [null]");
        Assert.hasText(pattern, "PATTERN: [" + pattern + "] 参数非法");
        return formatLocalDateTime(toLocalDateTime(localDate), pattern);
    }

    /**
     * LocalDateTime 类型的时间格式化为指定格式的字符串
     *
     * @param localDateTime LocalDateTime 类型的时间
     * @param pattern       格式，如 DateUtils.DATE_PATTERN
     * @return 指定格式字符串时间
     */
    public static String formatLocalDateTime(LocalDateTime localDateTime, String pattern) {
        Assert.notNull(localDateTime, "传入的日期不可以为 [null]");
        Assert.hasText(pattern, "PATTERN: [" + pattern + "] 参数非法");
        return localDateTime.format(getFormatter(pattern));
    }

    /**
     * 字符串转成 Date 类型
     *
     * @param str     日期字符串
     * @param pattern 日期的格式：如：DateUtils.DATE_PATTERN
     * @return Date 类型的时间
     */
    public static Date toDate(String str, String pattern) {
        Assert.hasText(str, "STR: [" + str + "] 参数非法");
        Assert.hasText(pattern, "PATTERN: [" + pattern + "] 参数非法");
        return toDate(LocalDateTime.parse(str, getFormatter(pattern)));
    }

    /**
     * LocalDate 转成 Date
     *
     * @param localDate LocalDate 类型日期
     * @return Date 类型的日期
     */
    public static Date toDate(LocalDate localDate) {
        Assert.notNull(localDate, "传入的日期不可以为 [null]");
        return Date.from(localDate.atStartOfDay(DEFAULT_ZONE_ID).toInstant());
    }

    /**
     * LocalDateTime 转成 Date
     *
     * @param localDateTime LocalDateTime 类型时间
     * @return Date 类型的时间
     */
    public static Date toDate(LocalDateTime localDateTime) {
        Assert.notNull(localDateTime, "传入的日期不可以为 [null]");
        return Date.from(localDateTime.atZone(DEFAULT_ZONE_ID).toInstant());
    }

    /**
     * 字符串转成 LocalDate 类型的日期 默认 str 形如 "yyyy-MM-dd"
     *
     * @param str 字符串日期
     * @return LocalDate 类型的日期
     */
    public static LocalDate toLocalDate(String str) {
        Assert.hasText(str, "STR: [" + str + "] 参数非法");
        return toLocalDate(str, DATE_PATTERN);
    }

    /**
     * 字符串转成 LocalDate 类型的日期
     *
     * @param str     字符串日期
     * @param pattern 字符串格式，如 DateUtils.DATE_PATTERN
     * @return LocalDate 类型的日期
     */
    public static LocalDate toLocalDate(String str, String pattern) {
        Assert.hasText(str, "STR: [" + str + "] 参数非法");
        Assert.hasText(pattern, "PATTERN: [" + pattern + "] 参数非法");
        return LocalDate.parse(str, getFormatter(pattern));
    }

    /**
     * Date 类型日期转成 LocalDate 类型的日期
     *
     * @param date Date 类型的日期
     * @return LocalDate 类型的日期
     */
    public static LocalDate toLocalDate(Date date) {
        Assert.notNull(date, "传入的日期不可以为 [null]");
        return toZonedDateTime(date).toLocalDate();
    }

    /**
     * 字符串类型的时间转成 LocalDateTime 类型的时间，默认形如 "yyyy-MM-dd HH:mm:ss"
     *
     * @param str 字符串时间，默认形如 "yyyy-MM-dd HH:mm:ss"
     * @return LocalDateTime 类型的时间
     */
    public static LocalDateTime toLocalDateTime(String str) {
        Assert.hasText(str, "STR: [" + str + "] 参数非法");
        return toLocalDateTime(str, DATE_TIME_PATTERN);
    }

    /**
     * 字符串类型的时间转成 LocalDateTime 类型的时间
     *
     * @param str     字符串时间
     * @param pattern 字符串时间格式
     * @return LocalDateTime 类型的时间
     */
    public static LocalDateTime toLocalDateTime(String str, String pattern) {
        Assert.hasText(str, "STR: [" + str + "] 参数非法");
        Assert.hasText(pattern, "PATTERN: [" + pattern + "] 参数非法");
        return LocalDateTime.parse(str, getFormatter(pattern));
    }


    /**
     * Date 类型的时间转成 LocalDateTime 类型的时间
     *
     * @param date Date 类型的时间
     * @return LocalDateTime 类型的时间
     */
    public static LocalDateTime toLocalDateTime(Date date) {
        Assert.notNull(date, "传入的日期不可以为 [null]");
        return toZonedDateTime(date).toLocalDateTime();
    }

    /**
     * LocalDate 时间转成 LocalDateTime 类型时间为当天开始时间
     *
     * @param localDate LocalDate 类型的时间
     * @return LocalDateTime 类型时间为当天开始时间
     */
    public static LocalDateTime toLocalDateTime(LocalDate localDate) {
        Assert.notNull(localDate, "传入的日期不可以为 [null]");
        return localDate.atStartOfDay();
    }

    /**
     * 对日期的【秒】进行加/减
     *
     * @param date    日期
     * @param seconds 秒数，负数为减
     * @return 加/减几秒后的日期
     */
    public static Date addDateSeconds(Date date, int seconds) {
        Assert.notNull(date, "传入的日期不可以为 [null]");
        return toDate(toLocalDateTime(date).plusSeconds(seconds));
    }

    /**
     * 对日期的【分钟】进行加/减
     *
     * @param date    日期
     * @param minutes 分钟数，负数为减
     * @return 加/减几分钟后的日期
     */
    public static Date addDateMinutes(Date date, int minutes) {
        Assert.notNull(date, "传入的日期不可以为 [null]");
        return toDate(toLocalDateTime(date).plusMinutes(minutes));
    }

    /**
     * 对日期的【小时】进行加/减
     *
     * @param date  日期
     * @param hours 小时数，负数为减
     * @return 加/减几小时后的日期
     */
    public static Date addDateHours(Date date, int hours) {
        Assert.notNull(date, "传入的日期不可以为 [null]");
        return toDate(toLocalDateTime(date).plusHours(hours));
    }

    /**
     * 对日期的【天】进行加/减
     *
     * @param date 日期
     * @param days 天数，负数为减
     * @return 加/减几天后的日期
     */
    public static Date addDateDays(Date date, int days) {
        Assert.notNull(date, "传入的日期不可以为 [null]");
        return toDate(toLocalDateTime(date).plusDays(days));
    }

    /**
     * 对日期的【周】进行加/减
     *
     * @param date  日期
     * @param weeks 周数，负数为减
     * @return 加/减几周后的日期
     */
    public static Date addDateWeeks(Date date, int weeks) {
        Assert.notNull(date, "传入的日期不可以为 [null]");
        return toDate(toLocalDateTime(date).plusWeeks(weeks));
    }

    /**
     * 对日期的【月】进行加/减
     *
     * @param date   日期
     * @param months 月数，负数为减
     * @return 加/减几月后的日期
     */
    public static Date addDateMonths(Date date, int months) {
        Assert.notNull(date, "传入的日期不可以为 [null]");
        return toDate(toLocalDateTime(date).plusMonths(months));
    }

    /**
     * 对日期的【年】进行加/减
     *
     * @param date  日期
     * @param years 年数，负数为减
     * @return 加/减几年后的日期
     */
    public static Date addDateYears(Date date, int years) {
        Assert.notNull(date, "传入的日期不可以为 [null]");
        return toDate(toLocalDateTime(date).plusYears(years));
    }

    /**
     * 从 Date 获取特定时区的时间
     *
     * @param date Date 类型的时间
     * @return DateUtils.DEFAULT_ZONE_ID 标定的时区时间
     */
    public static ZonedDateTime toZonedDateTime(Date date) {
        Assert.notNull(date, "传入的日期不可以为 [null]");
        return date.toInstant().atZone(DEFAULT_ZONE_ID);
    }

    /**
     * 从秒数拿到 LocalDateTime
     *
     * @param seconds 秒数
     * @return localDateTime
     */
    public static LocalDateTime fromSeconds(long seconds) {
        return LocalDateTime.ofEpochSecond(seconds, 0, DEFAULT_ZONE_OFFSET);
    }

    /**
     * 从毫秒数拿到 LocalDateTime
     *
     * @param millSeconds 毫秒数
     * @return localDateTime
     */
    public static LocalDateTime fromMillSeconds(long millSeconds) {
        Instant instant = Instant.ofEpochMilli(millSeconds);
        return LocalDateTime.ofInstant(instant, DEFAULT_ZONE_ID);
    }

    /**
     * 从 LocalDateTime 拿到秒数
     *
     * @param localDateTime localDateTime
     * @return 秒数
     */
    public static long getSeconds(LocalDateTime localDateTime) {
        Assert.notNull(localDateTime, "传入的日期不可以为 [null]");
        return getMillSeconds(localDateTime) / 1000;
    }

    /**
     * 从 LocalDateTime 拿到毫秒数
     *
     * @param localDateTime localDateTime
     * @return 毫秒数
     */
    public static long getMillSeconds(LocalDateTime localDateTime) {
        Assert.notNull(localDateTime, "传入的日期不可以为 [null]");
        return localDateTime.toInstant(DEFAULT_ZONE_OFFSET).toEpochMilli();
    }
}
