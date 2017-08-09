/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.coupon.domain;

/**
 * // 查询业务判断的参数
 * 1. 优惠券的过期时间  
 * 2. 优惠券的商户类型
 * 3. 优惠券的商户id
 * 4. 优惠券是否是商户的优惠券
 * 5. 优惠券是否被使用
 * @author zh
 * @version $Id: CouponVerificationInfo.java, v 0.1 2017年3月3日 下午3:08:20 zh Exp $
 */
public class CouponVerificationInfo {
    
    /** 优惠券是否可以开始使用  */
    private Boolean isStart;
    
    /** 是否过期 */
    private Boolean isExpire;
    
    /** 商户的类型 id */
    private Long businessType;
    
    /** 商户的id */
    private Long businessId;
    
    /** 是否是装修的优惠券  */
    private Boolean isDCR;
    
    /** 是否被使用 */
    private Boolean isUsed;

    public Long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }

    public Boolean getIsDCR() {
        return isDCR;
    }

    public void setIsDCR(Boolean isDCR) {
        this.isDCR = isDCR;
    }

    public Boolean getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(Boolean isUsed) {
        this.isUsed = isUsed;
    }

    public Boolean getIsExpire() {
        return isExpire;
    }

    public void setIsExpire(Boolean isExpire) {
        this.isExpire = isExpire;
    }

    public Long getBusinessType() {
        return businessType;
    }

    public void setBusinessType(Long businessType) {
        this.businessType = businessType;
    }

        /**
         * Getter method for property <tt>isStart</tt>.
         * 
         * @return property value of isStart
         */
    public Boolean getIsStart() {
        return isStart;
    }

        /**
         * Setter method for property <tt>isStart</tt>.
         * 
         * @param isStart value to be assigned to property isStart
         */
    public void setIsStart(Boolean isStart) {
        this.isStart = isStart;
    }
    
}
