package com.hxc.cloud.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hxc.cloud.order.entity.OrderInfo;
import com.hxc.cloud.order.mapper.OrderInfoMapper;
import com.hxc.cloud.order.service.IOrderInfoService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author huangxincheng
 * @since 2019-03-20
 */
@Service
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoMapper, OrderInfo> implements IOrderInfoService {

}
