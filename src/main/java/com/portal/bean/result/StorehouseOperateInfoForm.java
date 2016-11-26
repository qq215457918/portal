package com.portal.bean.result;

import java.util.Map;

import com.portal.bean.StorehouseOperateInfo;
import com.portal.common.util.DateUtil;
import com.portal.common.util.StringUtil;

public class StorehouseOperateInfoForm extends StorehouseOperateInfo {

    private static final long serialVersionUID = 1L;
    
    /**
     * 订单号
     */
    private String orderNumber;
    
    /**
     * 出库明细统计页面显示销售商品及赠品数量
     */
    private Map<String, Integer> goodAndGiftCounts;
    
    /**
     * 页面展示商品类型
     */
    private String viewTypeName;
    
    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }
    
    public void setViewTypeName(String viewTypeName) {
        this.viewTypeName = viewTypeName;
    }

    /**
     * 页面显示出库时间
     */
    public String getViewOperateDate() {
        if(!StringUtil.isNull(super.getOperateDate())) {
            return DateUtil.formatDate(super.getOperateDate(), "yyyy-MM-dd HH:mm:ss");            
        }else {
            return "";
        }
    }
    
    public String getViewTypeName() {
        if(!StringUtil.isNull(super.getGoodType())) {
            // 0:常规商品 1：礼品 2：配售 3：配送 4:兑换 
            switch (Integer.parseInt(super.getGoodType())) {
            case 1:
                return "礼品";
            case 2:
                return "配售";
            case 3:
                return "配送";
            case 4:
                return "兑换";
            default:
                return "常规商品";
            }
        }else {
            return viewTypeName;
        }
    }

    public Map<String, Integer> getGoodAndGiftCounts() {
        return goodAndGiftCounts;
    }

    public void setGoodAndGiftCounts(Map<String, Integer> goodAndGiftCounts) {
        this.goodAndGiftCounts = goodAndGiftCounts;
    }

}