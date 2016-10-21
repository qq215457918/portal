package com.portal.dao.extra;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.portal.bean.Criteria;
import com.portal.bean.result.StorehouseOperateInfoForm;

@Repository
public interface StorehouseOperateInfoExtraDao {
    
    /**
     * @Title: getCountsByCondition 
     * @Description: 根据条件查询出库明细记录总数
     * @param example
     * @return int
     * @author Xia ZhengWei
     * @date 2016年10月21日 上午12:50:35 
     * @version V1.0
     */
    int getCountsByCondition(Criteria example);

    /**
     * @Title: getOrganiPerformance 
     * @Description: 根据条件查询出库明细记录集
     * @param example
     * @return List<StorehouseOperateInfoForm>
     * @author Xia ZhengWei
     * @date 2016年10月21日 上午12:50:44 
     * @version V1.0
     */
    List<StorehouseOperateInfoForm> getOrganiPerformance(Criteria example);

}