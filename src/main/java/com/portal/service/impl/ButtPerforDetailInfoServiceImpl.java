package com.portal.service.impl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portal.bean.ButtPerforDetailInfo;
import com.portal.bean.Criteria;
import com.portal.common.util.DateUtil;
import com.portal.common.util.StringUtil;
import com.portal.dao.ButtPerforDetailInfoDao;
import com.portal.dao.extra.ButtPerforDetailInfoExtraDao;
import com.portal.service.ButtPerforDetailInfoService;

import net.sf.json.JSONObject;

@Service
public class ButtPerforDetailInfoServiceImpl implements ButtPerforDetailInfoService {
    
    @Autowired
    private ButtPerforDetailInfoDao buttPerforDetailInfoDao;
    
    @Autowired
    private ButtPerforDetailInfoExtraDao buttPerforExtraDao;

    private static final Logger logger = LoggerFactory.getLogger(ButtPerforDetailInfoServiceImpl.class);
    
    // 公共查询条件类
    Criteria criteria = new Criteria();

    public int countByExample(Criteria example) {
        int count = this.buttPerforDetailInfoDao.countByExample(example);
        logger.debug("count: {}", count);
        return count;
    }

    public ButtPerforDetailInfo selectByPrimaryKey(String id) {
        return this.buttPerforDetailInfoDao.selectByPrimaryKey(id);
    }

    public List<ButtPerforDetailInfo> selectByExample(Criteria example) {
        return this.buttPerforDetailInfoDao.selectByExample(example);
    }

    public int deleteByPrimaryKey(String id) {
        return this.buttPerforDetailInfoDao.deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(ButtPerforDetailInfo record) {
        return this.buttPerforDetailInfoDao.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(ButtPerforDetailInfo record) {
        return this.buttPerforDetailInfoDao.updateByPrimaryKey(record);
    }

    public int deleteByExample(Criteria example) {
        return this.buttPerforDetailInfoDao.deleteByExample(example);
    }

    public int updateByExampleSelective(ButtPerforDetailInfo record, Criteria example) {
        return this.buttPerforDetailInfoDao.updateByExampleSelective(record, example);
    }

    public int updateByExample(ButtPerforDetailInfo record, Criteria example) {
        return this.buttPerforDetailInfoDao.updateByExample(record, example);
    }

    public int insert(ButtPerforDetailInfo record) {
        return this.buttPerforDetailInfoDao.insert(record);
    }

    public int insertSelective(ButtPerforDetailInfo record) {
        return this.buttPerforDetailInfoDao.insertSelective(record);
    }
    
    public List<ButtPerforDetailInfo> seleteByCondition(Criteria criteria) {
        return buttPerforExtraDao.seleteByCondition(criteria);
    }

    public JSONObject ajaxButtPerforDetail(HttpServletRequest request) {
     // 查询部门业绩（默认查询上一周的数据）
        // 请求开始页
        int currentPage = StringUtil.getIntValue(request.getParameter("iDisplayStart"));
        // 每页显示几条
        int perpage = StringUtil.getIntValue(request.getParameter("iDisplayLength"));
        // 开始日期
        String startReportDate = request.getParameter("startReportDate");
        // 结束日期
        String endReportDate = request.getParameter("endReportDate");
        // 客服人员名称
        String viewPhoneStaffName = request.getParameter("viewPhoneStaffName");
        // 接待人员名称
        String viewReceiveStaffName = request.getParameter("viewReceiveStaffName");
        // 机构名称
        String viewPhoneStaffGroupName = request.getParameter("viewPhoneStaffGroupName");
        criteria.clear();
        criteria.setMysqlOffset(currentPage);
        criteria.setMysqlLength(perpage);
        
        if(StringUtil.isNotBlank(startReportDate)){
            criteria.put("startReportDate", startReportDate);
        }else {
            criteria.put("startReportDate", DateUtil.formatDate(DateUtil.getLastWeekMonday(new Date()), "yyyy-MM-dd"));
        }
        if(StringUtil.isNotBlank(endReportDate)){
            criteria.put("endReportDate",  DateUtil.formatDate(DateUtil.parseDate(endReportDate, "yyyy-MM-dd"), "yyyy-MM-dd 23:59:59"));
        }else {
            criteria.put("endReportDate", DateUtil.formatDate(DateUtil.getLastWeekSunday(new Date()), "yyyy-MM-dd 23:59:59"));
        }
        if(StringUtil.isNotBlank(viewPhoneStaffName)){
            criteria.put("viewPhoneStaffName", viewPhoneStaffName.trim());
        }
        if(StringUtil.isNotBlank(viewReceiveStaffName)){
            criteria.put("viewReceiveStaffName", viewReceiveStaffName.trim());
        }
        if(StringUtil.isNotBlank(viewPhoneStaffGroupName)){
            criteria.put("viewPhoneStaffGroupName", viewPhoneStaffGroupName);
        }
        
        // 获取总记录数
        int totalRecord = buttPerforExtraDao.countByCondition(criteria);
        // 获取数据集
        List<ButtPerforDetailInfo> list = buttPerforExtraDao.seleteByCondition(criteria);
        
        JSONObject resultJson =  new JSONObject();
        resultJson.put("sEcho", request.getParameter("sEcho"));
        resultJson.put("iTotalRecords", totalRecord);
        resultJson.put("iTotalDisplayRecords", totalRecord);
        resultJson.put("aaData", list);
        return resultJson;
    }

}