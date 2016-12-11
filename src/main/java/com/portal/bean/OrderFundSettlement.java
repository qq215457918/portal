package com.portal.bean;

import java.io.Serializable;
import java.math.BigDecimal;

public class OrderFundSettlement implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private String id;

    /**
     * 订单编号
     */
    private String orderNumber;

    /**
     * 收款账户ID
     */
    private String paymentAccountId;

    /**
     * 客户支付方式
     */
    private String customerPayType;

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
    private String remark;

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
     * @return 订单编号
     */
    public String getOrderNumber() {
        return orderNumber;
    }

    /**
     * @param orderNumber 
	 *            订单编号
     */
    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

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
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark 
	 *            备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }
}