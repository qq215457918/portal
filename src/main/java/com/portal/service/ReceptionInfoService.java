package com.portal.service;

import com.portal.bean.Criteria;
import com.portal.bean.ReceptionInfo;
import java.util.List;

public interface ReceptionInfoService {
    int countByExample(Criteria example);

    ReceptionInfo selectByPrimaryKey(String id);

    List<ReceptionInfo> selectByExample(Criteria example);

    int deleteByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ReceptionInfo record);

    int updateByPrimaryKey(ReceptionInfo record);

    int deleteByExample(Criteria example);

    int updateByExampleSelective(ReceptionInfo record, Criteria example);

    int updateByExample(ReceptionInfo record, Criteria example);

    int insert(ReceptionInfo record);

    int insertSelective(ReceptionInfo record);
}