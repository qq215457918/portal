package com.portal.bean.result;

import com.portal.bean.PaymentAccountInfo;
import com.portal.common.util.StringUtil;

public class PaymentAccountInfoFom extends PaymentAccountInfo {

    private static final long serialVersionUID = 1L;
    
    /**
     * 显示是否可用
     */
    private String viewIsUsable;
    
    /**
     * 收款账户所属区域
     */
    private String organization;

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

    public String getOrganization() {
        if(StringUtil.isNotBlank(super.getOrganizationId())) {
            if("1".equals(super.getOrganizationId())) {
                return "大连";
            }else {
                return "沈阳";
            }
        }
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }
    
    
}