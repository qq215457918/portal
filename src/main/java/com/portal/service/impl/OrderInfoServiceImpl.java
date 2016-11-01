package com.portal.service.impl;

import com.portal.bean.Criteria;
import com.portal.bean.CustomerInfo;
import com.portal.bean.OrderDetailInfo;
import com.portal.bean.OrderInfo;
import com.portal.bean.result.GoodsInfoForm;
import com.portal.bean.result.OrderInfoForm;
import com.portal.common.util.DateUtil;
import com.portal.common.util.StringUtil;
import com.portal.common.util.UUidUtil;
import com.portal.dao.OrderDetailInfoDao;
import com.portal.dao.OrderInfoDao;
import com.portal.dao.extra.GoodsDao;
import com.portal.dao.extra.OrderInfoExtraDao;
import com.portal.service.CustomerInfoService;
import com.portal.service.OrderDetailInfoService;
import com.portal.service.OrderInfoService;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import net.sf.json.JSONObject;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderInfoServiceImpl implements OrderInfoService {

    @Autowired
    private OrderInfoDao orderInfoDao;

    @Autowired
    private OrderDetailInfoDao orderInfoDetailDao;

    @Autowired
    private OrderInfoExtraDao orderInfoExtraDao;

    @Autowired
    private GoodsDao goodsDao;

    private CustomerInfoService customerInfoService;

    @Autowired
    private OrderDetailInfoService orderDetailInfoService;

    // 公共查询条件类
    Criteria criteria = new Criteria();

    private static final Logger logger = LoggerFactory.getLogger(OrderInfoServiceImpl.class);

    Criteria getCriteria(String customerId, int status, int orderType, int payType) {
        criteria.clear();
        criteria.put("customer_id", customerId);
        criteria.put("status", status);
        criteria.put("order_type", orderType);
        criteria.put("pay_type", payType);
        criteria.setOrderByClause("create_date");
        criteria.setMysqlLength(5);
        return criteria;
    }

    /**
    * 根绝类型获取订单信息 
    *    `status` varchar(1) COLLATE utf8_bin DEFAULT NULL COMMENT '订单状态 : 0未支付 1已支付 2已出库 3文交所已审核 4 已完成',
    *     `order_type` varchar(1) COLLATE utf8_bin DEFAULT NULL COMMENT '订单类型 1正常 2退货 3换货',
    *     `pay_type` varchar(1) COLLATE utf8_bin DEFAULT NULL COMMENT '支付类型  0全额支付 1定金支付 2派送支付',
    *     `pay_price` decimal(10,0) DEFAULT NULL COMMENT '订单金额',
    *     `actual_price` decimal(10,0) DEFAULT NULL COMMENT '实际支付金额',
    *     `finance_flag` varchar(45) COLLATE utf8_bin DEFAULT NULL COMMENT '支付状态(财务审核标志)\n0 未支付\n1已支付',
    * @param customerId
    * @param payType
    * @param orderType
    * @return
    * @throws IllegalAccessException
    * @throws InvocationTargetException
    */
    List<OrderInfoForm> getOrderInfo(String customerId, int status, int orderType, int payType) {
        List<OrderInfoForm> orderInfoForm = new ArrayList<OrderInfoForm>();
        List<OrderInfo> orderInfoList =
                orderInfoDao.selectByExample(getCriteria(customerId, status, orderType, payType));
        //把order的信息放入到form中
        orderInfoList.forEach(value -> {
            try {
                OrderInfoForm orderInfo = new OrderInfoForm();
                BeanUtils.copyProperties(orderInfo, value);
                orderInfoForm.add(orderInfo);
            } catch (IllegalAccessException e) {
                logger.warn("Unexpected exception:", e);
            } catch (InvocationTargetException e) {
                logger.warn("Unexpected exception:", e);
            }
        });

        //把商品详情信息放入到form中
        orderInfoForm.forEach(value -> {
            value.setOrderDetailInfoList(queryDetaiInfo(value.getId()));
            value.setCreateDateString(
                    DateUtil.formatDate(value.getCreateDate(), DateUtil.DATE_FMT_YYYYMMDDHHMMSS));
        });

        return orderInfoForm;
    }

    List<OrderInfoForm> getNormalOrderInfo(String customerId, int orderType, int payType) {
        return getOrderInfo(customerId, 1, orderType, payType);
    }

    /**
     * 查询商品订单 by customerId
     * by meng.yue
     * @return
     * @throws InvocationTargetException 
     * @throws IllegalAccessException 
     */
    public List<OrderInfoForm> queryGoodsInfo(String customerId) {
        return getNormalOrderInfo(customerId, 1, 0);
    }

    /**
     * 查看已经支付的定金订单
     * @param customerId
     * @return
     * @throws InvocationTargetException 
     * @throws IllegalAccessException 
     */
    public List<OrderInfoForm> queryRevokeDepositInfo(String customerId) {
        return getNormalOrderInfo(customerId, 1, 1);
    }

    /**
     * 退货订单
     * @param customerId
     * @return
     * @throws InvocationTargetException 
     * @throws IllegalAccessException 
     */
    public List<OrderInfoForm> queryReturnGoodsInfo(String customerId) {
        return getNormalOrderInfo(customerId, 2, 1);
    }

    /**
     * 换货订单
     * @param customerId
     * @return
     * @throws InvocationTargetException 
     * @throws IllegalAccessException 
     */
    public List<OrderInfoForm> xchangeReturnGoodsInfo(String customerId) {
        return getNormalOrderInfo(customerId, 3, 1);
    }

    //undone order
    //未完成的订单信息
    public List<OrderInfoForm> undoneOrder(String customerId) {
        return getOrderInfo(customerId, 0, 3, 1);
    }

    /**
     * 获取 订单详情信息
     * @param orderId
     * @return
     */
    List<OrderDetailInfo> queryDetaiInfo(String orderId) {
        criteria.clear();
        criteria.put("order_id", orderId);
        return orderInfoDetailDao.selectByExample(criteria);
    }

    /**
     * add present 2 order_info for review
     */
    public boolean addPresentOrder(HttpServletRequest request) {
        criteria.clear();
        String uuid = UUidUtil.getUUId();
        insertSelective(getOrderInfo(request, uuid));
        return addPresentDetailInfo(getOrderDetailInfo(request, uuid));
    }

    /**
     * ready for orderdetailInfo
     */
    OrderDetailInfo getOrderDetailInfo(HttpServletRequest request, String uuid) {
        OrderDetailInfo detailInfo = new OrderDetailInfo();
        GoodsInfoForm goodInfo = goodsDao.selectByPrimaryKey(request.getParameter("goodID"));
        detailInfo.setId(UUidUtil.getUUId());
        detailInfo.setOrderId(uuid);
        detailInfo.setGoodId(goodInfo.getId());
        detailInfo.setGoodSortId(goodInfo.getSortId());
        detailInfo.setGoodSortName(goodInfo.getSortName());
        detailInfo.setGoodType(goodInfo.getType());
        detailInfo.setGoodName(goodInfo.getName());
        return detailInfo;
    }

    /**
     * ready for orderInfo
     * @param request
     * @param cid
     * @return
     */
    OrderInfo getOrderInfo(HttpServletRequest request, String uuid) {
        String cid = request.getParameter("customerId");

        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setId(uuid);
        orderInfo.setCustomerId(cid);
        orderInfo.setOrderType("4");
        orderInfo.setCreateDate(new Date());
        orderInfo.setStatus("0");
        String[] staff = getStaffInfo(cid);
        orderInfo.setReceiverStaffId(staff[0]);
        orderInfo.setPhoneStaffId(staff[1]);
        orderInfo.setRemarks(request.getParameter("remarks"));
        return orderInfo;
    }

    /**
     * 获取接待人员信息
     * @param customerId
     * @return
     */
    String[] getStaffInfo(String customerId) {
        CustomerInfo cInfo = customerInfoService.selectByPrimaryKey(customerId);
        String[] staff = { cInfo.getReceiverStaffId(), cInfo.getPhoneStaffId() };
        return staff;
    }

    /**
     * add present 2 order_detail
     * @return
     */
    boolean addPresentDetailInfo(OrderDetailInfo detailInfo) {
        return orderDetailInfoService.insert(detailInfo) > 0 ? true : false;
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

    public JSONObject ajaxClinchPerforEveryDay(HttpServletRequest request) {
        // 查询两个地区的每日业绩情况（默认查询上一周）
        JSONObject result = new JSONObject();

        // 开始日期
        String startDate = request.getParameter("startDate");

        criteria.clear();
        if (StringUtil.isNotBlank(startDate)) {
            // 不管前台选择的是周几, 都获取到对应星期的周一
            startDate = DateUtil.formatDate(
                    DateUtil.getNowWeekMonday(DateUtil.parseDate(startDate, "yyyy-MM-dd")), "yyyy-MM-dd");
            criteria.put("startDate", startDate);
            // 默认存入当前选中日期对应的周日
            criteria.put("endDate",
                    DateUtil.formatDate(DateUtil.getNowWeekSunday(DateUtil.parseDate(startDate, "yyyy-MM-dd")),
                            "yyyy-MM-dd 23:59:59"));
        } else {
            // 如果开始时间为空, 则取当前日期的上一周为查询条件
            startDate = DateUtil.formatDate(DateUtil.getLastWeekMonday(new Date()), "yyyy-MM-dd");
            criteria.put("startDate", startDate);
            criteria.put("endDate",
                    DateUtil.formatDate(DateUtil.getLastWeekSunday(new Date()), "yyyy-MM-dd 23:59:59"));
        }

        // 获取一周内各地区的业绩
        Map<String, Integer> counts = orderInfoExtraDao.getClinchPerfors(criteria);

        //查询出大连一周的业绩
        Map<String, Object> dlAmounts = orderInfoExtraDao.getWeekClinchPerfors(startDate, "1");

        // 查询出沈阳一周的业绩
        Map<String, Object> syAmounts = orderInfoExtraDao.getWeekClinchPerfors(startDate, "0");

        if (counts != null) {
            result.put("totalAmounts",
                    counts.get("total_amounts") != null ? counts.get("total_amounts") : new BigDecimal(0));
            result.put("dlAmounts",
                    counts.get("dl_amounts") != null ? counts.get("dl_amounts") : new BigDecimal(0));
            result.put("syAmounts",
                    counts.get("sy_amounts") != null ? counts.get("sy_amounts") : new BigDecimal(0));
        } else {
            result.put("totalAmounts", 0);
            result.put("dlAmounts", 0);
            result.put("syAmounts", 0);
        }
        if (dlAmounts != null) {
            // 转换成JSON格式
            JSONObject dlResult = JSONObject.fromObject(dlAmounts);
            result.put("dlResult", dlResult);
        } else {
            result.put("dlResult", null);
        }
        if (syAmounts != null) {
            // 转换成JSON格式
            JSONObject syResult = JSONObject.fromObject(syAmounts);
            result.put("syResult", syResult);
        } else {
            result.put("syResult", null);
        }

        return result;
    }
}
