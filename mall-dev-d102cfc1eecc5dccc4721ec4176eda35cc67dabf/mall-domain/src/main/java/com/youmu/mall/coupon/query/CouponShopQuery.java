/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.coupon.query;

import com.youmu.mall.base.query.PageQuery;

/**
 * 优惠券商城查询对象.
 * @author zh
 * @version $Id: CouponQuery.java, v 0.1 2017年2月26日 下午6:06:37 zh Exp $
 */
public class CouponShopQuery extends PageQuery {
    
    /** 当前用户的id */
    private Long uid;
    
    /** 优惠券的使用状态  0 未使用  1 已经使用  2 已经过期 */
    private Integer status;
    
    /** 是否只是装修贷款的优惠券  */
    private Boolean onlyDcr;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }
    
    public Boolean getOnlyDcr() {
        return onlyDcr;
    }

    public void setOnlyDcr(Boolean onlyDcr) {
        this.onlyDcr = onlyDcr;
    }
}
