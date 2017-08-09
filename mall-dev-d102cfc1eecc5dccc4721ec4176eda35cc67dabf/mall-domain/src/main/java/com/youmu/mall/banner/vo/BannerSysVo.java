/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.banner.vo;

import com.youmu.common.upload.utils.ImageUploadUtils;

/**
 * 管理后台-Banner
 * @author yujiahao
 * @version $Id: BannerMobileVo.java, v 0.1 2017年3月6日 下午7:10:43 yujiahao Exp $
 */
public class BannerSysVo {
    /**id*/
    private Long id;
    
    /**名称*/
    private String name;
    
    /**banner的类型（0-移动端，1-PC端）*/
    private Integer type;
    
    /**图片地址*/
    private String image;
    
    /**链接地址*/
    private String linkurl;
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getImage() {
        return ImageUploadUtils.fillPath(image);
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

    
    
}
