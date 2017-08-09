/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.banner.vo;

import org.apache.commons.lang3.StringUtils;

import com.youmu.common.cofig.utils.ConfigUtils;
import com.youmu.common.upload.utils.ImageUploadUtils;

/**
 * 
 * @author yujiahao
 * @version $Id: BannerPCVo.java, v 0.1 2017年3月6日 下午7:11:01 yujiahao Exp $
 */
public class BannerShopVo {
    
    /**图片地址*/
    private String image;
    
    /**链接地址*/
    private String linkurl;
    
    /**banner类型*/
    private Integer type;
    
    
    
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
