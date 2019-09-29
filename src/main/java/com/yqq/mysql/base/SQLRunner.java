package com.yqq.mysql.base;


import com.yqq.mysql.base.conn.JDBCType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * JDBC辅助组件
 *
 * @author Live.InPast
 * @date 2019-07-31
 */
public class SQLRunner {

    /**
     * 数据库连接池
     */
    private Connection connection;

    /**
     * 数据源类型
     */
    private JDBCType JDBCType;

    /**
     * 默认10000条提交一次
     */
    private static final int DEFAULT_BATCH_SIZE = 10000;

    /**
     * 第三步：实现单例的过程中，创建唯一的数据库连接池
     * <p>
     * 私有化构造方法
     * <p>
     * JDBCHelper在整个程序运行声明周期中，只会创建一次实例
     * 在这一次创建实例的过程中，就会调用JDBCHelper()构造方法
     * 此时，就可以在构造方法中，去创建自己唯一的一个数据库连接池
     */
    public SQLRunner(Connection connection, JDBCType JDBCType) {
        this.connection = connection;
        this.JDBCType = JDBCType;
    }

    /**
     * 执行sql
     *
     * @param sql
     */
    public void executeSql(String sql) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param sql
     */
    public List<String> executeSelectAllSql(String sql) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet rs = preparedStatement.executeQuery();
        List<String> list = new ArrayList<>();
        while (rs.next()){
            String stock_code = rs.getString("stock_code");
            list.add(stock_code);
        }
        return list;
    }

    /**
     * 批量执行SQL语句
     *
     * @param sql        sql语句
     * @param paramsList 参数
     */
    public void executeBatch(String sql, List<Object[]> paramsList) {
        executeBatch(sql, paramsList, DEFAULT_BATCH_SIZE);
    }


    /**
     * 批量执行SQL语句
     *
     * @param sql        sql语句
     * @param paramsList 参数
     * @param batchSize  批量提交阀值
     */
    public void executeBatch(String sql, List<Object[]> paramsList, int batchSize) {
        if (batchSize <= 0) {
            batchSize = DEFAULT_BATCH_SIZE;
        }
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            // 第一步：使用Connection对象，取消自动提交
            connection.setAutoCommit(false);

            // 第二步：使用PreparedStatement.addBatch()方法加入批量的SQL参数
            if (paramsList != null && paramsList.size() > 0) {
                for (int i = 0; i < paramsList.size(); i++) {
                    Object[] objects = paramsList.get(i);
                    for (int j = 0; j < objects.length; j++) {
                        preparedStatement.setObject(j + 1, objects[j]);
                    }
                    preparedStatement.addBatch();
                    if (i % batchSize == 0) {
                        preparedStatement.executeBatch();
                        preparedStatement.clearBatch();
                    }
                }
            }

            //将剩余的批次提交
            preparedStatement.executeBatch();

            // 最后一步：使用Connection对象，提交批量的SQL语句
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭连接
     */
    public void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取连接类型
     *
     * @return
     */
    public JDBCType getJDBCType() {
        return JDBCType;
    }
}
