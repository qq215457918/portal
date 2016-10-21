package com.portal.bean.result;

import com.portal.bean.StorehouseOperateInfo;
import com.portal.common.util.DateUtil;
import com.portal.common.util.StringUtil;

public class StorehouseOperateInfoForm extends StorehouseOperateInfo {

    private static final long serialVersionUID = 1L;
    
    /**
     * 订单号
     */
    private String orderNumber;
    
    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
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

}