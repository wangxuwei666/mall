/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.dcr.query;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.youmu.common.context.GlobalConstant;
import com.youmu.mall.base.query.PageQuery;

/**
 * 装修贷款优惠券运营后台的查询对象.
 * @author zh
 * @version $Id: DcrOrderSysQuery.java, v 0.1 2017年3月3日 下午6:28:18 zh Exp $
 */
public class DcrOrderQuery extends PageQuery {
    
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date gmtCreateStart;
    
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date gmtCreateEnd;
    
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date gmtSubCompactStart;
    
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date gmtSubCompactEnd;
    
    /** 商户的id */
    private Long businessId;
    
    /** 订单的手机号码 */
    private String mobile;
    
    /** 订单的编号 */
    private String orderNo;
    
    /** 订单的状态 */
    private Integer orderStatus;
    
    /** 订单的最小状态 */
    private Integer minOrderStatus;
    
    /** 订单的最大状态 */
    private Integer maxOrderStatus;
    
    /** 用户id */
    private Long userId;
    
    /** 合同后台审核开始时间  */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date gmtCheckedStart;
    
    /** 合同后台审核结束时间  */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date gmtCheckedEnd;
    
    /** 时间排序字段 */
    private String gmtOrder = "o.gmt_create desc";

    public Date getGmtCreateStart() {
        return gmtCreateStart;
    }

    public void setGmtCreateStart(Date gmtCreateStart) {
        this.gmtCreateStart = gmtCreateStart;
    }

    public Date getGmtCreateEnd() {
        return gmtCreateEnd;
    }

    public void setGmtCreateEnd(Date gmtCreateEnd) {
        this.gmtCreateEnd = gmtCreateEnd;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getMinOrderStatus() {
        return minOrderStatus;
    }

    public void setMinOrderStatus(Integer minOrderStatus) {
        this.minOrderStatus = minOrderStatus;
    }

    public Integer getMaxOrderStatus() {
        return maxOrderStatus;
    }

    public void setMaxOrderStatus(Integer maxOrderStatus) {
        this.maxOrderStatus = maxOrderStatus;
    }

    public Date getGmtSubCompactStart() {
        return gmtSubCompactStart;
    }

    public void setGmtSubCompactStart(Date gmtSubCompactStart) {
        this.gmtSubCompactStart = gmtSubCompactStart;
    }

    public Date getGmtSubCompactEnd() {
        return gmtSubCompactEnd;
    }

    public void setGmtSubCompactEnd(Date gmtSubCompactEnd) {
        this.gmtSubCompactEnd = gmtSubCompactEnd;
    }

    public void setSysContactStatus(Integer sysContactStatus) {
        if(sysContactStatus != null) {
            switch (sysContactStatus) {
                case 0:
                    // 全部申请
                    break;
                case 1:
                   // 未联系
                    this.orderStatus = GlobalConstant.DCROrderStatus.USER_APPLY;
                    break;
                case 2:
                    // 已经联系 这里如果没联系但是流程已经往下走了 也视为已经联系??
                    this.setGmtOrder("o.gmt_handle desc");
                    this.minOrderStatus = GlobalConstant.DCROrderStatus.SYS_PROCESSING;
                    break;
            }
        }
    }


    public void setSysCompactCheckStatus(Integer sysCompactCheckStatus) {
        this.setGmtOrder("o.gmt_subcompact desc");
        if(sysCompactCheckStatus != null) {
            switch (sysCompactCheckStatus) {
                case 0:
                    // 全部可审核的贷款订单
                    this.setGmtOrder("o.gmt_subcompact desc");
                    this.minOrderStatus = GlobalConstant.DCROrderStatus.BUSINESS_SUBMIT_COMPACT;
                    break;
                case 1:
                   // 未审核
                    this.setGmtOrder("o.gmt_subcompact desc");
                    this.orderStatus = GlobalConstant.DCROrderStatus.BUSINESS_SUBMIT_COMPACT;
                    break;
                case 2:
                    // 已经审核
                    this.setGmtOrder("o.gmt_sysverifi desc");
                    this.minOrderStatus = GlobalConstant.DCROrderStatus.SYS_CONTRACT_REJECT;
                    break;
                case 3:
                    // 审核通过
                    this.setGmtOrder("o.gmt_sysverifi desc");
                    this.minOrderStatus = GlobalConstant.DCROrderStatus.SYS_AUDIT_CONTRACT;
                    break;
                case 4:
                    // 审核不通过
                    this.setGmtOrder("o.gmt_sysverifi desc");
                    this.orderStatus = GlobalConstant.DCROrderStatus.SYS_CONTRACT_REJECT;
                    break;
            }
        }
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }

    public void setCompleteStatus(Integer completeStatus) {
        switch (completeStatus) {
            case 0:
            	// 装修中的全部订单
                this.setGmtOrder("o.gmt_bankverifi desc");
                this.minOrderStatus = GlobalConstant.DCROrderStatus.BUSINESS_DECORATING;
                this.maxOrderStatus = GlobalConstant.DCROrderStatus.ORDER_FINISHED;
                break;
            case 1:
            	// 装修中
                this.setGmtOrder("o.gmt_bankverifi desc");
                this.orderStatus = GlobalConstant.DCROrderStatus.BUSINESS_DECORATING;
                break;
            case 2:
            	// 已经完成
                this.setGmtOrder("o.gmt_finish desc");
                this.orderStatus = GlobalConstant.DCROrderStatus.ORDER_FINISHED;
                break;
            default:
                break;
        }
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * Getter method for property <tt>gmtCheckedStart</tt>.
     * 
     * @return property value of gmtCheckedStart
     */
    public Date getGmtCheckedStart() {
        return gmtCheckedStart;
    }

    /**
     * Setter method for property <tt>gmtCheckedStart</tt>.
     * 
     * @param gmtCheckedStart value to be assigned to property gmtCheckedStart
     */
    public void setGmtCheckedStart(Date gmtCheckedStart) {
        this.gmtCheckedStart = gmtCheckedStart;
    }

    /**
     * Getter method for property <tt>gmtCheckedEnd</tt>.
     * 
     * @return property value of gmtCheckedEnd
     */
    public Date getGmtCheckedEnd() {
        return gmtCheckedEnd;
    }

    /**
     * Setter method for property <tt>gmtCheckedEnd</tt>.
     * 
     * @param gmtCheckedEnd value to be assigned to property gmtCheckedEnd
     */
    public void setGmtCheckedEnd(Date gmtCheckedEnd) {
        this.gmtCheckedEnd = gmtCheckedEnd;
    }

    public String getGmtOrder() {
        return gmtOrder;
    }

        /**
         * Setter method for property <tt>gmtOrder</tt>.
         * 
         * @param gmtOrder value to be assigned to property gmtOrder
         */
    public void setGmtOrder(String gmtOrder) {
        this.gmtOrder = gmtOrder;
    }
}
