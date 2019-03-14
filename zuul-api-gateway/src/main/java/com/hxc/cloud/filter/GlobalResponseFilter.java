package com.hxc.cloud.filter;

import com.alibaba.fastjson.JSON;
import com.hxc.cloud.common.response.AppResponse;
import com.hxc.cloud.filter.constants.IFilterConstants;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * Author: huangxincheng
 * <p>
 * <p>
 *     全局响应过滤器
 **/
//@Component
public class GlobalResponseFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return FilterConstants.POST_TYPE;
    }

    @Override
    public int filterOrder() {
        return IFilterConstants.POST_GLOBAL_RESPONSE_FILTER_ORDER;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        InputStream stream = ctx.getResponseDataStream();
        try {
            if (stream != null && ctx.getResponseStatusCode() == HttpStatus.OK.value()) {
                String data = StreamUtils.copyToString(stream, Charset.forName("UTF-8"));
                AppResponse appResponse = JSON.parseObject(data, AppResponse.class);
                if (appResponse.getCode() != null && appResponse.getMsg() != null && appResponse.getData() != null) {
                    ctx.setResponseBody(data);
                } else {
                    ctx.setResponseBody(JSON.toJSONString(AppResponse.ok(data)));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
