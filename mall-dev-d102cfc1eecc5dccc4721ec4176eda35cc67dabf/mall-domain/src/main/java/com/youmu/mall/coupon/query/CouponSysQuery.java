/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.coupon.query;

import com.youmu.mall.base.query.PageQuery;

/**
 * 优惠券查询对象.
 * @author zh
 * @version $Id: CouponQuery.java, v 0.1 2017年2月26日 下午6:06:37 zh Exp $
 */
public class CouponSysQuery extends PageQuery {
    
    /** 1 领取时间未过期  2 领取时间已经过期  */
    private Integer issueStatus;
    
    /** 优惠券 */
    private Integer status;
    
    /** 商户的类型id 优惠券的类型  */
    private  Long businessTypeId;
    
    /** 商家的id */
    private  Long businessId;

    public Integer getIssueStatus() {
        return issueStatus;
    }

    public void setIssueStatus(Integer issueStatus) {
        this.issueStatus = issueStatus;
    }

    public Long getBusinessTypeId() {
        return businessTypeId;
    }

    public void setBusinessTypeId(Long businessTypeId) {
        this.businessTypeId = businessTypeId;
    }

    public Long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }

        /**
         * Getter method for property <tt>status</tt>.
         * 
         * @return property value of status
         */
    public Integer getStatus() {
        return status;
    }

        /**
         * Setter method for property <tt>status</tt>.
         * 
         * @param status value to be assigned to property status
         */
    public void setStatus(Integer status) {
        this.status = status;
    }
}
