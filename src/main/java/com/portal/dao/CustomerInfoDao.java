package com.portal.dao;

import com.portal.bean.Criteria;
import com.portal.bean.CustomerInfo;
import com.portal.bean.OrderInfo;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerInfoDao {
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
    int insert(CustomerInfo record);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(CustomerInfo record);

    /**
     * 根据条件查询记录集
     */
    List<CustomerInfo> selectByExample(Criteria example);

    /**
     * 根据主键查询记录
     */
    CustomerInfo selectByPrimaryKey(String id);

    /**
     * 根据条件更新属性不为空的记录
     */
    int updateByExampleSelective(@Param("record") CustomerInfo record, @Param("example") Criteria example);

    /**
     * 根据条件更新记录
     */
    int updateByExample(@Param("record") CustomerInfo record, @Param("example") Criteria example);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(CustomerInfo record);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(CustomerInfo record);

    /**
     * 查询列表（带成交金额和最近成交时间）
     */
	List<CustomerInfo> selectCustomerExList(Criteria criteria);

	/**
     * 查询列表条数（带成交金额和最近成交时间）
     */
	int countCustomerEx(Criteria criteria);

	/**
     * @Title: updateExportDate 
     * @Description: 导出时间更新
     * @param resultList 
     * @return void
     * @throws
     */
	void updateExportDate(List<CustomerInfo> resultList);

	/**
     * @Title: insertAndUpdateCustomerInfo 
     * @Description: 插入用户信息 如果电话重复则更新
     * @param resultList 
     * @return void
     * @throws
     */
	void insertAndUpdateCustomerInfo(List<Map<String, Object>> data);

	/**
     * @Title: insertAndUpdateCustomerInfo 
     * @Description: 导入更新电联人员
     * @param data 
     * @return void
     * @throws
     */
	void updateCustomerInfo(Map<String, Object> data);

	/**
     * @Title: selectCustomerExportList 
     * @Description: 导出用户信息
     * @param criteria 
     * @return List<CustomerInfo>
     * @throws
     */
	List<CustomerInfo> selectCustomerExportList(Criteria criteria);
}