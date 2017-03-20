package com.portal.dao.impl;

import com.portal.bean.Resource;
import com.portal.bean.Role;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.mapping.StatementType;
import org.springframework.stereotype.Repository;

/**
 * <p>Resource: Zhang Kaitao
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
@Repository
public interface ResourceMapper {

    @Insert("insert into sys_resource( name, type, url, permission, parent_id, parent_ids, available) values"
            + "(#{resource.name},#{resource.type},#{resource.url},#{resource.permission},#{resource.parentId},#{resource.parentIds},#{resource.available})")
    @SelectKey(before = false, keyProperty = "resource.id", resultType = Long.class,
            statementType = StatementType.STATEMENT,
            statement = "SELECT LAST_INSERT_ID() AS id")
    public int insertResource(@Param("resource") Resource resource);

    @Update("update sys_resource set name=#{resource.name}, type=#{resource.type}, url=#{resource.url}, "
            + "permission=#{resource.permission}, parent_id=#{resource.parentId}, parent_ids=#{resource.parentIds}, available=#{resource.available}"
            + " where id=#{resource.id}")
    public int updateResource(@Param("resource") Resource resource);

    @Delete("delete from sys_resource where id=#{roleId}")
    public int deleteResource(Long roleId);

    @Select("select id, name, type, url, permission, parent_id, parent_ids, available from sys_resource where id=#{roleId}")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column = "type", property = "type"),
            @Result(column = "url", property = "url"),
            @Result(column = "parent_id", property = "parentId"),
            @Result(column = "parent_ids", property = "parentIds"),
            @Result(column = "available", property = "available")
    })
    public List<Resource> findOne(Long roleId);

    @Select("select id, name, type, url, permission, parent_id, parent_ids, available from sys_resource order by concat(parent_ids, id) asc")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column = "type", property = "type"),
            @Result(column = "url", property = "url"),
            @Result(column = "parent_id", property = "parentId"),
            @Result(column = "parent_ids", property = "parentIds"),
            @Result(column = "available", property = "available")
    })
    public List<Resource> findAll();
    
    // 获取所有子菜单数量
    @Select("select id, name, type, url, permission, parent_id, parent_ids from sys_resource where parent_id = #{parentId}")
    @Results({
        @Result(id = true, column = "id", property = "id"),
        @Result(column = "name", property = "name"),
        @Result(column = "type", property = "type"),
        @Result(column = "url", property = "url"),
        @Result(column = "parent_id", property = "parentId"),
        @Result(column = "parent_ids", property = "parentIds")
    })
    public List<Resource> getChildCounts(Long parentId);
    
    @Select("select id, role, description, resource_ids from sys_role where resource_ids like CONCAT('%', #{resourcesId} ,'%')")
    @Results({
        @Result(id = true, column = "id", property = "id"),
        @Result(column = "role", property = "role"),
        @Result(column = "description", property = "description"),
        @Result(column = "resource_ids", property = "resourceIdsStr")
    })
    public List<Role> getPromissionCounts(String resourcesId);
    
}
