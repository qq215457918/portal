package com.portal.bean;

import java.io.Serializable;
import java.util.Date;

public class PaymentAccountInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 收款账户ID
     */
    private String paymentAccountId;

    /**
     * 收款账户名称
     */
    private String paymentAccountName;

    /**
     * 银行名称
     */
    private String bankName;

    /**
     * 账号
     */
    private String accountNumber;

    /**
     * 是否可用 0-不可用 1-可用
     */
    private String isUsable;

    /**
     * 删除理由
     */
    private String deleteReason;

    /**
     * 删除标识 0-未删除 1-已删除
     */
    private String deleteFlag;

    /**
     * 添加日期
     */
    private Date createDate;

    /**
     * 创建用户ID
     */
    private String createUserId;

    /**
     * 修改日期
     */
    private Date updateDate;

    /**
     * 修改用户ID
     */
    private String updateUserId;
    
    /**
     * 机构 0沈阳 1大连
     */
    private String organizationId;

    /**
     * @return 收款账户ID
     */
    public String getPaymentAccountId() {
        return paymentAccountId;
    }

    /**
     * @param paymentAccountId 
	 *            收款账户ID
     */
    public void setPaymentAccountId(String paymentAccountId) {
        this.paymentAccountId = paymentAccountId;
    }

    /**
     * @return 收款账户名称
     */
    public String getPaymentAccountName() {
        return paymentAccountName;
    }

    /**
     * @param paymentAccountName 
	 *            收款账户名称
     */
    public void setPaymentAccountName(String paymentAccountName) {
        this.paymentAccountName = paymentAccountName;
    }

    /**
     * @return 银行名称
     */
    public String getBankName() {
        return bankName;
    }

    /**
     * @param bankName 
	 *            银行名称
     */
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    /**
     * @return 账号
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * @param accountNumber 
	 *            账号
     */
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    /**
     * @return 是否可用 0-不可用 1-可用
     */
    public String getIsUsable() {
        return isUsable;
    }

    /**
     * @param isUsable 
	 *            是否可用 0-不可用 1-可用
     */
    public void setIsUsable(String isUsable) {
        this.isUsable = isUsable;
    }

    /**
     * @return 删除理由
     */
    public String getDeleteReason() {
        return deleteReason;
    }

    /**
     * @param deleteReason 
	 *            删除理由
     */
    public void setDeleteReason(String deleteReason) {
        this.deleteReason = deleteReason;
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

    /**
     * @return 添加日期
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * @param createDate 
	 *            添加日期
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * @return 创建用户ID
     */
    public String getCreateUserId() {
        return createUserId;
    }

    /**
     * @param createUserId 
	 *            创建用户ID
     */
    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    /**
     * @return 修改日期
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * @param updateDate 
	 *            修改日期
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * @return 修改用户ID
     */
    public String getUpdateUserId() {
        return updateUserId;
    }

    /**
     * @param updateUserId 
	 *            修改用户ID
     */
    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}
}