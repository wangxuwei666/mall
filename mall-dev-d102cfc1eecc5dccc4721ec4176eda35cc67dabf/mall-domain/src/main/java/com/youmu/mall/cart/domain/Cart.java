/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.cart.domain;

import java.math.BigDecimal;

import com.youmu.mall.base.domain.BaseDomain;

/**
 * 
 * @author yujiahao
 * @version $Id: Cart.java, v 0.1 2017年2月28日 上午11:05:14 yujiahao Exp $
 */
public class Cart extends BaseDomain{
    /**
     * 用户id
     */
    private Long userId;
    
    /**
     * 商品id
     */
    private Long productId;
    
    /**
     * 数量
     */
    private Integer quantity;
    
    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 分类id
     */
    private Integer categoryId;

    /**
     * 分类详情id
     */
    private Integer specificationId;

    public Cart() {
        super();
    }

    /**
     * @param cartId
     * @param i
     */
    public Cart(Long cartId, int quantity ,Long userId) {
        super.setId(cartId);
        this.quantity = quantity;
        this.userId = userId;
    }

    
    
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getSpecificationId() {
        return specificationId;
    }

    public void setSpecificationId(Integer specificationId) {
        this.specificationId = specificationId;
    }
}
