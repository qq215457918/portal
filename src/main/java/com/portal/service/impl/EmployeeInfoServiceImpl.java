package com.portal.service.impl;

import com.portal.bean.Criteria;
import com.portal.bean.DailyEmployeeAudit;
import com.portal.bean.DailyEmployeeAuditHistory;
import com.portal.bean.EmployeeInfo;
import com.portal.bean.result.EmployeeInfoForm;
import com.portal.common.util.BeanUtils;
import com.portal.common.util.JsonUtils;
import com.portal.common.util.StringUtil;
import com.portal.common.util.UUidUtil;
import com.portal.dao.DailyEmployeeAuditDao;
import com.portal.dao.DailyEmployeeAuditHistoryDao;
import com.portal.dao.EmployeeInfoDao;
import com.portal.dao.extra.EmployeeInfoExtraDao;
import com.portal.service.EmployeeInfoService;
import com.portal.service.RoleService;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.sf.json.JSONObject;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeInfoServiceImpl implements EmployeeInfoService {
    @Autowired
    private EmployeeInfoDao employeeInfoDao;

    @Autowired
    private EmployeeInfoExtraDao employeeExtraDao;

    @Autowired
    private RoleService roleService;

    @Autowired
    private DailyEmployeeAuditDao auditDao;

    @Autowired
    private DailyEmployeeAuditHistoryDao auditHistoryDao;

    private static final Logger logger = LoggerFactory.getLogger(EmployeeInfoServiceImpl.class);

    Criteria criteria = new Criteria();

    public List<EmployeeInfo> getPhoneEmpByOrganization() {
        criteria.clear();
        EmployeeInfo employee = (EmployeeInfo) BeanUtils.getLoginUser();
        if (employee == null) {
            return null;
        }
        criteria.put("organizationId", employee.getOrganizationId());
        criteria.setOrderByClause("CONVERT(name USING gbk) asc");
        return selectByExample(criteria);
    }

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
        String roleIds = user.getRoleIds();
        if(roleIds.indexOf(',') == 0) {
            roleIds = roleIds.substring(1, roleIds.length());
        }
        String roleIdStr[] = roleIds.split(",");
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
        List<EmployeeInfo> example2 = selectByExample(example);
        if (CollectionUtils.isNotEmpty(example2)) {
            return example2.get(0);
        }
        return null;
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

    public List<EmployeeInfoForm> selectByConditions(Criteria example) {
        return employeeExtraDao.selectByConditions(example);
    }

    public JSONObject ajaxEmployeeData(Criteria criteria, String sEcho) {
        // 获取总记录数
        int totalRecord = employeeExtraDao.countByConditions(criteria);
        // 获取数据集
        List<EmployeeInfoForm> list = selectByConditions(criteria);

        JSONObject resultJson = new JSONObject();
        resultJson.put("sEcho", sEcho);
        resultJson.put("iTotalRecords", totalRecord);
        resultJson.put("iTotalDisplayRecords", totalRecord);
        resultJson.put("aaData", list);
        return resultJson;
    }

    public JSONObject deleteEmployeeInfo(String employeeId, JSONObject result) {
        EmployeeInfo employeeInfo = this.selectByPrimaryKey(employeeId);
        if("0".equals(employeeInfo.getDeleteFlag())) {
            // 逻辑删除--修改删除状态
            employeeInfo.setDeleteFlag("1");
            int count = this.updateByPrimaryKey(employeeInfo);
            if (count > 0) {
                // TODO - 判断是否有到该用户审批的流程, 直接结束流程
                // 每日业绩审批 走daily_employee_audit  daily_employee_audit_history
                criteria.clear();
                criteria.put("auditorId", employeeInfo.getId());
                List<DailyEmployeeAudit> auditList = auditDao.selectByExample(criteria);
                if (auditList.size() > 0) {
                    for (DailyEmployeeAudit dailyEmployeeAudit : auditList) {
                        dailyEmployeeAudit.setStatus("2");
                        count = auditDao.updateByPrimaryKey(dailyEmployeeAudit);
                        if (count > 0) {
                            DailyEmployeeAuditHistory auditHistory = new DailyEmployeeAuditHistory();
                            auditHistory.setId(UUidUtil.getUUId());
                            auditHistory.setAuditId(dailyEmployeeAudit.getId());
                            auditHistory.setAuditorId(employeeInfo.getId());
                            auditHistory.setAuditDate(new Date());
                            auditHistoryDao.insert(auditHistory);
                        }
                    }
                }
                result = JsonUtils.setSuccess();
            }else {
                result = JsonUtils.setError();
                result.put("text", "系统异常,请刷新后重试");
            }
        } else {
            result = JsonUtils.setError();
            result.put("text", "操作失败,该员工已被删除");
        }
        return result;
    }

    public JSONObject saveEmployeeInfo(EmployeeInfo employeeInfo, JSONObject results) {
        int count = 0;
        if (StringUtil.isNull(employeeInfo.getName().trim())) {
            results = JsonUtils.setError();
            results.put("text", "操作失败, 姓名不能为空");
            return results;
        } else {
            // 过滤特殊字符
            employeeInfo.setName(StringUtil.tstr(employeeInfo.getName().trim()));
        }
        if (StringUtil.isNull(employeeInfo.getLoginName().trim())) {
            results = JsonUtils.setError();
            results.put("text", "操作失败, 登录名不能为空");
            return results;
        } else {
            // 过滤特殊字符
            employeeInfo.setLoginName(StringUtil.tstr(employeeInfo.getLoginName().trim()));
        }
        if (StringUtil.isNull(employeeInfo.getPassword().trim())) {
            results = JsonUtils.setError();
            results.put("text", "操作失败, 密码不能为空");
            return results;
        } else {
            // 过滤特殊字符
            employeeInfo.setPassword(StringUtil.tstr(employeeInfo.getPassword().trim()));
        }
        if (StringUtil.isNull(employeeInfo.getPositionType())) {
            results = JsonUtils.setError();
            results.put("text", "操作失败, 职位类型必须选择一项");
            return results;
        }
        if (StringUtil.isNull(employeeInfo.getStatus())) {
            results = JsonUtils.setError();
            results.put("text", "操作失败, 账号状态必须选择一项");
            return results;
        }
        if (StringUtil.isNull(employeeInfo.getOrganizationId())) {
            results = JsonUtils.setError();
            results.put("text", "操作失败, 所属机构不能为空");
            return results;
        }
        if (StringUtil.isNotBlank(employeeInfo.getStaffNumber().trim())) {
            // 过滤特殊字符
            employeeInfo.setStaffNumber(StringUtil.tstr(employeeInfo.getStaffNumber().trim()));
        }
        
        String roleIds = employeeInfo.getRoleIds();
        if(StringUtil.isNotBlank(roleIds)) {
            StringBuilder builder = new StringBuilder();
            String[] roles = roleIds.split(",");
            for (String string : roles) {
                if(StringUtil.isNotBlank(string)) {
                    builder.append(",").append(string);
                }
            }
            builder.append(",");
            employeeInfo.setRoleIds(builder.toString());
        }
        
        if(StringUtil.isNotBlank(employeeInfo.getId())) {
            // 修改
            count = employeeInfoDao.updateByPrimaryKey(employeeInfo);
        } else {
            // 新增
            employeeInfo.setId(UUidUtil.getUUId());
            employeeInfo.setCreateDate(new Date());
            employeeInfo.setDeleteFlag("0");
            // TODO - 保存员工信息时设置默认头像路径
            employeeInfo.setPhotoPath("");
            employeeInfo.setReceptionFlag("0");
            count = employeeInfoDao.insert(employeeInfo);
        }
        if (count > 0) {
            results = JsonUtils.setSuccess();
        } else {
            results = JsonUtils.setError();
            results.put("text", "系统异常, 请刷新后重试");
        }
        return results;
    }

    public Map<String, String> getReceiveNameByPhoneId(Criteria criteria) {
        return employeeExtraDao.getReceiveNameByPhoneId(criteria);
    }

}
