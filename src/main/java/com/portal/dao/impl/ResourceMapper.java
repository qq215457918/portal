package com.portal.dao.impl;

import com.portal.bean.Resource;
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

    /*   public Resource createResource(final Resource resource) {
        final String sql = "insert into sys_resource(name, type, url, permission, parent_id, parent_ids, available) values(?,?,?,?,?,?,?)";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement psst = connection.prepareStatement(sql, new String[]{"id"});
                int count = 1;
                psst.setString(count++, resource.getName());
                psst.setString(count++, resource.getType().name());
                psst.setString(count++, resource.getUrl());
                psst.setString(count++, resource.getPermission());
                psst.setLong(count++, resource.getParentId());
                psst.setString(count++, resource.getParentIds());
                psst.setBoolean(count++, resource.getAvailable());
                return psst;
            }
        }, keyHolder);
        resource.setId(keyHolder.getKey().longValue());
        return resource;
    }*/

    @Insert("insert into sys_resource(id, name, type, url, permission, parent_id, parent_ids, available) values"
            + "(#{resource.id},#{resource.name},#{resource.type},#{resource.url},#{resource.permission},#{resource.parentId},#{resource.parentIds},#{resource.available})")
    @SelectKey(before = false, keyProperty = "resource.id", resultType = int.class,
            statementType = StatementType.STATEMENT,
            statement = "SELECT LAST_INSERT_ID() AS id")
    public int createResource(@Param("Resource") Resource resource);

    /*
    @Override
    public Resource updateResource(Resource resource) {
        final String sql = "update sys_resource set name=?, type=?, url=?, permission=?, parent_id=?, parent_ids=?, available=? where id=?";
        jdbcTemplate.update(
                sql,
                resource.getName(), resource.getType().name(), resource.getUrl(), resource.getPermission(), resource.getParentId(), resource.getParentIds(), resource.getAvailable(), resource.getId());
        return resource;
    }*/

    @Update("update sys_resource set name=#{resource.name}, type=#{resource.type}, url=#{resource.url}, "
            + "permission=#{resource.permission}, parent_id=#{resource.parentId}, parent_ids=#{resource.parentIds}, available=#{resource.available}"
            + " where id=#{resource.id}")
    public int updateResource(@Param("Resource") Resource resource);

    /*
    public void deleteResource(Long resourceId) {
        Resource resource = findOne(resourceId);
        final String deleteSelfSql = "delete from sys_resource where id=?";
        jdbcTemplate.update(deleteSelfSql, resourceId);
        final String deleteDescendantsSql = "delete from sys_resource where parent_ids like ?";
        jdbcTemplate.update(deleteDescendantsSql, resource.makeSelfAsParentIds() + "%");
    }
    */
    @Delete("delete from sys_resource where id=#{roleId}")
    public void deleteResource(Long roleId);

    /*    @Override
    public Resource findOne(Long resourceId) {
        final String sql = "select id, name, type, url, permission, parent_id, parent_ids, available from sys_resource where id=?";
        List<Resource> resourceList = jdbcTemplate.query(sql, new BeanPropertyRowMapper(Resource.class), resourceId);
        if(resourceList.size() == 0) {
            return null;
        }
        return resourceList.get(0);
    }
    */
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

    /*    @Override
    public List<Resource> findAll() {
        final String sql = "select id, name, type, url, permission, parent_id, parent_ids, available from sys_resource order by concat(parent_ids, id) asc";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper(Resource.class));
    }
    */

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
}
