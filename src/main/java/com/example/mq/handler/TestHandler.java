package com.example.mq.handler;

import com.example.dal.dao.MessageDao;
import com.example.mq.service.impl.AbstractConsumerServiceImpl;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class TestHandler extends AbstractConsumerServiceImpl {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestHandler.class);

    @Autowired
    private MessageDao messageDao;

    @Override
    public ConsumeConcurrentlyStatus handle(String msg) {
        LOGGER.info("mq消息处理开始, message:{}", msg);
        try {
            messageDao.insertMsg(msg);
        } catch (Exception e) {
            LOGGER.error("处理失败:{}", e);
            return ConsumeConcurrentlyStatus.RECONSUME_LATER;
        }

        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }
}
