package com.portal.dao.extra;

import com.portal.bean.Criteria;
import com.portal.bean.result.OrderDetailInfoForm;
import com.portal.bean.result.OrderInfoForm;
import com.portal.bean.result.OrderInfoFormNew;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

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
}
