package com.portal.bean;

import java.io.Serializable;
import java.util.Date;

public class ReportTrack implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 员工id
     */
    private String staffId;

    /**
     * 客户分类 0-空白客户 1-重复登门 2-说明会 3-成单 4-锁定
	 * 
     */
    private String customerType;

    /**
     * 登门数量
     */
    private Integer vistorCount;

    /**
     * 成单数
     */
    private Integer orderAmount;

    /**
     * 成单金额
     */
    private Long orderPrice;

    /**
     * 新客户总数
     */
    private Integer newChangeCustomer;

    /**
     * 报表时间
     */
    private Date reportDate;

    private Date createDate;

    /**
     * 审核状态 : 0未审核 1已审核
     */
    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return 员工id
     */
    public String getStaffId() {
        return staffId;
    }

    /**
     * @param staffId 
	 *            员工id
     */
    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    /**
     * @return 客户分类 0-空白客户 1-重复登门 2-说明会 3-成单 4-锁定
	 * 
     */
    public String getCustomerType() {
        return customerType;
    }

    /**
     * @param customerType 
	 *            客户分类 0-空白客户 1-重复登门 2-说明会 3-成单 4-锁定
	 * 
     */
    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    /**
     * @return 登门数量
     */
    public Integer getVistorCount() {
        return vistorCount;
    }

    /**
     * @param vistorCount 
	 *            登门数量
     */
    public void setVistorCount(Integer vistorCount) {
        this.vistorCount = vistorCount;
    }

    /**
     * @return 成单数
     */
    public Integer getOrderAmount() {
        return orderAmount;
    }

    /**
     * @param orderAmount 
	 *            成单数
     */
    public void setOrderAmount(Integer orderAmount) {
        this.orderAmount = orderAmount;
    }

    /**
     * @return 成单金额
     */
    public Long getOrderPrice() {
        return orderPrice;
    }

    /**
     * @param orderPrice 
	 *            成单金额
     */
    public void setOrderPrice(Long orderPrice) {
        this.orderPrice = orderPrice;
    }

    /**
     * @return 新客户总数
     */
    public Integer getNewChangeCustomer() {
        return newChangeCustomer;
    }

    /**
     * @param newChangeCustomer 
	 *            新客户总数
     */
    public void setNewChangeCustomer(Integer newChangeCustomer) {
        this.newChangeCustomer = newChangeCustomer;
    }

    /**
     * @return 报表时间
     */
    public Date getReportDate() {
        return reportDate;
    }

    /**
     * @param reportDate 
	 *            报表时间
     */
    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * @return 审核状态 : 0未审核 1已审核
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status 
	 *            审核状态 : 0未审核 1已审核
     */
    public void setStatus(String status) {
        this.status = status;
    }
}