package com.portal.service.impl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portal.bean.Criteria;
import com.portal.bean.DeptPerformanceInfo;
import com.portal.bean.result.DeptPerforInfoForm;
import com.portal.common.util.DateUtil;
import com.portal.common.util.StringUtil;
import com.portal.dao.DeptPerformanceInfoDao;
import com.portal.dao.extra.DeptPerformanceInfoExtraDao;
import com.portal.service.DeptPerformanceInfoService;

import net.sf.json.JSONObject;

@Service
public class DeptPerformanceInfoServiceImpl implements DeptPerformanceInfoService {
    
    private static final Logger logger = LoggerFactory.getLogger(DeptPerformanceInfoServiceImpl.class);

    @Autowired
    private DeptPerformanceInfoDao deptPerformanceInfoDao;
    
    @Autowired
    private DeptPerformanceInfoExtraDao deptPerformanceInfoExtraDao;

    // 公共查询条件类
    Criteria criteria = new Criteria();

    public int countByExample(Criteria example) {
        int count = this.deptPerformanceInfoDao.countByExample(example);
        logger.debug("count: {}", count);
        return count;
    }

    public DeptPerformanceInfo selectByPrimaryKey(String id) {
        return this.deptPerformanceInfoDao.selectByPrimaryKey(id);
    }

    public List<DeptPerformanceInfo> selectByExample(Criteria example) {
        return this.deptPerformanceInfoDao.selectByExample(example);
    }

    public int deleteByPrimaryKey(String id) {
        return this.deptPerformanceInfoDao.deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(DeptPerformanceInfo record) {
        return this.deptPerformanceInfoDao.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(DeptPerformanceInfo record) {
        return this.deptPerformanceInfoDao.updateByPrimaryKey(record);
    }

    public int deleteByExample(Criteria example) {
        return this.deptPerformanceInfoDao.deleteByExample(example);
    }

    public int updateByExampleSelective(DeptPerformanceInfo record, Criteria example) {
        return this.deptPerformanceInfoDao.updateByExampleSelective(record, example);
    }

    public int updateByExample(DeptPerformanceInfo record, Criteria example) {
        return this.deptPerformanceInfoDao.updateByExample(record, example);
    }

    public int insert(DeptPerformanceInfo record) {
        return this.deptPerformanceInfoDao.insert(record);
    }

    public int insertSelective(DeptPerformanceInfo record) {
        return this.deptPerformanceInfoDao.insertSelective(record);
    }
    
    public JSONObject ajaxOrganiPerformance(HttpServletRequest request) {
        // 查询机构业绩（默认查询上一周的数据）
        // 请求开始页
        int currentPage = StringUtil.getIntValue(request.getParameter("iDisplayStart"));
        // 每页显示几条
        int perpage = StringUtil.getIntValue(request.getParameter("iDisplayLength"));
        // 开始日期
        String startCreateDate = request.getParameter("startCreateDate");
        // 结束日期
        String endCreateDate = request.getParameter("endCreateDate");
        
        criteria.clear();
        // 分页参数
        criteria.setMysqlOffset(currentPage);
        criteria.setMysqlLength(perpage);
        // 已支付
        criteria.put("financeFlag", "1");
        if(StringUtil.isNotBlank(startCreateDate)){
            criteria.put("startCreateDate", startCreateDate);
        }else {
            criteria.put("startCreateDate", DateUtil.formatDate(DateUtil.getLastWeekMonday(new Date()), "yyyy-MM-dd"));
        }
        if(StringUtil.isNotBlank(endCreateDate)){
            criteria.put("endCreateDate", DateUtil.formatDate(DateUtil.parseDate(endCreateDate, "yyyy-MM-dd"), "yyyy-MM-dd 23:59:59"));
        }else {
            criteria.put("endCreateDate", DateUtil.formatDate(DateUtil.getLastWeekSunday(new Date()), "yyyy-MM-dd 23:59:59"));
        }
        // 获取总记录数
        int totalRecord = deptPerformanceInfoExtraDao.getCountsByCondition(criteria);
        // 获取数据集
        List<DeptPerforInfoForm> performanceList = deptPerformanceInfoExtraDao.getOrganiPerformance(criteria);
        
        JSONObject resultJson =  new JSONObject();
        resultJson.put("sEcho", request.getParameter("sEcho"));
        resultJson.put("iTotalRecords", totalRecord);
        resultJson.put("iTotalDisplayRecords", totalRecord);
        resultJson.put("aaData", performanceList);
        return resultJson;
    }

    public JSONObject ajaxPerformanceData(HttpServletRequest request, HttpServletResponse response) {
        // 查询部门业绩（默认查询上一周的数据）
        // 请求开始页
        int currentPage = StringUtil.getIntValue(request.getParameter("iDisplayStart"));
        // 每页显示几条
        int perpage = StringUtil.getIntValue(request.getParameter("iDisplayLength"));
        // 开始日期
        String startReportDate = request.getParameter("startReportDate");
        // 结束日期
        String endReportDate = request.getParameter("endReportDate");
        // 人员名称
        String employeeName = request.getParameter("employeeName");
        // 机构ID
        String organiId = request.getParameter("organiId");
        criteria.clear();
        criteria.setMysqlOffset(currentPage);
        criteria.setMysqlLength(perpage);
        
        if(StringUtil.isNotBlank(startReportDate)){
            criteria.put("startReportDate", startReportDate);
        }else {
            criteria.put("startReportDate", DateUtil.formatDate(DateUtil.getLastWeekMonday(new Date()), "yyyy-MM-dd"));
        }
        if(StringUtil.isNotBlank(endReportDate)){
            criteria.put("endReportDate",  DateUtil.formatDate(DateUtil.parseDate(endReportDate, "yyyy-MM-dd"), "yyyy-MM-dd 23:59:59"));
        }else {
            criteria.put("endReportDate", DateUtil.formatDate(DateUtil.getLastWeekSunday(new Date()), "yyyy-MM-dd 23:59:59"));
        }
        if(StringUtil.isNotBlank(employeeName)){
            criteria.put("employeeName", employeeName);
        }
        if(StringUtil.isNotBlank(organiId)){
            criteria.put("organiId", organiId);
        }
        
        // 获取总记录数
        int totalRecord = deptPerformanceInfoExtraDao.countByCondition(criteria);
        // 获取数据集
        List<DeptPerforInfoForm> performanceList = deptPerformanceInfoExtraDao.seleteByCondition(criteria);
        
        JSONObject resultJson =  new JSONObject();
        resultJson.put("sEcho", request.getParameter("sEcho"));
        resultJson.put("iTotalRecords", totalRecord);
        resultJson.put("iTotalDisplayRecords", totalRecord);
        resultJson.put("aaData", performanceList);
        return resultJson;
    }

    public JSONObject ajaxIndividualRanking(HttpServletRequest request, HttpServletResponse response) {
        // 查询个人业绩排名数据（默认查询上一周的数据）
        // 请求开始页
        int currentPage = StringUtil.getIntValue(request.getParameter("iDisplayStart"));
        // 每页显示几条
        int perpage = StringUtil.getIntValue(request.getParameter("iDisplayLength"));
        // 开始日期
        String startReportDate = request.getParameter("startReportDate");
        // 结束日期
        String endReportDate = request.getParameter("endReportDate");
        // 人员名称
        String employeeName = request.getParameter("employeeName");
        // 机构ID
        String organiId = request.getParameter("organiId");
        
        criteria.clear();
        criteria.setMysqlOffset(currentPage);
        criteria.setMysqlLength(perpage);
        
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
        if(StringUtil.isNotBlank(employeeName)){
            criteria.put("employeeName", employeeName);
        }
        if(StringUtil.isNotBlank(organiId)){
            criteria.put("organiId", organiId);
        }
        
        // 获取总记录数
        int totalRecord = deptPerformanceInfoExtraDao.countByCondition(criteria);
        // 获取数据集
        List<DeptPerforInfoForm> performanceList = deptPerformanceInfoExtraDao.getIndividualByCondition(criteria);
        
        JSONObject resultJson =  new JSONObject();
        resultJson.put("sEcho", request.getParameter("sEcho"));
        resultJson.put("iTotalRecords", totalRecord);
        resultJson.put("iTotalDisplayRecords", totalRecord);
        resultJson.put("aaData", performanceList);
        return resultJson;
    }
}