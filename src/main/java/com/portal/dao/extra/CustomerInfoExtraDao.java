package com.portal.dao.extra;

import java.util.List;

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

}