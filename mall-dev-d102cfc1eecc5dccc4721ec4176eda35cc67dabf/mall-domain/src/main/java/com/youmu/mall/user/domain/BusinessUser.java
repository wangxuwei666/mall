/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.user.domain;

import com.youmu.mall.base.domain.BaseDomain;
import com.youmu.mall.bus.domain.Business;

/**
 * 商户用户.
 * @author zh
 * @version $Id: BusinessUser.java, v 0.1 2017年2月27日 下午6:51:28 zh Exp $
 */
@SuppressWarnings("serial")
public class BusinessUser extends BaseDomain {
    
    /** 账号 */
    private String account;
    
    /** 账号 */
    private String username;
    
    /** 账号 */
    private String password;
    
    /** 状态 0 正常 -1禁用*/
    private Integer status;
    
    /** 商户账户对应的商户 */
    private Business business;
    
    /** 商户用户的类型 1 普通商户管理 2 银行核销人员 3 后台管理人员 */
    private Integer type;
    
    /** 是否是装修类型的商户用户  */
    private Boolean isDCR;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getUsername() {
        return username;
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

    public Business getBusiness() {
        return business;
    }

    public void setBusiness(Business business) {
        this.business = business;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

        /**
         * Getter method for property <tt>isDCR</tt>.
         * 
         * @return property value of isDCR
         */
    public Boolean getIsDCR() {
        return isDCR;
    }

        /**
         * Setter method for property <tt>isDCR</tt>.
         * 
         * @param isDCR value to be assigned to property isDCR
         */
    public void setIsDCR(Boolean isDCR) {
        this.isDCR = isDCR;
    }
    
    
}
