/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.coupon.domain;

/**
 * 用户领券.
 * @author zh
 * @version $Id: UserCoupon.java, v 0.1 2017年3月5日 下午3:28:10 zh Exp $
 */
public class UserCoupon {
    
    /** id */
    private Long id;
    
    /** 用户id */
    private Long userId;
    
    /** 优惠券id */
    private Long couponId;
    
    /** 优惠券编号 */
    private String couponNo;
    
    /** 是否 是装修优惠券 */
    private Boolean isDCR;

    public UserCoupon() {
        super();
    }
    

    public UserCoupon(Long userId, Long couponId, String couponNo, Boolean isDCR) {
        super();
        this.userId = userId;
        this.couponId = couponId;
        this.couponNo = couponNo;
        this.isDCR = isDCR;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getCouponNo() {
        return couponNo;
    }

    public void setCouponNo(String couponNo) {
        this.couponNo = couponNo;
    }

    public Boolean getIsDCR() {
        return isDCR;
    }

    public void setIsDCR(Boolean isDCR) {
        this.isDCR = isDCR;
    }

    public Long getCouponId() {
        return couponId;
    }

    public void setCouponId(Long couponId) {
        this.couponId = couponId;
    }
}
