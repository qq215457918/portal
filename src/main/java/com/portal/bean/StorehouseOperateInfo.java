package com.portal.bean;

import java.io.Serializable;
import java.util.Date;

public class StorehouseOperateInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 订单ID
     */
    private String orderId;

    /**
     * 订单详情ID
     */
    private String orderDetailId;

    /**
     * 商品分类ID
     */
    private String goodSortId;

    /**
     * 商品分类名称
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
     * 数量 如果退货数量为负
     */
    private Integer amount;

    /**
     * 操作标志 0入库 1已库
     */
    private String operateFlag;

    /**
     * 更新日期
     */
    private Date operateDate;

    /**
     * 更新人员id
     */
    private String operateId;

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
     * @return 订单详情ID
     */
    public String getOrderDetailId() {
        return orderDetailId;
    }

    /**
     * @param orderDetailId 
	 *            订单详情ID
     */
    public void setOrderDetailId(String orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    /**
     * @return 商品分类ID
     */
    public String getGoodSortId() {
        return goodSortId;
    }

    /**
     * @param goodSortId 
	 *            商品分类ID
     */
    public void setGoodSortId(String goodSortId) {
        this.goodSortId = goodSortId;
    }

    /**
     * @return 商品分类名称
     */
    public String getGoodSortName() {
        return goodSortName;
    }

    /**
     * @param goodSortName 
	 *            商品分类名称
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

    /**
     * @param goodType 
	 *            商品类型（同good_info type）
     */
    public void setGoodType(String goodType) {
        this.goodType = goodType;
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
     * @return 操作标志 0入库 1已库
     */
    public String getOperateFlag() {
        return operateFlag;
    }

    /**
     * @param operateFlag 
	 *            操作标志 0入库 1已库
     */
    public void setOperateFlag(String operateFlag) {
        this.operateFlag = operateFlag;
    }

    /**
     * @return 更新日期
     */
    public Date getOperateDate() {
        return operateDate;
    }

    /**
     * @param operateDate 
	 *            更新日期
     */
    public void setOperateDate(Date operateDate) {
        this.operateDate = operateDate;
    }

    /**
     * @return 更新人员id
     */
    public String getOperateId() {
        return operateId;
    }

    /**
     * @param operateId 
	 *            更新人员id
     */
    public void setOperateId(String operateId) {
        this.operateId = operateId;
    }
}