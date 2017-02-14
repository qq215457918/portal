package com.portal.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.portal.bean.Criteria;
import com.portal.bean.CustomerInfo;
import com.portal.bean.OrderInfo;
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
		criteria.put("startTime", request.getParameter("startTime"));
		criteria.put("endTime", request.getParameter("endTime"));
		criteria.put("importDate1", request.getParameter("importDate1"));
		criteria.put("importDate2", request.getParameter("importDate2"));
		criteria.put("exportDate1", request.getParameter("exportDate1"));
		criteria.put("exportDate2", request.getParameter("exportDate2"));
		criteria.put("backCountS", request.getParameter("backCountS"));
		criteria.put("backCountE", request.getParameter("backCountE"));
		criteria.put("visiteDate1", request.getParameter("visiteDate1"));
		criteria.put("visiteDate2", request.getParameter("visiteDate2"));
		
		criteria.put("phone", request.getParameter("phone"));
		criteria.put("type", request.getParameter("type"));
		criteria.put("updateDate", request.getParameter("updateDate"));
		
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
		criteria.put("phoneStage", request.getParameter("phoneStage"));
		
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
		criteria.put("startTime", request.getParameter("startTime"));
		criteria.put("endTime", request.getParameter("endTime"));
		criteria.put("importDate1", request.getParameter("importDate1"));
		criteria.put("importDate2", request.getParameter("importDate2"));
		criteria.put("exportDate1", request.getParameter("exportDate1"));
		criteria.put("exportDate2", request.getParameter("exportDate2"));
		criteria.put("backCountS", request.getParameter("backCountS"));
		criteria.put("backCountE", request.getParameter("backCountE"));
		criteria.put("visiteDate1", request.getParameter("visiteDate1"));
		criteria.put("visiteDate2", request.getParameter("visiteDate2"));
		
		List<CustomerInfo> resultList = customerInfoService.selectCustomerExportList(criteria);
		
		try{
			@SuppressWarnings("deprecation")
			String filePath = request.getRealPath("resources/excel");
			
			ExportBean excelBean = new ExportBean();
			makeData(type, resultList, excelBean);
			excelBean.setExportMode(0);
			excelBean.setSourceFile(filePath + "\\customer_excel.xls");
			new ExportExcelJxl(response, excelBean);
			
			if(null != resultList && resultList.size() > 0){
				customerInfoService.updateExportDate(resultList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	
	private void makeData(String type, List<CustomerInfo> paramList, ExportBean excelBean){
		String typeName = "";
		if("0".equals(type)){
			typeName = "空白用户";
		}else if("1".equals(type)){
			typeName = "登门用户";
		}else if("2".equals(type)){
			typeName = "说明会用户";
		}else if("3".equals(type)){
			typeName = "成单用户";
		}else if("4".equals(type)){
			typeName = "锁定用户";
		}
		Object[][] data = new Object[paramList.size()+1][22];
		
		int i = 1;
		
		data[0] = new Object[]{"第一级","第二级","第三级","第四级","姓名","客服","接待","赠品","商务电话","手机","登门时间","地区","变成本类型客户时间","其它电话2","关联亲友","QQ","MSN","网页","身份证","产品","金额"};
		for(CustomerInfo ci : paramList){
			data[i][0] = typeName;
			data[i][1] = null == ci.getSeason2()?"":ci.getSeason2();
			data[i][2] = null == ci.getSeason3()?"":ci.getSeason3();
			data[i][3] = null == ci.getSeason4()?"":ci.getSeason4();
			data[i][4] = null == ci.getName()?"":ci.getName();
			data[i][5] = null == ci.getPhoneStaffName()?"":ci.getPhoneStaffName();
			data[i][6] = (null == ci.getReceiverStaffName()?"":ci.getReceiverStaffName()).replace("\\n", "\n");
			data[i][7] = (null == ci.getGift()?"":ci.getGift()).replace("\\n", "\n");
			data[i][8] = null == ci.getBusinessPhone()?"":ci.getBusinessPhone();
			data[i][9] = null == ci.getPhone()?"":ci.getPhone();
			data[i][10] = (null == ci.getReceiverStaffDate()?"":ci.getReceiverStaffDate()).replace("\\n", "\n");
			data[i][11] = null == ci.getArea()?"":ci.getArea();
			data[i][12] = null == ci.getUpdateDate()?"":ci.getUpdateDate();
			data[i][13] = null == ci.getPhone2()?"":ci.getPhone2();
			data[i][14] = null == ci.getRelationId()?"":ci.getRelationId();
			data[i][15] = null == ci.getQq()?"":ci.getQq();
			data[i][16] = null == ci.getMsn()?"":ci.getMsn();
			data[i][17] = null == ci.getSite()?"":ci.getSite();
			data[i][18] = null == ci.getIdCard()?"":ci.getIdCard();
			data[i][19] = (null == ci.getProduct()?"":ci.getProduct()).replace("\\n", "\n");
			data[i][20] = null == ci.getTransactionAmount()?"":ci.getTransactionAmount();
			i++;
		}
		excelBean.setData(data);
		excelBean.setExcelName(typeName);
		excelBean.setSheetName(typeName);
	
//		}else if("1".equals(type)){
//			Object[][] data = new Object[paramList.size()+1][6];
//			
//			int i = 1;
//			data[0] = new Object[]{"序号", "电话", "电话1", "姓名", "登门次数", "最近登门时间"};
//			for(CustomerInfo ci : paramList){
//				data[i][0] = String.valueOf(i);
//				data[i][1] = null == ci.getPhone()?"":ci.getPhone();
//				data[i][2] = null == ci.getPhone2()?"":ci.getPhone2();
//				data[i][3] = null == ci.getName()?"":ci.getName();
//				data[i][4] = null == ci.getVisitCount()?"":ci.getVisitCount();
//				data[i][5] = null == ci.getRecentVisitDate()?"":ci.getRecentVisitDate();
//				i++;
//			}
//			excelBean.setData(data);
//			excelBean.setExcelName("登门用户");
//			excelBean.setSheetName("登门用户");
//		}else if("2".equals(type)){
//			Object[][] data = new Object[paramList.size()+1][7];
//			
//			int i = 1;
//			data[0] = new Object[]{"序号", "电话", "电话1", "访问时间", "客户类型", "上次访问时间", "上次倒出时间"};
//			for(CustomerInfo ci : paramList){
//				data[i][0] = String.valueOf(i);
//				data[i][1] = null == ci.getPhone()?"":ci.getPhone();
//				data[i][2] = null == ci.getPhone2()?"":ci.getPhone2();
//				data[i][3] = null == ci.getVisitDate()?"":ci.getVisitDate();
//				data[i][4] = "说明会用户";
//				data[i][5] = null == ci.getRecentVisitDate()?"":ci.getRecentVisitDate();
//				data[i][6] = null == ci.getRecentExportDate()?"":ci.getRecentExportDate();
//				i++;
//			}
//			excelBean.setData(data);
//			excelBean.setExcelName("说明会用户");
//			excelBean.setSheetName("说明会用户");
//		}else if("3".equals(type)){
//			Object[][] data = new Object[paramList.size()+1][5];
//			
//			int i = 1;
//			data[0] = new Object[]{"序号", "电话", "电话1", "订单总金额", "最后成单时间"};
//			for(CustomerInfo ci : paramList){
//				data[i][0] = String.valueOf(i);
//				data[i][1] = null == ci.getPhone()?"":ci.getPhone();
//				data[i][2] = null == ci.getPhone2()?"":ci.getPhone2();
//				data[i][3] = null == ci.getPayPrice()?"":ci.getPayPrice();
//				data[i][4] = null == ci.getRecentCreateDate()?"":ci.getRecentCreateDate();
//				i++;
//			}
//			excelBean.setData(data);
//			excelBean.setExcelName("成单用户");
//			excelBean.setSheetName("成单用户");
//		}else if("4".equals(type)){
//			Object[][] data = new Object[paramList.size()+1][5];
//			
//			int i = 1;
//			data[0] = new Object[]{"序号", "电话", "电话1", "订单总金额", "最后成单时间"};
//			for(CustomerInfo ci : paramList){
//				data[i][0] = String.valueOf(i);
//				data[i][1] = null == ci.getPhone()?"":ci.getPhone();
//				data[i][2] = null == ci.getPhone2()?"":ci.getPhone2();
//				data[i][3] = null == ci.getPayPrice()?"":ci.getPayPrice();
//				data[i][4] = null == ci.getRecentCreateDate()?"":ci.getRecentCreateDate();
//				i++;
//			}
//			excelBean.setData(data);
//			excelBean.setExcelName("锁定用户");
//			excelBean.setSheetName("锁定用户");
//		}
	}
	
	/**
	 * @throws IOException 
	 * @Title: importCustomer 
	 * @Description: 导入用户
	 * @param request
	 * @param response 
	 * @return void
	 * @throws
	 */
	@RequestMapping("importCustomer")
	public String importCustomer(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="importFile", required=false) MultipartFile file) {
		String type = "";
		try {
			//如果导入模版修改需要修改第二和第三个参数
			List<Map<String, Object>> data = importExcelUtil.readXLSDocument(file.getInputStream(), 1, 21);
			
			type = request.getParameter("type");
			
//			if(StringUtils.isBlank(type) || "0".equals(type)){
				customerInfoService.insertAndUpdateCustomerInfo(data);
//			}else {
//				customerInfoService.updateCustomerInfo(data);
//			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:costomerInfoIndex?type=" + type;
	}
	
	/**
	 * @Title: customerOrderInfoIndex 
	 * @Description: 用户订单详情
	 * @param request
	 * @param response 
	 * @return void
	 * @throws
	 */
	@RequestMapping("customerOrderInfoIndex")
	public String customerOrderInfoIndex(HttpServletRequest request, HttpServletResponse response){
		request.setAttribute("customerId", request.getParameter("customerId"));
		return "customer/customer_order_info";
	}
	
	/**
	 * @Title: customerOrderInfoList 
	 * @Description: 用户订单详情
	 * @param request
	 * @param response 
	 * @return void
	 * @throws
	 */
	@RequestMapping("customerOrderInfoList")
	public void customerOrderInfoList(HttpServletRequest request, HttpServletResponse response){
		String customerId = request.getParameter("customerId");
		String receiverStaffId = request.getParameter("receiverStaffId");
		String createDate = request.getParameter("createDate");
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("mysqlLength", Integer.valueOf(request.getParameter("iDisplayLength")));
		paramMap.put("mysqlOffset", Integer.valueOf(request.getParameter("iDisplayStart")));
		paramMap.put("customerId", customerId);
		paramMap.put("createDate", createDate);
		paramMap.put("receiverStaffId", receiverStaffId);
		List<OrderInfo> resultList = customerInfoService.selectCustomerOrderList(paramMap);
		
		int count = customerInfoService.selectCustomerOrderCount(paramMap);
		
		JsonUtils.resultJson(resultList, count, response, request);
	}
}
