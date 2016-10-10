package com.portal.service;

import com.portal.bean.Criteria;
import com.portal.bean.VisitEverydayInfo;

import net.sf.json.JSONObject;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface VisitEverydayInfoService {
    int countByExample(Criteria example);

    VisitEverydayInfo selectByPrimaryKey(String id);

    List<VisitEverydayInfo> selectByExample(Criteria example);

    int deleteByPrimaryKey(String id);

    int updateByPrimaryKeySelective(VisitEverydayInfo record);

    int updateByPrimaryKey(VisitEverydayInfo record);

    int deleteByExample(Criteria example);

    int updateByExampleSelective(VisitEverydayInfo record, Criteria example);

    int updateByExample(VisitEverydayInfo record, Criteria example);

    int insert(VisitEverydayInfo record);

    int insertSelective(VisitEverydayInfo record);
    
    /**
     * @Title: ajaxVisitData 
     * @Description: 异步获取每日登门统计数据
     * @param request
     * @param response 
     * @return void
     * @throws
     */
    JSONObject ajaxVisitData(HttpServletRequest request, HttpServletResponse response);
    
    /**
     * @Title: ajaxVisitEveryDayList 
     * @Description: 异步获取指定日期的登门情况
     * @param request
     * @param response
     * @return 
     * @return JSONObject
     * @throws
     */
    JSONObject ajaxVisitEveryDayList(HttpServletRequest request, HttpServletResponse response);
}