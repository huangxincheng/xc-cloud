package com.hxc.cloud.fallback;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Author: huangxincheng
 * <p>
 * <p>
 *     自定义Zuul回退机制处理器。
 **/
@Slf4j
@Component
public class GatewayFallback implements FallbackProvider {
    @Override
    public String getRoute() {
        //api服务id，如果需要所有调用都支持回退，则return "*"或return null
        //        return "spring-cloud-user";
        return "*";
    }

    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        log.error("GatewayFallback fallbackResponse", cause);
        if (cause instanceof RuntimeException) {
            return this.getClientHttpResponse(cause.getMessage());
        }
        return this.getClientHttpResponse("系统繁忙,请稍后再试.");
    }

    private ClientHttpResponse getClientHttpResponse(final String msg) {
        return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode() throws IOException {
                return HttpStatus.OK;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return HttpStatus.OK.value();
            }

            @Override
            public String getStatusText() throws IOException {
                return HttpStatus.OK.getReasonPhrase();
            }

            @Override
            public void close() {

            }

            @Override
            public InputStream getBody() throws IOException {
//                return new ByteArrayInputStream("{\"code\":-3,\"msg\":\"系统繁忙,请稍后再试.\"}".getBytes());
                return new ByteArrayInputStream(("{\"code\":-3,\"msg\":" +msg + "}").getBytes());
            }

            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
                return headers;
            }
        };
    }
}
