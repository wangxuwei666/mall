/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.common.wx.utils;

/**
 * 网页的access_token
 * @author zh
 * @version $Id: WxAuthAccessToken.java, v 0.1 2017年3月10日 上午11:02:44 zh Exp $
 */
public class WxAuthAccessToken extends WxAccessToken{
    
    /** 刷新的token */
    private String refresh_token;
    
    /** 用户的openid */
    private String openid;

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }
}
