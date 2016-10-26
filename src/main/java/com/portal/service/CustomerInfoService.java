package com.portal.service;

import com.portal.bean.Criteria;
import com.portal.bean.CustomerInfo;
import com.portal.bean.result.CustomerSimpleInfoForm;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import net.sf.json.JSONObject;

public interface CustomerInfoService {

    /**
     * 查询客户基本信息-frist
     * @param id
     * @return
     */
    public CustomerSimpleInfoForm getFristQueryInfo(String id);

    /**
     * 通过电话号码查询用户
     * @param phone
     * @return
     */
    public CustomerInfo selectByPhone(String phone);

    public boolean isCustomer(String phone);

    public int insertSelective(CustomerInfo record);

    int countByExample(Criteria example);

    CustomerInfo selectByPrimaryKey(String id);

    List<CustomerInfo> selectByExample(Criteria example);

    int deleteByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CustomerInfo record);

    int updateByPrimaryKey(CustomerInfo record);

    int deleteByExample(Criteria example);

    int updateByExampleSelective(CustomerInfo record, Criteria example);

    int updateByExample(CustomerInfo record, Criteria example);

    int insert(CustomerInfo record);

    public List<CustomerInfo> selectCustomerExList(Criteria criteria);

    public int countCustomerEx(Criteria criteria);

    /**
     * @Title: ajaxFiltrateCustomers 
     * @Description: 异步获取筛选客户类型数据
     * @param request
     * @return JSONObject
     * @author Xia ZhengWei
     * @date 2016年10月20日 下午11:08:39 
     * @version V1.0
     */
    JSONObject ajaxFiltrateCustomers(HttpServletRequest request);
}
