package com.portal.bean;

import java.io.Serializable;
import java.math.BigDecimal; 
import java.util.Date;

public class VisitReportInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private String id;

    /**
     * 统计日期
     */
    private Date reportDate;

    /**
     * 接待者ID
     */
    private String receiverStaffId;

    /**
     * 接待者名称
     */
    private String receiverStaffName;

    /**
     * 接待者所属区域 0--沈阳 1--大连
     */
    private String receiverArea;

    /**
     * 客户总数量
     */
    private Integer customerCounts;

    /**
     * 新客户数量
     */
    private Integer newCounts;

    /**
     * 新客户订单数
     */
    private Integer newOrders;

    /**
     * 新客户订单金额
     */
    private BigDecimal newAmounts;

    /**
     * 重复登门客户数量
     */
    private Integer repeatCounts;

    /**
     * 重复登门订单数
     */
    private Integer repeatOrders;

    /**
     * 重复登门订单金额
     */
    private BigDecimal repeatAmounts;

    /**
     * 说明会客户数量
     */
    private Integer roadshowCounts;

    /**
     * 说明会订单数量
     */
    private Integer roadshowOrders;

    /**
     * 说明会订单金额
     */
    private BigDecimal roadshowAmounts;

    /**
     * 成单客户数量
     */
    private Integer finishOrderCounts;

    /**
     * 成单客户订单数量
     */
    private Integer finishOrders;

    /**
     * 成单客户订单金额
     */
    private BigDecimal finishAmounts;

    /**
     * 锁定客户数量
     */
    private Integer lockedCounts;

    /**
     * 锁定客户订单数量
     */
    private Integer lockedOrders;

    /**
     * 锁定客户订单金额
     */
    private BigDecimal lockedAmounts;

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
     * @return 统计日期
     */
    public Date getReportDate() {
        return reportDate;
    }

    /**
     * @param reportDate 
	 *            统计日期
     */
    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    /**
     * @return 接待者ID
     */
    public String getReceiverStaffId() {
        return receiverStaffId;
    }

    /**
     * @param receiverStaffId 
	 *            接待者ID
     */
    public void setReceiverStaffId(String receiverStaffId) {
        this.receiverStaffId = receiverStaffId;
    }

    /**
     * @return 接待者名称
     */
    public String getReceiverStaffName() {
        return receiverStaffName;
    }

    /**
     * @param receiverStaffName 
	 *            接待者名称
     */
    public void setReceiverStaffName(String receiverStaffName) {
        this.receiverStaffName = receiverStaffName;
    }

    /**
     * @return 接待者所属区域 0--沈阳 1--大连
     */
    public String getReceiverArea() {
        return receiverArea;
    }

    /**
     * @param receiverArea 
	 *            接待者所属区域 0--沈阳 1--大连
     */
    public void setReceiverArea(String receiverArea) {
        this.receiverArea = receiverArea;
    }

    /**
     * @return 客户总数量
     */
    public Integer getCustomerCounts() {
        return customerCounts;
    }

    /**
     * @param customerCounts 
	 *            客户总数量
     */
    public void setCustomerCounts(Integer customerCounts) {
        this.customerCounts = customerCounts;
    }

    /**
     * @return 新客户数量
     */
    public Integer getNewCounts() {
        return newCounts;
    }

    /**
     * @param newCounts 
	 *            新客户数量
     */
    public void setNewCounts(Integer newCounts) {
        this.newCounts = newCounts;
    }

    /**
     * @return 新客户订单数
     */
    public Integer getNewOrders() {
        return newOrders;
    }

    /**
     * @param newOrders 
	 *            新客户订单数
     */
    public void setNewOrders(Integer newOrders) {
        this.newOrders = newOrders;
    }

    /**
     * @return 新客户订单金额
     */
    public BigDecimal getNewAmounts() {
        return newAmounts;
    }

    /**
     * @param newAmounts 
	 *            新客户订单金额
     */
    public void setNewAmounts(BigDecimal newAmounts) {
        this.newAmounts = newAmounts;
    }

    /**
     * @return 重复登门客户数量
     */
    public Integer getRepeatCounts() {
        return repeatCounts;
    }

    /**
     * @param repeatCounts 
	 *            重复登门客户数量
     */
    public void setRepeatCounts(Integer repeatCounts) {
        this.repeatCounts = repeatCounts;
    }

    /**
     * @return 重复登门订单数
     */
    public Integer getRepeatOrders() {
        return repeatOrders;
    }

    /**
     * @param repeatOrders 
	 *            重复登门订单数
     */
    public void setRepeatOrders(Integer repeatOrders) {
        this.repeatOrders = repeatOrders;
    }

    /**
     * @return 重复登门订单金额
     */
    public BigDecimal getRepeatAmounts() {
        return repeatAmounts;
    }

    /**
     * @param repeatAmounts 
	 *            重复登门订单金额
     */
    public void setRepeatAmounts(BigDecimal repeatAmounts) {
        this.repeatAmounts = repeatAmounts;
    }

    /**
     * @return 说明会客户数量
     */
    public Integer getRoadshowCounts() {
        return roadshowCounts;
    }

    /**
     * @param roadshowCounts 
	 *            说明会客户数量
     */
    public void setRoadshowCounts(Integer roadshowCounts) {
        this.roadshowCounts = roadshowCounts;
    }

    /**
     * @return 说明会订单数量
     */
    public Integer getRoadshowOrders() {
        return roadshowOrders;
    }

    /**
     * @param roadshowOrders 
	 *            说明会订单数量
     */
    public void setRoadshowOrders(Integer roadshowOrders) {
        this.roadshowOrders = roadshowOrders;
    }

    /**
     * @return 说明会订单金额
     */
    public BigDecimal getRoadshowAmounts() {
        return roadshowAmounts;
    }

    /**
     * @param roadshowAmounts 
	 *            说明会订单金额
     */
    public void setRoadshowAmounts(BigDecimal roadshowAmounts) {
        this.roadshowAmounts = roadshowAmounts;
    }

    /**
     * @return 成单客户数量
     */
    public Integer getFinishOrderCounts() {
        return finishOrderCounts;
    }

    /**
     * @param finishOrderCounts 
	 *            成单客户数量
     */
    public void setFinishOrderCounts(Integer finishOrderCounts) {
        this.finishOrderCounts = finishOrderCounts;
    }

    /**
     * @return 成单客户订单数量
     */
    public Integer getFinishOrders() {
        return finishOrders;
    }

    /**
     * @param finishOrders 
	 *            成单客户订单数量
     */
    public void setFinishOrders(Integer finishOrders) {
        this.finishOrders = finishOrders;
    }

    /**
     * @return 成单客户订单金额
     */
    public BigDecimal getFinishAmounts() {
        return finishAmounts;
    }

    /**
     * @param finishAmounts 
	 *            成单客户订单金额
     */
    public void setFinishAmounts(BigDecimal finishAmounts) {
        this.finishAmounts = finishAmounts;
    }

    /**
     * @return 锁定客户数量
     */
    public Integer getLockedCounts() {
        return lockedCounts;
    }

    /**
     * @param lockedCounts 
	 *            锁定客户数量
     */
    public void setLockedCounts(Integer lockedCounts) {
        this.lockedCounts = lockedCounts;
    }

    /**
     * @return 锁定客户订单数量
     */
    public Integer getLockedOrders() {
        return lockedOrders;
    }

    /**
     * @param lockedOrders 
	 *            锁定客户订单数量
     */
    public void setLockedOrders(Integer lockedOrders) {
        this.lockedOrders = lockedOrders;
    }

    /**
     * @return 锁定客户订单金额
     */
    public BigDecimal getLockedAmounts() {
        return lockedAmounts;
    }

    /**
     * @param lockedAmounts 
	 *            锁定客户订单金额
     */
    public void setLockedAmounts(BigDecimal lockedAmounts) {
        this.lockedAmounts = lockedAmounts;
    }
}