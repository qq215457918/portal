package com.portal.dao.extra;

import com.portal.bean.Criteria;
import com.portal.bean.result.OrderInfoForm;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
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
     * @Title: getWeekClinchPerfors 
     * @Description: 查询各地区一周内每天的业绩
     * @param startDate  开始日期
     * @param area  所属区域
     * @return Map<String,Object>
     * @throws
     */
    Map<String, Object> getWeekClinchPerfors(@Param(value = "startDate") String startDate,
            @Param(value = "area") String area);

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
}
