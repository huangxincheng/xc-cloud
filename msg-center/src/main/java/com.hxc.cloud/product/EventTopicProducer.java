package com.hxc.cloud.product;

import com.hxc.cloud.envent.OrderEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Random;
import java.util.UUID;

/**
 * Author: huangxincheng
 * <p>
 * <p>
 **/
@Component
@Slf4j
/**
 * 发送结构化对象的消息生产者
 */
public class EventTopicProducer {

    @Value("${msg.event-topic}")
    private String eventTopic;

    @Autowired
    private RocketMQTemplate rocketMQTemplate;



    public void asyncSend() {
        rocketMQTemplate.asyncSend(
                eventTopic,
                new OrderEvent().setOrderId(UUID.randomUUID().toString()).setPaidMoney(new BigDecimal(new Random().nextInt(6))),
                new SendCallback() {
                    @Override
                    public void onSuccess(SendResult sendResult) {
                        log.info("[EventTopicProduct] [onSuccess] sendResult = {}", sendResult);
                    }

                    @Override
                    public void onException(Throwable throwable) {
                        log.error("[EventTopicProduct] [onException]", throwable);
                    }
                }
        );
    }
}
