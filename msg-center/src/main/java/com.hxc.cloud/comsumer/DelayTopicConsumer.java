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
@Slf4j
@RocketMQMessageListener(topic = "${msg.delay-topic}", consumerGroup = "DelayTopicConsumer")
public class DelayTopicConsumer implements RocketMQListener<String> {
    @Override
    public void onMessage(String message) {
        log.info("[DelayTopicConsumer] [onMessage] 消费信息: {}", message);
    }
}
