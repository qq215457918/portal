package com.portal.service;

import com.portal.bean.Criteria;
import com.portal.bean.CustomerInfo;
import com.portal.bean.EmployeeInfo;
import com.portal.bean.OrderInfo;
import com.portal.bean.result.CustomerSimpleInfoForm;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import net.sf.json.JSONObject;

public interface CustomerInfoService {

    /**
     * 修改客户类型
     * @param cid
     * @param oldType
     * @param newType
     * @return
     */
    public int updateType(String cid, String newType);

    /**
     * 更新商品的列表信息
     * @param cid
     * @param product
     * @return
     */
    public int updateProduct(String cid, String product, String amount);

    /**
     * 更新赠品的列表信息
     * @param cid
     * @param product
     * @return
     */
    public int updateGift(String cid, String gift);

    /**
     * 通过id查询用户
     * @param phone
     * @return
     */
    public CustomerSimpleInfoForm getCutomerInfoById(String id);

    /**
     * 查询客户基本信息-frist
     * @param id
     * @return
     */
    public CustomerSimpleInfoForm getFristQueryInfo(String phone);

    /**
     * 通过电话号码查询用户
     * @param phone
     * @return
     */
    public CustomerInfo selectByPhone(String phone);

    /**
     * 新增客户信息
     * @param request
     * @return
     */
    public CustomerSimpleInfoForm insertCustomer(CustomerInfo record, EmployeeInfo employeeInfo);

    /**
     * 查询是否为已经注册的用户
     * @param phone
     * @return
     */
    public boolean isCustomer(String phone);

    /**
     * 修改用户基本信息
      * @param request
     * @return
     */
    public int updateCustomer(HttpServletRequest request);

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

    /**
     * @Title: ajaxCustomerStatistics 
     * @Description: 异步获取用户统计数据
     * @param request
     * @return JSONObject
     * @author Xia ZhengWei
     * @date 2016年10月29日 下午7:32:01 
     * @version V1.0
     */
    JSONObject ajaxCustomerStatistics(HttpServletRequest request);

    /**
     * @Title: updateExportDate 
     * @Description: 导出时间更新
     * @param resultList 
     * @return void
     * @throws
     */
    public void updateExportDate(List<CustomerInfo> resultList);

    /**
     * @Title: insertAndUpdateCustomerInfo 
     * @Description: 插入用户信息 如果电话重复则更新
     * @param data 
     * @return void
     * @throws
     */
    public String insertAndUpdateCustomerInfo(List<Map<String, Object>> data);

    /**
     * @Title: insertAndUpdateCustomerInfo 
     * @Description: 导入更新电联人员
     * @param data 
     * @return void
     * @throws
     */
    public void updateCustomerInfo(List<Map<String, Object>> data);

    /**
     * @Title: insertAndUpdateCustomerInfo 
     * @Description: 导出用户信息
     * @param criteria 
     * @return List<CustomerInfo>
     * @throws
     */
    public List<CustomerInfo> selectCustomerExportList(Criteria criteria);

    /**
     * @Title: customerOrderInfoList 
     * @Description: 用户订单详情
     * @param customerId
     * @return void
     * @throws
     */
    public List<OrderInfo> selectCustomerOrderList(Map<String, Object> paramMap);

    /**
     * @Title: selectCustomerOrderCount 
     * @Description: 用户订单详情数量
     * @param customerId
     * @return void
     * @throws
     */
    public int selectCustomerOrderCount(Map<String, Object> paramMap);

    /**
     * @Title: ajaxCustomerData 
     * @Description: 后台异步获取客户信息
     * @param criteria
     * @param sEcho
     * @return JSONObject
     * @author Xia ZhengWei
     * @date 2017年1月2日 下午9:50:32 
     * @version V1.0
     */
    JSONObject ajaxCustomerData(Criteria criteria, String sEcho);

    /**
     * @Title: saveCustomerInfo 
     * @Description: 后台修改客户基本信息
     * @param customerForm
     * @param results
     * @return JSONObject
     * @author Xia ZhengWei
     * @date 2017年1月2日 下午10:09:46 
     * @version V1.0
     */
    JSONObject saveCustomerInfo(CustomerSimpleInfoForm customerForm, JSONObject results);

    /**
     * @Title: getAllCustomer 
     * @Description: 获取所有客户信息(主键、电话、拨打时间)
     * @return List<CustomerInfo>
     * @author Xia ZhengWei
     * @date 2017年2月21日 下午9:14:32 
     * @version V1.0
     */
    public List<CustomerInfo> getAllCustomer();

    /**
     * @Title: insertAndUpdateCustomerInfo 
     * @Description: 插入用户信息 如果电话重复则累计
     * @param data 
     * @return void
     * @throws
     */
    public String insertAndUpdateCustomerInfoAdd(List<Map<String, Object>> data);

}
