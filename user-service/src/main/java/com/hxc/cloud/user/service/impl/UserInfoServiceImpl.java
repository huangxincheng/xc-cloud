package com.hxc.cloud.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hxc.cloud.user.entity.UserInfo;
import com.hxc.cloud.user.mapper.UserInfoMapper;
import com.hxc.cloud.user.service.IUserInfoService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author huangxincheng
 * @since 2019-03-22
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {

}
