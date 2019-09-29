package com.yqq.task;

import com.yqq.mysql.dao.IStockDao;
import com.yqq.mysql.dao.IStockDictDao;
import com.yqq.mysql.dao.impl.StockDaoImpl;
import com.yqq.mysql.dao.impl.StockDictDaoImpl;
import com.yqq.pojo.Stock;
import org.apache.commons.httpclient.HttpException;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import static com.yqq.http.HttpClientHelper.sendPost;

/**
 * Created by yqq on 2019/9/28.
 */
public class WriterDataToMysql {

    public static void main(String[] args) throws SQLException, HttpException, IOException, InterruptedException {

        IStockDictDao stockDictDao = new StockDictDaoImpl();
        List<String> stockCodeList = stockDictDao.findAllStockDict();
        //匹配上证 深证
        List<String> list = new ArrayList<>();
        list.add("sh000001");
        list.add("sz399001");
        for (String vo : stockCodeList) {
            if(vo.substring(0,2).equals("60")){
                String sh_code = "sh".concat(vo);
                list.add(sh_code);
            }else if(vo.substring(0,2).equals("00") || vo.substring(0,3).equals("300")){
                String sz_code = "sz".concat(vo);
                list.add(sz_code);
            }
        }
        //批次数
        int count = 50;
        ArrayList<String> batchList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            batchList.add(list.get(i));
            if (count == batchList.size() || i == list.size() -1) {
                String batchCode = batchList.stream().collect(Collectors.joining(","));
                String url = "http://hq.sinajs.cn/list="+batchCode;
                String responseData = sendPost(url);
                String[] stockArr = responseData.split("[;]");
                List<Stock> stockList = getStockData(stockArr);
                IStockDao stockDao = new StockDaoImpl();
                stockDao.insertBatch(stockList);
                stockDao.close();
                Thread.sleep(100);
                batchList.clear();
            }
        }
    }

    private static List<Stock> getStockData(String[] stockArr) {
        List<Stock> stockList = new ArrayList<>();
        for (int i = 0; i < stockArr.length -1; i++) {
            Stock stock = new Stock();
            String[] arr = stockArr[i].split("[=]");
            String stockNameStr = arr[0];
            int length = stockNameStr.length();
            String code = stockNameStr.substring(length-8,length);
            stock.setStockCode(code);
            String stockData = arr[1];
            int length2 = stockData.length();
            String substring = stockData.substring(1, length2 - 1);
            String[] split = substring.split("[,]");
            stock.setStockName(split[0]);
            stock.setTodayStartPrice(new BigDecimal(split[1]));
            stock.setYesterdayEndPrice(new BigDecimal(split[2]));
            stock.setNowPrice(new BigDecimal(split[3]));
            stock.setTodayMaxPrice(new BigDecimal(split[4]));
            stock.setTodayMinPrice(new BigDecimal(split[5]));
            stock.setBuyPrice(new BigDecimal(split[6]));
            stock.setSellPrice(new BigDecimal(split[7]));
            stock.setStockNum(Long.valueOf(split[8]));
            stock.setTotalMoney(new BigDecimal(split[9]));
            stock.setBuyOneNum(Long.valueOf(split[10]));
            stock.setBuyOnePrice(new BigDecimal(split[11]));
            stock.setBuyTwoNum(Long.valueOf(split[12]));
            stock.setBuyTwoPrice(new BigDecimal(split[13]));
            stock.setBuyThreeNum(Long.valueOf(split[14]));
            stock.setBuyThreePrice(new BigDecimal(split[15]));
            stock.setBuyFourNum(Long.valueOf(split[16]));
            stock.setBuyFourPrice(new BigDecimal(split[17]));
            stock.setBuyFiveNum(Long.valueOf(split[18]));
            stock.setBuyFivePrice(new BigDecimal(split[19]));
            stock.setSellOneNum(Long.valueOf(split[20]));
            stock.setSellOnePrice(new BigDecimal(split[21]));
            stock.setSellTwoNum(Long.valueOf(split[22]));
            stock.setSellTwoPrice(new BigDecimal(split[23]));
            stock.setSellThreeNum(Long.valueOf(split[24]));
            stock.setSellThreePrice(new BigDecimal(split[25]));
            stock.setSellFourNum(Long.valueOf(split[26]));
            stock.setSellFourPrice(new BigDecimal(split[27]));
            stock.setSellFiveNum(Long.valueOf(split[28]));
            stock.setSellFivePrice(new BigDecimal(split[29]));
            stock.setStockDate(split[30]);
            stock.setStockTime(split[31]);
            stock.setBackup(split[32]);
            stockList.add(stock);
        }
        return stockList;
    }
}
