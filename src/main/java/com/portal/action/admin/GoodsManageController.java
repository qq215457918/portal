package com.portal.action.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.portal.bean.Criteria;
import com.portal.bean.EmployeeInfo;
import com.portal.bean.GoodsInfo;
import com.portal.bean.GoodsSort;
import com.portal.bean.result.GoodsInfoForm;
import com.portal.common.util.ExportBean;
import com.portal.common.util.ExportExcelJxl;
import com.portal.common.util.JsonUtils;
import com.portal.common.util.StringUtil;
import com.portal.common.util.WebUtils;
import com.portal.service.GoodsInfoService;
import com.portal.service.GoodsSortService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @ClassName: GoodsManageController 
 * @Description: 后台商品管理
 * @author Xia ZhengWei
 * @date 2016年12月21日 上午1:15:11
 */
@Controller
@RequestMapping("admin/goodsManage")
public class GoodsManageController {
    
    @Autowired
    private GoodsInfoService goodsService;
    
    @Autowired
    private GoodsSortService goodsSortService;
    
    /**
     * @Title: toGoodsMagene 
     * @Description: 进入后台商品管理页面
     * @param request
     * @param response
     * @return String
     * @author Xia ZhengWei
     * @date 2016年12月21日 上午1:16:53 
     * @version V1.0
     */
    @RequiresPermissions("goodsMagene:view")
    @RequestMapping("/toGoodsMagene")
    public String toGoodsMagene(HttpServletRequest request, HttpServletResponse response) {
        // 保存活动导航标识
        WebUtils.setAttributeToSession(request);
        // 加载商品种类(大类-纸币/邮票/贵金属/赠品/兑换)
        List<GoodsSort> goodsBigSortList = goodsSortService.getGoodsBigSort();
        request.setAttribute("goodsBigSortList", goodsBigSortList);
        return "admin/goods_manage_list";
    }
    
    /**
     * @Title: ajaxGoodsSmallSorts 
     * @Description: 根据大类ID获取小类
     * @param request
     * @param response 
     * @return void
     * @author Xia ZhengWei
     * @date 2016年12月26日 下午9:56:21 
     * @version V1.0
     */
    @RequestMapping("/ajaxGoodsSmallSorts")
    public void ajaxGoodsSmallSorts(HttpServletRequest request, HttpServletResponse response) {
        String bigSortId = request.getParameter("bigSortId");
        Criteria criteria = new Criteria();
        List<GoodsSort> groupList = new ArrayList<GoodsSort>();
        if(StringUtil.isNotBlank(bigSortId)) {
            criteria.put("parentsId", bigSortId);
            criteria.setOrderByClause("CONVERT(name USING gbk) asc");
            groupList = goodsSortService.selectByExample(criteria);
        }
        // 向前端输出
        JsonUtils.outJsonString(JSONArray.fromObject(groupList).toString(), response);
    }
    
    /**
     * @Title: ajaxGoodsData 
     * @Description: 异步获取商品信息
     * @param request
     * @param response 
     * @return void
     * @author Xia ZhengWei
     * @date 2016年12月21日 下午10:29:57 
     * @version V1.0
     */
    @RequestMapping("/ajaxGoodsData")
    public void ajaxGoodsData(HttpServletRequest request, HttpServletResponse response) {
        // 公共查询条件类
        Criteria criteria = new Criteria();
        // 请求开始页
        int currentPage = StringUtil.getIntValue(request.getParameter("iDisplayStart"));
        // 每页显示几条
        int perpage = StringUtil.getIntValue(request.getParameter("iDisplayLength"));
        String sEcho = request.getParameter("sEcho");
        
        // 参数
        String name = request.getParameter("name");
        String code = request.getParameter("code");
        String bigSortId = request.getParameter("bigSortId");
        String sortId = request.getParameter("sortId");
        String type = request.getParameter("type");
        String repurchaseFlag = request.getParameter("repurchaseFlag");
        
        // 分页参数
        criteria.clear();
        criteria.put("deleteFlag", "0");
        criteria.setMysqlOffset(currentPage);
        criteria.setMysqlLength(perpage);
        criteria.setOrderByClause("CONVERT(g.`name` USING gbk) asc");
        
        // 非空判断
        if(StringUtil.isNotBlank(name)) {
            criteria.put("viewName", name.trim());
        }
        if(StringUtil.isNotBlank(code)) {
            criteria.put("viewCode", code.trim());
        }
        if(StringUtil.isNotBlank(bigSortId)) {
            criteria.put("bigSortId", bigSortId);
        }
        if(StringUtil.isNotBlank(sortId)) {
            criteria.put("viewSortId", sortId);
        }
        if(StringUtil.isNotBlank(type)) {
            criteria.put("viewType", type);
        }
        if(StringUtil.isNotBlank(repurchaseFlag)) {
            criteria.put("viewRepurchaseFlag", repurchaseFlag);
        }
        
        // 异步获取数据
        JSONObject results = goodsService.ajaxGoodsData(criteria, sEcho);
        // 向前端输出
        JsonUtils.outJsonString(results.toString(), response);
    }
    
    /**
     * @Title: toCompileGoodsInfo 
     * @Description: 进入编辑商品信息页面
     * @param request
     * @param response
     * @return String
     * @author Xia ZhengWei
     * @date 2016年12月28日 下午10:16:39 
     * @version V1.0
     */
    @RequestMapping("/toCompileGoodsInfo")
    public String toCompileGoodsInfo(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        // 加载商品种类(大类-纸币/邮票/贵金属/赠品/兑换)
        List<GoodsSort> goodsBigSortList = goodsSortService.getGoodsBigSort();
        request.setAttribute("goodsBigSortList", goodsBigSortList);
        if(StringUtil.isNotBlank(id)) {
            // 根据ID获取商品信息
            GoodsInfo goodsInfo = goodsService.selectByPrimaryKey(id);
            // 如果信息不为空, 则回显对应的种类
            if(goodsInfo != null & "0".equals(goodsInfo.getDeleteFlag())) {
                // 创建页面展示商品对象
                GoodsInfoForm goodsForm = new GoodsInfoForm();
                // 复制属性
                BeanUtils.copyProperties(goodsInfo, goodsForm);
                // 根据商品的种类ID获取种类对象信息
                GoodsSort goodsSort = goodsSortService.selectByPrimaryKey(goodsInfo.getSortId());
                // 如果种类不为空, 则商品对应的种类为小类; 不为空则对应种类为大类
                // 根据对应种类的大类ID获取所有小类
                Criteria criteria = new Criteria();
                if(goodsSort != null) {
                    goodsForm.setBigSortId(goodsSort.getParentsId());
                    criteria.put("parentsId", goodsSort.getParentsId());
                }else {
                    goodsForm.setBigSortId(goodsInfo.getSortId());
                    criteria.put("parentsId", goodsInfo.getSortId());
                }
                List<GoodsSort> sortList = goodsSortService.selectByExample(criteria);
                request.setAttribute("sortList", sortList);
                // 向域中存储对象信息
                request.setAttribute("goodsInfo", goodsForm);
                return "admin/compile_goods_info";
            }else {
                return "common/404";
            }
        }else {
            return "admin/compile_goods_info";
        }
    }
    
    /**
     * @Title: saveGoodsInfo 
     * @Description: 保存商品信息
     * @param goodsInfo
     * @param request
     * @param response 
     * @return void
     * @author Xia ZhengWei
     * @date 2016年12月28日 下午10:24:42 
     * @version V1.0
     */
    @RequestMapping("/saveGoodsInfo")
    public void saveGoodsInfo(GoodsInfoForm goodsInfoForm, HttpServletRequest request, HttpServletResponse response) {
        JSONObject results = null;
        if(goodsInfoForm != null) {
            EmployeeInfo employee = (EmployeeInfo) request.getSession().getAttribute("userInfo");
            if(employee != null) {
                results = goodsService.saveGoodsInfo(goodsInfoForm, employee, results);
            }else {
                results = JsonUtils.setError();
                results.put("text", "操作失败, 请重新登录后重试");
            }
            
        }else {
            results = JsonUtils.setError();
            results.put("text", "操作失败, 请刷新后重试");
        }
        JsonUtils.outJsonString(results.toString(), response);
    }
    
    @RequestMapping("/deleteGoodsInfo")
    public void deleteGoodsInfo(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        JSONObject results = JsonUtils.setSuccess();
        if(StringUtil.isNotBlank(id)) {
            results = goodsService.deleteGoodsInfo(id, results);
        }else {
            results = JsonUtils.setError();
            results.put("text", "删除数据失败, 请刷新后重试");
        }
        // 向前端输出
        JsonUtils.outJsonString(results.toString(), response);
    }
    
    /**
     * @Title: exportGoods 
     * @Description: 后台导出商品Excel
     * @param request
     * @param response
     * @throws IOException 
     * @return void
     * @author Xia ZhengWei
     * @date 2017年1月4日 下午10:37:24 
     * @version V1.0
     */
    @RequestMapping("/exportGoods")
    public void exportGoods(HttpServletRequest request, HttpServletResponse response) throws IOException{
        Criteria criteria = new Criteria();
        // 参数
        String name = request.getParameter("name");
        String code = request.getParameter("code");
        String bigSortId = request.getParameter("bigSortId");
        String sortId = request.getParameter("sortId");
        String type = request.getParameter("type");
        String repurchaseFlag = request.getParameter("repurchaseFlag");
        
        criteria.put("deleteFlag", "0");
        criteria.setOrderByClause("CONVERT(g.`name` USING gbk) asc");
        // 非空判断
        if(StringUtil.isNotBlank(name)) {
            criteria.put("viewName", name.trim());
        }
        if(StringUtil.isNotBlank(code)) {
            criteria.put("viewCode", code.trim());
        }
        if(StringUtil.isNotBlank(bigSortId)) {
            criteria.put("bigSortId", bigSortId);
        }
        if(StringUtil.isNotBlank(sortId)) {
            criteria.put("viewSortId", sortId);
        }
        if(StringUtil.isNotBlank(type)) {
            criteria.put("viewType", type);
        }
        if(StringUtil.isNotBlank(repurchaseFlag)) {
            criteria.put("viewRepurchaseFlag", repurchaseFlag);
        }
        
        // 获取数据集
        List<GoodsInfoForm> list = goodsService.selectByConditions(criteria);
        
        try{
            @SuppressWarnings("deprecation")
            String filePath = request.getRealPath("resources/excel");
            ExportBean excelBean = new ExportBean();
            makeData(list, excelBean);
            excelBean.setExportMode(0);
            excelBean.setSourceFile(filePath + "\\employee_excel.xls");
            new ExportExcelJxl(response, excelBean);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void makeData(List<GoodsInfoForm> list, ExportBean excelBean){
        Object[][] data = new Object[list.size()+1][11];
        int i = 1;
        data[0] = new Object[]{"序号","藏品名称","藏品序号","种类","分类","金额","数量","是否可托管","回购标识","回购开始日期","回购结束日期"};
        for(GoodsInfoForm g : list){
            data[i][0] = i;
            data[i][1] = (null == g.getName() ? "" : g.getName());
            data[i][2] = (null == g.getCode() ? "" : g.getCode());
            data[i][3] = (null == g.getSortName() ? "" : g.getSortName());
            data[i][4] = (null == g.getViewType() ? "" : g.getViewType());
            data[i][5] = (null == g.getPrice() ? "" : g.getPrice());
            data[i][6] = (null == g.getAmount() ? "" : g.getAmount());
            data[i][7] = (null == g.getViewTrusteeshipFlag() ? "" : g.getViewTrusteeshipFlag());
            data[i][8] = (null == g.getViewRepurchaseFlag() ? "" : g.getViewRepurchaseFlag());
            data[i][9] = (null == g.getViewRepurchaseStarttime() ? "" : g.getViewRepurchaseStarttime());
            data[i][10] = (null == g.getViewRepurchaseEndtime() ? "" : g.getViewRepurchaseEndtime());
            i++;
        }
        excelBean.setData(data);
        excelBean.setExcelName("藏品信息");
        excelBean.setSheetName("藏品信息");
    }
    
}
