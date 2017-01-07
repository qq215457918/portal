package com.portal.bean;

import java.io.Serializable;
import java.util.Date;

public class DailyEmployeeAuditHistory implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private String id;

    /**
     * 审核编号
     */
    private String auditId;

    /**
     * 审核人id
     */
    private String auditorId;

    /**
     * 审核日期
     */
    private Date auditDate;

    /**
     * 审核意见
     */
    private String message;
    
    /**
     * 审批人
     */
    private String auditorName;

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
     * @return 审核编号
     */
    public String getAuditId() {
        return auditId;
    }

    /**
     * @param auditId 
	 *            审核编号
     */
    public void setAuditId(String auditId) {
        this.auditId = auditId;
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
     * @return 审核日期
     */
    public Date getAuditDate() {
        return auditDate;
    }

    /**
     * @param auditDate 
	 *            审核日期
     */
    public void setAuditDate(Date auditDate) {
        this.auditDate = auditDate;
    }

    /**
     * @return 审核意见
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message 
	 *            审核意见
     */
    public void setMessage(String message) {
        this.message = message;
    }

	public String getAuditorName() {
		return auditorName;
	}

	public void setAuditorName(String auditorName) {
		this.auditorName = auditorName;
	}
}