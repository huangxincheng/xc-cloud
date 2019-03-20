package com.hxc.cloud.module.order.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
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

    private LocalDateTime tradeTime;

    private Integer userId;

    private Integer productId;

    private BigDecimal amount;

    private Integer serverPort;
}
