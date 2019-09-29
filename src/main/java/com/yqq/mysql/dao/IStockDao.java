package com.yqq.mysql.dao;

import com.yqq.mysql.base.IDBClose;
import com.yqq.pojo.Stock;
import java.util.List;

/**
 * Created by yqq on 2019/9/21.
 */
public interface IStockDao extends IDBClose {

    /**
     * 批量插入
     * @param list
     */
    void insertBatch(List<Stock> list);

    /**
     * 清空表
     */
    void truncate();

    /**
     * find all stock
     */




}
