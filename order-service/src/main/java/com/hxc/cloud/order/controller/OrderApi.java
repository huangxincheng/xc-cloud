package com.hxc.cloud.order.controller;

import com.hxc.cloud.base.BaseResponse;
import com.hxc.cloud.order.domain.ProductOrder;
import com.hxc.cloud.order.service.ProductOrderService;
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
    private ProductOrderService productOrderService;

    @RequestMapping("/save/{userId}/{productId}")
    @HystrixCommand(fallbackMethod = "saveFallback")
    public BaseResponse save(@PathVariable Integer userId, @PathVariable Integer productId) {
        ProductOrder data = productOrderService.saveOrder(userId, productId);
        return BaseResponse.toSuccess(data);
    }

    public BaseResponse saveFallback(Integer userId, Integer productId){
        log.error("saveFallback userId = " + userId + ", productId = " + productId);
        return BaseResponse.toFail("当前访问人次过多,稍后再试");
    }
}
