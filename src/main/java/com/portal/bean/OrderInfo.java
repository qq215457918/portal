package com.portal.bean;

import java.io.Serializable;
import java.util.Date;

public class OrderInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 订单号
     */
    private String orderNumber;

    /**
     * 客户id
     */
    private String customerId;

    /**
     * 客服id
     */
    private String phoneStaffId;

    /**
     * 接待人员id
     */
    private String receiverStaffId;

    /**
     * 订单状态 : 0未支付 1已支付 2已出库 3文交所已审核 4 已完成
     */
    private String status;

    /**
     * 订单类型 1正常 2退货 3换货
     */
    private String orderType;

    /**
     * 支付类型  0全额支付 1定金支付 2派送支付
     */
    private String payType;

    /**
     * 订单金额
     */
    private Long payPrice;

    /**
     * 实际支付金额
     */
    private Long actualPrice;

    /**
     * 支付状态(财务审核标志)<br>
	 * 0 未支付<br>
	 * 1已支付
     */
    private String financeFlag;

    /**
     * 支付接口<br>
	 * 接口1 接口2 微信支付等.
     */
    private String financeType;

    /**
     * 财务审批人员
     */
    private String financeOperatorId;

    /**
     * 财务审批日期
     */
    private Date financeDate;

    /**
     * 仓库审批标志<br>
	 * 0未审核<br>
	 * 1 已审核
     */
    private String warehouseFlag;

    /**
     * 仓库人员id
     */
    private String warehouseOperatorId;

    /**
     * 财务审批日期
     */
    private Date warehouseDate;

    /**
     * 文交所审批标志 <br>
	 * 0未审核<br>
	 * 1 已审核
     */
    private String cultureFlag;

    /**
     * 文交所人员id
     */
    private String cultureOperatorId;

    /**
     * 文交所审批日期
     */
    private Date cultureDate;

    private Date createDate;

    private String createId;

    private Date updateDate;

    private String updateId;

    private String deleteFlag;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return 订单号
     */
    public String getOrderNumber() {
        return orderNumber;
    }

    /**
     * @param orderNumber 
	 *            订单号
     */
    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    /**
     * @return 客户id
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     * @param customerId 
	 *            客户id
     */
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    /**
     * @return 客服id
     */
    public String getPhoneStaffId() {
        return phoneStaffId;
    }

    /**
     * @param phoneStaffId 
	 *            客服id
     */
    public void setPhoneStaffId(String phoneStaffId) {
        this.phoneStaffId = phoneStaffId;
    }

    /**
     * @return 接待人员id
     */
    public String getReceiverStaffId() {
        return receiverStaffId;
    }

    /**
     * @param receiverStaffId 
	 *            接待人员id
     */
    public void setReceiverStaffId(String receiverStaffId) {
        this.receiverStaffId = receiverStaffId;
    }

    /**
     * @return 订单状态 : 0未支付 1已支付 2已出库 3文交所已审核 4 已完成
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status 
	 *            订单状态 : 0未支付 1已支付 2已出库 3文交所已审核 4 已完成
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return 订单类型 1正常 2退货 3换货
     */
    public String getOrderType() {
        return orderType;
    }

    /**
     * @param orderType 
	 *            订单类型 1正常 2退货 3换货
     */
    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    /**
     * @return 支付类型  0全额支付 1定金支付 2派送支付
     */
    public String getPayType() {
        return payType;
    }

    /**
     * @param payType 
	 *            支付类型  0全额支付 1定金支付 2派送支付
     */
    public void setPayType(String payType) {
        this.payType = payType;
    }

    /**
     * @return 订单金额
     */
    public Long getPayPrice() {
        return payPrice;
    }

    /**
     * @param payPrice 
	 *            订单金额
     */
    public void setPayPrice(Long payPrice) {
        this.payPrice = payPrice;
    }

    /**
     * @return 实际支付金额
     */
    public Long getActualPrice() {
        return actualPrice;
    }

    /**
     * @param actualPrice 
	 *            实际支付金额
     */
    public void setActualPrice(Long actualPrice) {
        this.actualPrice = actualPrice;
    }

    /**
     * @return 支付状态(财务审核标志)<br>
	 * 0 未支付<br>
	 * 1已支付
     */
    public String getFinanceFlag() {
        return financeFlag;
    }

    /**
     * @param financeFlag 
	 *            支付状态(财务审核标志)<br>
	 * 0 未支付<br>
	 * 1已支付
     */
    public void setFinanceFlag(String financeFlag) {
        this.financeFlag = financeFlag;
    }

    /**
     * @return 支付接口<br>
	 * 接口1 接口2 微信支付等.
     */
    public String getFinanceType() {
        return financeType;
    }

    /**
     * @param financeType 
	 *            支付接口<br>
	 * 接口1 接口2 微信支付等.
     */
    public void setFinanceType(String financeType) {
        this.financeType = financeType;
    }

    /**
     * @return 财务审批人员
     */
    public String getFinanceOperatorId() {
        return financeOperatorId;
    }

    /**
     * @param financeOperatorId 
	 *            财务审批人员
     */
    public void setFinanceOperatorId(String financeOperatorId) {
        this.financeOperatorId = financeOperatorId;
    }

    /**
     * @return 财务审批日期
     */
    public Date getFinanceDate() {
        return financeDate;
    }

    /**
     * @param financeDate 
	 *            财务审批日期
     */
    public void setFinanceDate(Date financeDate) {
        this.financeDate = financeDate;
    }

    /**
     * @return 仓库审批标志<br>
	 * 0未审核<br>
	 * 1 已审核
     */
    public String getWarehouseFlag() {
        return warehouseFlag;
    }

    /**
     * @param warehouseFlag 
	 *            仓库审批标志<br>
	 * 0未审核<br>
	 * 1 已审核
     */
    public void setWarehouseFlag(String warehouseFlag) {
        this.warehouseFlag = warehouseFlag;
    }

    /**
     * @return 仓库人员id
     */
    public String getWarehouseOperatorId() {
        return warehouseOperatorId;
    }

    /**
     * @param warehouseOperatorId 
	 *            仓库人员id
     */
    public void setWarehouseOperatorId(String warehouseOperatorId) {
        this.warehouseOperatorId = warehouseOperatorId;
    }

    /**
     * @return 财务审批日期
     */
    public Date getWarehouseDate() {
        return warehouseDate;
    }

    /**
     * @param warehouseDate 
	 *            财务审批日期
     */
    public void setWarehouseDate(Date warehouseDate) {
        this.warehouseDate = warehouseDate;
    }

    /**
     * @return 文交所审批标志 <br>
	 * 0未审核<br>
	 * 1 已审核
     */
    public String getCultureFlag() {
        return cultureFlag;
    }

    /**
     * @param cultureFlag 
	 *            文交所审批标志 <br>
	 * 0未审核<br>
	 * 1 已审核
     */
    public void setCultureFlag(String cultureFlag) {
        this.cultureFlag = cultureFlag;
    }

    /**
     * @return 文交所人员id
     */
    public String getCultureOperatorId() {
        return cultureOperatorId;
    }

    /**
     * @param cultureOperatorId 
	 *            文交所人员id
     */
    public void setCultureOperatorId(String cultureOperatorId) {
        this.cultureOperatorId = cultureOperatorId;
    }

    /**
     * @return 文交所审批日期
     */
    public Date getCultureDate() {
        return cultureDate;
    }

    /**
     * @param cultureDate 
	 *            文交所审批日期
     */
    public void setCultureDate(Date cultureDate) {
        this.cultureDate = cultureDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreateId() {
        return createId;
    }

    public void setCreateId(String createId) {
        this.createId = createId;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateId() {
        return updateId;
    }

    public void setUpdateId(String updateId) {
        this.updateId = updateId;
    }

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
}