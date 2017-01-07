package com.portal.service.impl;

import com.portal.bean.Criteria;
import com.portal.bean.SellGoodsDetail;
import com.portal.dao.SellGoodsDetailDao;
import com.portal.service.SellGoodsDetailService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellGoodsDetailServiceImpl implements SellGoodsDetailService {
    @Autowired
    private SellGoodsDetailDao sellGoodsDetailDao;

    private static final Logger logger = LoggerFactory.getLogger(SellGoodsDetailServiceImpl.class);

    public int countByExample(Criteria example) {
        int count = this.sellGoodsDetailDao.countByExample(example);
        logger.debug("count: {}", count);
        return count;
    }

    public SellGoodsDetail selectByPrimaryKey(String id) {
        return this.sellGoodsDetailDao.selectByPrimaryKey(id);
    }

    public List<SellGoodsDetail> selectByExample(Criteria example) {
        return this.sellGoodsDetailDao.selectByExample(example);
    }

    public int deleteByPrimaryKey(String id) {
        return this.sellGoodsDetailDao.deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(SellGoodsDetail record) {
        return this.sellGoodsDetailDao.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(SellGoodsDetail record) {
        return this.sellGoodsDetailDao.updateByPrimaryKey(record);
    }

    public int deleteByExample(Criteria example) {
        return this.sellGoodsDetailDao.deleteByExample(example);
    }

    public int updateByExampleSelective(SellGoodsDetail record, Criteria example) {
        return this.sellGoodsDetailDao.updateByExampleSelective(record, example);
    }

    public int updateByExample(SellGoodsDetail record, Criteria example) {
        return this.sellGoodsDetailDao.updateByExample(record, example);
    }

    public int insert(SellGoodsDetail record) {
        return this.sellGoodsDetailDao.insert(record);
    }

    public int insertSelective(SellGoodsDetail record) {
        return this.sellGoodsDetailDao.insertSelective(record);
    }
}