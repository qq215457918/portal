package com.portal.action.admin;

import java.util.Date;
import java.util.List;

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

import com.portal.bean.Criteria;
import com.portal.bean.GroupInfo;
import com.portal.common.util.WebUtils;
import com.portal.service.GroupInfoService;

/**
 * @ClassName: OrganizationController 
 * @Description: 后台组织机构管理控制类
 * @author Xia ZhengWei
 * @date 2017年3月7日 下午10:39:35
 */
@Controller
@RequestMapping("admin/organization")
public class OrganizationController {

    @Autowired
    private GroupInfoService groupService;
    
    /**
     * @Title: index 
     * @Description: 进入页面
     * @param request
     * @param response
     * @return String
     * @author Xia ZhengWei
     * @date 2017年3月7日 下午10:39:18 
     * @version V1.0
     */
    @RequiresPermissions("organization:view")
    @RequestMapping(method = RequestMethod.GET)
    public String index(HttpServletRequest request, HttpServletResponse response) {
        // 保存活动导航标识
        WebUtils.setAttributeToSession(request);
        return "admin/organization/index";
    }

    /**
     * @Title: showTree 
     * @Description: 显示左侧组织机构树数据
     * @param request
     * @param response
     * @return String
     * @author Xia ZhengWei
     * @date 2017年3月7日 下午10:39:57 
     * @version V1.0
     */
    @RequiresPermissions("organization:view")
    @RequestMapping(value = "/tree", method = RequestMethod.GET)
    public String showTree(HttpServletRequest request, HttpServletResponse response) {
        Criteria example = new Criteria();
        List<GroupInfo> groupList = groupService.selectByExample(example);
        request.setAttribute("organizationList", groupList);
        return "admin/organization/tree";
    }
    
    /**
     * @Title: showMaintainForm 
     * @Description: 进入编辑页面
     * @param id
     * @param model
     * @return String
     * @author Xia ZhengWei
     * @date 2017年3月7日 下午10:57:26 
     * @version V1.0
     */
    @RequiresPermissions("organization:update")
    @RequestMapping(value = "/{id}/maintain", method = RequestMethod.GET)
    public String showMaintainForm(@PathVariable("id") String id, Model model) {
        model.addAttribute("organization", groupService.selectByPrimaryKey(id));
        return "admin/organization/maintain";
    }

    /**
     * @Title: showAppendChildForm 
     * @Description: 进入添加子机构信息页面
     * @param parentId
     * @param model
     * @param request
     * @param response
     * @return String
     * @author Xia ZhengWei
     * @date 2017年3月7日 下午11:01:19 
     * @version V1.0
     */
    @RequiresPermissions("organization:create")
    @RequestMapping(value = "/{parentId}/appendChild", method = RequestMethod.GET)
    public String showAppendChildForm(@PathVariable("parentId") String parentId, Model model,
            HttpServletRequest request, HttpServletResponse response) {
        GroupInfo parent = groupService.selectByPrimaryKey(parentId);
        model.addAttribute("parent", parent);
        GroupInfo child = new GroupInfo();
        child.setParentsId(parentId);
        model.addAttribute("child", child);
        model.addAttribute("op", "新增");
        return "admin/organization/appendChild";
    }
    
    /**
     * @Title: update 
     * @Description: 保存组织机构信息
     * @param organization
     * @param redirectAttributes
     * @return String
     * @author Xia ZhengWei
     * @date 2017年3月7日 下午11:05:06 
     * @version V1.0
     */
    @RequiresPermissions("organization:update")
    @RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
    public String update(GroupInfo group, @PathVariable("id") String id, RedirectAttributes redirectAttributes) {
        if(Integer.parseInt(id) >= 0) {
            // 修改部门名称
            groupService.updateByPrimaryKeySelective(group);
        }else {
            // 增加部门
            group.setCreateDate(new Date());
            groupService.addGroupInfo(group);
        }
        return "redirect:/admin/organization/success";
    }
    
    /**
     * @Title: delete 
     * @Description: 删除组织机构信息(会关联删除所有子部门)
     * @param id
     * @param redirectAttributes
     * @return String
     * @author Xia ZhengWei
     * @date 2017年3月7日 下午11:03:34 
     * @version V1.0
     */
    @RequiresPermissions("organization:delete")
    @RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
    public String delete(@PathVariable("id") String id, RedirectAttributes redirectAttributes) {
        groupService.deleteGroupInfo(id);
        return "redirect:/admin/organization/success";
    }
    
    
    /*    @RequiresPermissions("organization:update")
    @RequestMapping(value = "/{sourceId}/move", method = RequestMethod.GET)
    public String showMoveForm(@PathVariable("sourceId") Long sourceId, Model model) {
        Organization source = organizationService.findOne(sourceId);
        model.addAttribute("source", source);
        model.addAttribute("targetList", organizationService.findAllWithExclude(source));
        return "organization/move";
    }
    
    @RequiresPermissions("organization:update")
    @RequestMapping(value = "/{sourceId}/move", method = RequestMethod.POST)
    public String move(
            @PathVariable("sourceId") Long sourceId,
            @RequestParam("targetId") Long targetId) {
        Organization source = organizationService.findOne(sourceId);
        Organization target = organizationService.findOne(targetId);
        organizationService.move(source, target);
        return "redirect:/organization/success";
    }*/

    /**
     * @Title: success 
     * @Description: 进入操作成功页面
     * @return String
     * @author Xia ZhengWei
     * @date 2017年3月8日 上午12:01:37 
     * @version V1.0
     */
    @RequiresPermissions("organization:view")
    @RequestMapping(value = "/success", method = RequestMethod.GET)
    public String success() {
        return "admin/organization/success";
    }

}
