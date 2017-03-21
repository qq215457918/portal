package com.portal.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class SellGoodsDetail implements Serializable {
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
     * 销售藏品名称
     */
    private String goodsName;

    /**
     * 藏品类型 0-常规商品 2-配售 3-配送 4-兑换
     */
    private String goodType;

    /**
     * 出单类型 1-正常 2-退货 5-回购
     */
    private String orderType;

    /**
     * 数量
     */
    private Integer count;

    /**
     * 单价
     */
    private BigDecimal unitPrice;

    /**
     * 总计金额
     */
    private BigDecimal totalPrices;

    /**
     * 备注
     */
    private String remark;

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
     * @return 销售藏品名称
     */
    public String getGoodsName() {
        return goodsName;
    }

    /**
     * @param goodsName 
	 *            销售藏品名称
     */
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    /**
     * @return 藏品类型 0-常规商品 2-配售 3-配送 4-兑换
     */
    public String getGoodType() {
        return goodType;
    }

    /**
     * @param goodType 
	 *            藏品类型 0-常规商品 2-配售 3-配送 4-兑换
     */
    public void setGoodType(String goodType) {
        this.goodType = goodType;
    }

    /**
     * @return 出单类型 1-正常 2-退货 5-回购
     */
    public String getOrderType() {
        return orderType;
    }

    /**
     * @param orderType 
	 *            出单类型 1-正常 2-退货 5-回购
     */
    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    /**
     * @return 数量
     */
    public Integer getCount() {
        return count;
    }

    /**
     * @param count 
	 *            数量
     */
    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     * @return 单价
     */
    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    /**
     * @param unitPrice 
	 *            单价
     */
    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    /**
     * @return 总计金额
     */
    public BigDecimal getTotalPrices() {
        return totalPrices;
    }

    /**
     * @param totalPrices 
	 *            总计金额
     */
    public void setTotalPrices(BigDecimal totalPrices) {
        this.totalPrices = totalPrices;
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