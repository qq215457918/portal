package com.portal.common.util;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;

/**
 * 字符串、时间等基础应用处理
 * 
 */
public class StringUtil {

    public static String getOrderNo() {
        LocalDateTime nowDate = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMddHHmmssS");
        return nowDate.format(format);
    }

    /**
     * 获取当前时间时分秒
     * 
     * @return
     */
    public static String yearMd() {
        String now = StringUtil.dateString(new Date());
        String year = StringUtil.year(now);
        year = year.substring(year.length() - 2, year.length());
        String currentTimeMillis = StringUtil.cStr(System.currentTimeMillis());
        currentTimeMillis = currentTimeMillis.substring(
                currentTimeMillis.length() - 3, currentTimeMillis.length());
        return year + StringUtil.month(now) + StringUtil.day(now)
                + StringUtil.minute(now) + StringUtil.second(now)
                + currentTimeMillis;
    }

    /**
     * 获取访问者IP地址
     * 
     * @param request
     * @return
     */
    public static String getIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Forwarded-For");
            if (ip != null && ip.length() != 0
                    && !"unknown".equalsIgnoreCase(ip)) {
                // 多次反向代理后会有多个IP值，第一个为真实IP。
                int index = ip.indexOf(',');
                if (index != -1) {
                    ip = ip.substring(0, index);
                }
            }
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * 字符串SQL注入过滤
     * 
     * @param str
     *            SQL字符串
     * @return 过滤注入后的字符串
     */
    public static String tstr(String str) {
        str = nul(str);
        str = str.replace('<', '＜');
        str = str.replace('>', '＞');
        str = str.replace("'", "''");
        str = str.replace('\"', '＂');
        str = str.replace('%', '％');
        str = str.replace("--", "－－");
        // 插入数据库日志这一段待续　2012年8月22日　LIF
        return str;
    }

    /**
     * 类似于ASP中的IIF判断
     * 
     * @param Con1
     *            判断条件
     * @param Rev1
     *            true结果
     * @param Rev2
     *            false结果
     * @return 返回C?A:B的结果
     */
    public static String iif(boolean Con1, String Rev1, String Rev2) {
        return Con1 ? Rev1 : Rev2;
    }

    /**
     * 判断对象是否为null
     * 
     * @param obj
     * @return
     */
    public static boolean isNull(Object obj) {
        return obj == null || "".equals(obj);
    }

    /**
     * 规格化字符串(自动转换数据类型为字符串，NULL转换成空) 
     * String.valueOf(obj).trim()->String.valueOf(obj)
     * 
     * @param obj
     * @return
     */
    public static String nul(Object obj) {
        return isNull(obj) ? "" : String.valueOf(obj);
    }

    /**
     * 比较字符串是否相同
     * 
     * @param obj1
     * @param obj2
     * @return 相同返回true
     */
    public static boolean equ(Object obj1, Object obj2) {
        return nul(obj1).equals(nul(obj2));
    }

    /**
     * 比较字符串是否不同
     * 
     * @param obj1
     * @param obj2
     * @return 不同返回true
     */
    public static boolean neq(Object obj1, Object obj2) {
        return !(nul(obj1).equals(nul(obj2)));
    }

    /**
     * 判断提交过来的参数是否不为空(或无此参数)
     * 
     * @param obj
     * @return 不为空返回true
     */
    public static boolean notEmpty(Object obj) {
        String str = nul(obj);
        return !(equ(str, "") || equ(str, "undefined"));
    }

    /**
     * 判断提交过来的参数是否不为空并不为零(或无此参数)
     * 
     * @param obj
     * @return 参数不为零和空，则返回true
     */
    public static boolean notZero(Object obj) {
        String str = nul(obj);
        return !(equ(str, 0) || equ(str, "") || equ(str, "undefined"));
    }

    /**
     * 去掉字符串中的最后一个字符
     * 
     * @param str
     * @return
     */
    public static String lef(String str) {
        int len = str.length();
        return len > 0 ? str.substring(0, len - 1) : "";
    }

    /**
     * 判断obj1是否等于字符串obj2里的任意一个值(每个值之间用,隔开)，
     * 
     * @param obj1
     * @param obj2
     * @return 如果是则返回true，如果都不等于则返回false
     */
    public static boolean equr(Object obj1, Object obj2) {
        String[] str = nul(obj2).split("\\,");
        for (int i = 0; i < str.length; i ++) {
            if (equ(obj1, str[i]))
                return true;
        }
        return false;
    }

    /**
     * 如果obj1为空则返回obj2，否则返回obj1
     * 
     * @param obj1
     * @param obj2
     * @return
     */
    public static String pick(Object obj1, Object obj2) {
        if (equ(obj1, ""))
            return nul(obj2);
        return nul(obj1);
    }

    /**
     * 只替换字符串中的单引号
     * 
     * @param obj
     * @return
     */
    public static String ubbTstr(Object obj) {
        return nul(obj).replace("'", "''");
    }

    /**
     * HTML 格式编码 比如“中国人”转换为“%E4%B8%AD%E5%9B%BD%E4%BA%BA”
     * 
     * @param str
     * @return
     */

    public static String urlStr(String str) {
        try {
            return java.net.URLEncoder.encode(str, "UTF-8");
        } catch (Exception ex) {
            return str;
        }
    }

    /**
     * HTML 格式解码 比如“%E4%B8%AD%E5%9B%BD%E4%BA%BA”转换为“中国人”
     * 
     * @param str
     * @return
     */
    public static String deUrlStr(String str) {
        try {
            return java.net.URLDecoder.decode(str, "UTF-8");
        } catch (Exception ex) {
            return str;
        }
    }

    /**
     * 字符串转换为int
     * 
     * @param str
     * @return
     */
    public static int cInt(String str) {
        if (StringUtil.equ(str, "")) {
            return 0;
        } else {
            try {
                return Integer.parseInt(str.trim());
            } catch (Exception e) {
                return 0;
            }
        }
    }

    public static int cInt(Object str) {
        return Integer.parseInt(nul(str).trim());
    }

    /**
     * 获取当前日期是星期几<br>
     * 
     * @param dt
     * @return 当前日期是星期几
     */
    public static String getWeekOfDate(Date dt) {
        String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
            w = 0;
        }
        return weekDays[w];
    }

    /**
     * 判断指定的字符串是否标准时间 四种标准时间格式：yyyy-MM-dd、yyyy/MM/dd 、 yyyy-MM-dd
     * HH:mm:ss、yyyy/MM/dd HH:mm:ss 不要求必须补齐位数(2011-3-5 12:3:5是合法的)
     * 
     * @param sDate
     * @return
     */
    public static boolean isDate(String sDate) {
        if (sDate.indexOf(" ") + sDate.indexOf(":") == -2)
            sDate += " 0:0:0";
        java.text.SimpleDateFormat df = new java.text.SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        try {
            df.setLenient(false);
            df.parse(sDate);
            return true;
        } catch (java.text.ParseException ex) {
            df = new java.text.SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            try {
                df.setLenient(false);
                df.parse(sDate);
                return true;
            } catch (java.text.ParseException ey) {
                return false;
            }
        }
    }

    /**
     * 返回当前时间为util.Date类型
     * 
     * @return
     */
    public static java.util.Date getDate() {
        return new java.util.Date();
    }

    /**
     * 转换指定的标准时间为util.Date类型 四种标准时间格式：yyyy-MM-dd、yyyy/MM/dd 、 yyyy-MM-dd
     * HH:mm:ss、yyyy/MM/dd HH:mm:ss 只有年月日的，自动补齐0时0分0秒
     * 
     * @param sDate
     * @return
     */
    public static java.util.Date getDate(String sDate) {
        if (sDate.indexOf(" ") + sDate.indexOf(":") == -2)
            sDate += " 0:0:0";
        java.text.SimpleDateFormat df = new java.text.SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        try {
            df.setLenient(false);
            return df.parse(sDate);
        } catch (java.text.ParseException ex) {
            df = new java.text.SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            try {
                df.setLenient(false);
                return df.parse(sDate);
            } catch (java.text.ParseException ey) {
                return new java.util.Date();
            }
        }
    }

    /**
     * 将util.Date时间转换为标准时间格式(字符串)
     * 
     * @param dat
     * @return
     */
    public static String dateString(Object dat) {
        if (dat == null)
            return "";
        java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        String dateStr = formatter.format(dat);
        return dateStr;
    }

    /**
     * 将util.Date时间转换为标准时间格式(字符串YYYY-MM-DD)
     * 
     * @param dat
     * @return
     */
    public static String shortDateString(Object dat) {
        // ZDJ 2012年11月1日 修改(解决秒位补0的问题)
        if (dat == null)
            return "";
        String strFormat = "";
        if (instr(dat.toString(), ":") > 0) {
            strFormat = "yyyy-MM-dd HH:mm:ss";
        } else {
            strFormat = "yyyy-MM-dd";
        }
        java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(
                strFormat);
        String dateStr = formatter.format(dat);
        return dateStr;
    }

    /**
     * 相当于ASP中的cDate函数
     * 
     */
    public static String cDate(String dat) {
        return dateString(getDate(dat));
    }

    /**
     * 相当于ASP中的date()函数
     * 
     * @return
     */
    public static String date() {
        return new java.sql.Date(System.currentTimeMillis()).toString();
    }

    /**
     * 相当于ASP中的now()函数
     * 
     * @return
     */
    public static String now() {
        return dateString(getDate());
    }

    /**
     * 相当于ASP中的year()函数
     * 
     * @param dat
     *            标准时间格式
     * @return
     */
    public static String year(String dat) {
        if (isDate(dat))
            return dateString(getDate(dat)).substring(0, 4);
        return null;
    }

    /**
     * 相当于ASP中的month()函数
     * 
     * @param dat
     *            标准时间格式
     * @return
     */
    public static String month(String dat) {
        if (isDate(dat))
            return dateString(getDate(dat)).substring(5, 7);
        return null;
    }

    /**
     * 相当于ASP中的day()函数
     * 
     * @param dat
     *            标准时间格式
     * @return
     */
    public static String day(String dat) {
        if (isDate(dat))
            return dateString(getDate(dat)).substring(8, 10);
        return null;
    }

    /**
     * 相当于ASP中的hour()函数
     * 
     * @param dat
     *            标准时间格式
     * @return
     */
    public static String hour(String dat) {
        if (isDate(dat))
            return dateString(getDate(dat)).substring(11, 13);
        return null;
    }

    /**
     * 相当于ASP中的minute()函数
     * 
     * @param dat
     *            标准时间格式
     * @return
     */
    public static String minute(String dat) {
        if (isDate(dat))
            return dateString(getDate(dat)).substring(14, 16);
        return null;
    }

    /**
     * 相当于ASP中的second()函数
     * 
     * @param dat
     *            标准时间格式
     * @return
     */
    public static String second(String dat) {
        if (isDate(dat))
            return dateString(getDate(dat)).substring(17, 19);
        return null;
    }

    /**
     * 获取本月天数
     * 
     * @param dat
     *            标准时间格式
     * @return
     */
    public static int getLastDateOfMonth(String dat) {
        int Y = cInt(year(dat));
        int[] A = { 31, Y % 4 == 0 && Y % 100 != 0 || Y % 400 == 0 ? 29 : 28,
                31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
        return A[cInt(month(dat)) - 1];
    }

    /**
     * 计算时间差
     * 
     * @param interval
     *            间隔单位 s-秒 n-分 h-时 d-天
     * @param date1
     *            标准时间格式
     * @param date2
     *            标准时间格式
     * @return
     */
    public static long dateDiff(String interval, String date1, String date2) {
        if (!isDate(date1) || !isDate(date2))
            return 0;
        long inter;
        if (interval == "s") {
            inter = 1000;
        } else if (interval == "n") {
            inter = 1000 * 60;
        } else if (interval == "h") {
            inter = 1000 * 60 * 60;
        } else if (interval == "d") {
            inter = 1000 * 60 * 60 * 24;
        } else {
            inter = 1;
        }
        long day = (getDate(date2).getTime() - getDate(date1).getTime())
                / inter;
        return day;
    }

    /**
     * 时间加减运算
     * 
     * @param interval
     *            间隔单位
     * @param num
     *            间隔数量
     * @param date
     *            起始时间
     * @return
     */
    public static String dateAdd(String interval, int num, String date) {
        java.util.Calendar rightNow = java.util.Calendar.getInstance();
        rightNow.setTime(getDate(date));
        int inter;
        if (interval == "s") {
            inter = java.util.Calendar.SECOND;
        } else if (interval == "n") {
            inter = java.util.Calendar.MINUTE;
        } else if (interval == "h") {
            inter = java.util.Calendar.HOUR;
        } else if (interval == "d") {
            inter = java.util.Calendar.DATE;
        } else if (interval == "m") {
            inter = java.util.Calendar.MONTH;
        } else if (interval == "y") {
            inter = java.util.Calendar.YEAR;
        } else {
            return null;
        }
        rightNow.add(inter, num);
        java.util.Date dt = rightNow.getTime();
        return dateString(dt);
    }

    /**
     * 在str1中查找str2
     * 
     * @param str1
     * @param str2
     * @return 返回按ASP从1开始是第1个字符，没有找到返回0而不是返回-1
     *         代码中将其结果直接当boolean型使用时，改成判断是否等于0即可。
     */
    public static int instr(String str1, String str2) {
        if (isNull(str1))
            return 0;
        return str1.indexOf(str2) + 1;
    }

    /**
     * 在str1中反向查找str2
     * 
     * @param str1
     * @param str2
     * @return 返回按ASP从1开始是第1个字符，没有找到返回0而不是返回-1
     */
    public static int instrRev(String str1, String str2) {
        if (isNull(str1))
            return 0;
        return str1.lastIndexOf(str2) + 1;
    }

    /**
     * 在str1中查找str2，查找之前，str1和str2都用","包括起来
     * 
     * @param str1
     * @param str2
     * @return 如果找到返回true,否则返回false
     */
    public static boolean where(String str1, String str2) {
        return ("," + str1 + ",").indexOf("," + str2 + ",") > -1;
    }

    /**
     * 返回字符中长度
     * 
     * @param str
     * @return
     */
    public static int len(String str) {
        if (isNull(str))
            return 0;
        return str.length();
    }

    /**
     * 改小写
     * 
     * @param str
     * @return
     */
    public static String lcase(String str) {
        if (isNull(str))
            return str;
        return str.toLowerCase();
    }

    /**
     * 改大写
     * 
     * @param str
     * @return
     */
    public static String ucase(String str) {
        if (isNull(str))
            return str;
        return str.toUpperCase();
    }

    /**
     * 替换
     * 
     * @param str1
     * @param str2
     * @param str3
     * @return
     */
    public static String replace(String str1, String str2, String str3) {
        if (isNull(str1))
            return str1;
        return str1.replace(cStr(str2), cStr(str3));
    }

    /**
     * 拆分字符串为数组
     */
    public static String[] split(String str1, String str2) {
        str1 += " ";
        if (equ(str2, ","))
            str2 = "\\,";
        if (equ(str2, "|"))
            str2 = "\\|";
        if (equ(str2, "("))
            str2 = "\\(";
        if (equ(str2, "{"))
            str2 = "\\{";
        if (equ(str2, "$"))
            str2 = "\\$";
        if (equ(str2, "@"))
            str2 = "\\@";
        return str1.split(str2);
    }

    /**
     * 连接数组元素为字符串
     */
    public static String join(String[] arr, String str) {
        if (arr == null)
            return "";
        StringBuffer buff = new StringBuffer();
        for (int i = 0, len = arr.length; i < len; i ++) {
            buff.append(String.valueOf(arr[i]));
            if (i < len - 1)
                buff.append(str);
        }
        return buff.toString();
    }

    /**
     * 返回数组最大下标
     */
    public static int ubound(String[] arr) {
        return arr.length - 1;
    }

    /**
     * 相当于asp的left函数
     */
    public static String left(String str, int alt) {
        if (equ(nul(str), "")) {
            return "";
        }
        return nul(str).substring(0, alt);
    }

    /**
     * 返回字符串的首字符(左边第一个字符) 返回类型：String
     * 
     * @return
     */
    public static String getStartChar(String str) {
        return nul(str).replaceAll("^(.).+", "$1");
    }

    /**
     * 删除字符串中的首字符(即返回去掉第一个字符之后的内容) 返回类型：String
     * 
     * @return
     */
    public static String delStartChar(String str) {
        return nul(str).replaceAll("^(?:.)(.+)$", "$1");
    }

    /**
     * 返回字符串的末位字符(右边第一个字符) 返回类型：String
     * 
     * @return
     */
    public static String getEndChar(String str) {
        return nul(str).replaceAll("^.+(.)$", "$1");
    }

    /**
     * 删除字符串中的末位字符(即返回去掉最后一个字符之前的内容) 与lef方法等效 返回类型：String
     * 
     * @return
     */
    public static String delEndChar(String str) {
        return nul(str).replaceAll("^(.+).$", "$1");
    }

    /**
     * 返回按指定长度拆分的字符串数组
     * 
     * @param str
     * @return
     */
    public static String[] splitString(String str, int areaLong) {
        int ln = str.length();
        String[] sArr = null;
        if (ln % areaLong > 0) {
            sArr = new String[ln / areaLong + 1];
        } else {
            sArr = new String[ln / areaLong];
        }
        for (int i = 0; i < ln; i += areaLong) {
            if (i >= ln - areaLong) {
                sArr[i / areaLong] = str.substring(i);
            } else {
                sArr[i / areaLong] = str.substring(i, i + areaLong);
            }
        }
        return sArr;
    }

    public static void main(String[] arg) {}

    /**
     * 相当于asp的right函数
     * 
     * @param str
     * @param alt
     * @return
     */
    public static String right(String str, int alt) {
        if (equ(nul(str), "")) {
            return "";
        }
        return nul(str).substring(str.length() - alt, str.length());
    }

    /**
     * 相当于asp的trim函数
     * 
     * @param str
     * @return
     */
    public static String trim(Object str) {
        return nul(str).trim();
    }

    /**
     * 转换为String
     * 
     * @param str
     * @return
     */
    public static String cStr(Object str) {
        return nul(str);
    }

    /**
     * 转换为double
     * 
     * @param str
     * @return
     */
    public static Double cDbl(String str) {
        try {
            return Double.parseDouble(str.trim());
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 转换为boolean
     */
    public static boolean cBool(String str) {
        return neq(nul(str), "");
    }

    public static boolean cBool(int num) {
        return num != 0;
    }

    /**
     * 四舍五入
     */
    public static double round(double dbl, int cnt) {
        return Math.round(dbl * Math.pow(10, cnt)) / Math.pow(10, cnt);
    }

    /**
     * 判断字符串是否为数字
     */
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("-?[0-9]+.?[0-9]*");
        java.util.regex.Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    /**
     * 对应asp中的chr函数
     */
    public static String chr(int ascCode) {
        return (char) ascCode + "";
    }

    /**
     * 如果是null或空字符串，则返回0
     */
    public static String to0(Object str) {
        String temp = nul(str);
        if (equ(temp, "")) {
            return "0";
        }
        return temp;
    }

    /**
     * 是否移动设备，含IPAD和安卓系统，目前用于判断图标导航模式是否启用
     * 
     * @return ASP中返回(1是0否)，现改为返回bool值
     */
    public static boolean isPad(String sAgent) {
        // request.ServerVariables("HTTP_USER_AGENT");
        // String sAgent = request.getHeader("User-Agent");
        return instr(sAgent, "iPhone") > 0 || instr(sAgent, "iPad") > 0
                || instr(sAgent, "iPod") > 0;
    }

    /**
     * 是否移动设备，含IPAD和安卓系统，目前用于判断图标导航模式是否启用
     * 
     * @return ASP中返回(1是0否)，现改为返回bool值
     */
    public static boolean isMob(String sAgent) {
        // request.ServerVariables("HTTP_USER_AGENT");
        // String sAgent = request.getHeader("User-Agent");
        return instr(sAgent, "Mobile") > 0 || instr(sAgent, "android") > 0
                || isPad(sAgent);
    }

    /**
     * 取得Map指定位置的值
     * 
     * @param map
     * @param index
     * @return String
     */
    public static String getColValue(Map<String, Object> map, int index) {
        int i = 0;
        if (!map.isEmpty()) {
            Iterator<Entry<String, Object>> it = map.entrySet().iterator();
            while (it.hasNext()) {
                Entry<String, Object> next = (Entry<String, Object>) it.next();
                if (i == index) {
                    return nul(next.getValue());
                }
                i ++;
            }
        }
        return "";
    }

    /**
     * 转换SQL中ORACLE的兼容=''的写法
     */
    public static String sqlEqu(String str) {
        if (equ(str, "")) {
            return " IS NULL ";
        } else {
            return "='" + nul(str) + "' ";
        }
    }

    /**
     * 转换SQL中ORACLE的兼容<>''的写法
     */
    public static String sqlNeq(String str) {
        if (equ(str, "")) {
            return " IS NOT NULL ";
        } else {
            return "<>'" + nul(str) + "' ";
        }
    }

    /**
     * 相当于ASP中的mid函数
     * 
     * @param str
     * @param Start
     * @param Length
     * @return
     */
    public static String mid(String str, int Start, int Length) {
        str = nul(str);
        if (str == null || Start <= 0) {
            return null;
        } else if (str.equals("")) {
            return "";
        } else if (Start + Length - 1 >= str.length()) {
            return str.substring(Start - 1);
        } else {
            return str.substring(Start - 1, Start + Length - 1);
        }
    }

    /**
     * 对数组join的重载，支持对Map进行join
     * 
     * @param map
     * @param Spliter
     * @return
     */
    public static String join(Map<String, Object> map, String Spliter) {
        String Rev = "";
        if (!map.isEmpty()) {
            java.util.Iterator<Map.Entry<String, Object>> it = map.entrySet()
                    .iterator();
            while (it.hasNext()) {
                Map.Entry<String, Object> next = (Map.Entry<String, Object>) it
                        .next();
                Rev += nul(next.getValue()) + (it.hasNext() ? Spliter : "");
            }
        }
        return Rev;
    }

    /**
     * 取模，相当于asp中的mod
     * 
     * @param dbl1
     * @param dbl2
     * @return
     */
    public static int mod(double dbl1, double dbl2) {
        return (int) (dbl1 % dbl2);
    }

    public static int mod(int dbl1, double dbl2) {
        return (int) (dbl1 % dbl2);
    }

    public static int mod(int dbl1, int dbl2) {
        return (int) (dbl1 % dbl2);
    }

    public static int mod(double dbl1, int dbl2) {
        return (int) (dbl1 % dbl2);
    }

    /**
     * 将指定内容复制到剪切板中
     * 
     * @param code
     */
    public static void cpy(String code) {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable trandata = new StringSelection(code);
        clipboard.setContents(trandata, null);
    }

    /**
     * 是否是Windows操作系统
     * 
     * @return
     */
    public static boolean isWindowsOS() {
        String os = System.getProperty("os.name");
        return os.startsWith("Windows");
    }

    /**
     * 是否是Linux操作系统
     * 
     * @return
     */
    public static boolean isLinuxOS() {
        String os = System.getProperty("os.name");
        return os.startsWith("Linux");
    }

    /**
     * 获得系统的属性值
     * 
     * @param propertyName
     * @return
     */
    public static String getSysProperty(String propertyName) {
        return System.getProperty(propertyName);
    }

    /**
     * 执行本地系统命令
     * 
     * @param cmdName
     * @param param
     * @return
     */
    public static String runLocalCmd(String cmdName, String param) {
        StringBuffer sbf = new StringBuffer();
        try {
            ProcessBuilder pb = new ProcessBuilder("dmidecode", "-t 2");
            Process p = pb.start();
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    p.getInputStream()));
            String line = "";
            String EntChar = isWindowsOS() ? "\r\n" : "\n";
            while ((line = br.readLine()) != null) {
                sbf.append(line).append(EntChar);
            }
            br.close();
        } catch (IOException ex) {}
        return sbf.toString();
    }

    /**
     * Map转换成数组(忽略Key，按序生成)
     */
    public static String[] mapToArray(Map<String, Object> map) {
        String[] Rev = null;
        int i = 0;
        if (!map.isEmpty()) {
            java.util.Iterator<Map.Entry<String, Object>> it = map.entrySet()
                    .iterator();
            Rev = new String[map.size()];
            while (it.hasNext()) {
                Map.Entry<String, Object> next = (Map.Entry<String, Object>) it
                        .next();
                Rev[i] = nul(next.getValue());
                i ++;
            }
        }
        return Rev;
    }

    /**
     * Map转换成调用action的参数
     */
    public static String mapToParam(Map<String, Object> map) {
        String rtn = "";
        if (!map.isEmpty()) {
            java.util.Iterator<Map.Entry<String, Object>> it = map.entrySet()
                    .iterator();
            while (it.hasNext()) {
                Map.Entry<String, Object> next = (Map.Entry<String, Object>) it
                        .next();
                rtn += next.getKey() + "=" + next.getValue() + "&";
            }
            if (rtn.length() > 0 && rtn.endsWith("&")) {
                rtn = rtn.substring(0, rtn.length() - 1);
            }
        }
        return rtn;
    }

    /**
     * 数组转换成Map(Key为数组下标(String型))
     * 
     * @param array
     * @return
     */
    public static Map<String, Object> arrayToMap(String[] array) {
        Map<String, Object> map = new java.util.HashMap<String, Object>();
        for (int i = 0; i < array.length; i ++) {
            map.put(nul(i), array[i]);
        }
        return map;
    }

    /**
     * 对象转换为String类型
     * 
     * @param obj
     * @return
     */
    public static String Object2String(Object obj) {
        if (obj == null)
            return "";
        return obj.toString();
    }

    /**
     * 对象转换为Long类型(无法解析时返回null)
     * 
     * @param obj
     * @return
     */
    public static Long Object2Long(Object obj) {
        if (obj == null)
            return null;
        try {
            return Long.decode(obj.toString());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    /**
     * 对象转换为Long类型(无法解析时返回null)
     * 
     * @param obj
     * @return
     */
    public static Long cLng(Object obj) {
        return Object2Long(obj);
    }

    // 获取 request中的Domain
    public static String getDomain(HttpServletRequest request) {
        String rurl = StringUtil.cStr(request.getRequestURL());
        String ruri = StringUtil.cStr(request.getRequestURI());
        String dm1 = rurl.substring(0, rurl.length() - ruri.length());
        if (StringUtil.equ(StringUtil.right(dm1, 1), "/")) {
            dm1 = StringUtil.lef(dm1);
        }
        dm1 = dm1.replace("http://", "").replace("https://", "");
        if (dm1.indexOf(":") > -1) {
            dm1 = dm1.split(":")[0];
        }
        return dm1;
    }

    /**
     * 处理url
     * 
     * url为null返回null，url为空串或以http://或https://开头返回原参数，其他情况则加上http://。
     * 
     * @param url
     * @return
     */
    public static String handelUrl(String url) {
        if (url == null) {
            return null;
        }
        url = url.trim();
        if (url.equals("") || url.startsWith("http://")
                || url.startsWith("https://")) {
            return url;
        } else {
            return "http://" + url.trim();
        }
    }

    /**
     * 分割并且去除空格
     * 
     * @param str
     *            待分割字符串
     * @param sep
     *            分割符
     * @param sep2
     *            第二个分隔符
     * @return 如果str为空，则返回null。
     */
    public static String[] splitAndTrim(String str, String sep, String sep2) {
        if (StringUtils.isBlank(str)) {
            return null;
        }
        if (!StringUtils.isBlank(sep2)) {
            str = StringUtils.replace(str, sep2, sep);
        }
        String[] arr = StringUtils.split(str, sep);
        // trim
        for (int i = 0, len = arr.length; i < len; i ++) {
            arr[i] = arr[i].trim();
        }
        return arr;
    }

    /**
     * 文本转html
     * 
     * @param txt
     * @return
     */
    public static String txt2htm(String txt) {
        if (StringUtils.isBlank(txt)) {
            return txt;
        }
        StringBuilder sb = new StringBuilder((int) (txt.length() * 1.2));
        char c;
        boolean doub = false;
        for (int i = 0; i < txt.length(); i ++) {
            c = txt.charAt(i);
            if (c == ' ') {
                if (doub) {
                    sb.append(' ');
                    doub = false;
                } else {
                    sb.append("&nbsp;");
                    doub = true;
                }
            } else {
                doub = false;
                switch (c) {
                    case '&':
                        sb.append("&amp;");
                        break;
                    case '<':
                        sb.append("&lt;");
                        break;
                    case '>':
                        sb.append("&gt;");
                        break;
                    case '"':
                        sb.append("&quot;");
                        break;
                    case '\n':
                        sb.append("<br/>");
                        break;
                    default:
                        sb.append(c);
                        break;
                }
            }
        }
        return sb.toString();
    }

    /**
     * 剪切文本。如果进行了剪切，则在文本后加上"..."
     * 
     * @param s
     *            剪切对象。
     * @param len
     *            编码小于256的作为一个字符，大于256的作为两个字符。
     * @return
     */
    public static String textCut(String s, int len, String append) {
        if (s == null) {
            return null;
        }
        int slen = s.length();
        if (slen <= len) {
            return s;
        }
        // 最大计数（如果全是英文）
        int maxCount = len * 2;
        int count = 0;
        int i = 0;
        for (; count < maxCount && i < slen; i ++) {
            if (s.codePointAt(i) < 256) {
                count ++;
            } else {
                count += 2;
            }
        }
        if (i < slen) {
            if (count > maxCount) {
                i --;
            }
            if (!StringUtils.isBlank(append)) {
                if (s.codePointAt(i - 1) < 256) {
                    i -= 2;
                } else {
                    i --;
                }
                return s.substring(0, i) + append;
            } else {
                return s.substring(0, i);
            }
        } else {
            return s;
        }
    }

    //	public static String htmlCut(String s, int len, String append) {
    //		String text = html2Text(s, len * 2);
    //		return textCut(text, len, append);
    //	}
    //
    //	public static String html2Text(String html, int len) {
    //		try {
    //			Lexer lexer = new Lexer(html);
    //			Node node;
    //			StringBuilder sb = new StringBuilder(html.length());
    //			while ((node = lexer.nextNode()) != null) {
    //				if (node instanceof TextNode) {
    //					sb.append(node.toHtml());
    //				}
    //				if (sb.length() > len) {
    //					break;
    //				}
    //			}
    //			return sb.toString();
    //		} catch (ParserException e) {
    //			throw new RuntimeException(e);
    //		}
    //	}

    /**
     * 检查字符串中是否包含被搜索的字符串。被搜索的字符串可以使用通配符'*'。
     * 
     * @param str
     * @param search
     * @return
     */
    public static boolean contains(String str, String search) {
        if (StringUtils.isBlank(str) || StringUtils.isBlank(search)) {
            return false;
        }
        String reg = StringUtils.replace(search, "*", ".*");
        Pattern p = Pattern.compile(reg);
        return p.matcher(str).matches();
    }

    public static boolean containsKeyString(String str) {
        if (StringUtils.isBlank(str)) {
            return false;
        }
        if (str.contains("'") || str.contains("\"") || str.contains("\r")
                || str.contains("\n") || str.contains("\t")
                || str.contains("\b") || str.contains("\f")) {
            return true;
        }
        return false;
    }

    // 将""和'转义
    public static String replaceKeyString(String str) {
        if (containsKeyString(str)) {
            return str.replace("'", "\\'").replace("\"", "\\\"")
                    .replace("\r", "\\r").replace("\n", "\\n")
                    .replace("\t", "\\t").replace("\b", "\\b")
                    .replace("\f", "\\f");
        } else {
            return str;
        }
    }

    /**
     * 解析URL返回指定的参数值
     * 
     * @param URL
     * @param Arg
     * @return
     */
    public static String parseURL(String URL, String Arg) {
        String Args = "";
        if (URL.indexOf("?") == -1) {
            return Args;
        } else {
            Args = URL.split("\\?")[1];
        }
        String[] argList = Args.split("\\&");
        String[] emp = null;
        for (String str : argList) {
            emp = str.split("=");
            if (emp[0].equalsIgnoreCase(Arg)) {
                return emp[1];
            }
        }
        return "";
    }

    /**
     * map转换成JOSN
     * 
     * @param map
     * @return
     */
    public static String mapjson(Map<?, ?> map) {
        StringBuilder json = new StringBuilder();
        json.append("{");
        if (map != null && map.size() > 0) {
            for (Object key : map.keySet()) {
                json.append(objectjson(key));
                json.append(":");
                json.append(objectjson(map.get(key)));
                json.append(",");
            }
            json.setCharAt(json.length() - 1, '}');
        } else {
            json.append("}");
        }
        return json.toString();
    }

    /**
     * 对象转换成JSON
     * 
     * @param obj
     * @return
     */
    public static String objectjson(Object obj) {
        StringBuilder json = new StringBuilder();
        if (obj == null) {
            json.append("\"\"");
        } else if (obj instanceof String || obj instanceof Integer
                || obj instanceof Float || obj instanceof Boolean
                || obj instanceof Short || obj instanceof Double
                || obj instanceof Long || obj instanceof BigDecimal
                || obj instanceof BigInteger || obj instanceof Byte) {
            json.append("\"").append(stringjson(obj.toString())).append("\"");
        } else if (obj instanceof Map) {
            json.append(mapjson((Map<?, ?>) obj));
        }
        return json.toString();
    }

    /**
     * 字符串转换成JSON
     * 
     * @param s
     * @return
     */
    public static String stringjson(String s) {
        if (s == null)
            return "";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i ++) {
            char ch = s.charAt(i);
            switch (ch) {
                case '"':
                    sb.append("\\\"");
                    break;
                case '\\':
                    sb.append("\\\\");
                    break;
                case '\b':
                    sb.append("\\b");
                    break;
                case '\f':
                    sb.append("\\f");
                    break;
                case '\n':
                    sb.append("\\n");
                    break;
                case '\r':
                    sb.append("\\r");
                    break;
                case '\t':
                    sb.append("\\t");
                    break;
                case '/':
                    // sb.append("\\/");
                    sb.append("/");
                    break;
                default:
                    if (ch >= '\u0000' && ch <= '\u001F') {
                        String ss = Integer.toHexString(ch);
                        sb.append("\\u");
                        for (int k = 0; k < 4 - ss.length(); k ++) {
                            sb.append('0');
                        }
                        sb.append(ss.toUpperCase());
                    } else {
                        sb.append(ch);
                    }
            }
        }
        return sb.toString();
    }

    public static String sumFormat(String sum, int xsw) {
        if (isNull(sum)) {
            sum = "0";
        }
        BigDecimal big = new BigDecimal(sum);
        big = big.setScale(xsw, BigDecimal.ROUND_HALF_UP);
        return big.toString();
    }

    /**
     * 将字符串中的指定内容全部替换成指定的大小写形式 如将ABC、abc、Abc、aBc统一替换成"abc"可使用此方法
     * 
     * @param origString
     * @param reps
     * @return
     */
    public static String replaceIgnoerCase(String origString, String reps) {
        return Pattern.compile(reps, Pattern.CASE_INSENSITIVE)
                .matcher(origString).replaceAll(reps);
    }

    /**
     * Excel中的列名转换工具
     * 
     * @param columnIndex
     *            列的索引(第一列“A”的索引为0)
     * @return 列的名称
     */
    public static String getColumnName(int columnIndex) {
        int b = columnIndex / 26, c = columnIndex - b * 26 + 65, d = b + 64;
        return ((d == 64) ? "" : (char) d) + "" + (char) c;
    }

    /**
     * 远程同步访问
     * 
     * @param urlString
     *            远程URL
     * @param requestMethod
     *            请求方式(不传默认为GET)
     * @return
     */
    public static String doUrl(String urlString, String... requestMethod) {
        String res = "";
        try {
            URL url = new URL(urlString);
            java.net.HttpURLConnection conn = (java.net.HttpURLConnection) url
                    .openConnection();
            conn.setDoOutput(true);
            String strMethod = "GET";
            if (requestMethod == null || requestMethod.length == 0)
                strMethod = "GET";
            else
                strMethod = requestMethod[0];
            conn.setRequestMethod(strMethod);
            java.io.BufferedReader in = new java.io.BufferedReader(
                    new java.io.InputStreamReader(conn.getInputStream(),
                            "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                res += line;
            }
            in.close();
        } catch (Exception e) {
            res = e.getMessage();
        }
        return res;
    }

    // String[] 转换为 String
    public static String arrToStr(String[] arr) {
        if (arr == null) {
            return "arr is empty.";
        } else {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < arr.length; i ++)
                sb.append("[").append(arr[i]).append("] ");
            return sb.toString();
        }
    }

    /**
     * 读取文件内容
     * 
     * @param file
     * @return
     */
    public static String readFileContent(File file) {
        if (file == null || !file.exists()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        try {
            InputStream is = new FileInputStream(file);
            BufferedReader fr = new BufferedReader(new InputStreamReader(is,
                    "utf-8"));
            char[] temp = new char[1024];
            int i = -1;
            while ((i = fr.read(temp)) != -1) {
                sb.append(temp, 0, i);
            }
            fr.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    /**
     * 附件大小格式化
     * 
     * @param fileS
     * @return
     */
    public static String FormetFileSize(long fileS) {
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString = "";
        if (fileS < 1024) {
            fileSizeString = df.format((double) fileS) + "B";
        } else if (fileS < 1048576) {
            fileSizeString = df.format((double) fileS / 1024) + "K";
        } else if (fileS < 1073741824) {
            fileSizeString = df.format((double) fileS / 1048576) + "M";
        } else {
            fileSizeString = df.format((double) fileS / 1073741824) + "G";
        }
        return fileSizeString;
    }

    /**
     *方法名:getUUID
     *返回值:String
     *作     用:获得uuid生成的id
     *作     者:夏政委
     *日     期:2014年12月2日 上午9:07:23
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 将指定字符串转换成int值
     * @param number 字符串值
     * @return int 对应的int值
     */
    public static int getIntValue(String number) {
        // 判断参数是不是空的
        if (StringUtils.isNotBlank(number)) {
            try {
                // 如果参数不为空 强制转换类型
                return Integer.parseInt(number);
            } catch (Exception e) {
                return -1;
            }
        }
        // 如果参数为空 则返回0
        return 0;
    }

    /**
     * @Title: isNotBlank 
     * @Description: 判断字符串是否为空
     * @param text  字符串
     * @return boolean
     */
    public static boolean isNotBlank(String text) {
        if (StringUtils.isNotBlank(text)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 电话号码隐藏中间4位
     * @param phoneNumber
     * @return
     */
    public static String encryptPhone(String phoneNumber) {
        if (StringUtils.isBlank(phoneNumber) || phoneNumber.length() < 11) {
            return null;
        }
        phoneNumber = phoneNumber.trim();
        return phoneNumber.substring(0, 3) + "****" + phoneNumber.substring(7, phoneNumber.length());
    }
}
