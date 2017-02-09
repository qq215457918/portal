package com.portal.service;

import com.portal.bean.Criteria;
import com.portal.bean.ReceptionInfo;
import com.portal.bean.result.ReceptionInfoForm;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;

public interface ReceptionInfoService {

    /**
     * 更新订单信息orderID
     * @param orderID
     * @param customerId
     * @return
     */
    public boolean updatePresentOrderID(String presentID, String presentName, String customerId);

    /**
     * 更新订单信息orderID
     * @param orderID
     * @param customerId
     * @return
     */
    public boolean updateOrderID(String orderID, String customerId);

    /**
     * 查询正在接待的业务员信息
     * @param date
     * @return
     */
    public JSONObject receptionING(HttpServletRequest request, HttpServletResponse response);

    /**
     * 查询客户接待记录的前5条
     * @param customerId
     * @return
     */
    public List<ReceptionInfoForm> queryRecordListbyId(String customerId);

    /**
     * 结束接待时间
     * @param customerId
     * @param receiverStaffId
     * @return
     */
    public boolean updateEndReceptionTime(String customerId);

    public boolean insertReceptionTime(String customerId, String receiverStaffId, String receiverStaffName);

    int countByExample(Criteria example);

    ReceptionInfo selectByPrimaryKey(String id);

    List<ReceptionInfo> selectByExample(Criteria example);

    int deleteByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ReceptionInfo record);

    int updateByPrimaryKey(ReceptionInfo record);

    int deleteByExample(Criteria example);

    int updateByExampleSelective(ReceptionInfo record, Criteria example);

    int updateByExample(ReceptionInfo record, Criteria example);

    int insert(ReceptionInfo record);

    int insertSelective(ReceptionInfo record);

    /**
     * @Title: ajaxFiltrateCustomers 
     * @Description: 报表统计数据--异步获取登门出单统计数据
     * @param request
     * @return JSONObject
     * @author Xia ZhengWei
     * @date 2016年10月17日 下午9:03:26 
     * @version V1.0
     */
    JSONObject ajaxVisitAndOutOrder(HttpServletRequest request);

    /**
     * @Title: ajaxOutOrderDetail 
     * @Description: 报表统计数据--异步获取登门出单详细数据
     * @param request
     * @return 
     * @return JSONObject
     * @throws 
     * @author Xia ZhengWei
     * @date 2016年10月17日 下午11:40:32 
     * @version V1.0
     */
    JSONObject ajaxOutOrderDetail(HttpServletRequest request);
    
    /**
     * @Title: getByConditions 
     * @Description: 定时器获取每日接待信息
     * @param example
     * @return List<String>
     * @author Xia ZhengWei
     * @date 2017年1月19日 下午10:41:49 
     * @version V1.0
     */
    List<String> getByConditions(Criteria example);
}
