package com.portal.bean;

import java.io.Serializable;
import java.util.Date;

public class SellDailyInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private String id;

    /**
     * 所属地区 1-大连 0-沈阳
     */
    private String area;

    /**
     * 统计日期
     */
    private Date reportDate;

    /**
     * 创建用户ID
     */
    private String createUserId;

    /**
     * 创建日期
     */
    private Date createDate;

    /**
     * 修改用户ID
     */
    private String updateUserId;

    /**
     * 修改日期
     */
    private Date updateDate;

    /**
     * @return 主键id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id 
	 *            主键id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return 所属地区 1-大连 0-沈阳
     */
    public String getArea() {
        return area;
    }

    /**
     * @param area 
	 *            所属地区 1-大连 0-沈阳
     */
    public void setArea(String area) {
        this.area = area;
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
     * @return 创建用户ID
     */
    public String getCreateUserId() {
        return createUserId;
    }

    /**
     * @param createUserId 
	 *            创建用户ID
     */
    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    /**
     * @return 创建日期
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * @param createDate 
	 *            创建日期
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * @return 修改用户ID
     */
    public String getUpdateUserId() {
        return updateUserId;
    }

    /**
     * @param updateUserId 
	 *            修改用户ID
     */
    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }

    /**
     * @return 修改日期
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * @param updateDate 
	 *            修改日期
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}