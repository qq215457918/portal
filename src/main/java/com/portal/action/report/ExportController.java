package com.portal.action.report;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.portal.bean.ButtPerforDetailInfo;
import com.portal.bean.Criteria;
import com.portal.bean.result.DeptPerforInfoForm;
import com.portal.bean.result.OrderDetailInfoForm;
import com.portal.bean.result.OrderFundSettlementForm;
import com.portal.bean.result.StorehouseOperateInfoForm;
import com.portal.bean.result.VisitEverydayInfoForm;
import com.portal.bean.result.VisitReportInfoForm;
import com.portal.common.util.DateUtil;
import com.portal.common.util.ExportBean;
import com.portal.common.util.ExportExcelJxl;
import com.portal.common.util.StringUtil;
import com.portal.service.ButtPerforDetailInfoService;
import com.portal.service.DeptPerformanceInfoService;
import com.portal.service.OrderDetailInfoService;
import com.portal.service.OrderInfoService;
import com.portal.service.StorehouseOperateInfoService;
import com.portal.service.VisitEverydayInfoService;
import com.portal.service.VisitReportInfoService;

/**
 * @ClassName: ExportController 
 * @Description: 报表系统导出Excel控制类
 * @author Xia ZhengWei
 * @date 2017年1月4日 下午11:30:52
 */
@Controller
@RequestMapping("/reportExport")
public class ExportController {
    
    @Autowired
    private ButtPerforDetailInfoService buttPerforService;
    
    @Autowired
    private VisitReportInfoService visitReportService;
    
    @Autowired
    private VisitEverydayInfoService visitEveryDayService;
    
    @Autowired
    private DeptPerformanceInfoService deptPerformanceService;
    
    @Autowired
    private OrderInfoService orderService;
    
    @Autowired
    private StorehouseOperateInfoService storeHouseService;
    
    @Autowired
    private OrderDetailInfoService orderDetailService;

    /**
     * @Title: exportButtPerfor 
     * @Description: 导出展厅客服对接业绩
     * @param request
     * @param response
     * @throws IOException 
     * @return void
     * @throws 
     * @author Xia ZhengWei
     * @date 2017年1月4日 下午11:30:38 
     * @version V1.0
     */
    @RequestMapping("/exportButtPerfor")
    public void exportButtPerfor(HttpServletRequest request, HttpServletResponse response) throws IOException{
        Criteria criteria = new Criteria();
        // 开始日期
        String startReportDate = request.getParameter("startReportDate");
        String endReportDate = request.getParameter("endReportDate");
        String viewPhoneStaffName = request.getParameter("viewPhoneStaffName");
        String viewReceiveStaffName = request.getParameter("viewReceiveStaffName");
        String viewPhoneStaffGroupName = request.getParameter("viewPhoneStaffGroupName");
        
        if(StringUtil.isNotBlank(startReportDate)){
            criteria.put("startReportDate", startReportDate);
        }else {
            criteria.put("startReportDate", DateUtil.formatDate(DateUtil.getLastWeekMonday(new Date()), "yyyy-MM-dd"));
        }
        if(StringUtil.isNotBlank(endReportDate)){
            criteria.put("endReportDate",  DateUtil.formatDate(DateUtil.parseDate(endReportDate, "yyyy-MM-dd"), "yyyy-MM-dd 23:59:59"));
        }else {
            criteria.put("endReportDate", DateUtil.formatDate(DateUtil.getLastWeekSunday(new Date()), "yyyy-MM-dd 23:59:59"));
        }
        if(StringUtil.isNotBlank(viewPhoneStaffName)){
            criteria.put("viewPhoneStaffName", viewPhoneStaffName);
        }
        if(StringUtil.isNotBlank(viewReceiveStaffName)){
            criteria.put("viewReceiveStaffName", viewReceiveStaffName);
        }
        if(StringUtil.isNotBlank(viewPhoneStaffGroupName)){
            criteria.put("viewPhoneStaffGroupName", viewPhoneStaffGroupName);
        }
        
        List<ButtPerforDetailInfo> list = buttPerforService.seleteByCondition(criteria);
        
        try{
            @SuppressWarnings("deprecation")
            String filePath = request.getRealPath("resources/excel");
            ExportBean excelBean = new ExportBean();
            makeButtPerforData(viewPhoneStaffGroupName, list, excelBean);
            excelBean.setExportMode(0);
            excelBean.setSourceFile(filePath + "\\buttPerfor_excel.xls");
            new ExportExcelJxl(response, excelBean);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void makeButtPerforData(String area, List<ButtPerforDetailInfo> list, ExportBean excelBean){
        Object[][] data = new Object[list.size()+1][16];
        int i = 1;
        data[0] = new Object[]{"客服","接待","所属机构","成单接待数","成单出单数","成单出单率","成单业绩","成单单均","成单件均",
                                "锁定接待数","锁定出单数","锁定出单率","锁定业绩","锁定单均","锁定件均","单均产品件数"};
        for(ButtPerforDetailInfo e : list){
            data[i][0] = (null == e.getPhoneStaffName() ? "" : e.getPhoneStaffName());
            data[i][1] = (null == e.getReceiveStaffName() ? "" : e.getReceiveStaffName());
            data[i][2] = area;
            data[i][3] = (null == e.getReceiveFinishedCounts() ? "" : e.getReceiveFinishedCounts());
            data[i][4] = (null == e.getOutOrdersOfFinished() ? "" : e.getOutOrdersOfFinished());
            data[i][5] = (null == e.getOutOrderRateOfFinished() ? "" : e.getOutOrderRateOfFinished());
            data[i][6] = (null == e.getPerformanceOfFinished() ? "" : e.getPerformanceOfFinished());
            data[i][7] = (null == e.getOrderAvgOfFinished() ? "" : e.getOrderAvgOfFinished());
            data[i][8] = (null == e.getPieceAvgOfFinished() ? "" : e.getPieceAvgOfFinished());
            data[i][9] = (null == e.getReceiveLockedCounts() ? "" : e.getReceiveLockedCounts());
            data[i][10] = (null == e.getOutOrdersOfLocked() ? "" : e.getOutOrdersOfLocked());
            data[i][11] = (null == e.getOutOrderRateOfLocked() ? "" : e.getOutOrderRateOfLocked());
            data[i][12] = (null == e.getPerformanceOfLocked() ? "" : e.getPerformanceOfLocked());
            data[i][13] = (null == e.getOrderAvgOfLocked() ? "" : e.getOrderAvgOfLocked());
            data[i][14] = (null == e.getPieceAvgOfLocked() ? "" : e.getPieceAvgOfLocked());
            data[i][15] = (null == e.getOrderAvgOfGoodsCounts() ? "" : e.getOrderAvgOfGoodsCounts());
            i++;
        }
        excelBean.setData(data);
        excelBean.setExcelName(area + "展厅客服对接业绩");
        excelBean.setSheetName(area + "展厅客服对接业绩");
    }
    
    //------------------------------ 导出展厅客服对接业绩 ------------------------------
    
    /**
     * @Title: exportSalesmanStatement 
     * @Description: 导出展厅客服对接业绩
     * @param request
     * @param response
     * @throws IOException 
     * @return void
     * @author Xia ZhengWei
     * @date 2017年1月5日 下午9:57:46 
     * @version V1.0
     */
    @RequestMapping("/exportSalesmanStatement")
    public void exportSalesmanStatement(HttpServletRequest request, HttpServletResponse response) throws IOException{
        Criteria criteria = new Criteria();
        // 开始日期
        String area = request.getParameter("area");
        String receiveStaffName = request.getParameter("receiveStaffName");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        
        criteria.setOrderByClause("e.`name` asc");
        // 查询职位类型为业务员
        criteria.put("positionType", "2");
        // 查询未被删除的
        if(StringUtil.isNotBlank(receiveStaffName)){
            criteria.put("receiveStaffName", StringUtil.trim(receiveStaffName));
        }
        String areaText = "";
        if(StringUtil.isNotBlank(area)){
            criteria.put("area", StringUtil.trim(area));
            if("1".equals(area)) {
                areaText = "大连";
            }else {
                areaText = "沈阳";
            }
        }
        if(StringUtil.isNotBlank(startDate)){
            criteria.put("startDate", startDate);
        }
        if(StringUtil.isNotBlank(endDate)){
            criteria.put("endDate", DateUtil.formatDate(DateUtil.parseDate(endDate, "yyyy-MM-dd"), "yyyy-MM-dd 23:59:59"));
        }
        
        List<VisitReportInfoForm> list = visitReportService.getPerforByCondition(criteria);
        
        try{
            @SuppressWarnings("deprecation")
            String filePath = request.getRealPath("resources/excel");
            ExportBean excelBean = new ExportBean();
            makeSalesmanStatementData(areaText, list, excelBean);
            excelBean.setExportMode(0);
            excelBean.setSourceFile(filePath + "\\salesmanStatement_excel.xls");
            new ExportExcelJxl(response, excelBean);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void makeSalesmanStatementData(String areaText, List<VisitReportInfoForm> list, ExportBean excelBean){
        Object[][] data = new Object[list.size()+1][19];
        int i = 1;
        data[0] = new Object[]{"接待","锁定个数","锁定单数","锁定业绩","成单个数","成单单数","成单业绩","重复个数","重复单数",
                                "重复业绩","说明会个数","说明会单数","说明会业绩","新客户个数","新客户单数","新客户业绩","合计个数","合计单数","合计业绩"};
        for(VisitReportInfoForm e : list){
            data[i][0] = (null == e.getReceiverStaffName() ? "" : e.getReceiverStaffName());
            data[i][1] = (null == e.getLockedCounts() ? "" : e.getLockedCounts());
            data[i][2] = (null == e.getLockedOrders() ? "" : e.getLockedOrders());
            data[i][3] = (null == e.getLockedAmounts() ? "" : e.getLockedAmounts());
            data[i][4] = (null == e.getFinishOrderCounts() ? "" : e.getFinishOrderCounts());
            data[i][5] = (null == e.getFinishOrders() ? "" : e.getFinishOrders());
            data[i][6] = (null == e.getFinishAmounts() ? "" : e.getFinishAmounts());
            data[i][7] = (null == e.getRepeatCounts() ? "" : e.getRepeatCounts());
            data[i][8] = (null == e.getRepeatOrders() ? "" : e.getRepeatOrders());
            data[i][9] = (null == e.getRepeatAmounts() ? "" : e.getRepeatAmounts());
            data[i][10] = (null == e.getRoadshowCounts() ? "" : e.getRoadshowCounts());
            data[i][11] = (null == e.getRoadshowOrders() ? "" : e.getRoadshowOrders());
            data[i][12] = (null == e.getRoadshowAmounts() ? "" : e.getRoadshowAmounts());
            data[i][13] = (null == e.getNewCounts() ? "" : e.getNewCounts());
            data[i][14] = (null == e.getNewOrders() ? "" : e.getNewOrders());
            data[i][15] = (null == e.getNewAmounts() ? "" : e.getNewAmounts());
            data[i][16] = (null == e.getTotalCounts() ? "" : e.getTotalCounts());
            data[i][17] = (null == e.getTotalOrders() ? "" : e.getTotalOrders());
            data[i][18] = (null == e.getTotalAmounts() ? "" : e.getTotalAmounts());
            i++;
        }
        excelBean.setData(data);
        excelBean.setExcelName(areaText + "展厅客服对接业绩");
        excelBean.setSheetName(areaText + "展厅客服对接业绩");
    }
    
    //------------------------------ 导出每日登门统计数据 ------------------------------
    
    /**
     * @Title: exportVisitEveryDay 
     * @Description: 导出每日登门统计数据
     * @param request
     * @param response
     * @throws IOException 
     * @return void
     * @author Xia ZhengWei
     * @date 2017年1月5日 下午9:57:46 
     * @version V1.0
     */
    @RequestMapping("/exportVisitEveryDay")
    public void exportVisitEveryDay(HttpServletRequest request, HttpServletResponse response) throws IOException{
        Criteria criteria = new Criteria();
        String customerArea = request.getParameter("customerArea");
        String customerName = request.getParameter("customerName");
        String customerType = request.getParameter("customerType");
        String exercise = request.getParameter("exercise");
        String receiverStaffName = request.getParameter("receiverStaffName");
        String customServiceName = request.getParameter("customServiceName");
        String visitDate = request.getParameter("visitDate");
        criteria.setOrderByClause("customer_name asc");
        // 查询未被删除的
        String areaText = "";
        if(StringUtil.isNotBlank(customerArea)){
            criteria.put("customerArea", StringUtil.trim(customerArea));
            if("1".equals(customerArea)) {
                areaText = "大连";
            }else {
                areaText = "沈阳";
            }
        }
        if(StringUtil.isNotBlank(customerName)){
            criteria.put("viewCustomerName", StringUtil.trim(customerName));
        }
        if(StringUtil.isNotBlank(customerType)){
            criteria.put("customerType", StringUtil.trim(customerType));
        }
        if(StringUtil.isNotBlank(exercise)){
            criteria.put("exercise", StringUtil.trim(exercise));
        }
        if(StringUtil.isNotBlank(receiverStaffName)){
            criteria.put("viewReceiverName", StringUtil.trim(receiverStaffName));
        }
        if(StringUtil.isNotBlank(customServiceName)){
            criteria.put("viewServiceName", StringUtil.trim(customServiceName));
        }
        if(StringUtil.isNotBlank(visitDate)){
            criteria.put("startVisitDate", visitDate);
            criteria.put("endVisitDate", DateUtil.formatDate(DateUtil.parseDate(visitDate, "yyyy-MM-dd"), "yyyy-MM-dd 23:59:59"));
        }
        List<VisitEverydayInfoForm> list = visitEveryDayService.selectByCondition(criteria);
        try{
            @SuppressWarnings("deprecation")
            String filePath = request.getRealPath("resources/excel");
            ExportBean excelBean = new ExportBean();
            makeVisitEveryDayData(areaText, list, excelBean);
            excelBean.setExportMode(0);
            excelBean.setSourceFile(filePath + "\\visitEveryDay_excel.xls");
            new ExportExcelJxl(response, excelBean);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void makeVisitEveryDayData(String areaText, List<VisitEverydayInfoForm> list, ExportBean excelBean){
        Object[][] data = new Object[list.size()+1][10];
        int i = 1;
        data[0] = new Object[]{"日期","客户","客服","接待","活动","客户类型","成单金额","藏品名称","出单/订单","情况"};
        for(VisitEverydayInfoForm e : list){
            data[i][0] = (null == e.getViewVisitDate() ? "" : e.getViewVisitDate());
            data[i][1] = (null == e.getCustomerName() ? "" : e.getCustomerName());
            data[i][2] = (null == e.getCustomServiceName() ? "" : e.getCustomServiceName());
            data[i][3] = (null == e.getReceiverStaffName() ? "" : e.getReceiverStaffName());
            data[i][4] = (null == e.getViewExercise() ? "" : e.getViewExercise());
            data[i][5] = (null == e.getViewCustomerType() ? "" : e.getViewCustomerType());
            data[i][6] = (null == e.getTransactionAmount() ? "" : e.getTransactionAmount());
            data[i][7] = (null == e.getGoodsName() ? "" : e.getGoodsName());
            data[i][8] = (null == e.getViewOutOrIndent() ? "" : e.getViewOutOrIndent());
            data[i][9] = (null == e.getRemark() ? "" : e.getRemark());
            i++;
        }
        excelBean.setData(data);
        excelBean.setExcelName(areaText + "每日登门统计");
        excelBean.setSheetName(areaText + "每日登门统计");
    }
    
    //------------------------------ 导出个人业绩排名 ------------------------------
    
    /**
     * @Title: exportIndividualRanking 
     * @Description: 导出个人业绩排名
     * @param request
     * @param response
     * @throws IOException 
     * @return void
     * @author Xia ZhengWei
     * @date 2017年1月5日 下午9:57:46 
     * @version V1.0
     */
    @RequestMapping("/exportIndividualRanking")
    public void exportIndividualRanking(HttpServletRequest request, HttpServletResponse response) throws IOException{
        Criteria criteria = new Criteria();
        String startReportDate = request.getParameter("startReportDate");
        String endReportDate = request.getParameter("endReportDate");
        String employeeName = request.getParameter("employeeName");
        String organiId = request.getParameter("organiId");
        
        if(StringUtil.isNotBlank(startReportDate)){
            criteria.put("startReportDate", startReportDate);
        }else {
            criteria.put("startReportDate", DateUtil.formatDate(DateUtil.getLastWeekMonday(new Date()), "yyyy-MM-dd"));
        }
        if(StringUtil.isNotBlank(endReportDate)){
            criteria.put("endReportDate", DateUtil.formatDate(DateUtil.parseDate(endReportDate, "yyyy-MM-dd"), "yyyy-MM-dd 23:59:59"));
        }else {
            criteria.put("endReportDate", DateUtil.formatDate(DateUtil.getLastWeekSunday(new Date()), "yyyy-MM-dd 23:59:59"));
        }
        if(StringUtil.isNotBlank(employeeName)){
            criteria.put("employeeName", employeeName);
        }
        if(StringUtil.isNotBlank(organiId)){
            criteria.put("organiId", organiId);
        }
        List<DeptPerforInfoForm> list = deptPerformanceService.getIndividualByCondition(criteria);
        try{
            @SuppressWarnings("deprecation")
            String filePath = request.getRealPath("resources/excel");
            ExportBean excelBean = new ExportBean();
            makeIndividualRankingData(list, excelBean);
            excelBean.setExportMode(0);
            excelBean.setSourceFile(filePath + "\\individualRanking_excel.xls");
            new ExportExcelJxl(response, excelBean);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void makeIndividualRankingData(List<DeptPerforInfoForm> list, ExportBean excelBean){
        Object[][] data = new Object[list.size()+1][7];
        int i = 1;
        data[0] = new Object[]{"序号","机构","部门","小组","人员","业绩","新客户数"};
        for(DeptPerforInfoForm e : list){
            data[i][0] = i;
            data[i][1] = (null == e.getOrganizationName() ? "" : e.getOrganizationName());
            data[i][2] = (null == e.getDepartmentName() ? "" : e.getDepartmentName());
            data[i][3] = (null == e.getGroupName() ? "" : e.getGroupName());
            data[i][4] = (null == e.getEmployeeName() ? "" : e.getEmployeeName());
            data[i][5] = (null == e.getPerformance() ? "" : e.getPerformance());
            data[i][6] = (null == e.getNewCustomerCounts() ? "" : e.getNewCustomerCounts());
            i++;
        }
        excelBean.setData(data);
        excelBean.setExcelName("个人业绩排名");
        excelBean.setSheetName("个人业绩排名");
    }
    
    //------------------------------ 导出当日刷卡定金明细 ------------------------------
    
    /**
     * @Title: exportCreditCard 
     * @Description: 导出当日刷卡定金明细Excel
     * @param request
     * @param response
     * @throws IOException 
     * @return void
     * @author Xia ZhengWei
     * @date 2017年1月7日 下午12:31:33 
     * @version V1.0
     */
    @RequestMapping("/exportCreditCard")
    public void exportCreditCard(HttpServletRequest request, HttpServletResponse response) throws IOException{
        Criteria criteria = new Criteria();
        String area = request.getParameter("area");
        String startDate = request.getParameter("startDate");
        String customerPayType = request.getParameter("customerPayType");
        // 查询未被删除的
        String areaText = "";
        if(StringUtil.isNotBlank(area)){
            criteria.put("area", area);
            if("1".equals(area)) {
                areaText = "大连";
            }else {
                areaText = "沈阳";
            }
        }
        if (StringUtil.isNotBlank(startDate)) {
            criteria.put("startDate", startDate);
            criteria.put("endDate",
                    DateUtil.formatDate(DateUtil.parseDate(startDate, "yyyy-MM-dd"), "yyyy-MM-dd 23:59:59"));
        } else {
            // 为空, 默认查询当天的数据
            criteria.put("startDate", DateUtil.formatDate(new Date(), "yyyy-MM-dd"));
            criteria.put("endDate", DateUtil.formatDate(new Date(), "yyyy-MM-dd 23:59:59"));
        }
        if (StringUtil.isNotBlank(customerPayType)) {
            criteria.put("customerPayType", customerPayType); 
        }
        criteria.setOrderByClause("p.payment_account_name");
        
        List<OrderFundSettlementForm> list = orderService.getCreditCardDepositDetail(criteria);
        try{
            @SuppressWarnings("deprecation")
            String filePath = request.getRealPath("resources/excel");
            ExportBean excelBean = new ExportBean();
            makeCreditCardData(areaText, list, excelBean);
            excelBean.setExportMode(0);
            excelBean.setSourceFile(filePath + "\\creditCard_excel.xls");
            new ExportExcelJxl(response, excelBean);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void makeCreditCardData(String area, List<OrderFundSettlementForm> list, ExportBean excelBean){
        Object[][] data = new Object[list.size()+1][7];
        int i = 1;
        data[0] = new Object[]{"序号","收款账户名称","支付类别","出单号","需要支付金额","支付金额","手续费"};
        for(OrderFundSettlementForm e : list){
            data[i][0] = i;
            data[i][1] = (null == e.getPaymentAccountName() ? "" : e.getPaymentAccountName());
            data[i][2] = (null == e.getCustomerPayType() ? "" : e.getCustomerPayType());
            data[i][3] = (null == e.getOrderNumber() ? "" : e.getOrderNumber());
            data[i][4] = (null == e.getPayAmount() ? "" : e.getPayAmount());
            data[i][5] = (null == e.getPayAmountActual() ? "" : e.getPayAmountActual());
            data[i][6] = (null == e.getPoundage() ? "" : e.getPoundage());
            i++;
        }
        excelBean.setData(data);
        excelBean.setExcelName(area + "当日定金");
        excelBean.setSheetName(area + "当日定金");
    }
    
    //------------------------------ 导出出库明细 ------------------------------
    
    /**
     * @Title: exportCreditCard 
     * @Description: 导出出库明细Excel
     * @param request
     * @param response
     * @throws IOException 
     * @return void
     * @author Xia ZhengWei
     * @date 2017年1月7日 下午12:31:33 
     * @version V1.0
     */
    @RequestMapping("/exportOutwarehouseDetail")
    public void exportOutwarehouseDetail(HttpServletRequest request, HttpServletResponse response) throws IOException{
        Criteria criteria = new Criteria();
        String orderNumber = request.getParameter("orderNumber");
        String goodsName = request.getParameter("goodsName");
        String area = request.getParameter("area");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        
        // 已支付
        criteria.put("financeFlag", "1");
        String areaText = "";
        if(StringUtil.isNotBlank(area)){
            criteria.put("area", area);
            if("1".equals(area)) {
                areaText = "大连";
            }else {
                areaText = "沈阳";
            }
        }
        if(StringUtil.isNotBlank(orderNumber)){
            criteria.put("orderNumber", orderNumber);
        }
        if(StringUtil.isNotBlank(goodsName)){
            criteria.put("goodsName", goodsName.trim());
        }
        if(StringUtil.isNotBlank(startDate)){
            criteria.put("startDate", startDate);
        }else {
            criteria.put("startDate", DateUtil.formatDate(DateUtil.getLastWeekMonday(new Date()), "yyyy-MM-dd"));
        }
        if(StringUtil.isNotBlank(endDate)){
            criteria.put("endDate", DateUtil.formatDate(DateUtil.parseDate(endDate, "yyyy-MM-dd"), "yyyy-MM-dd 23:59:59"));
        }else {
            criteria.put("endDate", DateUtil.formatDate(DateUtil.getLastWeekSunday(new Date()), "yyyy-MM-dd 23:59:59"));
        }
        
        List<StorehouseOperateInfoForm> list = storeHouseService.getOrganiPerformance(criteria);
        try{
            @SuppressWarnings("deprecation")
            String filePath = request.getRealPath("resources/excel");
            ExportBean excelBean = new ExportBean();
            makeOutwarehouseDetailData(areaText, list, excelBean);
            excelBean.setExportMode(0);
            excelBean.setSourceFile(filePath + "\\outwarehouseDetail_excel.xls");
            new ExportExcelJxl(response, excelBean);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void makeOutwarehouseDetailData(String area, List<StorehouseOperateInfoForm> list, ExportBean excelBean){
        Object[][] data = new Object[list.size()+1][7];
        int i = 1;
        data[0] = new Object[]{"出单号","藏品分类","藏品名称","藏品类型","藏品价格","数量","出库日期"};
        for(StorehouseOperateInfoForm e : list){
            data[i][0] = (null == e.getOrderNumber() ? "" : e.getOrderNumber());
            data[i][1] = (null == e.getGoodSortName() ? "" : e.getGoodSortName());
            data[i][2] = (null == e.getGoodName() ? "" : e.getGoodName());
            data[i][3] = (null == e.getViewTypeName() ? "" : e.getViewTypeName());
            data[i][4] = (null == e.getPrice() ? "" : e.getPrice());
            data[i][5] = (null == e.getAmount() ? "" : e.getAmount());
            data[i][6] = (null == e.getViewOperateDate() ? "" : e.getViewOperateDate());
            i++;
        }
        excelBean.setData(data);
        excelBean.setExcelName(area + "出库明细");
        excelBean.setSheetName(area + "出库明细");
    }
    
    //------------------------------ 导出赠品明细 ------------------------------
    
    /**
     * @Title: exportGiftDetail 
     * @Description: 导出赠品明细Excel
     * @param request
     * @param response
     * @throws IOException 
     * @return void
     * @author Xia ZhengWei
     * @date 2017年1月7日 下午12:31:33 
     * @version V1.0
     */
    @RequestMapping("/exportGiftDetail")
    public void exportGiftDetail(HttpServletRequest request, HttpServletResponse response) throws IOException{
        Criteria criteria = new Criteria();
        String area = request.getParameter("area");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        
        String areaText = "";
        if(StringUtil.isNotBlank(area)){
            criteria.put("area", area);
            if("1".equals(area)) {
                areaText = "大连";
            }else {
                areaText = "沈阳";
            }
        }
        if(StringUtil.isNotBlank(startDate)){
            criteria.put("startDate", startDate);
        }else {
            criteria.put("startDate", DateUtil.formatDate(new Date(), "yyyy-MM-dd"));
        }
        if(StringUtil.isNotBlank(endDate)){
            criteria.put("endDate", DateUtil.formatDate(DateUtil.parseDate(endDate, "yyyy-MM-dd"), "yyyy-MM-dd 23:59:59"));
        }else {
            criteria.put("startDate", DateUtil.formatDate(new Date(), "yyyy-MM-dd 23:59:59"));
        }
        
        List<OrderDetailInfoForm> list = orderDetailService.getGiftDetail(criteria);
        try{
            @SuppressWarnings("deprecation")
            String filePath = request.getRealPath("resources/excel");
            ExportBean excelBean = new ExportBean();
            makeGiftDetailData(areaText, list, excelBean);
            excelBean.setExportMode(0);
            excelBean.setSourceFile(filePath + "\\giftDetail_excel.xls");
            new ExportExcelJxl(response, excelBean);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void makeGiftDetailData(String area, List<OrderDetailInfoForm> list, ExportBean excelBean){
        Object[][] data = new Object[list.size()+1][4];
        int i = 1;
        data[0] = new Object[]{"序号","赠品名称","数量","VIP"};
        for(OrderDetailInfoForm e : list){
            data[i][0] = i;
            data[i][1] = (null == e.getGoodName() ? "" : e.getGoodName());
            data[i][2] = (null == e.getVisitGiftCounts() ? "" : e.getVisitGiftCounts());
            data[i][3] = (null == e.getVipGiftCounts() ? "" : e.getVipGiftCounts());
            i++;
        }
        excelBean.setData(data);
        excelBean.setExcelName(area + "赠品明细");
        excelBean.setSheetName(area + "赠品明细");
    }
}
