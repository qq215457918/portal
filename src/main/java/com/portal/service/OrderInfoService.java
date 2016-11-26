package com.portal.service;

import com.portal.bean.Criteria;
import com.portal.bean.OrderInfo;
import com.portal.bean.result.OrderInfoForm;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import net.sf.json.JSONObject;

public interface OrderInfoService {

    public int updateRepurchaseOrder(HttpServletRequest request);

    public boolean updatePayDeposit(String orderId);

    public boolean updateCancelDeposit(String orderId);

    /**
     * 退货订单
     * @param orderId
     * @return
     */
    public boolean updateOrderReturn(String orderId);

    public boolean updateOrderReplace(String orderId);

    /**
     * 订单信息查询
     * @param example
     * @return
     */
    public List<OrderInfoForm> getDepositInfo(Criteria example);

    public List<OrderInfoForm> getOrderInfo(Criteria example);

    /**
     * 新增订单信息
     * @param request
     * @return
     */
    public boolean insertOrder(HttpServletRequest request);

    /**
     * 获取正常的订单类型
     * @param customerId
     * @return
     */
    List<OrderInfoForm> queryGoodsInfo(String customerId);

    /**
     * 查看已经支付的定金订单
     * @param customerId
     * @return
     * @throws InvocationTargetException 
     * @throws IllegalAccessException 
     */
    public List<OrderInfoForm> queryRevokeDepositInfo(String customerId);

    /**
     * 退货订单
     * @param customerId
     * @return
     * @throws InvocationTargetException 
     * @throws IllegalAccessException 
     */
    public List<OrderInfoForm> queryReturnGoodsInfo(String customerId);

    /**
     * 换货订单
     * @param customerId
     * @return
     * @throws InvocationTargetException 
     * @throws IllegalAccessException 
     */
    public List<OrderInfoForm> xchangeReturnGoodsInfo(String customerId);

    /**
     * 新增赠品订单，如果需要经历审批，则修改order_info 的财务审批字段
     * @param request
     * @return
     */
    boolean insertPresentOrder(HttpServletRequest request, int normalFlag);

    /**
     * 当天赠品记录查询
     * order_type='4'and create_date=now()
     */
    List<OrderInfoForm> selectTodayPresentList(String customerId);

    int countByExample(Criteria example);

    OrderInfo selectByPrimaryKey(String id);

    List<OrderInfo> selectByExample(Criteria example);

    int deleteByPrimaryKey(String id);

    int updateByPrimaryKeySelective(OrderInfo record);

    int updateByPrimaryKey(OrderInfo record);

    int deleteByExample(Criteria example);

    int updateByExampleSelective(OrderInfo record, Criteria example);

    int updateByExample(OrderInfo record, Criteria example);

    int insert(OrderInfo record);

    int insertSelective(OrderInfo record);

    /**
     * @Title: ajaxClinchPerforEveryDay 
     * @Description: 异步获取每日成交业绩数据
     * @param request
     * @return JSONObject
     * @author Xia ZhengWei
     * @date 2016年10月30日 下午7:02:14 
     * @version V1.0
     */
    JSONObject ajaxClinchPerforEveryDay(HttpServletRequest request);

    /**
     * @Title: ajaxStaffPerfors 
     * @Description: 异步获取员工业绩统计数据
     * @param request
     * @return JSONObject
     * @author Xia ZhengWei
     * @date 2016年10月31日 下午11:01:47 
     * @version V1.0
     */
    JSONObject ajaxStaffPerfors(HttpServletRequest request);

    /**
     * @Title: selectOrderInfoList 
     * @Description: 查询修改订单列表
     * @param criteria
     * @return 
     * @return List<CustomerInfo>
     * @throws
     */
    List<OrderInfo> selectOrderModifyList(Criteria criteria);

    /**
     * @Title: selectOrderInfoList 
     * @Description: 查询修改订单列表数量
     * @param criteria
     * @return 
     * @return List<CustomerInfo>
     * @throws
     */
    int countOrderModifyList(Criteria criteria);

    /**
     * @Title: selectOrderInfoList 
     * @Description: 查询修改订单列表数量
     * @param criteria
     * @return 
     * @return List<OrderInfo>
     * @throws
     */
    List<OrderInfo> selectFinanceEveryDay(Criteria criteria);

    /**
     * @Title: selectOrderInfoList 
     * @Description: 查询修改订单列表数量
     * @param criteria
     * @return 
     * @return int
     * @throws
     */
    int countFinanceEveryDay(Criteria criteria);
    
    /**
     * @Title: getSellDaily 
     * @Description: 获取销售日报表数据
     * @param request 
     * @return JSONObject
     * @author Xia ZhengWei
     * @date 2016年11月26日 下午2:34:13 
     * @version V1.0
     */
    JSONObject getSellDaily(HttpServletRequest request);

}
