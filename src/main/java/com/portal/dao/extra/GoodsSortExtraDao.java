package com.portal.dao.extra;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.portal.bean.GoodsSort;

@Repository
public interface GoodsSortExtraDao {
    
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