package com.hxc.cloud.order.service;

import com.hxc.cloud.constant.AppConstant;
import com.hxc.cloud.order.domain.ProductOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * Author: huangxincheng
 * <p>
 * <p>
 **/
@Service
@Slf4j
public class ProductOrderService {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 保存订单
     * @param productId
     * @param userId
     * @return
     */
    public ProductOrder saveOrder(Integer productId, Integer userId) {
        Map map = restTemplate.getForObject("http://" + AppConstant.PRODUCT_SERVICE + "/api/v1/product/get/" + productId, Map.class);
        ProductOrder productOrder = new ProductOrder()
                .setTradeNo(UUID.randomUUID().toString())
                .setProductId(productId)
                .setTradeTime(new Date())
                .setUserId(userId)
                .setServerPort((Integer)map.get("serverPort"));
        log.info("saveOrder productId = {} userId = {} order = {}", productId, userId, productOrder.toString());
        return productOrder;
    }
}
