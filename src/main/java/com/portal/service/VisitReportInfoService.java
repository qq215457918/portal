package com.portal.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.portal.bean.Criteria;
import com.portal.bean.VisitReportInfo;

import net.sf.json.JSONObject;

public interface VisitReportInfoService {
    int countByExample(Criteria example);

    VisitReportInfo selectByPrimaryKey(String id);

    List<VisitReportInfo> selectByExample(Criteria example);

    int deleteByPrimaryKey(String id);

    int updateByPrimaryKeySelective(VisitReportInfo record);

    int updateByPrimaryKey(VisitReportInfo record);

    int deleteByExample(Criteria example);

    int updateByExampleSelective(VisitReportInfo record, Criteria example);

    int updateByExample(VisitReportInfo record, Criteria example);

    int insert(VisitReportInfo record);

    int insertSelective(VisitReportInfo record);
    
    /**
     * @Title: ajaxStatistics 
     * @Description: 异步获取接待客户统计数据
     * @param request
     * @param response 
     * @return void
     * @throws
     */
    JSONObject ajaxStatistics(HttpServletRequest request, HttpServletResponse response);
    
    /**
     * @Title: ajaxReceiveAreaList 
     * @Description: 异步获取指定地区下的接待客户统计数据
     * @param request
     * @param response
     * @return JSONObject
     * @throws
     */
    JSONObject ajaxReceiveAreaList(HttpServletRequest request, HttpServletResponse response);
    
    /**
     * @Title: ajaxSalesmanStatement 
     * @Description: 异步获取业务员统计数据
     * @param request
     * @return JSONObject
     * @author Xia ZhengWei
     * @date 2016年10月27日 下午9:23:33 
     * @version V1.0
     */
    JSONObject ajaxSalesmanStatement(HttpServletRequest request);
    
}