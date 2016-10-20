package com.portal.service.impl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portal.bean.Criteria;
import com.portal.bean.StorehouseOperateInfo;
import com.portal.bean.result.StorehouseOperateInfoForm;
import com.portal.common.util.DateUtil;
import com.portal.common.util.StringUtil;
import com.portal.dao.StorehouseOperateInfoDao;
import com.portal.dao.extra.StorehouseOperateInfoExtraDao;
import com.portal.service.StorehouseOperateInfoService;

import net.sf.json.JSONObject;

@Service
public class StorehouseOperateInfoServiceImpl implements StorehouseOperateInfoService {
    @Autowired
    private StorehouseOperateInfoDao storehouseOperateInfoDao;
    
    @Autowired
    private StorehouseOperateInfoExtraDao storehouseOperateInfoExtraDao;

    private static final Logger logger = LoggerFactory.getLogger(StorehouseOperateInfoServiceImpl.class);
    
    // 公共查询条件类
    Criteria criteria = new Criteria();

    public int countByExample(Criteria example) {
        int count = this.storehouseOperateInfoDao.countByExample(example);
        logger.debug("count: {}", count);
        return count;
    }

    public StorehouseOperateInfo selectByPrimaryKey(String id) {
        return this.storehouseOperateInfoDao.selectByPrimaryKey(id);
    }

    public List<StorehouseOperateInfo> selectByExample(Criteria example) {
        return this.storehouseOperateInfoDao.selectByExample(example);
    }

    public int deleteByPrimaryKey(String id) {
        return this.storehouseOperateInfoDao.deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(StorehouseOperateInfo record) {
        return this.storehouseOperateInfoDao.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(StorehouseOperateInfo record) {
        return this.storehouseOperateInfoDao.updateByPrimaryKey(record);
    }

    public int deleteByExample(Criteria example) {
        return this.storehouseOperateInfoDao.deleteByExample(example);
    }

    public int updateByExampleSelective(StorehouseOperateInfo record, Criteria example) {
        return this.storehouseOperateInfoDao.updateByExampleSelective(record, example);
    }

    public int updateByExample(StorehouseOperateInfo record, Criteria example) {
        return this.storehouseOperateInfoDao.updateByExample(record, example);
    }

    public int insert(StorehouseOperateInfo record) {
        return this.storehouseOperateInfoDao.insert(record);
    }

    public int insertSelective(StorehouseOperateInfo record) {
        return this.storehouseOperateInfoDao.insertSelective(record);
    }
    
    public JSONObject ajaxOutwarehouseDetail(HttpServletRequest request) {
        // 查询出库明细（默认查询上一周的数据）
        // 请求开始页
        int currentPage = StringUtil.getIntValue(request.getParameter("iDisplayStart"));
        // 每页显示几条
        int perpage = StringUtil.getIntValue(request.getParameter("iDisplayLength"));
        // 开始日期
        String startCreateDate = request.getParameter("startCreateDate");
        // 结束日期
        String endCreateDate = request.getParameter("endCreateDate");
        
        criteria.clear();
        // 分页参数
        criteria.setMysqlOffset(currentPage);
        criteria.setMysqlLength(perpage);
        // 已支付
        criteria.put("financeFlag", "1");
        if(StringUtil.isNotBlank(startCreateDate)){
            criteria.put("startCreateDate", startCreateDate);
        }else {
            criteria.put("startCreateDate", DateUtil.formatDate(DateUtil.getLastWeekMonday(new Date()), "yyyy-MM-dd"));
        }
        if(StringUtil.isNotBlank(endCreateDate)){
            criteria.put("endCreateDate", DateUtil.formatDate(DateUtil.parseDate(endCreateDate, "yyyy-MM-dd"), "yyyy-MM-dd 23:59:59"));
        }else {
            criteria.put("endCreateDate", DateUtil.formatDate(DateUtil.getLastWeekSunday(new Date()), "yyyy-MM-dd 23:59:59"));
        }
        // 获取总记录数
        int totalRecord = storehouseOperateInfoExtraDao.getCountsByCondition(criteria);
        // 获取数据集
        List<StorehouseOperateInfoForm> performanceList = storehouseOperateInfoExtraDao.getOrganiPerformance(criteria);
        
        JSONObject resultJson =  new JSONObject();
        resultJson.put("sEcho", request.getParameter("sEcho"));
        resultJson.put("iTotalRecords", totalRecord);
        resultJson.put("iTotalDisplayRecords", totalRecord);
        resultJson.put("aaData", performanceList);
        return resultJson;
    }
}