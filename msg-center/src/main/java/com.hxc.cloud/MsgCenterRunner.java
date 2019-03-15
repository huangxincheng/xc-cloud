package com.hxc.cloud;

import com.hxc.cloud.product.*;
import org.apache.rocketmq.client.producer.SendResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Author: huangxincheng
 * <p>
 * <p>
 **/
@Component
public class MsgCenterRunner implements CommandLineRunner {

    @Autowired
    private MsgTopicProducer msgTopicProduct;

    @Autowired
    private EventTopicProducer eventTopicProduct;

    @Autowired
    private ExtTopicProducer extTopicProducer;

    @Autowired
    private DelayTopicProducer delayTopicProducer;

    @Autowired
    private OrderlyTopicProducer orderlyTopicProducer;

    @Override
    public void run(String... args) throws Exception {
        SendResult sendResult = msgTopicProduct.syncSend();
        eventTopicProduct.asyncSend();
        extTopicProducer.send();
        while (true) {
            int i = 1 / 1;
        }
    }
}
