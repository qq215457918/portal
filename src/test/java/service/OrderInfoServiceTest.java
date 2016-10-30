
package service;

import base.BaseTest;
import com.portal.bean.OrderInfo;
import com.portal.bean.result.OrderInfoForm;
import com.portal.service.OrderInfoService;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class OrderInfoServiceTest extends BaseTest {

    //    `status` varchar(1) COLLATE utf8_bin DEFAULT NULL COMMENT '订单状态 : 0未支付 1已支付 2已出库 3文交所已审核 4 已完成',
    //    `order_type` varchar(1) COLLATE utf8_bin DEFAULT NULL COMMENT '订单类型 1正常 2退货 3换货',
    //    `pay_type` varchar(1) COLLATE utf8_bin DEFAULT NULL COMMENT '支付类型  0全额支付 1定金支付 2派送支付',
    //    `pay_price` decimal(10,0) DEFAULT NULL COMMENT '订单金额',
    //    `actual_price` decimal(10,0) DEFAULT NULL COMMENT '实际支付金额',
    //    `finance_flag` varchar(45) COLLATE utf8_bin DEFAULT NULL COMMENT '支付状态(财务审核标志)\n0 未支付\n1已支付',
    @Autowired
    protected OrderInfoService orderInfoService;

    @Test
    public void getOrderInfoServiceTest() {
        OrderInfo orderInfo = orderInfoService.selectByPrimaryKey("1");
        log.debug("getOrderInfoServiceTest : {}", orderInfo);
        Assert.assertNotNull(orderInfo);
    }

    //正常订单
    @Test
    public void queryGoodsInfoTest() throws IllegalAccessException, InvocationTargetException {
        List<OrderInfoForm> from = orderInfoService.queryGoodsInfo("1");
        log.debug("getOrderInfoServiceTest : {}", from);
        Assert.assertNotNull(from);
    }

    //退货
    @Test
    public void queryReturnGoodsTest() throws IllegalAccessException, InvocationTargetException {
        List<OrderInfoForm> from = orderInfoService.queryReturnGoodsInfo("1");
        log.debug("getOrderInfoServiceTest : {}", from);
        Assert.assertNotNull(from);
    }

    //换货
    @Test
    public void xchangeReturnGoodsInfoTest() throws IllegalAccessException, InvocationTargetException {
        List<OrderInfoForm> from = orderInfoService.xchangeReturnGoodsInfo("1");
        log.debug("getOrderInfoServiceTest : {}", from);
        Assert.assertNotNull(from);
    }

    //定金内容
    @Test
    public void queryRevokeDepositInfoTest() throws IllegalAccessException, InvocationTargetException {
        List<OrderInfoForm> from = orderInfoService.queryRevokeDepositInfo("1");
        log.debug("getOrderInfoServiceTest : {}", from);
        Assert.assertNotNull(from);
    }
}
