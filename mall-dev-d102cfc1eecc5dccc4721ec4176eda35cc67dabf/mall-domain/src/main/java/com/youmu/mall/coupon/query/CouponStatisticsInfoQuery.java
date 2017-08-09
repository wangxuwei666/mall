/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.coupon.query;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.youmu.mall.base.query.PageQuery;

/**
 * 优惠券统计信息查询接口.
 * @author zh
 * @version $Id: CouponStatisticsInfoQuery.java, v 0.1 2017年3月13日 下午6:27:21 zh Exp $
 */
public class CouponStatisticsInfoQuery extends PageQuery {
    
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date gmtUsedStart;
    
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date gmtUsedEnd;

    /**
     * Getter method for property <tt>gmtUsedStart</tt>.
     * 
     * @return property value of gmtUsedStart
     */
    public Date getGmtUsedStart() {
        return gmtUsedStart;
    }

    /**
     * Setter method for property <tt>gmtUsedStart</tt>.
     * 
     * @param gmtUsedStart value to be assigned to property gmtUsedStart
     */
    public void setGmtUsedStart(Date gmtUsedStart) {
        this.gmtUsedStart = gmtUsedStart;
    }

    /**
     * Getter method for property <tt>gmtUsedEnd</tt>.
     * 
     * @return property value of gmtUsedEnd
     */
    public Date getGmtUsedEnd() {
        return gmtUsedEnd;
    }

    /**
     * Setter method for property <tt>gmtUsedEnd</tt>.
     * 
     * @param gmtUsedEnd value to be assigned to property gmtUsedEnd
     */
    public void setGmtUsedEnd(Date gmtUsedEnd) {
        this.gmtUsedEnd = gmtUsedEnd;
    }
    
}
