package com.portal.bean.result;

import java.io.Serializable;
import java.util.Date;

public class CustomerSimpleInfoForm implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 客户分类<br>
	 * 0 空白客户<br>
	 * 1 重复登门<br>
	 * 2说明会<br>
	 * 3成单<br>
	 * 4锁定<br>
	 * 
     */
    private String type;

    private String name;

    private String phoneStaffId;
    
    private String phoneStaffName;

    private String receiverStaffId;
    
    private String receiverStaffName;

    private String encryptPhone;
    
    private String area;//地址

    private Date recentVisitDate;

    private Date recentExportDate;

    private String blacklistFlag;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneStaffId() {
		return phoneStaffId;
	}

	public void setPhoneStaffId(String phoneStaffId) {
		this.phoneStaffId = phoneStaffId;
	}

	public String getPhoneStaffName() {
		return phoneStaffName;
	}

	public void setPhoneStaffName(String phoneStaffName) {
		this.phoneStaffName = phoneStaffName;
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

	public String getEncryptPhone() {
		return encryptPhone;
	}

	public void setEncryptPhone(String encryptPhone) {
		this.encryptPhone = encryptPhone;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public Date getRecentVisitDate() {
		return recentVisitDate;
	}

	public void setRecentVisitDate(Date recentVisitDate) {
		this.recentVisitDate = recentVisitDate;
	}

	public Date getRecentExportDate() {
		return recentExportDate;
	}

	public void setRecentExportDate(Date recentExportDate) {
		this.recentExportDate = recentExportDate;
	}

	public String getBlacklistFlag() {
		return blacklistFlag;
	}

	public void setBlacklistFlag(String blacklistFlag) {
		this.blacklistFlag = blacklistFlag;
	}

}