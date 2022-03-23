package com.example.dal.dao;

import com.example.mdo.MsgDO;

import java.sql.SQLException;
import java.util.List;

public interface MessageDao {

    void insertMsg(String msg)  throws SQLException;

    List<MsgDO> selectLast3()  throws SQLException;
}
