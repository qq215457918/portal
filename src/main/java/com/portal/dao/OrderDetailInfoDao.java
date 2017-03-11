package com.portal.dao;

import com.portal.bean.Criteria;
import com.portal.bean.OrderDetailInfo;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailInfoDao {
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
    int insert(OrderDetailInfo record);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(OrderDetailInfo record);

    /**
     * 根据条件查询记录集
     */
    List<OrderDetailInfo> selectByExample(Criteria example);

    /**
     * 根据主键查询记录
     */
    OrderDetailInfo selectByPrimaryKey(String id);

    /**
     * 根据条件更新属性不为空的记录
     */
    int updateByExampleSelective(@Param("record") OrderDetailInfo record, @Param("example") Criteria example);

    /**
     * 根据条件更新记录
     */
    int updateByExample(@Param("record") OrderDetailInfo record, @Param("example") Criteria example);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(OrderDetailInfo record);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(OrderDetailInfo record);

    /**
     * @Title: selectOrderInfoById 
     * @Description: 通过id获取订单信息列表
     * @param param
     * @return void
     * @throws
     */
    List<OrderDetailInfo> selectOrderInfoById(Map<String, Object> param);
}
