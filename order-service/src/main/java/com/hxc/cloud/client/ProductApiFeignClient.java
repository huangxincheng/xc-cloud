package com.hxc.cloud.client;

import com.hxc.cloud.common.constant.AppConstant;
import com.hxc.cloud.common.response.AppResponse;
import com.hxc.cloud.module.product.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Author: huangxincheng
 * <p>
 * <p>
 **/
@FeignClient(name = AppConstant.PRODUCT_SERVICE, fallback = ProductApiFeignClientFallback.class)
public interface ProductApiFeignClient {

    @GetMapping("/api/v1/product/get/{id}")
    AppResponse<ProductResponse> get(@PathVariable("id") Integer id);

    @GetMapping("/api/v1/product/list")
    String list();
}
