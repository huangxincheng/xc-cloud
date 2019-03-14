package com.hxc.cloud.common.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Author: huangxincheng
 * <p>
 * <p>
 **/
@Getter
@ToString
public enum AppCodeEnum {

    SUCCESS(0, "SUCCESS"),

    AUTH_FAIL(-1, "认证失败"),

    RATE_LIMITER_FAIL(-2, "请求太多,您已被挤出."),

    SYSTEM_ERROR_FAIL(-3, "系统繁忙,请稍后再试."),

    APP_EXCEPTION_FAIL(-4, "APP自定义异常"),
    ;

    private Integer code;

    private String msg;

    AppCodeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static AppCodeEnum getAppCodeEnum(int code) {
        for (AppCodeEnum appCodeEnum : AppCodeEnum.values()) {
            if (code == appCodeEnum.code) {
                return appCodeEnum;
            }
        }
        return null;
    }

    public static String getMsg(int code) {
        AppCodeEnum appCodeEnum = getAppCodeEnum(code);
        return appCodeEnum != null ? appCodeEnum.msg : null;
    }

}
