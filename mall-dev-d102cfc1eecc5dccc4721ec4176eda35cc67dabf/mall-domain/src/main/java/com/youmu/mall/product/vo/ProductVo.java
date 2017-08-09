/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.product.vo;

import com.youmu.common.upload.utils.ImageUploadUtils;
import com.youmu.mall.base.domain.BaseDomain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @author yujiahao
 * @version $Id: ProductVo.java, v 0.1 2017年2月26日 下午5:10:06 yujiahao Exp $
 */
@ApiModel(value = "用户端-（根据分类展示）商品信息")
public class ProductVo extends BaseDomain{
    
    @ApiModelProperty(value="商品名称")
    private String name;
    
    @ApiModelProperty(value="商品缩略图")
    private String thumbnail;
    
    @ApiModelProperty(value="原价")
    private String oldPrice;
    
    @ApiModelProperty(value="促销价")
    private String promotionPrice;
    
    @ApiModelProperty(value="活动价")
    private String bargainPrice;
    
    @ApiModelProperty(value="库存")
    private Integer total;
    
    @ApiModelProperty(value="摘要")
    private String digest;

    @ApiModelProperty(value="活动组类别（1秒杀，2特价）")
    private Integer groupType;
    
    @ApiModelProperty(value="活动名称")
    private String bargainName;
    
    @ApiModelProperty(value="分类")
    private int category;
    
    public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public String getBargainName() {
        return bargainName;
    }

    public void setBargainName(String bargainName) {
        this.bargainName = bargainName;
    }

    public Integer getGroupType() {
        return groupType;
    }

    public void setGroupType(Integer groupType) {
        this.groupType = groupType;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThumbnail() {
        return ImageUploadUtils.fillPath(thumbnail);
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(String oldPrice) {
        this.oldPrice = oldPrice;
    }

    public String getPromotionPrice() {
        return promotionPrice;
    }

    public void setPromotionPrice(String promotionPrice) {
        this.promotionPrice = promotionPrice;
    }

    public String getBargainPrice() {
        return bargainPrice;
    }

    public void setBargainPrice(String bargainPrice) {
        this.bargainPrice = bargainPrice;
    }
    
    
}
