package com.portal.service;

import com.portal.bean.Criteria;
import com.portal.bean.ReportTrack;
import java.util.List;

public interface ReportTrackService {
    int countByExample(Criteria example);

    ReportTrack selectByPrimaryKey(String id);

    List<ReportTrack> selectByExample(Criteria example);

    int deleteByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ReportTrack record);

    int updateByPrimaryKey(ReportTrack record);

    int deleteByExample(Criteria example);

    int updateByExampleSelective(ReportTrack record, Criteria example);

    int updateByExample(ReportTrack record, Criteria example);

    int insert(ReportTrack record);

    int insertSelective(ReportTrack record);
}