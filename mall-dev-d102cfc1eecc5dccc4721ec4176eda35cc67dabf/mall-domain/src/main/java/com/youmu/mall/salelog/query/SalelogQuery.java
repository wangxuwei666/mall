/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.salelog.query;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.youmu.mall.base.query.PageQuery;

/**
 * 
 * @author yujiahao
 * @version $Id: SalelogQuery.java, v 0.1 2017年3月15日 下午5:33:42 yujiahao Exp $
 */
public class SalelogQuery extends PageQuery{
    
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date beginDate;
    
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date endDate;

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    
    

}
