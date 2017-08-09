/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.coupon.vo;

/**
 * 优惠券核销历史列表对象.
 * @author zh
 * @version $Id: CouponVerifiHistoryListVo.java, v 0.1 2017年3月5日 上午11:42:40 zh Exp $
 */
public class CouponVerifiHistoryListVo {
    
    /** 优惠券的编号 */
    private String couponNo;
    
    /** 用户的手机号码  */
    private String userMobile;
    
    /** 是否核销完成  */
    private Boolean isComplete;
    
    /** 用于提交合同的数据 */
    private String orderNo;

    public String getCouponNo() {
        return couponNo;
    }

    public void setCouponNo(String couponNo) {
        this.couponNo = couponNo;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public Boolean getIsComplete() {
        return isComplete;
    }

    public void setIsComplete(Boolean isComplete) {
        this.isComplete = isComplete;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
}
