package com.portal.bean;

public enum CustomerType {
    BLANK("空白客户", 0), REPEAT("重复登门", 1), SEMINAR("说明会", 2), ORDER("成单", 3), LOCK("锁定", 4), REFERRAL("转介绍", 5);
    private String name;
    private int index;

    private CustomerType(String name, int index) {
        this.name = name;
        this.index = index;
    }

    // 获取name
    public static String getName(int index) {
        for (CustomerType t : CustomerType.values()) {
            if (t.getIndex() == index) {
                return t.name;
            }
        }
        return null;
    }

    // get set 方法
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

}
