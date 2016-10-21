package com.portal.service.impl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portal.bean.Criteria;
import com.portal.bean.CustomerInfo;
import com.portal.bean.result.CustomerSimpleInfoForm;
import com.portal.common.util.DateUtil;
import com.portal.common.util.StringUtil;
import com.portal.dao.CustomerInfoDao;
import com.portal.dao.extra.CustomerInfoExtraDao;
import com.portal.service.CustomerInfoService;
import com.portal.service.EmployeeInfoService;

import net.sf.json.JSONObject;

@Service
public class CustomerInfoServiceImpl implements CustomerInfoService {
    @Autowired
    private CustomerInfoDao customerInfoDao;

    @Autowired
    EmployeeInfoService EmployeeInfoService;

    @Autowired
    private CustomerInfoExtraDao customerInfoExtraDao;

    private static final Logger logger = LoggerFactory.getLogger(CustomerInfoServiceImpl.class);
    
    // 公共查询条件类
    Criteria criteria = new Criteria();

    /**
     * 通过电话号码查询客户信息
     * by meng.yue
     * @param phone
     * @return
     */
    public CustomerInfo selectByPhone(String phone) {
        return customerInfoExtraDao.selectByPhone(phone);
    }

    /**
     * 判断是否是已经记录的用户
     * by meng.yue
     * @param phone
     * @return
     */
    public boolean isCustomer(String phone) {
        return selectByPhone(phone) == null ? false : true;
    }

    public CustomerSimpleInfoForm getFristQueryInfo(String phone) {
        CustomerSimpleInfoForm cSimpleForm = new CustomerSimpleInfoForm();
        CustomerInfo cInfo = selectByPhone(phone);
        cSimpleForm.setId(cInfo.getId());
        cSimpleForm.setName(cInfo.getName());
        cSimpleForm.setEncryptPhone(cInfo.getPhone());
        cSimpleForm.setPhoneStaffId(cInfo.getPhoneStaffId());
        cSimpleForm
                .setPhoneStaffName(EmployeeInfoService.selectByPrimaryKey(cInfo.getPhoneStaffId()).getName());
        cSimpleForm.setReceiverStaffId(cInfo.getReceiverStaffId());
        cSimpleForm.setReceiverStaffName(
                EmployeeInfoService.selectByPrimaryKey(cInfo.getReceiverStaffId()).getName());
        return cSimpleForm;
    }

    public int insertSelective(CustomerInfo record) {
        return this.customerInfoDao.insertSelective(record);
    }

    public int countByExample(Criteria example) {
        int count = this.customerInfoDao.countByExample(example);
        logger.debug("count: {}", count);
        return count;
    }

    public CustomerInfo selectByPrimaryKey(String id) {
        return customerInfoDao.selectByPrimaryKey(id);
    }

    public List<CustomerInfo> selectByExample(Criteria example) {
        return this.customerInfoDao.selectByExample(example);
    }

    public int deleteByPrimaryKey(String id) {
        return this.customerInfoDao.deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(CustomerInfo record) {
        return this.customerInfoDao.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(CustomerInfo record) {
        return this.customerInfoDao.updateByPrimaryKey(record);
    }

    public int deleteByExample(Criteria example) {
        return this.customerInfoDao.deleteByExample(example);
    }

    public int updateByExampleSelective(CustomerInfo record, Criteria example) {
        return this.customerInfoDao.updateByExampleSelective(record, example);
    }

    public int updateByExample(CustomerInfo record, Criteria example) {
        return this.customerInfoDao.updateByExample(record, example);
    }

    public int insert(CustomerInfo record) {
        return this.customerInfoDao.insert(record);
    }

    @Override
    public List<CustomerInfo> selectCustomerExList(Criteria criteria) {
    	return customerInfoDao.selectCustomerExList(criteria);
    }
    
    @Override
    public int countCustomerEx(Criteria criteria) {
    	return customerInfoDao.countCustomerEx(criteria);
    }
    
    public JSONObject ajaxFiltrateCustomers(HttpServletRequest request) {
        // 查询筛选客户类型数据（默认查询上一周的数据）
        // 请求开始页
        int currentPage = StringUtil.getIntValue(request.getParameter("iDisplayStart"));
        // 每页显示几条
        int perpage = StringUtil.getIntValue(request.getParameter("iDisplayLength"));
        // 开始日期
        String startDate = request.getParameter("startDate");
        // 结束日期
        String endDate = request.getParameter("endDate");
        // 机构ID
        String area = request.getParameter("area");
        
        criteria.clear();
        criteria.setMysqlOffset(currentPage);
        criteria.setMysqlLength(perpage);
        
        if(StringUtil.isNotBlank(startDate)){
            criteria.put("startDate", startDate);
        }else {
            criteria.put("startDate", DateUtil.formatDate(DateUtil.getLastWeekMonday(new Date()), "yyyy-MM-dd"));
        }
        if(StringUtil.isNotBlank(endDate)){
            criteria.put("endDate", DateUtil.formatDate(DateUtil.parseDate(endDate, "yyyy-MM-dd"), "yyyy-MM-dd 23:59:59"));
        }else {
            criteria.put("endDate", DateUtil.formatDate(DateUtil.getLastWeekSunday(new Date()), "yyyy-MM-dd 23:59:59"));
        }
        if(StringUtil.isNotBlank(area)){
            criteria.put("area", area);
        }
        
        // 获取数据集
        List<CustomerSimpleInfoForm> customers = customerInfoExtraDao.getFiltrateCustomers(criteria);
        // 获取总记录数
        int totalRecord = customers.size();
        
        JSONObject resultJson =  new JSONObject();
        resultJson.put("sEcho", request.getParameter("sEcho"));
        resultJson.put("iTotalRecords", totalRecord);
        resultJson.put("iTotalDisplayRecords", totalRecord);
        resultJson.put("aaData", customers);
        return resultJson;
    }
}
