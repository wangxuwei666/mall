/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.product.vo;

import java.util.List;

import com.youmu.common.upload.utils.ImageUploadUtils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @author yujiahao
 * @version $Id: ProductSysVo.java, v 0.1 2017年3月1日 下午3:53:20 yujiahao Exp $
 */
@ApiModel(value = "管理后台-商品")
public class ProductSysVo {
    @ApiModelProperty(value = "id")
    private long id;
    
    @ApiModelProperty(value = "商户类型id")
    private long businessTypeId;
    
    @ApiModelProperty(value = "商户id")
    private long businessId;
    
    @ApiModelProperty(value = "商户名称")
    private String businessName;
    
    @ApiModelProperty(value="商品名称")
    private String name;
    
    @ApiModelProperty(value="商户类别")
    private String businessTypeName;
    
    @ApiModelProperty(value="原价")
    private String oldPrice;
    
    @ApiModelProperty(value="促销价")
    private String promotionPrice;
    
    @ApiModelProperty(value="库存")
    private Integer total;
    
    @ApiModelProperty(value="商品组状态（0初始  1已上架  2已下架 ）")
    private Integer status;
    
    @ApiModelProperty(value="商品缩略图")
    private String thumbnail;
    
    @ApiModelProperty(value="摘要")
    private String digest;
    
    @ApiModelProperty(value="介绍")
    private String intro;
    
    @ApiModelProperty(value = "前端展示的商品图片组")
    private List<String> images;
    
    @ApiModelProperty(value = "保存时接收的图片")
    private List<String> imagesUrls;

    /**
     * 分类id
     */
    private Integer categoryId;
    
    public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    /**返回前端的图片*/
    public List<String> getImages() {
        return ImageUploadUtils.fillPath(imagesUrls);
    }
    
    /**图片处理*/
    public List<String> getImageUrls(){
        return imagesUrls;
    }
    /**图片处理*/
    public void setImagesUrls(List<String> imagesUrls) {
        this.imagesUrls = imagesUrls;
    }
    
    
    public String getThumbnail() {
        return ImageUploadUtils.fillPath(thumbnail);
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public long getBusinessTypeId() {
        return businessTypeId;
    }

    public void setBusinessTypeId(long businessTypeId) {
        this.businessTypeId = businessTypeId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBusinessTypeName() {
        return businessTypeName;
    }

    public void setBusinessTypeName(String businessTypeName) {
        this.businessTypeName = businessTypeName;
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

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(long businessId) {
        this.businessId = businessId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
}
