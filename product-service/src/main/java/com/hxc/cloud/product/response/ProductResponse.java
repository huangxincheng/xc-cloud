package com.hxc.cloud.product.response;

import com.hxc.cloud.product.domain.ProductInfo;
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
