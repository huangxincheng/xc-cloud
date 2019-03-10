package com.hxc.cloud.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.hxc.cloud.JwtUtil;
import com.hxc.cloud.config.ApiGatewayProperties;
import com.hxc.cloud.filter.constants.IFilterConstants;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.http.protocol.RequestContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * Author: huangxincheng
 * <p>
 * <p>
 *     认证过滤器
 **/
@Component
public class AuthFilter extends ZuulFilter {

    @Autowired
    private ApiGatewayProperties apiGatewayProperties;

    /**
     * 过滤器类型
     * pre：可以在请求被路由之前调用
     * routing： 路由请求时被调用
     * post：在routing和error过滤器之后被调用
     * 处理请求时发生错误时被调用
     * @return
     */
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    /**
     * 过滤器顺序
     * 通过int值来定义过滤器的执行顺序，数值越小优先级越高。
     * @return
     */
    @Override
    public int filterOrder() {
        return IFilterConstants.AUTH_FILTER_ORDER;
    }

    /**
     * 过滤器是否处理
     * 返回一个boolean值来判断该过滤器是否要执行
     * @return
     */
    @Override
    public boolean shouldFilter() {
       return apiGatewayProperties.isAuth(RequestContext.getCurrentContext().getRequest().getRequestURI());
    }

    /**
     * 过滤器处理逻辑
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        String token = RequestContext.getCurrentContext().getRequest().getHeader("token");
        if (token == null) {
            token =  RequestContext.getCurrentContext().getRequest().getParameter("token");
        }
        String passToken = RequestContext.getCurrentContext().getRequest().getHeader("passToken");
        if (passToken == null) {
            passToken = RequestContext.getCurrentContext().getRequest().getParameter("passToken");
        }
        if ("true".equalsIgnoreCase(passToken) || (token != null && JwtUtil.getUserId(token) != null)) {

        } else {
            RequestContext.getCurrentContext().setSendZuulResponse(false);
            RequestContext.getCurrentContext().setResponseStatusCode(HttpStatus.OK.value());
            RequestContext.getCurrentContext().getResponse().setCharacterEncoding("UTF-8");
            RequestContext.getCurrentContext().getResponse().setHeader("content-type", "application/json;charset=UTF-8");
            RequestContext.getCurrentContext().setResponseBody("{\"code\":-1,\"msg\":\"认证失败\"}");
        }
        return null;
    }
}
