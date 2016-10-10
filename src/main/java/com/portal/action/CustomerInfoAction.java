package com.portal.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.portal.bean.Criteria;
import com.portal.bean.CustomerInfo;
import com.portal.common.util.JsonUtils;
import com.portal.service.CustomerInfoService;

/**
 * @ClassName: CustomerInfoAction 
 * @Description: 电联客户管理
 * @author Miao Wenqiang
 * @date 2016年9月25日 下午2:35:32
 */
@Controller
public class CustomerInfoAction {
	
	@Autowired
	private CustomerInfoService customerInfoService;
	
	/**
	 * @Title: selectCostomerInfoList 
	 * @Description: 用户信息首页
	 * @return 
	 * @return String
	 * @throws
	 */
	@RequestMapping("costomerInfoIndex")
	public String costomerInfoIndex(){
		return "customer/customer_info_index";
	}
	
	/**
	 * @Title: selectCustomerInfoList 
	 * @Description: 查询列表
	 * @param request
	 * @param response 
	 * @return void
	 * @throws
	 */
	@RequestMapping("selectCustomerInfoList")
	public void selectCustomerInfoList(HttpServletRequest request, HttpServletResponse response){
		Criteria criteria = new Criteria();
		criteria.setMysqlLength(Integer.valueOf(request.getParameter("iDisplayLength")));
		criteria.setMysqlOffset(Integer.valueOf(request.getParameter("iDisplayStart")));
		
		criteria.put("phoneStage", request.getParameter("phoneStage"));
		criteria.put("phone", request.getParameter("phone"));
		criteria.put("type", request.getParameter("type"));
		criteria.put("updateDate", request.getParameter("updateDate"));
		
		List<CustomerInfo> resultList = customerInfoService.selectByExample(criteria);
		
		int count = customerInfoService.countByExample(criteria);
		
		JsonUtils.resultJson(resultList, count, response, request);
	}
}
