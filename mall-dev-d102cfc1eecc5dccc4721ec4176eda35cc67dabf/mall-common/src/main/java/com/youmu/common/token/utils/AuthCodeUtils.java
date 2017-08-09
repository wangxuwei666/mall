/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.common.token.utils;

import java.security.SecureRandom;
import java.util.Random;

/**
 * 验证码工具类.
 * 
 * @author zh
 * @version $Id: AuthCodeUtils.java, v 0.1 2017年2月26日 上午10:13:17 zh Exp $
 */
public class AuthCodeUtils {
    
    /**
     *获取普通6位验证码.
     * 
     * @return 6位验证码的随机数字符串
     */
    public static int getCommonAuthCode() {
        return getRandomInt(100000, 999999);
    }
    
    /***
     * 获取  min=<rand<=max的随机数
     * 
     * 
     * @param min 最小值
     * @param max 最大值
     * @return
     */
    public static int getRandomInt(int min, int max) {
        Random random = new SecureRandom();
        return min + random.nextInt(max - min + 1);
    }
}
