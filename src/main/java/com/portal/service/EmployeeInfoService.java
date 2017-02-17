package com.portal.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.portal.bean.Criteria;
import com.portal.bean.EmployeeInfo;
import com.portal.bean.result.EmployeeInfoForm;

import net.sf.json.JSONObject;

public interface EmployeeInfoService {

    /**
     * 根据用户名查找其角色
     * @param username
     * @return
     */
    public Set<String> findRoles(String username);

    /**
     * 根据用户名查找其权限
     * @param username
     * @return
     */
    public Set<String> findPermissions(String username);

    /**
     * 验证用户是否有效
     * @param loginName
     * @param password
     * @return
     */
    public EmployeeInfo authentication(String loginName, String password);

    /**
     * 通过登陆名获取userInfo
     * @param loginName
     * @return
     */
    public EmployeeInfo selectByUserName(String loginName);

    int countByExample(Criteria example);

    EmployeeInfo selectByPrimaryKey(String id);

    List<EmployeeInfo> selectByExample(Criteria example);

    int deleteByPrimaryKey(String id);

    int updateByPrimaryKeySelective(EmployeeInfo record);

    int updateByPrimaryKey(EmployeeInfo record);

    int deleteByExample(Criteria example);

    int updateByExampleSelective(EmployeeInfo record, Criteria example);

    int updateByExample(EmployeeInfo record, Criteria example);

    int insert(EmployeeInfo record);

    int insertSelective(EmployeeInfo record);
    
    /**
     * @Title: selectByConditions 
     * @Description: 根据条件自定义方法获取数据
     * @param example
     * @return List<EmployeeInfoForm>
     * @author Xia ZhengWei
     * @date 2017年1月3日 下午8:42:25 
     * @version V1.0
     */
    List<EmployeeInfoForm> selectByConditions(Criteria example);
    
    /**
     * @Title: ajaxEmployeeData 
     * @Description: 异步获取员工数据
     * @param example
     * @param sEcho
     * @return JSONObject
     * @author Xia ZhengWei
     * @date 2016年12月20日 下午10:54:57 
     * @version V1.0
     */
    JSONObject ajaxEmployeeData(Criteria example, String sEcho);
    
    /**
     * @Title: saveEmployeeInfo 
     * @Description: 保存员工信息
     * @param employeeInfo 员工对象信息
     * @param results 返回结果
     * @return JSONObject
     * @author Xia ZhengWei
     * @date 2016年12月25日 下午7:05:04 
     * @version V1.0
     */
    JSONObject saveEmployeeInfo(EmployeeInfo employeeInfo, JSONObject results);
    
    /**
     * @Title: deleteEmployeeInfo 
     * @Description: 删除员工信息并解除权限关系
     * @param employeeId 员工ID
     * @param result 返回结果
     * @return JSONObject
     * @author Xia ZhengWei
     * @date 2016年12月22日 下午11:58:46 
     * @version V1.0
     */
    JSONObject deleteEmployeeInfo(String employeeId, JSONObject result);
    
    /**
     * @Title: getReceiveNameByPhoneId 
     * @Description: 统计每周对接业绩根据客服ID获取接待信息
     * @param criteria
     * @return Map<String, String>
     * @author Xia ZhengWei
     * @date 2017年2月13日 下午9:55:26 
     * @version V1.0
     */
    Map<String, String> getReceiveNameByPhoneId(Criteria criteria);
}
