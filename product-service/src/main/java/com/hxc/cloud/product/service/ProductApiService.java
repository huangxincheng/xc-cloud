package com.hxc.cloud.product.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hxc.cloud.common.utils.TransUtil;
import com.hxc.cloud.module.product.model.ProductInfoVo;
import com.hxc.cloud.product.entity.ProductInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Author: huangxincheng
 * <p>
 * <p>
 **/
@Service
public class ProductApiService {

    @Autowired
    private IProductInfoService service;

    public ProductInfoVo getProduct(Integer id) {
//        throw new AppProductException("查询商品失败");

        ProductInfo productInfo = service.getById(id);
        return TransUtil.trans(productInfo,productInfo1 -> {
            return new ProductInfoVo()
                    .setId(productInfo.getId())
                    .setPrice(productInfo.getProductMoney())
                    .setProductName(productInfo.getProductName());

        });
    }

    public List<ProductInfoVo> listProduct() {
        List<ProductInfo> list = service.list();
        return list.stream().map(productInfo -> {
            return new ProductInfoVo()
                    .setId(productInfo.getId())
                    .setPrice(productInfo.getProductMoney())
                    .setProductName(productInfo.getProductName());
        }).collect(Collectors.toList());
    }




}
