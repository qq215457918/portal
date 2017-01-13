package com.portal.action.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.portal.bean.Criteria;
import com.portal.bean.PaymentAccountInfo;
import com.portal.common.util.JsonUtils;
import com.portal.common.util.StringUtil;
import com.portal.common.util.WebUtils;
import com.portal.service.PaymentAccountInfoService;

import net.sf.json.JSONObject;

/**
 * @ClassName: paymentAccountController 
 * @Description: 后台-收款账户管理控制类
 * @author Xia ZhengWei
 * @date 2017年1月9日 下午8:51:12
 */
@Controller
@RequestMapping("admin/paymentAccount")
public class paymentAccountController {

    @Autowired
    private PaymentAccountInfoService paymentAccountService;
    
    /**
     * @Title: toPaymentAccountList 
     * @Description: 进入收款账户管理页面
     * @param request
     * @param response
     * @return String
     * @author Xia ZhengWei
     * @date 2017年1月9日 下午9:15:48 
     * @version V1.0
     */
    @RequiresPermissions("paymentAccount:view")
    @RequestMapping("toPaymentAccountList")
    public String toPaymentAccountList(HttpServletRequest request, HttpServletResponse response) {
        // 保存活动导航标识
        WebUtils.setAttributeToSession(request);
        return "admin/payment_account_list";
    }
    
    /**
     * @Title: ajaxPaymentAccountData 
     * @Description: 异步加载收款账户信息
     * @param request
     * @param response 
     * @return void
     * @author Xia ZhengWei
     * @date 2017年1月9日 下午9:16:17 
     * @version V1.0
     */
    @RequestMapping("/ajaxPaymentAccountData")
    public void ajaxPaymentAccountData(HttpServletRequest request, HttpServletResponse response) {
        // 公共查询条件类
        Criteria criteria = new Criteria();
        // 请求开始页
        int currentPage = StringUtil.getIntValue(request.getParameter("iDisplayStart"));
        // 每页显示几条
        int perpage = StringUtil.getIntValue(request.getParameter("iDisplayLength"));
        String sEcho = request.getParameter("sEcho");
        
        // 获取查询条件
        String paymentAccountName = request.getParameter("paymentAccountName");
        String bankName = request.getParameter("bankName");
        String accountNumber = request.getParameter("accountNumber");
        String isUsable = request.getParameter("isUsable");
        
        // 分页参数
        criteria.clear();
        criteria.setMysqlOffset(currentPage);
        criteria.setMysqlLength(perpage);
        criteria.put("deleteFlag", "0");
        // 根据收款帐号名称排序
        criteria.setOrderByClause("CONVERT(payment_account_name USING gbk) asc");
        
        if(StringUtil.isNotBlank(paymentAccountName)) {
            criteria.put("paymentAccountName", paymentAccountName.trim());
        }
        if(StringUtil.isNotBlank(bankName)) {
            criteria.put("bankName", bankName.trim());
        }
        if(StringUtil.isNotBlank(accountNumber)) {
            criteria.put("accountNumber", accountNumber.trim());
        }
        if(StringUtil.isNotBlank(isUsable)) {
            criteria.put("isUsable", isUsable);
        }
        // 异步获取数据
        JSONObject results = paymentAccountService.ajaxPaymentAccountData(criteria, sEcho);
        // 向前端输出
        JsonUtils.outJsonString(results.toString(), response);
    }
    
    /**
     * @Title: loadPaymentAccount 
     * @Description: 更新加载收款账户信息
     * @param request
     * @param response 
     * @return void
     * @author Xia ZhengWei
     * @date 2017年1月10日 下午10:30:40 
     * @version V1.0
     */
    @RequestMapping("/loadPaymentAccount")
    public void loadPaymentAccount(HttpServletRequest request, HttpServletResponse response) {
        JSONObject results = null;
        String paymentAccountId = request.getParameter("paymentAccountId");
        if(StringUtil.isNotBlank(paymentAccountId)) {
            PaymentAccountInfo paymentAccountInfo = paymentAccountService.selectByPrimaryKey(paymentAccountId);
            results = JsonUtils.setSuccess();
            results.put("payment", paymentAccountInfo);
        }else {
            results = JsonUtils.setError();
            results.put("text", "系统异常, 请刷新后重试");
        }
        // 向前端输出
        JsonUtils.outJsonString(results.toString(), response);
    }
    
    /**
     * @Title: savePaymentAccount 
     * @Description: 保存收款账户信息
     * @param payment
     * @param request
     * @param response 
     * @return void
     * @author Xia ZhengWei
     * @date 2017年1月9日 下午10:04:34 
     * @version V1.0
     */
    @RequestMapping("/savePaymentAccount")
    public void savePaymentAccount(PaymentAccountInfo payment, HttpServletRequest request, HttpServletResponse response) {
        JSONObject results = null;
        if(payment != null) {
            results = paymentAccountService.savePaymentAccount(payment, results, request);
        }else {
            results = JsonUtils.setError();
            results.put("text", "操作失败, 请刷新后重试");
        }
        JsonUtils.outJsonString(results.toString(), response);
    }
    
    /**
     * @Title: deletePaymentAccount 
     * @Description: 删除收款账户信息
     * @param request
     * @param response 
     * @return void
     * @author Xia ZhengWei
     * @date 2017年1月9日 下午10:30:46 
     * @version V1.0
     */
    @RequestMapping("/deletePaymentAccount")
    public void deletePaymentAccount(HttpServletRequest request, HttpServletResponse response) {
        JSONObject results = null;
        String paymentAccountId = request.getParameter("paymentAccountId");
        String deleteReason = request.getParameter("deleteReason");
        if(StringUtil.isNotBlank(paymentAccountId)) {
            results = paymentAccountService.updatePaymentAccount(paymentAccountId, results, request);
        }else {
            if(StringUtil.isNotBlank(deleteReason)) {
                results = JsonUtils.setError();
                results.put("text", "系统异常,请刷新后重试");
            }else {
                results = JsonUtils.setError();
                results.put("text", "操作失败,请填写删除原因");
            }
        }
        JsonUtils.outJsonString(results.toString(), response);
    }

}
