package com.portal.bean.result;

import com.portal.bean.DeptPerformanceInfo;

public class DeptPerforInfoForm extends DeptPerformanceInfo {

    private static final long serialVersionUID = 1L;
    
    /**
     * 机构名称
     */
    private String organizationName;
    
    /**
     * 部门名称
     */
    private String departmentName;
    
    /**
     * 组名称
     */
    private String groupName;
    
    /**
     * 员工姓名
     */
    private String employeeName;

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

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

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
    
}