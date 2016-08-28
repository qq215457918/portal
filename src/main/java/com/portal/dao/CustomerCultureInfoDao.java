package com.portal.dao;

import com.portal.bean.Criteria;
import com.portal.bean.CustomerCultureInfo;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerCultureInfoDao {
    /**
     * 根据条件查询记录总数
     */
    int countByExample(Criteria example);

    /**
     * 根据条件删除记录
     */
    int deleteByExample(Criteria example);

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(String id);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(CustomerCultureInfo record);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(CustomerCultureInfo record);

    /**
     * 根据条件查询记录集
     */
    List<CustomerCultureInfo> selectByExample(Criteria example);

    /**
     * 根据主键查询记录
     */
    CustomerCultureInfo selectByPrimaryKey(String id);

    /**
     * 根据条件更新属性不为空的记录
     */
    int updateByExampleSelective(@Param("record") CustomerCultureInfo record, @Param("example") Criteria example);

    /**
     * 根据条件更新记录
     */
    int updateByExample(@Param("record") CustomerCultureInfo record, @Param("example") Criteria example);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(CustomerCultureInfo record);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(CustomerCultureInfo record);
}