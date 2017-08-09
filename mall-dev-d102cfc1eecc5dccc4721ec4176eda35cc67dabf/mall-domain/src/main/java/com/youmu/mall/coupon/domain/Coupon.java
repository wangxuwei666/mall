/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.coupon.domain;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.youmu.mall.base.domain.BaseDomain;
import com.youmu.mall.bus.domain.Business;
import com.youmu.mall.bus.domain.BusinessType;

/**
 * 优惠券数据库持久化对象;
 * @author zh
 * @version $Id: Coupon.java, v 0.1 2017年2月26日 下午2:48:59 zh Exp $
 */
@SuppressWarnings("serial")
public class Coupon extends BaseDomain {
    
    /** 优惠券名称 */
    private String name;
    
    /** 优惠券金额  */
    private BigDecimal amount;
    
    /** 优惠券类型  */
    private BusinessType businessType;
    
    /** 是否是装修优惠券  */
    private Boolean isDCR;
    
    /** 优惠券商户  */
    private Business business;
    
    /** 单人领取数量限制 */
    private Integer issueLimit;
    
    /** 可领取开始时间  */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date gmtIssueStart;
    
    /** 可领取结束时间  */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date gmtIssueEnd;
    
    /** 有效期开始时间  */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date gmtStart;
    
    /** 有效期结束时间  */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date gmtEnd;
    
    /** 总数量  */
    private Integer total;
    
    /** 使用描述 */
    private String useIntro;
    
    /** 满多少可以使用 */
    private BigDecimal useAmount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Business getBusiness() {
        return business;
    }

    public void setBusiness(Business business) {
        this.business = business;
    }

    public Date getGmtStart() {
        return gmtStart;
    }

    public void setGmtStart(Date gmtStart) {
        this.gmtStart = gmtStart;
    }

    public Date getGmtEnd() {
        return gmtEnd;
    }

    public void setGmtEnd(Date gmtEnd) {
        this.gmtEnd = gmtEnd;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getUseIntro() {
        return useIntro;
    }

    public void setUseIntro(String useIntro) {
        this.useIntro = useIntro;
    }

    public BigDecimal getUseAmount() {
        return useAmount;
    }

    public void setUseAmount(BigDecimal useAmount) {
        this.useAmount = useAmount;
    }

    public Date getGmtIssueStart() {
        return gmtIssueStart;
    }

    public void setGmtIssueStart(Date gmtIssueStart) {
        this.gmtIssueStart = gmtIssueStart;
    }

    public Date getGmtIssueEnd() {
        return gmtIssueEnd;
    }

    public void setGmtIssueEnd(Date gmtIssueEnd) {
        this.gmtIssueEnd = gmtIssueEnd;
    }

    public Integer getIssueLimit() {
        return issueLimit;
    }

    public void setIssueLimit(Integer issueLimit) {
        this.issueLimit = issueLimit;
    }

    public BusinessType getBusinessType() {
        return businessType;
    }

    public void setBusinessType(BusinessType businessType) {
        this.businessType = businessType;
    }

    public Boolean getIsDCR() {
        return isDCR;
    }

    public void setIsDCR(Boolean isDCR) {
        this.isDCR = isDCR;
    }
}
