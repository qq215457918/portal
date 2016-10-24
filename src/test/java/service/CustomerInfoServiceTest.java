package service;

import base.BaseTest;
import com.portal.bean.CustomerInfo;
import com.portal.service.impl.CustomerInfoServiceImpl;
import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

@Slf4j
public class CustomerInfoServiceTest extends BaseTest {

    //	@Autowired
    //    private CustomerInfoService customerInfoService;

    @Test
    public void selectByPhoneTest() {
        CustomerInfoServiceImpl customerServiceImpl = new CustomerInfoServiceImpl();
        CustomerInfo customer = customerServiceImpl.selectByPhone("15041298725");
        Assert.assertNotNull(customer);
        log.debug("--------------->>");
    }

    @Ignore
    public void isCustomerTest() {
        //		boolean result = customerInfoServiceImpl.isCustomer("15041298725");
        //		Assert.assertTrue(result);
    }

    @Ignore
    public void insertSelectiveTest() {
        //		CustomerInfo customer = new CustomerInfo();
        //		customer.setName("wangergou");
        //		customer.setPhone("123123123");
        //		int result = customerInfoServiceImpl.insertSelective(customer);
        //		
        //		LOG.debug("CustomerInfoService customer:{}-----> result:{}"+customer.toString()+result);
        //		Assert.assertEquals(1, result);
    }
}
