package com.hxc.cloud.client;

import com.hxc.cloud.constant.AppConstant;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * Author: huangxincheng
 * <p>
 * <p>
 **/
@FeignClient(name = AppConstant.USER_SERVICE, fallback = UserApiFeignClientFallback.class)
public interface UserApiFeignClient {
}
