package com.portal.action.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.portal.bean.Criteria;
import com.portal.bean.CustomerInfo;
import com.portal.bean.EmployeeInfo;
import com.portal.bean.result.CustomerSimpleInfoForm;
import com.portal.common.util.JsonUtils;
import com.portal.common.util.StringUtil;
import com.portal.common.util.WebUtils;
import com.portal.service.CustomerInfoService;

import net.sf.json.JSONObject;

/**
 * @ClassName: CustomerManageController 
 * @Description: 后台客户信息管理控制类
 * @author Xia ZhengWei
 * @date 2017年1月2日 下午8:29:18
 */
@Controller
@RequestMapping("admin/customerManage")
public class CustomerManageController {
    
    @Autowired
    private CustomerInfoService customerService;
    
    /**
     * @Title: toCustomerMagene 
     * @Description: 进入后台管理客户信息列表
     * @param request
     * @param response
     * @return String
     * @author Xia ZhengWei
     * @date 2017年1月2日 下午8:30:53 
     * @version V1.0
     */
    @RequestMapping("/toCustomerManage")
    public String toCustomerManage(HttpServletRequest request, HttpServletResponse response) {
        // 保存活动导航标识
        WebUtils.setAttributeToSession(request);
        // 默认是当前登录人所属机构
        EmployeeInfo employee = (EmployeeInfo) request.getSession().getAttribute("userInfo");
        if(employee != null) {
            request.setAttribute("area", employee.getOrganizationId());
            return "admin/customer_manage_list";
        }else {
            return "redirect:/login";
        }
    }
    
    /**
     * @Title: ajaxCustomerData 
     * @Description: 异步获取客户信息
     * @param request
     * @param response 
     * @return void
     * @author Xia ZhengWei
     * @date 2017年1月2日 下午9:03:15 
     * @version V1.0
     */
    @RequestMapping("/ajaxCustomerData")
    public void ajaxCustomerData(HttpServletRequest request, HttpServletResponse response) {
        // 公共查询条件类
        Criteria criteria = new Criteria();
        // 请求开始页
        int currentPage = StringUtil.getIntValue(request.getParameter("iDisplayStart"));
        // 每页显示几条
        int perpage = StringUtil.getIntValue(request.getParameter("iDisplayLength"));
        String sEcho = request.getParameter("sEcho");
        
        // 获取查询条件
        String area = request.getParameter("area");
        String name = request.getParameter("name");
        String sex = request.getParameter("sex");
        String vipCard = request.getParameter("vipCard");
        String type = request.getParameter("type");
        
        // 分页参数
        criteria.clear();
        criteria.setMysqlOffset(currentPage);
        criteria.setMysqlLength(perpage);
        // 按照名称排序
        criteria.setOrderByClause("CONVERT(name USING gbk) asc");
        
        if(StringUtil.isNotBlank(area)) {
            criteria.put("area", area);
        }
        if(StringUtil.isNotBlank(name)) {
            criteria.put("name", name.trim());
        }
        if(StringUtil.isNotBlank(sex)) {
            criteria.put("sex", sex);
        }
        if(StringUtil.isNotBlank(vipCard)) {
            criteria.put("vipCard", vipCard);
        }
        if(StringUtil.isNotBlank(type)) {
            criteria.put("type", type);
        }
        // 异步获取数据
        JSONObject results = customerService.ajaxCustomerData(criteria, sEcho);
        // 向前端输出
        JsonUtils.outJsonString(results.toString(), response);
    }
    
    /**
     * @Title: toCompileCustomer 
     * @Description: 进入修改客户基本信息页面
     * @param request
     * @param response
     * @return String
     * @author Xia ZhengWei
     * @date 2017年1月2日 下午9:57:26 
     * @version V1.0
     */
    @RequestMapping("/toCompileCustomer")
    public String toCompileCustomer(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        if(StringUtil.isNotBlank(id)) {
            CustomerInfo info = customerService.selectByPrimaryKey(id);
            if(info != null) {
                return "admin/compile_employee_info";
            }else {
                return "common/404";
            }
        }else {
            return "admin/compile_customer_info";
        }
    }
    
    /**
     * @Title: saveCustomerInfo 
     * @Description: 后台保存客户基本信息
     * @param customerForm
     * @param request
     * @param response 
     * @return void
     * @author Xia ZhengWei
     * @date 2017年1月2日 下午10:10:26 
     * @version V1.0
     */
    @RequestMapping("/saveCustomerInfo")
    public void saveCustomerInfo(CustomerSimpleInfoForm customerForm, HttpServletRequest request, HttpServletResponse response) {
        JSONObject results = null;
        if(customerForm != null) {
            results = customerService.saveCustomerInfo(customerForm, results);
        }else {
            results = JsonUtils.setError();
            results.put("text", "操作失败, 请刷新后重试");
        }
        JsonUtils.outJsonString(results.toString(), response);
    }

}
