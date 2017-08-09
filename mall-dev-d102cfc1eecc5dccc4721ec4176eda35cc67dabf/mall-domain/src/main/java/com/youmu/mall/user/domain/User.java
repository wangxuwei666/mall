/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.user.domain;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.youmu.mall.base.domain.BaseDomain;

/**
 * 普通用户.
 * 
 * @author zh
 * @version $Id: GenUser.java, v 0.1 2017年2月24日 下午3:34:44 zh Exp $
 */
@SuppressWarnings("serial")
public class User extends BaseDomain {
    
    /** 手机号码  */
    private String mobile;
    
    /** 用户名 */
    private String username;
    
    /** 密码 */
    private String password;
    
    /** 状态 */
    private Integer status;
    
    /** 用户头像 */
    private String headIcon;
    
    /** 微信openid */
    private String wxOpenid;
    
    public User() {
        super();
    }
    
    public User(Long id) {
        setId(id);
    }
    
    public String getHeadIcon() {
        return headIcon;
    }

    public void setHeadIcon(String headIcon) {
        this.headIcon = headIcon;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

        /**
         * Getter method for property <tt>wxOpenid</tt>.
         * 
         * @return property value of wxOpenid
         */
    public String getWxOpenid() {
        return wxOpenid;
    }

        /**
         * Setter method for property <tt>wxOpenid</tt>.
         * 
         * @param wxOpenid value to be assigned to property wxOpenid
         */
    public void setWxOpenid(String wxOpenid) {
        this.wxOpenid = wxOpenid;
    }
}
