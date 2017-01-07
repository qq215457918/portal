package com.portal.service.impl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portal.bean.Criteria;
import com.portal.bean.EmployeeInfo;
import com.portal.bean.SellDailyDetail;
import com.portal.bean.SellDailyInfo;
import com.portal.bean.SellGoodsDetail;
import com.portal.bean.result.SellDailyInfoForm;
import com.portal.common.exception.DBException;
import com.portal.common.util.JsonUtils;
import com.portal.common.util.StringUtil;
import com.portal.common.util.UUidUtil;
import com.portal.dao.SellDailyDetailDao;
import com.portal.dao.SellDailyInfoDao;
import com.portal.dao.SellGoodsDetailDao;
import com.portal.service.SellDailyInfoService;

import net.sf.json.JSONObject;

@Service
public class SellDailyInfoServiceImpl implements SellDailyInfoService {
    @Autowired
    private SellDailyInfoDao sellDailyInfoDao;
    
    @Autowired
    private SellDailyDetailDao sellDailyDatailDao;
    
    @Autowired
    private SellGoodsDetailDao sellGoodsDetailDao;

    private static final Logger logger = LoggerFactory.getLogger(SellDailyInfoServiceImpl.class);

    public int countByExample(Criteria example) {
        int count = this.sellDailyInfoDao.countByExample(example);
        logger.debug("count: {}", count);
        return count;
    }

    public SellDailyInfo selectByPrimaryKey(String id) {
        return this.sellDailyInfoDao.selectByPrimaryKey(id);
    }

    public List<SellDailyInfo> selectByExample(Criteria example) {
        return this.sellDailyInfoDao.selectByExample(example);
    }

    public int deleteByPrimaryKey(String id) {
        return this.sellDailyInfoDao.deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(SellDailyInfo record) {
        return this.sellDailyInfoDao.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(SellDailyInfo record) {
        return this.sellDailyInfoDao.updateByPrimaryKey(record);
    }

    public int deleteByExample(Criteria example) {
        return this.sellDailyInfoDao.deleteByExample(example);
    }

    public int updateByExampleSelective(SellDailyInfo record, Criteria example) {
        return this.sellDailyInfoDao.updateByExampleSelective(record, example);
    }

    public int updateByExample(SellDailyInfo record, Criteria example) {
        return this.sellDailyInfoDao.updateByExample(record, example);
    }

    public int insert(SellDailyInfo record) {
        return this.sellDailyInfoDao.insert(record);
    }

    public int insertSelective(SellDailyInfo record) {
        return this.sellDailyInfoDao.insertSelective(record);
    }

    public JSONObject saveSellDaily(SellDailyInfoForm sellDaily, JSONObject results, HttpServletRequest request) throws DBException {
        if(sellDaily != null) {
            logger.info("报表模块-保存销售日报表获取-登录员工信息");
            EmployeeInfo employeeInfo = (EmployeeInfo) request.getSession().getAttribute("userInfo");
            if(employeeInfo != null) {
                SellDailyInfo info = new SellDailyInfo();
                BeanUtils.copyProperties(sellDaily, info);
                String sellDailyId = UUidUtil.getUUId();
                info.setId(sellDailyId);
                info.setReportDate(new Date());
                info.setCreateUserId(employeeInfo.getId());
                info.setCreateDate(new Date());
                info.setUpdateUserId(employeeInfo.getId());
                info.setUpdateDate(new Date());
                logger.info("报表模块-保存销售日报表对象信息");
                int count = sellDailyInfoDao.insert(info);
                if(count > 0) {
                    try {
                        List<SellDailyDetail> dailyDetail = sellDaily.getSellDailyDetails();
                        if(dailyDetail.size() > 0) {
                            logger.info("报表模块-开始循环保存销售日报表对象信息中的资金结算明细");
                            for (SellDailyDetail sellDailyDetail : dailyDetail) {
                                sellDailyDetail.setId(UUidUtil.getUUId());
                                sellDailyDetail.setSellDailyId(sellDailyId);
                                sellDailyDetail.setCreateUserId(employeeInfo.getId());
                                sellDailyDetail.setCreateDate(new Date());
                                // 过滤特殊字符
                                sellDailyDetail.setRemarks(StringUtil.tstr(sellDailyDetail.getRemarks().trim()));
                                sellDailyDatailDao.insert(sellDailyDetail);
                            }
                        }
                        List<SellGoodsDetail> goodsDetail = sellDaily.getSellGoodsDetails();
                        if(goodsDetail.size() > 0) {
                            logger.info("报表模块-开始循环保存销售日报表对象信息中的销售藏品明细");
                            for (SellGoodsDetail sellGoodsDetail : goodsDetail) {
                                sellGoodsDetail.setId(UUidUtil.getUUId());
                                sellGoodsDetail.setSellDailyId(sellDailyId);
                                sellGoodsDetail.setCreateUserId(employeeInfo.getId());
                                sellGoodsDetail.setCreateDate(new Date());
                                sellGoodsDetailDao.insert(sellGoodsDetail);
                            }
                        }
                        logger.info("报表模块-保存销售日报表对象信息成功");
                        results = JsonUtils.setSuccess();
                    } catch(Exception e) {
                        logger.info("报表模块-保存销售日报表对象信息中明细时报错");
                        e.printStackTrace();
                        throw new DBException("操作数据库失败");
                    }
                }else {
                    logger.info("报表模块-保存销售日报表对象信息失败");
                    results = JsonUtils.setError();
                    results.put("text", "操作失败，请刷新后重试");
                }
            }else {
                logger.info("报表模块-用户Session过期或者未登录");
                results = JsonUtils.setError();
                results.put("text", "操作失败，用户未登录请登录后重试");
            }
        }else {
            logger.info("报表模块-保存销售日报表-获取登录员工信息失败，失败原因：对象为空");
            results = JsonUtils.setError();
            results.put("text", "操作失败，数据异常请刷新后重试");
        }
        return results;
    }
}