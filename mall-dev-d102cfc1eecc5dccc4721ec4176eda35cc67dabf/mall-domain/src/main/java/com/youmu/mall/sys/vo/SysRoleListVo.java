/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.sys.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 系统角色列表对象.
 * @author zh
 * @version $Id: SysRoleListVo.java, v 0.1 2017年3月15日 下午2:47:15 zh Exp $
 */
public class SysRoleListVo {
    
    /** 角色id */
    private Long id;
    
    /** 角色名称 */
    private String name;
    
    /** 创建时间 */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date gmtCreate;

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
