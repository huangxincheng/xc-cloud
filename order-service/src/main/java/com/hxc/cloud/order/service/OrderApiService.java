package com.hxc.cloud.order.service;

import com.alibaba.fastjson.JSON;
import com.hxc.cloud.client.ProductFeignClient;
import com.hxc.cloud.common.exception.AppProductException;
import com.hxc.cloud.common.response.AppCodeEnum;
import com.hxc.cloud.common.response.AppResponse;
import com.hxc.cloud.common.utils.TransUtil;
import com.hxc.cloud.module.order.model.ProductOrder;
import com.hxc.cloud.module.product.ProductResponse;
import com.hxc.cloud.order.entity.OrderInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * Author: huangxincheng
 * <p>
 * <p>
 **/
@Service
@Slf4j
public class OrderApiService {

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

    @Autowired
    private IOrderInfoService orderInfoService;

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
        OrderInfo orderInfo = new OrderInfo()
                .setProductId(productId)
                .setTradeNo(UUID.randomUUID().toString())
                .setTradeAmount(new BigDecimal(new Random().nextInt(100)))
                .setTradeTime(LocalDateTime.now())
                .setUserId(userId);
        orderInfoService.save(orderInfo);
        ProductOrder po = TransUtil.trans(orderInfo, o -> {
            ProductOrder productOrder = new ProductOrder()
                    .setTradeNo(o.getTradeNo())
                    .setProductId(o.getProductId())
                    .setTradeTime(o.getTradeTime())
                    .setUserId(o.getUserId())
                    .setServerPort(appResponse.getData().getServerPort());
            return productOrder;
        });
        log.info("saveOrder productId = {} userId = {} order = {}", productId, userId, po.toString());
        return po;
    }
}
