package com.portal.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.util.StringUtils;

import com.portal.common.exception.SystemException;



/**
 * 类名称：DateUtil 内容摘要：处理日期的工具类
 * @version 1.0 2011-7-6
 */
public class DateUtil {

    /** 格式化日期对象 */
    private static SimpleDateFormat format = new SimpleDateFormat();

    /** 日期格式【yyyyMMddHHmmssSSS】 */
    public static String PATTERN_YYYY_MM_DD_HH_mm_ss_SSS = "yyyyMMddHHmmssSSS";

    /** 日期格式【yyMMdd】 */
    public static String PATTERN_YY_MM_DD = "yyMMdd";

    /**
     * 日期格式： yyyy
     */
    public static final String DATE_FMT_YYYY = "yyyy";

    /**
     * 日期格式： MM
     */
    public static final String DATE_FMT_MM = "MM";

    /**
     * 日期格式： yyyyMM
     */
    public static final String DATE_FMT_YYYYMM_NS = "yyyyMM";

    /**
     * 日期格式： yyyy/MM/dd
     */
    public static final String DATE_FMT_YYYYMMDD = "yyyy/MM/dd";

    /**
     * 日期格式： yyyyMMdd
     */
    public static final String DATE_FMT_YYYYMMDD_NS = "yyyyMMdd";

    /**
     * 日期格式： yyyy/MM/dd HH:mm:ss
     */
    public static final String DATE_FMT_YYYYMMDDHHMMSS = "yyyy/MM/dd HH:mm:ss";

    /**
     * 日期格式：yyyy-MM-dd
     */
    public static final String DATE_FMT_YYYY_MM_DD = "yyyy-MM-dd";

    /**
     * 日期格式：yyyy年MM月dd日
     */
    public static final String DATE_FMT_YYYY_MM_DD2 = "yyyy年MM月dd日";
    
    
    /**
     * yyyy-MM-dd HH:mm:ss
     */
    public static final String DATE_FMT_YYYY_MM_DD_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";
    
    

    /**
     * 取得日期字符串
     * @param date 日期
     * @param pattern 日期格式
     * @return String 日期字符串
     */
    public static String formatDate(Date date, String pattern) {
        if (date == null) {
            return "";
        }
        format.applyPattern(pattern);
        return format.format(date);
    }

    /**
     * 向前或是向后滚动年
     * @param Date date
     * @param int rollCount
     * @return Date 日期
     */
    public static Date rollYear(Date date, int rollCount) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.roll(Calendar.YEAR, rollCount);
        return ca.getTime();
    }

    /**
     * 向前或者向后滚动月份 正数像前滚 负数向后滚
     * @param date 准备滚动的日期
     * @param rollCount 滚动参数
     * @return Date 滚动之后的日期
     */
    public static Date rollMonth(Date date, int rollCount) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.add(Calendar.MONTH, rollCount);
        return ca.getTime();
    }

    /**
     * 时间滚动 正数像前滚 负数向后滚
     * @param date 准备滚动的日期
     * @param calendar 滚动系数
     *            年 Calendar.YEAR;
     *            月 Calendar.MONTH;
     *            日 Calendar.DATE;
     *            时 Calendar.HOUR;
     *            钟Calendar.MINUTE;
     * @param rollCount 滚动参数
     * @return Date 滚动之后的日期
     */
    public static Date rollDate(Date date, int calendar, int rollCount) {

        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.add(calendar, rollCount);
        return ca.getTime();
    }

    /**
     * 取得去年的今天
     * @return String 去年的今天
     */
    public static Date getLastYearOfToday() {
        return rollYear(new Date(), -1);
    }

    /**
     * 取得日期字符串
     * @param strDate 日期
     * @param pattern 日期格式
     * @return Date 转换后日期类型的值
     * @throws SystemException
     */
    public static Date parseDate(String strDate, String pattern) throws SystemException {
        if (StringUtils.isEmpty(strDate)) {
            return null;
        }
        format.applyPattern(pattern);
        try {
            return format.parse(strDate);
        } catch (ParseException e) {
            throw new SystemException(e, e.getMessage());
        }
    }

    /**
     * 取得当前年
     * @return String 年的字符型值
     */
    public static String getYear() {
        return formatDate(new Date(), DATE_FMT_YYYY);
    }

    /**
     * 取得去年
     * @return String 去年的字符型值
     */
    public static String getLastYear() {
        return formatDate(getLastYearOfToday(), DATE_FMT_YYYY);
    }

    /**
     * 取得当前年月日
     * @return String 年月日的字符型值
     */
    public static String getYearMonthDay() {
        return formatDate(new Date(), DATE_FMT_YYYY_MM_DD);
    }

    /**
     * 取得当前年月日时分秒
     * @return String 年月日时分秒的日期型值
     */
    public static String getSystemTime() {
    	Date date = new Date();
    	SimpleDateFormat from = new SimpleDateFormat("yyyyMMddHHmmss"); 
    	return from.format(date);

    }
    /**
     * 取得当前年月日时分秒
     * @return String 年月日时分秒的日期型值
     */
    public static String getSystemTimeMs() {
    	Date date = new Date();
    	SimpleDateFormat from = new SimpleDateFormat("yyyyMMddHHmmssSSS"); 
    	return from.format(date);

    }
    /**
     * 取得当前年月日时分秒
     * @return String 年月日时分秒的日期型值
     */
    public static Date getSystemTimeSS(){
    	return parseDate(getSystemTime(), "yyyyMMddHHmmss");
    }
    /**
     * 取得当前年月日
     * @return Date 年月日的日期型值
     */
    public static Date getSystemTimeDate() {
    	return parseDate(getYearMonthDay(), "yyyy-MM-dd");
    }
    /**
     * 取得去年月日(去年的今天)
     * @return String 去年月日的字符型值
     */
    public static String getLastYearMonthDay() {
        return formatDate(getLastYearOfToday(), DATE_FMT_YYYY_MM_DD);
    }

    /**
     * 
     * 方法名: getMonthDays
     * 描述: 根据月份获取天数
     * 参数: @param month
     * 参数: @return
     * 创建人:yi
     * 创建时间: 2016年5月19日 下午1:27:43
     * 版本号: v1.0
     * 抛出异常:
     * 返回类型: String
     */
    @SuppressWarnings("deprecation")
    public static Integer getMonthDays(Integer month){
        HashMap<Integer, Integer> monthDaysMap = new HashMap<Integer, Integer>();
        Date date = new Date();
        int year = date.getYear();
        monthDaysMap.put(1, 31);
        if((year%4==0&&year%100!=0)||year%400==0){
            //今年是闰年
            monthDaysMap.put(2, 29);
        }else{
            monthDaysMap.put(2, 28);
        }
        monthDaysMap.put(3, 31);
        monthDaysMap.put(4, 30);
        monthDaysMap.put(5, 31);
        monthDaysMap.put(6, 30);
        monthDaysMap.put(7, 31);
        monthDaysMap.put(8, 31);
        monthDaysMap.put(9, 30);
        monthDaysMap.put(10, 31);
        monthDaysMap.put(11, 30);
        monthDaysMap.put(12, 31);
        return monthDaysMap.get(month);
    }
    
    /**
     * 方法名: getFirstDayOfMonth 
     * 描述: 获取本月第一天
     * 参数: @return     
     * 创建人: Xia ZhengWei 
     * 创建时间: 2016年6月29日 上午11:23:39
     * 版本号: v1.0   
     * 抛出异常:
     * 返回类型: String
     */
    public static String getFirstDayOfMonth() {
    	Calendar calendar = Calendar.getInstance();    
    	calendar.add(Calendar.MONTH, 0);
    	calendar.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
        format.applyPattern("yyyy-MM-dd");
        String firstDay = format.format(calendar.getTime());
    	return firstDay;
    }
    
    /**
     * 方法名: getLastDayOfMonth 
     * 描述: 获取本月最后一天
     * 参数: @return     
     * 创建人: Xia ZhengWei 
     * 创建时间: 2016年6月29日 上午11:23:35
     * 版本号: v1.0   
     * 抛出异常:
     * 返回类型: String
     */
    public static String getLastDayOfMonth() {
    	Calendar calendar = Calendar.getInstance();    
    	calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));  
        format.applyPattern("yyyy-MM-dd");
        String lastDay = format.format(calendar.getTime());
    	return lastDay;
    }
    
    /**
     * 方法名: nextMonthFirstDate 
     * 描述: 获取当前日期的下个月第一天
     * 参数: @return     
     * 创建人: Xia ZhengWei 
     * 创建时间: 2016年6月30日 上午9:42:33
     * 版本号: v1.0   
     * 抛出异常:
     * 返回类型: String
     */
    public static String nextMonthFirstDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.MONTH, 1);
        format.applyPattern("yyyy-MM-dd");
        String nextFirstDay = format.format(calendar.getTime());
    	return nextFirstDay;
    }
    
    /**
     * @Title: getNowWeekMonday 
     * @Description: 获取本周一
     * @param date  指定日期
     * @return Date
     * @throws
     */
    public static Date getNowWeekMonday(Date date) {    
        Calendar cal = Calendar.getInstance();    
        cal.setTime(date);    
        cal.add(Calendar.DAY_OF_MONTH, -1); //解决周日会出现 并到下一周的情况    
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);    
        return cal.getTime();    
    }
    
    /**
     * @Title: getNowWeekSunday 
     * @Description: 获取本周日
     * @param date  指定日期
     * @return Date
     * @throws
     */
    @SuppressWarnings("deprecation")
    public static Date getNowWeekSunday(Date date) { 
        if(date.getDay() != 0) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            // 这种输出的是上个星期周日的日期，因为老外那边把周日当成第一天
            cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
            // 增加一个星期，才是我们中国人理解的本周日的日期
            cal.add(Calendar.WEEK_OF_YEAR, 1);
            return cal.getTime();    
        }else {
            return date;
        }
    }
    
    /**
     * @Title: getLastWeekMonday 
     * @Description: 获取上周一
     * @param date  指定日期
     * @return Date
     * @throws
     */
    public static Date getLastWeekMonday(Date date) {    
       Date a = DateUtils.addDays(date, -1);    
       Calendar cal = Calendar.getInstance();    
       cal.setTime(a);    
       cal.add(Calendar.WEEK_OF_YEAR, -1);// 一周    
       cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);    
       return cal.getTime();    
    }
    
    /**
     * @Title: getLastWeekSunday 
     * @Description: 获取上周日
     * @param date  指定日期
     * @return Date
     * @throws
     */
    public static Date getLastWeekSunday(Date date) {    
       Date a = DateUtils.addDays(date, -1);    
       Calendar cal = Calendar.getInstance();    
       cal.setTime(a);    
       cal.set(Calendar.DAY_OF_WEEK, 1);      
       return cal.getTime();    
    }
    
    /**
     * @Title: getYesterday 
     * @Description: 获取日期的前一天 
     * @param date
     * @return Date
     * @throws
     */
    public static Date getYesterday(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);//把当前时间赋给日历
        calendar.add(Calendar.DAY_OF_MONTH, -1);  //设置为前一天
        return calendar.getTime();   //得到前一天的时间
    }
    
    /**
     * @Title: getDates 
     * @Description: 获取间隔日期中的所有日期
     * @param startDay  开始日期
     * @param endDay    结束日期
     * @return List<String>
     * @author Xia ZhengWei
     * @date 2016年11月22日 下午10:00:05 
     * @version V1.0
     */
    public static List<String> getDates(Calendar startDay, Calendar endDay) {
        List<String> dateList = new ArrayList<String>();
        // 开始日期比结束日期大则返回空的集合
        if (startDay.compareTo(endDay) >= 0) {
            return dateList;
        }else {
            Calendar currentPrintDay = startDay;
            dateList.add(DateUtil.formatDate(startDay.getTime(), "yyyy-MM-dd"));
            while (true) {  
                // 日期加一  
                currentPrintDay.add(Calendar.DATE, 1);
                dateList.add(DateUtil.formatDate(currentPrintDay.getTime(), "yyyy-MM-dd"));
                // 日期加一后判断是否达到终了日，达到则终止打印  
                if (currentPrintDay.compareTo(endDay) == 0) {  
                    break;  
                }
            }
        }
        return dateList;
    }
    
    /**
     * @Title: getLaterSixDate 
     * @Description: 获取起始日期后指定间隔的日期
     * @param startDay
     * @return Date
     * @author Xia ZhengWei
     * @date 2016年11月23日 下午10:10:26 
     * @version V1.0
     */
    public static Date getLaterSixDate(Calendar startDay, Integer afterDays) {
        Calendar currentPrintDay = startDay;
        // 例：日期加6  
        // currentPrintDay.add(Calendar.DATE, 6);
        currentPrintDay.add(Calendar.DATE, afterDays);
        return currentPrintDay.getTime();
    }
    
}
