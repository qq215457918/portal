package com.portal.service;

import com.portal.bean.Criteria;
import com.portal.bean.SellDailyInfo;
import com.portal.bean.result.SellDailyInfoForm;
import com.portal.common.exception.DBException;

import net.sf.json.JSONObject;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

public interface SellDailyInfoService {
    int countByExample(Criteria example);

    SellDailyInfo selectByPrimaryKey(String id);

    List<SellDailyInfo> selectByExample(Criteria example);

    int deleteByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SellDailyInfo record);

    int updateByPrimaryKey(SellDailyInfo record);

    int deleteByExample(Criteria example);

    int updateByExampleSelective(SellDailyInfo record, Criteria example);

    int updateByExample(SellDailyInfo record, Criteria example);

    int insert(SellDailyInfo record);

    int insertSelective(SellDailyInfo record);
    
    /**
     * @Title: saveSellDaily 
     * @Description: 保存销售日报表 
     * @param sellDaily 销售日报表信息对象
     * @param results   返回结果
     * @param request
     * @return JSONObject
     * @author Xia ZhengWei
     * @date 2016年10月25日 下午3:37:10 
     * @version V1.0
     * @throws DBException 
     */
    JSONObject saveSellDaily(SellDailyInfoForm sellDaily, JSONObject results, HttpServletRequest request) throws DBException;
}