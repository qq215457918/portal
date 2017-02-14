package com.portal.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portal.bean.Criteria;
import com.portal.bean.VisitEverydayInfo;
import com.portal.bean.result.VisitEverydayInfoForm;
import com.portal.common.util.DateUtil;
import com.portal.common.util.StringUtil;
import com.portal.dao.VisitEverydayInfoDao;
import com.portal.dao.extra.VisitEverydayInfoExtDao;
import com.portal.service.VisitEverydayInfoService;

import net.sf.json.JSONObject;

@Service
public class VisitEverydayInfoServiceImpl implements VisitEverydayInfoService {
    
    @Autowired
    private VisitEverydayInfoDao visitEverydayInfoDao;
    
    @Autowired
    private VisitEverydayInfoExtDao visitEverydayInfoExtDao;

    private static final Logger logger = LoggerFactory.getLogger(VisitEverydayInfoServiceImpl.class);
    
    // 公共查询条件类
    Criteria criteria = new Criteria();

    public int countByExample(Criteria example) {
        int count = this.visitEverydayInfoDao.countByExample(example);
        logger.debug("count: {}", count);
        return count;
    }

    public VisitEverydayInfo selectByPrimaryKey(String id) {
        return this.visitEverydayInfoDao.selectByPrimaryKey(id);
    }

    public List<VisitEverydayInfo> selectByExample(Criteria example) {
        return this.visitEverydayInfoDao.selectByExample(example);
    }

    public int deleteByPrimaryKey(String id) {
        return this.visitEverydayInfoDao.deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(VisitEverydayInfo record) {
        return this.visitEverydayInfoDao.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(VisitEverydayInfo record) {
        return this.visitEverydayInfoDao.updateByPrimaryKey(record);
    }

    public int deleteByExample(Criteria example) {
        return this.visitEverydayInfoDao.deleteByExample(example);
    }

    public int updateByExampleSelective(VisitEverydayInfo record, Criteria example) {
        return this.visitEverydayInfoDao.updateByExampleSelective(record, example);
    }

    public int updateByExample(VisitEverydayInfo record, Criteria example) {
        return this.visitEverydayInfoDao.updateByExample(record, example);
    }

    public int insert(VisitEverydayInfo record) {
        return this.visitEverydayInfoDao.insert(record);
    }

    public int insertSelective(VisitEverydayInfo record) {
        return this.visitEverydayInfoDao.insertSelective(record);
    }
    
    public JSONObject ajaxVisitData(HttpServletRequest request, HttpServletResponse response) {
        // 查询两个地区的每日登门情况（默认查询上一周）
        JSONObject result = new JSONObject();
        
        // 开始日期
        String startVisitDate = request.getParameter("startVisitDate");
        String endVisitDate = request.getParameter("endVisitDate");
        
        criteria.clear();
        if(StringUtil.isNotBlank(startVisitDate)){
            criteria.put("startVisitDate2", startVisitDate);
        }else {
            // 如果开始时间为空, 则取上周一为开始时间
            startVisitDate = DateUtil.formatDate(DateUtil.getLastWeekMonday(new Date()), "yyyy-MM-dd");
            criteria.put("startVisitDate2", startVisitDate);
        }
        if(StringUtil.isNotBlank(endVisitDate)){
            criteria.put("endVisitDate2", endVisitDate);
        }else {
            // 如果结束时间为空,判断开始日期是否为空
            // 如果开始日期为空, 则默认结束日期为上周日
            // 如果开始日期不为空, 则结束日期为开始日期后6天的日期
            if(StringUtil.isNotBlank(startVisitDate)) {
                Date date=DateUtil.parseDate(startVisitDate, "yyyy-MM-dd");  
                Calendar cal=Calendar.getInstance();  
                cal.setTime(date);
                endVisitDate = DateUtil.formatDate(DateUtil.getLaterSixDate(cal, 6), "yyyy-MM-dd");
                criteria.put("endVisitDate2", endVisitDate);
            }else {
                endVisitDate = DateUtil.formatDate(DateUtil.getLastWeekSunday(new Date()), "yyyy-MM-dd");
                criteria.put("endVisitDate2", endVisitDate);
            }
        }
        
        // 将查询日期转换成Calendar
        Calendar startDate=Calendar.getInstance();
        Calendar endDate=Calendar.getInstance();
        startDate.setTime(DateUtil.parseDate(startVisitDate, "yyyy-MM-dd"));
        endDate.setTime(DateUtil.parseDate(endVisitDate, "yyyy-MM-dd"));
        
        // 获取两个日期之间的所有日期
        List<String> dates = DateUtil.getDates(startDate, endDate);
        
        // 获取两个日期内各地区的登门数量
        Map<String, Integer> counts = visitEverydayInfoExtDao.getVisitCounts(criteria);
        
        //查询出大连登门日期及对应数量
        criteria.put("customerArea", "1");
        List<VisitEverydayInfoForm> dlCustomer = visitEverydayInfoExtDao.getDayAndVisitCounts(criteria);
        
        //生成大连登门数量
        List<Integer> dlCounts = generateCountsList(dates, dlCustomer);
        
        // 查询出沈阳登门日期及对应数量
        criteria.put("customerArea", "0");
        List<VisitEverydayInfoForm> syCustomer = visitEverydayInfoExtDao.getDayAndVisitCounts(criteria);
        
        //生成沈阳登门数量
        List<Integer> syCounts = generateCountsList(dates, syCustomer);
        
        if(counts != null) {
            result.put("customerCounts", counts.get("total_counts") != null ? counts.get("total_counts") : new BigDecimal(0));
            result.put("dlCounts", counts.get("dl_counts") != null ? counts.get("dl_counts") : new BigDecimal(0));
            result.put("syCounts", counts.get("sy_counts") != null ? counts.get("sy_counts") : new BigDecimal(0));
        }else {
            result.put("customerCounts", 0);
            result.put("dlCounts", 0);
            result.put("syCounts", 0);
        }
        
        result.put("dates", dates);
        result.put("dlResult", dlCounts);
        result.put("syResult", syCounts);
        
        return result;
    }
    
    /**
     * @Title: generateCountsList 
     * @Description: 生成报表需要的线形图数据
     * @param dates  查询日期条件所包含的所有日期
     * @param visitCustomer 登门用户
     * @return List<Integer>
     * @author Xia ZhengWei
     * @date 2016年11月23日 下午11:53:29 
     * @version V1.0
     */
    private List<Integer> generateCountsList(List<String> dates, List<VisitEverydayInfoForm> visitCustomer) {
        Map<String, Integer> counts = new HashMap<String, Integer>();
        if(visitCustomer.size() > 0) {
            for (VisitEverydayInfoForm visitEverydayInfoForm : visitCustomer) {
                counts.put(visitEverydayInfoForm.getViewVisitDate(), visitEverydayInfoForm.getVisitCounts());
            }
        }
        List<Integer> dateCounts = new ArrayList<Integer>();
        for(int idx = 0; idx < dates.size(); idx ++) {
            if(counts.get(dates.get(idx)) != null) {
                dateCounts.add(counts.get(dates.get(idx)));
            }else {
                dateCounts.add(0);
            }
        }
        return dateCounts;
    }

    public JSONObject ajaxVisitEveryDayList(HttpServletRequest request, HttpServletResponse response) {
        // 请求开始页
        int currentPage = StringUtil.getIntValue(request.getParameter("iDisplayStart"));
        // 每页显示几条
        int perpage = StringUtil.getIntValue(request.getParameter("iDisplayLength"));
        
        // 客户姓名
        String customerName = request.getParameter("customerName");
        // 客户类型
        String customerType = request.getParameter("customerType");
        // 活动类型
        String exercise = request.getParameter("exercise");
        // 接待人姓名
        String receiverStaffName = request.getParameter("receiverStaffName");
        // 客服姓名
        String customServiceName = request.getParameter("customServiceName");
        // 接待人所属区域
        String customerArea = request.getParameter("customerArea");
        // 开始日期
        String visitDate = request.getParameter("visitDate");
        
        criteria.clear();
        // 分页参数
        criteria.setMysqlOffset(currentPage);
        criteria.setMysqlLength(perpage);
        criteria.setOrderByClause("customer_name asc");
        
        // 查询未被删除的
        if(StringUtil.isNotBlank(customerName)){
            criteria.put("viewCustomerName", StringUtil.trim(customerName));
        }
        if(StringUtil.isNotBlank(customerType)){
            criteria.put("customerType", StringUtil.trim(customerType));
        }
        if(StringUtil.isNotBlank(exercise)){
            criteria.put("exercise", StringUtil.trim(exercise));
        }
        if(StringUtil.isNotBlank(receiverStaffName)){
            criteria.put("viewReceiverName", StringUtil.trim(receiverStaffName));
        }
        if(StringUtil.isNotBlank(customServiceName)){
            criteria.put("viewServiceName", StringUtil.trim(customServiceName));
        }
        if(StringUtil.isNotBlank(customerArea)){
            criteria.put("customerArea", StringUtil.trim(customerArea));
        }
        if(StringUtil.isNotBlank(visitDate)){
            criteria.put("startVisitDate", visitDate);
            criteria.put("endVisitDate", DateUtil.formatDate(DateUtil.parseDate(visitDate, "yyyy-MM-dd"), "yyyy-MM-dd 23:59:59"));
        }
        
        // 获取总记录数
        int totalRecord = visitEverydayInfoExtDao.countByCondition(criteria);
        // 获取数据集
        List<VisitEverydayInfoForm> list = visitEverydayInfoExtDao.selectByCondition(criteria);
        
        JSONObject resultJson =  new JSONObject();
        resultJson.put("sEcho", request.getParameter("sEcho"));
        resultJson.put("iTotalRecords", totalRecord);
        resultJson.put("iTotalDisplayRecords", totalRecord);
        resultJson.put("aaData", list);
        return resultJson;
    }

    public List<VisitEverydayInfoForm> selectByCondition(Criteria criteria) {
        return visitEverydayInfoExtDao.selectByCondition(criteria);
    }

    public List<VisitEverydayInfoForm> getTaskDataByCondition(Criteria criteria) {
        return visitEverydayInfoExtDao.getTaskDataByCondition(criteria);
    }
    
}