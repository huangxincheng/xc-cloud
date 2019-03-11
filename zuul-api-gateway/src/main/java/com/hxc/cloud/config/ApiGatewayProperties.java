package com.hxc.cloud.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Author: huangxincheng
 * <p>
 * <p>
 **/
@Component
@ConfigurationProperties(prefix = "api.gateway")
@EnableAutoConfiguration
public class ApiGatewayProperties {

    private List<ApiService> uris;

    public boolean isAuth(String path) {
        List<ApiService> list = uris.stream()
                .filter(apiService -> { return path.startsWith(apiService.path); })
                .collect(Collectors.toList());
        if (list != null && list.size() > 0) {
            return list.get(0).isAuth();
        }
        return false;
    }

    public boolean isRateLimiter(String path) {
        List<ApiService> list = uris.stream()
                .filter(apiService -> { return path.startsWith(apiService.path); })
                .collect(Collectors.toList());
        if (list != null && list.size() > 0) {
            return list.get(0).getRateNum() > 0 ? true : false;
        }
        return false;
    }

    public int getRateNum(String path) {
        List<ApiService> list = uris.stream()
                .filter(apiService -> { return path.startsWith(apiService.path); })
                .collect(Collectors.toList());
        if (list != null && list.size() > 0) {
            return list.get(0).getRateNum();
        }
        return 0;
    }

    public int getRateSecond(String path) {
        List<ApiService> list = uris.stream()
                .filter(apiService -> { return path.startsWith(apiService.path); })
                .collect(Collectors.toList());
        if (list != null && list.size() > 0) {
            return list.get(0).getRateSecond();
        }
        return 1;
    }


    public static class ApiService {

        /**
         * 请求uri
         */
        private String path;

        /**
         * 是否需要登录认证
         */
        private boolean auth = false;

        /**
         * 限流数
         */
        private int rateNum;

        /**
         * 限流时间 单位秒
         */
        private int rateSecond = 1;

        public boolean isAuth() {
            return auth;
        }

        public void setAuth(boolean auth) {
            this.auth = auth;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public int getRateNum() {
            return rateNum;
        }

        public void setRateNum(int rateNum) {
            this.rateNum = rateNum;
        }

        public int getRateSecond() {
            return rateSecond;
        }

        public void setRateSecond(int rateSecond) {
            this.rateSecond = rateSecond;
        }
    }

    public List<ApiService> getUris() {
        return uris;
    }

    public void setUris(List<ApiService> uris) {
        this.uris = uris;
    }
}
