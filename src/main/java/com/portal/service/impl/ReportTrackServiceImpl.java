package com.portal.service.impl;

import com.portal.bean.Criteria;
import com.portal.bean.ReportTrack;
import com.portal.dao.ReportTrackDao;
import com.portal.service.ReportTrackService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportTrackServiceImpl implements ReportTrackService {
    @Autowired
    private ReportTrackDao reportTrackDao;

    private static final Logger logger = LoggerFactory.getLogger(ReportTrackServiceImpl.class);

    public int countByExample(Criteria example) {
        int count = this.reportTrackDao.countByExample(example);
        logger.debug("count: {}", count);
        return count;
    }

    public ReportTrack selectByPrimaryKey(String id) {
        return this.reportTrackDao.selectByPrimaryKey(id);
    }

    public List<ReportTrack> selectByExample(Criteria example) {
        return this.reportTrackDao.selectByExample(example);
    }

    public int deleteByPrimaryKey(String id) {
        return this.reportTrackDao.deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(ReportTrack record) {
        return this.reportTrackDao.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(ReportTrack record) {
        return this.reportTrackDao.updateByPrimaryKey(record);
    }

    public int deleteByExample(Criteria example) {
        return this.reportTrackDao.deleteByExample(example);
    }

    public int updateByExampleSelective(ReportTrack record, Criteria example) {
        return this.reportTrackDao.updateByExampleSelective(record, example);
    }

    public int updateByExample(ReportTrack record, Criteria example) {
        return this.reportTrackDao.updateByExample(record, example);
    }

    public int insert(ReportTrack record) {
        return this.reportTrackDao.insert(record);
    }

    public int insertSelective(ReportTrack record) {
        return this.reportTrackDao.insertSelective(record);
    }
}