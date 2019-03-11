package com.hxc.cloud;

import com.hxc.cloud.config.ApiGatewayProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
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
