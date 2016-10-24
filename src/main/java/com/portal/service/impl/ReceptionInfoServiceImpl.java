package com.portal.service.impl;

import com.portal.bean.Criteria;
import com.portal.bean.ReceptionInfo;
import com.portal.bean.result.ReceptionInfoForm;
import com.portal.common.util.DateUtil;
import com.portal.common.util.StringUtil;
import com.portal.dao.ReceptionInfoDao;
import com.portal.dao.extra.ReceptionInfoExtraDao;
import com.portal.service.ReceptionInfoService;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;
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

    // 公共查询条件类
    Criteria criteria = new Criteria();

    /**
     * 记录开始接待时间
     * by meng.yue
     * @return
     */
    public boolean recordReceptionTime(String customerId, String receiverStaffId) {
        ReceptionInfo receptionInfo = new ReceptionInfo();
        receptionInfo.setCustomerId(customerId);
        receptionInfo.setReceiverStaffId(receiverStaffId);
        receptionInfo.setCreateDate(new Date());
        receptionInfo.setStartTime(new Date());
        int result = insert(receptionInfo);
        return result > 0 ? true : false;
    }

    /**
     * 记录结束接待时间
     * by meng.yue
     * @return
     */
    public boolean endReceptionTime(String customerId, String receiverStaffId) {

        return false;
    }

    public List<ReceptionInfoForm> receptionING(HttpServletRequest request, HttpServletResponse response,
            String date) {
        criteria = getPageCriteria(request);
        criteria.put("create_date", date);
        criteria.put("endtimeflag", "1");
        return receptionInfoExtraDao.selectByExample(criteria);
    }

    public Criteria getPageCriteria(HttpServletRequest request) {
        criteria.clear();
        int currentPage = StringUtil.getIntValue(request.getParameter("iDisplayStart"));
        int perpage = StringUtil.getIntValue(request.getParameter("iDisplayLength"));
        criteria.setMysqlOffset(currentPage);
        criteria.setMysqlLength(perpage);
        return criteria;
    }

    /**
     * 查询客户的拜访记录byID
     * by meng.yue
     * @return
     */
    public List<ReceptionInfoForm> queryRecordListbyId(String customerId) {
        List<ReceptionInfoForm> receptionInfoFromList = receptionInfoExtraDao.queryRecordListbyId(customerId);
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

    public JSONObject ajaxVisitAndOutOrder(HttpServletRequest request) {
        // 查询登门出单统计数据（默认查询上一周的数据）
        // 请求开始页
        int currentPage = StringUtil.getIntValue(request.getParameter("iDisplayStart"));
        // 每页显示几条
        int perpage = StringUtil.getIntValue(request.getParameter("iDisplayLength"));
        // 开始日期
        String startDate = request.getParameter("startDate");
        // 结束日期
        String endDate = request.getParameter("endDate");
        // 地区
        String area = request.getParameter("area");
        // 客户姓名
        String customerName = request.getParameter("customerName");
        // 客户类型
        String type = request.getParameter("type");

        criteria.clear();
        criteria.setMysqlOffset(currentPage);
        criteria.setMysqlLength(perpage);
        criteria.setOrderByClause("r.create_date asc");

        if (StringUtil.isNotBlank(startDate)) {
            criteria.put("startDate", startDate);
        } else {
            criteria.put("startDate",
                    DateUtil.formatDate(DateUtil.getLastWeekMonday(new Date()), "yyyy-MM-dd"));
        }
        if (StringUtil.isNotBlank(endDate)) {
            criteria.put("endDate",
                    DateUtil.formatDate(DateUtil.parseDate(endDate, "yyyy-MM-dd"), "yyyy-MM-dd 23:59:59"));
        } else {
            criteria.put("endDate",
                    DateUtil.formatDate(DateUtil.getLastWeekSunday(new Date()), "yyyy-MM-dd 23:59:59"));
        }
        if (StringUtil.isNotBlank(area)) {
            criteria.put("area", area);
        }
        if (StringUtil.isNotBlank(customerName)) {
            criteria.put("name", customerName);
        }
        if (StringUtil.isNotBlank(type)) {
            criteria.put("type", type);
        }

        // 获取总记录数
        int totalRecord = receptionInfoExtraDao.countByCondition(criteria);
        // 获取数据集
        List<ReceptionInfoForm> list = receptionInfoExtraDao.selectByCondition(criteria);

        JSONObject resultJson = new JSONObject();
        resultJson.put("sEcho", request.getParameter("sEcho"));
        resultJson.put("iTotalRecords", totalRecord);
        resultJson.put("iTotalDisplayRecords", totalRecord);
        resultJson.put("aaData", list);
        return resultJson;
    }

    public JSONObject ajaxOutOrderDetail(HttpServletRequest request) {
        // 查询登门出单详细数据
        // 请求开始页
        int currentPage = StringUtil.getIntValue(request.getParameter("iDisplayStart"));
        // 每页显示几条
        int perpage = StringUtil.getIntValue(request.getParameter("iDisplayLength"));
        // 开始日期
        String startDate = request.getParameter("startDate");
        // 结束日期
        String endDate = request.getParameter("endDate");
        // 客户ID
        String customerId = request.getParameter("customerId");
        // 产品名称
        String goodsName = request.getParameter("goodsName");
        // 产品种类
        // String goodsSortName = request.getParameter("goodsSortName");

        criteria.clear();
        criteria.setMysqlOffset(currentPage);
        criteria.setMysqlLength(perpage);
        criteria.setOrderByClause("o.create_date asc");

        // 订单支付状态为已支付
        criteria.put("financeFlag", "1");
        if (StringUtil.isNotBlank(startDate)) {
            criteria.put("startDate", startDate);
        }
        if (StringUtil.isNotBlank(endDate)) {
            criteria.put("endDate",
                    DateUtil.formatDate(DateUtil.parseDate(endDate, "yyyy-MM-dd"), "yyyy-MM-dd 23:59:59"));
        }
        if (StringUtil.isNotBlank(customerId)) {
            criteria.put("customerId", customerId);
        }
        if (StringUtil.isNotBlank(goodsName)) {
            criteria.put("goodsName", goodsName);
        }
        /*if(StringUtil.isNotBlank(goodsSortName)){
            criteria.put("goodsSortName", goodsSortName);
        }*/

        // 获取总记录数
        int totalRecord = receptionInfoExtraDao.countByCondition(criteria);
        // 获取数据集
        List<ReceptionInfoForm> list = receptionInfoExtraDao.selectByCondition(criteria);

        JSONObject resultJson = new JSONObject();
        resultJson.put("sEcho", request.getParameter("sEcho"));
        resultJson.put("iTotalRecords", totalRecord);
        resultJson.put("iTotalDisplayRecords", totalRecord);
        resultJson.put("aaData", list);
        return resultJson;
    }
}
