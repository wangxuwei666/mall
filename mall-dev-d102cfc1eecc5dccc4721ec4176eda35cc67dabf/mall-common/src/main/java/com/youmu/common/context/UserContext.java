/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.common.context;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * web上下文.
 * 
 * @author zh
 * @version $Id: UserContext.java, v 0.1 2017年2月26日 上午11:55:22 zh Exp $
 */
public class UserContext {
    
    /** 用户跳转的页面 */
    public static final String TO_URL_IN_SESSION = "TO_URL_IN_SESSION";
    
    /**
     *  获取当前请求
     */
    public static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
    }
    
    /**
     *  获取当前请求
     */
    public static HttpSession getSession() {
        return getRequest().getSession();
    }
    
    public static Object getAttrInSession(String attrName) {
        return getSession().getAttribute(attrName);
    }
    
    public static void setAttrInSession(String attrName, Object object) {
        getSession().setAttribute(attrName, object);
    }
    
   /**
    *  获取当前用户id
    */
    public static String getUserId() {
        return getRequest().getAttribute("uid").toString();
    }
    
    /**
     *  获取当前用户id
     */
    public static Long getLongUserId() {
        return Long.valueOf(getUserId());
    }
}
