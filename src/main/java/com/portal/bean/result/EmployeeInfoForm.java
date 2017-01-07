package com.portal.bean.result;

import com.portal.bean.EmployeeInfo;
import com.portal.common.util.DateUtil;

public class EmployeeInfoForm extends EmployeeInfo {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 部门名称
     */
    private String departmentName;
    
    /**
     * 小组名称
     */
    private String groupName;
    
    /**
     * 小组名称
     */
    private String viewCreateDate;

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getViewCreateDate() {
        if(super.getCreateDate() != null) {
            return DateUtil.formatDate(super.getCreateDate(), "yyyy-MM-dd");
        }
        return viewCreateDate;
    }

    public void setViewCreateDate(String viewCreateDate) {
        this.viewCreateDate = viewCreateDate;
    }

}