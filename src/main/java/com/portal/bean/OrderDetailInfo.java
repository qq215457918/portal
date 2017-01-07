package com.portal.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.portal.common.util.NumberToCN;

public class OrderDetailInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 订单ID
     */
    private String orderId;

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
    
    private String goodTypeName;

    /**
     * 商品名称
     */
    private String goodName;

    /**
     * 价格
     */
    private Long oldPrice;

    /**
     * 价格
     */
    private Long price;

    /**
     * 价格
     */
    private Long repurchasePrice;

    /**
     * 数量 如果退货数量为负
     */
    private Integer amount;

    /**
     * 删除标志 0正常 1删除
     */
    private String orderType;

    private String oldOrderId;

    /**
     * 删除标志 0正常 1删除
     */
    private String deleteFlag;

    /**
     * 更新日期
     */
    private Date updateDate;

    /**
     * 更新人员id
     */
    private String updateId;

    /**
     * 订单备注
     */
    private String remark;

    /**
     * 顾客姓名
     */
    private String customerName;

    /**
     * 电联人
     */
    private String phoneStaffName;

    /**
     * 接待人
     */
    private String receiverStaffName;

    /**
     * 定金
     */
    private String actualPrice;

    /**
     * 总价
     */
    private String payPrice;

    /**
     * 支付类型
     */
    private String payType;

    private String payTypeName;

    private String payPriceCN;

    private String today;

    public Long getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(Long oldPrice) {
        this.oldPrice = oldPrice;
    }

    public String getOldOrderId() {
        return oldOrderId;
    }

    public void setOldOrderId(String oldOrderId) {
        this.oldOrderId = oldOrderId;
    }

    public Long getRepurchasePrice() {
        return repurchasePrice;
    }

    public void setRepurchasePrice(Long repurchasePrice) {
        this.repurchasePrice = repurchasePrice;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return 订单ID
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * @param orderId 
     *            订单ID
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    /**
     * @return 商品类型ID
     */
    public String getGoodSortId() {
        return goodSortId;
    }

    /**
     * @param goodSortId 
     *            商品类型ID
     */
    public void setGoodSortId(String goodSortId) {
        this.goodSortId = goodSortId;
    }

    /**
     * @return 商品类型名称
     */
    public String getGoodSortName() {
        return goodSortName;
    }

    /**
     * @param goodSortName 
     *            商品类型名称
     */
    public void setGoodSortName(String goodSortName) {
        this.goodSortName = goodSortName;
    }

    /**
     * @return 商品ID
     */
    public String getGoodId() {
        return goodId;
    }

    /**
     * @param goodId 
     *            商品ID
     */
    public void setGoodId(String goodId) {
        this.goodId = goodId;
    }

    /**
     * @return 商品类型（同good_info type）
     */
    public String getGoodType() {
        return goodType;
    }
    
    public String getGoodTypeName() {
		return goodTypeName;
	}
    
    /**
     * @param goodType 
     *            商品类型（同good_info type）
     */
    public void setGoodType(String goodType) {
        this.goodType = goodType;
        
        if ("1".equals(goodType)) {
            this.goodTypeName = "礼品 ";
        } else if ("2".equals(goodType)) {
            this.goodTypeName = "配售";
        } else if ("3".equals(goodType)) {
            this.goodTypeName = "配送";
        } else if ("4".equals(goodType)) {
            this.goodTypeName = "兑换";
        }
    }

    /**
     * @return 商品名称
     */
    public String getGoodName() {
        return goodName;
    }

    /**
     * @param goodName 
     *            商品名称
     */
    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    /**
     * @return 价格
     */
    public Long getPrice() {
        return price;
    }

    /**
     * @param price 
     *            价格
     */
    public void setPrice(Long price) {
        this.price = price;
    }

    /**
     * @return 数量 如果退货数量为负
     */
    public Integer getAmount() {
        return amount;
    }

    /**
     * @param amount 
     *            数量 如果退货数量为负
     */
    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    /**
     * @return 删除标志 0正常 1删除
     */
    public String getDeleteFlag() {
        return deleteFlag;
    }

    /**
     * @param deleteFlag 
     *            删除标志 0正常 1删除
     */
    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    /**
     * @return 更新日期
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * @param updateDate 
     *            更新日期
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * @return 更新人员id
     */
    public String getUpdateId() {
        return updateId;
    }

    /**
     * @param updateId 
     *            更新人员id
     */
    public void setUpdateId(String updateId) {
        this.updateId = updateId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
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

    public String getToday() {
        return today;
    }

    public void setToday(String today) {
        this.today = today;
    }

    public String getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(String actualPrice) {
        this.actualPrice = actualPrice;
    }

    public String getPayPrice() {
        return payPrice;
    }

    public void setPayPrice(String payPrice) {
        this.payPrice = payPrice;

        this.payPriceCN = NumberToCN.number2CNMontrayUnit(new BigDecimal(payPrice));
    }

    public String getPayPriceCN() {
        return payPriceCN;
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

    public String getPayTypeName() {
        return payTypeName;
    }

}
