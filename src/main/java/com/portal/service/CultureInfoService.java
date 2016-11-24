package com.portal.service;

import com.portal.bean.Criteria;
import com.portal.bean.CultureInfo;
import java.util.List;

public interface CultureInfoService {
    int countByExample(Criteria example);

    CultureInfo selectByPrimaryKey(String id);

    List<CultureInfo> selectByExample(Criteria example);

    int deleteByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CultureInfo record);

    int updateByPrimaryKey(CultureInfo record);

    int deleteByExample(Criteria example);

    int updateByExampleSelective(CultureInfo record, Criteria example);

    int updateByExample(CultureInfo record, Criteria example);

    int insert(CultureInfo record);

    int insertSelective(CultureInfo record);
}