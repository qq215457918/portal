package com.portal.bean;

import java.io.Serializable;
import java.util.Date;

public class ButtPerforDetailInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private String id;

    /**
     * 客服ID
     */
    private String phoneStaffId;

    /**
     * 客服姓名
     */
    private String phoneStaffName;

    /**
     * 客服所属机构名称
     */
    private String phoneStaffGroupName;

    /**
     * 接待姓名
     */
    private String receiveStaffName;

    /**
     * 成单接待数
     */
    private Integer receiveFinishedCounts;

    /**
     * 成单出单数
     */
    private Integer outOrdersOfFinished;

    /**
     * 成单出单率
     */
    private String outOrderRateOfFinished;

    /**
     * 成单业绩
     */
    private Long performanceOfFinished;

    /**
     * 成单单均
     */
    private String orderAvgOfFinished;

    /**
     * 成单件均
     */
    private String pieceAvgOfFinished;

    /**
     * 锁定接待数
     */
    private Integer receiveLockedCounts;

    /**
     * 锁定出单数
     */
    private Integer outOrdersOfLocked;

    /**
     * 锁定出单率
     */
    private String outOrderRateOfLocked;

    /**
     * 锁定业绩
     */
    private Long performanceOfLocked;

    /**
     * 锁定单均
     */
    private String orderAvgOfLocked;

    /**
     * 锁定件均
     */
    private String pieceAvgOfLocked;

    /**
     * 单均产品件数
     */
    private String orderAvgOfGoodsCounts;

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
     * @return 客服ID
     */
    public String getPhoneStaffId() {
        return phoneStaffId;
    }

    /**
     * @param phoneStaffId 
	 *            客服ID
     */
    public void setPhoneStaffId(String phoneStaffId) {
        this.phoneStaffId = phoneStaffId;
    }

    /**
     * @return 客服姓名
     */
    public String getPhoneStaffName() {
        return phoneStaffName;
    }

    /**
     * @param phoneStaffName 
	 *            客服姓名
     */
    public void setPhoneStaffName(String phoneStaffName) {
        this.phoneStaffName = phoneStaffName;
    }

    /**
     * @return 客服所属机构名称
     */
    public String getPhoneStaffGroupName() {
        return phoneStaffGroupName;
    }

    /**
     * @param phoneStaffGroupName 
	 *            客服所属机构名称
     */
    public void setPhoneStaffGroupName(String phoneStaffGroupName) {
        this.phoneStaffGroupName = phoneStaffGroupName;
    }

    /**
     * @return 接待姓名
     */
    public String getReceiveStaffName() {
        return receiveStaffName;
    }

    /**
     * @param receiveStaffName 
	 *            接待姓名
     */
    public void setReceiveStaffName(String receiveStaffName) {
        this.receiveStaffName = receiveStaffName;
    }

    /**
     * @return 成单接待数
     */
    public Integer getReceiveFinishedCounts() {
        return receiveFinishedCounts;
    }

    /**
     * @param receiveFinishedCounts 
	 *            成单接待数
     */
    public void setReceiveFinishedCounts(Integer receiveFinishedCounts) {
        this.receiveFinishedCounts = receiveFinishedCounts;
    }

    /**
     * @return 成单出单数
     */
    public Integer getOutOrdersOfFinished() {
        return outOrdersOfFinished;
    }

    /**
     * @param outOrdersOfFinished 
	 *            成单出单数
     */
    public void setOutOrdersOfFinished(Integer outOrdersOfFinished) {
        this.outOrdersOfFinished = outOrdersOfFinished;
    }

    /**
     * @return 成单出单率
     */
    public String getOutOrderRateOfFinished() {
        return outOrderRateOfFinished;
    }

    /**
     * @param outOrderRateOfFinished 
	 *            成单出单率
     */
    public void setOutOrderRateOfFinished(String outOrderRateOfFinished) {
        this.outOrderRateOfFinished = outOrderRateOfFinished;
    }

    /**
     * @return 成单业绩
     */
    public Long getPerformanceOfFinished() {
        return performanceOfFinished;
    }

    /**
     * @param performanceOfFinished 
	 *            成单业绩
     */
    public void setPerformanceOfFinished(Long performanceOfFinished) {
        this.performanceOfFinished = performanceOfFinished;
    }

    /**
     * @return 成单单均
     */
    public String getOrderAvgOfFinished() {
        return orderAvgOfFinished;
    }

    /**
     * @param orderAvgOfFinished 
	 *            成单单均
     */
    public void setOrderAvgOfFinished(String orderAvgOfFinished) {
        this.orderAvgOfFinished = orderAvgOfFinished;
    }

    /**
     * @return 成单件均
     */
    public String getPieceAvgOfFinished() {
        return pieceAvgOfFinished;
    }

    /**
     * @param pieceAvgOfFinished 
	 *            成单件均
     */
    public void setPieceAvgOfFinished(String pieceAvgOfFinished) {
        this.pieceAvgOfFinished = pieceAvgOfFinished;
    }

    /**
     * @return 锁定接待数
     */
    public Integer getReceiveLockedCounts() {
        return receiveLockedCounts;
    }

    /**
     * @param receiveLockedCounts 
	 *            锁定接待数
     */
    public void setReceiveLockedCounts(Integer receiveLockedCounts) {
        this.receiveLockedCounts = receiveLockedCounts;
    }

    /**
     * @return 锁定出单数
     */
    public Integer getOutOrdersOfLocked() {
        return outOrdersOfLocked;
    }

    /**
     * @param outOrdersOfLocked 
	 *            锁定出单数
     */
    public void setOutOrdersOfLocked(Integer outOrdersOfLocked) {
        this.outOrdersOfLocked = outOrdersOfLocked;
    }

    /**
     * @return 锁定出单率
     */
    public String getOutOrderRateOfLocked() {
        return outOrderRateOfLocked;
    }

    /**
     * @param outOrderRateOfLocked 
	 *            锁定出单率
     */
    public void setOutOrderRateOfLocked(String outOrderRateOfLocked) {
        this.outOrderRateOfLocked = outOrderRateOfLocked;
    }

    /**
     * @return 锁定业绩
     */
    public Long getPerformanceOfLocked() {
        return performanceOfLocked;
    }

    /**
     * @param performanceOfLocked 
	 *            锁定业绩
     */
    public void setPerformanceOfLocked(Long performanceOfLocked) {
        this.performanceOfLocked = performanceOfLocked;
    }

    /**
     * @return 锁定单均
     */
    public String getOrderAvgOfLocked() {
        return orderAvgOfLocked;
    }

    /**
     * @param orderAvgOfLocked 
	 *            锁定单均
     */
    public void setOrderAvgOfLocked(String orderAvgOfLocked) {
        this.orderAvgOfLocked = orderAvgOfLocked;
    }

    /**
     * @return 锁定件均
     */
    public String getPieceAvgOfLocked() {
        return pieceAvgOfLocked;
    }

    /**
     * @param pieceAvgOfLocked 
	 *            锁定件均
     */
    public void setPieceAvgOfLocked(String pieceAvgOfLocked) {
        this.pieceAvgOfLocked = pieceAvgOfLocked;
    }

    /**
     * @return 单均产品件数
     */
    public String getOrderAvgOfGoodsCounts() {
        return orderAvgOfGoodsCounts;
    }

    /**
     * @param orderAvgOfGoodsCounts 
	 *            单均产品件数
     */
    public void setOrderAvgOfGoodsCounts(String orderAvgOfGoodsCounts) {
        this.orderAvgOfGoodsCounts = orderAvgOfGoodsCounts;
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }
}