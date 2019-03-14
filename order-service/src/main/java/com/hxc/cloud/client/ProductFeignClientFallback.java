package com.hxc.cloud.client;

import com.hxc.cloud.common.response.AppResponse;
import com.hxc.cloud.module.product.ProductResponse;
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
    public AppResponse<ProductResponse> get(Integer id) {
        log.error("ProductFeignClient get 异常 发送短信");
        return null;
    }

    @Override
    public String list() {
        log.error("ProductFeignClient list异常 发送短信");
        return null;
    }
}
