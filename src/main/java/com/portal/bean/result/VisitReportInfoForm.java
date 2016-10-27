package com.portal.bean.result;

import com.portal.bean.VisitReportInfo;

/**
 * @ClassName: VisitReportInfoForm 
 * @Description: 接待统计信息扩展对象（页面交互对象）
 * @author Xia ZhengWei
 * @date 2016年9月26日 下午10:15:27
 */
public class VisitReportInfoForm extends VisitReportInfo {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 业务员统计-总客户数
     */
    private Integer totalCounts;

    /**
     * 业务员统计-总单数
     */
    private Integer totalOrders;
    
    /**
     * 业务员统计-总业绩
     */
    private Integer totalAmounts;

    public Integer getTotalCounts() {
        return totalCounts;
    }

    public void setTotalCounts(Integer totalCounts) {
        this.totalCounts = totalCounts;
    }

    public Integer getTotalOrders() {
        return totalOrders;
    }

    public void setTotalOrders(Integer totalOrders) {
        this.totalOrders = totalOrders;
    }

    public Integer getTotalAmounts() {
        return totalAmounts;
    }

    public void setTotalAmounts(Integer totalAmounts) {
        this.totalAmounts = totalAmounts;
    }
    
}