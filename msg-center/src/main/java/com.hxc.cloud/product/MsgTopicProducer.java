package com.hxc.cloud.product;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Author: huangxincheng
 * <p>
 * <p>
 **/
@Component
@Slf4j
/**
 * 发送简单消息生产者
 */
public class MsgTopicProducer {

    @Value("${msg.msg-topic}")
    private String msgTopic;

    @Autowired
    private RocketMQTemplate rocketMQTemplate;


    public SendResult syncSend() {
        SendResult sendResult = rocketMQTemplate.syncSend(msgTopic, "HELLO, WORLD");
        log.info("[MsgTopicProduct] [syncSend] sendResult : " + sendResult);
        return sendResult;
    }
}
