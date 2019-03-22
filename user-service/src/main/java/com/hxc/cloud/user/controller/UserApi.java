package com.hxc.cloud.user.controller;

import com.hxc.cloud.common.response.AppResponse;
import com.hxc.cloud.module.user.UserActiveResponse;
import com.hxc.cloud.module.user.UserRegisterResponse;
import com.hxc.cloud.module.user.request.UserRegisterRequest;
import com.hxc.cloud.user.service.UserApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Author: huangxincheng
 * <p>
 * <p>
 **/
@RestController
@RequestMapping("/api/v1/user")
@Slf4j
public class UserApi {

    @Autowired
    private UserApiService userApiService;

    /**
     * 用户注册接口
     * @param request
     * @return
     */
    @PutMapping("/register")
    public AppResponse<UserRegisterResponse> register(@RequestBody UserRegisterRequest request) {
        return userApiService.register(request.getUsername(), request.getPasswd(), request.getEmail());
    }

    /**
     * 用户激活接口
     * @param token
     * @return
     */
    @GetMapping("/active/{token}")
    public AppResponse<UserActiveResponse> active(@PathVariable String token) {
        int userId = Integer.parseInt(token);
        return userApiService.active(userId);
    }
}
