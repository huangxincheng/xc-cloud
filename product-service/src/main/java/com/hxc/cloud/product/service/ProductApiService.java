package com.hxc.cloud.product.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hxc.cloud.common.utils.TransUtil;
import com.hxc.cloud.module.product.model.ProductInfoVo;
import com.hxc.cloud.product.entity.ProductInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
//        ProductInfo p = service.getOne(
//                new QueryWrapper<ProductInfo>().lambda()
//                        .and(q -> q.eq(ProductInfo::getProductName, "眼镜框"))
//                        .and(q->q.eq(ProductInfo::getProductMoney, 1000.5))
//        );
//        System.out.println(p);
        ProductInfo productInfo = service.getById(id);
        return TransUtil.trans(productInfo, p -> {
            return new ProductInfoVo()
                    .setId(p.getId())
                    .setPrice(p.getProductMoney())
                    .setProductName(p.getProductName());

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


    public List<ProductInfoVo> listProductByName(String name) {
        List<ProductInfo> list = service.list(new QueryWrapper<ProductInfo>().lambda()
                .and(q -> q.eq(ProductInfo::getProductName, name)));
        return list.stream().map(productInfo -> {
            return new ProductInfoVo()
                    .setId(productInfo.getId())
                    .setPrice(productInfo.getProductMoney())
                    .setProductName(productInfo.getProductName());
        }).collect(Collectors.toList());
    }

    public ProductInfoVo getProductByName(String name) {
        ProductInfo productInfo = service.getOne(new QueryWrapper<ProductInfo>()
                .last("limit 1")
                .orderByDesc("ctime")
                .lambda()
                .and(q -> q.eq(ProductInfo::getProductName, name)));
        return TransUtil.trans(productInfo ,p -> {
            return new ProductInfoVo()
                    .setId(p.getId())
                    .setPrice(p.getProductMoney())
                    .setProductName(p.getProductName());

        });
    }




}
