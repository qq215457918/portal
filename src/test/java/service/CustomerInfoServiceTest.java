package service;

import base.BaseTest;
import com.portal.bean.CustomerInfo;
import com.portal.bean.result.CustomerSimpleInfoForm;
import com.portal.common.util.UUidUtil;
import com.portal.service.CustomerInfoService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class CustomerInfoServiceTest extends BaseTest {

    @Autowired
    private CustomerInfoService customerInfoService;

    @Ignore
    public void selectByPhoneTest() {
        CustomerInfo customer = customerInfoService.selectByPhone("15041298725");
        Assert.assertNotNull(customer);
        //log.debug("--------------->>");
    }

    @Ignore
    public void isCustomerTest() {
        boolean result = customerInfoService.isCustomer("15041298725");
        Assert.assertTrue(result);
    }

    @Ignore
    public void insertSelectiveTest() {
        CustomerInfo customer = new CustomerInfo();
        customer.setId(UUidUtil.getUUId());
        customer.setName("wangergou");
        customer.setPhone("123123123");
        int result = customerInfoService.insertSelective(customer);

        //log.debug("CustomerInfoService customer:{}-----> result:{}" + customer.toString() + result);
        Assert.assertEquals(1, result);
    }

    @Test
    public void getFristQueryInfoTest() {
        CustomerSimpleInfoForm from = customerInfoService.getFristQueryInfo("15041298725");
        Assert.assertNotNull(from);
    }
}
