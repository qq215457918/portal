package com.portal.dao.extra;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.portal.bean.GroupInfo;

@Repository
public interface GroupInfoExtraDao {
    
    /**
     * @Title: getAllCompany 
     * @Description: 获取所有机构
     * @return List<GroupInfo>
     * @author Xia ZhengWei
     * @date 2016年12月25日 下午6:10:01 
     * @version V1.0
     */
    List<GroupInfo> getAllCompany();
    

}