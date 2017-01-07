package com.portal.dao.extra;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.portal.bean.Criteria;
import com.portal.bean.result.GoodsInfoForm;

@Repository
public interface GoodsDao {

    /**
     * 根据主键查询记录
     */
    GoodsInfoForm selectByPrimaryKey(String id);

    List<GoodsInfoForm> selectByExample(Criteria criteria);
    
    /**
     * @Title: countByConditions 
     * @Description: 根据条件获取总记录数
     * @param criteria
     * @return int
     * @author Xia ZhengWei
     * @date 2016年12月26日 下午10:23:45 
     * @version V1.0
     */
    int countByConditions(Criteria criteria);
    
    /**
     * @Title: selectByConditions 
     * @Description: 根据条件获取数据
     * @param criteria
     * @return List<GoodsInfo>
     * @author Xia ZhengWei
     * @date 2016年12月26日 下午10:24:26 
     * @version V1.0
     */
    List<GoodsInfoForm> selectByConditions(Criteria criteria);

}
