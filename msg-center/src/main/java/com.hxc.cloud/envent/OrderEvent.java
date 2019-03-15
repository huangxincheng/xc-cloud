package com.hxc.cloud.envent;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * Author: huangxincheng
 * <p>
 * <p>
 **/
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper=false)
public class OrderEvent {

    private String orderId;

    private BigDecimal paidMoney;
}
