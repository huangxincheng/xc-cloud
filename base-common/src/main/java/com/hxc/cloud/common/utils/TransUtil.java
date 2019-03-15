package com.hxc.cloud.common.utils;

import java.util.function.Function;

/**
 * Author: huangxincheng
 * <p>
 * <p>
 *     转换Util
 **/
public class TransUtil {

    /**
     * 当对象转换
     * @param t
     * @param function
     * @param <T>
     * @param <R>
     * @return
     */
    public static  <T,R> R trans(T t, Function<T,R> function) {
        return function.apply(t);
    }
}
