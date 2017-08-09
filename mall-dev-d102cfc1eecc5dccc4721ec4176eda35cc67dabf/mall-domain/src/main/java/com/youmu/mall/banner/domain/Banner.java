/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.banner.domain;

import com.youmu.mall.base.domain.BaseDomain;

/**
 * Banner
 * @author yujiahao
 * @version $Id: Banner.java, v 0.1 2017年3月6日 下午7:07:33 yujiahao Exp $
 */
public class Banner extends BaseDomain{
    
    /**banner的类型（0-用户端，1-其他）*/
    private Integer type; 
    
    /**图片地址*/
    private String image;
    
    /**链接地址*/
    private String linkurl;
    
    /**排序*/
    private Integer orders;
    
    /**名称*/
    private String name;
    
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLinkurl() {
        return linkurl;
    }

    public void setLinkurl(String linkurl) {
        this.linkurl = linkurl;
    }

    public Integer getOrders() {
        return orders;
    }

    public void setOrders(Integer orders) {
        this.orders = orders;
    }
    
    
    
}
