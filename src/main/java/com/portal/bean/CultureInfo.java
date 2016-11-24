package com.portal.bean;

import java.io.Serializable;
import java.util.Date;

public class CultureInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 文交所名称
     */
    private String name;

    /**
     * 描述信息
     */
    private String desc;

    private Date createDate;

    private String deleteFlag;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return 文交所名称
     */
    public String getName() {
        return name;
    }

    /**
     * @param name 
	 *            文交所名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return 描述信息
     */
    public String getDesc() {
        return desc;
    }

    /**
     * @param desc 
	 *            描述信息
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
}