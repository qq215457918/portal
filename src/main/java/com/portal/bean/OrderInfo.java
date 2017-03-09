package com.portal.bean;

import com.portal.common.util.NumberToCN;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;

    private String orderId;

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

    private String statusName;

    /**
     * 订单类型 1正常 2退货 3换货
     */
    private String orderType;

    /**
     * 支付类型  0全额支付 1定金支付 2派送支付
     */
    private String payType;

    private String payTypeName;

    /**
     * 订单金额
     */
    private Long payPrice;

    private String payPriceCN;

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

    private String wareHouseFlagName;

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
     * 文交所审批备注
     */
    private String cultureRemark;

    /**
     * 文交所人员id
     */
    private String cultureOperatorId;

    /**
     * 文交所审批日期
     */
    private Date cultureDate;

    /**
     * 备注信息
     */
    private String remarks;

    /**
     * 电联人
     */
    private String phoneStaffName;

    /**
     * 接待人
     */
    private String receiverStaffName;

    /**
     * 大连订单 1 沈阳订单 0 
     */
    private String areaFlag;

    private Date createDate;

    private String createId;

    private Date updateDate;

    private String updateId;

    private String deleteFlag;

    private String customerName;

    private String customerPhone;

    private String goodsName;

    private String orderTypeName;

    private String goodsQuantity;

    private List<OrderFundSettlement> paymentList = new ArrayList<OrderFundSettlement>();

    public String getAreaFlag() {
        return areaFlag;
    }

    public void setAreaFlag(String areaFlag) {
        this.areaFlag = areaFlag;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
        this.orderId = id;
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
     *            订单状态 : 0未支付 1已支付 2已出库 3文交所已审核 4 已完成  5 待审批
     */
    public void setStatus(String status) {
        this.status = status;

        if ("0".equals(status)) {
            statusName = "未支付";
        } else if ("1".equals(status)) {
            statusName = "已支付";
        } else if ("2".equals(status)) {
            statusName = "已出库";
        } else if ("3".equals(status)) {
            statusName = "文交所审核";
        } else if ("4".equals(status)) {
            statusName = "已完成";
        }
    }

    /**
     * @return 订单类型 1正常 2退货 3换货
     */
    public String getOrderType() {
        return orderType;
    }

    /**
     * @return 订单类型 1正常 2退货 3换货
     */
    public String getOrderTypeName() {
        return orderTypeName;
    }

    /**
     * @param orderType 
     *            订单类型 1正常 2退货(detail) 3换货(去掉) 4赠品 5回购 6VIP赠品 7回购待确认',
     */
    public void setOrderType(String orderType) {
        this.orderType = orderType;

        if ("1".equals(orderType)) {
            this.orderTypeName = "已售商品";
        } else if ("2".equals(orderType)) {
            this.orderTypeName = "退货商品";
        } else if ("3".equals(orderType)) {
            this.orderTypeName = "换货商品";
        } else if ("4".equals(orderType)) {
            this.orderTypeName = "赠品";
        } else if ("5".equals(orderType)) {
            this.orderTypeName = "回购商品";
        } else if ("6".equals(orderType)) {
            this.orderTypeName = "VIP赠品";
        } else if ("7".equals(orderType)) {
            this.orderTypeName = "回购待确认";
        }
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

        if ("0".equals(payType)) {
            this.payTypeName = "全额支付";
        } else if ("1".equals(payType)) {
            this.payTypeName = "订金支付";
        } else if ("2".equals(payType)) {
            this.payTypeName = "派送支付";
        }

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

        this.payPriceCN = NumberToCN.number2CNMontrayUnit(new BigDecimal(payPrice));
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

        if ("0".equals(warehouseFlag)) {
            wareHouseFlagName = "未出库";
        } else if ("1".equals(warehouseFlag)) {
            wareHouseFlagName = "已出库";
        } else if ("-1".equals(warehouseFlag)) {
            wareHouseFlagName = "已确认入库";
        } else if ("".equals(warehouseFlag)) {
            wareHouseFlagName = "未出库";
        }
    }

    public String getWareHouseFlagName() {
        return wareHouseFlagName;
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

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getStatusName() {
        return statusName;
    }

    public String getPayTypeName() {
        return payTypeName;
    }

    public String getPayPriceCN() {
        return payPriceCN;
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

    public String getGoodsQuantity() {
        return goodsQuantity;
    }

    public void setGoodsQuantity(String goodsQuantity) {
        this.goodsQuantity = goodsQuantity;
    }

    public String getCultureRemark() {
        return cultureRemark;
    }

    public void setCultureRemark(String cultureRemark) {
        this.cultureRemark = cultureRemark;
    }

    public List<OrderFundSettlement> getPaymentList() {
        return paymentList;
    }

    public void setPaymentList(List<OrderFundSettlement> paymentList) {
        this.paymentList = paymentList;
    }
}
