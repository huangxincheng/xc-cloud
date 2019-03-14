package com.hxc.cloud.common.config;

import com.hxc.cloud.common.exception.AppException;
import com.hxc.cloud.common.response.AppCodeEnum;
import com.hxc.cloud.common.response.AppResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Author: huangxincheng
 * <p>
 * <p>
 **/
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = AppException.class)
    public AppResponse handlerAppResponse(Exception ex) {
        log.error("[GlobalExceptionHandler] [handlerAppResponse] ========", ex);
        return AppResponse.fail(AppCodeEnum.APP_EXCEPTION_FAIL.getCode(), ex.getMessage());
    }
}
