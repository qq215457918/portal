package com.portal.service.impl;

import com.portal.bean.Criteria;
import com.portal.bean.GoodsInfo;
import com.portal.bean.result.GoodsInfoForm;
import com.portal.dao.GoodsInfoDao;
import com.portal.dao.extra.GoodsDao;
import com.portal.service.GoodsInfoService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoodsInfoServiceImpl implements GoodsInfoService {
    @Autowired
    private GoodsInfoDao goodsInfoDao;

    @Autowired
    private GoodsDao goodsDao;

    // 公共查询条件类
    Criteria criteria = new Criteria();

    private static final Logger logger = LoggerFactory.getLogger(GoodsInfoServiceImpl.class);

    /**
     * 获取商品列表信息
     */
    public List<GoodsInfoForm> getGoodInfoList(HttpServletRequest request) {
        return goodsDao.selectByExample(getGoodListCriteria(request));
    }

    /**
     * 获取商品列表数量
     * @param request
     * @return
     */
    public int getGoodInfoCount(HttpServletRequest request) {
        return countByExample(getGoodListCriteria(request));
    }

    Criteria getGoodListCriteria(HttpServletRequest request) {
        criteria.clear();
        criteria.setMysqlLength(Integer.valueOf(request.getParameter("iDisplayLength")));
        criteria.setMysqlOffset(Integer.valueOf(request.getParameter("iDisplayStart")));

        criteria.put("type", request.getParameter("type"));
        criteria.put("phoneStage", request.getParameter("phoneStage"));
        criteria.put("phone", request.getParameter("phone"));
        criteria.put("type", request.getParameter("type"));
        criteria.put("updateDate", request.getParameter("updateDate"));
        criteria.put("startTime", request.getParameter("startTime"));
        criteria.put("endTime", request.getParameter("endTime"));
        criteria.put("backCountS", request.getParameter("backCountS"));
        criteria.put("backCountE", request.getParameter("backCountE"));
        return criteria;
    }

    /**
     * 获取赠品信息
     * 修改mapper 里面的 amount>0
     */
    public List<GoodsInfo> selectPresentInfo(HttpServletRequest request) {
        criteria.clear();
        criteria.put("type", 1);
        criteria.put("moreAmount", 0);
        return selectByExample(criteria);
    }

    public int countByExample(Criteria example) {
        int count = this.goodsInfoDao.countByExample(example);
        logger.debug("count: {}", count);
        return count;
    }

    public GoodsInfo selectByPrimaryKey(String id) {
        return this.goodsInfoDao.selectByPrimaryKey(id);
    }

    public List<GoodsInfo> selectByExample(Criteria example) {
        return this.goodsInfoDao.selectByExample(example);
    }

    public int deleteByPrimaryKey(String id) {
        return this.goodsInfoDao.deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(GoodsInfo record) {
        return this.goodsInfoDao.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(GoodsInfo record) {
        return this.goodsInfoDao.updateByPrimaryKey(record);
    }

    public int deleteByExample(Criteria example) {
        return this.goodsInfoDao.deleteByExample(example);
    }

    public int updateByExampleSelective(GoodsInfo record, Criteria example) {
        return this.goodsInfoDao.updateByExampleSelective(record, example);
    }

    public int updateByExample(GoodsInfo record, Criteria example) {
        return this.goodsInfoDao.updateByExample(record, example);
    }

    public int insert(GoodsInfo record) {
        return this.goodsInfoDao.insert(record);
    }

    public int insertSelective(GoodsInfo record) {
        return this.goodsInfoDao.insertSelective(record);
    }
}
