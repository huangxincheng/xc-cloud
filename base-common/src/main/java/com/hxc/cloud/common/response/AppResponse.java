package com.hxc.cloud.common.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * Author: huangxincheng
 * <p>
 * <p>
 **/
@Getter
@Setter
@ToString
@Accessors(chain = true)
public class AppResponse<T extends Object> implements Serializable {

    private Integer code;

    private String msg;

    private T data;

    public static <T> AppResponse<T> ok(T data) {
        return new AppResponse()
                .setCode(AppCodeEnum.SUCCESS.getCode())
                .setMsg(AppCodeEnum.SUCCESS.getMsg())
                .setData(data);
    }

    public static AppResponse fail(Integer code, String msg) {
        return new AppResponse().setCode(code).setMsg(msg);
    }

    public static AppResponse fail(AppCodeEnum appCodeEnum) {
        return fail(appCodeEnum.getCode(), appCodeEnum.getMsg());
    }
}
