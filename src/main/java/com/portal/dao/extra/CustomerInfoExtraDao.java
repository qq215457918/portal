package com.portal.dao.extra;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.portal.bean.Criteria;
import com.portal.bean.CustomerInfo;
import com.portal.bean.result.CustomerSimpleInfoForm;

@Repository
public interface CustomerInfoExtraDao {

    /**
     * 根据电话号码查询用户信息
     * by meng.yue
     */
    CustomerInfo selectByPhone(String phone);
    
    /**
     * @Title: getFiltrateCustomers 
     * @Description: 根据条件获取筛选客户数据
     * @param criteria
     * @return List<CustomerSimpleInfoForm>
     * @author Xia ZhengWei
     * @date 2016年10月20日 下午11:11:18 
     * @version V1.0
     */
    List<CustomerSimpleInfoForm> getFiltrateCustomers(Criteria criteria);
    
    /**
     * @Title: getCustomerCounts 
     * @Description: 根据起始日期条件查询接待客户数量
     * @param example
     * @return Map<String,Integer>
     * @author Xia ZhengWei
     * @date 2016年10月29日 下午8:21:49 
     * @version V1.0
     */
    Map<String, Integer> getCustomerCounts(Criteria example);
    
    /**
     * @Title: getAllCategoryCustomer 
     * @Description: 获取所有各种分类对应的客户数量
     * @param example
     * @return Map<String,Object>
     * @author Xia ZhengWei
     * @date 2016年10月29日 下午8:21:59 
     * @version V1.0
     */
    Map<String, Object> getAllCategoryCustomer(Criteria example);
    
    /**
     * @Title: countByConditions 
     * @Description: 后台管理根据条件查询客户总数量
     * @param example
     * @return int
     * @author Xia ZhengWei
     * @date 2017年1月3日 下午3:01:43 
     * @version V1.0
     */
    int countByConditions(Criteria example);
    
    /**
     * @Title: selectByConditions 
     * @Description: 后台管理根据条件查询客户数据
     * @param example
     * @return List<CustomerSimpleInfoForm>
     * @author Xia ZhengWei
     * @date 2017年1月3日 下午3:02:39 
     * @version V1.0
     */
    List<CustomerSimpleInfoForm> selectByConditions(Criteria example);

}