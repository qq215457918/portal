package com.portal.dao.extra;

import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.portal.bean.Criteria;

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
    Map<String, Object> getWeekClinchPerfors(@Param(value="startDate") String startDate, @Param(value="area") String area);
}