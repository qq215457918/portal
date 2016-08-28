package com.portal.bean;

import java.io.Serializable;
import java.util.Date;

public class GoodsInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 种类ID
     */
    private String sortId;

    /**
     * 商品类型 0:常规商品 1：礼品 2：配售 3：配送 4:兑换
     */
    private String type;

    /**
     * 商品序号
     */
    private String code;

    /**
     * 产品名称
     */
    private String name;

    /**
     * 数量
     */
    private Integer amount;

    /**
     * 是否可托管
     */
    private String trusteeshipFlag;

    /**
     * 回购标志
     */
    private String repurchaseFlag;

    /**
     * 回购信息
     */
    private String repurchaseInfo;

    /**
     * 回购开始时间
     */
    private Date repurchaseStarttime;

    /**
     * 回购结束时间
     */
    private Date repurchaseEndtime;

    /**
     * 创建日期
     */
    private Date createDate;

    /**
     * 更新日期
     */
    private Date updateDate;

    /**
     * 创建人id
     */
    private String createUserid;

    /**
     * 更新人id
     */
    private String updateUserid;

    /**
     * 删除标志 0正常 1删除
     */
    private String deleteFlag;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return 种类ID
     */
    public String getSortId() {
        return sortId;
    }

    /**
     * @param sortId 
	 *            种类ID
     */
    public void setSortId(String sortId) {
        this.sortId = sortId;
    }

    /**
     * @return 商品类型 0:常规商品 1：礼品 2：配售 3：配送 4:兑换
     */
    public String getType() {
        return type;
    }

    /**
     * @param type 
	 *            商品类型 0:常规商品 1：礼品 2：配售 3：配送 4:兑换
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return 商品序号
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code 
	 *            商品序号
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return 产品名称
     */
    public String getName() {
        return name;
    }

    /**
     * @param name 
	 *            产品名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return 数量
     */
    public Integer getAmount() {
        return amount;
    }

    /**
     * @param amount 
	 *            数量
     */
    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    /**
     * @return 是否可托管
     */
    public String getTrusteeshipFlag() {
        return trusteeshipFlag;
    }

    /**
     * @param trusteeshipFlag 
	 *            是否可托管
     */
    public void setTrusteeshipFlag(String trusteeshipFlag) {
        this.trusteeshipFlag = trusteeshipFlag;
    }

    /**
     * @return 回购标志
     */
    public String getRepurchaseFlag() {
        return repurchaseFlag;
    }

    /**
     * @param repurchaseFlag 
	 *            回购标志
     */
    public void setRepurchaseFlag(String repurchaseFlag) {
        this.repurchaseFlag = repurchaseFlag;
    }

    /**
     * @return 回购信息
     */
    public String getRepurchaseInfo() {
        return repurchaseInfo;
    }

    /**
     * @param repurchaseInfo 
	 *            回购信息
     */
    public void setRepurchaseInfo(String repurchaseInfo) {
        this.repurchaseInfo = repurchaseInfo;
    }

    /**
     * @return 回购开始时间
     */
    public Date getRepurchaseStarttime() {
        return repurchaseStarttime;
    }

    /**
     * @param repurchaseStarttime 
	 *            回购开始时间
     */
    public void setRepurchaseStarttime(Date repurchaseStarttime) {
        this.repurchaseStarttime = repurchaseStarttime;
    }

    /**
     * @return 回购结束时间
     */
    public Date getRepurchaseEndtime() {
        return repurchaseEndtime;
    }

    /**
     * @param repurchaseEndtime 
	 *            回购结束时间
     */
    public void setRepurchaseEndtime(Date repurchaseEndtime) {
        this.repurchaseEndtime = repurchaseEndtime;
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
     * @return 更新日期
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * @param updateDate 
	 *            更新日期
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * @return 创建人id
     */
    public String getCreateUserid() {
        return createUserid;
    }

    /**
     * @param createUserid 
	 *            创建人id
     */
    public void setCreateUserid(String createUserid) {
        this.createUserid = createUserid;
    }

    /**
     * @return 更新人id
     */
    public String getUpdateUserid() {
        return updateUserid;
    }

    /**
     * @param updateUserid 
	 *            更新人id
     */
    public void setUpdateUserid(String updateUserid) {
        this.updateUserid = updateUserid;
    }

    /**
     * @return 删除标志 0正常 1删除
     */
    public String getDeleteFlag() {
        return deleteFlag;
    }

    /**
     * @param deleteFlag 
	 *            删除标志 0正常 1删除
     */
    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
}