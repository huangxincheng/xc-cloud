package com.hxc.cloud.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Author: huangxincheng
 * <p>
 * <p>
 **/
@Component
@Slf4j
public class ProductFeignClientFallback implements ProductFeignClient {
    @Override
    public String get(Integer id) {
        log.error("ProductFeignClient get 异常 发送短信");
        return null;
    }

    @Override
    public String list() {
        log.error("ProductFeignClient list异常 发送短信");
        return null;
    }
}
