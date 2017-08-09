/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.common.context;

/**
 * 全局的一些常量.
 * 
 * @author zh
 * @version $Id: GlobalContext.java, v 0.1 2017年2月25日 下午4:53:58 zh Exp $
 */
public class TokenContext {
    
    /**  token过期时间  7 day */
    public static int TOKEN_EXPIRE = 7 * 24 * 60 * 60 * 1000;
    
    /** session expired half hour */
    public static int SESSION_EXPIRE = 30 * 60 * 1000;
}
