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
    
}