package com.portal.bean;

import java.io.Serializable;
import java.util.Date;

public class VisitEverydayInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private String id;

    /**
     * 客户ID
     */
    private String customerId;

    /**
     * 客户名称
     */
    private String customerName;

    /**
     * 客户分类 0--空白客户  1--重复登门  2--说明会  3--成单  4--锁定
     */
    private String customerType;

    /**
     * 用户所在地区：0--沈阳  1--大连
     */
    private String customerArea;

    /**
     * 客服ID
     */
    private String customServiceId;

    /**
     * 客户名称
     */
    private String customServiceName;

    /**
     * 接待者ID
     */
    private String receiverStaffId;

    /**
     * 接待者名称
     */
    private String receiverStaffName;

    /**
     * 成单金额
     */
    private Long transactionAmount;

    /**
     * 活动：0--无  1--HZ  2--4DS  3--QB  4--回款  5--DS  6--LY  7--LY+HZ  8--XY  9--HZ+38国家  10--3B
     */
    private String exercise;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 出单或订单：1--出单  2--订单
     */
    private String outOrIndent;

    /**
     * 登门日期
     */
    private Date visitDate;

    private String condition;

    /**
     * @return 主键ID
     */
    public String getId() {
        return id;
    }

    /**
     * @param id 
	 *            主键ID
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return 客户ID
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     * @param customerId 
	 *            客户ID
     */
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    /**
     * @return 客户名称
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * @param customerName 
	 *            客户名称
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * @return 客户分类 0--空白客户  1--重复登门  2--说明会  3--成单  4--锁定
     */
    public String getCustomerType() {
        return customerType;
    }

    /**
     * @param customerType 
	 *            客户分类 0--空白客户  1--重复登门  2--说明会  3--成单  4--锁定
     */
    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    /**
     * @return 用户所在地区：0--沈阳  1--大连
     */
    public String getCustomerArea() {
        return customerArea;
    }

    /**
     * @param customerArea 
	 *            用户所在地区：0--沈阳  1--大连
     */
    public void setCustomerArea(String customerArea) {
        this.customerArea = customerArea;
    }

    /**
     * @return 客服ID
     */
    public String getCustomServiceId() {
        return customServiceId;
    }

    /**
     * @param customServiceId 
	 *            客服ID
     */
    public void setCustomServiceId(String customServiceId) {
        this.customServiceId = customServiceId;
    }

    /**
     * @return 客户名称
     */
    public String getCustomServiceName() {
        return customServiceName;
    }

    /**
     * @param customServiceName 
	 *            客户名称
     */
    public void setCustomServiceName(String customServiceName) {
        this.customServiceName = customServiceName;
    }

    /**
     * @return 接待者ID
     */
    public String getReceiverStaffId() {
        return receiverStaffId;
    }

    /**
     * @param receiverStaffId 
	 *            接待者ID
     */
    public void setReceiverStaffId(String receiverStaffId) {
        this.receiverStaffId = receiverStaffId;
    }

    /**
     * @return 接待者名称
     */
    public String getReceiverStaffName() {
        return receiverStaffName;
    }

    /**
     * @param receiverStaffName 
	 *            接待者名称
     */
    public void setReceiverStaffName(String receiverStaffName) {
        this.receiverStaffName = receiverStaffName;
    }

    /**
     * @return 成单金额
     */
    public Long getTransactionAmount() {
        return transactionAmount;
    }

    /**
     * @param transactionAmount 
	 *            成单金额
     */
    public void setTransactionAmount(Long transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    /**
     * @return 活动：0--无  1--HZ  2--4DS  3--QB  4--回款  5--DS  6--LY  7--LY+HZ  8--XY  9--HZ+38国家  10--3B
     */
    public String getExercise() {
        return exercise;
    }

    /**
     * @param exercise 
	 *            活动：0--无  1--HZ  2--4DS  3--QB  4--回款  5--DS  6--LY  7--LY+HZ  8--XY  9--HZ+38国家  10--3B
     */
    public void setExercise(String exercise) {
        this.exercise = exercise;
    }

    /**
     * @return 商品名称
     */
    public String getGoodsName() {
        return goodsName;
    }

    /**
     * @param goodsName 
	 *            商品名称
     */
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    /**
     * @return 出单或订单：1--出单  2--订单
     */
    public String getOutOrIndent() {
        return outOrIndent;
    }

    /**
     * @param outOrIndent 
	 *            出单或订单：1--出单  2--订单
     */
    public void setOutOrIndent(String outOrIndent) {
        this.outOrIndent = outOrIndent;
    }

    /**
     * @return 登门日期
     */
    public Date getVisitDate() {
        return visitDate;
    }

    /**
     * @param visitDate 
	 *            登门日期
     */
    public void setVisitDate(Date visitDate) {
        this.visitDate = visitDate;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }
}