package com.hxc.cloud.order.controller;

import com.hxc.cloud.order.domain.ProductOrder;
import com.hxc.cloud.order.service.ProductOrderService;
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
public class OrderApi {

    @Autowired
    private ProductOrderService productOrderService;

    @RequestMapping("/save/{userId}/{productId}")
    public ProductOrder save(@PathVariable Integer userId,@PathVariable Integer productId) {
        return productOrderService.saveOrder(userId, productId);
    }
}
