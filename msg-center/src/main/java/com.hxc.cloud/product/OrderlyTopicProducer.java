package com.hxc.cloud.product;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

/**
 * Author: huangxincheng
 * <p>
 * <p>
 **/
@Component
@Slf4j
public class OrderlyTopicProducer {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Value("${msg.orderly-topic}")
    private String orderlyTopic;

    public void sendOrderly() {
        for(int i = 0; i < 10; i++) {
            SendResult sendResult = rocketMQTemplate.syncSendOrderly(orderlyTopic, MessageBuilder.withPayload("顺序消息ID=" + i).build(), i + "");
            log.info("[OrderTopicProducer] [sendOrderly] id = {} sendResult = {}", i, sendResult);
        }
    }


}
