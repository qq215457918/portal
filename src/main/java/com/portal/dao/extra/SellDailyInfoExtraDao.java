package com.portal.dao.extra;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.portal.bean.Criteria;
import com.portal.bean.result.SellDailyInfoForm;

@Repository
public interface SellDailyInfoExtraDao {
    
    /**
     * 根据条件获取记录
     */
    List<SellDailyInfoForm> getSellDailiesByCondition(Criteria example);

}