package com.hxc.cloud.comsumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * Author: huangxincheng
 * <p>
 * <p>
 **/
@Component
@RocketMQMessageListener(topic = "${msg.msg-topic}", consumerGroup = "MsgTopicConsumer")
@Slf4j
public class MsgTopicConsumer implements RocketMQListener<String> {
    @Override
    public void onMessage(String message) {
        log.info("MsgTopicConsumer 消费消息 message = {}", message);
    }
}
