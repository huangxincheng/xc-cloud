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
@Slf4j
@Component
/**
 * 发送延时消息生产者
 */
public class DelayTopicProducer {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Value("${msg.delay-topic}")
    private String delayTopic;

    public SendResult sendDelay() {
        /**
         * delayLevel : 0 表示不延时
         *              1 表示延时1s
         *              ......
         *              3 表示延时10s
         *              ......
         *              5 表示延时1m
         *              ......
         *              在服务器端（rocketmq-broker端）的属性配置文件中加入以下行：(下面是默认的)
         * messageDelayLevel=1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h
         */
        SendResult sendResult = rocketMQTemplate.syncSend(delayTopic, MessageBuilder.withPayload("delay payload").build(), rocketMQTemplate.getProducer().getSendMsgTimeout(), 3);
        log.info("[DelayTopicProducer] [sendDelay] sendResult:{}", sendResult);
        return sendResult;
    }
}
