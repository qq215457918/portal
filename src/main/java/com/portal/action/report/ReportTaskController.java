package com.portal.action.report;

import org.springframework.stereotype.Component;

@Component
public class ReportTaskController {
    
    // 第一个定时任务：每日晚19点统计向每日业绩报表中插入数据（查询订单表、员工、客户）
    
    
    // 第二个定时任务：每日晚19点05分统计向每日登门表中插入数据（根据日常接待表）
    
    
    // 第三个定时任务：每日晚19点10分统计向接待统计表中插入数据（日常接待、订单）
    
    
    // 第四个定时任务：每日晚19点15分统计向部门业绩统计表插入数据（查询订单、员工、客户表）
    // 表数据：机构ID、机构名称、部门ID、部门名称、小组ID、小组名称、人员ID、人员名称、业绩、件数、新客户数量、统计日期
    
    
    // 第五个定时任务：每周日晚19点20统计向对接业绩表中插入数据（）
    // 表数据：客服ID、客服姓名、接待姓名、成单接待数、出单数、出单率、业绩、单均、件均、锁定接待数、出单数、出单率、业绩、单均、件均、单均产品件数
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
