package com.yqq.mysql.base.conn;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 将所有的mysql数据连接都注册到这个类
 * 第一步实现ISQLConn接口
 * 第二步在JDBCType枚举中新增数据源枚举
 * 第三部register(JDBCType.XXX, new XXXXConnection());
 *
 * @author Live.InPast
 * @date 2019-08-01
 */
public class JDBCRegister {

    private static final Map<JDBCType, JDBCManager> JDBC_CONN_MAP = new ConcurrentHashMap<>();

    static {
        //默认数据源
        register(JDBCType.DEFAULT, new DefaultJDBCManager());
        //注册其他数据源...
    }

    private JDBCRegister() {
        //私有构造函数,不允许new
    }

    /**
     * 注册数据库
     *
     * @param jdbcType   数据源枚举
     * @param connection 接口
     */
    private static void register(JDBCType jdbcType, JDBCManager connection) {
        JDBC_CONN_MAP.put(jdbcType, connection);
    }

    /**
     * 获取表单处理对象
     *
     * @param conn
     * @return
     */
    public static JDBCManager getJdbcConnection(JDBCType conn) {
        JDBCManager manager = JDBC_CONN_MAP.get(conn);
        return Optional.ofNullable(manager)
                .orElseThrow(() -> new RuntimeException(conn.getName() + " jdbc connection handle not implement."));
    }

}
