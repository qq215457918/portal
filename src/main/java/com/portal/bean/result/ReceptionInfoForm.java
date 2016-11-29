package com.portal.bean.result;

import com.portal.common.util.StringUtil;
import java.io.Serializable;

public class ReceptionInfoForm implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;

    private String customerId;

    private String customerName;

    private String receiverStaffId;

    private String receiverStaffName;

    private String startTime;

    private String endTime;

    private String orderId;

    private String presentOrderId;

    private String presentName;

    private String createDate;

    /**
     * 报表-登门出单统计中-客服姓名
     */
    private String phoneStaffName;

    /**
     * 报表-登门出单统计中-客户类型
     */
    private String type;

    /**
     * 报表-登门出单统计中-客户联系电话
     */
    private String phone;

    /**
     * 报表-登门出单统计中-关联客户姓名
     */
    private String relationName;

    /**
     * 报表-登门出单统计中-客户生日
     */
    private String birthday;

    /**
     * 报表-登门出单统计中-客户地址
     */
    private String area;

    /**
     * 报表-登门出单统计中-会员卡号
     */
    private String vipCard;

    /**
     * 报表-登门出单统计中-出单数量
     */
    private String orderCount;
    
    /**
     * 报表-共接待时长
     */
    private String totalTime;

    
    public String getPresentName() {
        return presentName;
    }

    public void setPresentName(String presentName) {
        this.presentName = presentName;
    }
    
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getPresentOrderId() {
        return presentOrderId;
    }

    public void setPresentOrderId(String presentOrderId) {
        this.presentOrderId = presentOrderId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
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

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public String getPhoneStaffName() {
        return phoneStaffName;
    }

    public void setPhoneStaffName(String phoneStaffName) {
        this.phoneStaffName = phoneStaffName;
    }

    public String getType() {
        if (StringUtil.isNotBlank(type)) {
            if ("0".equals(type)) {
                type = "空白";
            } else if ("1".equals(type)) {
                type = "重复";
            } else if ("2".equals(type)) {
                type = "说明会";
            } else if ("3".equals(type)) {
                type = "成单";
            } else if ("4".equals(type)) {
                type = "锁定";
            } else if ("5".equals(type)) {
                type = "转介绍";
            }
        }
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRelationName() {
        return relationName;
    }

    public void setRelationName(String relationName) {
        this.relationName = relationName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getArea() {
        if (StringUtil.isNotBlank(this.area)) {
            if ("1".equals(this.area)) {
                return "大连";
            } else {
                return "沈阳";
            }
        } else {
            return area;
        }
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getVipCard() {
        return vipCard;
    }

    public void setVipCard(String vipCard) {
        this.vipCard = vipCard;
    }

    public String getOrderCount() {
        if ("0".equals(orderCount)) {
            orderCount = "";
        }
        return orderCount;
    }

    public void setOrderCount(String orderCount) {
        this.orderCount = orderCount;
    }
    
    public String getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(String totalTime) {
        this.totalTime = totalTime;
    }

}
