package com.portal.service.impl;

import com.portal.bean.Criteria;
import com.portal.bean.ReceptionInfo;
import com.portal.bean.result.ReceptionInfoFrom;
import com.portal.dao.ReceptionInfoDao;
import com.portal.dao.extra.ReceptionInfoExtraDao;
import com.portal.service.ReceptionInfoService;

import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReceptionInfoServiceImpl implements ReceptionInfoService {
    @Autowired
    private ReceptionInfoDao receptionInfoDao;
    
    @Autowired
    private ReceptionInfoExtraDao receptionInfoExtraDao;

    private static final Logger logger = LoggerFactory.getLogger(ReceptionInfoServiceImpl.class);

    /**
     * 记录开始接待时间
     * by meng.yue
     * @return
     */
    public boolean recordReceptionTime(String customerId, String receiverStaffId){
    	ReceptionInfo receptionInfo = new ReceptionInfo();
    	receptionInfo.setCustomerId(customerId);
    	receptionInfo.setReceiverStaffId(receiverStaffId);
    	receptionInfo.setCreateDate(new Date());
    	receptionInfo.setStartTime(new Date());
    	int result = insert(receptionInfo);
    	return result>0 ? true:false;
    }
    
    /**
     * 查询客户的拜访记录byID
     * by meng.yue
     * @return
     */
    public List<ReceptionInfoFrom> queryRecordListbyId(String customerId){
    	List<ReceptionInfoFrom> receptionInfoFromList = receptionInfoExtraDao.queryRecordListbyId(customerId);
    	return receptionInfoFromList;
    }
    
    public int countByExample(Criteria example) {
        int count = this.receptionInfoDao.countByExample(example);
        logger.debug("count: {}", count);
        return count;
    }

    public ReceptionInfo selectByPrimaryKey(String id) {
        return this.receptionInfoDao.selectByPrimaryKey(id);
    }

    public List<ReceptionInfo> selectByExample(Criteria example) {
        return this.receptionInfoDao.selectByExample(example);
    }

    public int deleteByPrimaryKey(String id) {
        return this.receptionInfoDao.deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(ReceptionInfo record) {
        return this.receptionInfoDao.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(ReceptionInfo record) {
        return this.receptionInfoDao.updateByPrimaryKey(record);
    }

    public int deleteByExample(Criteria example) {
        return this.receptionInfoDao.deleteByExample(example);
    }

    public int updateByExampleSelective(ReceptionInfo record, Criteria example) {
        return this.receptionInfoDao.updateByExampleSelective(record, example);
    }

    public int updateByExample(ReceptionInfo record, Criteria example) {
        return this.receptionInfoDao.updateByExample(record, example);
    }

    public int insert(ReceptionInfo record) {
        return this.receptionInfoDao.insert(record);
    }

    public int insertSelective(ReceptionInfo record) {
        return this.receptionInfoDao.insertSelective(record);
    }
}