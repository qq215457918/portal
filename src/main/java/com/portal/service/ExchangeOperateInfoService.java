package com.portal.service;

import com.portal.bean.Criteria;
import com.portal.bean.ExchangeOperateInfo;
import java.util.List;

public interface ExchangeOperateInfoService {
    int countByExample(Criteria example);

    ExchangeOperateInfo selectByPrimaryKey(String id);

    List<ExchangeOperateInfo> selectByExample(Criteria example);

    int deleteByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ExchangeOperateInfo record);

    int updateByPrimaryKey(ExchangeOperateInfo record);

    int deleteByExample(Criteria example);

    int updateByExampleSelective(ExchangeOperateInfo record, Criteria example);

    int updateByExample(ExchangeOperateInfo record, Criteria example);

    int insert(ExchangeOperateInfo record);

    int insertSelective(ExchangeOperateInfo record);
}