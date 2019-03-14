package com.hxc.cloud.order.service;

import com.alibaba.fastjson.JSON;
import com.hxc.cloud.client.ProductFeignClient;
import com.hxc.cloud.common.exception.AppProductException;
import com.hxc.cloud.common.response.AppCodeEnum;
import com.hxc.cloud.common.response.AppResponse;
import com.hxc.cloud.constant.AppConstant;
import com.hxc.cloud.module.product.ProductResponse;
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

    /**
     * Ribbon 方式调用
     */
    @Autowired
    private RestTemplate restTemplate;

    /**
     * Feign 方式调用
     */
    @Autowired
    private ProductFeignClient productFeignClient;

    /**
     * 保存订单
     * @param productId
     * @param userId
     * @return
     */
    public ProductOrder saveOrder(Integer productId, Integer userId) {
        AppResponse<ProductResponse> appResponse = productFeignClient.get(productId);
        log.info("fiegn 调用 appResponse = {}", JSON.toJSONString(appResponse));
        if (AppCodeEnum.SUCCESS.getCode().intValue() != appResponse.getCode()) {
            throw new AppProductException("查询商品信息失败");
        }
        ProductOrder productOrder = new ProductOrder()
                .setTradeNo(UUID.randomUUID().toString())
                .setProductId(productId)
                .setTradeTime(new Date())
                .setUserId(userId)
                .setServerPort(appResponse.getData().getServerPort());
        log.info("saveOrder productId = {} userId = {} order = {}", productId, userId, productOrder.toString());
        return productOrder;
    }
}
