package com.portal.task;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.portal.bean.ButtPerforDetailInfo;
import com.portal.bean.Criteria;
import com.portal.bean.CustomerInfo;
import com.portal.bean.DeptPerformanceInfo;
import com.portal.bean.EmployeeInfo;
import com.portal.bean.GroupInfo;
import com.portal.bean.VisitEverydayInfo;
import com.portal.bean.VisitReportInfo;
import com.portal.bean.result.VisitEverydayInfoForm;
import com.portal.common.util.DateUtil;
import com.portal.common.util.UUidUtil;
import com.portal.service.ButtPerforDetailInfoService;
import com.portal.service.CustomerInfoService;
import com.portal.service.DeptPerformanceInfoService;
import com.portal.service.EmployeeInfoService;
import com.portal.service.GroupInfoService;
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
    
    // 展厅客服对接业绩Service
    @Autowired
    private ButtPerforDetailInfoService buttperforService;
    
    // 组织机构Service
    @Autowired
    private GroupInfoService groupInfoService;

    // 公共查询条件类
    Criteria criteria = new Criteria();
    
    // 第一个定时任务：每日晚19点05分统计向每日登门表中插入数据（根据日常接待表）
    
    /**
     * @Title: visitEveryDay 
     * @Description: 统计每日登门任务(从每日接待和订单表中获取数据)
     * @return void
     * @author Xia ZhengWei
     * @date 2016年11月7日 下午10:14:44 
     * @version V1.0
     */
    @Scheduled(cron = "0 00 19 * * ?")  
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
    
    
    // 第二个定时任务：每日晚19点10分统计向接待统计表中插入数据（日常接待、订单）
    
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
            info.setReportDate(new Date());
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
    
    
    // 第三个定时任务：每日晚19点20分统计向部门业绩统计表插入数据（查询订单、员工、客户表）改为每半小时一次
    // 表数据：机构ID、机构名称、部门ID、部门名称、小组ID、小组名称、人员ID、人员名称、业绩、件数、新客户数量、统计日期
    
    /**
     * @Title: deptPerfors 
     * @Description: 部门业绩统计任务
     * @return void
     * @author Xia ZhengWei
     * @date 2016年11月7日 下午10:15:50 
     * @version V1.0
     */
    @Scheduled(cron = "0 0/30 * * * ?")  
    public void deptPerfors(){  
        logger.info("启动每日半小时执行一次的定时任务, 操作内容：统计订单、员工、客户表向部门业绩统计表插入数据-------------------");
        int count = 0;
        criteria.clear();
        criteria.put("deleteFlag", "0");
        criteria.put("startTime", DateUtil.formatDate(new Date(), "yyyy-MM-dd"));
        criteria.put("endTime", DateUtil.formatDate(new Date(), "yyyy-MM-dd 23:59:59"));
        List<DeptPerformanceInfo> perforList = deptPerforService.getPerformanceForTask(criteria);
        if(CollectionUtils.isNotEmpty(perforList)) {
            count = deptPerforService.addPerformance(perforList, count);
        }
        logger.info("每日每日半小时执行一次的定时任务结束, 共存储" + count + "条记录-------------------");
    }
    
    
    // 第四个定时任务：每周日晚19点30统计向对接业绩表中插入数据（）
    // 表数据：客服ID、客服姓名、接待姓名、成单接待数、出单数、出单率、业绩、单均、件均、锁定接待数、出单数、出单率、业绩、单均、件均、单均产品件数
    /**
     * @Title: buttPerfors 
     * @Description: 对接业绩任务
     * @return void
     * @author Xia ZhengWei
     * @date 2016年11月7日 下午10:16:06 
     * @version V1.0
     */
    @Scheduled(cron = "0 30 19 ? * SUN")
    public void buttPerfors(){  
        logger.info("启动每周日19点30分的定时任务, 操作内容：统计向对接业绩表中插入数据-------------------");
        
        int count = 0;
        // 先获取所有客服信息, 根据客服ID获取其他数据
        // 获取所有客服人员
        criteria.clear();
        criteria.put("deleteFlag", "0");
        criteria.put("status", "0");
        criteria.put("positionType", "1");  
        List<EmployeeInfo> employeeList = employeeService.selectByExample(criteria);
        if(employeeList != null && employeeList.size() > 0) {
            // 获取本周一和周日
            String startTime = DateUtil.formatDate(DateUtil.getNowWeekMonday(new Date()), "yyyy-MM-dd");
            String endTime = DateUtil.formatDate(DateUtil.getNowWeekSunday(new Date()), "yyyy-MM-dd 23:59:59");
            // 创建对接对象
            ButtPerforDetailInfo buttPerforInfo = null;
            for (EmployeeInfo employeeInfo : employeeList) {
                String employeeId = employeeInfo.getId(); 
                // 统计每周对接业绩根据客服ID获取接待名称
                criteria.clear();
                criteria.put("deleteFlag", "0");
                criteria.put("employeeId", employeeId);
                criteria.put("startTime", startTime);
                criteria.put("endTime", endTime);
                Map<String, String> receiveNameAndId = employeeService.getReceiveNameByPhoneId(criteria);
                // 如果接待信息为空则略过跳至下一个
                if(receiveNameAndId != null) {
                    buttPerforInfo = new ButtPerforDetailInfo();
                    // 设置客服ID
                    buttPerforInfo.setPhoneStaffId(employeeId);
                    // 根据客服ID获取客服信息
                    EmployeeInfo info = employeeService.selectByPrimaryKey(employeeId);
                    // 设置客服姓名
                    buttPerforInfo.setPhoneStaffName(info.getName());
                    // 根据客服信息中的组织机构ID获取对应信息
                    GroupInfo groupInfo = groupInfoService.selectByPrimaryKey(info.getOrganizationId());
                    // 设置机构名称
                    buttPerforInfo.setPhoneStaffGroupName(groupInfo.getName());
                    
                    // 设置接待姓名
                    buttPerforInfo.setReceiveStaffName(receiveNameAndId.get("name"));
                    // 获取成单/锁定-接待数/出单数
                    criteria.clear();
                    criteria.put("receiverStaffId", receiveNameAndId.get("id"));
                    criteria.put("startDate", startTime);
                    criteria.put("endDate", endTime);
                    Map<String, Integer> countsAndOrders = visitReportService.getRecevieCountsAndOrders(criteria);
                    // 成单出单率=接待数/出单数
                    // 锁定出单率=接待数/出单数
                    if(countsAndOrders != null) {
                        Integer finishOrderCounts = countsAndOrders.get("finishOrderCounts");
                        Integer finishOrders = countsAndOrders.get("finishOrders");
                        Integer finishAmounts = countsAndOrders.get("finishAmounts");
                        Integer lockedCounts = countsAndOrders.get("lockedCounts");
                        Integer lockedOrders = countsAndOrders.get("lockedOrders");
                        Integer lockedAmounts = countsAndOrders.get("lockedAmounts");
                        // 设置成单接待数
                        buttPerforInfo.setReceiveFinishedCounts(finishOrderCounts);
                        // 设置成单出单数
                        buttPerforInfo.setOutOrdersOfFinished(finishOrders);
                        // 设置成单业绩
                        buttPerforInfo.setPerformanceOfFinished(finishAmounts.longValue());
                        // 设置锁定接待数
                        buttPerforInfo.setReceiveLockedCounts(lockedCounts);
                        // 设置锁定出单数
                        buttPerforInfo.setOutOrdersOfLocked(lockedOrders);
                        // 设置锁定业绩
                        buttPerforInfo.setPerformanceOfLocked(lockedAmounts.longValue());
                        // 设置成单出单率
                        if(finishOrderCounts == 0 || finishOrders == 0) {
                            buttPerforInfo.setOutOrderRateOfFinished("0");
                        }else {
                            buttPerforInfo.setOutOrderRateOfFinished(String.format("%.2f", finishOrderCounts/finishOrders) + "%");
                            // 设置成单单均
                            buttPerforInfo.setOrderAvgOfFinished(String.format("%.2f", finishAmounts/finishOrders));
                        }
                        // 设置锁定出单率
                        if(lockedCounts == 0 || lockedOrders == 0) {
                            buttPerforInfo.setOutOrderRateOfLocked("0");
                        }else {
                            buttPerforInfo.setOutOrderRateOfLocked(String.format("%.2f", lockedCounts/lockedOrders) + "%");
                            // 设置锁定单均
                            buttPerforInfo.setOrderAvgOfLocked(String.format("%.2f", lockedAmounts/lockedOrders));
                        }
                        // 获取成单件数
                        criteria.clear();
                        criteria.put("type", "3");
                        criteria.put("employeeId", employeeId);
                        criteria.put("startDate", startTime);
                        criteria.put("endDate", endTime);
                        int finishOrderGoodsCounts = orderService.getOrderGoodsCounts(criteria);
                        // 获取锁定件数
                        criteria.put("type", "4");
                        int lockedOrderGoodsCounts = orderService.getOrderGoodsCounts(criteria);
                        
                        // 设置成单/锁定-件均=订单金额/件数
                        if(finishOrderGoodsCounts == 0) {
                            buttPerforInfo.setPieceAvgOfFinished("0");
                        }else {
                            buttPerforInfo.setPieceAvgOfFinished(String.format("%.2f", finishAmounts/finishOrderGoodsCounts));
                        }
                        if(lockedOrderGoodsCounts == 0) {
                            buttPerforInfo.setPieceAvgOfLocked("0");
                        }else {
                            buttPerforInfo.setPieceAvgOfLocked(String.format("%.2f", lockedAmounts/lockedOrderGoodsCounts));
                        }
                        
                        // 单均产品件数=成单锁定的总件数/成单锁定的总单数
                        if((finishOrders == 0 & lockedOrders == 0) || (finishOrderGoodsCounts == 0 & lockedOrderGoodsCounts == 0)) {
                            buttPerforInfo.setOrderAvgOfGoodsCounts("0");
                        }else {
                            buttPerforInfo.setOrderAvgOfGoodsCounts(String.format("%.2f", (finishOrderGoodsCounts + lockedOrderGoodsCounts) / (finishOrders + lockedOrders)));
                        }
                    }else {
                        buttPerforInfo.setReceiveFinishedCounts(0);
                        buttPerforInfo.setOutOrdersOfFinished(0);
                        buttPerforInfo.setPerformanceOfFinished(0L);
                        buttPerforInfo.setReceiveLockedCounts(0);
                        buttPerforInfo.setOutOrdersOfLocked(0);
                        buttPerforInfo.setPerformanceOfLocked(0L);
                        buttPerforInfo.setOutOrderRateOfFinished("0");
                        buttPerforInfo.setOutOrderRateOfLocked("0");
                        buttPerforInfo.setOrderAvgOfFinished("0");
                        buttPerforInfo.setOrderAvgOfLocked("0");
                        buttPerforInfo.setPieceAvgOfFinished("0");
                        buttPerforInfo.setPieceAvgOfLocked("0");
                        buttPerforInfo.setOrderAvgOfGoodsCounts("0");
                    }
                    buttPerforInfo.setId(UUidUtil.getUUId());
                    buttPerforInfo.setReportDate(new Date());
                    count += buttperforService.insert(buttPerforInfo);
                }else {
                    // 没有对接接待人员, 继续向下执行
                    continue;
                }
            }
        }
        logger.info("每日19点30分的定时任务结束, 共存储" + count + "条记录-------------------");
    }
    
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
    
}
