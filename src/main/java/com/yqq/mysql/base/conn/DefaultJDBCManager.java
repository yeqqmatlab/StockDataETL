package com.yqq.mysql.base.conn;


import com.yqq.constants.Constants;
import com.yqq.task.com.yqq.config.ConfigurationManager;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * 默认数据库连接对象
 *
 * @author Live.InPast
 * @date 2019-08-01
 */
public class DefaultJDBCManager implements JDBCManager {

    @Override
    public Connection getConnection() {
        String driver = Constants.MYSQL_DRIVER_URL;
        String url = ConfigurationManager.getProperty(Constants.MYSQL_WHDB_JDBC_URL);
        String user = ConfigurationManager.getProperty(Constants.MYSQL_WHDB_USERNAME);
        String password = ConfigurationManager.getProperty(Constants.MYSQL_WHDB_PASSWORD);

        try {
            Class.forName(driver);
            return DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }


}
