package com.portal.service;

import com.portal.bean.Criteria;
import com.portal.bean.PaymentAccountInfo;

import net.sf.json.JSONObject;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

public interface PaymentAccountInfoService {
    int countByExample(Criteria example);

    PaymentAccountInfo selectByPrimaryKey(String paymentAccountId);

    List<PaymentAccountInfo> selectByExample(Criteria example);

    int deleteByPrimaryKey(String paymentAccountId);

    int updateByPrimaryKeySelective(PaymentAccountInfo record);

    int updateByPrimaryKey(PaymentAccountInfo record);

    int deleteByExample(Criteria example);

    int updateByExampleSelective(PaymentAccountInfo record, Criteria example);

    int updateByExample(PaymentAccountInfo record, Criteria example);

    int insert(PaymentAccountInfo record);

    int insertSelective(PaymentAccountInfo record);
    
    /**
     * @Title: ajaxPaymentAccountData 
     * @Description: 异步获取收款账户名称-后台
     * @param criteria
     * @param sEcho
     * @return JSONObject
     * @author Xia ZhengWei
     * @date 2017年1月9日 下午9:52:33 
     * @version V1.0
     */
    JSONObject ajaxPaymentAccountData(Criteria criteria, String sEcho);
    
    /**
     * @Title: savePaymentAccount 
     * @Description: 保存收款账户信息
     * @param payment
     * @param results
     * @param request
     * @return JSONObject
     * @author Xia ZhengWei
     * @date 2017年1月9日 下午10:27:48 
     * @version V1.0
     */
    JSONObject savePaymentAccount(PaymentAccountInfo payment, JSONObject results, HttpServletRequest request);
    
    /**
     * @Title: updatePaymentAccount 
     * @Description: 删除收款账户信息对象
     * @param paymentAccountId
     * @param deleteReason
     * @param results
     * @param request
     * @return JSONObject
     * @author Xia ZhengWei
     * @date 2017年1月9日 下午10:31:53 
     * @version V1.0
     */
    JSONObject updatePaymentAccount(String paymentAccountId, String deleteReason, JSONObject results, HttpServletRequest request);
}