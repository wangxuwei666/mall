/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.order.vo;

import java.math.BigDecimal;
import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.youmu.mall.base.domain.BaseDomain;

import io.swagger.annotations.ApiModelProperty;

/**
 * 商户端-商品订单
 * @author yujiahao
 * @version $Id: ProductOrderBusVo.java, v 0.1 2017年3月9日 下午8:17:26 yujiahao Exp $
 */
public class ProductOrderBusVo extends BaseDomain{
    
    @ApiModelProperty(value = "订单编号")
    private String sn;      
     
    @ApiModelProperty(value = "发货状态")  
    private Integer status;
    
    @ApiModelProperty(value = "订单支付时间")  
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private String gmtPayFinished;
    
    @ApiModelProperty(value = "用户手机号")  
    private String mobile;
    
    @ApiModelProperty(value = "商品数量")  
    private Integer quantity;

    @ApiModelProperty(value = "商品总金额")  
    private BigDecimal totalAmount;
    
    
    
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getGmtPayFinished() {
        return gmtPayFinished;
    }

    public void setGmtPayFinished(String gmtPayFinished) {
        this.gmtPayFinished = gmtPayFinished;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    
    
               
}

