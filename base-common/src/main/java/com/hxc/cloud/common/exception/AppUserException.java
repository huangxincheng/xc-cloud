package com.hxc.cloud.common.exception;

/**
 * Author: huangxincheng
 * <p>
 * <p>
 *     AppUserException
 **/
public class AppUserException extends AppException {
    public AppUserException() {
        super();
    }


    public AppUserException(String message) {
        super(message);
    }


    public AppUserException(String message, Throwable cause) {
        super(message, cause);
    }

}
