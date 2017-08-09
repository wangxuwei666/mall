/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.salelog.vo;

import java.math.BigDecimal;

/**
 * 销售记录
 * @author yujiahao
 * @version $Id: SalelogSysVo.java, v 0.1 2017年3月15日 下午6:36:30 yujiahao Exp $
 */
public class SalelogSysVo {
    
    /**商户类型名称*/
    private String businessTypeName;
    
    /**累计销售金额*/
    private BigDecimal amount;
    
    /**累计销售商品数量*/
    private Integer quantity;

    
    public String getBusinessTypeName() {
        return businessTypeName;
    }

    public void setBusinessTypeName(String businessTypeName) {
        this.businessTypeName = businessTypeName;
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

    @Override
    public String toString() {
        return "SalelogSysVo [businessTypeName=" + businessTypeName + ", amount=" + amount + ", quantity=" + quantity + "]";
    }
    
    
}
