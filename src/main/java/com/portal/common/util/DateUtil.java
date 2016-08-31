package com.portal.common.util;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import org.springframework.util.StringUtils;

import com.portal.common.exception.SystemException;

import net.sf.json.JSONObject;



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
     * 取得当前年月日时分秒
     * @return Date 年月日时分秒的日期型值
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
     * 
     * 方法名: jisuanRqi
     * 描述: 计算时间
     * 参数: @param startTime
     * 参数: @param endTime
     * 参数：@param tag
     *          1:加班出差
     *          2:其余
     * 参数: @return
     * 创建人: yi
     * 创建时间: 2016年7月2日 下午7:01:09
     * 版本号: v1.0
     * 抛出异常:
     * 返回类型: JSONObject
     */
    @SuppressWarnings({ "deprecation", "unused" })
    public static JSONObject jisuanRqi(Date startTime,Date endTime,String tag){
    	Date stTime = new Date(startTime.getTime());
    	Date etTime = new Date(endTime.getTime());
        JSONObject result = JsonUtils.setSuccess();
        //声明变量
        String minutes = "";  //分
        Long miao = 0L; //秒
        
        
        //判断 如果 任何参数 未传入 返回错误信息
        if("".equals(stTime) || "".equals(etTime)){
            return result;
        }else{
            //拿到 结束时间 分
        	BigDecimal endMinutes = new BigDecimal((double)etTime.getMinutes());
            //拿到 开始时间 分
        	BigDecimal startMinutes = new BigDecimal((double)stTime.getMinutes());
            
            //计算分
            if(startMinutes.subtract(endMinutes).doubleValue()>0){
                //开始分 大于 结束分  ((60-(开始分 - 结束分))/60)
                  minutes = new BigDecimal("60").subtract(endMinutes.subtract(startMinutes).divide(new BigDecimal(60),2,BigDecimal.ROUND_HALF_DOWN)).toString();
              }else{
                //开始分 小于 技术分 (结束-开始)/60
                  minutes = endMinutes.subtract(startMinutes).divide(new BigDecimal(60),2,BigDecimal.ROUND_HALF_DOWN).toString();
              }
            //结束时间-开始时间 相差秒数
            miao = etTime.getTime() - stTime.getTime();
            
            //获取结束日期(String类型)
            String strEndTime = DateUtil.formatDate(etTime, "yyyy-MM-dd");
            //获取开始日期(String类型)
            String strStartTime = DateUtil.formatDate(stTime, "yyyy-MM-dd");
            
            
            
            //正常上班时间
            Date onShangBanDate = new Date(strEndTime.substring(0, 4)+"/"+(etTime.getMonth()+1)+"/"+etTime.getDate() +" 8:30:00");
            //正常下班时间
            Date onXiaBanDate = new Date(strStartTime.substring(0, 4)+"/"+(stTime.getMonth()+1)+"/"+stTime.getDate() +" 17:30:00"); 
            
            //------------------------------ 跨月 清空 ----------------------------------
            
            if(stTime.getDate() <=31 && etTime.getDate()>=1 && (stTime.getMonth()<etTime.getMonth() || stTime.getYear() < etTime.getYear())){
                int day = 0;//保存跨月天数
                int count = 0;//记录有几个周六周天
                
                //用正常下班时间 - 申请开始时间 
                long startTimeCha = onXiaBanDate.getTime()-stTime.getTime();
                double startHours = 0D;
                //如果下班时间大于 1点 则小时数减一
                if((12 -stTime.getHours()+1)>1 && etTime.getHours() >=13){
                    // 把 秒换算成小时
                    startHours = Math.floor(startTimeCha/1000/60/60)-1;
                }else{
                    // 把 秒换算成小时
                    startHours = Math.floor(startTimeCha/1000/60/60);
                }
                //如果小于1 则改为0.0
                if(startHours<0){
                    startHours = 0;
                }
                
                //用申请结束时间 - 正常上班时间
                long endTimeCha = etTime.getTime() - onShangBanDate.getTime();
                double endHours = 0D;
                //如果下班时间大于 1点 则小时数减一
                if(etTime.getHours() >=13 && stTime.getHours() < 12){
                    // 把 秒换算成小时
                    endHours = Math.floor(endTimeCha/1000/60/60)-1;
                }else{
                    // 把 秒换算成小时
                    endHours = Math.floor(endTimeCha/1000/60/60);
                }
                
                // 计算年
                if(stTime.getYear() < etTime.getYear()){
                    //记录开始日期该年总天数
                    int zongDays = 0;
                    //每个月天数
                    int monthDays = 0;
                    //开始日期 月份天数
                    Date kaishiDays = null;
                    // 计算跨年
                    // 如果开始年份和结束年份不一致
                    // 循环差的年数差
                    for(int i = 0;i<=etTime.getYear() - stTime.getYear();i++){
                        // 获得开始日期 月份天数
                        kaishiDays = new Date(stTime.getYear(),stTime.getMonth()+i+1,0);
                        // 获得天数
                        int tianshu = kaishiDays.getDate();
                        // 获得 开始日期 到月末 日期 天数
                        int shengyvTianshu = tianshu - stTime.getDate();
                        // 循环 该月份 和 12月 剩余月份
                        for(int j = 0;j<12 - (stTime.getMonth()+2);j++){
                           //获得月份
                           int month = stTime.getMonth();
                           //获得每个月天数
                           monthDays = new Date(stTime.getYear(),month+j,0).getDate();
                           //得到总的天数
                           zongDays += monthDays;
                        }
                    }
                    //计算请假月份 实际天数
                    monthDays = new Date(stTime.getYear(),stTime.getMonth()+1,0).getDate();
                    zongDays += monthDays-stTime.getDate()-1;
                    // 计算 明年
                    if(kaishiDays.getMonth()+1 >= 1 && kaishiDays.getYear()!=stTime.getYear()){
                        //证明去年已经循环完
                        for(int v = 0;v<etTime.getMonth()+1;v++){
                            if(etTime.getMonth()+1 == v+1){
                                //该月
                                //用全部天数 干掉 截止的
                                zongDays += etTime.getDate();
                            }else{
                                zongDays += new Date(stTime.getYear()+1,v+1,0).getDate();
                            }
                        }
                    }
                    
                    //加班申请 不计算周六周天
                    if(!"1".equals(tag)){
                        //计算周六周天
                        int tian = day==0?zongDays:day;
                        for(int i = 1;i<=tian;i++){
                            //循环每一天
                            stTime.setDate(stTime.getDate()+1);
                            //判断每一天 是否是周六周天
                            if(stTime.getDay() == 6 || stTime.getDay() == 0){
                                count = count+1;
                            }
                        }
                    }
                    
                    result.put("time", String.format("%.2f", (((zongDays-count)*8+startHours+endHours)+Double.valueOf(minutes))));
                    
                    return result;
                }else{
                    if(stTime.getDate() - etTime.getDate() < 0){
                        //获得 当月天数
                        Date d = new Date(stTime.getYear(),stTime.getMonth()+1,0);
                        int days = d.getDate();
                        day = days+etTime.getDate();
                    }else{
                        day =  etTime.getDate() - stTime.getDate();
                        //获得 当月天数
                        Date d = new Date(stTime.getYear(),stTime.getMonth()+1,0);
                        if(day == 0){
                            day = d.getDate();
                        }
                        if(day<0){
                            day = d.getDate()-stTime.getDate()+etTime.getDate()-1;
                        }
                    }
                    //加班申请 不计算周六周天
                    if(!"1".equals(tag)){
                        //计算周六周天
                        for(int i = 1;i<=day;i++){
                            //循环每一天
                            stTime.setDate(stTime.getDate()+1);
                            //判断每一天 是否是周六周天
                            if(stTime.getDay() == 6 || stTime.getDay() == 0){
                                count = count+1;
                            }
                        }
                    }
                    result.put("time", String.format("%.2f", (((day-count)*8+startHours+endHours)+Double.valueOf(minutes))));
                    return result;
                }
            }else{
                //------------------------------ 同月 清空 ----------------------------------
                int count = 0;//记录有几个周六周天
                int xianggeDay = (etTime.getDate() - stTime.getDate())-1;
                
                //用正常下班时间 - 申请开始时间 
                long startTimeCha = onXiaBanDate.getTime()-stTime.getTime();
                double startHours = 0;
                //如果下班时间大于 1点 则小时数减一
                if((12 -stTime.getHours()+1)>1 && etTime.getHours() >=13){
                    // 把 秒换算成小时
                    startHours = Math.floor(startTimeCha/1000/60/60)-1;
                }else{
                    // 把 秒换算成小时
                    startHours = Math.floor(startTimeCha/1000/60/60);
                }
                //如果小于1 则改为0.0
                if(startHours<0){
                    startHours = 0;
                }
                //用申请结束时间 - 正常上班时间
                long endTimeCha = etTime.getTime() - onShangBanDate.getTime();
                double endHours = 0;
                //如果下班时间大于 1点 则小时数减一
                if(etTime.getHours() >= 17 ){
                    // 把 秒换算成小时
                    endHours = Math.floor(endTimeCha/1000/60/60)-1;
                }else{
                    // 把 秒换算成小时
                    endHours = Math.floor(endTimeCha/1000/60/60)-1;
                }
                //加班申请 不计算周六周天
                if(!"1".equals(tag)){
                    //计算周六周天
                    for(int i = 1;i<=xianggeDay;i++){
                        //循环每一天
                        stTime.setDate(stTime.getDate()+1);
                        //判断每一天 是否是周六周天
                        if(stTime.getDay() == 6 || stTime.getDay() == 0){
                            count = count+1;
                        }
                    }
                }
                //同一天
                result.put("time", String.format("%.2f", (((xianggeDay-count)*8+startHours+endHours)+Double.valueOf(minutes))));
                return result;
            }
        }
    }
    /**
     * 判断申请 是否重复
     * @param startDate
     * @param endDate
     * @param result
     * @param startTime
     * @param endTime
     * @param applyName
     * @return
     */
	public static JSONObject otherApply(Date startDate, Date endDate,
			JSONObject result, Date startTime,Date endTime,String applyName) {
		String strStartDate = DateUtil.formatDate(startDate, "yyyy-MM-dd");
		String yiStrStartDate = DateUtil.formatDate(startTime, "yyyy-MM-dd");
		String strEndDate = DateUtil.formatDate(endDate, "yyyy-MM-dd");
		String yiStrEndDate = DateUtil.formatDate(endTime, "yyyy-MM-dd");
		if(strStartDate.equals(yiStrStartDate) & strEndDate.equals(yiStrEndDate)){
			if((startDate.compareTo(startTime)>=0 & startDate.compareTo(endTime) < 0) 
					& ((startDate.compareTo(startTime)>=0 & endDate.compareTo(endTime)>=0)
		    				||(startDate.compareTo(startTime)>=0 & endDate.compareTo(endTime)<=0))){
		        result = JsonUtils.setError();
		        result.put("text", "同一时间段已发"+applyName+"申请");
		        return result;
			}else if((startDate.compareTo(startTime) <=0 & startDate.compareTo(endTime) < 0 ) 
					& ((startDate.compareTo(startTime)<=0 & endDate.compareTo(endTime)>=0)
					|| (startDate.compareTo(startTime)<=0 & endDate.compareTo(endTime)<=0))){
		        result = JsonUtils.setError();
		        result.put("text", "同一时间段已发"+applyName+"申请");
		        return result;
			}
		}else if(!strStartDate.equals(yiStrStartDate) & !strEndDate.equals(yiStrEndDate)){
			if(endDate.compareTo(startTime)!=-1 & startDate.compareTo(startTime) != -1
					& startDate.compareTo(endTime)<0){
		        result = JsonUtils.setError();
		        result.put("text", "同一时间段已发"+applyName+"申请");
		        return result;
			}else if(endDate.compareTo(startTime)<0 & endDate.compareTo(endTime)<0
					& startDate.compareTo(endTime)>0){
		        result = JsonUtils.setError();
		        result.put("text", "同一时间段已发"+applyName+"申请");
		        return result;
			}else if(startDate.compareTo(startTime)<0 & endDate.compareTo(endTime)>0){
				if((startDate.compareTo(startTime)>=0 & startDate.compareTo(endTime) < 0) 
		    			& ((startDate.compareTo(startTime)>=0 & endDate.compareTo(endTime)>=0)
		        				||(startDate.compareTo(startTime)>=0 & endDate.compareTo(endTime)<=0))){
		            result = JsonUtils.setError();
		            result.put("text", "同一时间段已发"+applyName+"申请");
		            return result;
				}else if((startDate.compareTo(startTime) <=0 & startDate.compareTo(endTime) < 0 ) 
						& ((startDate.compareTo(startTime)<=0 & endDate.compareTo(endTime)>=0)
						|| (startDate.compareTo(startTime)<=0 & endDate.compareTo(endTime)<=0))){
		            result = JsonUtils.setError();
		            result.put("text", "同一时间段已发"+applyName+"申请");
		            return result;
				}
			}
		}else if(strStartDate.equals(yiStrStartDate) & !strEndDate.equals(yiStrEndDate)){
		    result = JsonUtils.setError();
		    result.put("text", "同一时间段已发"+applyName+"申请");
		    return result;
		}else if(!strStartDate.equals(yiStrStartDate) & strEndDate.equals(yiStrEndDate)){
		    result = JsonUtils.setError();
		    result.put("text", "同一时间段已发"+applyName+"申请");
		    return result;
		}
		return result;
	}
}
