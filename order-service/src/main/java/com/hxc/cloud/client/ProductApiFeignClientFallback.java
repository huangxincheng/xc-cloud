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
public class ProductApiFeignClientFallback implements ProductApiFeignClient {
    @Override
    public AppResponse<ProductResponse> get(Integer id) {
        log.error("ProductApiFeignClient get 异常 发送短信");
        return null;
    }

    @Override
    public String list() {
        log.error("ProductApiFeignClient list异常 发送短信");
        return null;
    }
}
