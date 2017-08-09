/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.sys.domain;

import java.util.List;

import com.youmu.mall.base.domain.BaseDomain;

/**
 * 系统角色.
 * @author zh
 * @version $Id: SysRole.java, v 0.1 2017年3月14日 上午10:21:27 zh Exp $
 */
@SuppressWarnings("serial")
public class SysRole extends BaseDomain {
    
    /** 角色名称  */
    private String name;
    
    /** 菜单 */
    private List<SysMenu> menus;

    /**
     * Getter method for property <tt>name</tt>.
     * 
     * @return property value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter method for property <tt>name</tt>.
     * 
     * @param name value to be assigned to property name
     */
    public void setName(String name) {
        this.name = name;
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

}
