package com.yqq.mysql.base.conn;

/**
 * 数据源枚举,定义所有的数据源
 *
 * @author Live.InPast
 * @date 2019-08-01
 */
public enum JDBCType {

    /**
     * 默认数据源
     */
    DEFAULT(0, "默认数据源zsy_wh");

    JDBCType(int value, String name) {
        this.value = value;
        this.name = name;
    }

    /**
     * 值
     */
    private int value;

    /**
     * 名称
     */
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
}


