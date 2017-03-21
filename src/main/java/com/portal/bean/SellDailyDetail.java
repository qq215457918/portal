package com.portal.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class SellDailyDetail implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private String id;

    /**
     * 销售报表ID
     */
    private String sellDailyId;

    /**
     * 收款账户名称
     */
    private String paymentAccountName;

    /**
     * 客户支付方式
     */
    private String customerPayType;

    /**
     * 出单支付类型 0全额支付 1定金支付 2派送支付 3余款支付
     */
    private String orderPayType;

    /**
     * 需要支付金额
     */
    private BigDecimal payAmount;

    /**
     * 实际支付金额
     */
    private BigDecimal payAmountActual;

    /**
     * 手续费
     */
    private BigDecimal poundage;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 创建用户ID
     */
    private String createUserId;

    /**
     * 创建日期
     */
    private Date createDate;

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
     * @return 销售报表ID
     */
    public String getSellDailyId() {
        return sellDailyId;
    }

    /**
     * @param sellDailyId 
	 *            销售报表ID
     */
    public void setSellDailyId(String sellDailyId) {
        this.sellDailyId = sellDailyId;
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
     * @return 客户支付方式
     */
    public String getCustomerPayType() {
        return customerPayType;
    }

    /**
     * @param customerPayType 
	 *            客户支付方式
     */
    public void setCustomerPayType(String customerPayType) {
        this.customerPayType = customerPayType;
    }

    /**
     * @return 出单支付类型 0全额支付 1定金支付 2派送支付 3余款支付
     */
    public String getOrderPayType() {
        return orderPayType;
    }

    /**
     * @param orderPayType 
	 *            出单支付类型 0全额支付 1定金支付 2派送支付 3余款支付
     */
    public void setOrderPayType(String orderPayType) {
        this.orderPayType = orderPayType;
    }

    /**
     * @return 需要支付金额
     */
    public BigDecimal getPayAmount() {
        return payAmount;
    }

    /**
     * @param payAmount 
	 *            需要支付金额
     */
    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    /**
     * @return 实际支付金额
     */
    public BigDecimal getPayAmountActual() {
        return payAmountActual;
    }

    /**
     * @param payAmountActual 
	 *            实际支付金额
     */
    public void setPayAmountActual(BigDecimal payAmountActual) {
        this.payAmountActual = payAmountActual;
    }

    /**
     * @return 手续费
     */
    public BigDecimal getPoundage() {
        return poundage;
    }

    /**
     * @param poundage 
	 *            手续费
     */
    public void setPoundage(BigDecimal poundage) {
        this.poundage = poundage;
    }

    /**
     * @return 备注
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * @param remarks 
	 *            备注
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
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
     * @return 创建日期
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * @param createDate 
	 *            创建日期
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}