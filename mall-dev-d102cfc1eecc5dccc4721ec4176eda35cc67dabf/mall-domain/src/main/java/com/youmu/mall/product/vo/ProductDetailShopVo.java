/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.product.vo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.youmu.common.upload.utils.ImageUploadUtils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 用户端商品详情
 * @author yujiahao
 * @version $Id: ProductDetailVo.java, v 0.1 2017年3月1日 下午3:08:39 yujiahao Exp $
 */
@ApiModel(value = "用户端商品详情")
public class ProductDetailShopVo{
    
    @ApiModelProperty(value = "id")
    private long id;

    @ApiModelProperty(value="商品名称")
    private String name;
    
    @ApiModelProperty(value="商家id")
    private Long businessId;
    
    @ApiModelProperty(value="商家名称")
    private String businessName;
    
    @ApiModelProperty(value="商家类型id")
    private Long businessTypeId;
    
    @ApiModelProperty(value="原价")
    private BigDecimal oldPrice;
    
    @ApiModelProperty(value="促销价")
    private BigDecimal promotionPrice;
    
    @ApiModelProperty(value="活动价")
    private BigDecimal bargainPrice;
    
    @ApiModelProperty(value="活动组类别（1秒杀，2特价）")
    private Integer groupType;
    
    @ApiModelProperty(value="活动名称")
    private String bargainName;
    
    @ApiModelProperty(value="积分返还天数")
    private String backdays;
    
    @ApiModelProperty(value="库存")
    private Integer total;
    
    @ApiModelProperty(value="状态值  0未开始 1进行中 2已结束")
    private Integer Status;
    
    @ApiModelProperty(value="摘要")
    private String digest;
    
    @ApiModelProperty(value="运费")
    private Integer freight;
    
    @ApiModelProperty(value="商品介绍")
    private String intro;
    
    @ApiModelProperty(value="商品缩略图")
    private String thumbnail;
    
    @ApiModelProperty(value="商品销量")
    private String saleCount;
    
    @ApiModelProperty(value = "商品图片")
    private List<String> images;
    
    @ApiModelProperty(value = "保存时接收的图片")
    private List<String> imagesUrls;
    
    /**剩余商品数量*/
    private Integer quantity;
    
    /**参加活动的该商品总数*/
    private Integer totalQuantity;

    /**分类id*/
    private Integer categoryId;
    
    /**抢完商品的时间 */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date gmtFinish;
    
    

    public String getBackdays() {
		return backdays;
	}

	public void setBackdays(String backdays) {
		this.backdays = backdays;
	}

	public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(Integer totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public Date getGmtFinish() {
        return gmtFinish;
    }

    public void setGmtFinish(Date gmtFinish) {
        this.gmtFinish = gmtFinish;
    }

    public String getSaleCount() {
        return saleCount;
    }

    public void setSaleCount(String saleCount) {
        this.saleCount = saleCount;
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

    public Integer getGroupType() {
        return groupType;
    }

    public void setGroupType(Integer groupType) {
        this.groupType = groupType;
    }

    public String getBargainName() {
        return bargainName;
    }

    public void setBargainName(String bargainName) {
        this.bargainName = bargainName;
    }

    public BigDecimal getBargainPrice() {
        return bargainPrice;
    }

    public void setBargainPrice(BigDecimal bargainPrice) {
        this.bargainPrice = bargainPrice;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getStatus() {
		return Status;
	}

	public void setStatus(Integer status) {
		Status = status;
	}

	public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public Integer getFreight() {
        return freight;
    }

    public void setFreight(Integer freight) {
        this.freight = freight;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public List<String> getImages() {
        return ImageUploadUtils.fillPath(imagesUrls);
    }
    
    public List<String> getImageUrls(){
        return imagesUrls;
    }

    public void setImagesUrls(List<String> imagesUrls) {
        this.imagesUrls = imagesUrls;
    }
    
    public Long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }

    public String getThumbnail() {
        return ImageUploadUtils.fillPath(thumbnail);
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Long getBusinessTypeId() {
        return businessTypeId;
    }

    public void setBusinessTypeId(Long businessTypeId) {
        this.businessTypeId = businessTypeId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
}
