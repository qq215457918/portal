package com.portal.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portal.bean.Criteria;
import com.portal.bean.VisitReportInfo;
import com.portal.bean.result.VisitReportInfoForm;
import com.portal.common.util.DateUtil;
import com.portal.common.util.StringUtil;
import com.portal.dao.VisitReportInfoDao;
import com.portal.dao.extra.VisitReportInfoExtDao;
import com.portal.service.VisitReportInfoService;

import net.sf.json.JSONObject;

@Service
public class VisitReportInfoServiceImpl implements VisitReportInfoService {
    
    @Autowired
    private VisitReportInfoDao visitReportInfoDao;
    
    @Autowired
    private VisitReportInfoExtDao visitReportExtDao;
    
    // 公共查询条件类
    Criteria criteria = new Criteria();

    private static final Logger logger = LoggerFactory.getLogger(VisitReportInfoServiceImpl.class);

    public int countByExample(Criteria example) {
        int count = this.visitReportInfoDao.countByExample(example);
        logger.debug("count: {}", count);
        return count;
    }

    public VisitReportInfo selectByPrimaryKey(String id) {
        return this.visitReportInfoDao.selectByPrimaryKey(id);
    }

    public List<VisitReportInfo> selectByExample(Criteria example) {
        return this.visitReportInfoDao.selectByExample(example);
    }

    public int deleteByPrimaryKey(String id) {
        return this.visitReportInfoDao.deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(VisitReportInfo record) {
        return this.visitReportInfoDao.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(VisitReportInfo record) {
        return this.visitReportInfoDao.updateByPrimaryKey(record);
    }

    public int deleteByExample(Criteria example) {
        return this.visitReportInfoDao.deleteByExample(example);
    }

    public int updateByExampleSelective(VisitReportInfo record, Criteria example) {
        return this.visitReportInfoDao.updateByExampleSelective(record, example);
    }

    public int updateByExample(VisitReportInfo record, Criteria example) {
        return this.visitReportInfoDao.updateByExample(record, example);
    }

    public int insert(VisitReportInfo record) {
        return this.visitReportInfoDao.insert(record);
    }

    public int insertSelective(VisitReportInfo record) {
        return this.visitReportInfoDao.insertSelective(record);
    }

    public JSONObject ajaxStatistics(HttpServletRequest request, HttpServletResponse response) {
        // 查询两个地区的接待客户数量（默认查询上一周的数据）
        JSONObject result = new JSONObject();
        // 开始日期
        String startReportDate = request.getParameter("startReportDate");
        // 结束日期
        String endReportDate = request.getParameter("endReportDate");
        criteria.clear();
        if(StringUtil.isNotBlank(startReportDate)){
            criteria.put("startReportDate", startReportDate);
        }else {
            criteria.put("startReportDate", DateUtil.formatDate(DateUtil.getLastWeekMonday(new Date()), "yyyy-MM-dd"));
        }
        if(StringUtil.isNotBlank(endReportDate)){
            criteria.put("endReportDate", DateUtil.formatDate(DateUtil.parseDate(endReportDate, "yyyy-MM-dd"), "yyyy-MM-dd 23:59:59"));
        }else {
            criteria.put("endReportDate", DateUtil.formatDate(DateUtil.getLastWeekSunday(new Date()), "yyyy-MM-dd 23:59:59"));
        }
        // 获取用户数量
        Map<String, Integer> counts = visitReportExtDao.getCustomerCounts(criteria);
        
        //查询出大连各种客户类型的客户数量
        criteria.put("receiverArea", 1);
        Map<String, Object> dlCustomer = visitReportExtDao.getAllCategoryCustomer(criteria);
        
        // 获取沈阳对应下的各种分类的客户数量
        criteria.put("receiverArea", 0);
        Map<String, Object> syCustomer = visitReportExtDao.getAllCategoryCustomer(criteria);
        
        if(counts != null) {
            result.put("customerCounts", counts.get("total_counts") != null ? counts.get("total_counts") : new BigDecimal(0));
            result.put("dlCounts", counts.get("dl_counts") != null ? counts.get("dl_counts") : new BigDecimal(0));
            result.put("syCounts", counts.get("sy_counts") != null ? counts.get("sy_counts") : new BigDecimal(0));
        }else {
            result.put("customerCounts", 0);
            result.put("dlCounts", 0);
            result.put("syCounts", 0);
        }
        if(dlCustomer != null) {
            // 转换成JSON格式
            JSONObject dlResult = JSONObject.fromObject(dlCustomer);
            result.put("dlResult", dlResult);
        }else {
            result.put("dlResult", null);
        }
        if(syCustomer != null) {
            // 转换成JSON格式
            JSONObject syResult = JSONObject.fromObject(syCustomer);
            result.put("syResult", syResult);
        }else {
            result.put("syResult", null);
        }
        
        return result;
    }

    public JSONObject ajaxReceiveAreaList(HttpServletRequest request, HttpServletResponse response) {
        // 请求开始页
        int currentPage = StringUtil.getIntValue(request.getParameter("iDisplayStart"));
        // 每页显示几条
        int perpage = StringUtil.getIntValue(request.getParameter("iDisplayLength"));
        // 接待人姓名
        String receiverStaffName = request.getParameter("receiverStaffName");
        // 接待人所属区域
        String receiverArea = request.getParameter("receiverArea");
        // 开始日期
        String startReportDate = request.getParameter("startReportDate");
        // 结束日期
        String endReportDate = request.getParameter("endReportDate");
        
        criteria.clear();
        // 分页参数
        criteria.setMysqlOffset(currentPage);
        criteria.setMysqlLength(perpage);
        criteria.setOrderByClause("receiver_staff_name asc");
        
        // 查询未被删除的
        if(StringUtil.isNotBlank(receiverStaffName)){
            criteria.put("viewStaffName", StringUtil.trim(receiverStaffName));
        }
        if(StringUtil.isNotBlank(receiverArea)){
            criteria.put("receiverArea", StringUtil.trim(receiverArea));
        }
        if(StringUtil.isNotBlank(startReportDate)){
            criteria.put("startReportDate", startReportDate);
        }
        if(StringUtil.isNotBlank(endReportDate)){
            criteria.put("endReportDate", DateUtil.formatDate(DateUtil.parseDate(endReportDate, "yyyy-MM-dd"), "yyyy-MM-dd 23:59:59"));
        }
        // 获取总记录数
        int totalRecord = visitReportExtDao.countByCondition(criteria);
        // 获取数据集
        List<VisitReportInfoForm> list = visitReportExtDao.selectByCondition(criteria);
        
        JSONObject resultJson =  new JSONObject();
        resultJson.put("sEcho", request.getParameter("sEcho"));
        resultJson.put("iTotalRecords", totalRecord);
        resultJson.put("iTotalDisplayRecords", totalRecord);
        resultJson.put("aaData", list);
        return resultJson;
    }
    
    public List<VisitReportInfoForm> getPerforByCondition(Criteria criteria) {
        return visitReportExtDao.getPerforByCondition(criteria);
    }

    public JSONObject ajaxSalesmanStatement(HttpServletRequest request) {
        // 请求开始页
        int currentPage = StringUtil.getIntValue(request.getParameter("iDisplayStart"));
        // 每页显示几条
        int perpage = StringUtil.getIntValue(request.getParameter("iDisplayLength"));
        // 接待人姓名
        String receiveStaffName = request.getParameter("receiveStaffName");
        // 接待人所属区域
        String area = request.getParameter("area");
        // 开始日期
        String startDate = request.getParameter("startDate");
        // 结束日期
        String endDate = request.getParameter("endDate");
        
        criteria.clear();
        // 分页参数
        criteria.setMysqlOffset(currentPage);
        criteria.setMysqlLength(perpage);
        criteria.setOrderByClause("e.`name` asc");
        
        // 查询职位类型为业务员
        criteria.put("positionType", "2");
        // 查询未被删除的
        if(StringUtil.isNotBlank(receiveStaffName)){
            criteria.put("receiveStaffName", StringUtil.trim(receiveStaffName));
        }
        if(StringUtil.isNotBlank(area)){
            criteria.put("area", StringUtil.trim(area));
        }
        if(StringUtil.isNotBlank(startDate)){
            criteria.put("startDate", startDate);
        }
        if(StringUtil.isNotBlank(endDate)){
            criteria.put("endDate", DateUtil.formatDate(DateUtil.parseDate(endDate, "yyyy-MM-dd"), "yyyy-MM-dd 23:59:59"));
        }
        // 获取总记录数
        int totalRecord = visitReportExtDao.getPerforCounts(criteria);
        // 获取数据集
        List<VisitReportInfoForm> list = visitReportExtDao.getPerforByCondition(criteria);
        
        JSONObject resultJson =  new JSONObject();
        resultJson.put("sEcho", request.getParameter("sEcho"));
        resultJson.put("iTotalRecords", totalRecord);
        resultJson.put("iTotalDisplayRecords", totalRecord);
        resultJson.put("aaData", list);
        return resultJson;
    }

}