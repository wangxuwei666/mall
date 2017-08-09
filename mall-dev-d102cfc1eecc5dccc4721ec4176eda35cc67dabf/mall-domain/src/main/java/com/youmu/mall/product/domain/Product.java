/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.product.domain;

import com.youmu.common.upload.utils.ImageUploadUtils;
import com.youmu.mall.base.domain.BaseDomain;

import io.swagger.annotations.ApiModel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 商品
 * @author yujiahao
 * @version $Id: GenProduct.java, v 0.1 2017�?�?5�?上午11:55:17 yujiahao Exp $
 */
@ApiModel(value = "商品")
public class Product extends BaseDomain{
    
    /**产品名称 */
    private String name;
    
    /**商户id */
    private Long businessId;
    
    /**原价 */
    private BigDecimal oldPrice;
    
    /**促销价*/
    private BigDecimal promotionPrice;
    
    /**摘要 */
    private String digest;
    
    /**介绍 */
    private String intro;
    
    /**编码 */
    private String sn;
    
    /**库存数量 */
    private Integer total;
    
    /**状态: 0初始  1已上架 2已下架 */
    private Integer status;
    
    /**缩略*/
    private String thumbnail;
    
    /**上架时间*/
    private Date gmtPutaway;
    
    /**下架时间*/
    private Date gmtOutstock;
    
    /**版本控制*/
    private long version;
    
    /**销量 */
    private Integer saleCount;

    /**
     * 分类id
     */
    private Integer categoryId;

    /**商品图片 多张图片用逗号隔开*/
    private List<String> images;
    
    private Long businessTypeId;
    
    public Product() {
        super();
    }

    public Product(Long productId) {
        setId(productId);
    }

    public Product(Long id,Integer status) {
        super.setId(id);
        this.status = status;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getSaleCount() {
        return saleCount;
    }

    public void setSaleCount(Integer saleCount) {
        this.saleCount = saleCount;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
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

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Date getGmtPutaway() {
        return gmtPutaway;
    }

    public void setGmtPutaway(Date gmtPutaway) {
        this.gmtPutaway = gmtPutaway;
    }

    public Date getGmtOutstock() {
        return gmtOutstock;
    }

    public void setGmtOutstock(Date gmtOutstock) {
        this.gmtOutstock = gmtOutstock;
    }

    public Long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

	public Long getBusinessTypeId() {
		return businessTypeId;
	}

	public void setBusinessTypeId(Long businessTypeId) {
		this.businessTypeId = businessTypeId;
	}

	@Override
	public String toString() {
		return "Product [name=" + name + ", businessId=" + businessId + ", oldPrice=" + oldPrice + ", promotionPrice="
				+ promotionPrice + ", digest=" + digest + ", intro=" + intro + ", sn=" + sn + ", total=" + total
				+ ", status=" + status + ", thumbnail=" + thumbnail + ", gmtPutaway=" + gmtPutaway + ", gmtOutstock="
				+ gmtOutstock + ", version=" + version + ", saleCount=" + saleCount + ", categoryId=" + categoryId
				+ ", images=" + images + ", businessTypeId=" + businessTypeId + "]";
	}

}
