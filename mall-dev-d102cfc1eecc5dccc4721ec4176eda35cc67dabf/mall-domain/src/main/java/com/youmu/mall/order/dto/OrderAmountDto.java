/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.order.dto;

import java.math.BigDecimal;

/**
 * 订单 id-金额 
 * @author yujiahao
 * @version $Id: OrderAmountDto.java, v 0.1 2017年3月10日 下午6:28:38 yujiahao Exp $
 */
public class OrderAmountDto {
    
    private BigDecimal paidAmount;
    
    private Long orderId;

	public OrderAmountDto() {
        super();
    }

    public OrderAmountDto(Long orderId,BigDecimal paidAmount) {
        super();
        this.orderId = orderId;
        this.paidAmount = paidAmount;
    }

    public BigDecimal getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(BigDecimal paidAmount) {
        this.paidAmount = paidAmount;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
    
    
}
