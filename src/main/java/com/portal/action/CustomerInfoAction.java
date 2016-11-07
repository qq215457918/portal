package com.portal.action;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.portal.bean.Criteria;
import com.portal.bean.CustomerInfo;
import com.portal.common.util.ExportBean;
import com.portal.common.util.ExportExcelJxl;
import com.portal.common.util.ImportExcelUtil;
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
	
	@Autowired
	private ImportExcelUtil importExcelUtil;
	
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
		
		criteria.put("type", request.getParameter("costomerType"));
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
	
	/**
	 * @throws IOException 
	 * @Title: exportCustomer 
	 * @Description: 导出用户
	 * @param request
	 * @param response 
	 * @return void
	 * @throws
	 */
	@RequestMapping("exportCustomer")
	public void exportCustomer(HttpServletRequest request, HttpServletResponse response) throws IOException{
		Criteria criteria = new Criteria();
		
		String type = request.getParameter("type");
		
		criteria.put("type", type);
		criteria.put("phoneStage", request.getParameter("phoneStage"));
		criteria.put("phone", request.getParameter("phone"));
		criteria.put("type", request.getParameter("type"));
		criteria.put("updateDate", request.getParameter("updateDate"));
		criteria.put("startTime", request.getParameter("startTime"));
		criteria.put("endTime", request.getParameter("endTime"));
		criteria.put("backCountS", request.getParameter("backCountS"));
		criteria.put("backCountE", request.getParameter("backCountE"));
		
		List<CustomerInfo> resultList = customerInfoService.selectByExample(criteria);
		
		try{
			@SuppressWarnings("deprecation")
			String filePath = request.getRealPath("resources/excel");
			
			ExportBean excelBean = new ExportBean();
			makeData(type, resultList, excelBean);
			excelBean.setExportMode(0);
			excelBean.setSourceFile(filePath + "\\blank_excel.xls");
			new ExportExcelJxl(response, excelBean);
			
			if(null != resultList && resultList.size() > 0){
				customerInfoService.updateExportDate(resultList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	
	private void makeData(String type, List<CustomerInfo> paramList, ExportBean excelBean){
		if("0".equals(type)){
			Object[][] data = new Object[paramList.size()+1][7];
			
			int i = 1;
			data[0] = new Object[]{"序号", "电话", "电话1", "访问时间", "客户类型", "上次电联时间", "上次倒出时间"};
			for(CustomerInfo ci : paramList){
				data[i][0] = String.valueOf(i);
				data[i][1] = null == ci.getPhone()?"":ci.getPhone();
				data[i][2] = null == ci.getPhone2()?"":ci.getPhone2();
				data[i][3] = null == ci.getVisitDate()?"":ci.getVisitDate();
				data[i][4] = "空白用户";
				data[i][5] = null == ci.getRecentVisitDate()?"":ci.getRecentVisitDate();
				data[i][6] = null == ci.getRecentExportDate()?"":ci.getRecentExportDate();
				i++;
			}
			excelBean.setData(data);
			excelBean.setExcelName("空白用户");
			excelBean.setSheetName("空白用户");
		}else if("1".equals(type)){
			Object[][] data = new Object[paramList.size()+1][6];
			
			int i = 1;
			data[0] = new Object[]{"序号", "电话", "电话1", "姓名", "登门次数", "最近登门时间"};
			for(CustomerInfo ci : paramList){
				data[i][0] = String.valueOf(i);
				data[i][1] = null == ci.getPhone()?"":ci.getPhone();
				data[i][2] = null == ci.getPhone2()?"":ci.getPhone2();
				data[i][3] = null == ci.getName()?"":ci.getName();
				data[i][4] = null == ci.getTransactionAmount()?"":ci.getTransactionAmount();
				data[i][5] = null == ci.getRecentVisitDate()?"":ci.getRecentVisitDate();
				i++;
			}
			excelBean.setData(data);
			excelBean.setExcelName("登门用户");
			excelBean.setSheetName("登门用户");
		}else if("2".equals(type)){
			Object[][] data = new Object[paramList.size()+1][7];
			
			int i = 1;
			data[0] = new Object[]{"序号", "电话", "电话1", "访问时间", "客户类型", "上次访问时间", "上次倒出时间"};
			for(CustomerInfo ci : paramList){
				data[i][0] = String.valueOf(i);
				data[i][1] = null == ci.getPhone()?"":ci.getPhone();
				data[i][2] = null == ci.getPhone2()?"":ci.getPhone2();
				data[i][3] = null == ci.getVisitDate()?"":ci.getVisitDate();
				data[i][4] = "说明会用户";
				data[i][5] = null == ci.getRecentVisitDate()?"":ci.getRecentVisitDate();
				data[i][6] = null == ci.getRecentExportDate()?"":ci.getRecentExportDate();
				i++;
			}
			excelBean.setData(data);
			excelBean.setExcelName("说明会用户");
			excelBean.setSheetName("说明会用户");
		}else if("3".equals(type)){
			Object[][] data = new Object[paramList.size()+1][5];
			
			int i = 1;
			data[0] = new Object[]{"序号", "电话", "电话1", "订单总金额", "最后成单时间"};
			for(CustomerInfo ci : paramList){
				data[i][0] = String.valueOf(i);
				data[i][1] = null == ci.getPhone()?"":ci.getPhone();
				data[i][2] = null == ci.getPhone2()?"":ci.getPhone2();
				data[i][3] = null == ci.getPayPrice()?"":ci.getPayPrice();
				data[i][4] = null == ci.getRecentCreateDate()?"":ci.getRecentCreateDate();
				i++;
			}
			excelBean.setData(data);
			excelBean.setExcelName("成单用户");
			excelBean.setSheetName("成单用户");
		}else if("4".equals(type)){
			Object[][] data = new Object[paramList.size()+1][5];
			
			int i = 1;
			data[0] = new Object[]{"序号", "电话", "电话1", "订单总金额", "最后成单时间"};
			for(CustomerInfo ci : paramList){
				data[i][0] = String.valueOf(i);
				data[i][1] = null == ci.getPhone()?"":ci.getPhone();
				data[i][2] = null == ci.getPhone2()?"":ci.getPhone2();
				data[i][3] = null == ci.getPayPrice()?"":ci.getPayPrice();
				data[i][4] = null == ci.getRecentCreateDate()?"":ci.getRecentCreateDate();
				i++;
			}
			excelBean.setData(data);
			excelBean.setExcelName("锁定用户");
			excelBean.setSheetName("锁定用户");
		}
	}
	
	/**
	 * @throws IOException 
	 * @Title: importCustomer 
	 * @Description: 导出用户
	 * @param request
	 * @param response 
	 * @return void
	 * @throws
	 */
	@RequestMapping("importCustomer")
	public String importCustomer(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="importFile", required=false) MultipartFile file) {
		try {
			//如果导入模版修改需要修改第二和第三个参数
			List<Map<String, Object>> data = importExcelUtil.readXLSDocument(file.getInputStream(), 1, 2);
			
			String type = request.getParameter("type");
			
			if(StringUtils.isBlank(type) || "0".equals(type)){
				customerInfoService.insertAndUpdateCustomerInfo(data);
			}else {
				customerInfoService.updateCustomerInfo(data);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
