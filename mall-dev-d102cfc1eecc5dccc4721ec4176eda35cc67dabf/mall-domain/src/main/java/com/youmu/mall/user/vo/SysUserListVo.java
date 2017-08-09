/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.user.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.youmu.mall.sys.domain.SysRole;

/**
 * 系统用户列表對象
 * 
 * @author zh
 * @version $Id: SysUser.java, v 0.1 2017年2月27日 下午7:39:30 zh Exp $
 */
public class SysUserListVo {
    
    /** 系統用戶的id */
    private Long id;
    
    /** 用户名 */
    private String username;
    
    /** 手机号 */
    private String account;
    
    /** 密码 */
    private String password;
    
    /** 系统角色 */
    private SysRole role;
    
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date gmtCreate;
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

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

        /**
         * Getter method for property <tt>role</tt>.
         * 
         * @return property value of role
         */
    public SysRole getRole() {
        return role;
    }

        /**
         * Setter method for property <tt>role</tt>.
         * 
         * @param role value to be assigned to property role
         */
    public void setRole(SysRole role) {
        this.role = role;
    }

        /**
         * Getter method for property <tt>id</tt>.
         * 
         * @return property value of id
         */
        public Long getId() {
            return id;
        }

        /**
         * Setter method for property <tt>id</tt>.
         * 
         * @param id value to be assigned to property id
         */
        public void setId(Long id) {
            this.id = id;
        }

        /**
         * Getter method for property <tt>gmtCreate</tt>.
         * 
         * @return property value of gmtCreate
         */
        public Date getGmtCreate() {
            return gmtCreate;
        }

        /**
         * Setter method for property <tt>gmtCreate</tt>.
         * 
         * @param gmtCreate value to be assigned to property gmtCreate
         */
        public void setGmtCreate(Date gmtCreate) {
            this.gmtCreate = gmtCreate;
        }
}
