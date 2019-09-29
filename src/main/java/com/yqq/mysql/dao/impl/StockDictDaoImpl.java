package com.yqq.mysql.dao.impl;

import com.yqq.mysql.base.BaseDao;
import com.yqq.mysql.dao.IStockDictDao;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by yqq on 2019/9/27.
 */
public class StockDictDaoImpl extends BaseDao implements IStockDictDao {

    @Override
    public List<String> findAllStockDict() throws SQLException {

        String sql = "select * from stock_dict";

        List<String> list = getSQLRunner().executeSelectAllSql(sql);

        return list;
    }
}
