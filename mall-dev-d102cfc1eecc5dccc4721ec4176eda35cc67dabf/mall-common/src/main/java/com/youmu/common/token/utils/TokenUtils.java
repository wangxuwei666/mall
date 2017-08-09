/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.common.token.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;

import com.youmu.common.web.utils.CookieUtils;

/**
 * 生成token的工具类.
 * 
 * @author zh
 * @version $Id: TokenUtils.java, v 0.1 2017年2月25日 下午5:56:45 zh Exp $
 */
public class TokenUtils {
    
    private static Logger logger = LoggerFactory.getLogger(TokenUtils.class);
    
    /** 将认证信息放在cookie中的名称 */
    public static final String TOKEN_IN_COOKIE = "token";
    
    public static final String TOKEN_PREFIX = "Bearer ";
    
    public static final String SHOP_TOKEN_SUFFIX = "shop";
    
    public static final String SYS_TOKEN_SUFFIX = "sys";
    
    public static final String BUS_FRONT_TOKEN_SUFFIX = "busf";
    
    public static final String BUS_TOKEN_SUFFIX = "bus";
    
    
    /***
     * 生成toke
     * 
     * @param uid 用户id
     * @param millis 过期毫秒
     * @return
     */
    public static String createToken(String uid, long millis) {
        return JwtUtils.createToken(uid, millis);
    }
    
    /***
     * 生成toke
     *
     * @param uid 用户id
     * @param millis 过期毫秒
     * @return
     */
    public static String getUidInToken(String token) {
        return JwtUtils.validateToken(token);
    }

    /**
     * 从请求头中获取token信息.
     * @param request
     * @return
     */
    public static String getTokenInHeader(HttpServletRequest request) {
        try {
            String header = request.getHeader(HttpHeaders.AUTHORIZATION);
            if(StringUtils.isNotBlank(header)) {
                return header.substring(TOKEN_PREFIX.length()).trim();
            }
        } catch (Exception e) {
            logger.error("从请求头中获取token错误", e);
        }
        return null;
    }
    
    /**
     * 从cookie中获取token信息.
     * @param request
     * @return
     */
    public static String getTokenInCookie(HttpServletRequest request, String tokenSuffix) {
        try {
            String token = CookieUtils.getCookieValue(request, TOKEN_IN_COOKIE + tokenSuffix);
            if(StringUtils.isNotBlank(token)) {
                return token;
            }
        } catch (Exception e) {
            logger.error("从请求头中获取token错误", e);
        }
        return null;
    }
    
    /**
     * 在cookie中获设置token信息.
     */
    public static void setTokenInCookie(HttpServletResponse response, String tokenSuffix,  String token) {
        CookieUtils.setCookie(response, TOKEN_IN_COOKIE + tokenSuffix, token);
    }

    /**
     * 
     * @param response
     */
    public static void removeTokenInCookie(HttpServletResponse response, String tokenSuffix) {
        CookieUtils.removeCookie(response, TOKEN_IN_COOKIE + tokenSuffix);
    }
    
}
