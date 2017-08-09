/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.user.vo;

/**
 * 已经登录的商户的商户用户信息.
 * @author zh
 * @version $Id: LoggedBusinessVo.java, v 0.1 2017年2月28日 下午5:14:58 zh Exp $
 */
public class LoggedBusinessUserVo {
    
    /** 商户账户 */
    private String account;
    
    /** 商户名称 */
    private String businessName;
    
    /** 是否通过认证 */
    private Boolean authPass;
    
    /** 是否是装修公司 */
    private Boolean isDCR;
    
    /** 商户类型id */
    private Long businessTypeId;
    
    /** 商户用户类型 */
    private Integer type;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public Boolean getAuthPass() {
        return authPass;
    }

    public void setAuthPass(Boolean authPass) {
        this.authPass = authPass;
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

            /**
             * Getter method for property <tt>businessTypeId</tt>.
             * 
             * @return property value of businessTypeId
             */
        public Long getBusinessTypeId() {
            return businessTypeId;
        }

            /**
             * Setter method for property <tt>businessTypeId</tt>.
             * 
             * @param businessTypeId value to be assigned to property businessTypeId
             */
        public void setBusinessTypeId(Long businessTypeId) {
            this.businessTypeId = businessTypeId;
        }

                /**
                 * Getter method for property <tt>type</tt>.
                 * 
                 * @return property value of type
                 */
            public Integer getType() {
                return type;
            }

                /**
                 * Setter method for property <tt>type</tt>.
                 * 
                 * @param type value to be assigned to property type
                 */
            public void setType(Integer type) {
                this.type = type;
            }
    
}
