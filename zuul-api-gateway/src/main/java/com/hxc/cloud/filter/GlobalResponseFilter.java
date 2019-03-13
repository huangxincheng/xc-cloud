package com.hxc.cloud.filter;

import com.hxc.cloud.filter.constants.IFilterConstants;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
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
@Component
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
            if (stream != null) {
                String data = StreamUtils.copyToString(stream, Charset.forName("UTF-8"));
                ctx.setResponseBody("{\"code\":0,\"msg\":\"SUCCESS\",\"data\":"+data+"}");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
