/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.bus.query;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.youmu.mall.base.query.PageQuery;

/**
 * 商户后台查询对象.
 * @author zh
 * @version $Id: BusinessQuery.java, v 0.1 2017年2月28日 上午10:25:09 zh Exp $
 */
public class BusinessQuery extends PageQuery {
    
    private Long typeId;
    
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date gmtEnd;
    
    /** 是否已经过期 */
    private Boolean expired;

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public Date getGmtEnd() {
        return gmtEnd;
    }

    public void setGmtEnd(Date gmtEnd) {
        this.gmtEnd = gmtEnd;
    }

        /**
         * Getter method for property <tt>expired</tt>.
         * 
         * @return property value of expired
         */
    public Boolean getExpired() {
        return expired;
    }

        /**
         * Setter method for property <tt>expired</tt>.
         * 
         * @param expired value to be assigned to property expired
         */
    public void setExpired(Boolean expired) {
        this.expired = expired;
    }
}
