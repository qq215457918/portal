package com.portal.bean;

import java.io.Serializable;
import java.util.Date;

public class GroupInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 上级ID
     */
    private String parentsId;

    private String name;

    private Date createDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return 上级ID
     */
    public String getParentsId() {
        return parentsId;
    }

    /**
     * @param parentsId 
	 *            上级ID
     */
    public void setParentsId(String parentsId) {
        this.parentsId = parentsId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}