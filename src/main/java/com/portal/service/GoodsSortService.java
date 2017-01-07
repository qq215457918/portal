package com.portal.service;

import java.util.List;

import com.portal.bean.Criteria;
import com.portal.bean.GoodsSort;

public interface GoodsSortService {
    int countByExample(Criteria example);

    GoodsSort selectByPrimaryKey(String id);

    List<GoodsSort> selectByExample(Criteria example);

    int deleteByPrimaryKey(String id);

    int updateByPrimaryKeySelective(GoodsSort record);

    int updateByPrimaryKey(GoodsSort record);

    int deleteByExample(Criteria example);

    int updateByExampleSelective(GoodsSort record, Criteria example);

    int updateByExample(GoodsSort record, Criteria example);

    int insert(GoodsSort record);

    int insertSelective(GoodsSort record);
    
    /**
     * @Title: getGoodsBigSort 
     * @Description: 获取物品的种类（大类-纸币/邮票/贵金属/赠品/兑换）
     * @return List<GoodsSort>
     * @author Xia ZhengWei
     * @date 2016年12月26日 下午9:46:08 
     * @version V1.0
     */
    List<GoodsSort> getGoodsBigSort();
    
}