package com.portal.bean.result;

import com.portal.bean.OrderDetailInfo;

public class OrderDetailInfoForm extends OrderDetailInfo {

    private static final long serialVersionUID = 1L;
    
    /**
     * 订单创建时间
     */
    private String createDate;
    
    /**
     * 订单详细每条的总额
     */
    private String totalPrice;
    
    /**
     * 销售日报表-商品显示备注信息
     */
    private String viewRemark;
    
    /**
     * 赠品明细表-登门领取赠品数量
     */
    private Integer visitGiftCounts;
    
    /**
     * 赠品明细表-VIP领取赠品数量
     */
    private Integer vipGiftCounts;
    
    /**
     * 藏品类型 0-常规商品 2-配售 3-配送 4-兑换
     */
    private String goodType;
    
    /**
     * 出单类型 1-正常 2-退货 5-回购
     */
    private String orderType;

    public String getGoodType() {
        return goodType;
    }

    public void setGoodType(String goodType) {
        this.goodType = goodType;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getViewRemark() {
        return viewRemark;
    }

    public void setViewRemark(String viewRemark) {
        this.viewRemark = viewRemark;
    }

    public Integer getVisitGiftCounts() {
        return visitGiftCounts;
    }

    public void setVisitGiftCounts(Integer visitGiftCounts) {
        this.visitGiftCounts = visitGiftCounts;
    }

    public Integer getVipGiftCounts() {
        return vipGiftCounts;
    }

    public void setVipGiftCounts(Integer vipGiftCounts) {
        this.vipGiftCounts = vipGiftCounts;
    }
    
}