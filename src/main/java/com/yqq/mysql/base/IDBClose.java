package com.yqq.mysql.base;

/**
 * 关闭SQLRunner接口,需要所有的dao的接口去继承
 *
 * @author Live.InPast
 * @date 2019-08-01
 */
public interface IDBClose {

    /**
     * 关闭SqlRunner
     */
    void close();

}
