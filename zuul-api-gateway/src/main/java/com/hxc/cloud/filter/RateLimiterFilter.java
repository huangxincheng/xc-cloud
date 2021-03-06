package com.hxc.cloud.filter;

import com.alibaba.fastjson.JSON;
import com.hxc.cloud.RateLimitUtil;
import com.hxc.cloud.common.response.AppCodeEnum;
import com.hxc.cloud.common.response.AppResponse;
import com.hxc.cloud.config.ApiGatewayProperties;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import static com.hxc.cloud.filter.constants.IFilterConstants.FILTER_LIMIT_KEY_PRE;
import static com.hxc.cloud.filter.constants.IFilterConstants.PRE_RATE_LIMITER_FILTER_ORDER;

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
        return PRE_RATE_LIMITER_FILTER_ORDER;
    }

    @Override
    public boolean shouldFilter() {
        String pass = RequestContext.getCurrentContext().getRequest().getHeader("passLimiter");
        if (pass == null) {
            pass = RequestContext.getCurrentContext().getRequest().getParameter("passLimiter");
        }
        if ("true".equalsIgnoreCase(pass)) {
            return false;
        }
        return apiGatewayProperties.isRateLimiter(RequestContext.getCurrentContext().getRequest().getRequestURI());
    }

    @Override
    public Object run() throws ZuulException {
        ApiGatewayProperties.ApiService apiService = apiGatewayProperties.getApiService(RequestContext.getCurrentContext().getRequest().getRequestURI());
        int rateNum = apiService.getRateNum();
        int rateSecond = apiService.getRateSecond();
        boolean rateLimiter = RateLimitUtil.isRateLimiter(FILTER_LIMIT_KEY_PRE + apiService.getPath(), rateNum, rateSecond);
        if (rateLimiter) {
            //过滤该请求，不往下级服务去转发请求，到此结束
            RequestContext.getCurrentContext().setSendZuulResponse(false);
            RequestContext.getCurrentContext().setResponseStatusCode(HttpStatus.OK.value());
            RequestContext.getCurrentContext().getResponse().setCharacterEncoding("UTF-8");
//            RequestContext.getCurrentContext().getResponse().setHeader("content-type", "application/json;charset=UTF-8");
            RequestContext.getCurrentContext().getResponse().setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            AppResponse appResponse = AppResponse.fail(AppCodeEnum.RATE_LIMITER_FAIL);
            RequestContext.getCurrentContext().setResponseBody(JSON.toJSONString(appResponse));
        }
        return null;
    }
}
