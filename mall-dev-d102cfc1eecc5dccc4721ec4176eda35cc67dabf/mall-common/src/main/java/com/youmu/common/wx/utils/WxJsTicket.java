/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.common.wx.utils;

/**
 * 
 * @author zh
 * @version $Id: WxJsTicket.java, v 0.1 2017年3月9日 上午11:30:55 zh Exp $
 */
public class WxJsTicket {
    
    /** ticket */
    private String ticket;
    
    /** 过期时间 */
    private int expires_in;
    
    /** 过期时间戳 */
    private long expireTimeStamp;

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    public long getExpireTimeStamp() {
        return expireTimeStamp;
    }

    public void setExpireTimeStamp(long expireTimeStamp) {
        this.expireTimeStamp = expireTimeStamp;
    }
}
