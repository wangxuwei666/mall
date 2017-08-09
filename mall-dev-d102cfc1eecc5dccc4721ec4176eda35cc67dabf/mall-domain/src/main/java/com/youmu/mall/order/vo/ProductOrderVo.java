/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.order.vo;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.youmu.mall.order.domain.ProductOrderItem;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @author yujiahao
 * @version $Id: ProductOrderVo.java, v 0.1 2017年2月28日 下午7:56:06 yujiahao Exp $
 */
@ApiModel(value = "商品订单-管理后台")
public class ProductOrderVo {
    @ApiModelProperty(value = "id")
    private long id;
    
    @ApiModelProperty(value = "用户手机号")
    private String mobile;
    
    @ApiModelProperty(value = "订单编号")
    private String sn;
    
    @ApiModelProperty(value = "订单金额")
    private BigDecimal totalAmount;
    
    @ApiModelProperty(value = "商品数量")
    private Integer quantity;
    
    @ApiModelProperty(value = "订单状态")
    private Integer status;
    
    @ApiModelProperty(value = "订单创建时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private String gmtCreate;
    
    
    
    
    public String getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(String gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    
}
