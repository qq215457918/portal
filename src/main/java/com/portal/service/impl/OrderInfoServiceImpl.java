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
import com.portal.dao.extra.OrderDetailInfoExtraDao;
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
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderInfoServiceImpl implements OrderInfoService {

    @Autowired
    private OrderDetailInfoExtraDao orderDetailInfoExtraDao;

    @Autowired
    private OrderInfoDao orderInfoDao;

    @Autowired
    private OrderDetailInfoDao orderInfoDetailDao;

    @Autowired
    private OrderInfoExtraDao orderInfoExtraDao;

    @Autowired
    private GoodsDao goodsDao;

    @Autowired
    private CustomerInfoService customerInfoService;

    @Autowired
    private OrderDetailInfoService orderDetailInfoService;

    // 公共查询条件类
    Criteria criteria = new Criteria();

    private static final Logger logger = LoggerFactory.getLogger(OrderInfoServiceImpl.class);

    /**
     * 修改订单为换货订单
     * @return
     */
    public boolean updateOrderReplace(String orderId) {

        OrderInfo orderCriteria = new OrderInfo();
        orderCriteria.setId(orderId);
        orderCriteria.setPayType("3");
        orderCriteria.setStatus("0");
        orderCriteria.setWarehouseFlag("0");
        orderCriteria.setCultureFlag("0");
        updateCancelDetail(orderId);        //更新detail的数量为负
        return orderInfoDao.updateByPrimaryKeySelective(orderCriteria) > 0 ? true : false;
    }

    /**
     * 修改订单为退货订单
     * @return
     */
    public boolean updateOrderReturn(String orderId) {
        updateReturnDetail(orderId);
        OrderInfo orderCriteria = new OrderInfo();
        orderCriteria.setId(orderId);
        orderCriteria.setPayType("2");
        orderCriteria.setStatus("0");
        orderCriteria.setFinanceFlag("0");
        orderCriteria.setWarehouseFlag("0");
        orderCriteria.setCultureFlag("0");
        return orderInfoDao.updateByPrimaryKeySelective(orderCriteria) > 0 ? true : false;
    }

    public void updateReturnDetail(String orderId) {
        Criteria criteria = new Criteria();
        criteria.put("orderId", orderId);
        criteria.put("deleteFlag", "0");
        orderInfoDetailDao.selectByExample(criteria).forEach(value -> {
            value.setId(UUidUtil.getUUId());
            value.setAmount(~value.getAmount() + 1);
            value.setUpdateDate(new Date());
            orderInfoDetailDao.insertSelective(value);
        });
    }

    /**
     * 修改订单定金为正常订单
     */
    public boolean updatePayDeposit(String orderId) {
        OrderInfo orderCriteria = new OrderInfo();
        orderCriteria.setId(orderId);
        //pay_type=3 3余款支付
        orderCriteria.setPayType("3");
        orderCriteria.setStatus("0");
        orderCriteria.setFinanceOperatorId("");
        orderCriteria.setFinanceFlag("0");
        orderCriteria.setFinanceType("");
        orderCriteria.setDeleteFlag("0");
        Long depositPrice = orderInfoDao.selectByPrimaryKey(orderId).getPayPrice();
        orderCriteria.setPayPrice(getCountPrice4Deposit(orderId) - depositPrice);
        return orderInfoDao.updateByPrimaryKeySelective(orderCriteria) > 0 ? true : false;
    }

    public Long getCountPrice4Deposit(String orderId) {
        Criteria criteria = new Criteria();
        criteria.put("orderId", orderId);
        criteria.put("deleteFlag", "0");
        return orderDetailInfoExtraDao.countPrice4Deposit(criteria);
    }

    /**
     * 修改订单定金为撤销
     */
    public boolean updateCancelDeposit(String orderId) {
        OrderInfo orderCriteria = new OrderInfo();
        orderCriteria.setId(orderId);
        //pay_type=1 & OrderType=2 就是定金撤销
        orderCriteria.setOrderType("2");
        orderCriteria.setFinanceOperatorId("");
        orderCriteria.setFinanceFlag("0");
        orderCriteria.setFinanceType("");
        //        orderInfoDetailDao.selectByExample(criteria);
        updateCancelDetail(orderId);
        return orderInfoDao.updateByPrimaryKeySelective(orderCriteria) > 0 ? true : false;
    }

    /**
     * 修改订单详情的数量为负
     * @param orderId
     * @return
     */
    public boolean updateCancelDetail(String orderId) {
        Criteria criteria = new Criteria();
        criteria.put("orderId", orderId);
        criteria.put("deleteFlag", "0");
        orderInfoDetailDao.selectByExample(criteria).forEach(value -> {
            value.setAmount(~value.getAmount() + 1);
            orderInfoDetailDao.updateByExampleSelective(value, criteria);
        });
        return true;
    }

    public List<OrderInfoForm> getOrderInfo(Criteria example) {
        List<OrderInfoForm> orderInfoForm =
                orderInfoExtraDao.selectByExample4Page(example);
        //把商品详情信息放入到form中
        orderInfoForm.forEach(value -> {
            value.setOrderDetailInfoList(queryDetaiInfo(value.getId()));
            value.setCreateDateString(
                    DateUtil.formatDate(value.getCreateDate(), DateUtil.DATE_FMT_YYYYMMDDHHMMSS));
        });

        return orderInfoForm;
    }

    public List<OrderInfoForm> getDepositInfo(Criteria example) {
        List<OrderInfoForm> orderInfoForm =
                orderInfoExtraDao.selectByExample4Page(example);
        //把商品详情信息放入到form中
        orderInfoForm.forEach(value -> {
            value.setOrderDetailInfoList(queryDetaiInfo(value.getId()));
            value.setCreateDateString(
                    DateUtil.formatDate(value.getCreateDate(), DateUtil.DATE_FMT_YYYYMMDDHHMMSS));
            Long totalPrice = getCountPrice4Deposit(value.getId());
            Long depositPrice = orderInfoDao.selectByPrimaryKey(value.getId()).getPayPrice();
            value.setDepositPrice(
                    depositPrice - totalPrice);
        });

        return orderInfoForm;
    }

    Criteria getCriteria(String customerId, int status, int orderType, int payType, int todayFlag) {
        criteria.clear();
        criteria.put("customer_id", customerId);
        criteria.put("status", status);
        criteria.put("order_type", orderType);
        criteria.put("pay_type", payType);
        if (todayFlag == 1)
            criteria.put("today_flag", 1);
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
    *     
    *  isToday==1? createDay=now() : createDay=null   
    * @param customerId
    * @param payType
    * @param orderType
    * @return
    * @throws IllegalAccessException
    * @throws InvocationTargetException
    */
    List<OrderInfoForm> getOrderInfoByDate(String customerId, int status, int orderType, int payType,
            int isToday) {
        List<OrderInfoForm> orderInfoForm = new ArrayList<OrderInfoForm>();
        List<OrderInfo> orderInfoList =
                orderInfoDao.selectByExample(getCriteria(customerId, status, orderType, payType, isToday));
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

    List<OrderInfoForm> getOrderInfo(String customerId, int status, int orderType, int payType) {
        return getOrderInfoByDate(customerId, status, orderType, payType, 0);
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
        criteria.put("orderId", orderId);
        return orderInfoDetailDao.selectByExample(criteria);
    }

    /**
     * 插入订单信息
     */
    public boolean insertOrder(HttpServletRequest request) {
        //goodInfo=" + goodInfo + "&totalPrice="+totalPrice+"&submitType="+
        String uuid = UUidUtil.getUUId();
        insertSelective(
                insertOrderInfo(request.getParameter("cid"),
                        request.getParameter("submitType").equals("deposit") ? "1" : "0", uuid,
                        request.getParameter("amount")));

        JSONArray json = JSONArray.fromObject(request.getParameter("goodInfo"));
        if (json.size() > 0) {
            for (int i = 0; i < json.size(); i ++) {
                JSONObject job = json.getJSONObject(i);
                insertPresentDetailInfo(
                        getOrderDetailInfo(job.get("id").toString(), job.get("num").toString().trim(), uuid));
            }
        }
        return true;
    }

    /**
     * orderinfo的预备对象
     * @param payType
     * @param uuid
     * @return
     */
    OrderInfo insertOrderInfo(String cid, String payType, String uuid, String amount) {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setId(uuid);
        orderInfo.setPayType(payType);
        orderInfo.setPayPrice(Long.parseLong(amount));
        orderInfo.setCustomerId(cid);
        orderInfo.setOrderType("1");
        orderInfo.setStatus("0");
        orderInfo.setCreateDate(new Date());
        String[] staff = getStaffInfo(cid);
        orderInfo.setReceiverStaffId(staff[0]);
        orderInfo.setPhoneStaffId(staff[1]);
        orderInfo.setDeleteFlag("0");
        orderInfo.setCreateDate(new Date());
        return orderInfo;
    }

    /**
     * add present 2 order_info for review
     */
    public boolean insertPresentOrder(HttpServletRequest request, int normalFlag) {
        criteria.clear();
        String uuid = UUidUtil.getUUId();
        insertSelective(getPresentOrderInfo(request, uuid, normalFlag));
        return insertPresentDetailInfo(
                getOrderDetailInfo(request.getParameter("goodId"), request.getParameter("count"), uuid));
    }

    /**
     * ready for orderdetailInfo
     * add goodID count
     */
    OrderDetailInfo getOrderDetailInfo(String goodId, String count, String uuid) {
        OrderDetailInfo detailInfo = new OrderDetailInfo();
        GoodsInfoForm goodInfo = goodsDao.selectByPrimaryKey(goodId);
        detailInfo.setId(UUidUtil.getUUId());
        detailInfo.setOrderId(uuid);
        detailInfo.setGoodId(goodInfo.getId());
        detailInfo.setGoodSortId(goodInfo.getSortId());
        detailInfo.setGoodSortName(goodInfo.getSortName());
        detailInfo.setGoodType(goodInfo.getType());
        detailInfo.setPrice(goodInfo.getPrice());
        detailInfo.setGoodName(goodInfo.getName());
        detailInfo.setDeleteFlag("0");
        detailInfo.setAmount(Integer.parseInt(count));
        return detailInfo;
    }

    /**
     * ready for PresentOrderInfo
     * 是都正常支付？normalFlag=1：normalFlag=0
     * @param request
     * @param cid
     * @return
     */
    OrderInfo getPresentOrderInfo(HttpServletRequest request, String uuid, int normalFlag) {
        String cid = request.getParameter("customerId");
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setId(uuid);
        orderInfo.setCustomerId(cid);
        orderInfo.setOrderType("4");
        if (normalFlag == 0) {
            orderInfo.setStatus("1");
            orderInfo.setFinanceFlag("1");
            orderInfo.setFinanceDate(new Date());
        } else {
            orderInfo.setStatus("0");
        }
        orderInfo.setCreateDate(new Date());
        String[] staff = getStaffInfo(cid);
        orderInfo.setReceiverStaffId(staff[0]);
        orderInfo.setPhoneStaffId(staff[1]);
        orderInfo.setRemarks(request.getParameter("reason"));
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
    boolean insertPresentDetailInfo(OrderDetailInfo detailInfo) {
        return orderDetailInfoService.insertSelective(detailInfo) > 0 ? true : false;
    }

    /**
     * 当天赠品记录查询
     * order_type='4'and create_date=now()
     */
    public List<OrderInfoForm> selectTodayPresentList(String customerId) {
        return getOrderInfoByDate(customerId, 0, 3, 0, 1);
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

    public JSONObject ajaxStaffPerfors(HttpServletRequest request) {
        // 查询两个地区的员工业绩情况（默认查询上一周,交订金和完成订单的订单业绩）
        JSONObject result = new JSONObject();
        JSONObject dlresult = new JSONObject();
        JSONObject syresult = new JSONObject();

        // 职位类别（1-客服/2-业务员）
        String positionType = request.getParameter("positionType");
        // 员工名称
        String viewStaffName = request.getParameter("staffName");
        // 开始日期
        String startDate = request.getParameter("startDate");
        // 结束日期
        String endDate = request.getParameter("endDate");

        criteria.clear();
        if (StringUtil.isNotBlank(positionType)) {
            criteria.put("positionType", positionType);
            if ("1".equals(positionType)) {
                criteria.put("conditionId", "o.phone_staff_id");
            } else if ("2".equals(positionType)) {
                criteria.put("conditionId", "o.receiver_staff_id");
            }
        }
        if (StringUtil.isNotBlank(viewStaffName)) {
            criteria.put("viewStaffName", viewStaffName);
        }
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

        //查询出大连客服的业绩
        criteria.put("area", "1");
        // 获取大连区域下对应职位类型的所有员工名称
        List<String> dlStaffNames = orderInfoExtraDao.getEmployeeInfos(criteria);
        // 获取大连区域下员工业绩
        List<OrderInfoForm> dlAmounts = orderInfoExtraDao.getStaffPerfors(criteria);

        // 查询出沈阳客服的业绩
        criteria.put("area", "0");
        // 获取沈阳区域下对应职位类型的所有员工名称
        List<String> syStaffNames = orderInfoExtraDao.getEmployeeInfos(criteria);
        // 获取沈阳区域下员工业绩
        List<OrderInfoForm> syAmounts = orderInfoExtraDao.getStaffPerfors(criteria);

        result.put("dlStaffNames", dlStaffNames);
        result.put("syStaffNames", syStaffNames);

        dlresult = geneteJson(dlresult, dlAmounts);
        syresult = geneteJson(syresult, syAmounts);

        result.put("dlResult", dlresult);
        result.put("syResult", syresult);

        return result;
    }

    /**
     * @Title: geneteJson 
     * @Description: 将员工业绩与名称生成Map键值对格式并返回
     * @param result   员工名称和业绩的json格式
     * @param amounts   员工的业绩数据集
     * @return JSONObject
     * @author Xia ZhengWei
     * @date 2016年11月1日 下午11:13:49 
     * @version V1.0
     */
    public JSONObject geneteJson(JSONObject result, List<OrderInfoForm> amounts) {
        if (amounts != null && amounts.size() > 0) {
            for (OrderInfoForm orderInfoForm : amounts) {
                result.put(orderInfoForm.getStaffName(), orderInfoForm.getPerformance());
            }
            return result;
        } else {
            return result;
        }
    }

    /**
     * @Title: selectOrderInfoList 
     * @Description: 查询修改订单列表
     * @param criteria
     * @return 
     * @return List<OrderInfo>
     * @throws
     */
    @Override
    public List<OrderInfo> selectOrderModifyList(Criteria criteria) {
        return orderInfoDao.selectOrderModifyList(criteria);
    }

    /**
     * @Title: selectOrderInfoList 
     * @Description: 查询修改订单列表数量
     * @param criteria
     * @return 
     * @return List<OrderInfo>
     * @throws
     */
    @Override
    public int countOrderModifyList(Criteria criteria) {
        return orderInfoDao.countOrderModifyList(criteria);
    }
    
    /**
     * @Title: selectOrderInfoList 
     * @Description: 查询修改订单列表数量
     * @param criteria
     * @return 
     * @return List<OrderInfo>
     * @throws
     */	
    @Override
    public List<OrderInfo> selectFinanceEveryDay(Criteria criteria) {
    	return orderInfoDao.selectFinanceEveryDay(criteria);
    }
    
    /**
     * @Title: selectOrderInfoList 
     * @Description: 查询修改订单列表数量
     * @param criteria
     * @return 
     * @return int
     * @throws
     */
    @Override
    public int countFinanceEveryDay(Criteria criteria) {
    	return orderInfoDao.countFinanceEveryDay(criteria);
    }
}
