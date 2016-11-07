package com.portal.service;

import com.portal.bean.Criteria;
import com.portal.bean.CustomerCultureInfo;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public interface CustomerCultureInfoService {

    /**
     * 修改文交所信息
     * @param request
     * @return
     */
    public int updateCulture(HttpServletRequest request);

    boolean getCultureInfo(String cId);

    int countByExample(Criteria example);

    CustomerCultureInfo selectByPrimaryKey(String id);

    List<CustomerCultureInfo> selectByExample(Criteria example);

    int deleteByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CustomerCultureInfo record);

    int updateByPrimaryKey(CustomerCultureInfo record);

    int deleteByExample(Criteria example);

    int updateByExampleSelective(CustomerCultureInfo record, Criteria example);

    int updateByExample(CustomerCultureInfo record, Criteria example);

    int insert(CustomerCultureInfo record);

    int insertSelective(CustomerCultureInfo record);
}
