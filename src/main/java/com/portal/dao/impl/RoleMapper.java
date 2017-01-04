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

    /*    public Role createRole(final Role role) {
        final String sql = "insert into sys_role(role, description, resource_ids, available) values(?,?,?,?)";
    
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement psst = connection.prepareStatement(sql, new String[] { "id" });
                int count = 1;
                psst.setString(count ++, role.getRole());
                psst.setString(count ++, role.getDescription());
                psst.setString(count ++, role.getResourceIdsStr());
                psst.setBoolean(count ++, role.getAvailable());
                return psst;
            }
        }, keyHolder);
        role.setId(keyHolder.getKey().longValue());
        return role;
    }*/

    @Insert("insert into sys_role(id,role, description, resource_ids, available) values(#{role.id},#{role.role},#{role.description},#{role.resourceIds},#{role.available})")
    @SelectKey(before = false, keyProperty = "role.id", resultType = int.class,
            statementType = StatementType.STATEMENT,
            statement = "SELECT LAST_INSERT_ID() AS id")
    public int createRole(@Param("Role") Role role);

    /*    @Override
    public Role updateRole(Role role) {
        final String sql = "update sys_role set role=?, description=?, resource_ids=?, available=? where id=?";
        jdbcTemplate.update(
                sql,
                role.getRole(), role.getDescription(), role.getResourceIdsStr(), role.getAvailable(),
                role.getId());
        return role;
    }*/

    @Update("update sys_role set role=#{role.role}, description=#{role.description}, resource_ids=#{role.resourceIds}, available=#{role.available} where id=#{role.id}")
    public int updateRole(@Param("Role") Role role);

    /*  public void deleteRole(Long roleId) {
        final String sql = "delete from sys_role where id=?";
        jdbcTemplate.update(sql, roleId);
    }*/

    @Delete("delete from sys_role where id=#{roleId}")
    public void deleteRole(Long roleId);

    /* @Override
    public Role findOne(Long roleId) {
        final String sql =
                "select id, role, description, resource_ids as resourceIdsStr, available from sys_role where id=?";
        List<Role> roleList = jdbcTemplate.query(sql, new BeanPropertyRowMapper(Role.class), roleId);
        if (roleList.size() == 0) {
            return null;
        }
        return roleList.get(0);
    }*/
    @Select("select id, role, description, resource_ids, available from sys_role where id=#{roleId}")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "role", property = "role"),
            @Result(column = "description", property = "description"),
            @Result(column = "resource_ids", property = "resourceIdsStr"),
            @Result(column = "available", property = "available")
    })
    public List<Role> findOne(Long roleId);

    /* @Override
    public List<Role> findAll() {
        final String sql =
                "select id, role, description, resource_ids as resourceIdsStr, available from sys_role";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper(Role.class));
    }
    */
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
