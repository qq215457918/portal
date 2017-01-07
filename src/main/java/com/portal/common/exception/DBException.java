package com.portal.common.exception;

/**
 * @ClassName: DBException 
 * @Description: 自定义数据库异常类
 * @author Xia ZhengWei
 * @date 2016年10月25日 下午4:16:27
 */
public class DBException extends Exception {

    private static final long serialVersionUID = 1L;
    
    /**
     * 构造函数
     * @param msg 错误信息
     */
    public DBException(String msg) {
        super(msg);
    }
    
}
