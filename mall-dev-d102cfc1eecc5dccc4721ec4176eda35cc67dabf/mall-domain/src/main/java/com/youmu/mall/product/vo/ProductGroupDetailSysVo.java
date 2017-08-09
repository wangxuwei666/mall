/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.product.vo;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 管理后台-商品组明细
 * @author yujiahao
 * @version $Id: ProductGroupDetailSysVo.java, v 0.1 2017年3月7日 下午6:21:18 yujiahao Exp $
 */
public class ProductGroupDetailSysVo {
    /**商品id */
    private Long productId;
    
    /**商品名称 */
    private String name;
    
    /**原价*/
    private BigDecimal oldPrice;
    
    /**促销价*/
    private BigDecimal promotionPrice;
    
    /**库存*/
    private Integer total;
    
    /**活动价的商品数量*/
    private Integer quantity;
    
    /**活动价价格*/
    private BigDecimal price;
    
    /**积分活动返还天数*/
    private int backdays;
    
    public int getBackdays() {
		return backdays;
	}

	public void setBackdays(int backdays) {
		this.backdays = backdays;
	}

	public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
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

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    
    
}
