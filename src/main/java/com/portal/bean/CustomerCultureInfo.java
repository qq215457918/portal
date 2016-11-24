package com.portal.bean;

import java.io.Serializable;
import java.util.Date;

public class CustomerCultureInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;

    private String cultureId;

    private String idCard;

    private String cultureName;

    private Date accountDate;

    /**
     * 是否银商绑定
     */
    private String bankFlag;

    private String bankName;

    private String customerName;

    private String phone;

    /**
     * 会员代码
     */
    private String code;

    private String updateFlag;

    private String deleteFlag;

    private Date updateDate;

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getCultureId() {
        return cultureId;
    }

    public void setCultureId(String cultureId) {
        this.cultureId = cultureId;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCultureName() {
        return cultureName;
    }

    public void setCultureName(String cultureName) {
        this.cultureName = cultureName;
    }

    public Date getAccountDate() {
        return accountDate;
    }

    public void setAccountDate(Date accountDate) {
        this.accountDate = accountDate;
    }

    /**
     * @return 是否银商绑定
     */
    public String getBankFlag() {
        return bankFlag;
    }

    /**
     * @param bankFlag 
     *            是否银商绑定
     */
    public void setBankFlag(String bankFlag) {
        this.bankFlag = bankFlag;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return 会员代码
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code 
     *            会员代码
     */
    public void setCode(String code) {
        this.code = code;
    }

    public String getUpdateFlag() {
        return updateFlag;
    }

    public void setUpdateFlag(String updateFlag) {
        this.updateFlag = updateFlag;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
