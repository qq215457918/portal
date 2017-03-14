package com.portal.service.impl;

import com.portal.bean.Criteria;
import com.portal.bean.CustomerInfo;
import com.portal.bean.EmployeeInfo;
import com.portal.bean.OrderDetailInfo;
import com.portal.bean.OrderInfo;
import com.portal.bean.SellDailyDetail;
import com.portal.bean.SellGoodsDetail;
import com.portal.bean.result.GoodsInfoForm;
import com.portal.bean.result.OrderDetailInfoForm;
import com.portal.bean.result.OrderFundSettlementForm;
import com.portal.bean.result.OrderInfoForm;
import com.portal.bean.result.OrderInfoFormNew;
import com.portal.bean.result.SellDailyInfoForm;
import com.portal.common.util.DateUtil;
import com.portal.common.util.StringUtil;
import com.portal.common.util.UUidUtil;
import com.portal.dao.OrderDetailInfoDao;
import com.portal.dao.OrderInfoDao;
import com.portal.dao.extra.GoodsDao;
import com.portal.dao.extra.OrderDetailInfoExtraDao;
import com.portal.dao.extra.OrderInfoExtraDao;
import com.portal.dao.extra.SellDailyInfoExtraDao;
import com.portal.service.CustomerInfoService;
import com.portal.service.EmployeeInfoService;
import com.portal.service.OrderDetailInfoService;
import com.portal.service.OrderInfoService;
import com.portal.service.ReceptionInfoService;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
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
    private OrderDetailInfoDao orderDetailInfoDao;

    @Autowired
    private OrderInfoExtraDao orderInfoExtraDao;

    @Autowired
    private GoodsDao goodsDao;

    @Autowired
    private CustomerInfoService customerInfoService;

    @Autowired
    private OrderDetailInfoService orderDetailInfoService;

    @Autowired
    private SellDailyInfoExtraDao sellDailyInfoExreaDao;

    @Autowired
    ReceptionInfoService receptionInfoService;

    @Autowired
    EmployeeInfoService employeeInfoService;

    // 公共查询条件类
    Criteria criteria = new Criteria();

    private static final Logger logger = LoggerFactory.getLogger(OrderInfoServiceImpl.class);

    /**
     * 确认客户登门之后：
     * 修改回购订单状态，并为库房发送一条待审核的信息。
     * @param request
     * @return
     */
    public int updateRepurchaseOrder(HttpServletRequest request) {
        //        OrderInfo record = new OrderInfo();
        //        record.setId(request.getParameter("orderId"));
        //        record.setStatus("0");// 7 回购待确认 ->1 未支付
        //        return updateByPrimaryKeySelective(record);
        OrderDetailInfo detailInfo = orderDetailInfoDao.selectByPrimaryKey(request.getParameter("detailId"));
        OrderInfo orderInfo = orderInfoDao.selectByPrimaryKey(detailInfo.getOrderId());
        orderInfo.setOrderType("5");// 5 回购确认完毕
        orderInfoDao.updateByPrimaryKey(orderInfo);

        detailInfo.setPrice(Long.valueOf(request.getParameter("price")));
        detailInfo.setOrderType("5");// 5 回购确认完毕
        return orderDetailInfoDao.updateByPrimaryKeySelective(detailInfo);
    }

    /**
     * 审批回购确认
     * 找到DetailOrder的信息把状态改为 order_type = 5 和审批的金额
     * @param request
     * @return
     */
    public int updateConfirmRepurchase(HttpServletRequest request) {
        OrderDetailInfo detailInfo = orderDetailInfoDao.selectByPrimaryKey(request.getParameter("detailId"));
        OrderInfo orderInfo = orderInfoDao.selectByPrimaryKey(detailInfo.getOrderId());
        Long price = Long.valueOf(request.getParameter("price"));
        orderInfo.setPayPrice(price);
        orderInfo.setOrderType("5");
        orderInfoDao.updateByPrimaryKey(orderInfo);

        detailInfo.setPrice(price);
        detailInfo.setOrderType("5");// 5 待回购
        return orderDetailInfoDao.updateByPrimaryKeySelective(detailInfo);
    }

    /**
     * 正常回购
     * 找到DetailOrder的信息把状态改为 order_type = 5 修改金额，保存旧金额，加一条新的订单信息
     * @param request
     * @return
     */
    public int updateNormalRepurchase(HttpServletRequest request) {
        OrderDetailInfo detailInfo = orderDetailInfoDao.selectByPrimaryKey(request.getParameter("detailId"));
        OrderInfo orderInfo = orderInfoDao.selectByPrimaryKey(detailInfo.getOrderId());
        String UUid = UUidUtil.getUUId();
        setDeleteFlag(orderInfo);

        addNewOrder(request, UUid, orderInfo.getPhoneStaffId(), "5", orderInfo.getAreaFlag());
        detailInfo.setOldOrderId(detailInfo.getId());
        detailInfo.setOrderId(UUid);
        detailInfo.setOrderType("5");// 5回购订单类型
        detailInfo.setOldPrice(detailInfo.getPrice());
        detailInfo.setPrice(Long.valueOf(request.getParameter("price")));
        deleteOldOrder(orderInfo);
        return orderDetailInfoDao.updateByPrimaryKeySelective(detailInfo);
    }

    /**
     * set orderInfo deleteFlag ='1'
     * @param orderInfo
     */
    public void setDeleteFlag(OrderInfo orderInfo) {
        orderInfo.setDeleteFlag("1");
        orderInfoDao.updateByPrimaryKeySelective(orderInfo);
    }

    public void addNewOrder(HttpServletRequest request, String UUid, String phoneStaffId, String OrderType,
            String areaFlag) {
        String employeeId = (String) request.getSession().getAttribute("userId");
        OrderInfo orderInfoNew = new OrderInfo();
        orderInfoNew.setId(UUid);
        orderInfoNew.setCustomerId(request.getSession().getAttribute("cId").toString());
        orderInfoNew.setOrderType(OrderType);
        orderInfoNew.setPayType("0");
        orderInfoNew.setOrderNumber(StringUtil.getOrderNo());
        orderInfoNew.setReceiverStaffId(employeeId);
        orderInfoNew.setPhoneStaffId(phoneStaffId);
        orderInfoNew.setStatus("0");
        //输入回购金额 price
        orderInfoNew.setPayPrice(Long.valueOf(request.getParameter("price")));
        orderInfoNew.setActualPrice(0L);
        orderInfoNew.setRemarks(request.getParameter("reason"));
        orderInfoNew.setCreateDate(new Date());
        orderInfoNew.setCreateId(employeeId);
        orderInfoNew.setDeleteFlag("0");
        orderInfoNew.setOrderNumber(StringUtil.getOrderNo());
        orderInfoNew.setAreaFlag(areaFlag);
        orderInfoDao.insertSelective(orderInfoNew);
    }

    /**
     * 新增 一条带回购的订单order_info，订单状态为 order_type = 7 
     *  
     * @param request
     * @return
     */
    public int updateSpecialRepurchase(HttpServletRequest request) {
        OrderDetailInfo detailInfo = orderDetailInfoDao.selectByPrimaryKey(request.getParameter("detailId"));
        OrderInfo orderInfo = orderInfoDao.selectByPrimaryKey(detailInfo.getOrderId());
        String UUid = UUidUtil.getUUId();

        addNewOrder(request, UUid, orderInfo.getPhoneStaffId(), "7", orderInfo.getAreaFlag());
        detailInfo.setOldOrderId(detailInfo.getOrderId());
        detailInfo.setOrderId(UUid);
        detailInfo.setOrderType("7");
        detailInfo.setOldPrice(detailInfo.getPrice());
        detailInfo.setPrice(Long.valueOf(request.getParameter("price")));
        detailInfo.setAmount(Integer.valueOf(request.getParameter("count")));
        deleteOldOrder(orderInfo);
        return orderDetailInfoDao.updateByPrimaryKeySelective(detailInfo);
    }

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
        //        updateCancelDetail(orderId);        //更新detail的数量为负
        return orderInfoDao.updateByPrimaryKeySelective(orderCriteria) > 0 ? true : false;
    }

    /**
     * 修改订单为退货订单
     * 2016-12-11新修改，如果订单中多个商品，可以实现单个商品进行退货
     * 1、选择商品进行退货，order_info 新增一条记录.
     * 2、把detail_info的orderId修改为新订单
     * 3、新增detail_info的old_order_id记录原有订单信息
     * @return
     */
    public boolean updateOrderReturn(HttpServletRequest request) {
        boolean result = false;
        String cid = request.getSession().getAttribute("cId").toString();
        String receiverStaffId = request.getSession().getAttribute("userId").toString();
        String UUid = UUidUtil.getUUId();

        OrderDetailInfo detailInfo =
                orderDetailInfoDao.selectByPrimaryKey(request.getParameter("detailId"));
        OrderInfo orderInfo = orderInfoDao.selectByPrimaryKey(detailInfo.getOrderId());

        detailInfo.setOldOrderId(detailInfo.getOrderId());
        detailInfo.setOrderId(UUid);
        detailInfo.setOrderType("2");
        detailInfo.setOldOrderId(orderInfo.getId());
        result = orderDetailInfoDao.updateByPrimaryKeySelective(detailInfo) > 0 ? true : false;

        OrderInfo orderInfoNew = new OrderInfo();
        orderInfoNew.setId(UUid);
        orderInfoNew.setCustomerId(cid);
        orderInfoNew.setPayType("0");
        orderInfoNew.setOrderType("2");
        orderInfoNew.setOrderNumber(StringUtil.getOrderNo());
        orderInfoNew.setReceiverStaffId(receiverStaffId);
        orderInfoNew.setPhoneStaffId(orderInfo.getPhoneStaffId());
        orderInfoNew.setStatus("0");
        orderInfoNew.setPayPrice(detailInfo.getPrice());
        orderInfoNew.setActualPrice(0L);
        orderInfoNew.setCreateDate(new Date());
        orderInfoNew.setCreateId(receiverStaffId);
        orderInfoNew.setDeleteFlag("0");
        orderInfoNew.setAreaFlag(orderInfo.getAreaFlag());
        result = orderInfoDao.insertSelective(orderInfoNew) > 0 ? true : false;
        deleteOldOrder(orderInfo);
        return result;
    }

    public void deleteOldOrder(OrderInfo orderInfo) {
        orderInfo.setDeleteFlag("1");
        orderInfoDao.updateByPrimaryKeySelective(orderInfo);
    }

    public void updateReturnDetail(String orderId) {
        Criteria criteria = new Criteria();
        criteria.put("orderId", orderId);
        criteria.put("deleteFlag", "0");
        orderDetailInfoDao.selectByExample(criteria).forEach(value -> {
            value.setId(UUidUtil.getUUId());
            value.setAmount(~value.getAmount() + 1);
            value.setUpdateDate(new Date());
            value.setOrderType("2");//修改订单状态为“退货”
            orderDetailInfoDao.insertSelective(value);
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
        orderCriteria.setWarehouseFlag("");
        orderCriteria.setCultureFlag("");
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
        orderCriteria.setStatus("0");
        orderCriteria.setActualPrice(null);
        //        orderInfoDetailDao.selectByExample(criteria);
        //        updateCancelDetail(orderId);
        return orderInfoDao.updateByPrimaryKeySelective(orderCriteria) > 0 ? true : false;
    }

    /**
     * 修改订单详情的数量为负
     * @param orderId
     * @return
     */
    /*    public boolean updateCancelDetail(String orderId) {
        Criteria criteria = new Criteria();
        criteria.put("orderId", orderId);
        criteria.put("deleteFlag", "0");
        orderDetailInfoDao.selectByExampleOld(criteria).forEach(value -> {
            value.setAmount(~value.getAmount() + 1);
            orderDetailInfoDao.updateByPrimaryKeySelective(value);
        });
        return true;
    }*/

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

    public List<OrderInfoFormNew> getOrderInfoNew(Criteria example) {
        List<OrderInfoFormNew> orderInfoForm =
                orderInfoExtraDao.selectByExampleNew4Page(example);
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
            Long depositPrice = value.getPayPrice() == null ? 0L : value.getPayPrice();
            value.setDepositPrice(
                    totalPrice - depositPrice);
            value.setTotalPrice(totalPrice);
        });
        return orderInfoForm;
    }

    Criteria getCriteria(String customerId, int status, int orderType, int payType) {
        criteria.clear();
        criteria.put("customerId", customerId);
        criteria.put("status", status);
        criteria.put("orderType", orderType);
        criteria.put("payType", payType);
        criteria.put("deleteFlag", "0");
        criteria.setOrderByClause("create_date desc");
        criteria.setMysqlLength(5);
        return criteria;
    }

    /**
    * 根绝类型获取订单信息 
    *    `status` varchar(1) COLLATE utf8_bin DEFAULT NULL COMMENT '订单状态 : 0未支付 1已支付 2已出库 3文交所已审核 4 已完成',
    *     `order_type` varchar(1) COLLATE utf8_bin DEFAULT NULL COMMENT '订单类型 1正常 2退货 3换货 4赠品',
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
    List<OrderInfoForm> getOrderInfoByDate(String customerId, int status, int orderType, int payType) {
        List<OrderInfoForm> orderInfoResult =
                orderInfoExtraDao
                        .selectByExample4Page(getCriteria(customerId, status, orderType, payType));
        //把商品详情信息放入到form中
        orderInfoResult.forEach(value -> {
            value.setOrderDetailInfoList(queryDetaiInfo(value.getId()));
            value.setCreateDateString(
                    DateUtil.formatDate(value.getCreateDate(), DateUtil.DATE_FMT_YYYYMMDDHHMMSS));
        });

        return orderInfoResult;
    }

    List<OrderInfoForm> getOrderInfo(String customerId, int status, int orderType, int payType) {
        return getOrderInfoByDate(customerId, status, orderType, payType);
    }

    List<OrderInfoForm> getNormalOrderInfo(String customerId, int orderType, int payType) {
        return getOrderInfo(customerId, 4, orderType, payType);
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
        return orderDetailInfoDao.selectByExample(criteria);
    }

    /**
     * 插入订单信息
     * 
     *modify： 修改为可以拆单，把礼物的商品单独拿出来，生成订单。
     *1.首先判断订单里面是否有礼品
     *2.如果 有则 吧good_info 里面的赠品信息拿出
     *3.生成单独的礼品处理方法类
     *
     *0110modify：新增reception_info 中添加orderID 和presentOrderID
     *0122modify:新增功能添加customer_info 的product（商品）和gift（赠品）
     * @throws InterruptedException 
     */
    public boolean insertOrder(HttpServletRequest request) {
        //goodInfo=" + goodInfo + "&totalPrice="+totalPrice+"&submitType="+
        String uuid = UUidUtil.getUUId();
        String puuid = UUidUtil.getUUId();//赠品的单独订单
        JSONArray json = JSONArray.fromObject(request.getParameter("goodInfo"));
        boolean hasGoods = false;
        boolean hasPresent = false;
        Long amount = 0L;
        String cid = request.getSession().getAttribute("cId").toString();
        StringBuffer presentNameList = new StringBuffer();
        StringBuffer giftNameList = new StringBuffer();//客户基本信息表用的赠品信息
        StringBuffer productNameList = new StringBuffer();//客户基本信息表用的商品信息

        boolean deposit = request.getParameter("submitType").equals("deposit");
        if (json.size() > 0) {
            for (int i = 0; i < json.size(); i ++) {
                JSONObject job = json.getJSONObject(i);
                String goodID = job.get("id").toString();
                //通过查询goodID 查看goodType
                GoodsInfoForm goodsInfo = goodsDao.selectByPrimaryKey(goodID);
                if (goodsInfo == null) {
                    return false;
                }
                //if goodType=1：赠品单独插入赠品表 
                if (goodsInfo.getType().equals("1")) {
                    hasPresent = true;
                    insertPresentDetailInfo(//查询商品信息插入到订单详情表中                    
                            getOrderDetailInfo(goodID, job.get("num").toString().trim(), puuid,
                                    "6"));//6 为VIP赠品
                    updateGoodsCount(goodID);
                    presentNameList.append(goodsInfo.getName() + ",");
                    giftNameList.append(goodsInfo.getName() + "\\n");
                } else {//其他类型单独插入goodType
                    hasGoods = true;
                    insertPresentDetailInfo(//查询商品信息插入到订单详情表中                    
                            getOrderDetailInfo(goodID, job.get("num").toString().trim(), uuid,
                                    "1"));//1 为正常订单
                    updateGoodsCount(goodID);
                    productNameList.append(goodsInfo.getName() + "\\n");
                }
            }

            if (hasPresent) {
                insertSelective(insertOrderInfo(cid, deposit ? "1" : "0", puuid, 0L, request));//礼品的订单金额为0
                //在接待表中添加赠品订单 puuid
                receptionInfoService.updatePresentOrderID(puuid, presentNameList.toString(), cid);
                //在客户信息中家赠品信息
                customerInfoService.updateGift(cid, giftNameList.toString());
            }
            try {
                Thread.sleep(1000);//防止赠品表和订单表的id相同
            } catch (InterruptedException e) {
                logger.warn("Unexpected exception:", e);
            }
            if (hasGoods) {
                amount = Long.valueOf(request.getParameter("amount")); //累加订单详情的金额
                insertSelective(insertOrderInfo(cid, deposit ? "1" : "0", uuid, amount, request));
                //在接待表中添加赠品订单 uuid
                receptionInfoService.updateOrderID(uuid, cid);
                if (!deposit) {
                    //在客户信息中家商品信息
                    customerInfoService.updateProduct(cid, productNameList.toString(), amount.toString());
                }
            }
        }
        return true;
    }

    //减少商品的购买数量
    void updateGoodsCount(String goodID) {
        goodsDao.updateGoodsCount(goodID);
    }

    /**
     * orderinfo的预备对象
     * @param payType
     * @param uuid
     * @return
     */
    OrderInfo insertOrderInfo(String cid, String payType, String uuid, Long amount,
            HttpServletRequest request) {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setId(uuid);
        orderInfo.setOrderNumber(StringUtil.getOrderNo());
        orderInfo.setPayType(payType);
        orderInfo.setPayPrice(amount);
        orderInfo.setCustomerId(cid);
        orderInfo.setOrderType("1");
        orderInfo.setStatus("0");
        orderInfo.setCreateDate(new Date());
        String[] staff = getStaffInfo(cid, request);
        orderInfo.setReceiverStaffId(staff[0]);
        orderInfo.setPhoneStaffId(staff[1]);
        orderInfo.setAreaFlag(staff[2]);
        orderInfo.setDeleteFlag("0");
        orderInfo.setCreateDate(new Date());
        return orderInfo;
    }

    /**
     * 提交需要审批的礼品信息
     * order_type=6 vip 赠品 4 正常正品
     * 20170309 add //在客户信息中家赠品信息
     * customerInfoService.updateGift(cid, giftNameList.toString());
     */
    public boolean insertPresentOrder(HttpServletRequest request, int normalFlag, Boolean isVIP) {
        String uuid = UUidUtil.getUUId();
        String goodStr = request.getParameter("goodId");
        if (StringUtils.isEmpty(goodStr)) {
            return false;
        }
        String count = (String) request.getParameter("count");
        for (String goodId : goodStr.split(",")) {
            insertPresentDetailInfo(
                    getOrderDetailInfo(goodId, StringUtils.isEmpty(count) ? "1" : count, uuid,
                            isVIP ? "6" : "4"));
            updateGoodsCount(goodId);
        }
        insertSelective(getPresentOrderInfo(request, uuid, normalFlag, isVIP));
        customerInfoService.updateGift(request.getSession().getAttribute("cId").toString(),
                request.getParameter("goodName").replace(",", "\n"));
        return true;
    }

    /**
     * ready for orderdetailInfo
     * add goodID count
     */
    OrderDetailInfo getOrderDetailInfo(String goodId, String count, String uuid, String orderType) {
        OrderDetailInfo detailInfo = new OrderDetailInfo();
        if (goodId.indexOf(",") > -1) {
            goodId = goodId.replace(",", "");
        }
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
        detailInfo.setOrderType(orderType);
        if (StringUtil.isNull(count))
            count = "1";
        detailInfo.setAmount(Integer.parseInt(count));
        return detailInfo;
    }

    /**
     * ready for PresentOrderInfo
     * normalFlag = 0 是正常的礼品领取
     * @param request
     * @param cid
     * @return
     */
    OrderInfo getPresentOrderInfo(HttpServletRequest request, String uuid, int normalFlag, Boolean isVIP) {
        String cid = request.getSession().getAttribute("cId").toString();
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setId(uuid);
        orderInfo.setOrderNumber(StringUtil.getOrderNo());
        orderInfo.setCustomerId(cid);

        if (isVIP)
            orderInfo.setOrderType("6");
        else
            orderInfo.setOrderType("4");

        if (normalFlag == 0) //一般赠品信息，不需要审批
            orderInfo.setStatus("1");
        else
            orderInfo.setStatus("5");

        orderInfo.setCreateDate(new Date());
        String[] staff = getStaffInfo(cid, request);
        orderInfo.setReceiverStaffId(staff[0]);
        orderInfo.setPhoneStaffId(staff[1]);
        orderInfo.setPayType("0");//礼品订单都是全额支付
        orderInfo.setDeleteFlag("0");
        orderInfo.setRemarks(request.getParameter("reason"));
        orderInfo.setAreaFlag(staff[2]);
        return orderInfo;
    }

    /**
     * 获取接待人员信息
     * 加判断，如果customer的没有接待人员则获取当前session登陆的employeeInfo
     * @param customerId
     * @return
     */
    String[] getStaffInfo(String customerId, HttpServletRequest request) {
        CustomerInfo cInfo = customerInfoService.selectByPrimaryKey(customerId);
        EmployeeInfo employee = new EmployeeInfo();
        if (StringUtils.isEmpty(cInfo.getReceiverStaffId())) {
            employee = (EmployeeInfo) request.getSession().getAttribute("userInfo");
        } else {
            employee = employeeInfoService.selectByPrimaryKey(cInfo.getReceiverStaffId());
        }
        String[] staff =
                { employee.getId(), cInfo.getPhoneStaffId(), employee.getOrganizationId() };
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
    public List<OrderInfoForm> selectPresentList(String customerId) {
        //getCriteria(customerId, 1, 4, 0)
        criteria.clear();
        criteria.put("customerId", customerId);
        criteria.put("payType", 0);
        criteria.put("deleteFlag", "0");
        criteria.put("presentCheck", true);

        criteria.setOrderByClause("create_date desc");
        criteria.setMysqlLength(5);
        List<OrderInfoForm> orderInfoResult =
                orderInfoExtraDao
                        .selectByExample4Page(criteria);
        //把商品详情信息放入到form中
        orderInfoResult.forEach(value -> {
            value.setOrderDetailInfoList(queryDetaiInfo(value.getId()));
            value.setCreateDateString(
                    DateUtil.formatDate(value.getCreateDate(), DateUtil.DATE_FMT_YYYYMMDDHHMMSS));
        });

        return orderInfoResult;
        // return getOrderInfoByDate(customerId, 1, 4, 0);
    }

    /**
     * order_type = 4 赠品  6 VIP赠品
     * finance_flag = 0
     * 审批完毕审批finance_flag为 1 
     * 查询需要审批的礼品信息
     * @param customerId
     * @return
     */
    public List<OrderInfoFormNew> updateCheckPresentList(Criteria example) {
        return orderInfoExtraDao.selectByExampleNew4Page(example);
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
        String endDate = request.getParameter("endDate");

        criteria.clear();
        if (StringUtil.isNotBlank(startDate)) {
            criteria.put("startDate2", startDate);
        } else {
            // 如果开始时间为空, 则取上周一为开始时间
            startDate = DateUtil.formatDate(DateUtil.getLastWeekMonday(new Date()), "yyyy-MM-dd");
            criteria.put("startDate2", startDate);
        }
        if (StringUtil.isNotBlank(endDate)) {
            criteria.put("endDate2", endDate);
        } else {
            // 如果结束时间为空,判断开始日期是否为空
            // 如果开始日期为空, 则默认结束日期为上周日
            // 如果开始日期不为空, 则结束日期为开始日期后6天的日期
            if (StringUtil.isNotBlank(startDate)) {
                Date date = DateUtil.parseDate(startDate, "yyyy-MM-dd");
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                endDate = DateUtil.formatDate(DateUtil.getLaterSixDate(cal, 6), "yyyy-MM-dd");
                criteria.put("endDate2", endDate);
            } else {
                endDate = DateUtil.formatDate(DateUtil.getLastWeekSunday(new Date()), "yyyy-MM-dd");
                criteria.put("endDate2", endDate);
            }
        }

        // 将查询日期转换成Calendar
        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();
        start.setTime(DateUtil.parseDate(startDate, "yyyy-MM-dd"));
        end.setTime(DateUtil.parseDate(endDate, "yyyy-MM-dd"));

        // 获取两个日期之间的所有日期
        List<String> dates = DateUtil.getDates(start, end);

        // 获取指定日期内内各地区的业绩
        Map<String, Integer> counts = orderInfoExtraDao.getClinchPerfors(criteria);

        //查询出大连指定日期内的业绩
        criteria.put("area", "1");
        List<OrderInfoForm> dlAmountsList = orderInfoExtraDao.getDayAndPerfors(criteria);

        //生成大连业绩
        List<Integer> dlAmounts = generateAmountsList(dates, dlAmountsList);

        // 查询出沈阳指定日期内的业绩
        criteria.put("area", "0");
        List<OrderInfoForm> syAmountsList = orderInfoExtraDao.getDayAndPerfors(criteria);

        //生成沈阳业绩
        List<Integer> syAmounts = generateAmountsList(dates, syAmountsList);

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

        result.put("dates", dates);
        result.put("dlResult", dlAmounts);
        result.put("syResult", syAmounts);

        return result;
    }

    /**
     * @Title: generateAmountsList 
     * @Description: 生成报表需要的线形图数据
     * @param dates  查询日期条件所包含的所有日期
     * @param amounts   业绩  
     * @return List<Integer>
     * @author Xia ZhengWei
     * @date 2016年11月24日 下午11:54:07 
     * @version V1.0
     */
    private List<Integer> generateAmountsList(List<String> dates, List<OrderInfoForm> amounts) {
        Map<String, Integer> amountsMap = new HashMap<String, Integer>();
        if (amounts.size() > 0) {
            for (OrderInfoForm orderInfoForm : amounts) {
                String creatdate = DateUtil.formatDate(orderInfoForm.getCreateDate(), "yyyy-MM-dd");
                amountsMap.put(creatdate, orderInfoForm.getPerformance());
            }
        }
        List<Integer> dateAmounts = new ArrayList<Integer>();
        for (int idx = 0; idx < dates.size(); idx ++) {
            if (amountsMap.get(dates.get(idx)) != null) {
                dateAmounts.add(amountsMap.get(dates.get(idx)));
            } else {
                dateAmounts.add(0);
            }
        }
        return dateAmounts;
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

    public JSONObject getSellDaily(HttpServletRequest request) {
        logger.info("获取各地区的销售日报表数据");
        JSONObject result = new JSONObject();
        // 所属区域
        String area = request.getParameter("area");

        // 查询日期
        String startDate = request.getParameter("startDate");

        criteria.clear();
        if (StringUtil.isNotBlank(area)) {
            criteria.put("area", area);
        }
        if (StringUtil.isNotBlank(startDate)) {
            criteria.put("startDate", startDate);
            criteria.put("endDate",
                    DateUtil.formatDate(DateUtil.parseDate(startDate, "yyyy-MM-dd"), "yyyy-MM-dd 23:59:59"));
        } else {
            startDate = DateUtil.formatDate(new Date(), "yyyy-MM-dd");
            // 为空, 默认查询当天的数据
            criteria.put("startDate", startDate);
            criteria.put("endDate", DateUtil.formatDate(new Date(), "yyyy-MM-dd 23:59:59"));
        }

        // 如果根据传递的日期从数据库表中查询数据，查到了直接显示，差不多则统计;
        // 查询之前统计的数据
        criteria.put("reportDate", startDate);
        // 获取数据库中之前保存的数据
        List<SellDailyInfoForm> sellDailies = sellDailyInfoExreaDao.getSellDailiesByCondition(criteria);
        if (sellDailies != null && sellDailies.size() > 0) {
            // 获取销售商品信息
            List<SellGoodsDetail> goodsDetail = sellDailies.get(0).getSellGoodsDetails();
            // 获取销售结算明细
            List<SellDailyDetail> dailyDetail = sellDailies.get(0).getSellDailyDetails();
            result.put("type", "search");
            result.put("goodsList", goodsDetail);
            result.put("clearing", dailyDetail);
        } else {
            // 获取销售商品信息
            List<OrderDetailInfoForm> goodsList = orderInfoExtraDao.getSellGoods(criteria);
            // 获取销售结算明细
            List<OrderInfoForm> clearingList = orderInfoExtraDao.getSellclearingDetail(criteria);
            // 获取定金退款
            List<Integer> depositRefund = orderInfoExtraDao.getDepositRefund(criteria);
            // 获取定金回款
            List<Integer> depositReturn = orderInfoExtraDao.getDepositReturn(criteria);

            result.put("type", "compile");
            result.put("goodsList", goodsList);
            result.put("clearing", clearingList);
            result.put("depositRefund", depositRefund);
            result.put("depositReturn", depositReturn);
        }
        return result;
    }

    public JSONObject ajaxCreditCardDepositDetail(HttpServletRequest request, JSONObject results) {
        logger.info("获取各地区的销售日报表数据");
        // 所属区域
        String area = request.getParameter("area");
        // 查询日期
        String startDate = request.getParameter("startDate");

        criteria.clear();
        if (StringUtil.isNotBlank(area)) {
            criteria.put("area", area);
        }
        if (StringUtil.isNotBlank(startDate)) {
            criteria.put("startDate", startDate);
            criteria.put("endDate",
                    DateUtil.formatDate(DateUtil.parseDate(startDate, "yyyy-MM-dd"), "yyyy-MM-dd 23:59:59"));
        } else {
            // 为空, 默认查询当天的数据
            criteria.put("startDate", DateUtil.formatDate(new Date(), "yyyy-MM-dd"));
            criteria.put("endDate", DateUtil.formatDate(new Date(), "yyyy-MM-dd 23:59:59"));
        }
        // 设置排序
        criteria.setOrderByClause("p.payment_account_name");
        // 获取总条数
        int totalRecord = orderInfoExtraDao.getCountsCardDetail(criteria);
        // 获取数据
        List<OrderFundSettlementForm> depositDetail = orderInfoExtraDao.getCreditCardDepositDetail(criteria);

        results.put("sEcho", request.getParameter("sEcho"));
        results.put("iTotalRecords", totalRecord);
        results.put("iTotalDisplayRecords", totalRecord);
        results.put("aaData", depositDetail);
        return results;
    }

    public List<OrderFundSettlementForm> getCreditCardDepositDetail(Criteria criteria) {
        return orderInfoExtraDao.getCreditCardDepositDetail(criteria);
    }

    public int getOrderCounts(Criteria criteria) {
        return orderInfoExtraDao.getOrderCounts(criteria);
    }

    public int getOrderAmounts(Criteria criteria) {
        return orderInfoExtraDao.getOrderAmounts(criteria);
    }

    public int getOrderGoodsCounts(Criteria criteria) {
        return orderInfoExtraDao.getOrderGoodsCounts(criteria);
    }

    @Override
    public OrderInfo selectPirntInfoById(String orderId) {
        return orderInfoDao.selectPirntInfoById(orderId);
    }
}
