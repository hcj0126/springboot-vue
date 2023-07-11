package com.hcj.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * DateUtils
 *   日期工具类
 * @author hcj
 * @date 2023-06-16
 */
public class DateUtils {
    public DateUtils() {
    }

    public static Calendar createCalendar() {
        return new GregorianCalendar();
    }

    public static SimpleDateFormat getDateFormat(String format) {
        return new SimpleDateFormat(format);
    }

    public static String formatDateTime(Date date, String format) {
        return getDateFormat(format).format(date);
    }

    public static Date parseDate(String date, String format) throws ParseException {
        return getDateFormat(format).parse(date);
    }

    public static String parseDateForMill(Long mills, String format) {
        Date d = new Date(mills);
        String str = formatDateTime(d, format);
        return str;
    }

    public static long getCurrent() {
        return System.currentTimeMillis();
    }

    public static String getNowDate(String format) {
        return formatDateTime(new Date(), format);
    }

    public static String getNowDate() {
        return formatDateTime(new Date(), "yyyy-MM-dd HH:mm:ss");
    }

    public static String getNowTime() {
        return formatDateTime(new Date(), "HH:mm:ss");
    }

    public static boolean isAm(Date date) {
        Calendar gregorianCalendar = createCalendar();
        gregorianCalendar.setTime(date);
        return gregorianCalendar.get(9) == 0;
    }

    public static boolean isPm(Date date) {
        Calendar gregorianCalendar = createCalendar();
        gregorianCalendar.setTime(date);
        return gregorianCalendar.get(9) == 1;
    }

    public static int getYear(Date date) {
        Calendar gregorianCalendar = createCalendar();
        gregorianCalendar.setTime(date);
        return gregorianCalendar.get(1);
    }

    public static int getMonth(Date date) {
        Calendar gregorianCalendar = createCalendar();
        gregorianCalendar.setTime(date);
        return gregorianCalendar.get(2);
    }

    public static int getDay(Date date) {
        Calendar gregorianCalendar = createCalendar();
        gregorianCalendar.setTime(date);
        return gregorianCalendar.get(5);
    }

    public static int getNoInYear(Date date) {
        Calendar gregorianCalendar = createCalendar();
        gregorianCalendar.setTime(date);
        return gregorianCalendar.get(6);
    }

    public static int getNoInMonth(Date date) {
        Calendar gregorianCalendar = createCalendar();
        gregorianCalendar.setTime(date);
        return gregorianCalendar.get(5);
    }

    public static int getNoInWeek(Date date) {
        Calendar gregorianCalendar = createCalendar();
        gregorianCalendar.setTime(date);
        return gregorianCalendar.get(7);
    }

    public static int getNoInWeekOfMonth(Date date) {
        Calendar gregorianCalendar = createCalendar();
        gregorianCalendar.setTime(date);
        return gregorianCalendar.get(8);
    }

    public static String getNowDayBefore(String format) {
        return getDayBefore(new Date(), format, 1);
    }

    public static String getNowDayAfter(String format) {
        return getDayAfter(new Date(), format, 1);
    }

    public static String getDayBefore(Date date, String format, int num) {
        Calendar gregorianCalendar = createCalendar();
        gregorianCalendar.setTime(date);
        int day = gregorianCalendar.get(5);
        gregorianCalendar.set(5, day - num);
        return formatDateTime(gregorianCalendar.getTime(), format);
    }

    public static String getDayAfter(Date date, String format, int num) {
        Calendar gregorianCalendar = createCalendar();
        gregorianCalendar.setTime(date);
        int day = gregorianCalendar.get(5);
        gregorianCalendar.set(5, day + num);
        return formatDateTime(gregorianCalendar.getTime(), format);
    }

    public static String getWeekInMonthDayBefor(Date date, String format, int num) {
        Calendar gregorianCalendar = createCalendar();
        gregorianCalendar.setTime(date);
        int month = gregorianCalendar.get(4);
        gregorianCalendar.set(4, month - num);
        return formatDateTime(gregorianCalendar.getTime(), format);
    }

    public static String getWeekInMonthDayAfter(Date date, String format, int num) {
        Calendar gregorianCalendar = createCalendar();
        gregorianCalendar.setTime(date);
        int month = gregorianCalendar.get(4);
        gregorianCalendar.set(4, month + num);
        return formatDateTime(gregorianCalendar.getTime(), format);
    }

    public static String getMonthDayBefore(Date date, String format, int num) {
        Calendar gregorianCalendar = createCalendar();
        gregorianCalendar.setTime(date);
        int month = gregorianCalendar.get(2);
        gregorianCalendar.set(2, month - num);
        return formatDateTime(gregorianCalendar.getTime(), format);
    }

    public static String getMonthDayAfter(Date date, String format, int num) {
        Calendar gregorianCalendar = createCalendar();
        gregorianCalendar.setTime(date);
        int month = gregorianCalendar.get(2);
        gregorianCalendar.set(2, month + num);
        return formatDateTime(gregorianCalendar.getTime(), format);
    }

    public static String getFirstDayOfWeek(Date date, String format) {
        Calendar gregorianCalendar = createCalendar();
        gregorianCalendar.setFirstDayOfWeek(2);
        gregorianCalendar.setTime(date);
        gregorianCalendar.set(7, gregorianCalendar.getFirstDayOfWeek());
        return formatDateTime(gregorianCalendar.getTime(), format);
    }

    public static String getLastDayOfWeek(Date date, String format) {
        Calendar gregorianCalendar = createCalendar();
        gregorianCalendar.setFirstDayOfWeek(2);
        gregorianCalendar.setTime(date);
        gregorianCalendar.set(7, gregorianCalendar.getFirstDayOfWeek() + 6);
        return formatDateTime(gregorianCalendar.getTime(), format);
    }

    public static Long getIntevalOfHours(String startDate, String endDate, String format) throws ParseException {
        Long diff = getIntevalOfMs(startDate, endDate, format);
        return diff / 1000L / 60L / 60L;
    }

    public static Long getIntevalOfMin(String startDate, String endDate, String format) throws ParseException {
        Long diff = getIntevalOfMs(startDate, endDate, format);
        return diff / 60000L;
    }

    public static Long getIntevalOfSeconds(String startDate, String endDate, String format) throws ParseException {
        Long diff = getIntevalOfMs(startDate, endDate, format);
        return diff / 1000L;
    }

    public static Long getIntevalOfMs(String startDate, String endDate, String format) throws ParseException {
        Long startTemp = parseDate(startDate, format).getTime();
        Long endTemp = parseDate(endDate, format).getTime();
        return endTemp - startTemp;
    }
}
