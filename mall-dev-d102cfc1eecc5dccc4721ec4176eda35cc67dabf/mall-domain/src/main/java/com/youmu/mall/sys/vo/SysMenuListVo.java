/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.sys.vo;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 系统菜单的类别对象.
 * @author zh
 * @version $Id: SysMenuListVo.java, v 0.1 2017年3月15日 上午11:36:18 zh Exp $
 */
public class SysMenuListVo {
    
    /** 菜单的id  */
    private Long id;
    
    /** 菜单的名称 */
    private String name;
    
    /** 父菜单的id */
    @JsonIgnore
    private Long parentId;
    
    /** 子菜单 */
    private List<SysMenuListVo> children = new ArrayList<>();

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
         * Getter method for property <tt>parentId</tt>.
         * 
         * @return property value of parentId
         */
    public Long getParentId() {
        return parentId;
    }

        /**
         * Setter method for property <tt>parentId</tt>.
         * 
         * @param parentId value to be assigned to property parentId
         */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

            /**
             * Getter method for property <tt>children</tt>.
             * 
             * @return property value of children
             */
        public List<SysMenuListVo> getChildren() {
            return children;
        }

            /**
             * Setter method for property <tt>children</tt>.
             * 
             * @param children value to be assigned to property children
             */
        public void setChildren(List<SysMenuListVo> children) {
            this.children = children;
        }
    
}
