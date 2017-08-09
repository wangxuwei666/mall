/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.user.domain;

import java.util.List;

import com.youmu.mall.base.domain.BaseDomain;
import com.youmu.mall.sys.domain.SysMenu;
import com.youmu.mall.sys.domain.SysRole;

/**
 * 系统用户数据库对象.
 * 
 * @author zh
 * @version $Id: SysUser.java, v 0.1 2017年2月27日 下午7:39:30 zh Exp $
 */
@SuppressWarnings("serial")
public class SysUser extends BaseDomain {
    
    /** 用户名 */
    private String username;
    
    /** 手机号 */
    private String account;
    
    /** 密码 */
    private String password;
    
    /** 系统角色 */
    private SysRole role;
    
    /** 用户的菜单列表 */
    private List<SysMenu> menus;
    
    /** 系统状态 */
    private Integer status;
    
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
     * Getter method for property <tt>menus</tt>.
     * 
     * @return property value of menus
     */
    public List<SysMenu> getMenus() {
        return menus;
    }

    /**
     * Setter method for property <tt>menus</tt>.
     * 
     * @param menus value to be assigned to property menus
     */
    public void setMenus(List<SysMenu> menus) {
        this.menus = menus;
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
             * Getter method for property <tt>status</tt>.
             * 
             * @return property value of status
             */
        public Integer getStatus() {
            return status;
        }

            /**
             * Setter method for property <tt>status</tt>.
             * 
             * @param status value to be assigned to property status
             */
        public void setStatus(Integer status) {
            this.status = status;
        }
}
