/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.user.vo;

/**
 * 商户用户列表对象.
 * @author zh
 * @version $Id: BusinessUserListVo.java, v 0.1 2017年2月28日 下午12:39:32 zh Exp $
 */
public class BusinessUserListVo {
    
    /** 账户id */
    private Long id;
    
    /** 账户 */
    private String account;
    
    /** 商户名称 */
    private String businessName;
    
    /** 商户类型名称 */
    private String typeName;
    
    /** 商户的用户类型  */
    private String userType;
    
    /** 使用类型 */
    private String useType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    /**
     * Getter method for property <tt>userType</tt>.
     * 
     * @return property value of userType
     */
    public String getUserType() {
        return userType;
    }

    /**
     * Setter method for property <tt>userType</tt>.
     * 
     * @param userType value to be assigned to property userType
     */
    public void setUserType(String userType) {
        this.userType = userType;
    }

    /**
     * Getter method for property <tt>useType</tt>.
     * 
     * @return property value of useType
     */
    public String getUseType() {
        return useType;
    }

    /**
     * Setter method for property <tt>useType</tt>.
     * 
     * @param useType value to be assigned to property useType
     */
    public void setUseType(String useType) {
        this.useType = useType;
    }
}
