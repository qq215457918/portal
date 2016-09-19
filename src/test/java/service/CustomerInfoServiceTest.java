package service;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.portal.bean.CustomerInfo;
import com.portal.dao.CustomerInfoDao;
import com.portal.service.CustomerInfoService;
import com.portal.service.EmployeeInfoService;
import com.portal.service.GoodsInfoService;
import com.portal.service.impl.CustomerInfoServiceImpl;

import base.BaseTest;
import jdk.nashorn.internal.ir.annotations.Ignore;

public class CustomerInfoServiceTest extends BaseTest{
	
//	@Autowired
//    private CustomerInfoService customerInfoService;
	 
    @Before
    public void init() {
        LOG.debug("initializing...");
    }

    @After
    public void destroy() {
        LOG.debug("destroying...");
    }
	
	@Test
	public void selectByPhoneTest(){
		CustomerInfoServiceImpl  customerServiceImpl = new CustomerInfoServiceImpl();
		CustomerInfo  customer =  customerServiceImpl.selectByPhone("15041298725");
		Assert.assertNotNull(customer);
		LOG.debug("--------------->>");
	}
	
	@Ignore
	public void isCustomerTest(){
//		boolean result = customerInfoServiceImpl.isCustomer("15041298725");
//		Assert.assertTrue(result);
	}
	
	@Ignore
	public void  insertSelectiveTest(){
//		CustomerInfo customer = new CustomerInfo();
//		customer.setName("wangergou");
//		customer.setPhone("123123123");
//		int result = customerInfoServiceImpl.insertSelective(customer);
//		
//		LOG.debug("CustomerInfoService customer:{}-----> result:{}"+customer.toString()+result);
//		Assert.assertEquals(1, result);
	}
}
