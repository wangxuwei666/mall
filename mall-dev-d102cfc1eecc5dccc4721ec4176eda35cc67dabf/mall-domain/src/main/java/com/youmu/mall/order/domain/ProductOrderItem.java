/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.order.domain;

import java.math.BigDecimal;
import com.youmu.mall.base.domain.BaseDomain;

/**
 * 
 * @author yujiahao
 * @version $Id: ProductOrderItem.java, v 0.1 2017年2月28日 下午8:06:35 yujiahao Exp $
 */
public class ProductOrderItem extends BaseDomain{
    
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
     * 分类详情
     */
    private Integer specificationId;
    
    /**
     * 活动组类别
     */
    private Integer groupType;
    
    
    public Integer getGroupType() {
		return groupType;
	}

	public void setGroupType(Integer groupType) {
		this.groupType = groupType;
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
        return thumbnail;
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

    public Integer getSpecificationId() {
        return specificationId;
    }

    public void setSpecificationId(Integer specificationId) {
        this.specificationId = specificationId;
    }

	@Override
	public String toString() {
		return "ProductOrderItem [name=" + name + ", productOrderId=" + productOrderId + ", productId=" + productId
				+ ", businessId=" + businessId + ", cartId=" + cartId + ", price=" + price + ", quantity=" + quantity
				+ ", thumbnail=" + thumbnail + ", digest=" + digest + ", freight=" + freight + ", specificationId="
				+ specificationId + ", groupType=" + groupType + "]";
	}
    
}
