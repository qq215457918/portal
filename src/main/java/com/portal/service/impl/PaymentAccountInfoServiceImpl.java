package com.portal.service.impl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portal.bean.Criteria;
import com.portal.bean.EmployeeInfo;
import com.portal.bean.PaymentAccountInfo;
import com.portal.bean.result.PaymentAccountInfoFom;
import com.portal.common.util.BeanUtils;
import com.portal.common.util.JsonUtils;
import com.portal.common.util.StringUtil;
import com.portal.common.util.UUidUtil;
import com.portal.dao.PaymentAccountInfoDao;
import com.portal.dao.extra.PaymentAccountInfoExtraDao;
import com.portal.service.PaymentAccountInfoService;

import net.sf.json.JSONObject;

@Service
public class PaymentAccountInfoServiceImpl implements PaymentAccountInfoService {
    
    @Autowired
    private PaymentAccountInfoDao paymentAccountInfoDao;
    
    @Autowired
    private PaymentAccountInfoExtraDao paymentExtraDao;

    private static final Logger logger = LoggerFactory.getLogger(PaymentAccountInfoServiceImpl.class);

    public int countByExample(Criteria example) {
        int count = this.paymentAccountInfoDao.countByExample(example);
        logger.debug("count: {}", count);
        return count;
    }

    public PaymentAccountInfo selectByPrimaryKey(String paymentAccountId) {
        return this.paymentAccountInfoDao.selectByPrimaryKey(paymentAccountId);
    }

    public List<PaymentAccountInfo> selectByExample(Criteria example) {
        return this.paymentAccountInfoDao.selectByExample(example);
    }

    public int deleteByPrimaryKey(String paymentAccountId) {
        return this.paymentAccountInfoDao.deleteByPrimaryKey(paymentAccountId);
    }

    public int updateByPrimaryKeySelective(PaymentAccountInfo record) {
        return this.paymentAccountInfoDao.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(PaymentAccountInfo record) {
        return this.paymentAccountInfoDao.updateByPrimaryKey(record);
    }

    public int deleteByExample(Criteria example) {
        return this.paymentAccountInfoDao.deleteByExample(example);
    }

    public int updateByExampleSelective(PaymentAccountInfo record, Criteria example) {
        return this.paymentAccountInfoDao.updateByExampleSelective(record, example);
    }

    public int updateByExample(PaymentAccountInfo record, Criteria example) {
        return this.paymentAccountInfoDao.updateByExample(record, example);
    }

    public int insert(PaymentAccountInfo record) {
        return this.paymentAccountInfoDao.insert(record);
    }

    public int insertSelective(PaymentAccountInfo record) {
        return this.paymentAccountInfoDao.insertSelective(record);
    }

    public JSONObject ajaxPaymentAccountData(Criteria criteria, String sEcho) {
        // 获取总记录数
        int totalRecord = paymentExtraDao.countByConditions(criteria);
        // 获取数据集
        List<PaymentAccountInfoFom> list = paymentExtraDao.selectByConditions(criteria);
        
        JSONObject resultJson =  new JSONObject();
        resultJson.put("sEcho", sEcho);
        resultJson.put("iTotalRecords", totalRecord);
        resultJson.put("iTotalDisplayRecords", totalRecord);
        resultJson.put("aaData", list);
        return resultJson;
    }
    
    public JSONObject savePaymentAccount(PaymentAccountInfo payment, JSONObject results, HttpServletRequest request) {
        int count = 0;
        if(StringUtil.isNull(payment.getPaymentAccountName().trim())) {
            results = JsonUtils.setError();
            results.put("text", "操作失败, 收款账户不能为空");
            return results;
        }else {
            // 过滤特殊字符
            payment.setPaymentAccountName(StringUtil.tstr(payment.getPaymentAccountName().trim()));
        }
        if(StringUtil.isNull(payment.getBankName().trim())) {
            results = JsonUtils.setError();
            results.put("text", "操作失败, 银行名称不能为空");
            return results;
        }else {
            // 过滤特殊字符
            payment.setBankName(StringUtil.tstr(payment.getBankName().trim()));
        }
        if(StringUtil.isNull(payment.getAccountNumber().trim())) {
            results = JsonUtils.setError();
            results.put("text", "操作失败, 收款帐号不能为空");
            return results;
        }else {
            // 过滤特殊字符
            payment.setAccountNumber(StringUtil.tstr(payment.getAccountNumber().trim()));
        }
        EmployeeInfo employee = (EmployeeInfo) BeanUtils.getLoginUser();
        if(StringUtil.isNotBlank(payment.getPaymentAccountId())) {
            // 修改
            payment.setUpdateUserId(employee.getId());
            payment.setUpdateDate(new Date());
            count = updateByPrimaryKeySelective(payment);
        }else {
            // 新增
            payment.setPaymentAccountId(UUidUtil.getUUId());
            payment.setCreateUserId(employee.getId());
            payment.setCreateDate(new Date());
            payment.setUpdateUserId(employee.getId());
            payment.setUpdateDate(new Date());
            payment.setDeleteFlag("0");
            count = insert(payment);
        }
        if(count > 0) {
            results = JsonUtils.setSuccess();
        }else {
            results = JsonUtils.setError();
            results.put("text", "系统异常, 请刷新后重试");
        }
        return results;
    }

    public JSONObject updatePaymentAccount(String paymentAccountId, String deleteReason, JSONObject results, HttpServletRequest request) {
        EmployeeInfo employee = (EmployeeInfo) BeanUtils.getLoginUser();
        PaymentAccountInfo paymentAccountInfo = selectByPrimaryKey(paymentAccountId);
        paymentAccountInfo.setDeleteFlag("1");
        paymentAccountInfo.setDeleteReason(deleteReason);
        paymentAccountInfo.setUpdateUserId(employee.getId());
        paymentAccountInfo.setUpdateDate(new Date());
        int count = updateByPrimaryKey(paymentAccountInfo);
        if(count > 0) {
            results = JsonUtils.setSuccess();
        }else {
            results = JsonUtils.setError();
            results.put("text", "系统异常, 请刷新后重试");
        }
        return results;
    }
}