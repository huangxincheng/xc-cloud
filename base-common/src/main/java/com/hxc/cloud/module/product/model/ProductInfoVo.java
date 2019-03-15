package com.hxc.cloud.module.product.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Author: huangxincheng
 * <p>
 * <p>
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ProductInfoVo implements Serializable {

    private Integer id;

    private String productName;

    private BigDecimal price;
}
