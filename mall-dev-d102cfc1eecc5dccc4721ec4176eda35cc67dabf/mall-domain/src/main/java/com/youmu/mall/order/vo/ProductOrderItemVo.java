/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.order.vo;

import java.math.BigDecimal;

import com.youmu.common.upload.utils.ImageUploadUtils;
import com.youmu.mall.base.domain.BaseDomain;
import com.youmu.mall.exception.BusinessException;

/**
 * 
 * @author yujiahao
 * @version $Id: ProductOrderItem.java, v 0.1 2017年2月28日 下午8:06:35 yujiahao Exp $
 */
public class ProductOrderItemVo extends BaseDomain{
    
    /**
     * 商品项名称
     */
    private String name;
    
    /**
     * 商品主订单id
     */
    private Long productOrderId;
    
    /**
     * 商品id
     */
    private Long productId;
    
    /**
     * 商家id
     */
    private Long businessId;
    
    /**
     * 购物车id
     */
    private Long cartId;
    
    /**
     * 商品项价格
     */
    private BigDecimal price;
    
    /**
     * 商品项数量
     */
    private Integer quantity;
    
    /**
     * 缩略图
     */
    private String thumbnail;
    
    /**
     * 摘要
     */
    private String digest;
    
    /**
     * 运费
     */
    private BigDecimal freight;
    
    /**
     * 商品原价
     */
    private BigDecimal oldPrice;

    /**
     * 分类名字
     */
    private String categoryName;

    /**
     * 分类详情名字
     */
    private String specificationName;

    /**
     * 活动组分类
     */
    private int groupType;
    
    /**
     * 返回前端字段，商品每一项的支付总价
     * 
     * @return
     */
    public BigDecimal getProductPaidPrice(){
        if(price==null || quantity==null){
            return null;
        }
        return price.multiply(new BigDecimal(quantity)).setScale(2, BigDecimal.ROUND_HALF_EVEN);
    }

    public BigDecimal getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(BigDecimal oldPrice) {
        this.oldPrice = oldPrice;
    }

    public BigDecimal getFreight() {
        return freight;
    }

    public void setFreight(BigDecimal freight) {
        this.freight = freight;
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

    public Long getProductOrderId() {
        return productOrderId;
    }

    public void setProductOrderId(Long productOrderId) {
        this.productOrderId = productOrderId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getSpecificationName() {
        return specificationName;
    }

    public void setSpecificationName(String specificationName) {
        this.specificationName = specificationName;
    }

	public int getGroupType() {
		return groupType;
	}

	public void setGroupType(int groupType) {
		this.groupType = groupType;
	}
    
}
