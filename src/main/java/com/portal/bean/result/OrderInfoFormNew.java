package com.portal.bean.result;

import java.io.Serializable;
import java.util.Date;

public class OrderInfoFormNew implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;

    private String orderNumber;

    private String customerId;
    private String customerName;
    private String customerPhone;

    private String phoneStaffId;

    private String phoneStaffName;

    private String receiverStaffId;

    private String receiverStaffName;

    /**
     * 订单状态 : 0未支付 1已支付 2已出库 3文交所已审核 4 已完成
     */
    private String status;

    /**
     * 支付类型  0全额支付 1定金支付 2派送支付
     */
    private String payType;

    /**
     * 订单金额
     */
    private Long payPrice;

    /**
     * 实际支付金额(订单详情)
     */
    private Long actualPrice;

    /**
     * 定金余额
     */
    private Long depositPrice;

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

    private Date financeDate;

    /**
     * 仓库审批标志<br>
     * 0未审核<br>
     * 1 已审核
     */
    private String warehouseFlag;

    private String warehouseOperatorId;

    private Date warehouseDate;

    private String cultureFlag;

    private String cultureOperatorId;

    private Date cultureDate;

    /**
     * 备注信息
     */
    private String remarks;

    private String createDateString;

    //订单详情
    /**
     * 商品类型ID
     */
    private String goodSortId;

    /**
     * 商品类型名称
     */
    private String goodSortName;

    /**
     * 商品ID
     */
    private String goodId;

    /**
     * 商品类型（同good_info type）
     */
    private String goodType;

    /**
     * 商品名称
     */
    private String goodName;

    /**
     * 价格
     */
    private Long price;

    /**
     * 实际支付金额(订单详情)
     */
    private Long repurchasePrice;

    /**
     * 数量 如果退货数量为负
     */
    private Integer amount;

    /**
     * 订单详情类型 1正常 2退货 3换货
     */
    private String orderType;

    /**
     * 删除标志 0正常 1删除
     */
    private String deleteFlag;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public Long getRepurchasePrice() {
        return repurchasePrice;
    }

    public void setRepurchasePrice(Long repurchasePrice) {
        this.repurchasePrice = repurchasePrice;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public Long getPayPrice() {
        return payPrice;
    }

    public void setPayPrice(Long payPrice) {
        this.payPrice = payPrice;
    }

    public Long getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(Long actualPrice) {
        this.actualPrice = actualPrice;
    }

    public Long getDepositPrice() {
        return depositPrice;
    }

    public void setDepositPrice(Long depositPrice) {
        this.depositPrice = depositPrice;
    }

    public String getFinanceFlag() {
        return financeFlag;
    }

    public void setFinanceFlag(String financeFlag) {
        this.financeFlag = financeFlag;
    }

    public String getFinanceType() {
        return financeType;
    }

    public void setFinanceType(String financeType) {
        this.financeType = financeType;
    }

    public String getFinanceOperatorId() {
        return financeOperatorId;
    }

    public void setFinanceOperatorId(String financeOperatorId) {
        this.financeOperatorId = financeOperatorId;
    }

    public Date getFinanceDate() {
        return financeDate;
    }

    public void setFinanceDate(Date financeDate) {
        this.financeDate = financeDate;
    }

    public String getWarehouseFlag() {
        return warehouseFlag;
    }

    public void setWarehouseFlag(String warehouseFlag) {
        this.warehouseFlag = warehouseFlag;
    }

    public String getWarehouseOperatorId() {
        return warehouseOperatorId;
    }

    public void setWarehouseOperatorId(String warehouseOperatorId) {
        this.warehouseOperatorId = warehouseOperatorId;
    }

    public Date getWarehouseDate() {
        return warehouseDate;
    }

    public void setWarehouseDate(Date warehouseDate) {
        this.warehouseDate = warehouseDate;
    }

    public String getCultureFlag() {
        return cultureFlag;
    }

    public void setCultureFlag(String cultureFlag) {
        this.cultureFlag = cultureFlag;
    }

    public String getCultureOperatorId() {
        return cultureOperatorId;
    }

    public void setCultureOperatorId(String cultureOperatorId) {
        this.cultureOperatorId = cultureOperatorId;
    }

    public Date getCultureDate() {
        return cultureDate;
    }

    public void setCultureDate(Date cultureDate) {
        this.cultureDate = cultureDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getCreateDateString() {
        return createDateString;
    }

    public void setCreateDateString(String createDateString) {
        this.createDateString = createDateString;
    }

    public String getGoodSortId() {
        return goodSortId;
    }

    public void setGoodSortId(String goodSortId) {
        this.goodSortId = goodSortId;
    }

    public String getGoodSortName() {
        return goodSortName;
    }

    public void setGoodSortName(String goodSortName) {
        this.goodSortName = goodSortName;
    }

    public String getGoodId() {
        return goodId;
    }

    public void setGoodId(String goodId) {
        this.goodId = goodId;
    }

    public String getGoodType() {
        return goodType;
    }

    public void setGoodType(String goodType) {
        this.goodType = goodType;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

}
