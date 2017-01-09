package com.portal.bean.result;

import java.io.Serializable;
import java.util.Date;

import com.portal.common.util.DateUtil;
import com.portal.common.util.StringUtil;

public class GoodsInfoForm implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 种类ID
     */
    private String sortId;

    /**
     * 种类名称
     */
    private String sortName;

    /**
     * 种类描述
     */
    private String sortDescription;

    /**
     * 商品类型 0:常规商品 1：赠品 2：配售 3：配送 4:兑换
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
     * 单位
     */
    private String unit;

    /**
     * 价格
     */
    private Long price;

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
     * 表单字段-回购开始时间
     */
    private String viewRepurchaseStarttime;

    /**
     * 回购结束时间
     */
    private Date repurchaseEndtime;
    
    /**
     * 表单字段-回购结束时间
     */
    private String viewRepurchaseEndtime;

    /**
     * 创建日期
     */
    private Date createDate;
    
    /**
     * 表单字段-创建日期
     */
    private String viewCreateDate;

    /**
     * 更新日期
     */
    private Date updateDate;
    
    /**
     * 表单字段-创建日期
     */
    private String viewUpdateDate;

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
    
    /**
     * 后台商品管理-商品类型 0:常规商品 1：赠品 2：配售 3：配送 4:兑换
     */
    private String viewType;
    
    /**
     * 后台商品管理-是否可托管
     */
    private String viewTrusteeshipFlag;
    
    /**
     * 后台商品管理-回购标志
     */
    private String viewRepurchaseFlag;
    
    /**
     * 后台商品管理-商品种类ID（大类）
     */
    private String bigSortId;

    public String getViewRepurchaseStarttime() {
        if(this.repurchaseStarttime != null) {
            return DateUtil.formatDate(this.repurchaseStarttime, "yyyy-MM-dd");
        }else {
            return viewRepurchaseStarttime;
        }
    }

    public void setViewRepurchaseStarttime(String viewRepurchaseStarttime) {
        this.viewRepurchaseStarttime = viewRepurchaseStarttime;
    }

    public String getViewRepurchaseEndtime() {
        if(this.repurchaseEndtime != null) {
            return DateUtil.formatDate(this.repurchaseEndtime, "yyyy-MM-dd");
        }else {
            return viewRepurchaseEndtime;
        }
    }

    public void setViewRepurchaseEndtime(String viewRepurchaseEndtime) {
        this.viewRepurchaseEndtime = viewRepurchaseEndtime;
    }

    public String getViewCreateDate() {
        return viewCreateDate;
    }

    public void setViewCreateDate(String viewCreateDate) {
        this.viewCreateDate = viewCreateDate;
    }

    public String getViewUpdateDate() {
        return viewUpdateDate;
    }

    public void setViewUpdateDate(String viewUpdateDate) {
        this.viewUpdateDate = viewUpdateDate;
    }

    public String getBigSortId() {
        return bigSortId;
    }

    public void setBigSortId(String bigSortId) {
        this.bigSortId = bigSortId;
    }

    public String getViewType() {
        if(StringUtil.isNotBlank(this.type)) {
            switch (Integer.parseInt(this.type)) {
            case 0:
                return "常规商品";
            case 1:
                return "礼品";
            case 2:
                return "配售";
            case 3:
                return "配送";
            default:
                return "兑换";
            }
        }
        return viewType;
    }

    public void setViewType(String viewType) {
        this.viewType = viewType;
    }

    public String getViewTrusteeshipFlag() {
        if("1".equals(this.trusteeshipFlag) || "0".equals(this.trusteeshipFlag)) {
            if(StringUtil.isNotBlank(this.trusteeshipFlag)) {
                switch (Integer.parseInt(this.trusteeshipFlag)) {
                case 0:
                    return "否";
                default:
                    return "是";
                }
            }
            return viewTrusteeshipFlag;
        }else {
            return this.trusteeshipFlag;
        }
    }

    public void setViewTrusteeshipFlag(String viewTrusteeshipFlag) {
        this.viewTrusteeshipFlag = viewTrusteeshipFlag;
    }

    public String getViewRepurchaseFlag() {
        if(StringUtil.isNotBlank(this.repurchaseFlag)) {
            switch (Integer.parseInt(this.repurchaseFlag)) {
            case 0:
                return "否";
            default:
                return "是";
            }
        }
        return viewRepurchaseFlag;
    }

    public void setViewRepurchaseFlag(String viewRepurchaseFlag) {
        this.viewRepurchaseFlag = viewRepurchaseFlag;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    public String getSortDescription() {
        return sortDescription;
    }

    public void setSortDescription(String sortDescription) {
        this.sortDescription = sortDescription;
    }

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
     * @return 商品类型 0:常规商品 1：赠品 2：配售 3：配送 4:兑换
     */
    public String getType() {
        return type;
    }

    /**
     * @param type 
     *            商品类型 0:常规商品 1：赠品 2：配售 3：配送 4:兑换
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
