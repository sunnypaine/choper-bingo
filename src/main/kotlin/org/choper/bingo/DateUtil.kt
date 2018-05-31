package org.choper.bingo

import java.text.SimpleDateFormat
import java.util.*

object DateUtil {
    /**
     * 获取日期的年
     *
     * @param date 指定的日期
     * @return
     */
    fun getYear(date: Date): Int {
        var cal: Calendar = Calendar.getInstance();
        cal.time = date;

        return cal.get(Calendar.YEAR);
    }

    /**
     * 得到日期的月
     *
     * @param date
     * @return
     */
    fun getMonth(date: Date): Int {
        var cal: Calendar = Calendar.getInstance();
        cal.time = date;

        return cal.get(Calendar.MONTH) + 1;
    }

    /**
     * 得到日期的日
     *
     * @param date
     * @return
     */
    fun getDay(date: Date): Int {
        var cal: Calendar = Calendar.getInstance();
        cal.time = date;

        return cal.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取某日期的当天的00:00:00
     *
     * @param date
     * @return
     */
    fun getStartTimeOfDay(date: Date): Date {
        return toDate(toString(date, "yyyy-MM-dd 00:00:00"), "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 获取某日期的当天的23:59:59
     *
     * @param date
     * @return
     */
    fun getEndTimeOfDay(date: Date): Date {
        return toDate(toString(date, "yyyy-MM-dd 23:59:59"), "yyyy-MM-dd HH:mm:ss")
    }

    /**
     * 将日期字符串转换为日期
     *
     * @param dateString   日期字符串
     * @param formatString 日期格式
     * @return
     */
    fun toDate(dateString: String, formatString: String): Date {
        var format: SimpleDateFormat = SimpleDateFormat(formatString);
        var date: Date = format.parse(dateString)

        return date;
    }

    /**
     * 将日期转换为字符串格式
     *
     * @param date         日期
     * @param formatString 日期格式
     * @return
     */
    fun toString(date: Date, formatString: String): String {
        var format: SimpleDateFormat = SimpleDateFormat(formatString);
        var dateString: String = format.format(date);

        return dateString;
    }

    /**
     * 获取指定日期所在月的第一天
     *
     * @param date 指定的日期。
     * @return
     */
    fun getFirstDayOfMonth(date: Date): String {
        var format: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd");

        var cal: Calendar = Calendar.getInstance();
        cal.time = date;
        cal.add(Calendar.MONTH, 0);
        cal.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天
        var first: String = format.format(cal.time);

        return first;
    }

    /**
     * 获取指定日期所在月的最后一天
     *
     * @param date
     * @return
     */
    fun getLastDayOfMonth(date: Date): String {
        var format: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd");

        var cal: Calendar = Calendar.getInstance();
        cal.time = date;
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        var last: String = format.format(cal.time);

        return last;
    }
}