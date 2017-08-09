/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.product.domain;

import java.math.BigDecimal;
import com.youmu.mall.base.domain.BaseDomain;

/**
 * 活动商品组的商品明细
 * @author yujiahao
 * @version $Id: ProductGroupDetail.java, v 0.1 2017年3月7日 上午11:37:33 yujiahao Exp $
 */
public class ProductGroupDetail extends BaseDomain{
    
    /**活动商品组明细id */
    private Long itemId;
    
    /**商品组id */
    private Long groupId;
    
    /**商品id */
    private Long productId;
    
    /**活动商品数量 */
    private Integer quantity;
    
    /**该商品的总活动商品数 */
    private Integer totalQuantity;

    /**活动价格 */
    private BigDecimal price;
    
    /**积分活动返还天数*/
    private Integer backdays;
    
	/**活动备注 */
    private String remark;
    
    /**版本控制*/
    private Long version;
    
    
    public Integer getBackdays() {
		return backdays;
	}

	public void setBackdays(Integer backdays) {
		this.backdays = backdays;
	}


    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(Integer totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    
    
}
