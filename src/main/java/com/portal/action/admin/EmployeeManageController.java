package com.portal.action.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.portal.bean.Criteria;
import com.portal.bean.EmployeeInfo;
import com.portal.bean.GroupInfo;
import com.portal.bean.Role;
import com.portal.bean.result.EmployeeInfoForm;
import com.portal.common.util.BeanUtils;
import com.portal.common.util.DateUtil;
import com.portal.common.util.ExportBean;
import com.portal.common.util.ExportExcelJxl;
import com.portal.common.util.JsonUtils;
import com.portal.common.util.StringUtil;
import com.portal.common.util.WebUtils;
import com.portal.service.EmployeeInfoService;
import com.portal.service.GroupInfoService;
import com.portal.service.RoleService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @ClassName: EmployeeManageController 
 * @Description: 后台员工管理
 * @author Xia ZhengWei
 * @date 2016年12月20日 下午10:46:59
 */
@Controller
@RequestMapping("admin/employeeManage")
public class EmployeeManageController {
    
    
    @Autowired
    private EmployeeInfoService employeeService;
    
    @Autowired
    private GroupInfoService groupService;
    
    @Autowired
    private RoleService roleService;
    
    /**
     * @Title: toEmployeeManage 
     * @Description: 进入后台员工管理页面
     * @param request
     * @param response
     * @return String
     * @author Xia ZhengWei
     * @date 2016年12月20日 下午10:49:59 
     * @version V1.0
     */
    @RequiresPermissions("employeeManage:view")
    @RequestMapping("/toEmployeeManage")
    public String toEmployeeManage(HttpServletRequest request, HttpServletResponse response) {
        // 保存活动导航标识
        WebUtils.setAttributeToSession(request);
        // 默认是当前登录人所属机构, 该机构下的所有部门
        Criteria criteria = new Criteria();
        // 获取登录用户所属机构ID
        EmployeeInfo employee = (EmployeeInfo) BeanUtils.getLoginUser();
        if(employee != null) {
            criteria.put("parentsId", employee.getOrganizationId());
            // 按照名称排序
            criteria.setOrderByClause("CONVERT(name USING gbk) asc");
            List<GroupInfo> deptList = groupService.selectByExample(criteria);
            request.setAttribute("organizationId", employee.getOrganizationId());
            request.setAttribute("deptList", deptList);
            return "admin/employee_manage_list";
        }else {
            return "redirect:/login";
        }
    }
    
    /**
     * @Title: ajaxDeptOrGroupDataByParentsId 
     * @Description: 根据父级ID获取子级部门或组数据
     * @param request
     * @param response 
     * @return void
     * @author Xia ZhengWei
     * @date 2016年12月22日 下午9:34:14 
     * @version V1.0
     */
    @RequestMapping("/ajaxDeptOrGroupDataByParentsId")
    public void ajaxDeptOrGroupDataByParentsId(HttpServletRequest request, HttpServletResponse response) {
        Criteria criteria = new Criteria();
        String parentsId = request.getParameter("parentsId");
        if(StringUtil.isNotBlank(parentsId)) {
            criteria.put("parentsId", parentsId);
        }
        // 按照名称排序
        criteria.setOrderByClause("CONVERT(name USING gbk) asc");
        List<GroupInfo> groupList = groupService.selectByExample(criteria);
        JsonUtils.outJsonString(JSONArray.fromObject(groupList).toString(), response);
    }
    
    /**
     * @Title: ajaxEmployeeData 
     * @Description: 异步获取员工数据
     * @param request
     * @param response 
     * @return void
     * @author Xia ZhengWei
     * @date 2016年12月20日 下午10:55:17 
     * @version V1.0
     */
    @RequestMapping("/ajaxEmployeeData")
    public void ajaxEmployeeData(HttpServletRequest request, HttpServletResponse response) {
        // 公共查询条件类
        Criteria criteria = new Criteria();
        // 请求开始页
        int currentPage = StringUtil.getIntValue(request.getParameter("iDisplayStart"));
        // 每页显示几条
        int perpage = StringUtil.getIntValue(request.getParameter("iDisplayLength"));
        String sEcho = request.getParameter("sEcho");
        
        // 获取查询条件
        String name = request.getParameter("name");
        String sex = request.getParameter("sex");
        String organizationId = request.getParameter("organizationId");
        String departmentId = request.getParameter("departmentId");
        String groupId = request.getParameter("groupId");
        String startCreateDate = request.getParameter("startCreateDate");
        String endCreateDate = request.getParameter("endCreateDate");
        
        // 分页参数
        criteria.clear();
        criteria.setMysqlOffset(currentPage);
        criteria.setMysqlLength(perpage);
        criteria.setOrderByClause("department_id asc");
        
        if(StringUtil.isNotBlank(name)) {
            criteria.put("name", name.trim());
        }
        if(StringUtil.isNotBlank(sex)) {
            criteria.put("sex", sex);
        }
        if(StringUtil.isNotBlank(organizationId)) {
            criteria.put("organizationId", organizationId);
        }
        if(StringUtil.isNotBlank(departmentId)) {
            criteria.put("departmentId", departmentId);
        }
        if(StringUtil.isNotBlank(groupId)) {
            criteria.put("groupId", groupId);
        }
        if(StringUtil.isNotBlank(startCreateDate)) {
            criteria.put("startCreateDate", DateUtil.formatDate(DateUtil.parseDate(startCreateDate, "yyyy-MM-dd"), "yyyy-MM-dd 00:00:00"));
        }
        if(StringUtil.isNotBlank(endCreateDate)) {
            criteria.put("endCreateDate", DateUtil.formatDate(DateUtil.parseDate(endCreateDate, "yyyy-MM-dd"), "yyyy-MM-dd 23:59:59"));
        }
        // 异步获取数据
        JSONObject results = employeeService.ajaxEmployeeData(criteria, sEcho);
        // 向前端输出
        JsonUtils.outJsonString(results.toString(), response);
    }
    
    /**
     * @Title: toCompileEmployeeInfo 
     * @Description: 进入编辑员工信息页面
     * @param request
     * @param response
     * @return String
     * @author Xia ZhengWei
     * @date 2016年12月22日 下午10:19:50 
     * @version V1.0
     */
    @RequestMapping("/toCompileEmployeeInfo")
    public String toCompileEmployeeInfo(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        // 获取所有公司
        // TODO - 获取指定公司ID：s9f7f23r23q01s3w
        List<GroupInfo> companyList = groupService.getAllCompany();
        request.setAttribute("companyList", companyList);
        // 获取所有角色信息
        List<Role> roleList = roleService.findAll();
        request.setAttribute("roleList", roleList);
        if(StringUtil.isNotBlank(id)) {
            EmployeeInfo employeeInfo = employeeService.selectByPrimaryKey(id);
            if(employeeInfo != null & "0".equals(employeeInfo.getDeleteFlag())) {
                Criteria criteria = new Criteria();
                // 获取对应公司下的部门
                List<GroupInfo> departmentList = new ArrayList<GroupInfo>();
                if(StringUtil.isNotBlank(employeeInfo.getOrganizationId())) {
                    criteria.put("parentsId", employeeInfo.getOrganizationId());
                    criteria.setOrderByClause("CONVERT(name USING gbk) asc");
                    departmentList = groupService.selectByExample(criteria);
                }
                // 获取对应公司下的组
                List<GroupInfo> groupList = new ArrayList<GroupInfo>();
                if(StringUtil.isNotBlank(employeeInfo.getDepartmentId())) {
                    criteria.put("parentsId", employeeInfo.getDepartmentId());
                    groupList = groupService.selectByExample(criteria);
                }
                // 向域中存储对象信息
                request.setAttribute("employee", employeeInfo);
                request.setAttribute("departmentList", departmentList);
                request.setAttribute("groupList", groupList);
                return "admin/compile_employee_info";
            }else {
                return "common/404";
            }
        }else {
            return "admin/compile_employee_info";
        }
    }
    
    /**
     * @Title: saveEmployeeInfo 
     * @Description: 保存员工信息
     * @param employee
     * @param request 
     * @param response
     * @return void
     * @author Xia ZhengWei
     * @date 2016年12月21日 上午12:50:17 
     * @version V1.0
     */
    @RequestMapping("/saveEmployeeInfo")
    public void saveEmployeeInfo(EmployeeInfo employee, HttpServletRequest request, HttpServletResponse response) {
        JSONObject results = null;
        if(employee != null) {
            results = employeeService.saveEmployeeInfo(employee, results);
        }else {
            results = JsonUtils.setError();
            results.put("text", "操作失败, 请刷新后重试");
        }
        JsonUtils.outJsonString(results.toString(), response);
    }
    
    /**
     * @Title: deleteEmployeeInfo 
     * @Description: 删除员工信息并解除权限关系
     * @param request
     * @param response 
     * @return void
     * @author Xia ZhengWei
     * @date 2016年12月21日 上午12:51:25 
     * @version V1.0
     */
    @RequestMapping("/deleteEmployeeInfo")
    public void deleteEmployeeInfo(HttpServletRequest request, HttpServletResponse response) {
        JSONObject results = null;
        String employeeId = request.getParameter("employeeId");
        if(StringUtil.isNotBlank(employeeId)) {
            results = employeeService.deleteEmployeeInfo(employeeId, results);
        }else {
            results = JsonUtils.setError();
            results.put("text", "系统异常,请刷新后重试");
        }
        JsonUtils.outJsonString(results.toString(), response);
    }
    
    /**
     * @Title: exportEmployee 
     * @Description: 后台管理导出员工数据
     * @param request
     * @param response
     * @throws IOException 
     * @return void
     * @author Xia ZhengWei
     * @date 2017年1月3日 下午8:38:32 
     * @version V1.0
     */
    @RequestMapping("/exportEmployee")
    public void exportEmployee(HttpServletRequest request, HttpServletResponse response) throws IOException{
        Criteria criteria = new Criteria();
        // 获取查询条件
        String name = request.getParameter("name");
        String sex = request.getParameter("sex");
        String organizationId = request.getParameter("organizationId");
        String departmentId = request.getParameter("departmentId");
        String groupId = request.getParameter("groupId");
        String startCreateDate = request.getParameter("startCreateDate");
        String endCreateDate = request.getParameter("endCreateDate");
        
        criteria.put("deleteFlag", "0");
        criteria.setOrderByClause("department_id asc");
        if(StringUtil.isNotBlank(name)) {
            criteria.put("name", name.trim());
        }
        if(StringUtil.isNotBlank(sex)) {
            criteria.put("sex", sex);
        }
        String area = null;
        if(StringUtil.isNotBlank(organizationId)) {
            criteria.put("organizationId", organizationId);
            if("1".equals(organizationId)) {
                area = "大连";
            }else {
                area = "沈阳";
            }
        }else {
            area = "";
        }
        if(StringUtil.isNotBlank(departmentId)) {
            criteria.put("departmentId", departmentId);
        }
        if(StringUtil.isNotBlank(groupId)) {
            criteria.put("groupId", groupId);
        }
        if(StringUtil.isNotBlank(startCreateDate)) {
            criteria.put("startCreateDate", DateUtil.formatDate(DateUtil.parseDate(startCreateDate, "yyyy-MM-dd"), "yyyy-MM-dd 00:00:00"));
        }
        if(StringUtil.isNotBlank(endCreateDate)) {
            criteria.put("endCreateDate", DateUtil.formatDate(DateUtil.parseDate(endCreateDate, "yyyy-MM-dd"), "yyyy-MM-dd 23:59:59"));
        }
        
        List<EmployeeInfoForm> employeeList = employeeService.selectByConditions(criteria);
        
        try{
            @SuppressWarnings("deprecation")
            String filePath = request.getRealPath("resources/excel");
            ExportBean excelBean = new ExportBean();
            makeData(area, employeeList, excelBean);
            excelBean.setExportMode(0);
            excelBean.setSourceFile(filePath + "\\employee_excel.xls");
            new ExportExcelJxl(response, excelBean);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void makeData(String area, List<EmployeeInfoForm> employeeList, ExportBean excelBean){
        Object[][] data = new Object[employeeList.size()+1][10];
        int i = 1;
        data[0] = new Object[]{"序号","员工姓名","性别","登录名","所属机构","部门名称","小组名称","职位类型","接待状态","创建日期"};
        for(EmployeeInfoForm e : employeeList){
            data[i][0] = i;
            data[i][1] = (null == e.getName() ? "" : e.getName());
            data[i][2] = (null == e.getSex() ? "" : e.getSex());
            data[i][3] = (null == e.getLoginName() ? "" : e.getLoginName());
            data[i][4] = area;
            data[i][5] = (null == e.getDepartmentName() ? "" : e.getDepartmentName());
            data[i][6] = (null == e.getGroupName() ? "" : e.getGroupName());
            data[i][7] = (null == e.getPositionType() ? "" : e.getPositionType());
            data[i][8] = (null == e.getReceptionFlag() ? "" : e.getReceptionFlag());
            data[i][9] = (null == e.getViewCreateDate() ? "" : e.getViewCreateDate());
            i++;
        }
        excelBean.setData(data);
        excelBean.setExcelName(area + "员工信息");
        excelBean.setSheetName(area + "员工信息");
    }
    
}
