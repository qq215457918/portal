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
    
    /**
     * @Title: addGroupInfo 
     * @Description: 新增组织机构部门信息
     * @param record
     * @return int
     * @author Xia ZhengWei
     * @date 2017年3月7日 下午11:39:04 
     * @version V1.0
     */
    int addGroupInfo(GroupInfo record);

}