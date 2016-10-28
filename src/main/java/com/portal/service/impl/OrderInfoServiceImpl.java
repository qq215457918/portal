package com.portal.service.impl;

import com.portal.bean.Criteria;
import com.portal.bean.OrderDetailInfo;
import com.portal.bean.OrderInfo;
import com.portal.bean.result.OrderInfoForm;
import com.portal.dao.OrderDetailInfoDao;
import com.portal.dao.OrderInfoDao;
import com.portal.service.OrderInfoService;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
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

    private static final Logger logger = LoggerFactory.getLogger(OrderInfoServiceImpl.class);

    Criteria getCriteria(String customerId, int status, int orderType, int payType) {
        Criteria example = new Criteria();
        example.put("customer_id", customerId);
        example.put("status", status);
        example.put("order_type", orderType);
        example.put("pay_type", payType);
        example.setOrderByClause("create_date");
        example.setMysqlLength(5);
        return example;
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
    OrderInfoForm getOrderInfo(String customerId, int status, int orderType, int payType) {
        OrderInfoForm orderInfoForm = new OrderInfoForm();
        List<OrderInfo> orderInfoList =
                orderInfoDao.selectByExample(getCriteria(customerId, status, orderType, payType));
        try {
            BeanUtils.copyProperties(orderInfoForm, orderInfoList.get(0));
        } catch (IllegalAccessException e) {
            logger.warn("Unexpected exception:", e);
        } catch (InvocationTargetException e) {
            logger.warn("Unexpected exception:", e);

        }
        orderInfoForm.setOrderDetailInfoList(queryDetaiInfo(orderInfoList.get(0).getId()));
        return orderInfoForm;
    }

    OrderInfoForm getNormalOrderInfo(String customerId, int orderType, int payType) {
        return getOrderInfo(customerId, 1, orderType, payType);
    }

    /**
     * 查询商品订单 by customerId
     * by meng.yue
     * @return
     * @throws InvocationTargetException 
     * @throws IllegalAccessException 
     */
    public OrderInfoForm queryGoodsInfo(String customerId) {
        return getNormalOrderInfo(customerId, 1, 0);
    }

    /**
     * 查看已经支付的定金订单
     * @param customerId
     * @return
     * @throws InvocationTargetException 
     * @throws IllegalAccessException 
     */
    public OrderInfoForm queryRevokeDepositInfo(String customerId) {
        return getNormalOrderInfo(customerId, 1, 1);
    }

    /**
     * 退货订单
     * @param customerId
     * @return
     * @throws InvocationTargetException 
     * @throws IllegalAccessException 
     */
    public OrderInfoForm queryReturnGoodsInfo(String customerId) {
        return getNormalOrderInfo(customerId, 2, 1);
    }

    /**
     * 换货订单
     * @param customerId
     * @return
     * @throws InvocationTargetException 
     * @throws IllegalAccessException 
     */
    public OrderInfoForm xchangeReturnGoodsInfo(String customerId) {
        return getNormalOrderInfo(customerId, 3, 1);
    }

    //undone order
    //未完成的订单信息
    public OrderInfoForm undoneOrder(String customerId) {
        return getOrderInfo(customerId, 0, 3, 1);
    }

    /**
     * 获取 订单详情信息
     * @param orderId
     * @return
     */
    List<OrderDetailInfo> queryDetaiInfo(String orderId) {
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
}
