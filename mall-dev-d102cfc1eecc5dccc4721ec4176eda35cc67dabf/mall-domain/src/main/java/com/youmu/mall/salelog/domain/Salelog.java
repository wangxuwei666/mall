/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.salelog.domain;

import java.math.BigDecimal;

import com.youmu.mall.base.domain.BaseDomain;

/**
 * 销售日志
 * @author yujiahao
 * @version $Id: Salelog.java, v 0.1 2017年3月15日 下午3:55:06 yujiahao Exp $
 */
public class Salelog extends BaseDomain{
    
    /**用户id*/
    private Long userId;
    
    /**商户类别id*/
    private Long businessTypeId;
    
    /**销售金额*/
    private BigDecimal amount;
    
    /**销售数量(商品数量)*/
    private Integer quantity;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getBusinessTypeId() {
        return businessTypeId;
    }

    public void setBusinessTypeId(Long businessTypeId) {
        this.businessTypeId = businessTypeId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    
    
}
