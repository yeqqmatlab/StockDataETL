package com.yqq.mysql.base;

import com.yqq.mysql.base.conn.JDBCRegister;
import com.yqq.mysql.base.conn.JDBCType;
import java.sql.Connection;

/**
 * 基础DAO,配置所有获取连接的公共类
 *
 * @author Live.InPast
 * @date 2019-08-01
 */
public class BaseDao {

    /**
     * 操作数据库
     */
    private SQLRunner sqlRunner;

    /**
     * 父类构造函数
     */
    public BaseDao() {
        Connection connection = JDBCRegister.getJdbcConnection(JDBCType.DEFAULT).getConnection();
        sqlRunner = new SQLRunner(connection, JDBCType.DEFAULT);
    }


    /**
     * 切换JDBC连接对象
     *
     * @param jdbcType jdbc
     */
    public void switchSqlRunner(JDBCType jdbcType) {
        //切换数据源之前清空原有数据源
        if (sqlRunner != null) {
            if (jdbcType == sqlRunner.getJDBCType()) {
                System.out.println("The sql runner that you want to switch is the same as original.");
            } else {
                sqlRunner.close();
                sqlRunner = null;
            }
        } else {
            Connection connection = JDBCRegister.getJdbcConnection(jdbcType).getConnection();
            sqlRunner = new SQLRunner(connection, jdbcType);
        }
    }

    /**
     * 获取JDBC操作对象
     *
     * @return
     */
    public SQLRunner getSQLRunner() {
        if (sqlRunner == null) {
            throw new RuntimeException("SQLRunner is not initial,please init it first.");
        }
        return sqlRunner;
    }

    /**
     * 关闭JDBC操作对象
     */
    public void close() {
        if (sqlRunner != null) {
            sqlRunner.close();
        }
    }

}
