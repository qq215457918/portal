package com.portal.bean;

import java.io.Serializable;
import java.util.Date;

public class DeptPerformanceInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private String id;

    /**
     * 员工ID
     */
    private String employeeId;

    /**
     * 业绩
     */
    private Long performance;

    /**
     * 件数
     */
    private Integer orderAmounts;

    /**
     * 新客户数量
     */
    private Integer newCustomerCounts;

    /**
     * 统计时间
     */
    private Date reportDate;

    /**
     * @return 主键ID
     */
    public String getId() {
        return id;
    }

    /**
     * @param id 
	 *            主键ID
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return 员工ID
     */
    public String getEmployeeId() {
        return employeeId;
    }

    /**
     * @param employeeId 
	 *            员工ID
     */
    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    /**
     * @return 业绩
     */
    public Long getPerformance() {
        return performance;
    }

    /**
     * @param performance 
	 *            业绩
     */
    public void setPerformance(Long performance) {
        this.performance = performance;
    }

    /**
     * @return 件数
     */
    public Integer getOrderAmounts() {
        return orderAmounts;
    }

    /**
     * @param orderAmounts 
	 *            件数
     */
    public void setOrderAmounts(Integer orderAmounts) {
        this.orderAmounts = orderAmounts;
    }

    /**
     * @return 新客户数量
     */
    public Integer getNewCustomerCounts() {
        return newCustomerCounts;
    }

    /**
     * @param newCustomerCounts 
	 *            新客户数量
     */
    public void setNewCustomerCounts(Integer newCustomerCounts) {
        this.newCustomerCounts = newCustomerCounts;
    }

    /**
     * @return 统计时间
     */
    public Date getReportDate() {
        return reportDate;
    }

    /**
     * @param reportDate 
	 *            统计时间
     */
    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }
}