package com.portal.bean;

import java.io.Serializable;
import java.util.Date;

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
}