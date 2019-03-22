package com.hxc.cloud.module.user;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * Author: huangxincheng
 * <p>
 * <p>
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UserActiveResponse implements Serializable {

    /**
     * 激活返回状态 参考UserRegisterEnum
     */
    private int activeStatus;

    /**
     * 激活返回信息
     */
    private String activeMsg;

    public enum UserActiveEnum {

        SUCCESS(0, "激活成功"),

        USER_AIREADY_ACTIVE(-1, "激活失败,用户已激活"),

        USER_NOT_EXISTI(-2, "激活失败,不存在此用户信息"),

        ACTIVE_USER_FAIL(-3, "激活用户失败,系统异常")

        ;
        private int state;

        private String msg;

        UserActiveEnum(int state, String msg) {
            this.state = state;
            this.msg = msg;
        }
    }

    public static UserActiveResponse response(UserActiveResponse.UserActiveEnum activeEnum) {
        return new UserActiveResponse()
                .setActiveStatus(activeEnum.state)
                .setActiveMsg(activeEnum.msg);
    }
}
