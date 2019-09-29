package com.yqq.mysql.base.conn;

import java.sql.Connection;

/**
 * 获取数据库连接对象管理
 *
 * @author Live.InPast
 * @date 2019-08-01
 */
public interface JDBCManager {

    /**
     * 获取数据库连接池
     *
     * @return
     */
    Connection getConnection();


}
