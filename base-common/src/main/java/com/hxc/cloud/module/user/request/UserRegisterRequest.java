package com.hxc.cloud.module.user.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * Author: huangxincheng
 * <p>
 * <p>
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UserRegisterRequest {

    private String username;

    private String passwd;

    private String email;
}
