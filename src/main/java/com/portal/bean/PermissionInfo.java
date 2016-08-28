package com.portal.bean;

import java.io.Serializable;
import java.util.Date;

public class PermissionInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 菜单url
     */
    private String menuUrl;

    private Date createDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return 菜单url
     */
    public String getMenuUrl() {
        return menuUrl;
    }

    /**
     * @param menuUrl 
	 *            菜单url
     */
    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}