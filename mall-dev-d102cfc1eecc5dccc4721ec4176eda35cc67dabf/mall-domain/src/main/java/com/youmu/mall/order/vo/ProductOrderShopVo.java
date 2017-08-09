/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.order.vo;

import java.math.BigDecimal;
import java.util.List;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 用户端订单列表
 * @author yujiahao
 * @version $Id: ProductOrderShopVo.java, v 0.1 2017年3月1日 下午1:42:10 yujiahao Exp $
 */
@ApiModel(value = "商品订单-用户端")
public class ProductOrderShopVo {
    @ApiModelProperty(value = "id")
    private long id;
    
    @ApiModelProperty(value = "订单编号")
    private String sn;
    
    @ApiModelProperty(value = "订单金额")
    private BigDecimal totalAmount;

    @ApiModelProperty(value = "订单运费")
    private BigDecimal freight;
    
    @ApiModelProperty(value = "订单状态")
    private Integer status;
    
    @ApiModelProperty(value = "商品总数")
    private Integer quantity;
    
    @ApiModelProperty(value = "订单消费积分值")
    private Long pointsValue;
    
    @ApiModelProperty(value = "订单是否超时(0-未超时，1-已超时)")
    private Integer isTimeout;
    
    @ApiModelProperty(value = "订单是否包含积分组(0-不包含， 4-包含)")
    private String isGroupFour;
    
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

	public Integer getIsTimeout() {
        return isTimeout;
    }

    public void setIsTimeout(Integer isTimeout) {
        this.isTimeout = isTimeout;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<ProductOrderItemVo> getItems() {
        return items;
    }

    public void setItems(List<ProductOrderItemVo> items) {
        this.items = items;
    }

    public BigDecimal getFreight() {
        return freight;
    }

    public void setFreight(BigDecimal freight) {
        this.freight = freight;
    }
}
