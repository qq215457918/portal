package com.portal.task;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.portal.bean.Criteria;
import com.portal.bean.CustomerInfo;
import com.portal.bean.DeptPerformanceInfo;
import com.portal.bean.EmployeeInfo;
import com.portal.bean.VisitEverydayInfo;
import com.portal.bean.VisitReportInfo;
import com.portal.bean.result.VisitEverydayInfoForm;
import com.portal.common.util.DateUtil;
import com.portal.common.util.UUidUtil;
import com.portal.service.CustomerInfoService;
import com.portal.service.DeptPerformanceInfoService;
import com.portal.service.EmployeeInfoService;
import com.portal.service.OrderInfoService;
import com.portal.service.ReceptionInfoService;
import com.portal.service.ReportTrackService;
import com.portal.service.VisitEverydayInfoService;
import com.portal.service.VisitReportInfoService;

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
    
    // 每日接待Service
    @Autowired
    private ReceptionInfoService receptionService;
    
    // 每日登门Service
    @Autowired
    private VisitEverydayInfoService visitEverydayService;
    
    // 客户Service
    @Autowired
    private CustomerInfoService customerService;
    
    // 员工Service
    @Autowired
    private EmployeeInfoService employeeService;
    
    // 接待统计Service
    @Autowired
    private VisitReportInfoService visitReportService;
    
    // 部门业绩统计Service
    @Autowired
    private DeptPerformanceInfoService deptPerforService;

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
    // 每日业绩提交审核那块直接走的查询 没走这张表, 暂时不做这个了
    //@Scheduled(cron = "0 00 19 * * ?")  
    public void everyPerformances(){  
        logger.info("启动每日19点的定时任务, 操作内容：统计每日业绩存储到每日业绩表-------------------");
        
        /*
         * 先查询所有未离职或删除的员工信息
         * 循环根据员工ID查询对应客户类型的登门数量（日常接待表中的数据）
         * 再查询订单表中的出单数量及金额
         */
        // 查询所有未禁用或删除的员工信息
        /*criteria.put("deleteFlag", "0");
        criteria.put("status", "0");
        List<EmployeeInfo> employeeList = employeeService.selectByExample(criteria);
        if(employeeList != null && employeeList.size() > 0) {
            // 循环根据员工ID查询对应客户类型的登门数量（日常接待表中的数据）
            for (EmployeeInfo employeeInfo : employeeList) {
                
            }
        }*/
        
        /*select 
        (select COUNT(1) 
            from reception_info r 
            left join customer_info c on r.customer_id = c.id
            where (r.receiver_staff_id = '123' or r.phone_staff_id = '123')
            and r.create_date >= '2016-10-19'
            and r.create_date <= '2016-10-19 23:59:59'
            and c.type = '0') as no_buy_counts,
            (select COUNT(1) 
            from reception_info r 
            left join customer_info c on r.customer_id = c.id
            where (r.receiver_staff_id = '123' or r.phone_staff_id = '123')
            and r.create_date >= '2016-10-19'
            and r.create_date <= '2016-10-19 23:59:59'
            and c.type = '1') as repeat_counts,
            (select COUNT(1) 
            from reception_info r 
            left join customer_info c on r.customer_id = c.id
            where (r.receiver_staff_id = '123' or r.phone_staff_id = '123')
            and r.create_date >= '2016-10-19'
            and r.create_date <= '2016-10-19 23:59:59'
            and c.type = '2') as roadshow_counts,
            (select COUNT(1) 
            from reception_info r 
            left join customer_info c on r.customer_id = c.id
            where (r.receiver_staff_id = '123' or r.phone_staff_id = '123')
            and r.create_date >= '2016-10-19'
            and r.create_date <= '2016-10-19 23:59:59'
            and c.type = '3') as finish_counts,
            (select COUNT(1) 
            from reception_info r 
            left join customer_info c on r.customer_id = c.id
            where (r.receiver_staff_id = '123' or r.phone_staff_id = '123')
            and r.create_date >= '2016-10-19'
            and r.create_date <= '2016-10-19 23:59:59'
            and c.type = '4') as locked_counts
            from reception_info
            where (receiver_staff_id = '123' or phone_staff_id = '123')
            and create_date >= '2016-10-19'
            and create_date <= '2016-10-19 23:59:59'
        */
        
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
    //@Scheduled(cron = "0 00 19 * * ?")  
    public void visitEveryDay(){  
        logger.info("启动每日19点05分的定时任务, 操作内容：统计每日接待情况存储到每日登门表-------------------");
        criteria.clear();
        criteria.put("startTime", DateUtil.formatDate(new Date(), "yyyy-MM-dd"));
        criteria.put("endTime", DateUtil.formatDate(new Date(), "yyyy-MM-dd 23:59:59"));
        List<VisitEverydayInfoForm> list = visitEverydayService.getTaskDataByCondition(criteria);
        int count = 0;
        if(list.size() > 0) {
            VisitEverydayInfo info = null;
            for (VisitEverydayInfoForm form : list) {
                info = new VisitEverydayInfo();
                BeanUtils.copyProperties(form, info);
                info.setId(UUidUtil.getUUId());
                info.setExercise("0");
                count = visitEverydayService.insert(info);
            }
        }
        logger.info("每日19点05分的定时任务结束, 共存储" + count + "条记录-------------------");
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
    //@Scheduled(cron = "0 10 19 * * ?")  
    public void receiveReport(){  
        logger.info("启动每日19点10分的定时任务, 操作内容：统计每日接待情况存储到每日接待统计表-------------------");
        /**
         * 1. 获取所有接待人员
         * 2. 循环获取接待人员当日接待的所有客户
         * 3. 循环根据客户的ID设置对应的数量、订单数和订单金额
         */
        // 获取所有接待人员
        criteria.clear();
        criteria.put("deleteFlag", "0");
        criteria.put("status", "0");
        criteria.put("positionType", "2");
        List<EmployeeInfo> employeeList = employeeService.selectByExample(criteria);
        int count = 0;
        int customerCounts = 0;
        int newCounts = 0;
        int newOrders = 0;
        int newAmounts = 0;
        int repeatCounts = 0;
        int repeatOrders = 0;
        int repeatAmounts = 0;
        int roadshowCounts = 0;
        int roadshowOrders = 0;
        int roadshowAmounts = 0;
        int finishOrderCounts = 0;
        int finishOrders = 0;
        int finishAmounts = 0;
        int lockedCounts = 0;
        int lockedOrders = 0;
        int lockedAmounts = 0;
        
        VisitReportInfo info = null;
        String startTime = DateUtil.formatDate(new Date(), "yyyy-MM-dd");
        String endTime = DateUtil.formatDate(new Date(), "yyyy-MM-dd 23:59:59");
        for (EmployeeInfo employeeInfo : employeeList) {
            // 创建对象并赋值
            info = new VisitReportInfo();
            info.setId(UUidUtil.getUUId());
            info.setReceiverStaffId(employeeInfo.getId());
            info.setReceiverStaffName(employeeInfo.getName());
            info.setReceiverArea(employeeInfo.getOrganizationId());
            // 根据员工ID到每日接待表中获取接待过的所有客户ID
            criteria.clear();
            criteria.put("receiverStaffId", employeeInfo.getId());
            criteria.put("startTime", startTime);
            criteria.put("endTime", endTime);
            List<String> customerList = receptionService.getByConditions(criteria);
            // 重置数量及金额
            customerCounts = 0;
            newCounts = 0;
            newOrders = 0;
            newAmounts = 0;
            repeatCounts = 0;
            repeatOrders = 0;
            repeatAmounts = 0;
            roadshowCounts = 0;
            roadshowOrders = 0;
            roadshowAmounts = 0;
            finishOrderCounts = 0;
            finishOrders = 0;
            finishAmounts = 0;
            lockedCounts = 0;
            lockedOrders = 0;
            lockedAmounts = 0;
            // 循环客户ID, 判断是否为新客户, 不是则获取对应的登门数、成单数及成单金额, 是则新客户数量加1
            for (String customerId : customerList) {
                criteria.clear();
                criteria.put("customerId", customerId);
                criteria.put("startTime", startTime);
                criteria.put("endTime", endTime);
                // 判断用户是否为新客户
                int newCount = visitReportService.checkIsNewCount(criteria);
                if(newCount == 1) {
                    // 是新客户
                    newCounts += newCount;
                    // 获取该客户的成单数量
                    newOrders += getOrderCounts(criteria);
                    // 获取该客户的成单金额
                    newAmounts += getOrderAmounts(criteria);
                }else {
                    // 不是新客户, 获取客户信息并判断客户的类型
                    // 获取客户信息
                    CustomerInfo customerInfo = customerService.selectByPrimaryKey(customerId);
                    int type = Integer.parseInt(customerInfo.getType());
                    switch (type) {
                    case 1:
                        // 1. 重复登门客户
                        repeatCounts += 1;
                        // 获取该客户的成单数量
                        repeatOrders += getOrderCounts(criteria);
                        // 获取该客户的成单金额
                        repeatAmounts += getOrderAmounts(criteria);
                        break;
                    case 2:
                        // 2. 说明会客户
                        roadshowCounts += 1;
                        // 获取该客户的成单数量
                        roadshowOrders += getOrderCounts(criteria);
                        // 获取该客户的成单金额
                        roadshowAmounts += getOrderAmounts(criteria);
                        break;
                    case 3:
                        // 3. 成单客户
                        finishOrderCounts += 1;
                        // 获取该客户的成单数量
                        finishOrders += getOrderCounts(criteria);
                        // 获取该客户的成单金额
                        finishAmounts += getOrderAmounts(criteria);
                        break;
                    case 4:
                        // 4. 锁定客户
                        lockedCounts += 1;
                        // 获取该客户的成单数量
                        lockedOrders += getOrderCounts(criteria);
                        // 获取该客户的成单金额
                        lockedAmounts += getOrderAmounts(criteria);
                        break;
                    default:
                        break;
                    }
                }
            }
            info.setCustomerCounts(customerCounts);
            info.setNewCounts(newCounts);
            info.setNewOrders(newOrders);
            info.setNewAmounts(newAmounts);
            info.setRepeatCounts(repeatCounts);
            info.setRepeatOrders(repeatOrders);
            info.setRepeatAmounts(repeatAmounts);
            info.setRoadshowCounts(roadshowCounts);
            info.setRoadshowOrders(roadshowOrders);
            info.setRoadshowAmounts(roadshowAmounts);
            info.setFinishOrderCounts(finishOrderCounts);
            info.setFinishOrders(finishOrders);
            info.setFinishAmounts(finishAmounts);
            info.setLockedCounts(lockedCounts);
            info.setLockedOrders(lockedOrders);
            info.setLockedAmounts(lockedAmounts);
            // 插入数据
            count += visitReportService.insert(info);
        }
        logger.info("每日19点10分的定时任务结束, 共存储" + count + "条记录-------------------");
    }
    
    /**
     * @Title: getOrderCounts 
     * @Description: 获取对应客户指定时间段的出单数
     * @param criteria
     * @return int
     * @author Xia ZhengWei
     * @date 2017年2月8日 下午10:55:58 
     * @version V1.0
     */
    private int getOrderCounts(Criteria criteria) {
        return orderService.getOrderCounts(criteria);
    }
    
    /**
     * @Title: getOrderAmounts 
     * @Description: 获取对应客户指定时间段的实际出单金额
     * @param criteria
     * @return int
     * @author Xia ZhengWei
     * @date 2017年2月8日 下午10:56:02 
     * @version V1.0
     */
    private int getOrderAmounts(Criteria criteria) {
        return orderService.getOrderAmounts(criteria);
    }
    
    
    // 第四个定时任务：每日晚19点20分统计向部门业绩统计表插入数据（查询订单、员工、客户表）
    // 表数据：机构ID、机构名称、部门ID、部门名称、小组ID、小组名称、人员ID、人员名称、业绩、件数、新客户数量、统计日期
    
    /**
     * @Title: deptPerfors 
     * @Description: 部门业绩统计任务
     * @return void
     * @author Xia ZhengWei
     * @date 2016年11月7日 下午10:15:50 
     * @version V1.0
     */
    //@Scheduled(cron = "0 20 19 * * ?")  
    public void deptPerfors(){  
        logger.info("启动每日19点20分的定时任务, 操作内容：统计订单、员工、客户表向部门业绩统计表插入数据-------------------");
        int count = 0;
        criteria.clear();
        criteria.put("deleteFlag", "0");
        criteria.put("startTime", DateUtil.formatDate(new Date(), "yyyy-MM-dd"));
        criteria.put("endTime", DateUtil.formatDate(new Date(), "yyyy-MM-dd 23:59:59"));
        List<DeptPerformanceInfo> perforList = deptPerforService.getPerformanceForTask(criteria);
        if(perforList != null && perforList.size() > 0) {
            for (DeptPerformanceInfo deptPerforInfo : perforList) {
                deptPerforInfo.setId(UUidUtil.getUUId());
                deptPerforInfo.setReportDate(new Date());
                count += deptPerforService.insert(deptPerforInfo);
            }
        }
        logger.info("每日19点20分的定时任务结束, 共存储" + count + "条记录-------------------");
    }
    
    
    // 第五个定时任务：每周日晚19点30统计向对接业绩表中插入数据（）
    // 表数据：客服ID、客服姓名、接待姓名、成单接待数、出单数、出单率、业绩、单均、件均、锁定接待数、出单数、出单率、业绩、单均、件均、单均产品件数
    /**
     * @Title: buttPerfors 
     * @Description: 对接业绩任务
     * @return void
     * @author Xia ZhengWei
     * @date 2016年11月7日 下午10:16:06 
     * @version V1.0
     */
    //@Scheduled(cron = "0 30 19 ? * SUN")  
    public void buttPerfors(){  
        logger.info("启动每周日19点30分的定时任务, 操作内容：统计向对接业绩表中插入数据-------------------");
        
        // 先获取所有客服信息, 根据客服ID获取其他数据
        // 获取所有客服人员
        criteria.clear();
        criteria.put("deleteFlag", "0");
        criteria.put("status", "0");
        criteria.put("positionType", "1");  
        /*List<EmployeeInfo> employeeList = employeeService.selectByExample(criteria);
        if(employeeList != null && employeeList.size() > 0) {
            for (EmployeeInfo employeeInfo : employeeList) {
                String employeeId = employeeInfo.getId(); 
                
                
                
                
                
            }
        }*/
        
        int count = 0;
        criteria.clear();
        criteria.put("deleteFlag", "0");
        criteria.put("startTime", DateUtil.formatDate(new Date(), "yyyy-MM-dd"));
        criteria.put("endTime", DateUtil.formatDate(new Date(), "yyyy-MM-dd 23:59:59"));
        
        
        
        
        
        
        
        
        logger.info("每日19点30分的定时任务结束, 共存储" + count + "条记录-------------------");
    }
    
    /**
     * 
     * -- 接待名称
        -- select ei.`name` 
        -- from employee_info ei 
        -- left join order_info o on ei.id = o.receiver_staff_id 
        -- inner join employee_info e on o.phone_staff_id = e.id 
        -- where e.id = '5002'
        -- and o.create_date >= '2016-12-12'
        -- and o.create_date <= '2016-12-18 23:59:59'
        -- group by ei.`name`
        
        -- 成单/锁定-接待数
        --  select sum(v.finish_order_counts), sum(v.locked_counts) 
        --  from visit_report_info v
        --  where v.receiver_staff_id = '5002'
        --  and v.report_date >= '2016-09-26'
        --  and v.report_date <= '2016-09-26 23:59:59'
        
        -- 成单/锁定-出单数
        --  select sum(v.finish_orders), sum(v.locked_orders) 
        --  from visit_report_info v
        --  where v.receiver_staff_id = '5002'
        --  and v.report_date >= '2016-09-26'
        --  and v.report_date <= '2016-09-26 23:59:59'
        
        -- 成单出单率=接待数/出单数
        -- 锁定出单率=接待数/出单数
        
        -- 成单/锁定-业绩
        --  select sum(v.finish_amounts), sum(v.locked_amounts) 
        --  from visit_report_info v
        --  where v.receiver_staff_id = '5002'
        --  and v.report_date >= '2016-09-26'
        --  and v.report_date <= '2016-09-26 23:59:59'
        
        -- 成单/锁定-单均=订单金额/成单数量
        
        
        -- 成单/锁定-件均=订单金额/件数
        
        -- 成单/锁定-件数(3/4)
        -- select count(1) from (
        --  select od.order_id 
        --  from order_info o 
        --  left join order_detail_info od on o.id = od.order_id 
        --  left join customer_info c on o.customer_id = c.id 
        --  inner join employee_info e on o.phone_staff_id = e.id 
        --  where c.type = '3' 
        --   and e.id = '5002'
        --   and o.create_date >= '2016-12-12'
        --   and o.create_date <= '2016-12-18 23:59:59'
        --  group by od.order_id
        -- ) a
        
        -- 单均产品件数=成单锁定的总件数/成单锁定的总单数
        -- (select sum(od.amount) as amounts
        -- from order_info o 
        -- left join order_detail_info od on o.id = od.order_id 
        -- left join customer_info c on o.customer_id = c.id 
        -- inner join employee_info e on o.phone_staff_id = e.id 
        -- where c.type = '3'

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
