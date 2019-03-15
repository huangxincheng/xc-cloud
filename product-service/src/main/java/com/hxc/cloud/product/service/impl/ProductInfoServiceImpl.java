package com.hxc.cloud.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hxc.cloud.product.entity.ProductInfo;
import com.hxc.cloud.product.mapper.ProductInfoMapper;
import com.hxc.cloud.product.service.IProductInfoService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author huangxincheng
 * @since 2019-03-15
 */
@Service
public class ProductInfoServiceImpl extends ServiceImpl<ProductInfoMapper, ProductInfo> implements IProductInfoService {

}
