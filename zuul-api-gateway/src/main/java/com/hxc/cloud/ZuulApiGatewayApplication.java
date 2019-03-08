package com.hxc.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * Author: huangxincheng
 * <p>
 * <p>
 **/
@SpringBootApplication
@EnableZuulProxy
//@EnableDiscoveryClient 底层默认已经加了
public class ZuulApiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuulApiGatewayApplication.class, args);
    }
}
