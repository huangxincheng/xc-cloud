package com.hxc.cloud.module.product;

import com.hxc.cloud.module.product.domain.ProductInfo;
import lombok.Data;

import java.io.Serializable;

/**
 * Author: huangxincheng
 * <p>
 * <p>
 **/
@Data
public class ProductResponse implements Serializable {

    private ProductInfo productInfo;

    private Integer serverPort;
}
