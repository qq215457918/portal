package com.portal.dao.extra;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.portal.bean.Criteria;
import com.portal.bean.result.OrderDetailInfoForm;
import com.portal.bean.result.OrderFundSettlementForm;
import com.portal.bean.result.OrderInfoForm;
import com.portal.bean.result.OrderInfoFormNew;

@Repository
public interface OrderInfoExtraDao {

    /**
     * @Title: getClinchPerfors 
     * @Description: 查询各地区一周的业绩
     * @param example
     * @return Map<String,Integer>
     * @throws
     */
    Map<String, Integer> getClinchPerfors(Criteria example);

    /**
     * @Title: getDayAndPerfors 
     * @Description: 获取每日业绩统计线形图数据
     * @param example
     * @return List<OrderInfoForm>
     * @author Xia ZhengWei
     * @date 2016年11月24日 下午11:42:24 
     * @version V1.0
     */
    List<OrderInfoForm> getDayAndPerfors(Criteria example);

    /**
     * @Title: getEmployeeInfos 
     * @Description: 根据条件查询员工名称
     * @param example
     * @return List<String>
     * @author Xia ZhengWei
     * @date 2016年11月1日 下午10:42:33 
     * @version V1.0
     */
    List<String> getEmployeeInfos(Criteria example);

    /**
     * @Title: getStaffPerfors 
     * @Description: 获取员工业绩数据
     * @param example
     * @return List<OrderInfoForm>
     * @author Xia ZhengWei
     * @date 2016年10月31日 下午11:30:20 
     * @version V1.0
     */
    List<OrderInfoForm> getStaffPerfors(Criteria example);

    /**
     * 订单信息查询
     * @param criteria
     * @return
     */
    List<OrderInfoForm> selectByExample4Page(Criteria criteria);

    /**
     * 订单信息查询以订单详情进行展示
     * @param criteria
     * @return
     */
    List<OrderInfoFormNew> selectByExampleNew4Page(Criteria criteria);

    /**
     * @Title: getSellGoods 
     * @Description: 销售日报表-获取销售商品信息
     * @param criteria
     * @return List<OrderDetailInfoForm>
     * @author Xia ZhengWei
     * @date 2016年11月26日 下午2:53:34 
     * @version V1.0
     */
    List<OrderDetailInfoForm> getSellGoods(Criteria criteria);

    /**
     * @Title: getSellclearingDetail 
     * @Description: 销售日报表-获取销售结算明细
     * @param criteria
     * @return List<OrderInfoForm>
     * @author Xia ZhengWei
     * @date 2016年11月26日 下午2:54:10 
     * @version V1.0
     */
    List<OrderInfoForm> getSellclearingDetail(Criteria criteria);
    
    /**
     * @Title: getPayOutAmounts 
     * @Description: 销售日报表-获取回购付出的金额
     * @param criteria
     * @return double
     * @author Xia ZhengWei
     * @date 2017年3月15日 下午6:59:42 
     * @version V1.0
     */
    double getPayOutAmounts(Criteria criteria);
    
    /**
     * @Title: getCountsCardDetail 
     * @Description: 获取当日刷卡定金明细数据总条数
     * @param criteria
     * @return int
     * @author Xia ZhengWei
     * @date 2016年12月11日 下午9:13:05 
     * @version V1.0
     */
    int getCountsCardDetail(Criteria criteria);
    
    /**
     * @Title: getCreditCardDepositDetail 
     * @Description: 获取当日刷卡定金明细数据
     * @param request
     * @return JSONObject
     * @author Xia ZhengWei
     * @date 2016年12月11日 下午8:41:31
     * @version V1.0
     */
    List<OrderFundSettlementForm> getCreditCardDepositDetail(Criteria criteria);
    
    /**
     * @Title: getOrderCounts 
     * @Description: 获取对应客户指定时间段的出单数（定时器使用, 其他地方也可以使用）
     * @param criteria
     * @return int
     * @author Xia ZhengWei
     * @date 2017年2月8日 下午10:47:46 
     * @version V1.0
     */
    int getOrderCounts(Criteria criteria);
    
    /**
     * @Title: getOrderAmounts 
     * @Description: 获取对应客户指定时间段的实际出单金额（定时器使用, 其他地方也可以使用）
     * @param criteria
     * @return int
     * @author Xia ZhengWei
     * @date 2017年2月8日 下午10:49:14 
     * @version V1.0
     */
    int getOrderAmounts(Criteria criteria);
    
    /**
     * @Title: getOrderGoodsCounts 
     * @Description: 获取对应人员在指定时间段的出售藏品件数（定时器使用）
     * @param criteria
     * @return int
     * @author Xia ZhengWei
     * @date 2016年12月12日 下午11:59:00 
     * @version V1.0
     */
    int getOrderGoodsCounts(Criteria criteria);
    
    /**
     * @Title: getDepositRefund 
     * @Description: 获取每天的定金退款数据（定时器使用）
     * @param criteria
     * @return List<Integer>
     * @author Xia ZhengWei
     * @date 2016年12月12日 下午10:46:18 
     * @version V1.0
     */
    List<Integer> getDepositRefund(Criteria criteria);
    
    /**
     * @Title: getDepositReturn 
     * @Description: 获取每天的定金回款数据（定时器使用）
     * @param criteria
     * @return List<Integer>
     * @author Xia ZhengWei
     * @date 2016年12月12日 下午10:47:21 
     * @version V1.0
     */
    List<Integer> getDepositReturn(Criteria criteria);
    
}
