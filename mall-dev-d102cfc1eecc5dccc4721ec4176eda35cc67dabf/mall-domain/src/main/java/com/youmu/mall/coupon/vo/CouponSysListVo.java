/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.coupon.vo;

import java.math.BigDecimal;

/**
 * 优惠券后台系统列表对象
 * @author zh
 * @version $Id: CouponListVo.java, v 0.1 2017年2月26日 下午6:17:09 zh Exp $
 */
public class CouponSysListVo {
    
    /** 类型名称 */
    private String typeName;
    
    /** 优惠券金额 */
    private BigDecimal amount;
    
    /** 名称 */
    private String name;
    
    /** 使用限制 */
    private String useLimit;
    
    /** 领取张数限制 */
    private String issueLimit;
    
    /** 领取的时间区间 */
    private String issueDateRange;
    
    /** 可以使用的时间区间 */
    private String useDateRange;
    
    /** 总数量 */
    private Integer total;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUseLimit() {
        return useLimit;
    }

    public void setUseLimit(String useLimit) {
        this.useLimit = useLimit;
    }

    public String getIssueLimit() {
        return issueLimit;
    }

    public void setIssueLimit(String issueLimit) {
        this.issueLimit = issueLimit;
    }

    public String getIssueDateRange() {
        return issueDateRange;
    }

    public void setIssueDateRange(String issueDateRange) {
        this.issueDateRange = issueDateRange;
    }

    public String getUseDateRange() {
        return useDateRange;
    }

    public void setUseDateRange(String useDateRange) {
        this.useDateRange = useDateRange;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
