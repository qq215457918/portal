package com.portal.service.impl;

import com.portal.bean.Criteria;
import com.portal.bean.EmployeeInfo;
import com.portal.dao.EmployeeInfoDao;
import com.portal.service.EmployeeInfoService;
import com.portal.service.RoleService;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import org.apache.commons.beanutils.ConvertUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeInfoServiceImpl implements EmployeeInfoService {
    @Autowired
    private EmployeeInfoDao employeeInfoDao;

    @Autowired
    private RoleService roleService;

    private static final Logger logger = LoggerFactory.getLogger(EmployeeInfoServiceImpl.class);

    Criteria criteria = new Criteria();

    /**
     * 根据用户名查找其角色
     * @param username
     * @return
     */
    public Set<String> findRoles(String username) {
        EmployeeInfo user = selectByUserName(username);
        if (user == null) {
            return Collections.EMPTY_SET;
        }
        String roleIdStr[] = user.getRoleIds().split(",");
        Long roleIdsLong[] = new Long[roleIdStr.length];
        for (int i = 0; i < roleIdStr.length; i ++) {
            roleIdsLong[i] = Long.valueOf(roleIdStr[i]);
        }
        return roleService.findRoles(roleIdsLong);
    }

    /**
     * 根据用户名查找其权限
     * @param username
     * @return
     */
    public Set<String> findPermissions(String username) {
        EmployeeInfo user = selectByUserName(username);
        if (user == null) {
            return Collections.EMPTY_SET;
        }
        //        return roleService.findPermissions(user.getRoleIds().toArray(new Long[0]));

        return roleService
                .findPermissions((Long[]) ConvertUtils.convert(user.getRoleIds().split(","), Long.class));
    }

    public EmployeeInfo authentication(String loginName, String password) {
        criteria.clear();
        criteria.put("loginName", loginName);
        criteria.put("password", password);
        return selectByExample(criteria).get(0);
    }

    public EmployeeInfo selectByUserName(String loginName) {
        Criteria example = new Criteria();
        example.put("loginName", loginName);
        return selectByExample(example).get(0);
    }

    public int countByExample(Criteria example) {
        int count = this.employeeInfoDao.countByExample(example);
        logger.debug("count: {}", count);
        return count;
    }

    public EmployeeInfo selectByPrimaryKey(String id) {
        return this.employeeInfoDao.selectByPrimaryKey(id);
    }

    public List<EmployeeInfo> selectByExample(Criteria example) {
        return this.employeeInfoDao.selectByExample(example);
    }

    public int deleteByPrimaryKey(String id) {
        return this.employeeInfoDao.deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(EmployeeInfo record) {
        return this.employeeInfoDao.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(EmployeeInfo record) {
        return this.employeeInfoDao.updateByPrimaryKey(record);
    }

    public int deleteByExample(Criteria example) {
        return this.employeeInfoDao.deleteByExample(example);
    }

    public int updateByExampleSelective(EmployeeInfo record, Criteria example) {
        return this.employeeInfoDao.updateByExampleSelective(record, example);
    }

    public int updateByExample(EmployeeInfo record, Criteria example) {
        return this.employeeInfoDao.updateByExample(record, example);
    }

    public int insert(EmployeeInfo record) {
        return this.employeeInfoDao.insert(record);
    }

    public int insertSelective(EmployeeInfo record) {
        return this.employeeInfoDao.insertSelective(record);
    }
}
