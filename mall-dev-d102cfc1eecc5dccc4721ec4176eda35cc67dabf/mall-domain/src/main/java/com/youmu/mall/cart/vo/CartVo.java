/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.cart.vo;

import java.math.BigDecimal;

import com.youmu.common.upload.utils.ImageUploadUtils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @author yujiahao
 * @version $Id: CartVo.java, v 0.1 2017年2月28日 上午11:11:34 yujiahao Exp $
 */
@ApiModel(value = "购物车")
public class CartVo {
    
    @ApiModelProperty(value = "id")
    private long id;
    
    @ApiModelProperty(value = "商品id")
    private String productId;
    
    @ApiModelProperty(value = "商品原价")
    private BigDecimal oldPrice;
    
    @ApiModelProperty(value = "商品促销价")
    private BigDecimal promotionPrice;
    
    @ApiModelProperty(value = "商品活动价")
    private BigDecimal bargainPrice;
    
    @ApiModelProperty(value = "商品图片")
    private String productImg;
    
    @ApiModelProperty(value = "商品名称")
    private String productName;
    
    @ApiModelProperty(value = "商品数量")
    private Integer quantity;
    
    @ApiModelProperty(value = "商家id")
    private Long businessId;
    
    @ApiModelProperty(value = "摘要")
    private String digest;

    @ApiModelProperty(value = "分类名字")
    private String categoryName;
    @ApiModelProperty(value = "分类id")
    private String categoryId;

    @ApiModelProperty(value = "分类详情id")
    private String specificationId;

    @ApiModelProperty(value = "分类详情名字")
    private String specificationName;
    
    @ApiModelProperty(value = "活动商品组别")
    private int groupType;

    public int getGroupType() {
		return groupType;
	}

	public void setGroupType(int groupType) {
		this.groupType = groupType;
	}

	public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public Long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BigDecimal getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(BigDecimal oldPrice) {
        this.oldPrice = oldPrice;
    }

    public BigDecimal getPromotionPrice() {
        return promotionPrice;
    }

    public void setPromotionPrice(BigDecimal promotionPrice) {
        this.promotionPrice = promotionPrice;
    }

    public BigDecimal getBargainPrice() {
        return bargainPrice;
    }

    public void setBargainPrice(BigDecimal bargainPrice) {
        this.bargainPrice = bargainPrice;
    }

    public String getProductImg() {
        return ImageUploadUtils.fillPath(productImg);
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getSpecificationId() {
        return specificationId;
    }

    public void setSpecificationId(String specificationId) {
        this.specificationId = specificationId;
    }

    public String getSpecificationName() {
        return specificationName;
    }

    public void setSpecificationName(String specificationName) {
        this.specificationName = specificationName;
    }
}
