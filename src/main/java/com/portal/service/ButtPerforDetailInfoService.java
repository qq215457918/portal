package com.portal.service;

import com.portal.bean.ButtPerforDetailInfo;
import com.portal.bean.Criteria;

import net.sf.json.JSONObject;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

public interface ButtPerforDetailInfoService {
    int countByExample(Criteria example);

    ButtPerforDetailInfo selectByPrimaryKey(String id);

    List<ButtPerforDetailInfo> selectByExample(Criteria example);

    int deleteByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ButtPerforDetailInfo record);

    int updateByPrimaryKey(ButtPerforDetailInfo record);

    int deleteByExample(Criteria example);

    int updateByExampleSelective(ButtPerforDetailInfo record, Criteria example);

    int updateByExample(ButtPerforDetailInfo record, Criteria example);

    int insert(ButtPerforDetailInfo record);

    int insertSelective(ButtPerforDetailInfo record);
    
    /**
     * @Title: seleteByCondition 
     * @Description: 根据条件获取数据
     * @param criteria
     * @return List<ButtPerforDetailInfo>
     * @author Xia ZhengWei
     * @date 2017年1月4日 下午11:39:02 
     * @version V1.0
     */
    List<ButtPerforDetailInfo> seleteByCondition(Criteria criteria);
    
    /**
     * @Title: ajaxButtPerforDetail 
     * @Description: 异步获取展厅客服对接业绩详细数据
     * @param request
     * @return JSONObject
     * @author Xia ZhengWei
     * @date 2016年10月24日 下午10:19:58 
     * @version V1.0
     */
    JSONObject ajaxButtPerforDetail(HttpServletRequest request);
}