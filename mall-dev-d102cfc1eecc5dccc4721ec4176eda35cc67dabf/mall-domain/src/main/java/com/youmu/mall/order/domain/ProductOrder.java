/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.order.domain;

import java.math.BigDecimal;
import java.util.List;

import com.youmu.mall.base.domain.BaseDomain;

/**
 * 
 * @author yujiahao
 * @version $Id: ProductOrder.java, v 0.1 2017年2月28日 上午10:10:41 yujiahao Exp $
 */
public class ProductOrder extends BaseDomain{
    
    /**
     * 用户id
     */
    private Long userId;
    
    /**
     * 优惠劵id
     */
    private Long couponId;
    
    /**
     * 收货人地址id
     */
    private Long receiverId;
    
    /**
     * 商户id
     */
    private Long businessId;
    
    /**
     * 订单编号
     */
    private String sn;
    
    /**
     * 外部订单号
     */
    private String outTradeNumber;
    
    /**
     * 微信返回的订单号
     */
    private String transactionId;
    
    /**
     * 订单商品总数量
     */
    private Integer quantity;
    
    /**
     * 总金额
     */
    private BigDecimal totalAmount;
    
    /**
     * 实际支付金额
     */
    private BigDecimal paidAmount;
    
    /**
     * 优惠劵抵扣金额
     */
    private BigDecimal couponAmount;
    
    /**
     * 运费
     */
    private BigDecimal freight;
    
    /**
     * 收货人名称
     */
    private String receiverName;
    
    /**
     * 收货人电话
     */
    private String receiverMobile;
    
    /**
     * 用户收货地址
     */
    private String address;
    
    /**
     * 订单状态(-1已取消，0待支付，1待发货，2待收货，3已完成)
     */
    private Integer status;
    
    /**
     * 订单备注
     */
    private String remark;
    
    /**
     * 支付方式
     */
    private String payMethod;
    
    /**
     * 发货时间
     */
    private String gmtSend;
    
    /**
     * 支付完成时间
     */
    private String gmtPayFinished;
    
    /**
     * 消费的积分值
     */
    private Long pointsValue;
    
    /**
     * 是否包含积分组  0-不含积分组  4-含积分组
     */
    private String isGroupFour;
    
    /**
     * 订单子项
     */
    private List<ProductOrderItem> items;
    
    public String getIsGroupFour() {
		return isGroupFour;
	}

	public void setIsGroupFour(String isGroupFour) {
		this.isGroupFour = isGroupFour;
	}

	public Long getPointsValue() {
		return pointsValue;
	}

	public void setPointsValue(Long pointsValue) {
		this.pointsValue = pointsValue;
	}

	public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCouponId() {
        return couponId;
    }

    public void setCouponId(Long couponId) {
        this.couponId = couponId;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getOutTradeNumber() {
        return outTradeNumber;
    }

    public void setOutTradeNumber(String outTradeNumber) {
        this.outTradeNumber = outTradeNumber;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(BigDecimal paidAmount) {
        this.paidAmount = paidAmount;
    }

    public BigDecimal getCouponAmount() {
        return couponAmount;
    }

    public void setCouponAmount(BigDecimal couponAmount) {
        this.couponAmount = couponAmount;
    }

    public BigDecimal getFreight() {
        return freight;
    }

    public void setFreight(BigDecimal freight) {
        this.freight = freight;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverMobile() {
        return receiverMobile;
    }

    public void setReceiverMobile(String receiverMobile) {
        this.receiverMobile = receiverMobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    public String getGmtPayFinished() {
        return gmtPayFinished;
    }

    public void setGmtPayFinished(String gmtPayFinished) {
        this.gmtPayFinished = gmtPayFinished;
    }

    public List<ProductOrderItem> getItems() {
        return items;
    }

    public void setItems(List<ProductOrderItem> items) {
        this.items = items;
    }

    public Long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }

    public String getGmtSend() {
        return gmtSend;
    }

    public void setGmtSend(String gmtSend) {
        this.gmtSend = gmtSend;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    @Override
    public String toString() {
        return "ProductOrder [userId=" + userId + ", couponId=" + couponId + ", receiverId=" + receiverId + ", businessId=" + businessId + ", sn=" + sn + ", outTradeNumber=" + outTradeNumber
               + ", transactionId=" + transactionId + ", quantity=" + quantity + ", totalAmount=" + totalAmount + ", paidAmount=" + paidAmount + ", couponAmount=" + couponAmount + ", freight="
               + freight + ", receiverName=" + receiverName + ", receiverMobile=" + receiverMobile + ", address=" + address + ", status=" + status + ", remark=" + remark + ", payMethod=" + payMethod
               + ", gmtSend=" + gmtSend + ", gmtPayFinished=" + gmtPayFinished + ", items=" + items + "]";
    }
    
    

    
    
    
}
