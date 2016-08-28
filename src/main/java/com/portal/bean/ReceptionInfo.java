package com.portal.bean;

import java.io.Serializable;
import java.util.Date;

public class ReceptionInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;

    private String customerId;

    private String receiverStaffId;

    private Date startTime;

    private Date endTime;

    private Date createDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getReceiverStaffId() {
        return receiverStaffId;
    }

    public void setReceiverStaffId(String receiverStaffId) {
        this.receiverStaffId = receiverStaffId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}