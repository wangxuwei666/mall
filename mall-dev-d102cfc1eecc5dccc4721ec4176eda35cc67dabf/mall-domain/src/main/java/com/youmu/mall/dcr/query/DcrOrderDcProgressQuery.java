/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.dcr.query;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;

import com.youmu.common.context.GlobalConstant;
import com.youmu.mall.base.query.PageQuery;

/**
 * 订单装修流程查询.
 * @author zh
 * @version $Id: DcrOrderDcProgressQuery.java, v 0.1 2017年3月7日 下午4:47:43 zh Exp $
 */
public class DcrOrderDcProgressQuery extends PageQuery {
    
    /** 订单的手机号码 */
    private String mobile;
    
    /** 订单的编号 */
    private String orderNo;
    
    /** 订单的状态 */
    private Integer orderStatus;
    
    /** 订单的状态 */
    private Integer minOrderStatus;
    
    /** 系统审核的最小状态  */
    private Integer sysVerifiStatus;
    
    /** 系统审核的最大状态  */
    private Integer maxSysVerifiStatus;
    
    /** 系统审核的状态 */
    private Integer minSysVerifiStatus;
    
    /** 流程的状态 */
    private Integer status;
    
    /** 流程的最小状态 */
    private Integer minStatus;
    
    /** 流程的最大状态 */
    private Integer maxStatus;
    
    /** 银行审核完成开始时间  */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date gmtSubmitStart;
    
    /** 银行审核结束时间 */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date gmtSubmitEnd;
    
    /** 后台的审核状态 */
    private Integer verifiStatus;
    
    /** 操作状态 */
    private Integer operator;
    
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        if(StringUtils.isNotBlank(mobile)) {
            this.mobile = mobile;
        }
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        if(StringUtils.isNotBlank(orderNo)) {
            this.orderNo = orderNo;
        }
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void setIsVerifi(Boolean isVerifi) {
        if(isVerifi != null) {
            if(isVerifi) {
                // 已经审核
                this.minSysVerifiStatus = GlobalConstant.DCPhaseVerifiStatus.NOT_PASSED;
                this.minOrderStatus =  GlobalConstant.DCROrderStatus.BUSINESS_DECORATING;
                // 精确查询 审核是否通过的状态
                if(getVerifiStatus() != null) {
                    switch (getVerifiStatus()) {
                        case 0:
                            // noting to do
                            break;
                        case 1:
                            this.minSysVerifiStatus = GlobalConstant.DCPhaseVerifiStatus.PASSED;
                            break;
                        case 2:
                            this.sysVerifiStatus = GlobalConstant.DCPhaseVerifiStatus.NOT_PASSED;
                            break;

                        default:
                            break;
                    }
                }
            } else {
                // 等待审核
                this.orderStatus = GlobalConstant.DCROrderStatus.BUSINESS_DECORATING;
                this.sysVerifiStatus  = GlobalConstant.DCPhaseVerifiStatus.WAIT_VERIFI;
            }
        }
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setIsRemit(Boolean isRemit) {
        if(isRemit != null) {
            if(isRemit) {
                this.setMinOrderStatus(GlobalConstant.DCROrderStatus.BUSINESS_DECORATING);
                this.status = GlobalConstant.DecorateStatus.FINISH;
            } else {
                this.orderStatus = GlobalConstant.DCROrderStatus.BUSINESS_DECORATING;
                this.status = GlobalConstant.DecorateStatus.WAIT_REMIT;
            }
        }
    }

    public Integer getSysVerifiStatus() {
        return sysVerifiStatus;
    }

    public void setSysVerifiStatus(Integer sysVerifiStatus) {
        this.sysVerifiStatus = sysVerifiStatus;
    }

    public Integer getMaxSysVerifiStatus() {
        return maxSysVerifiStatus;
    }

    public void setMaxSysVerifiStatus(Integer maxSysVerifiStatus) {
        this.maxSysVerifiStatus = maxSysVerifiStatus;
    }

    public Integer getMinSysVerifiStatus() {
        return minSysVerifiStatus;
    }

    public void setMinSysVerifiStatus(Integer minSysVerifiStatus) {
        this.minSysVerifiStatus = minSysVerifiStatus;
    }

    public Integer getMinStatus() {
        return minStatus;
    }

    public void setMinStatus(Integer minStatus) {
        this.minStatus = minStatus;
    }

    public Integer getMaxStatus() {
        return maxStatus;
    }

    public void setMaxStatus(Integer maxStatus) {
        this.maxStatus = maxStatus;
    }

    public Integer getMinOrderStatus() {
        return minOrderStatus;
    }

    public void setMinOrderStatus(Integer minOrderStatus) {
        this.minOrderStatus = minOrderStatus;
    }

    /**
     * Getter method for property <tt>gmtSubmitStart</tt>.
     * 
     * @return property value of gmtSubmitStart
     */
    public Date getGmtSubmitStart() {
        return gmtSubmitStart;
    }

    /**
     * Setter method for property <tt>gmtSubmitStart</tt>.
     * 
     * @param gmtSubmitStart value to be assigned to property gmtSubmitStart
     */
    public void setGmtSubmitStart(Date gmtSubmitStart) {
        this.gmtSubmitStart = gmtSubmitStart;
    }

    /**
     * Getter method for property <tt>gmtSubmitEnd</tt>.
     * 
     * @return property value of gmtSubmitEnd
     */
    public Date getGmtSubmitEnd() {
        return gmtSubmitEnd;
    }

    /**
     * Setter method for property <tt>gmtSubmitEnd</tt>.
     * 
     * @param gmtSubmitEnd value to be assigned to property gmtSubmitEnd
     */
    public void setGmtSubmitEnd(Date gmtSubmitEnd) {
        this.gmtSubmitEnd = gmtSubmitEnd;
    }

        /**
         * Getter method for property <tt>verifiStatus</tt>.
         * 
         * @return property value of verifiStatus
         */
    public Integer getVerifiStatus() {
        return verifiStatus;
    }

    public void setVerifiStatus(Integer verifiStatus) {
        this.verifiStatus = verifiStatus;
    }

        public Integer getOperator() {
            return operator;
        }

        public void setOperator(Integer operator) {
            this.operator = operator;
        }
        
        
}
