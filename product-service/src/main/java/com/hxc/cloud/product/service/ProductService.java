package com.hxc.cloud.product.service;

import com.hxc.cloud.module.product.model.ProductInfoVo;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: huangxincheng
 * <p>
 * <p>
 **/
@Service
public class ProductService {

    private static List<ProductInfoVo> pis;

    static {
        pis = new ArrayList<>();
        pis.add(new ProductInfoVo(1, "商品01", new BigDecimal(101)));
        pis.add(new ProductInfoVo(2, "商品02", new BigDecimal(102)));
        pis.add(new ProductInfoVo(3, "商品03", new BigDecimal(103)));
        pis.add(new ProductInfoVo(4, "商品04", new BigDecimal(104)));
        pis.add(new ProductInfoVo(5, "商品05", new BigDecimal(105)));
        pis.add(new ProductInfoVo(6, "商品06", new BigDecimal(106)));
        pis.add(new ProductInfoVo(7, "商品07", new BigDecimal(107)));
        pis.add(new ProductInfoVo(8, "商品08", new BigDecimal(108)));
        pis.add(new ProductInfoVo(9, "商品09", new BigDecimal(109)));
        pis.add(new ProductInfoVo(10, "商品10", new BigDecimal(110)));
    }

    public ProductInfoVo getProduct(Integer id) {
//        throw new AppProductException("查询商品失败");
        return pis.get(id - 1);
    }

    public List<ProductInfoVo> listProduct() {
        return pis;
    }




}
