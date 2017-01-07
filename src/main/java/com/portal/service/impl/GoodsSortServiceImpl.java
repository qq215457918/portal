package com.portal.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portal.bean.Criteria;
import com.portal.bean.GoodsSort;
import com.portal.dao.GoodsSortDao;
import com.portal.dao.extra.GoodsSortExtraDao;
import com.portal.service.GoodsSortService;

@Service
public class GoodsSortServiceImpl implements GoodsSortService {
    
    @Autowired
    private GoodsSortDao goodsSortDao;

    @Autowired
    private GoodsSortExtraDao goodsSortExtraDao;
    
    private static final Logger logger = LoggerFactory.getLogger(GoodsSortServiceImpl.class);

    public int countByExample(Criteria example) {
        int count = this.goodsSortDao.countByExample(example);
        logger.debug("count: {}", count);
        return count;
    }

    public GoodsSort selectByPrimaryKey(String id) {
        return this.goodsSortDao.selectByPrimaryKey(id);
    }

    public List<GoodsSort> selectByExample(Criteria example) {
        return this.goodsSortDao.selectByExample(example);
    }

    public int deleteByPrimaryKey(String id) {
        return this.goodsSortDao.deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(GoodsSort record) {
        return this.goodsSortDao.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(GoodsSort record) {
        return this.goodsSortDao.updateByPrimaryKey(record);
    }

    public int deleteByExample(Criteria example) {
        return this.goodsSortDao.deleteByExample(example);
    }

    public int updateByExampleSelective(GoodsSort record, Criteria example) {
        return this.goodsSortDao.updateByExampleSelective(record, example);
    }

    public int updateByExample(GoodsSort record, Criteria example) {
        return this.goodsSortDao.updateByExample(record, example);
    }

    public int insert(GoodsSort record) {
        return this.goodsSortDao.insert(record);
    }

    public int insertSelective(GoodsSort record) {
        return this.goodsSortDao.insertSelective(record);
    }

    public List<GoodsSort> getGoodsBigSort() {
        return this.goodsSortExtraDao.getGoodsBigSort();
    }

}