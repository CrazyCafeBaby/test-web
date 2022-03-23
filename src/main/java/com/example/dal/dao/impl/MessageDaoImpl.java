package com.example.dal.dao.impl;


import com.example.dal.dao.MessageDao;
import com.example.mdo.MsgDO;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 消息dao实现类
 */
@Component
public class MessageDaoImpl implements MessageDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageDaoImpl.class);

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    private DataSource dataSource;

    /***
     * @see MessageDao#insertMsg(String)
     */
    public void insertMsg(String msg) throws SQLException {
        LOGGER.info("数据库开始");
        Connection connection = dataSource.getConnection();

        Statement statement = connection.createStatement();

        statement.executeUpdate(String.format(
                "INSERT INTO msg_detail (msg) VALUES ('%s')", msg
        ));

        connection.close();
    }

    /**
     * @see MessageDao#selectLast3()
     */
    public List<MsgDO> selectLast3()  throws SQLException {
        LOGGER.info("数据库查询开始");
        String sql = "SELECT id,gmt_create,gmt_modified,msg from msg_detail order by id desc limit 3";
        Map<String, Object> param = new HashMap<String, Object>();
        return jdbcTemplate.query(sql, param, new RowMapper<MsgDO>() {
            public MsgDO mapRow(ResultSet resultSet, int i) throws SQLException {
                MsgDO msgDO = new MsgDO();
                msgDO.setId(resultSet.getLong("id"));
                msgDO.setMsg(resultSet.getString("msg"));
                msgDO.setGmtCreate(resultSet.getDate("gmt_create"));
                msgDO.setGmtModified(resultSet.getDate("gmt_modified"));
                return msgDO;
            }
        });
    }
}
