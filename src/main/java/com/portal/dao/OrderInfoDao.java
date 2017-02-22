package com.portal.dao;

import com.portal.bean.Criteria;
import com.portal.bean.CustomerInfo;
import com.portal.bean.OrderInfo;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderInfoDao {
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
    int insert(OrderInfo record);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(OrderInfo record);

    /**
     * 根据条件查询记录集
     */
    List<OrderInfo> selectByExample(Criteria example);

    /**
     * 根据主键查询记录
     */
    OrderInfo selectByPrimaryKey(String id);

    /**
     * 根据条件更新属性不为空的记录
     */
    int updateByExampleSelective(@Param("record") OrderInfo record, @Param("example") Criteria example);

    /**
     * 根据条件更新记录
     */
    int updateByExample(@Param("record") OrderInfo record, @Param("example") Criteria example);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(OrderInfo record);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(OrderInfo record);
    
    /**
	 * @Title: selectCustomerOrderCount 
	 * @Description: 用户订单详情数量
	 * @param customerId
	 * @return void
	 * @throws
	 */
	int selectCustomerOrderCount(Map<String, Object> paramMap);

	/**
	 * @Title: customerOrderInfoList 
	 * @Description: 用户订单详情
	 * @param customerId
	 * @return void
	 * @throws
	 */
	List<OrderInfo> selectCustomerOrderList(Map<String, Object> paramMap);

    /**
     * @Title: selectOrderInfoList 
     * @Description: 查询修改订单列表
     * @param criteria
     * @return 
     * @return List<OrderInfo>
     * @throws
     */
	List<OrderInfo> selectOrderModifyList(Criteria criteria);

	/**
     * @Title: selectOrderInfoList 
     * @Description: 查询修改订单列表数量
     * @param criteria
     * @return 
     * @return List<OrderInfo>
     * @throws
     */
	int countOrderModifyList(Criteria criteria);

	/**
     * @Title: selectOrderInfoList 
     * @Description: 查询修改订单列表数量
     * @param criteria
     * @return 
     * @return List<OrderInfo>
     * @throws
     */	
	List<OrderInfo> selectFinanceEveryDay(Criteria criteria);

	/**
     * @Title: selectOrderInfoList 
     * @Description: 查询修改订单列表数量
     * @param criteria
     * @return 
     * @return int
     * @throws
     */
	int countFinanceEveryDay(Criteria criteria);

	/**
     * @Title: selectPirntInfoById 
     * @Description: 通过id获取打印订单信息
     * @param criteria
     * @return OrderInfo
     */
	OrderInfo selectPirntInfoById(String orderId);
    
}