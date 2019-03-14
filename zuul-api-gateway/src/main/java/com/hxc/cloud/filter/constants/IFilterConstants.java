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
    public static final int PRE_AUTH_FILTER_ORDER = 3;

    /**
     * 限流过滤器顺序
     */
    public static final int PRE_RATE_LIMITER_FILTER_ORDER = -5;

    /**
     * 全局返回值顺序
     */
    public static final int POST_GLOBAL_RESPONSE_FILTER_ORDER  = -3;

    /*
       <!-------------------------------FILTER REDIS KEY PRE ----------------------------->
     */
    /**
     * Zuul 限流Redis Key 前缀
     */
    public static final String FILTER_LIMIT_KEY_PRE = "zuul_rate_limiter_pre:";
}
