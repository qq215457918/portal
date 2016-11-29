package com.portal.bean;

public enum OrderStatus {
    UNPAID("未支付", 0), PAID("已支付", 1), LIBRARY("已出库", 2), EXCHAGE("文交所已审核", 3), DONE("已完成", 4), PENDING("待审批",
            5);
    private String name;
    private int index;

    private OrderStatus(String name, int index) {
        this.name = name;
        this.index = index;
    }

    // 获取name
    public static String getName(int index) {
        for (OrderStatus t : OrderStatus.values()) {
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
