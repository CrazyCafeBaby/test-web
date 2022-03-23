package com.example.dal.dao;

import com.example.mdo.MsgDO;

import java.sql.SQLException;
import java.util.List;

/**
 * 消息dao
 */
public interface MessageDao {

    /**
     * 新增一条消息记录
     *
     * @param msg 消息内容
     * @throws SQLException 异常
     */
    void insertMsg(String msg)  throws SQLException;

    /**
     * 查询最近三次新增的消息记录
     *
     * @return 最新三条消息记录列表
     * @throws SQLException 异常
     */
    List<MsgDO> selectLast3()  throws SQLException;
}
