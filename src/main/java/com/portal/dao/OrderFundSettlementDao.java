package com.portal.dao;

import com.portal.bean.Criteria;
import com.portal.bean.OrderFundSettlement;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderFundSettlementDao {
    /**
     * 根据条件查询记录总数
     */
    int countByExample(Criteria example);

    /**
     * 根据条件删除记录
     */
    int deleteByExample(Criteria example);

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(String id);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(OrderFundSettlement record);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(OrderFundSettlement record);

    /**
     * 根据条件查询记录集
     */
    List<OrderFundSettlement> selectByExample(Criteria example);

    /**
     * 根据主键查询记录
     */
    OrderFundSettlement selectByPrimaryKey(String id);

    /**
     * 根据条件更新属性不为空的记录
     */
    int updateByExampleSelective(@Param("record") OrderFundSettlement record, @Param("example") Criteria example);

    /**
     * 根据条件更新记录
     */
    int updateByExample(@Param("record") OrderFundSettlement record, @Param("example") Criteria example);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(OrderFundSettlement record);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(OrderFundSettlement record);
}