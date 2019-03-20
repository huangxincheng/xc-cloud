package com.hxc.cloud.order.controller;

import com.hxc.cloud.common.response.AppCodeEnum;
import com.hxc.cloud.common.response.AppResponse;
import com.hxc.cloud.module.order.model.ProductOrder;
import com.hxc.cloud.order.service.OrderApiService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author: huangxincheng
 * <p>
 * <p>
 **/
@RestController
@RequestMapping("/api/v1/order")
@Slf4j
public class OrderApi {

    @Autowired
    private OrderApiService productOrderService;

    @RequestMapping("/save/{userId}/{productId}")
    @HystrixCommand(fallbackMethod = "saveFallback")
    public AppResponse save(@PathVariable Integer userId, @PathVariable Integer productId) {
        ProductOrder data = productOrderService.saveOrder(userId, productId);
        return AppResponse.ok(data);
    }

    public AppResponse saveFallback(Integer userId, Integer productId, Throwable ex){
        log.error("[OrderApi] [saveFallback] userId = " + userId + " productId = " + productId, ex);
        return AppResponse.fail(AppCodeEnum.APP_EXCEPTION_FAIL.getCode(), "保存商品信息失败");
    }
}
