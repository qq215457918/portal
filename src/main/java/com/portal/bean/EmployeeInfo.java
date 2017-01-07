package com.portal.bean;

import java.io.Serializable;
import java.util.Date;

public class EmployeeInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别 1-男 0-女
     */
    private String sex;

    /**
     * 工号
     */
    private String staffNumber;

    /**
     * 登录名称
     */
    private String loginName;

    /**
     * 登录密码
     */
    private String password;

    /**
     * 机构ID
     */
    private String organizationId;

    /**
     * 部门ID
     */
    private String departmentId;

    /**
     * 组ID
     */
    private String groupId;

    /**
     * 岗位角色ID
     */
    private String positionId;

    /**
     * 职位类型 1-客服 2-接待
     */
    private String positionType;

    /**
     * 头像
     */
    private String photoPath;

    /**
     * 接待标示(0未接待 1 正在接待)
     */
    private String receptionFlag;

    /**
     * 员工状态 0-未禁用 1-已禁用
     */
    private String status;

    private Date createDate;

    /**
     * 删除标识 0-未删除 1-已删除
     */
    private String deleteFlag;

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

    /**
     * @return 姓名
     */
    public String getName() {
        return name;
    }

    /**
     * @param name 
	 *            姓名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return 性别 1-男 0-女
     */
    public String getSex() {
        return sex;
    }

    /**
     * @param sex 
	 *            性别 1-男 0-女
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * @return 工号
     */
    public String getStaffNumber() {
        return staffNumber;
    }

    /**
     * @param staffNumber 
	 *            工号
     */
    public void setStaffNumber(String staffNumber) {
        this.staffNumber = staffNumber;
    }

    /**
     * @return 登录名称
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * @param loginName 
	 *            登录名称
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    /**
     * @return 登录密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password 
	 *            登录密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return 机构ID
     */
    public String getOrganizationId() {
        return organizationId;
    }

    /**
     * @param organizationId 
	 *            机构ID
     */
    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    /**
     * @return 部门ID
     */
    public String getDepartmentId() {
        return departmentId;
    }

    /**
     * @param departmentId 
	 *            部门ID
     */
    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    /**
     * @return 组ID
     */
    public String getGroupId() {
        return groupId;
    }

    /**
     * @param groupId 
	 *            组ID
     */
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    /**
     * @return 岗位角色ID
     */
    public String getPositionId() {
        return positionId;
    }

    /**
     * @param positionId 
	 *            岗位角色ID
     */
    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    /**
     * @return 职位类型 1-客服 2-接待
     */
    public String getPositionType() {
        return positionType;
    }

    /**
     * @param positionType 
	 *            职位类型 1-客服 2-接待
     */
    public void setPositionType(String positionType) {
        this.positionType = positionType;
    }

    /**
     * @return 头像
     */
    public String getPhotoPath() {
        return photoPath;
    }

    /**
     * @param photoPath 
	 *            头像
     */
    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
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

    /**
     * @return 员工状态 0-未禁用 1-已禁用
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status 
	 *            员工状态 0-未禁用 1-已禁用
     */
    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * @return 删除标识 0-未删除 1-已删除
     */
    public String getDeleteFlag() {
        return deleteFlag;
    }

    /**
     * @param deleteFlag 
	 *            删除标识 0-未删除 1-已删除
     */
    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
}