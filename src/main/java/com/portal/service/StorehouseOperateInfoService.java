package com.portal.service;

import com.portal.bean.Criteria;
import com.portal.bean.StorehouseOperateInfo;

import net.sf.json.JSONObject;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

public interface StorehouseOperateInfoService {
    int countByExample(Criteria example);

    StorehouseOperateInfo selectByPrimaryKey(String id);

    List<StorehouseOperateInfo> selectByExample(Criteria example);

    int deleteByPrimaryKey(String id);

    int updateByPrimaryKeySelective(StorehouseOperateInfo record);

    int updateByPrimaryKey(StorehouseOperateInfo record);

    int deleteByExample(Criteria example);

    int updateByExampleSelective(StorehouseOperateInfo record, Criteria example);

    int updateByExample(StorehouseOperateInfo record, Criteria example);

    int insert(StorehouseOperateInfo record);

    int insertSelective(StorehouseOperateInfo record);
    
    /**
     * @Title: ajaxOutwarehouseDetail 
     * @Description: 异步获取出库明细统计数据
     * @param request
     * @return JSONObject
     * @author Xia ZhengWei
     * @date 2016年10月20日 下午10:26:21 
     * @version V1.0
     */
    JSONObject ajaxOutwarehouseDetail(HttpServletRequest request);
}