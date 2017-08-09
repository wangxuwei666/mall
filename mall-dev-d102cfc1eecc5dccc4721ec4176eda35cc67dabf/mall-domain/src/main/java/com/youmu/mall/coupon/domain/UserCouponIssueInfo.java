/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.coupon.domain;

/**
 * 用户优惠券领取信息.
 * 
 * @author zh
 * @version $Id: UserCouponIssueInfo.java, v 0.1 2017年2月27日 上午11:33:59 zh Exp $
 */
public class UserCouponIssueInfo {
    
    /** 用户id */
    private Long userId;
    
    /** 优惠券id */
    private Long couponId;
    
    /** 是否是装修贷款优惠券 */
    private Boolean isDCR;
    
    /** 我的领取数量  */
    private Integer issueCount;
    
    /** 领取总的数量  */
    private Integer issueTotal;
    
    /** 总数量  */
    private Integer total;
    
    /** 一个用户可以领取多少个 */
    private Integer issueLimit;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCouponId() {
        return couponId;
    }

    public void setCouponId(Long couponId) {
        this.couponId = couponId;
    }

    public Integer getIssueCount() {
        return issueCount;
    }

    public void setIssueCount(Integer issueCount) {
        this.issueCount = issueCount;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getIssueLimit() {
        return issueLimit;
    }

    public void setIssueLimit(Integer issueLimit) {
        this.issueLimit = issueLimit;
    }

    public Integer getIssueTotal() {
        return issueTotal;
    }

    public void setIssueTotal(Integer issueTotal) {
        this.issueTotal = issueTotal;
    }

    public Boolean getIsDCR() {
        return isDCR;
    }

    public void setIsDCR(Boolean isDCR) {
        this.isDCR = isDCR;
    }
}
