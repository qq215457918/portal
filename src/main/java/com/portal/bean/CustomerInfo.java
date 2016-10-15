package com.portal.bean;

import java.io.Serializable;
import java.util.Date;

public class CustomerInfo implements Serializable {
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
    
    private String typeName;

    private String season2;

    private String season3;

    private String season4;

    private String name;

    private String phoneStaffId;

    private String receiverStaffId;

    private String businessPhone;

    private String phone;

    private Date visitDate;

    private String area;

    private String phone2;

    private String relationId;

    private String qq;

    private String msn;

    private String site;

    private String idCard;

    /**
     * 产品
     */
    private String product;

    private Long transactionAmount;

    private Date recentVisitDate;

    private Date recentExportDate;

    private String blacklistFlag;

    private Date updateDate;

    /**
     * 是否更新标志
     */
    private String updateFlag;
    
    /**
     * 最近成单时间
     */
    private String recentCreateDate;
    
    /**
     * 成单总额
     */
    private String payPrice;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return 客户分类<br>
	 * 0 空白客户<br>
	 * 1 重复登门<br>
	 * 2说明会<br>
	 * 3成单<br>
	 * 4锁定<br>
	 * 
     */
    public String getType() {
        return type;
    }
    
    public String getTypeName() {
        return typeName;
    }

    /**
     * @param type 
	 *            客户分类<br>
	 * 0 空白客户<br>
	 * 1 重复登门<br>
	 * 2说明会<br>
	 * 3成单<br>
	 * 4锁定<br>
	 * 
     */
    public void setType(String type) {
        this.type = type;
        
        if("0".equals(type)){
        	typeName = "空白客户";
        }else if("1".equals(type)){
        	typeName = "重复登门";
        }else if("2".equals(type)){
        	typeName = "说明会";
        }else if("3".equals(type)){
        	typeName = "成单";
        }else if("4".equals(type)){
        	typeName = "锁定";
        }
    }

    public String getSeason2() {
        return season2;
    }

    public void setSeason2(String season2) {
        this.season2 = season2;
    }

    public String getSeason3() {
        return season3;
    }

    public void setSeason3(String season3) {
        this.season3 = season3;
    }

    public String getSeason4() {
        return season4;
    }

    public void setSeason4(String season4) {
        this.season4 = season4;
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

    public String getReceiverStaffId() {
        return receiverStaffId;
    }

    public void setReceiverStaffId(String receiverStaffId) {
        this.receiverStaffId = receiverStaffId;
    }

    public String getBusinessPhone() {
        return businessPhone;
    }

    public void setBusinessPhone(String businessPhone) {
        this.businessPhone = businessPhone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Date visitDate) {
        this.visitDate = visitDate;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getRelationId() {
        return relationId;
    }

    public void setRelationId(String relationId) {
        this.relationId = relationId;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getMsn() {
        return msn;
    }

    public void setMsn(String msn) {
        this.msn = msn;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    /**
     * @return 产品
     */
    public String getProduct() {
        return product;
    }

    /**
     * @param product 
	 *            产品
     */
    public void setProduct(String product) {
        this.product = product;
    }

    public Long getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(Long transactionAmount) {
        this.transactionAmount = transactionAmount;
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

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * @return 是否更新标志
     */
    public String getUpdateFlag() {
        return updateFlag;
    }

    /**
     * @param updateFlag 
	 *            是否更新标志
     */
    public void setUpdateFlag(String updateFlag) {
        this.updateFlag = updateFlag;
    }

	public String getRecentCreateDate() {
		return recentCreateDate;
	}

	public void setRecentCreateDate(String recentCreateDate) {
		this.recentCreateDate = recentCreateDate;
	}

	public String getPayPrice() {
		return payPrice;
	}

	public void setPayPrice(String payPrice) {
		this.payPrice = payPrice;
	}
    
    
}