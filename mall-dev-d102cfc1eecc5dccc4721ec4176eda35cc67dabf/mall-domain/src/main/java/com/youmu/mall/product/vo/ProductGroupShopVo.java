/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.product.vo;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.youmu.common.upload.utils.ImageUploadUtils;

/**
 * 用户端 - 活动商品组数据
 * @author yujiahao
 * @version $Id: ProductGroupShopVo.java, v 0.1 2017年3月21日 下午4:58:00 yujiahao Exp $
 */
public class ProductGroupShopVo {
    private Long id;
    
    /**商品组图片*/
    private String groupImg;
    
    /**商品组活动开始时间 */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date gmtStart;
    
    /**商品组活动结束时间 */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date gmtEnd;
    
    /**系统时间*/
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date currentDate;
    
    /**商品组-商品项*/
    private List<ProductGroupDetailShopVo> detailVos;
    
    public Date getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getGroupImg() {
        return ImageUploadUtils.fillPath(groupImg);
    }

    public void setGroupImg(String groupImg) {
        this.groupImg = groupImg;
    }

    public List<ProductGroupDetailShopVo> getDetailVos() {
        return detailVos;
    }

    public void setDetailVos(List<ProductGroupDetailShopVo> detailVos) {
        this.detailVos = detailVos;
    }
    
    
}
