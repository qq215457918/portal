package com.portal.bean.result;

import com.portal.bean.PaymentAccountInfo;
import com.portal.common.util.StringUtil;

public class PaymentAccountInfoFom extends PaymentAccountInfo {

    private static final long serialVersionUID = 1L;
    
    /**
     * 显示是否可用
     */
    private String viewIsUsable;

    public String getViewIsUsable() {
        if(StringUtil.isNotBlank(super.getIsUsable())) {
            if("1".equals(super.getIsUsable())) {
                return "可用";
            }else {
                return "不可用";
            }
        }
        return viewIsUsable;
    }

    public void setViewIsUsable(String viewIsUsable) {
        this.viewIsUsable = viewIsUsable;
    }
    
    
}