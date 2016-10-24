package com.portal.dao.extra;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.portal.bean.ButtPerforDetailInfo;
import com.portal.bean.Criteria;

@Repository
public interface ButtPerforDetailInfoExtraDao {
    
    /**
     * 根据条件查询记录总数
     */
    int countByCondition(Criteria example);

    /**
     * 根据条件查询记录集
     */
    List<ButtPerforDetailInfo> seleteByCondition(Criteria example);

}