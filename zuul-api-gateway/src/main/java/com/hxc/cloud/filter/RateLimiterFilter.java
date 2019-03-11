package com.hxc.cloud.filter;

import com.hxc.cloud.RateLimitUtil;
import com.hxc.cloud.config.ApiGatewayProperties;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.bouncycastle.cert.ocsp.Req;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import static com.hxc.cloud.filter.constants.IFilterConstants.FILTER_LIMIT_KEY_PRE;
import static com.hxc.cloud.filter.constants.IFilterConstants.RATE_LIMITER_FILTER_ORDER;

/**
 * Author: huangxincheng
 * <p>
 * <p>
 *     限流过滤器
 **/
@Component
public class RateLimiterFilter extends ZuulFilter {

    @Autowired
    private ApiGatewayProperties apiGatewayProperties;

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return RATE_LIMITER_FILTER_ORDER;
    }

    @Override
    public boolean shouldFilter() {
        return apiGatewayProperties.isRateLimiter(RequestContext.getCurrentContext().getRequest().getRequestURI());
    }

    @Override
    public Object run() throws ZuulException {
        int rateNum = apiGatewayProperties.getRateNum(RequestContext.getCurrentContext().getRequest().getRequestURI());
        int rateSecond = apiGatewayProperties.getRateSecond(RequestContext.getCurrentContext().getRequest().getRequestURI());
        boolean rateLimiter = RateLimitUtil.isRateLimiter(FILTER_LIMIT_KEY_PRE + RequestContext.getCurrentContext().getRequest().getRequestURI(), rateNum, rateSecond);
        if (rateLimiter) {
            //过滤该请求，不往下级服务去转发请求，到此结束
            RequestContext.getCurrentContext().setSendZuulResponse(false);
            RequestContext.getCurrentContext().setResponseStatusCode(HttpStatus.OK.value());
            RequestContext.getCurrentContext().getResponse().setCharacterEncoding("UTF-8");
            RequestContext.getCurrentContext().getResponse().setHeader("content-type", "application/json;charset=UTF-8");
            RequestContext.getCurrentContext().setResponseBody("{\"code\":-2,\"msg\":\"请求太多,您已被挤出.\"}");
        }
        return null;
    }
}
