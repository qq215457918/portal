package com.portal.bean.result;

import java.io.Serializable;
import java.util.Date;

public class ReceptionInfoFrom implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;

    private String customerId;
    
    private String customerName;

    private String receiverStaffId;
    
    private String receiverStaffName;

    private String startTime;

    private String endTime;

    private String createDate;

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

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getReceiverStaffId() {
		return receiverStaffId;
	}

	public void setReceiverStaffId(String receiverStaffId) {
		this.receiverStaffId = receiverStaffId;
	}

	public String getReceiverStaffName() {
		return receiverStaffName;
	}

	public void setReceiverStaffName(String receiverStaffName) {
		this.receiverStaffName = receiverStaffName;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}