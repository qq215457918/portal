
package com.portal.action.reception;

import com.portal.bean.CustomerInfo;
import com.portal.bean.EmployeeInfo;
import com.portal.bean.result.CustomerSimpleInfoForm;
import com.portal.bean.result.ReceptionInfoForm;
import com.portal.common.util.WebUtils;
import com.portal.service.CustomerInfoService;
import com.portal.service.EmployeeInfoService;
import com.portal.service.OrderInfoService;
import com.portal.service.ReceptionInfoService;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 接待客户模块
 * 接待模块查询业务
 */
@Controller
@RequestMapping("/visit")
public class ReceptionAction {
    @Autowired
    private ReceptionInfoService receptionInfoService;

    @Autowired
    private CustomerInfoService customerInfoService;

    @Autowired
    private OrderInfoService orderInfoService;

    @Autowired
    private EmployeeInfoService employeeService;

    /**
     * 进入用户查询页面
     * @param request
     * @param response
     * @return
     */
    @RequiresPermissions("visit:button")
    @RequestMapping(value = "/query")
    public String queryCustomer(HttpServletRequest request, HttpServletResponse response) {
        return "reception/inquiry_query";
    }

    /**
     * 查询用手机号，是否是我们系统中已经录入的客户
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/first")
    public ModelAndView fristCustomerInfo(HttpServletRequest request, HttpServletResponse response) {
        getBasePath(request, response);
        String phoneNo = request.getParameter("phoneNo");
        boolean exist = customerInfoService.isCustomer(phoneNo);
        ModelAndView model = new ModelAndView();
        if (!exist) {
            //找到此区域下的所有客服信息
            model.addObject("phoneEmp", employeeService.getPhoneEmpByOrganization());
            model.addObject("phone", phoneNo);
            model.setViewName("reception/inquiry_add");
            return model;
        }
        CustomerSimpleInfoForm info = customerInfoService.getFristQueryInfo(phoneNo);
        model.setViewName("reception/query_frist");
        model.addObject("result", info);
        return model;
    }

    @RequestMapping(value = "/second")
    public ModelAndView secondCustomerInfo(HttpServletRequest request, HttpServletResponse response) {
        getBasePath(request, response);
        WebUtils.setAttributeToSession(request);
        ModelAndView model = new ModelAndView();
        String customerId = request.getParameter("cId");
        if (StringUtils.isEmpty(customerId)) {
            customerId = request.getAttribute("cId").toString();
        }
        //查询接待表
        EmployeeInfo employeeInfo = (EmployeeInfo) request.getAttribute("employeeInfo");
        //ADD by xdx for filter repeatedly visit log with out end action. 
        List<ReceptionInfoForm> receptions = receptionInfoService.queryRecordListbyId(customerId);
        if(receptions.size()>0){
        	ReceptionInfoForm lastReceptionInfo = receptions.get(0);
        	System.out.println("lastReceptionInfo.getEndTime():"+lastReceptionInfo.getEndTime());
        	if(lastReceptionInfo.getEndTime()!=null&&lastReceptionInfo.getEndTime().length()>0){
                receptionInfoService.insertReceptionTime(customerId, employeeInfo.getId(), employeeInfo.getName());
        	}
        }else{
            receptionInfoService.insertReceptionTime(customerId, employeeInfo.getId(), employeeInfo.getName());
        }
        //add end
        customerInfoService.updateVisitCount(customerId);
        //修改客户类型 登门客户类型为1
        customerInfoService.updateType(customerId, "1");
        if (StringUtils.isEmpty((String) request.getAttribute("receiverStaffName"))) {//receiverStaffName
            CustomerInfo cInfoNew = new CustomerInfo();
            cInfoNew.setId(customerId);
            cInfoNew.setReceiverStaffId(employeeInfo.getId());
            customerInfoService.updateByPrimaryKeySelective(cInfoNew);
        }
        model.addObject("info", customerInfoService.getCutomerInfoById(customerId, employeeInfo));
        model.addObject("goods", orderInfoService.queryGoodsInfo(customerId));
        model.addObject("returnGoods", orderInfoService.queryReturnGoodsInfo(customerId));
        model.addObject("revokeDeposit", orderInfoService.queryRevokeDepositInfo(customerId));
        model.addObject("receptionInfo", receptionInfoService.queryRecordListbyId(customerId));
        //model.addObject("cId", customerId);
        request.getSession().setAttribute("cId", customerId);
        model.setViewName("reception/query_second");
        return model;
    }

    /**
     * modify 查询add 是否电话号码是否存在
     * modify 0310
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/add")
    public ModelAndView customerAdd(CustomerInfo customerInfo, HttpServletRequest request) {
        CustomerSimpleInfoForm isExist =
                customerInfoService.getFristQueryInfo(customerInfo.getPhone());
        ModelAndView model = new ModelAndView();
        if (isExist != null) {
            model.setViewName("reception/query_frist");
            model.addObject("result", isExist);
            return model;
        } else {
            model.addObject("result", customerInfoService.insertCustomer(customerInfo,
                    (EmployeeInfo) request.getSession().getAttribute("userInfo")));
            model.setViewName("reception/query_frist");
            return model;
        }
    }

    /**
     * 结束接待
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/quit")
    public String receptionQuit(HttpServletRequest request, HttpServletResponse response) {
        getBasePath(request, response);
        //String cId = request.getParameter("cId");
        String cId = (String) request.getSession().getAttribute("cId");
        receptionInfoService.updateEndReceptionTime(cId);
        return "reception/inquiry_query";
    }

    public void getBasePath(HttpServletRequest request, HttpServletResponse response) {
        String basePath = WebUtils.getBasePath(request, response);
        request.getSession().setAttribute("basePath", basePath);
    }

}
