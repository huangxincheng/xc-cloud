package com.hxc.cloud.user.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hxc.cloud.common.response.AppResponse;
import com.hxc.cloud.module.user.UserActiveResponse;
import com.hxc.cloud.module.user.UserRegisterResponse;
import com.hxc.cloud.user.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


/**
 * Author: huangxincheng
 * <p>
 * <p>
 **/
@Service
public class UserApiService {
    @Autowired
    private IUserInfoService userInfoService;

    /**
     * 用户注册
     * @param userName 用户名
     * @param userPasswd 用户密码
     * @param userEmail 用户邮箱
     * @return
     */
    public AppResponse<UserRegisterResponse> register(String userName, String userPasswd, String userEmail) {
        UserInfo one = userInfoService.getOne(new QueryWrapper<UserInfo>().lambda()
                .and(q -> q.eq(UserInfo::getUserName, userName)));
        if (one != null) {
            return AppResponse.ok(UserRegisterResponse.response(UserRegisterResponse.UserRegisterEnum.NAME_AIREADY_EXISTI));
        }
        one = userInfoService.getOne(new QueryWrapper<UserInfo>().lambda()
        .and(q -> q.eq(UserInfo::getUserEmail, userEmail)));
        if (one != null) {
            return AppResponse.ok(UserRegisterResponse.response(UserRegisterResponse.UserRegisterEnum.EMAIL_AIREADY_EXISTI));
        }
        boolean b = userInfoService.save(new UserInfo()
                .setUserName(userName)
                .setUserEmail(userEmail)
                .setUserPasswd(userPasswd)
                .setCreateTime(LocalDateTime.now())
                .setIsActive(false));

        //TODO 发送邮件 可采用MQ或者Async

        if (b) {
            return AppResponse.ok(UserRegisterResponse.response(UserRegisterResponse.UserRegisterEnum.SUCCESS));
        } else {
            return AppResponse.ok(UserRegisterResponse.response(UserRegisterResponse.UserRegisterEnum.CREATE_USER_FAIL));
        }
    }

    /**
     * 用户激活
     * @param userId 用户ID
     * @return
     */
    public AppResponse<UserActiveResponse> active(Integer userId) {
        UserInfo one = userInfoService.getById(userId);
        if (one == null) {
            return AppResponse.ok(UserActiveResponse.response(UserActiveResponse.UserActiveEnum.USER_NOT_EXISTI));
        }
        if (one.getIsActive()) {
            return AppResponse.ok(UserActiveResponse.response(UserActiveResponse.UserActiveEnum.USER_AIREADY_ACTIVE));
        }
        boolean b = userInfoService.updateById(one.setIsActive(true).setActiveTime(LocalDateTime.now()));
        if (b) {
            return AppResponse.ok(UserActiveResponse.response(UserActiveResponse.UserActiveEnum.SUCCESS));
        } else {
            return AppResponse.ok(UserActiveResponse.response(UserActiveResponse.UserActiveEnum.ACTIVE_USER_FAIL));
        }
    }
}
