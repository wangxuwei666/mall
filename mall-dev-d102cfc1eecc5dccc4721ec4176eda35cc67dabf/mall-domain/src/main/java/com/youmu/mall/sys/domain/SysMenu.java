/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.sys.domain;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.youmu.mall.base.domain.BaseDomain;

/**
 * 系统菜单.
 * @author zh
 * @version $Id: SysMenu.java, v 0.1 2017年3月14日 上午10:22:40 zh Exp $
 */
@SuppressWarnings("serial")
public class SysMenu extends BaseDomain {
    
    /** 用户名 */
    private String name;
    
    /** url地址 */
    private String url;
    
    /** 父菜单 */
    @JsonIgnore
    private SysMenu parent;
    
    /** 菜单在前台页面的位置 */
    private Integer index;
    
    /** 子菜单 */
    private List<SysMenu> children = new ArrayList<>();

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
     * Getter method for property <tt>url</tt>.
     * 
     * @return property value of url
     */
    public String getUrl() {
        return url;
    }

    /**
     * Setter method for property <tt>url</tt>.
     * 
     * @param url value to be assigned to property url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Getter method for property <tt>children</tt>.
     * 
     * @return property value of children
     */
    public List<SysMenu> getChildren() {
        return children;
    }

    /**
     * Setter method for property <tt>children</tt>.
     * 
     * @param children value to be assigned to property children
     */
    public void setChildren(List<SysMenu> children) {
        this.children = children;
    }

        /**
         * Getter method for property <tt>parent</tt>.
         * 
         * @return property value of parent
         */
    public SysMenu getParent() {
        return parent;
    }

        /**
         * Setter method for property <tt>parent</tt>.
         * 
         * @param parent value to be assigned to property parent
         */
    public void setParent(SysMenu parent) {
        this.parent = parent;
    }

            /**
             * Getter method for property <tt>index</tt>.
             * 
             * @return property value of index
             */
        public Integer getIndex() {
            return index;
        }

            /**
             * Setter method for property <tt>index</tt>.
             * 
             * @param index value to be assigned to property index
             */
        public void setIndex(Integer index) {
            this.index = index;
        }
}
