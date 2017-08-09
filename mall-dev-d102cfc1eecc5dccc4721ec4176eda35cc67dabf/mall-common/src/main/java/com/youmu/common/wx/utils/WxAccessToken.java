/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.common.wx.utils;

/**
 * 微信accessToken
 * @author zh
 * @version $Id: WxAccessToken.java, v 0.1 2017年3月9日 上午10:40:02 zh Exp $
 */
public class WxAccessToken {
    
    /** token */
    private String access_token;
    
    /** 过期时间 */
    private int expires_in;
    
    /** 过期时间戳 */
    private long expireTimeStamp;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
        this.expireTimeStamp = System.currentTimeMillis() + expires_in * 1000 - 2 * 60 * 1000;  
    }

    public long getExpireTimeStamp() {
        return expireTimeStamp;
    }

    public void setExpireTimeStamp(long expireTimeStamp) {
        this.expireTimeStamp = expireTimeStamp;
    }
}
