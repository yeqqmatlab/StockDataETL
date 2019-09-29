package com.yqq.mysql.dao.impl;

import com.yqq.mysql.base.BaseDao;
import com.yqq.mysql.dao.IStockDao;
import com.yqq.pojo.Stock;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yqq on 2019/9/21.
 */
public class StockDaoImpl extends BaseDao implements IStockDao {

    @Override
    public void insertBatch(List<Stock> list) {

        StringBuffer sql = new StringBuffer();
        sql.append(" INSERT INTO stock_data_tab (  ");
        sql.append("     stock_code, ");
        sql.append("     stock_name, ");
        sql.append("     today_start_price, ");
        sql.append("     yesterday_end_price, ");
        sql.append("     now_price, ");
        sql.append("     today_max_price, ");
        sql.append("     today_min_price, ");
        sql.append("     buy_price, ");
        sql.append("     sell_price, ");
        sql.append("     stock_num, ");
        sql.append("     total_money, ");
        sql.append("     buy_one_num, ");
        sql.append("     buy_one_price, ");
        sql.append("     buy_two_num, ");
        sql.append("     buy_two_price, ");
        sql.append("     buy_three_num, ");
        sql.append("     buy_three_price, ");
        sql.append("     buy_four_num, ");
        sql.append("     buy_four_price, ");
        sql.append("     buy_five_num, ");
        sql.append("     buy_five_price, ");
        sql.append("     sell_one_num, ");
        sql.append("     sell_one_price, ");
        sql.append("     sell_two_num, ");
        sql.append("     sell_two_price, ");
        sql.append("     sell_three_num, ");
        sql.append("     sell_three_price, ");
        sql.append("     sell_four_num, ");
        sql.append("     sell_four_price, ");
        sql.append("     sell_five_num, ");
        sql.append("     sell_five_price, ");
        sql.append("     stock_date, ");
        sql.append("     stock_time, ");
        sql.append("     back_up  ");
        sql.append("     ) ");
        sql.append(" VALUES ");
        sql.append("     ( ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");

        List<Object[]> paramsList = new ArrayList<>();

        for (Stock stock : list) {
            Object[] params = new Object[34];
            params[0] = stock.getStockCode();
            params[1] = stock.getStockName();
            params[2] = stock.getTodayStartPrice();
            params[3] = stock.getYesterdayEndPrice();
            params[4] = stock.getNowPrice();
            params[5] = stock.getTodayMaxPrice();
            params[6] = stock.getTodayMinPrice();
            params[7] = stock.getBuyPrice();
            params[8] = stock.getSellPrice();
            params[9] = stock.getStockNum();
            params[10] = stock.getTotalMoney();
            params[11] = stock.getBuyOneNum();
            params[12] = stock.getBuyOnePrice();
            params[13] = stock.getBuyTwoNum();
            params[14] = stock.getBuyTwoPrice();
            params[15] = stock.getBuyThreeNum();
            params[16] = stock.getBuyThreePrice();
            params[17] = stock.getBuyFourNum();
            params[18] = stock.getBuyFourPrice();
            params[19] = stock.getBuyFiveNum();
            params[20] = stock.getBuyFivePrice();
            params[21] = stock.getSellOneNum();
            params[22] = stock.getSellOnePrice();
            params[23] = stock.getSellTwoNum();
            params[24] = stock.getSellTwoPrice();
            params[25] = stock.getSellThreeNum();
            params[26] = stock.getSellThreePrice();
            params[27] = stock.getSellFourNum();
            params[28] = stock.getSellFourPrice();
            params[29] = stock.getSellFiveNum();
            params[30] = stock.getSellFivePrice();
            params[31] = stock.getStockDate();
            params[32] = stock.getStockTime();
            params[33] = stock.getBackup();
            paramsList.add(params);
        }
        getSQLRunner().executeBatch(sql.toString(), paramsList);
    }

    @Override
    public void truncate() {

    }
}
