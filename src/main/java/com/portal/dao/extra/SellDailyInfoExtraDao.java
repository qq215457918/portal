package com.portal.dao.extra;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.portal.bean.Criteria;
import com.portal.bean.SellDailyDetail;
import com.portal.bean.SellGoodsDetail;
import com.portal.bean.result.SellDailyInfoForm;

@Repository
public interface SellDailyInfoExtraDao {
    
    /**
     * @Title: getSellDailiesByCondition 
     * @Description: 根据条件获取记录
     * @param example
     * @return List<SellDailyInfoForm>
     * @author Xia ZhengWei
     * @date 2017年3月20日 下午11:38:12 
     * @version V1.0
     */
    List<SellDailyInfoForm> getSellDailiesByCondition(Criteria example);
    
    /**
     * @Title: getSellGoodsDetails 
     * @Description: 获取销售商品信息
     * @param sellDailyId
     * @return List<SellGoodsDetail>
     * @author Xia ZhengWei
     * @date 2017年3月20日 下午11:46:54 
     * @version V1.0
     */
    List<SellGoodsDetail> getSellGoodsDetails(@Param(value="sellDailyId") String sellDailyId);
    
    /**
     * @Title: getSellDailyDetails 
     * @Description: 获取销售结算明细
     * @param sellDailyId
     * @return List<SellDailyDetail>
     * @author Xia ZhengWei
     * @date 2017年3月20日 下午11:46:46 
     * @version V1.0
     */
    List<SellDailyDetail> getSellDailyDetails(@Param(value="sellDailyId") String sellDailyId);

}