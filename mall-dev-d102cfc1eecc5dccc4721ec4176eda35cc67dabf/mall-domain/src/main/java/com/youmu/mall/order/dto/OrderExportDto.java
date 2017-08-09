/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.order.dto;

import java.math.BigDecimal;
import java.util.List;

import com.youmu.common.excel.utils.ExcelExportField;
import com.youmu.mall.order.domain.ProductOrderItem;

/**
 * 订单导出对象
 * @author yujiahao
 * @version $Id: OrderExportDto.java, v 0.1 2017年5月9日 下午4:21:25 yujiahao Exp $
 */
@ExcelExportField("商品待发货订单")
public class OrderExportDto {
    @ExcelExportField("订单编号")
    private String sn;
    
    @ExcelExportField("订单金额")
    private BigDecimal paidAmount;
    
    @ExcelExportField("订单支付时间")
    private String gmtPayFinished;
    
    @ExcelExportField("用户手机号")
    private String mobile;
    
    @ExcelExportField("商品名称X数量")
    private String productInfo;
    
    @ExcelExportField("发货状态")
    private String status;
    
    @ExcelExportField("收货人")
    private String receiverName;
    
    @ExcelExportField("收货人电话")
    private String receiverMobile;
    
    @ExcelExportField("收货人地址")
    private String address;
    
    private List<ProductOrderItem> items;

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public BigDecimal getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(BigDecimal paidAmount) {
        this.paidAmount = paidAmount;
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

    public String getProductInfo() {
        return productInfo;
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

    public List<ProductOrderItem> getItems() {
        return items;
    }

    public void setItems(List<ProductOrderItem> items) {
        this.items = items;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "OrderExportDto [sn=" + sn + ", paidAmount=" + paidAmount + ", gmtPayFinished=" + gmtPayFinished + ", mobile=" + mobile + ", productInfo=" + getProductInfo() + ", status=" + status
               + ", receiverName=" + receiverName + ", receiverMobile=" + receiverMobile + ", address=" + address + ", items=" + items + "]";
    }

    public void setProductInfo(String productInfo) {
        this.productInfo = productInfo;
    }

    
    
    
}
