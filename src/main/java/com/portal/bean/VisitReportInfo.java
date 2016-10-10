package com.portal.bean;

import java.io.Serializable;
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
     * 空白客户数量
     */
    private Integer newCounts;

    /**
     * 重复登门客户数量
     */
    private Integer repeatCounts;

    /**
     * 说明会客户数量
     */
    private Integer roadshowCounts;

    /**
     * 成单客户数量
     */
    private Integer finishOrderCounts;

    /**
     * 锁定客户数量
     */
    private Integer lockedCounts;

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
     * @return 空白客户数量
     */
    public Integer getNewCounts() {
        return newCounts;
    }

    /**
     * @param newCounts 
	 *            空白客户数量
     */
    public void setNewCounts(Integer newCounts) {
        this.newCounts = newCounts;
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
}