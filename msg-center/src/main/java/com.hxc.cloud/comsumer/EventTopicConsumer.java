package com.hxc.cloud.comsumer;

import com.hxc.cloud.envent.OrderEvent;
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
@RocketMQMessageListener(topic = "${msg.event-topic}", consumerGroup = "EventTopicConsumer")
public class EventTopicConsumer implements RocketMQListener<OrderEvent> {
    @Override
    public void onMessage(OrderEvent message) {
        log.info("[EventTopicConsumer] [onMessage] 消费信息: {}", message);
    }
}
