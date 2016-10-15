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
@RequestMapping("customerInfo")
public class CustomerInfoAction {
	
	@Autowired
	private CustomerInfoService customerInfoService;
	
	/**
	 * @Title: selectCostomerInfoList 
	 * @Description: 用户信息首页
	 * @param request
	 * @return 
	 * @return String
	 * @throws
	 */
	@RequestMapping("costomerInfoIndex")
	public String costomerInfoIndex(HttpServletRequest request){
		String type = request.getParameter("type");
		
		if("0".equals(type)){
			return "customer/blank_info_index";
		}else if("1".equals(type)){
			return "customer/back_info_index";
		}else if("2".equals(type)){
			return "customer/explanation_info_index";
		}else if("3".equals(type)){
			return "customer/complete_info_index";
		}else if("4".equals(type)){
			return "customer/lock_info_index";
		}
		return null;
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
		
		criteria.put("type", request.getParameter("type"));
		criteria.put("phoneStage", request.getParameter("phoneStage"));
		criteria.put("phone", request.getParameter("phone"));
		criteria.put("type", request.getParameter("type"));
		criteria.put("updateDate", request.getParameter("updateDate"));
		criteria.put("startTime", request.getParameter("startTime"));
		criteria.put("endTime", request.getParameter("endTime"));
		criteria.put("backCountS", request.getParameter("backCountS"));
		criteria.put("backCountE", request.getParameter("backCountE"));
		
		List<CustomerInfo> resultList = customerInfoService.selectByExample(criteria);
		
		int count = customerInfoService.countByExample(criteria);
		
		JsonUtils.resultJson(resultList, count, response, request);
	}
	
	/**
	 * @Title: selectCustomerInfoList 
	 * @Description: 查询列表
	 * @param request
	 * @param response 
	 * @return void
	 * @throws
	 */
	@RequestMapping("selectCustomerExList")
	public void selectCustomerExList(HttpServletRequest request, HttpServletResponse response){
		Criteria criteria = new Criteria();
		criteria.setMysqlLength(Integer.valueOf(request.getParameter("iDisplayLength")));
		criteria.setMysqlOffset(Integer.valueOf(request.getParameter("iDisplayStart")));
		
		criteria.put("type", request.getParameter("type"));
		criteria.put("payPriceS", request.getParameter("payPriceS"));
		criteria.put("payPriceE", request.getParameter("payPriceE"));
		criteria.put("createDateS", request.getParameter("createDateS"));
		criteria.put("createDateE", request.getParameter("createDateE"));
		
		List<CustomerInfo> resultList = customerInfoService.selectCustomerExList(criteria);
		
		int count = customerInfoService.countCustomerEx(criteria);
		
		JsonUtils.resultJson(resultList, count, response, request);
	}
}
