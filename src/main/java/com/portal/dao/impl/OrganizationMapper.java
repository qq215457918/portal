package com.portal.dao.impl;

import com.portal.bean.Organization;
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
 * <p>Organization: Zhang Kaitao
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */

@Repository
public interface OrganizationMapper {

    /* @Insert("insert into sys_organization( name, parent_id, parent_ids, available) values(?,?,?,?)")
    public Organization createOrganization(final Organization organization) {
        final String sql = "insert into sys_organization( name, parent_id, parent_ids, available) values(?,?,?,?)";
    
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement psst = connection.prepareStatement(sql, new String[]{"id"});
                int count = 1;
                psst.setString(count++, organization.getName());
                psst.setLong(count++, organization.getParentId());
                psst.setString(count++, organization.getParentIds());
                psst.setBoolean(count++, organization.getAvailable());
                return psst;
            }
        }, keyHolder);
        organization.setId(keyHolder.getKey().longValue());
        return organization;
    }*/

    @Insert("insert into sys_organization(id, name, parent_id, parent_ids, available) values"
            + "(#{organization.id},#{organization.name},#{organization.parentId},#{organization.parentIds},#{organization.available})")
    @SelectKey(before = false, keyProperty = "organization.id", resultType = int.class,
            statementType = StatementType.STATEMENT,
            statement = "SELECT LAST_INSERT_ID() AS id")
    public int createOrganization(@Param("Organization") Organization organization);

    /*  @Override
    public Organization updateOrganization(Organization organization) {
        final String sql = "update sys_organization set name=?, parent_id=?, parent_ids=?, available=? where id=?";
        jdbcTemplate.update(
                sql,
                organization.getName(), organization.getParentId(), organization.getParentIds(), organization.getAvailable(), organization.getId());
        return organization;
    }*/

    @Update("update sys_organization set name=#{organization.name}, parent_id=#{organization.parentId}, parent_ids=#{organization.parentIds}, available=#{organization.available} where id=#{organization.id}")
    public int updateOrganization(@Param("Organization") Organization organization);

    /*public void deleteOrganization(Long organizationId) {
        Organization organization = findOne(organizationId);
        final String deleteSelfSql = "delete from sys_organization where id=?";
        jdbcTemplate.update(deleteSelfSql, organizationId);
        final String deleteDescendantsSql = "delete from sys_organization where parent_ids like ?";
        jdbcTemplate.update(deleteDescendantsSql, organization.makeSelfAsParentIds() + "%");
    }
    */
    @Delete("delete from sys_organization where id=#{organizationId}")
    public void deleteOrganization(Long organizationId);

    /*@Override
    public Organization findOne(Long organizationId) {
        final String sql = "select id, name, parent_id, parent_ids, available from sys_organization where id=?";
        List<Organization> organizationList = jdbcTemplate.query(sql, new BeanPropertyRowMapper(Organization.class), organizationId);
        if(organizationList.size() == 0) {
            return null;
        }
        return organizationList.get(0);
    }*/
    @Select("select id, name, parent_id, parent_ids, available from sys_organization where id=#{organizationId}")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column = "parent_id", property = "parentId"),
            @Result(column = "parent_ids", property = "parentIds"),
            @Result(column = "available", property = "available")
    })
    public List<Organization> findOne(Long organizationId);

    /* @Override
    public List<Organization> findAll() {
        final String sql = "select id, name, parent_id, parent_ids, available from sys_organization";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper(Organization.class));
    }*/

    @Select("select id, name, parent_id, parent_ids, available from sys_organization")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column = "parent_id", property = "parentId"),
            @Result(column = "parent_ids", property = "parentIds"),
            @Result(column = "available", property = "available")
    })
    public List<Organization> findAll();

    /* @Override
    public List<Organization> findAllWithExclude(Organization excludeOraganization) {
        //TODO 改成not exists 利用索引
        final String sql = "select id, name, parent_id, parent_ids, available from sys_organization where id!=? and parent_ids not like ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper(Organization.class), excludeOraganization.getId(), excludeOraganization.makeSelfAsParentIds() + "%");
    }*/

    /*@Override
    public void move(Organization source, Organization target) {
        String moveSourceSql = "update sys_organization set parent_id=?,parent_ids=? where id=?";
        jdbcTemplate.update(moveSourceSql, target.getId(), target.getParentIds(), source.getId());
        String moveSourceDescendantsSql = "update sys_organization set parent_ids=concat(?, substring(parent_ids, length(?))) where parent_ids like ?";
        jdbcTemplate.update(moveSourceDescendantsSql, target.makeSelfAsParentIds(), source.makeSelfAsParentIds(), source.makeSelfAsParentIds() + "%");
    }*/
}
