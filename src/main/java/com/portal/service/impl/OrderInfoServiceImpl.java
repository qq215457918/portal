package com.portal.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portal.bean.Criteria;
import com.portal.bean.OrderDetailInfo;
import com.portal.bean.OrderInfo;
import com.portal.bean.result.OrderInfoForm;
import com.portal.common.util.DateUtil;
import com.portal.common.util.StringUtil;
import com.portal.dao.OrderDetailInfoDao;
import com.portal.dao.OrderInfoDao;
import com.portal.dao.extra.OrderInfoExtraDao;
import com.portal.service.OrderInfoService;

import net.sf.json.JSONObject;

@Service
public class OrderInfoServiceImpl implements OrderInfoService {
    @Autowired
    private OrderInfoDao orderInfoDao;
    
    @Autowired
    private OrderDetailInfoDao orderInfoDetailDao;
    
    @Autowired
    private OrderInfoExtraDao orderInfoExtraDao;

    private static final Logger logger = LoggerFactory.getLogger(OrderInfoServiceImpl.class);
    
    // 公共查询条件类
    private Criteria criteria = new Criteria();

    
    public Criteria getCriteria(String customerId,int status,int orderType ,int payType){
    	Criteria example = new Criteria();
    	example.put("customer_id", customerId);
    	example.put("status", status);
    	example.put("order_type", orderType);
    	example.put("pay_type", payType);
    	return example;
    }
    
   public OrderInfoForm getOrderInfo(String customerId,int orderType ,int payType) throws IllegalAccessException, InvocationTargetException{
   	OrderInfoForm orderInfoForm = new OrderInfoForm();
   	List<OrderInfo> orderInfoList = orderInfoDao.selectByExample(getCriteria(customerId,1,orderType,payType));
   	BeanUtils.copyProperties(orderInfoForm, orderInfoList.get(0));
   	orderInfoForm.setOrderDetailInfoList(queryDetaiInfo(orderInfoList.get(0).getId()));
	   
	   return orderInfoForm;
   }
    /**
     * 查询商品订单 by customerId
     * by meng.yue
     * @return
     * @throws InvocationTargetException 
     * @throws IllegalAccessException 
     */
    public OrderInfoForm queryGoodsInfo(String customerId) throws IllegalAccessException, InvocationTargetException{

    	return getOrderInfo(customerId,1,0);
    } 
    
    /**
     * 撤销定金订单
     * @param customerId
     * @return
     * @throws InvocationTargetException 
     * @throws IllegalAccessException 
     */
    public OrderInfoForm queryRevokeDepositInfo(String customerId) throws IllegalAccessException, InvocationTargetException{
    	return getOrderInfo(customerId,1,1);
    } 
    
    /**
     * 退货订单
     * @param customerId
     * @return
     * @throws InvocationTargetException 
     * @throws IllegalAccessException 
     */
    public OrderInfoForm queryReturnGoodsInfo(String customerId) throws IllegalAccessException, InvocationTargetException{
    	return getOrderInfo(customerId,2,1);
    } 
    
    
    /**
     * 换货订单
     * @param customerId
     * @return
     * @throws InvocationTargetException 
     * @throws IllegalAccessException 
     */
    public OrderInfoForm xchangeReturnGoodsInfo(String customerId) throws IllegalAccessException, InvocationTargetException{
    	return getOrderInfo(customerId,3,1);
    } 
    
    /**
     * 获取 订单详情信息
     * @param orderId
     * @return
     */
    public List<OrderDetailInfo> queryDetaiInfo(String orderId){
    	Criteria example = new Criteria();
    	example.put("order_id", orderId);
    	return orderInfoDetailDao.selectByExample(example);
    }
    
    public int countByExample(Criteria example) {
        int count = this.orderInfoDao.countByExample(example);
        logger.debug("count: {}", count);
        return count;
    }

    public OrderInfo selectByPrimaryKey(String id) {
        return this.orderInfoDao.selectByPrimaryKey(id);
    }

    public List<OrderInfo> selectByExample(Criteria example) {
        return this.orderInfoDao.selectByExample(example);
    }

    public int deleteByPrimaryKey(String id) {
        return this.orderInfoDao.deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(OrderInfo record) {
        return this.orderInfoDao.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(OrderInfo record) {
        return this.orderInfoDao.updateByPrimaryKey(record);
    }

    public int deleteByExample(Criteria example) {
        return this.orderInfoDao.deleteByExample(example);
    }

    public int updateByExampleSelective(OrderInfo record, Criteria example) {
        return this.orderInfoDao.updateByExampleSelective(record, example);
    }

    public int updateByExample(OrderInfo record, Criteria example) {
        return this.orderInfoDao.updateByExample(record, example);
    }

    public int insert(OrderInfo record) {
        return this.orderInfoDao.insert(record);
    }

    public int insertSelective(OrderInfo record) {
        return this.orderInfoDao.insertSelective(record);
    }

    public int countByCondition(Criteria criteria) {
        int count = this.orderInfoExtraDao.countByCondition(criteria);
        logger.debug("count: {}", count);
        return count;
    }

    public JSONObject getOrganiPerformance(HttpServletRequest request) {
        // 查询机构业绩（默认查询上一周的数据）
        // 请求开始页
        int currentPage = StringUtil.getIntValue(request.getParameter("iDisplayStart"));
        // 每页显示几条
        int perpage = StringUtil.getIntValue(request.getParameter("iDisplayLength"));
        // 开始日期
        String startCreateDate = request.getParameter("startCreateDate");
        // 结束日期
        String endCreateDate = request.getParameter("endCreateDate");
        
        criteria.clear();
        // 分页参数
        criteria.setMysqlOffset(currentPage);
        criteria.setMysqlLength(perpage);
        // 已支付
        criteria.put("financeFlag", "1");
        if(StringUtil.isNotBlank(startCreateDate)){
            criteria.put("startCreateDate", startCreateDate);
        }else {
            criteria.put("startCreateDate", DateUtil.formatDate(DateUtil.getLastWeekMonday(new Date()), "yyyy-MM-dd"));
        }
        if(StringUtil.isNotBlank(endCreateDate)){
            criteria.put("endCreateDate", DateUtil.formatDate(DateUtil.parseDate(endCreateDate, "yyyy-MM-dd"), "yyyy-MM-dd 23:59:59"));
        }else {
            criteria.put("endCreateDate", DateUtil.formatDate(DateUtil.getLastWeekSunday(new Date()), "yyyy-MM-dd 23:59:59"));
        }
        // 获取总记录数
        int totalRecord = orderInfoExtraDao.countByCondition(criteria);
        // 获取数据集
        List<OrderInfoForm> performanceList = orderInfoExtraDao.getOrganiPerformance(criteria);
        
        JSONObject resultJson =  new JSONObject();
        resultJson.put("sEcho", request.getParameter("sEcho"));
        resultJson.put("iTotalRecords", totalRecord);
        resultJson.put("iTotalDisplayRecords", totalRecord);
        resultJson.put("aaData", performanceList);
        return resultJson;
    }
}