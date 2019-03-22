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
public class UserRegisterResponse implements Serializable {

    /**
     * 注册返回状态 参考UserRegisterEnum
     */
    private int registerStatus;

    /**
     * 注册返回信息
     */
    private String registerMsg;

    public enum UserRegisterEnum {

        SUCCESS(0, "注册成功"),

        NAME_AIREADY_EXISTI(-1, "用户名存在"),

        EMAIL_AIREADY_EXISTI(-2, "邮箱已存在"),

        CREATE_USER_FAIL(-3, "创建用户失败,系统异常")

        ;
        private int state;

        private String msg;

        UserRegisterEnum(int state, String msg) {
            this.state = state;
            this.msg = msg;
        }
    }

    public static UserRegisterResponse response(UserRegisterEnum registerEnum) {
        return new UserRegisterResponse()
                .setRegisterStatus(registerEnum.state)
                .setRegisterMsg(registerEnum.msg);
    }
}
