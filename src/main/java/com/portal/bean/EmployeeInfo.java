package com.portal.bean;

import java.io.Serializable;
import java.util.Date;

public class EmployeeInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;

    private String groupId;

    private String name;

    private String positionType;

    private String loginName;

    private String password;

    private String roleIds;

    /**
     * 接待标示(0未接待 1 正在接待)
     */
    private String receptionFlag;

    private Date createDate;

    private String deleteFlag;

    public String getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(String roleIds) {
        this.roleIds = roleIds;
    }

    /**
     * @return 主键
     */
    public String getId() {
        return id;
    }

    /**
     * @param id 
     *            主键
     */
    public void setId(String id) {
        this.id = id;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPositionType() {
        return positionType;
    }

    public void setPositionType(String positionType) {
        this.positionType = positionType;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return 接待标示(0未接待 1 正在接待)
     */
    public String getReceptionFlag() {
        return receptionFlag;
    }

    /**
     * @param receptionFlag 
     *            接待标示(0未接待 1 正在接待)
     */
    public void setReceptionFlag(String receptionFlag) {
        this.receptionFlag = receptionFlag;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
}
