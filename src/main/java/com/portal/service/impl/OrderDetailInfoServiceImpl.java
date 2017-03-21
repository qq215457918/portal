package com.portal.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portal.bean.Criteria;
import com.portal.bean.OrderDetailInfo;
import com.portal.bean.result.OrderDetailInfoForm;
import com.portal.bean.result.ReceptionInfoForm;
import com.portal.common.util.DateUtil;
import com.portal.common.util.StringUtil;
import com.portal.dao.OrderDetailInfoDao;
import com.portal.dao.extra.OrderDetailInfoExtraDao;
import com.portal.service.OrderDetailInfoService;

import net.sf.json.JSONObject;

@Service
public class OrderDetailInfoServiceImpl implements OrderDetailInfoService {
    @Autowired
    private OrderDetailInfoDao orderDetailInfoDao;
    
    @Autowired
    private OrderDetailInfoExtraDao orderDetailExtraDao;

    private static final Logger logger = LoggerFactory.getLogger(OrderDetailInfoServiceImpl.class);
    
    // 公共查询条件类
    Criteria criteria = new Criteria();

    public int countByExample(Criteria example) {
        int count = this.orderDetailInfoDao.countByExample(example);
        logger.debug("count: {}", count);
        return count;
    }

    public OrderDetailInfo selectByPrimaryKey(String id) {
        return this.orderDetailInfoDao.selectByPrimaryKey(id);
    }

    public List<OrderDetailInfo> selectByExample(Criteria example) {
        return this.orderDetailInfoDao.selectByExample(example);
    }

    public int deleteByPrimaryKey(String id) {
        return this.orderDetailInfoDao.deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(OrderDetailInfo record) {
        return this.orderDetailInfoDao.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(OrderDetailInfo record) {
        return this.orderDetailInfoDao.updateByPrimaryKey(record);
    }

    public int deleteByExample(Criteria example) {
        return this.orderDetailInfoDao.deleteByExample(example);
    }

    public int updateByExampleSelective(OrderDetailInfo record, Criteria example) {
        return this.orderDetailInfoDao.updateByExampleSelective(record, example);
    }

    public int updateByExample(OrderDetailInfo record, Criteria example) {
        return this.orderDetailInfoDao.updateByExample(record, example);
    }

    public int insert(OrderDetailInfo record) {
        return this.orderDetailInfoDao.insert(record);
    }

    public int insertSelective(OrderDetailInfo record) {
        return this.orderDetailInfoDao.insertSelective(record);
    }
    
    public JSONObject ajaxOutOrderDetail(HttpServletRequest request) {
        // 查询登门出单详细数据
        // 请求开始页
        int currentPage = StringUtil.getIntValue(request.getParameter("iDisplayStart"));
        // 每页显示几条
        int perpage = StringUtil.getIntValue(request.getParameter("iDisplayLength"));
        // 开始日期
        String startDate = request.getParameter("startDate");
        // 结束日期
        String endDate = request.getParameter("endDate");
        // 客户ID
        String customerId = request.getParameter("customerId");
        // 产品名称
        String goodName = request.getParameter("goodName");
        // 产品种类
        // String goodsSortName = request.getParameter("goodsSortName");
        
        criteria.clear();
        criteria.setMysqlOffset(currentPage);
        criteria.setMysqlLength(perpage);
        criteria.setOrderByClause("o.create_date asc");
        
        // 订单支付状态为已支付
        criteria.put("financeFlag", "1");
        criteria.put("orderDeleteFlag", "0");
        if(StringUtil.isNotBlank(startDate)){
            criteria.put("startDate", startDate);
        }
        if(StringUtil.isNotBlank(endDate)){
            criteria.put("endDate", DateUtil.formatDate(DateUtil.parseDate(endDate, "yyyy-MM-dd"), "yyyy-MM-dd 23:59:59"));
        }
        if(StringUtil.isNotBlank(customerId)){
            criteria.put("customerId", customerId);
        }
        if(StringUtil.isNotBlank(goodName)){
            criteria.put("goodName", goodName);
        }
        /*if(StringUtil.isNotBlank(goodsSortName)){
            criteria.put("goodsSortName", goodsSortName);
        }*/
        
        // 获取总记录数
        int totalRecord = orderDetailExtraDao.getOrderDetailCounts(criteria);
        // 获取数据集
        List<ReceptionInfoForm> list = orderDetailExtraDao.getOrderDetailsByCondition(criteria);
        
        JSONObject resultJson =  new JSONObject();
        resultJson.put("sEcho", request.getParameter("sEcho"));
        resultJson.put("iTotalRecords", totalRecord);
        resultJson.put("iTotalDisplayRecords", totalRecord);
        resultJson.put("aaData", list);
        return resultJson;
    }
    
    /**
	 * @Title: selectOrderInfoById 
	 * @Description: 通过id获取订单信息列表
	 * @param param
	 * @return void
	 * @throws
	 */
    @Override
    public List<OrderDetailInfo> selectOrderInfoById(Map<String, Object> param) {
    	return orderDetailInfoDao.selectOrderInfoById(param);
    }

    public JSONObject ajaxGiftDetail(HttpServletRequest request, JSONObject results) {
        // 查询赠品明细
        // 请求开始页
        int currentPage = StringUtil.getIntValue(request.getParameter("iDisplayStart"));
        // 每页显示几条
        int perpage = StringUtil.getIntValue(request.getParameter("iDisplayLength"));
        // 开始日期
        String startDate = request.getParameter("startDate");
        // 结束日期
        String endDate = request.getParameter("endDate");
        // 所属区域
        String area = request.getParameter("area");
        
        criteria.clear();
        criteria.setMysqlOffset(currentPage);
        criteria.setMysqlLength(perpage);
        
        if(StringUtil.isNotBlank(startDate)){
            criteria.put("startDate", startDate);
        }else {
            criteria.put("startDate", DateUtil.formatDate(new Date(), "yyyy-MM-dd"));
        }
        if(StringUtil.isNotBlank(endDate)){
            criteria.put("endDate", DateUtil.formatDate(DateUtil.parseDate(endDate, "yyyy-MM-dd"), "yyyy-MM-dd 23:59:59"));
        }else {
            criteria.put("startDate", DateUtil.formatDate(new Date(), "yyyy-MM-dd 23:59:59"));
        }
        if(StringUtil.isNotBlank(area)){
            criteria.put("area", area);
        }
        
        // 获取总记录数
        int totalRecord = orderDetailExtraDao.getGiftDetailCounts(criteria);
        // 获取数据集
        List<OrderDetailInfoForm> list = orderDetailExtraDao.getGiftDetail(criteria);
        
        JSONObject resultJson =  new JSONObject();
        resultJson.put("sEcho", request.getParameter("sEcho"));
        resultJson.put("iTotalRecords", totalRecord);
        resultJson.put("iTotalDisplayRecords", totalRecord);
        resultJson.put("aaData", list);
        return resultJson;
    }

    public List<OrderDetailInfoForm> getGiftDetail(Criteria criteria) {
        return orderDetailExtraDao.getGiftDetail(criteria);
    }
}