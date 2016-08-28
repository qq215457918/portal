package com.portal.bean;

import java.io.Serializable;
import java.util.Date;

public class ReportTrack implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 客户id
     */
    private Date reportDate;

    /**
     * 客户类型 :0锁定 1 成单 2重复登门 3新登门 4说明会<br>
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

    private Date createDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return 客户id
     */
    public Date getReportDate() {
        return reportDate;
    }

    /**
     * @param reportDate 
	 *            客户id
     */
    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    /**
     * @return 客户类型 :0锁定 1 成单 2重复登门 3新登门 4说明会<br>
	 * 
     */
    public String getCustomerType() {
        return customerType;
    }

    /**
     * @param customerType 
	 *            客户类型 :0锁定 1 成单 2重复登门 3新登门 4说明会<br>
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}