package com.example.web;

import com.alibaba.fastjson.JSON;
import com.example.dal.dao.MessageDao;
import com.example.integration.RpcClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试controller层，提供http接口测试
 */
@RestController
public class Controller {

    private static final Logger LOGGER = LoggerFactory.getLogger(Controller.class);

    private RpcClient rpcClient;

    private MessageDao messageDao;

    /**
     * 发送消息的http接口
     */
    @RequestMapping("/message/send")
    public String create(@RequestParam("msg") String msg) {
        rpcClient.sendMessage(msg);

        return "send msg [" + msg + "], success";
    }

    /**
     * 查询最近三次新增消息的接口
     */
    @RequestMapping("/message/get")
    public String get() {
        try {
            LOGGER.info("查询开始");
            String abc =  "查询最近三次消息发送结果：" + JSON.toJSONString(messageDao.selectLast3());
            LOGGER.info("查询结束," + abc);
            return abc;
        } catch (Exception e) {
            return "abc";
        }
    }

    public void setRpcClient(RpcClient rpcClient) {
        this.rpcClient = rpcClient;
    }

    public void setMessageDao(MessageDao messageDao) {
        this.messageDao = messageDao;
    }
}
