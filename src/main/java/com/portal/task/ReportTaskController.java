package com.portal.task;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.portal.bean.Criteria;
import com.portal.bean.OrderInfo;
import com.portal.service.OrderInfoService;
import com.portal.service.ReportTrackService;

/**
 * @ClassName: ReportTaskController 
 * @Description: 报表统计定时任务类
 * @author Xia ZhengWei
 * @date 2016年11月7日 下午9:59:24
 */

@Component
public class ReportTaskController {
    
    // 输出log对象
    Logger logger = LoggerFactory.getLogger(this.getClass());
    
    // 订单Service
    @Autowired
    private OrderInfoService orderService;
    
    // 每日业绩Service
    @Autowired
    private ReportTrackService reportTrackService;
    
    // 公共查询条件类
    Criteria criteria = new Criteria();
    
    /**
     * @Title: everyPerformances 
     * @Description: 每日业绩统计任务(每日各个客户类型对应的登门数、出单数(订单状态为已完成/支付类型为支付定金)、金额及新客户数量)
     * @return void
     * @author Xia ZhengWei
     * @date 2016年11月7日 下午10:03:26 
     * @version V1.0
     */
    @Scheduled(cron = "0 0 19 * * ?")  
    public void everyPerformances(){  
        logger.info("启动每日19点的定时任务, 操作内容：统计每日业绩存储到每日业绩表-------------------");
        
        
        List<OrderInfo> orderLists = orderService.selectByExample(criteria);
        
        
        
        
        
        logger.info("每日19点的定时任务结束, 共存储" + 10 + "条记录-------------------");
    } 
    
    
    // 第二个定时任务：每日晚19点05分统计向每日登门表中插入数据（根据日常接待表）
    
    /**
     * @Title: visitEveryDay 
     * @Description: 统计每日登门任务(从每日接待和订单表中获取数据)
     * @return void
     * @author Xia ZhengWei
     * @date 2016年11月7日 下午10:14:44 
     * @version V1.0
     */
    @Scheduled(cron = "0 5 19 * * ?")  
    public void visitEveryDay(){  
         
        
        
        
    }
    
    
    // 第三个定时任务：每日晚19点10分统计向接待统计表中插入数据（日常接待、订单）
    
    /**
     * @Title: receiveReport 
     * @Description: 接待统计任务
     * @return void
     * @author Xia ZhengWei
     * @date 2016年11月7日 下午10:15:03 
     * @version V1.0
     */
    @Scheduled(cron = "0 10 19 * * ?")  
    public void receiveReport(){  
         
        
        
        
    }
    
    
    // 第四个定时任务：每日晚19点15分统计向部门业绩统计表插入数据（查询订单、员工、客户表）
    // 表数据：机构ID、机构名称、部门ID、部门名称、小组ID、小组名称、人员ID、人员名称、业绩、件数、新客户数量、统计日期
    
    /**
     * @Title: deptPerfors 
     * @Description: 部门业绩统计任务
     * @return void
     * @author Xia ZhengWei
     * @date 2016年11月7日 下午10:15:50 
     * @version V1.0
     */
    @Scheduled(cron = "0 15 19 * * ?")  
    public void deptPerfors(){  
         
        
        
        
    }
    
    
    // 第五个定时任务：每周日晚19点20统计向对接业绩表中插入数据（）
    // 表数据：客服ID、客服姓名、接待姓名、成单接待数、出单数、出单率、业绩、单均、件均、锁定接待数、出单数、出单率、业绩、单均、件均、单均产品件数
    /**
     * 
     * @Title: buttPerfors 
     * @Description: 对接业绩任务
     * @return void
     * @author Xia ZhengWei
     * @date 2016年11月7日 下午10:16:06 
     * @version V1.0
     */
    @Scheduled(cron = "0 20 19 * * ?")  
    public void buttPerfors(){  
         
        
        
        
    }
    
    /**
     * 
     * 数据库建表--每周生成一次---SQL文
     * 
     * 单均=订单金额/成单数量
     * 件均=订单金额/件数
     * 单均产品件数=件数/成单数量
     * 
     * 
     * SELECT
            e.id,
            e.`name` as 客服,
            (select g.`name` from group_info g where g.id = getOrganiId(e.group_id)) as area,
            (select ei.`name` 
                from employee_info ei 
                left join order_info o on ei.id = o.receiver_staff_id 
                inner join employee_info e on o.phone_staff_id = e.id 
                group by ei.`name`
            ) as 接待,
        
            (select sum(a.finish_order_counts) from (
                    select v.finish_order_counts, v.receiver_staff_id 
                    from visit_report_info v 
                    left join order_info o on v.receiver_staff_id = o.receiver_staff_id 
                    inner join employee_info e on o.phone_staff_id = e.id 
                    group by v.receiver_staff_id
                ) a
            ) as 成单接待数,
            
            (select count(1) from (
                    select o.id 
                    from order_info o 
                    left join order_detail_info od on o.id = od.order_id 
                    left join customer_info c on o.customer_id = c.id 
                    inner join employee_info e on o.phone_staff_id = e.id 
                    where c.type = '3' 
                    group by od.order_id) a
            ) as 成单出单数,
        
            # 成单率=接待数/出单数
        
            concat(
                round(
                    (select sum(a.finish_order_counts) from (
                            select v.finish_order_counts, v.receiver_staff_id 
                            from visit_report_info v 
                            left join order_info o on v.receiver_staff_id = o.receiver_staff_id 
                            inner join employee_info e on o.phone_staff_id = e.id 
                            group by v.receiver_staff_id
                        ) a
                    )
                /
                    (select count(1) from (
                            select od.order_id 
                            from order_info o 
                            left join order_detail_info od on o.id = od.order_id 
                            left join customer_info c on o.customer_id = c.id 
                            inner join employee_info e on o.phone_staff_id = e.id 
                            where c.type = '3' 
                            group by od.order_id) a
                    )
                *100,2),'%') as 成单出单率,
            
            (select sum(o.actual_price) 
                from order_info o 
                left join customer_info c on o.customer_id = c.id 
                inner join employee_info e on o.phone_staff_id = e.id 
                where c.type = '3'
            ) as 成单业绩,
        
            # 单均=订单金额/成单数量
        
            round(
                (select sum(o.actual_price) 
                    from order_info o 
                    left join customer_info c on o.customer_id = c.id 
                    inner join employee_info e on o.phone_staff_id = e.id 
                    where c.type = '3'
                )
            /
                (select count(1) from (
                        select od.order_id 
                        from order_info o 
                        left join order_detail_info od on o.id = od.order_id 
                        left join customer_info c on o.customer_id = c.id 
                        inner join employee_info e on o.phone_staff_id = e.id 
                        where c.type = '3' 
                        group by od.order_id) a
                )
            ,2) as 成单单均,
        
            # 件均=订单金额/件数
        
            round(
                (select sum(o.actual_price) 
                    from order_info o 
                    left join customer_info c on o.customer_id = c.id 
                    inner join employee_info e on o.phone_staff_id = e.id 
                    where c.type = '3'
                )
            /
                (select count(1) from (
                    select od.order_id 
                    from order_info o 
                    left join order_detail_info od on o.id = od.order_id 
                    left join customer_info c on o.customer_id = c.id 
                    inner join employee_info e on o.phone_staff_id = e.id 
                    where c.type = '3' 
                    group by od.order_id) a
                )
            ,2) as 成单件均,
        
        #=============================
        
            (select sum(a.locked_counts) from (
                    select v.locked_counts, v.receiver_staff_id 
                    from visit_report_info v 
                    left join order_info o on v.receiver_staff_id = o.receiver_staff_id 
                    inner join employee_info e on o.phone_staff_id = e.id 
                    group by v.receiver_staff_id
                ) a
            ) as 锁定接待数,
            
            (select count(1) from (
                    select o.id 
                    from order_info o 
                    left join order_detail_info od on o.id = od.order_id 
                    left join customer_info c on o.customer_id = c.id 
                    inner join employee_info e on o.phone_staff_id = e.id 
                    where c.type = '4' 
                    group by od.order_id) a
            ) as 锁定出单数,
        
            # 成单率=接待数/出单数
        
            concat(
                round(
                    (select sum(a.locked_counts) from (
                            select v.locked_counts, v.receiver_staff_id 
                            from visit_report_info v 
                            left join order_info o on v.receiver_staff_id = o.receiver_staff_id 
                            inner join employee_info e on o.phone_staff_id = e.id 
                            group by v.receiver_staff_id
                        ) a
                    )
                /
                    (select count(1) from (
                            select od.order_id 
                            from order_info o 
                            left join order_detail_info od on o.id = od.order_id 
                            left join customer_info c on o.customer_id = c.id 
                            inner join employee_info e on o.phone_staff_id = e.id 
                            where c.type = '4' 
                            group by od.order_id) a
                    )
                *100,2),'%') as 锁定出单率,
            
            (select sum(o.actual_price) 
                from order_info o 
                left join customer_info c on o.customer_id = c.id 
                inner join employee_info e on o.phone_staff_id = e.id 
                where c.type = '4'
            ) as 锁定业绩,
        
            # 单均=订单金额/成单数量
        
            round(
                (select sum(o.actual_price) 
                    from order_info o 
                    left join customer_info c on o.customer_id = c.id 
                    inner join employee_info e on o.phone_staff_id = e.id 
                    where c.type = '4'
                )
            /
                (select count(1) from (
                        select od.order_id 
                        from order_info o 
                        left join order_detail_info od on o.id = od.order_id 
                        left join customer_info c on o.customer_id = c.id 
                        inner join employee_info e on o.phone_staff_id = e.id 
                        where c.type = '4' 
                        group by od.order_id) a
                )
            ,2) as 锁定单均,
        
            # 件均=订单金额/件数
        
            round(
                (select sum(o.actual_price) 
                    from order_info o 
                    left join customer_info c on o.customer_id = c.id 
                    inner join employee_info e on o.phone_staff_id = e.id 
                    where c.type = '4'
                )
            /
                (select count(1) from (
                    select od.order_id 
                    from order_info o 
                    left join order_detail_info od on o.id = od.order_id 
                    left join customer_info c on o.customer_id = c.id 
                    inner join employee_info e on o.phone_staff_id = e.id 
                    where c.type = '4' 
                    group by od.order_id) a
                )
            ,2) as 锁定件均,
        
            # 单均产品件数=件数/成单数量
        
            round(
                (select sum(amounts) from (
                                (select sum(od.amount) as amounts
                                from order_info o 
                                left join order_detail_info od on o.id = od.order_id 
                                left join customer_info c on o.customer_id = c.id 
                                inner join employee_info e on o.phone_staff_id = e.id 
                                where c.type = '3')
                    UNION
                                (select sum(od.amount) as amounts
                                from order_info o 
                                left join order_detail_info od on o.id = od.order_id 
                                left join customer_info c on o.customer_id = c.id 
                                inner join employee_info e on o.phone_staff_id = e.id 
                                where c.type = '4')
                    ) a
                )
            /
                (select sum(counts) from (
                                (select count(1) as counts from (
                                select o.id 
                                from order_info o 
                                left join order_detail_info od on o.id = od.order_id 
                                left join customer_info c on o.customer_id = c.id 
                                inner join employee_info e on o.phone_staff_id = e.id 
                                where c.type = '3' 
                                group by od.order_id) a
                            )
                    UNION
                            (select count(1) as counts from (
                                select o.id 
                                from order_info o 
                                left join order_detail_info od on o.id = od.order_id 
                                left join customer_info c on o.customer_id = c.id 
                                inner join employee_info e on o.phone_staff_id = e.id 
                                where c.type = '4' 
                                group by od.order_id) a
                            )   
                    ) a
                )
            ,2) as 单均产品件数
        
        from employee_info e
        where e.position_type = '1'
        group by e.`name`
        order by e.`name`
     * 
     */
    
    
}
