package com.portal.common.util;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName: BeanUtils 
 * @Description: 对象工具类
 * @author Xia ZhengWei
 * @date 2017年1月9日 下午10:26:18
 */
public class BeanUtils {

    /**
     * @Title: getLoginUser 
     * @Description: 获取当前登录账号信息
     * @param request
     * @return Object
     * @author Xia ZhengWei
     * @date 2017年1月9日 下午10:25:57 
     * @version V1.0
     */
    public static Object getLoginUser(HttpServletRequest request) {
        return request.getSession().getAttribute("loginInfo");
    }
}
