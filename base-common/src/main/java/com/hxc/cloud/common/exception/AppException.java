package com.hxc.cloud.common.exception;

/**
 * Author: huangxincheng
 * <p>
 * <p>
 *     AppException
 **/
public class AppException extends RuntimeException {

    public AppException() {
        super();
    }


    public AppException(String message) {
        super(message);
    }


    public AppException(String message, Throwable cause) {
        super(message, cause);
    }

}
