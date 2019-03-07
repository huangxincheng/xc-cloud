package com.hxc.cloud.base;

import lombok.*;
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
public class BaseResponse implements Serializable {

    private int status;

    private String msg = "SUCCESS";

    private Object data;

    private BaseResponse() {}


    public final static BaseResponse toSuccess(Object data) {
        return new BaseResponse().setData(data);
    }

    public final static BaseResponse toFail(String msg) {
        return toFail(-1, msg);
    }

    public final static BaseResponse toFail(int status, String msg) {
        return new BaseResponse().setStatus(status).setMsg(msg);
    }

}

