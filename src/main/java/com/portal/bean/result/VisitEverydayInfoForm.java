package com.portal.bean.result;

import com.portal.bean.VisitEverydayInfo;
import com.portal.common.util.DateUtil;
import com.portal.common.util.StringUtil;

public class VisitEverydayInfoForm extends VisitEverydayInfo {

    private static final long serialVersionUID = 1L;
    
    /**
     * 页面展示登门日期
     */
    private String viewVisitDate;
    
    /**
     * 页面展示活动：0--无  1--HZ  2--4DS  3--QB  4--回款  5--DS  6--LY  7--LY+HZ  8--XY  9--HZ+38国家  10--3B
     */
    private String viewExercise;
    
    /**
     * 页面展示客户分类: 0--空白客户  1--重复登门  2--说明会  3--成单  4--锁定  5--转介绍
     */
    private String viewCustomerType;
    
    /**
     * 页面展示出单或订单：1--出单  2--订单
     */
    private String viewOutOrIndent;
    
    /**
     * @Title: getViewVisitDate 
     * @Description: 页面展示登门日期
     * @return String
     * @throws
     */
    public String getViewVisitDate() {
        if(!StringUtil.isNull(super.getVisitDate())) {
            viewVisitDate = DateUtil.formatDate(super.getVisitDate(), "yyyy-MM-dd");
        }
        return viewVisitDate;
    }
    public void setViewVisitDate(String viewVisitDate) {
        this.viewVisitDate = viewVisitDate;
    }
    
    /**
     * @Title: getViewCustomerType 
     * @Description: 页面展示客户类型
     * @return String
     * @throws
     */
    public String getViewCustomerType() {
        if(StringUtil.isNotBlank(super.getCustomerType())) {
            if("0".equals(super.getCustomerType())) {
                viewCustomerType = "空白";
            }else if("1".equals(super.getCustomerType())) {
                viewCustomerType = "重复";
            }else if("2".equals(super.getCustomerType())) {
                viewCustomerType = "说明会";
            }else if("3".equals(super.getCustomerType())) {
                viewCustomerType = "成单";
            }else if("4".equals(super.getCustomerType())) {
                viewCustomerType = "锁定";
            }else if("5".equals(super.getCustomerType())) {
                viewCustomerType = "转介绍";
            }
        }
        return viewCustomerType;
    }
    public void setViewCustomerType(String viewCustomerType) {
        this.viewCustomerType = viewCustomerType;
    }
    public void setViewExercise(String viewExercise) {
        this.viewExercise = viewExercise;
    }
    
    /**
     * @Title: getViewExercise 
     * @Description: 页面展示活动
     * @return String
     * @throws
     */
    public String getViewExercise() {
        if(StringUtil.isNotBlank(super.getExercise())) {
            if("0".equals(super.getExercise())) {
                viewExercise = "无";
            }else if("1".equals(super.getExercise())) {
                viewExercise = "HZ";
            }else if("2".equals(super.getExercise())) {
                viewExercise = "4DS";
            }else if("3".equals(super.getExercise())) {
                viewExercise = "QB";
            }else if("4".equals(super.getExercise())) {
                viewExercise = "回款";
            }else if("5".equals(super.getExercise())) {
                viewExercise = "DS";
            }else if("6".equals(super.getExercise())) {
                viewExercise = "LY";
            }else if("7".equals(super.getExercise())) {
                viewExercise = "LY+HZ";
            }else if("8".equals(super.getExercise())) {
                viewExercise = "XY";
            }else if("9".equals(super.getExercise())) {
                viewExercise = "HZ+38国家";
            }else if("10".equals(super.getExercise())) {
                viewExercise = "3B";
            }
        }
        return viewExercise;
    }
    public String getViewOutOrIndent() {
        if(StringUtil.isNotBlank(super.getOutOrIndent())) {
            if("1".equals(super.getOutOrIndent())) {
                viewOutOrIndent = "出单";
            }else{
                viewOutOrIndent = "订单";
            }
        }
        return viewOutOrIndent;
    }
    public void setViewOutOrIndent(String viewOutOrIndent) {
        this.viewOutOrIndent = viewOutOrIndent;
    }
    
}