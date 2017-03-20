package com.portal.action.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.portal.bean.Criteria;
import com.portal.bean.Role;
import com.portal.bean.result.EmployeeInfoForm;
import com.portal.common.util.JsonUtils;
import com.portal.common.util.StringUtil;
import com.portal.common.util.WebUtils;
import com.portal.service.EmployeeInfoService;
import com.portal.service.ResourceService;
import com.portal.service.RoleService;

import net.sf.json.JSONObject;

/**
 * @ClassName: RoleController 
 * @Description: 后台角色管理控制类
 * @author Xia ZhengWei
 * @date 2017年3月9日 下午9:15:30
 */
@Controller
@RequestMapping("admin/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private ResourceService resourceService;
    
    @Autowired
    private EmployeeInfoService employeeService;
    
    Criteria criteria = new Criteria();

    /**
     * @Title: list 
     * @Description: 进入后台角色管理页面
     * @param model
     * @param request
     * @param response
     * @return String
     * @author Xia ZhengWei
     * @date 2017年3月9日 下午9:15:04 
     * @version V1.0
     */
    @RequiresPermissions("role:view")
    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model, HttpServletRequest request, HttpServletResponse response) {
        // 保存活动导航标识
        WebUtils.setAttributeToSession(request);
        model.addAttribute("roleList", roleService.findAll());
        return "admin/role/list";
    }

    /**
     * @Title: showCreateForm 
     * @Description: 进入新增角色页面
     * @param model
     * @param request
     * @param response
     * @return String
     * @author Xia ZhengWei
     * @date 2017年3月9日 下午9:15:58 
     * @version V1.0
     */
    @RequiresPermissions("role:create")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String showCreateForm(Model model, HttpServletRequest request, HttpServletResponse response) {
        setCommonData(model);
        model.addAttribute("role", new Role());
        model.addAttribute("op", "新增");
        return "admin/role/edit";
    }

    /**
     * @Title: create 
     * @Description: 新增
     * @param role
     * @param redirectAttributes
     * @param request
     * @param response
     * @return String
     * @author Xia ZhengWei
     * @date 2017年3月9日 下午9:23:30 
     * @version V1.0
     */
    @RequiresPermissions("role:create")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(Role role, RedirectAttributes redirectAttributes, HttpServletRequest request,
            HttpServletResponse response) {
        roleService.insertRole(role);
        return "redirect:/admin/role";
    }

    /**
     * @Title: showUpdateForm 
     * @Description: 进入修改页面
     * @param id
     * @param model
     * @param request
     * @param response
     * @return String
     * @author Xia ZhengWei
     * @date 2017年3月9日 下午9:23:48 
     * @version V1.0
     */
    @RequiresPermissions("role:update")
    @RequestMapping(value = "/{id}/update", method = RequestMethod.GET)
    public String showUpdateForm(@PathVariable("id") Long id, Model model, HttpServletRequest request,
            HttpServletResponse response) {
        setCommonData(model);
        model.addAttribute("role", roleService.findOne(id));
        model.addAttribute("op", "修改");
        return "admin/role/edit";
    }

    /**
     * @Title: update 
     * @Description: 修改
     * @param role
     * @param redirectAttributes
     * @param request
     * @param response
     * @return String
     * @author Xia ZhengWei
     * @date 2017年3月9日 下午9:24:06 
     * @version V1.0
     */
    @RequiresPermissions("role:update")
    @RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
    public String update(Role role, RedirectAttributes redirectAttributes, HttpServletRequest request,
            HttpServletResponse response) {
        roleService.updateRole(role);
        return "redirect:/admin/role";
    }
    
    /**
     * @Title: checkHasPromi 
     * @Description: 删除前校验是否存在关联关系
     * @param request
     * @param response 
     * @return void
     * @author Xia ZhengWei
     * @date 2017年3月11日 下午9:59:56 
     * @version V1.0
     */
    @RequestMapping("/checkHasPromi")
    public void checkHasPromi(HttpServletRequest request, HttpServletResponse response) {
        JSONObject result = new JSONObject();
        String roleId = request.getParameter("roleId");
        if(StringUtil.isNotBlank(roleId)) {
            criteria.clear();
            criteria.put("roleIds", "," + roleId + ",");
            List<EmployeeInfoForm> list = employeeService.selectByConditions(criteria);
            if(CollectionUtils.isNotEmpty(list)) {
                result.put("status", 1);
            }else {
                result.put("status", 0);
            }
        }
        JsonUtils.outJsonString(result.toString(), response);
    }

    /**
     * @Title: showDeleteForm 
     * @Description: 删除
     * @param id
     * @param model
     * @param request
     * @param response
     * @return String
     * @author Xia ZhengWei
     * @date 2017年3月9日 下午9:24:19 
     * @version V1.0
     */
    @RequiresPermissions("role:delete")
    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public String showDeleteForm(@PathVariable("id") Long id, Model model, HttpServletRequest request,
            HttpServletResponse response) {
        roleService.deleteRole(id);
        return "redirect:/admin/role";
    }

    private void setCommonData(Model model) {
        model.addAttribute("resourceList", resourceService.findAll());
    }

}
