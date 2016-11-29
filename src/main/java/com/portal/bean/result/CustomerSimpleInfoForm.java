package com.portal.bean.result;

import com.portal.common.util.StringUtil;
import java.io.Serializable;
import java.util.Date;

public class CustomerSimpleInfoForm implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 客户分类<br>
     * 0 空白客户<br>
     * 1 重复登门<br>
     * 2说明会<br>
     * 3成单<br>
     * 4锁定<br>
     * 
     */
    private String type;

    private String name;

    private String phoneStaffId;

    private String phoneStaffName;

    private String receiverStaffId;

    private String receiverStaffName;

    private String encryptPhone;

    private String phone;

    private String encryptPhone2;

    private String relationId;//关联亲友

    private String area;//地址

    private Date recentVisitDate;

    private Date recentExportDate;

    private String blacklistFlag;

    /**
     * 筛选客户类型--客户类型
     */
    private String viewType;

    /**
     * 筛选客户类型--登门量
     */
    private int visitCount;

    /**
     * 筛选客户类型--出单量
     */
    private int outOrderCount;

    /**
     * 筛选客户类型--出单金额
     */
    private Long outPrices;

    /**
     * 筛选客户类型--出单率
     */
    private String outRate;

    /**
     * 筛选客户类型--占总业绩百分比
     */
    private String totalPerforPercentage;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRelationId() {
        return relationId;
    }

    public void setRelationId(String relationId) {
        this.relationId = relationId;
    }

    public String getEncryptPhone2() {
        return encryptPhone2;
    }

    public void setEncryptPhone2(String encryptPhone2) {
        this.encryptPhone2 = encryptPhone2;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneStaffId() {
        return phoneStaffId;
    }

    public void setPhoneStaffId(String phoneStaffId) {
        this.phoneStaffId = phoneStaffId;
    }

    public String getPhoneStaffName() {
        return phoneStaffName;
    }

    public void setPhoneStaffName(String phoneStaffName) {
        this.phoneStaffName = phoneStaffName;
    }

    public String getReceiverStaffId() {
        return receiverStaffId;
    }

    public void setReceiverStaffId(String receiverStaffId) {
        this.receiverStaffId = receiverStaffId;
    }

    public String getReceiverStaffName() {
        return receiverStaffName;
    }

    public void setReceiverStaffName(String receiverStaffName) {
        this.receiverStaffName = receiverStaffName;
    }

    public String getEncryptPhone() {
        return encryptPhone;
    }

    public void setEncryptPhone(String encryptPhone) {
        this.encryptPhone = encryptPhone;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Date getRecentVisitDate() {
        return recentVisitDate;
    }

    public void setRecentVisitDate(Date recentVisitDate) {
        this.recentVisitDate = recentVisitDate;
    }

    public Date getRecentExportDate() {
        return recentExportDate;
    }

    public void setRecentExportDate(Date recentExportDate) {
        this.recentExportDate = recentExportDate;
    }

    public String getBlacklistFlag() {
        return blacklistFlag;
    }

    public void setBlacklistFlag(String blacklistFlag) {
        this.blacklistFlag = blacklistFlag;
    }

    public String getViewType() {
        if (StringUtil.isNotBlank(this.type)) {
            if ("1".equals(this.type)) {
                return "重复登门";
            } else if ("2".equals(this.type)) {
                return "说明会";
            } else if ("3".equals(this.type)) {
                return "成单";
            } else if ("4".equals(this.type)) {
                return "锁定";
            } else if ("5".equals(this.type)) {
                return "转介绍";
            }
        }
        return viewType;
    }

    public void setViewType(String viewType) {
        this.viewType = viewType;
    }

    public int getVisitCount() {
        return visitCount;
    }

    public void setVisitCount(int visitCount) {
        this.visitCount = visitCount;
    }

    public int getOutOrderCount() {
        return outOrderCount;
    }

    public void setOutOrderCount(int outOrderCount) {
        this.outOrderCount = outOrderCount;
    }

    public Long getOutPrices() {
        if (null == this.outPrices) {
            return 0L;
        } else {
            return outPrices;
        }
    }

    public void setOutPrices(Long outPrices) {
        this.outPrices = outPrices;
    }

    public String getOutRate() {
        if (StringUtil.isNotBlank(this.outRate)) {
            return outRate;
        } else {
            return "0";
        }
    }

    public void setOutRate(String outRate) {
        this.outRate = outRate;
    }

    public String getTotalPerforPercentage() {
        if (StringUtil.isNotBlank(this.totalPerforPercentage)) {
            return totalPerforPercentage;
        } else {
            return "0";
        }
    }

    public void setTotalPerforPercentage(String totalPerforPercentage) {
        this.totalPerforPercentage = totalPerforPercentage;
    }
}
