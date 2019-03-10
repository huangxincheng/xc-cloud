package com.hxc.cloud;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.apache.commons.lang.exception.ExceptionUtils;

/**
 * Author: huangxincheng
 * <p>
 * <p>
 **/
public class JwtUtil {

    private static final String pre = "pass:";

    public static String getToken(Long userid) {
        String token = JWT.create().withAudience(userid.toString()).sign(Algorithm.HMAC256(pre + userid));
        return token;
    }

    public static String getUserId(String token) {
        try {
            String userId = JWT.decode(token).getAudience().get(0);
            return userId;
        } catch (Exception e) {
            System.out.println("解密失败 token = " + token + "e = " + ExceptionUtils.getMessage(e));
            return null;
        }
    }
}
