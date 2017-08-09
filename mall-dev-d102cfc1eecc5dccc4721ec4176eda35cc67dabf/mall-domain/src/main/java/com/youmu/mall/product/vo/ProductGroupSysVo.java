/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.product.vo;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.youmu.common.upload.utils.ImageUploadUtils;
import com.youmu.mall.base.domain.BaseDomain;

/**
 * 管理后台-商品组
 * @author yujiahao
 * @version $Id: ProductGroupSysVo.java, v 0.1 2017年3月7日 上午10:50:12 yujiahao Exp $
 */
public class ProductGroupSysVo extends BaseDomain{
    /**商品组名称 */
    private String name;
    
    /**商品组内剩余商品总数量 */
    private Integer restQuantity;
    
    /**商品组活动开始时间 */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date gmtStart;
    
    /**商品组活动结束时间 */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date gmtEnd;
    
    
    /**商品组图片Banner */
    private String groupImg;
    
    

    public String getGroupImg() {
        return ImageUploadUtils.fillPath(groupImg);
    }

    public void setGroupImg(String groupImg) {
        this.groupImg = groupImg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRestQuantity() {
        return restQuantity;
    }

    public void setRestQuantity(Integer restQuantity) {
        this.restQuantity = restQuantity;
    }

    public Date getGmtStart() {
        return gmtStart;
    }

    public void setGmtStart(Date gmtStart) {
        this.gmtStart = gmtStart;
    }

    public Date getGmtEnd() {
        return gmtEnd;
    }

    public void setGmtEnd(Date gmtEnd) {
        this.gmtEnd = gmtEnd;
    }

    
    
    
}
