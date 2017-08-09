/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.sys.vo;

import java.util.List;

/**
 * 系统角色修改的视图对象.
 * @author zh
 * @version $Id: SysRoleUpdateVo.java, v 0.1 2017年3月21日 上午11:26:23 zh Exp $
 */
public class SysRoleUpdateVo {
    
    private Long id;
    
    private String name;
    
    private List<SysMenuListVo> menus;

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
    public List<SysMenuListVo> getMenus() {
        return menus;
    }

    /**
     * Setter method for property <tt>menus</tt>.
     * 
     * @param menus value to be assigned to property menus
     */
    public void setMenus(List<SysMenuListVo> menus) {
        this.menus = menus;
    }
    
}
