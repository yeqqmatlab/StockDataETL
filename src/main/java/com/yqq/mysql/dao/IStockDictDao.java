package com.yqq.mysql.dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by yqq on 2019/9/27.
 */
public interface IStockDictDao {

    List<String> findAllStockDict() throws SQLException;

}
