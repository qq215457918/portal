package com.portal.dao.impl;

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
 * <p>Role: Zhang Kaitao
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
@Repository
public interface RoleMapper {

    @Insert("insert into sys_role(role, description, resource_ids, available) values(#{role.role},#{role.description}, #{resourceIdsStr}, #{role.available})")
    @SelectKey(before = false, keyProperty = "role.id", resultType = Long.class,
            statementType = StatementType.STATEMENT,
            statement = "SELECT LAST_INSERT_ID() AS id")
    public int insertRole(@Param("role") Role role, @Param("resourceIdsStr") String resourceIdsStr);

    @Update("update sys_role set role=#{role.role}, description=#{role.description}, resource_ids=#{resourceIdsStr}, available=#{role.available} where id=#{role.id}")
    public int updateRole(@Param("role") Role role, @Param("resourceIdsStr") String resourceIdsStr);

    @Delete("delete from sys_role where id=#{roleId}")
    public void deleteRole(Long roleId);

    @Select("select id, role, description, resource_ids, available from sys_role where id=#{roleId}")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "role", property = "role"),
            @Result(column = "description", property = "description"),
            @Result(column = "resource_ids", property = "resourceIdsStr"),
            @Result(column = "available", property = "available")
    })
    public List<Role> findOne(Long roleId);

    @Select("select id, role, description, resource_ids, available from sys_role")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "role", property = "role"),
            @Result(column = "description", property = "description"),
            @Result(column = "resource_ids", property = "resourceIdsStr"),
            @Result(column = "available", property = "available")
    })
    public List<Role> findAll();
}
