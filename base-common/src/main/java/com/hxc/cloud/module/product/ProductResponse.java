package com.hxc.cloud.module.product;

import com.hxc.cloud.module.product.model.ProductInfoVo;
import lombok.Data;

import java.io.Serializable;

/**
 * Author: huangxincheng
 * <p>
 * <p>
 **/
@Data
public class ProductResponse implements Serializable {

    private ProductInfoVo productInfo;

    private Integer serverPort;
}
