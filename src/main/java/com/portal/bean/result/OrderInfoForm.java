package com.portal.bean.result;

import com.portal.bean.OrderDetailInfo;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class OrderInfoForm implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;

    private String customerId;

    private String phoneStaffId;

    private String phoneStaffName;

    private String receiverStaffId;

    private String receiverStaffName;

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

    private Date createDate;

    private String createId;

    private Date updateDate;

    private String updateId;

    private String deleteFlag;

    private String createDateString;

    public Long getDepositPrice() {
        return depositPrice;
    }

    public void setDepositPrice(Long depositPrice) {
        this.depositPrice = depositPrice;
    }

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public String getPhoneStaffName() {
        return phoneStaffName;
    }

    public void setPhoneStaffName(String phoneStaffName) {
        this.phoneStaffName = phoneStaffName;
    }

    public String getReceiverStaffName() {
        return receiverStaffName;
    }

    public void setReceiverStaffName(String receiverStaffName) {
        this.receiverStaffName = receiverStaffName;
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

    //订单详情列表
    private List<OrderDetailInfo> orderDetailInfoList;

    /**
     * 统计业绩--员工ID
     */
    private String staffId;

    /**
     * 统计业绩--员工名称
     */
    private String staffName;

    /**
     * 统计业绩--业绩
     */
    private Integer performance;

    public List<OrderDetailInfo> getOrderDetailInfoList() {
        return orderDetailInfoList;
    }

    public void setOrderDetailInfoList(List<OrderDetailInfo> orderDetailInfoList) {
        this.orderDetailInfoList = orderDetailInfoList;
    }

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

    public Date getFinanceDate() {
        return financeDate;
    }

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

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public Integer getPerformance() {
        return performance;
    }

    public void setPerformance(Integer performance) {
        this.performance = performance;
    }

}
