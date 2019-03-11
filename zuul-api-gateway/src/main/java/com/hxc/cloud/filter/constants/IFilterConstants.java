package com.hxc.cloud.filter.constants;

/**
 * Author: huangxincheng
 * <p>
 * <p>
 **/
public class IFilterConstants {

    /*
       <!-------------------------------FILTER ORDER ----------------------------->
     */
    /**
     * 用户认证过滤器顺序
     */
    public static final int AUTH_FILTER_ORDER = 3;

    /**
     * 限流过滤器顺序
     */
    public static final int RATE_LIMITER_FILTER_ORDER = -5;

    /*
       <!-------------------------------FILTER LIMIT KEY PRE ----------------------------->
     */
    public static final String FILTER_LIMIT_KEY_PRE = "zuul_rate_limiter_pre:";
}
