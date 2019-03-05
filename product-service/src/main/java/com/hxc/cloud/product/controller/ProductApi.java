package com.hxc.cloud.product.controller;

import com.hxc.cloud.product.domain.ProductInfo;
import com.hxc.cloud.product.response.ProductResponse;
import com.hxc.cloud.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Author: huangxincheng
 * <p>
 * <p>
 **/
@RestController
@Slf4j
@RequestMapping("/api/v1/product")
public class ProductApi {

    @Autowired
    private ProductService productService;

    @Value("${server.port}")
    private Integer serverPort;

    @RequestMapping("/get/{id}")
    public ProductResponse get(@PathVariable Integer id) {
        ProductInfo product = productService.getProduct(id);
        ProductResponse response = new ProductResponse();
        response.setProductInfo(product);
        response.setServerPort(serverPort);
        log.info("ProducApi serverPory = {} productInfo = {}", serverPort, product);
        return response;
    }

    @RequestMapping("/list")
    public List<ProductInfo> list() {
        return productService.listProduct();
    }
}
