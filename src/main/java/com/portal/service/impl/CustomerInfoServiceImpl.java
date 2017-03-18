package com.portal.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.portal.bean.Criteria;
import com.portal.bean.CustomerInfo;
import com.portal.bean.CustomerType;
import com.portal.bean.EmployeeInfo;
import com.portal.bean.OrderInfo;
import com.portal.bean.result.CustomerSimpleInfoForm;
import com.portal.common.util.DateUtil;
import com.portal.common.util.StringUtil;
import com.portal.common.util.UUidUtil;
import com.portal.dao.CustomerInfoDao;
import com.portal.dao.OrderInfoDao;
import com.portal.dao.extra.CustomerInfoExtraDao;
import com.portal.service.CustomerInfoService;
import com.portal.service.EmployeeInfoService;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerInfoServiceImpl implements CustomerInfoService {
    @Autowired
    private CustomerInfoDao customerInfoDao;

    @Autowired
    private OrderInfoDao orderInfoDao;

    @Autowired
    EmployeeInfoService employeeInfoService;

    @Autowired
    private CustomerInfoExtraDao customerInfoExtraDao;

    private static final Logger logger = LoggerFactory.getLogger(CustomerInfoServiceImpl.class);

    // 公共查询条件类
    Criteria criteria = new Criteria();

    //登门次数加1
    public void updateVisitCount(String cid) {
        criteria.clear();
        criteria.put("cid", cid);
        customerInfoExtraDao.updateVisitCount(criteria);
    }

    //修改客户类型 登门之后修改  客户分类 0 空白客户  1 重复登门 2说明会  3成单  4锁定 5转介绍
    // 登门修改为1 成单修改为3

    public int updateType(String cid, String newType) {
        CustomerInfo customerInfo = customerInfoDao.selectByPrimaryKey(cid);
        if (customerInfo == null) {
            return 0;
        }
        String oldType = customerInfo.getType().equals("") ? "0" : customerInfo.getType();
        if (Integer.valueOf(oldType) < Integer.valueOf(newType)) {
            criteria.clear();
            criteria.put("cid", cid);
            criteria.put("type", newType);
            return customerInfoExtraDao.updateType(criteria);
        }
        return 0;
    }

    // product 使用\n连接
    public int updateProduct(String cid, String product, String amount) {
        criteria.clear();
        criteria.put("cid", cid);
        criteria.put("product", product);
        criteria.put("amount", amount);
        return customerInfoExtraDao.updateProduct(criteria);
    }

    // gift 使用\n连接
    public int updateGift(String cid, String gift) {
        criteria.clear();
        criteria.put("cid", cid);
        criteria.put("gift", gift);
        return customerInfoExtraDao.updateGift(criteria);
    }

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

    /**
     * 根据id 查询用户信息
     * @param id
     * @return
     */
    public CustomerSimpleInfoForm getCutomerInfoById(String id, EmployeeInfo employeeInfo) {
        CustomerInfo cInfo = selectByPrimaryKey(id);
        return getSimpleInfoForm(cInfo, employeeInfo);
    }

    //根据电话号码查询ID信息
    public CustomerSimpleInfoForm getFristQueryInfo(String phone) {
        CustomerInfo cInfo = selectByPhone(phone);
        if (cInfo == null) {
            return null;
        }
        return getSimpleInfoForm(cInfo, null);
    }

    //转为页面显示的 customerForm
    CustomerSimpleInfoForm getSimpleInfoForm(CustomerInfo cInfo, EmployeeInfo employeeInfo) {
        CustomerSimpleInfoForm cSimpleForm = new CustomerSimpleInfoForm();
        cSimpleForm.setId(cInfo.getId());
        cSimpleForm.setName(cInfo.getName());
        cSimpleForm.setPhone(cInfo.getPhone());
        cSimpleForm.setEncryptPhone(StringUtil.encryptPhone(cInfo.getPhone()));
        if (StringUtil.isNotBlank(cInfo.getPhone2())) {
            cSimpleForm.setEncryptPhone2(StringUtil.encryptPhone(cInfo.getPhone2()));
        }
        cSimpleForm.setType(CustomerType.getName(Integer.parseInt(cInfo.getType())));
        cSimpleForm.setRelationId(cInfo.getRelationId());
        if (!StringUtils.isEmpty(cInfo.getPhoneStaffId())) {
            cSimpleForm.setPhoneStaffId(cInfo.getPhoneStaffId());
            cSimpleForm
                    .setPhoneStaffName(
                            employeeInfoService.selectByPrimaryKey(cInfo.getPhoneStaffId()).getName());
        }
        if (StringUtils.isEmpty(cInfo.getReceiverStaffId())) {
            if (employeeInfo != null) {
                cSimpleForm.setReceiverStaffId(employeeInfo.getId());
                cSimpleForm.setReceiverStaffName(employeeInfo.getName());

            }
        } else {
            cSimpleForm.setReceiverStaffId(cInfo.getReceiverStaffId());
            cSimpleForm.setReceiverStaffName(
                    employeeInfoService.selectByPrimaryKey(cInfo.getReceiverStaffId()).getName());
        }
        cSimpleForm.setBlacklistFlag(cInfo.getBlacklistFlag() == "1" ? "是" : "否");

        //20170219 add
        String staffDate = null;
        if (!StringUtils.isEmpty(cInfo.getReceiverStaffDate())) {
            staffDate = cInfo.getReceiverStaffDate().replace("\\n", "<br/>");
        }
        cSimpleForm.setReceiverStaffDate(staffDate);
        String product = null;
        if (!StringUtils.isEmpty(cInfo.getProduct())) {
            product = cInfo.getProduct().replace("\\n", "<br/>");
        }
        cSimpleForm.setProduct(product);
        cSimpleForm.setTransactionAmount(cInfo.getTransactionAmount());
        cSimpleForm.setCallDates(cInfo.getCallDates());
        String gift = null;
        if (!StringUtils.isEmpty(cInfo.getGift())) {
            gift = cInfo.getGift().replace("\\n", "<br/>");
        }
        cSimpleForm.setGift(gift);
        String staffName = null;
        if (!StringUtils.isEmpty(cInfo.getReceiverStaffName())) {
            staffName = cInfo.getReceiverStaffName().replace("\\n", "<br/>");
        }
        cSimpleForm.setHisReceiverStaffName(staffName);
        cSimpleForm.setVisitCount(Integer.valueOf(cInfo.getVisitCount() == null ? "0" : cInfo.getVisitCount()));
        cSimpleForm.setRecentVisitDate(cInfo.getRecentVisitDate());
        return cSimpleForm;
    }

    /**
     * 新增客户员工
     * add receiverStaffName & receiverStaffId
     * @param request
     * @return
     */
    public CustomerSimpleInfoForm insertCustomer(CustomerInfo cInfo, EmployeeInfo employeeInfo) {
        cInfo.setId(UUidUtil.getUUId());
        cInfo.setType("1");
        cInfo.setBirthday(DateUtil.parseDate(cInfo.getBirthdayStr(), DateUtil.DATE_FMT_YYYY_MM_DD));
        cInfo.setRecentVisitDate(new Date());
        cInfo.setReceiverStaffId(employeeInfo.getId());
        cInfo.setReceiverStaffName(employeeInfo.getName());
        insertSelective(cInfo);
        return getFristQueryInfo(cInfo.getPhone());

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

        if (StringUtil.isNotBlank(startDate)) {
            criteria.put("startDate", startDate);
        } else {
            criteria.put("startDate",
                    DateUtil.formatDate(DateUtil.getLastWeekMonday(new Date()), "yyyy-MM-dd"));
        }
        if (StringUtil.isNotBlank(endDate)) {
            criteria.put("endDate",
                    DateUtil.formatDate(DateUtil.parseDate(endDate, "yyyy-MM-dd"), "yyyy-MM-dd 23:59:59"));
        } else {
            criteria.put("endDate",
                    DateUtil.formatDate(DateUtil.getLastWeekSunday(new Date()), "yyyy-MM-dd 23:59:59"));
        }
        if (StringUtil.isNotBlank(area)) {
            criteria.put("area", area);
        }

        // 获取数据集
        List<CustomerSimpleInfoForm> customers = customerInfoExtraDao.getFiltrateCustomers(criteria);
        // 获取总记录数
        int totalRecord = customers.size();

        JSONObject resultJson = new JSONObject();
        resultJson.put("sEcho", request.getParameter("sEcho"));
        resultJson.put("iTotalRecords", totalRecord);
        resultJson.put("iTotalDisplayRecords", totalRecord);
        resultJson.put("aaData", customers);
        return resultJson;
    }

    public JSONObject ajaxCustomerStatistics(HttpServletRequest request) {
        // 查询两个地区的接待客户数量（默认查询上一周的数据）
        JSONObject result = new JSONObject();
        // 开始日期
        String startUpdateDate = request.getParameter("startDate");
        // 结束日期
        String endUpdateDate = request.getParameter("endDate");
        criteria.clear();
        if (StringUtil.isNotBlank(startUpdateDate)) {
            criteria.put("startUpdateDate", startUpdateDate);
        } else {
            criteria.put("startUpdateDate",
                    DateUtil.formatDate(DateUtil.getLastWeekMonday(new Date()), "yyyy-MM-dd"));
        }
        if (StringUtil.isNotBlank(endUpdateDate)) {
            criteria.put("endUpdateDate", DateUtil.formatDate(DateUtil.parseDate(endUpdateDate, "yyyy-MM-dd"),
                    "yyyy-MM-dd 23:59:59"));
        } else {
            criteria.put("endUpdateDate",
                    DateUtil.formatDate(DateUtil.getLastWeekSunday(new Date()), "yyyy-MM-dd 23:59:59"));
        }
        // 获取用户数量
        Map<String, Integer> counts = customerInfoExtraDao.getCustomerCounts(criteria);

        //查询出大连各种客户类型的客户数量
        criteria.put("area", 1);
        Map<String, Object> dlCustomer = customerInfoExtraDao.getAllCategoryCustomer(criteria);

        // 获取沈阳对应下的各种分类的客户数量
        criteria.put("area", 0);
        Map<String, Object> syCustomer = customerInfoExtraDao.getAllCategoryCustomer(criteria);

        if (counts != null) {
            result.put("customerCounts",
                    counts.get("total_counts") != null ? counts.get("total_counts") : new BigDecimal(0));
            result.put("dlCounts",
                    counts.get("dl_counts") != null ? counts.get("dl_counts") : new BigDecimal(0));
            result.put("syCounts",
                    counts.get("sy_counts") != null ? counts.get("sy_counts") : new BigDecimal(0));
        } else {
            result.put("customerCounts", 0);
            result.put("dlCounts", 0);
            result.put("syCounts", 0);
        }
        if (dlCustomer != null) {
            // 转换成JSON格式
            JSONObject dlResult = JSONObject.fromObject(dlCustomer);
            result.put("dlResult", dlResult);
        } else {
            result.put("dlResult", null);
        }
        if (syCustomer != null) {
            // 转换成JSON格式
            JSONObject syResult = JSONObject.fromObject(syCustomer);
            result.put("syResult", syResult);
        } else {
            result.put("syResult", null);
        }

        return result;
    }

    /**
     * @Title: updateExportDate 
     * @Description: 导出时间更新
     * @param resultList 
     * @return void
     * @throws
     */
    @Override
    public void updateExportDate(List<CustomerInfo> resultList) {
        customerInfoDao.updateExportDate(resultList);
    }

    /**
     * @Title: insertAndUpdateCustomerInfo 
     * @Description: 插入用户信息 如果电话重复则更新
     * @param resultList 
     * @return void
     * @throws
     */
    @Override
    public String insertAndUpdateCustomerInfo(List<Map<String, Object>> data) {
        StringBuffer sb = new StringBuffer();
        int i = 0;
        for (; i < data.size(); i ++) {
            if (null == data.get(i).get("p9") || "".equals(data.get(i).get("p9"))) {
                continue;
            }
            if (!(String.valueOf(data.get(i).get("p9")).length() == 11
                    || String.valueOf(data.get(i).get("p9")).length() == 8
                    || String.valueOf(data.get(i).get("p9")).length() == 12
                    || String.valueOf(data.get(i).get("p9")).length() == 13)) {
                continue;
            }

            try {
                customerInfoDao.insertAndUpdateCustomerInfo(data.get(i));
            } catch (Exception e) {
                e.printStackTrace();
                sb.append(i + 1).append(",");
                continue;
            }
        }
        return sb.toString();
    }

    /**
     * @Title: insertAndUpdateCustomerInfo 
     * @Description: 导入更新电联人员
     * @param data 
     * @return void
     * @throws
     */
    @Override
    public void updateCustomerInfo(List<Map<String, Object>> data) {
        for (Map<String, Object> m : data) {
            customerInfoDao.updateCustomerInfo(m);
        }
    }

    /**
     * @Title: insertAndUpdateCustomerInfo 
     * @Description: 导出用户信息
     * @param criteria 
     * @return List<CustomerInfo>
     * @throws
     */
    @Override
    public List<CustomerInfo> selectCustomerExportList(Criteria criteria) {
        return customerInfoDao.selectCustomerExportList(criteria);
    }

    /**
     * @Title: selectCustomerOrderCount 
     * @Description: 用户订单详情数量
     * @param customerId
     * @return void
     * @throws
     */
    @Override
    public int selectCustomerOrderCount(Map<String, Object> paramMap) {
        return orderInfoDao.selectCustomerOrderCount(paramMap);
    }

    /**
     * @Title: customerOrderInfoList 
     * @Description: 用户订单详情
     * @param customerId
     * @return void
     * @throws
     */
    @Override
    public List<OrderInfo> selectCustomerOrderList(Map<String, Object> paramMap) {
        return orderInfoDao.selectCustomerOrderList(paramMap);
    }

    public JSONObject ajaxCustomerData(Criteria criteria, String sEcho) {
        // 获取总记录数
        int totalRecord = customerInfoExtraDao.countByConditions(criteria);
        // 获取数据集
        List<CustomerSimpleInfoForm> list = customerInfoExtraDao.selectByConditions(criteria);
        JSONObject resultJson = new JSONObject();
        resultJson.put("sEcho", sEcho);
        resultJson.put("iTotalRecords", totalRecord);
        resultJson.put("iTotalDisplayRecords", totalRecord);
        resultJson.put("aaData", list);
        return resultJson;
    }

    public JSONObject saveCustomerInfo(CustomerSimpleInfoForm customerForm, JSONObject results) {
        return null;
    }

    public List<CustomerInfo> getAllCustomer() {
        return customerInfoExtraDao.getAllCustomer();
    }

    /*
    * @Title: insertAndUpdateCustomerInfo 
    * @Description: 插入用户信息 如果电话重复则更新
    * @param resultList 
    * @return void
    * @throws
    */
    @Override
    public String insertAndUpdateCustomerInfoAdd(List<Map<String, Object>> data) {
        StringBuffer sb = new StringBuffer();
        int i = 0;
        for (; i < data.size(); i ++) {
            if (null == data.get(i).get("p9") || "".equals(data.get(i).get("p9"))) {
                continue;
            }
            if (!(String.valueOf(data.get(i).get("p9")).length() == 11
                    || String.valueOf(data.get(i).get("p9")).length() == 8
                    || String.valueOf(data.get(i).get("p9")).length() == 12
                    || String.valueOf(data.get(i).get("p9")).length() == 13)) {
                continue;
            }
            try {
                customerInfoDao.insertAndUpdateCustomerInfoAdd(data.get(i));
            } catch (Exception e) {
                e.printStackTrace();
                sb.append(i + 1).append(",");
                continue;
            }
        }
        return sb.toString();
    }
}
