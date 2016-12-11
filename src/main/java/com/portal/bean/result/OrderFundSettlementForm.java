package com.portal.bean.result;

import com.portal.bean.OrderFundSettlement;

public class OrderFundSettlementForm extends OrderFundSettlement {
    
    private static final long serialVersionUID = 1L;

    /**
     * 收款账户ID
     */
    private String paymentAccountName;

    public String getPaymentAccountName() {
        return paymentAccountName;
    }

    public void setPaymentAccountName(String paymentAccountName) {
        this.paymentAccountName = paymentAccountName;
    }

}