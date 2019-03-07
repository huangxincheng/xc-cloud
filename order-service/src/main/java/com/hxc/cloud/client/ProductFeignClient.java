package com.hxc.cloud.client;

import com.hxc.cloud.constant.AppConstant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Author: huangxincheng
 * <p>
 * <p>
 **/
@FeignClient(name = AppConstant.PRODUCT_SERVICE, fallback = ProductFeignClientFallback.class)
public interface ProductFeignClient {

    @GetMapping("/api/v1/product/get/{id}")
    String get(@PathVariable("id") Integer id);

    @GetMapping("/api/v1/product/list")
    String list();
}
