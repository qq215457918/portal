package com.portal.action.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.portal.bean.Role;
import com.portal.common.util.WebUtils;
import com.portal.service.ResourceService;
import com.portal.service.RoleService;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-2-14
 * <p>Version: 1.0
 */
@Controller
@RequestMapping("admin/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private ResourceService resourceService;

    @RequiresPermissions("role:view")
    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model, HttpServletRequest request, HttpServletResponse response) {
        // 保存活动导航标识
        WebUtils.setAttributeToSession(request);
        model.addAttribute("roleList", roleService.findAll());
        return "admin/role/list";
    }

    @RequiresPermissions("role:create")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String showCreateForm(Model model, HttpServletRequest request, HttpServletResponse response) {
        setCommonData(model);
        model.addAttribute("role", new Role());
        model.addAttribute("op", "新增");
        return "admin/role/edit";
    }

    @RequiresPermissions("role:create")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(Role role, RedirectAttributes redirectAttributes, HttpServletRequest request,
            HttpServletResponse response) {
        roleService.insertRole(role);
        redirectAttributes.addFlashAttribute("msg", "新增成功");
        return "redirect:/role";
    }

    @RequiresPermissions("role:update")
    @RequestMapping(value = "/{id}/update", method = RequestMethod.GET)
    public String showUpdateForm(@PathVariable("id") Long id, Model model, HttpServletRequest request,
            HttpServletResponse response) {
        setCommonData(model);
        model.addAttribute("role", roleService.findOne(id));
        model.addAttribute("op", "修改");
        return "admin/role/edit";
    }

    @RequiresPermissions("role:update")
    @RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
    public String update(Role role, RedirectAttributes redirectAttributes, HttpServletRequest request,
            HttpServletResponse response) {
        roleService.updateRole(role);
        redirectAttributes.addFlashAttribute("msg", "修改成功");
        return "redirect:/role";
    }

    @RequiresPermissions("role:delete")
    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public String showDeleteForm(@PathVariable("id") Long id, Model model, HttpServletRequest request,
            HttpServletResponse response) {
        setCommonData(model);
        model.addAttribute("role", roleService.findOne(id));
        model.addAttribute("op", "删除");
        return "admin/role/edit";
    }

    @RequiresPermissions("role:delete")
    @RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
    public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes,
            HttpServletRequest request, HttpServletResponse response) {
        roleService.deleteRole(id);
        redirectAttributes.addFlashAttribute("msg", "删除成功");
        return "redirect:/role";
    }

    private void setCommonData(Model model) {
        model.addAttribute("resourceList", resourceService.findAll());
    }

}
