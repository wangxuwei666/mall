/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.order.query;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.youmu.mall.base.query.PageQuery;

/**
 * 
 * @author yujiahao
 * @version $Id: OrderQuery.java, v 0.1 2017年2月28日 下午6:28:50 yujiahao Exp $
 */
public class ProductOrderQuery extends PageQuery{
    
    /**
     * 订单状态(-1已取消，0待支付，1已支付)
     */
    private Integer status;
    
    private Long businessTypeId;
    private Long userId;
    
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date beginDate;
    
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date endDate;
    private String sn;
    
    /**
     * 商户用户的商户id
     */
    private Long businessId;
    
    /**
     * 运单状态（1待发货，2待收货）
     */
    private Integer shippingStatus;
    
    
    
    public Long getBusinessId() {
        return businessId;
    }
    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }
    public Integer getShippingStatus() {
        return shippingStatus;
    }
    public void setShippingStatus(Integer shippingStatus) {
        this.shippingStatus = shippingStatus;
    }
    public String getSn() {
        return sn;
    }
    public void setSn(String sn) {
        this.sn = sn;
    }
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public Long getBusinessTypeId() {
        return businessTypeId;
    }
    public void setBusinessTypeId(Long businessTypeId) {
        this.businessTypeId = businessTypeId;
    }
    public Date getBeginDate() {
        return beginDate;
    }
    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }
    public Date getEndDate() {
        return endDate;
    }
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    
    
    
}
