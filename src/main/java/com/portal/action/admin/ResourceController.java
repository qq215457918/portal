package com.portal.action.admin;

import com.portal.bean.Resource;
import com.portal.bean.Role;
import com.portal.common.util.JsonUtils;
import com.portal.common.util.StringUtil;
import com.portal.common.util.WebUtils;
import com.portal.service.ResourceService;

import net.sf.json.JSONObject;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-2-14
 * <p>Version: 1.0
 */
@Controller
@RequestMapping("admin/resource")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    /**
     * 进入到myjob页面
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "init")
    public ModelAndView init(HttpServletRequest request, HttpServletResponse response) {
        WebUtils.setAttributeToSession(request);
        getBasePath(request, response);
        ModelAndView model = new ModelAndView();
        model.setViewName("admin/main");
        return model;
    }

    @ModelAttribute("types")
    public Resource.ResourceType[] resourceTypes() {
        return Resource.ResourceType.values();
    }

    /**
     * @Title: list 
     * @Description: 进入权限菜单管理页面
     * @param model
     * @param request
     * @param response
     * @return String
     * @author Xia ZhengWei
     * @date 2017年3月11日 下午1:22:28 
     * @version V1.0
     */
    @RequiresPermissions("resource:view")
    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model, HttpServletRequest request, HttpServletResponse response) {
        // 保存活动导航标识
        WebUtils.setAttributeToSession(request);
        model.addAttribute("resourceList", resourceService.findAll());
        return "admin/resource/list";
    }

    /**
     * @Title: showAppendChildForm 
     * @Description: 进入新增页面
     * @param parentId
     * @param model
     * @param request
     * @param response
     * @return String
     * @author Xia ZhengWei
     * @date 2017年3月11日 下午1:22:00 
     * @version V1.0
     */
    @RequiresPermissions("resource:create")
    @RequestMapping(value = "/{parentId}/appendChild", method = RequestMethod.GET)
    public String showAppendChildForm(@PathVariable("parentId") Long parentId, Model model,
            HttpServletRequest request, HttpServletResponse response) {
        if(parentId > 0) {
            // 新增子菜单
            Resource parent = resourceService.findOne(parentId);
            model.addAttribute("parent", parent);
            Resource child = new Resource();
            child.setParentId(parentId);
            model.addAttribute("resource", child);
            model.addAttribute("op", "新增子菜单");
        }else {
            // 新增
            model.addAttribute("resource", new Resource());
            model.addAttribute("op", "新增菜单");
        }
        // 获取所有权限菜单
        List<Resource> resourcesList = resourceService.findAll();
        model.addAttribute("resourcesList", resourcesList);
        return "admin/resource/edit";
    }

    /**
     * @Title: create 
     * @Description: 新增
     * @param resource
     * @param redirectAttributes
     * @param request
     * @param response
     * @return String
     * @author Xia ZhengWei
     * @date 2017年3月11日 下午1:34:34 
     * @version V1.0
     */
    @RequiresPermissions("resource:create")
    @RequestMapping(value = "/{parentId}/appendChild", method = RequestMethod.POST)
    public String create(Resource resource, RedirectAttributes redirectAttributes, HttpServletRequest request,
            HttpServletResponse response) {
        if(resource != null) {
            Resource parent = resourceService.findOne(resource.getParentId());
            resource.setParentIds(parent.getParentIds() + resource.getParentId() + "/");
            resourceService.insertResource(resource);
        }
        return "redirect:/admin/resource";
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
     * @date 2017年3月11日 下午1:35:05 
     * @version V1.0
     */
    @RequiresPermissions("resource:update")
    @RequestMapping(value = "/{id}/update", method = RequestMethod.GET)
    public String showUpdateForm(@PathVariable("id") Long id, Model model, HttpServletRequest request,
            HttpServletResponse response) {
        model.addAttribute("resource", resourceService.findOne(id));
        model.addAttribute("parent", resourceService.findOne(resourceService.findOne(id).getParentId()));
        // 获取所有权限菜单
        List<Resource> resourcesList = resourceService.findAll();
        model.addAttribute("resourcesList", resourcesList);
        model.addAttribute("op", "修改");
        return "admin/resource/edit";
    }

    /**
     * @Title: update 
     * @Description: 修改
     * @param resource
     * @param redirectAttributes
     * @param request
     * @param response
     * @return String
     * @author Xia ZhengWei
     * @date 2017年3月11日 下午3:06:39 
     * @version V1.0
     */
    @RequiresPermissions("resource:update")
    @RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
    public String update(Resource resource, RedirectAttributes redirectAttributes, HttpServletRequest request,
            HttpServletResponse response) {
        if(resource != null) {
            Resource parent = resourceService.findOne(resource.getParentId());
            resource.setParentIds(parent.getParentIds() + resource.getParentId() + "/");
            resourceService.updateResource(resource);
        }
        return "redirect:/admin/resource";
    }
    
    @RequestMapping("/checkHasChildAndPromi")
    public void checkHasChildAndPromi(HttpServletRequest request, HttpServletResponse response) {
        JSONObject result = new JSONObject();
        String parentId = request.getParameter("parentId");
        if(StringUtil.isNotBlank(parentId)) {
            int count = resourceService.checkHasChildAndPromi(Long.parseLong(parentId));
            if(count > 0) {
                result.put("status", 1);
            }else {
                result.put("status", 0);
            }
        }
        JsonUtils.outJsonString(result.toString(), response);
    }

    /**
     * @Title: delete 
     * @Description: 删除
     * @param id
     * @param redirectAttributes
     * @param request
     * @param response
     * @return 
     * @return String
     * @throws 
     * @author Xia ZhengWei
     * @date 2017年3月11日 下午3:08:22 
     * @version V1.0
     */
    @RequiresPermissions("resource:delete")
    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes,
            HttpServletRequest request, HttpServletResponse response) {
        resourceService.deleteResource(id);
        return "redirect:/admin/resource";
    }

    public void getBasePath(HttpServletRequest request, HttpServletResponse response) {
        String basePath = WebUtils.getBasePath(request, response);
        request.getSession().setAttribute("basePath", basePath);
    }

}
