/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.product.vo;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.youmu.common.cofig.utils.ConfigUtils;
import com.youmu.common.upload.utils.ImageUploadUtils;

/**
 * 用户端 - 活动商品组商品项
 * @author yujiahao
 * @version $Id: ProductGroupDetailShopVo.java, v 0.1 2017年3月21日 下午5:00:45 yujiahao Exp $
 */
public class ProductGroupDetailShopVo {
    /**商品id */
    private Long productId;
    
    /**商品名称 */
    private String productName;
    
    /**商品图片 */
    private String thumbnail;
    
    /**商品摘要 */
    private String digest;
    
    /**原价*/
    private BigDecimal oldPrice;
    
    /**活动价价格*/
    private BigDecimal price;
    
    /**积分返还天数*/
    private int backdays;
    
    /**备注*/
    private String remark;
    
    /**剩余商品数量*/
    private Integer quantity;
    
    /**参加活动的该商品总数*/
    private Integer totalQuantity;
    
    /**抢完商品的时间 */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date gmtFinish;
    
    
    public int getBackdays() {
		return backdays;
	}

	public void setBackdays(int backdays) {
		this.backdays = backdays;
	}

	public Date getGmtFinish() {
        return gmtFinish;
    }

    public void setGmtFinish(Date gmtFinish) {
        this.gmtFinish = gmtFinish;
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

    public String getThumbnail() {
        return ImageUploadUtils.fillPath(thumbnail);
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public BigDecimal getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(BigDecimal oldPrice) {
        this.oldPrice = oldPrice;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    
    
}
