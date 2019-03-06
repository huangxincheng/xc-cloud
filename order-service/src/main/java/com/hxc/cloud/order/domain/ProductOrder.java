package com.hxc.cloud.order.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Author: huangxincheng
 * <p>
 * <p>
 **/
@Data
@Accessors(chain = true)
public class ProductOrder implements Serializable {

    private String tradeNo;

    private Date tradeTime;

    private Integer userId;

    private Integer productId;

    private BigDecimal amount;

    private Integer serverPort;
}
