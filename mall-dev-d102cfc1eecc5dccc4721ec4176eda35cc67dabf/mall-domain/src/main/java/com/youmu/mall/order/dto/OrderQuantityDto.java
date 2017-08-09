/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.order.dto;

/**
 * 商品数量
 * @author yujiahao
 * @version $Id: OrderQuantityDto.java, v 0.1 2017年3月6日 上午10:30:48 yujiahao Exp $
 */
public class OrderQuantityDto {
    
    /**订单商品id*/
    private Long productId;
    
    /**商品数量*/
    private Integer quantity;
    
    /**商品的版本*/
    private long version;
    
    

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
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

    @Override
    public String toString() {
        return "OrderQuantityDto [productId=" + productId + ", quantity=" + quantity + ", version=" + version + "]";
    }
    
    
    
    
}
