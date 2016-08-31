package com.portal.common.exception;

public class SystemException extends RuntimeException {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /** 异常携带的信息 */
    private Object object = null;
    /** 异常 */
    private Throwable original = null;
    /** 错误信息 */
    private String errorMsg = null;

    /**
     * 构造函数
     * @param e
     *            异常
     */
    public SystemException(Throwable e) {
        this.original = e;
    }
    
    /**
     * 构造函数
     * @param e
     *            异常
     */
    public SystemException(String msgId) {
        super(msgId);
        this.errorMsg = msgId;
    }

    /**
     * 构造函数
     * @param e
     *            异常
     * @param message
     *            信息
     */
    public SystemException(Throwable e, String msgId) {
        super(msgId);
        this.errorMsg = msgId;
        this.original = e;
    }

    /**
     * 构造函数
     * @param e
     *            异常
     */
    public SystemException(Throwable e, String msgId, Object object) {
        super(msgId);
        this.original = e;
        this.errorMsg = msgId;
        this.object = object;
    }

    /**
     * 构造函数
     * @param e
     *            异常
     */
    public SystemException(String msgId, Object object) {
        super(msgId);
        this.errorMsg = msgId;
        this.object = object;
    }
    /**
     * 取得 object
     * @return object Object
     */
    public Object getObject() {
        return object;
    }

    /**
     * 设定 object
     * @param object Object
     */
    public void setObject(Object object) {
        this.object = object;
    }

    /**
     * 取得异常对象
     * @return 异常
     */
    public Throwable getOriginal() {
        return original;
    }

    /**
     * 设定异常对象
     * @param original
     *            异常对象
     */
    protected void setOriginal(Throwable original) {
        this.original = original;
    }

    /**
     * 取得错误信息
     * @return 错误信息
     */
    public String getErrorMsg() {
        return errorMsg;
    }

    /**
     * 设定错误信息
     * @param errorMsg
     *            错误信息
     */
    protected void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
