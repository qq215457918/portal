package com.portal.bean;

import java.io.Serializable;
import java.util.Date;

public class DailyEmployeeAudit implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private String id;

    /**
     * 发起人id
     */
    private String employeeId;

    /**
     * 状态 0待审核 1审核中 2审核完毕 3审核失败
     */
    private String status;

    /**
     * 审核人id
     */
    private String auditorId;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 删除标识 0正常 1删除
     */
    private String deleteFlag;

    /**
     * @return 主键id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id 
	 *            主键id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return 发起人id
     */
    public String getEmployeeId() {
        return employeeId;
    }

    /**
     * @param employeeId 
	 *            发起人id
     */
    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    /**
     * @return 状态 0待审核 1审核中 2审核完毕 3审核失败
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status 
	 *            状态 0待审核 1审核中 2审核完毕 3审核失败
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return 审核人id
     */
    public String getAuditorId() {
        return auditorId;
    }

    /**
     * @param auditorId 
	 *            审核人id
     */
    public void setAuditorId(String auditorId) {
        this.auditorId = auditorId;
    }

    /**
     * @return 创建时间
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * @param createDate 
	 *            创建时间
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * @return 删除标识 0正常 1删除
     */
    public String getDeleteFlag() {
        return deleteFlag;
    }

    /**
     * @param deleteFlag 
	 *            删除标识 0正常 1删除
     */
    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
}