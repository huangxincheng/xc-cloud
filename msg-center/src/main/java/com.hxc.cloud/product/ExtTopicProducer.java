package com.hxc.cloud.product;

import lombok.extern.slf4j.Slf4j;
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
 * 发送组合Topic+Tag的消息生产者
 */
public class ExtTopicProducer {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Value("${msg.ext-topic}")
    private String extTopic;

    @Value("${msg.ext-tag}")
    private String extTag;

    public void send() {
        rocketMQTemplate.convertAndSend(extTopic + ":" + extTag, "To do ext-topic ext-tag");
        rocketMQTemplate.convertAndSend(extTopic + ":" + "aaa", "To do ext-topic aaa");
        log.info("[ExtTopicProducer] [send] convertAndSend");
    }
}
