/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.order.vo;

import java.math.BigDecimal;
import java.util.List;

import com.youmu.common.validate.utils.ValidateUtils;
import com.youmu.mall.base.domain.BaseDomain;
import com.youmu.mall.order.domain.ProductOrderItem;

/**
 * 商户端-商品订单明细
 * @author yujiahao
 * @version $Id: ProductOrderDetailBusVo.java, v 0.1 2017年3月9日 下午8:42:50 yujiahao Exp $
 */
public class ProductOrderDetailBusVo extends BaseDomain{
    /**
     * 订单状态
     */
    private Integer status;
    
    /**
     * 订单编号
     */
    private String sn;
    
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
     * 运送单编号
     */
    private String shippingSn;
    
    /**
     * 用户订单备注
     */
    private String remark;
    
    /**
     * 订单子项
     */
    private List<ProductOrderItemVo> items;
    
    /**
     * 返给前端的商品总金额
     * 
     * @return
     */
    public BigDecimal getTotalPrice(){
        BigDecimal totalPrice = ValidateUtils.BIGDECIMAL_ZERO;  
        for (ProductOrderItemVo productOrderItemVo : items) {
            BigDecimal price = productOrderItemVo.getPrice();
            Integer quantity = productOrderItemVo.getQuantity();
            totalPrice = totalPrice.add(price.multiply(new BigDecimal(quantity)));
        }
        return totalPrice.setScale(2, BigDecimal.ROUND_HALF_EVEN);
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

    public List<ProductOrderItemVo> getItems() {
        return items;
    }

    public void setItems(List<ProductOrderItemVo> items) {
        this.items = items;
    }

    public String getShippingSn() {
        return shippingSn;
    }

    public void setShippingSn(String shippingSn) {
        this.shippingSn = shippingSn;
    }

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
    
    
    
    
}
