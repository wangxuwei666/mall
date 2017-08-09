/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.order.vo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.youmu.mall.base.domain.BaseDomain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 商品订单明细
 * @author yujiahao
 * @version $Id: ProductOrderDetailVo.java, v 0.1 2017年3月1日 下午2:55:41 yujiahao Exp $
 */
@ApiModel(value = "商品订单明细-用户端")
public class ProductOrderDetailShopVo extends BaseDomain{
    @ApiModelProperty(value = "订单状态")
    private Integer status;
    
    @ApiModelProperty(value = "订单编号")
    private String sn;
    
    @ApiModelProperty(value = "配送方式")
    private String shippingMethod;
    
    @ApiModelProperty(value = "支付方式")
    private String payMethod;
    
    @ApiModelProperty(value = "运单号")
    private String shippingSn;
    
    @ApiModelProperty(value = "订单备注")
    private String remark;
    
    @ApiModelProperty(value = "商品总金额")
    private BigDecimal totalAmount;
    
    @ApiModelProperty(value = "实付款")
    private BigDecimal paidAmount;
    
    @ApiModelProperty(value = "商品总数")
    private Integer quantity;
    
    @ApiModelProperty(value = "运费")
    private BigDecimal freight;

    @ApiModelProperty(value = "订单消费积分值")
    private Long pointsValue;
    
    @ApiModelProperty(value = "订单是否包含积分组 0-不含 ，4-包含")
    private String isGroupFour;
    
    @ApiModelProperty(value = "优惠劵抵扣金额")
    private BigDecimal couponAmount;
    
    @ApiModelProperty(value = "收货人姓名")
    private String receiverName;
    
    @ApiModelProperty(value = "收货人电话")
    private String receiverMobile;
    
    @ApiModelProperty(value = "收货人详细地址")
    private String address;
    
    @ApiModelProperty(value = "系统当前时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date currentDate;
    
    @ApiModelProperty(value = "订单子项")
    private List<ProductOrderItemVo> items;

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

	public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public BigDecimal getFreight() {
        return freight;
    }

    public void setFreight(BigDecimal freight) {
        this.freight = freight;
    }

    public BigDecimal getCouponAmount() {
        return couponAmount;
    }

    public void setCouponAmount(BigDecimal couponAmount) {
        this.couponAmount = couponAmount;
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

    public List<ProductOrderItemVo> getItems() {
        return items;
    }

    public void setItems(List<ProductOrderItemVo> items) {
        this.items = items;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getShippingMethod() {
        return shippingMethod;
    }

    public void setShippingMethod(String shippingMethod) {
        this.shippingMethod = shippingMethod;
    }

    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    public String getShippingSn() {
        return shippingSn;
    }

    public void setShippingSn(String shippingSn) {
        this.shippingSn = shippingSn;
    }

    public Date getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }


    
    
    
}
