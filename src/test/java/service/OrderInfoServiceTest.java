
package service;

import base.BaseTest;
import com.portal.bean.OrderInfo;
import com.portal.service.OrderInfoService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class OrderInfoServiceTest extends BaseTest {

    @Autowired
    protected OrderInfoService orderInfoServiceImpl;

    @Test
    public void getOrderInfoServiceTest() {
//        OrderInfo orderInfo = orderInfoServiceImpl.selectByPrimaryKey("1");
//        log.debug("getOrderInfoServiceTest : {}", orderInfo);
//        Assert.assertNotNull(orderInfo);
    }
}
