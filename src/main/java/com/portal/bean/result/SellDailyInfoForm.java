package com.portal.bean.result;

import java.util.ArrayList;
import java.util.List;

import com.portal.bean.SellDailyDetail;
import com.portal.bean.SellDailyInfo;
import com.portal.bean.SellGoodsDetail;

public class SellDailyInfoForm extends SellDailyInfo {

    private static final long serialVersionUID = 1L;
    
    /**
     * 资金结算明细
     */
    private List<SellDailyDetail> sellDailyDetails = new ArrayList<SellDailyDetail>();
    
    /**
     * 销售藏品明细
     */
    private List<SellGoodsDetail> sellGoodsDetails = new ArrayList<SellGoodsDetail>();

    public List<SellDailyDetail> getSellDailyDetails() {
        return sellDailyDetails;
    }

    public void setSellDailyDetails(List<SellDailyDetail> sellDailyDetails) {
        this.sellDailyDetails = sellDailyDetails;
    }

    public List<SellGoodsDetail> getSellGoodsDetails() {
        return sellGoodsDetails;
    }

    public void setSellGoodsDetails(List<SellGoodsDetail> sellGoodsDetails) {
        this.sellGoodsDetails = sellGoodsDetails;
    }

}