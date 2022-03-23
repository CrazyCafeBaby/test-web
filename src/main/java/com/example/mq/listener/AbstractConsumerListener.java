package com.example.mq.listener;

import com.example.mq.properties.MqConsumerProperties;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * 抽象消息监听器
 */
@Configuration
public abstract class AbstractConsumerListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractConsumerListener.class);

    @Autowired
    private MqConsumerProperties consumerProperties;

    /**
     * 开启消息监听
     */
    public void listener(String topic, String tags) throws MQClientException {

        LOGGER.info("开启" + topic + ": " + tags + "消费监听");

        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("TEST_TOPIC");
        consumer.setNamesrvAddr("172.31.38.111:9876");
        consumer.subscribe(topic, tags);

        consumer.registerMessageListener(new MessageListenerConcurrently() {
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                return AbstractConsumerListener.this.onMessage(msgs);
            }
        });

        consumer.start();

    }

    public abstract ConsumeConcurrentlyStatus onMessage(List<MessageExt> msgs);
}
