/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.dcr.vo;

import java.math.BigDecimal;
import java.util.List;

/**
 * 用户装修贷款订单.
 * @author zh
 * @version $Id: UserDcrOrder.java, v 0.1 2017年3月7日 下午3:13:09 zh Exp $
 */
public class UserDcrOrderListVo {
    
    /** 订单id */
    private Long id;
    
    /** 订单编号 */
    private String orderNo;
    
    /** 总金额 */
    private BigDecimal totalAmount;
    
    /** 阶段列表 */
    private List<DCPhaseUserListVo> phases;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<DCPhaseUserListVo> getPhases() {
        return phases;
    }

    public void setPhases(List<DCPhaseUserListVo> phases) {
        this.phases = phases;
    }
}
