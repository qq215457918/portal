package com.portal.bean;

import java.io.Serializable;
import java.util.Date;

public class ReportGood implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 商品id
     */
    private String goodId;

    /**
     * 商品类型
     */
    private String goodType;

    /**
     * 商品名称
     */
    private String goodName;

    /**
     * 商品数量
     */
    private Integer amount;

    /**
     * 单价
     */
    private Long price;

    private String orderId;

    private Date createDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return 商品id
     */
    public String getGoodId() {
        return goodId;
    }

    /**
     * @param goodId 
	 *            商品id
     */
    public void setGoodId(String goodId) {
        this.goodId = goodId;
    }

    /**
     * @return 商品类型
     */
    public String getGoodType() {
        return goodType;
    }

    /**
     * @param goodType 
	 *            商品类型
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
     * @return 商品数量
     */
    public Integer getAmount() {
        return amount;
    }

    /**
     * @param amount 
	 *            商品数量
     */
    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    /**
     * @return 单价
     */
    public Long getPrice() {
        return price;
    }

    /**
     * @param price 
	 *            单价
     */
    public void setPrice(Long price) {
        this.price = price;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}