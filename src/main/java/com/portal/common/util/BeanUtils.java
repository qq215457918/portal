package com.portal.common.util;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

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
     * @return Object
     * @author Xia ZhengWei
     * @date 2017年1月9日 下午10:25:57 
     * @version V1.0
     */
    public static Object getLoginUser() {
        Subject currentUser = SecurityUtils.getSubject();
        Session session = currentUser.getSession();
        return session.getAttribute("userInfo");
    }
}
