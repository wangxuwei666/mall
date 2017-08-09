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
 * 管理后台-商品活动组-编辑页面对象
 * @author yujiahao
 * @version $Id: ProductGroupEditVo.java, v 0.1 2017年3月7日 下午8:19:31 yujiahao Exp $
 */
public class ProductGroupEditVo {
    private Long id;
    
    /**商品组名称 */
    private String name;
    
    /**用户限制购买数量 */
    private Integer limitQuantity;
    
    /**商品组活动开始时间 */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private String gmtStart;
    
    /**商品组活动结束时间 */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private String gmtEnd;
    
    /**商品组图片Banner */
    private String groupImg;
    
    /**商品组明细*/
    private List<ProductGroupDetailSysVo> detailVos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLimitQuantity() {
        return limitQuantity;
    }

    public void setLimitQuantity(Integer limitQuantity) {
        this.limitQuantity = limitQuantity;
    }

    public String getGmtStart() {
        return gmtStart;
    }

    public void setGmtStart(String gmtStart) {
        this.gmtStart = gmtStart;
    }

    public String getGmtEnd() {
        return gmtEnd;
    }

    public void setGmtEnd(String gmtEnd) {
        this.gmtEnd = gmtEnd;
    }

    public String getGroupImg() {
        return ImageUploadUtils.fillPath(groupImg);
    }

    public void setGroupImg(String groupImg) {
        this.groupImg = groupImg;
    }

    public List<ProductGroupDetailSysVo> getDetailVos() {
        return detailVos;
    }

    public void setDetailVos(List<ProductGroupDetailSysVo> detailVos) {
        this.detailVos = detailVos;
    }
    
    
    
    
}
