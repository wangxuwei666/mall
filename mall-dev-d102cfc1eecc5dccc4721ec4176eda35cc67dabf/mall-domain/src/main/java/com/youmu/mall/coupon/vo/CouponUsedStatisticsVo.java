/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.coupon.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 优惠券统计
 * @author zh
 * @version $Id: CouponUsedStatisticsVo.java, v 0.1 2017年3月13日 下午6:31:41 zh Exp $
 */
@ApiModel(description="优惠券使用统计信息")
public class CouponUsedStatisticsVo {
    
    @ApiModelProperty(value="类型名称")
    private String typeName;
    
    @ApiModelProperty(value="使用数量")
    private Integer usedNum;
    
    @ApiModelProperty(value="领取数量")
    private Integer issuedNum;
    
    @ApiModelProperty(value="过期数量")
    private Integer expiredNum;
    
    @ApiModelProperty(value="开始使用日期")
    @JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
    private Date gmtUsedFrom;
    
    @ApiModelProperty(value="使用结束日期")
    @JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
    private Date gmtUsedTo;

    /**
     * Getter method for property <tt>typeName</tt>.
     * 
     * @return property value of typeName
     */
    public String getTypeName() {
        return typeName;
    }

    /**
     * Setter method for property <tt>typeName</tt>.
     * 
     * @param typeName value to be assigned to property typeName
     */
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    /**
     * Getter method for property <tt>usedNum</tt>.
     * 
     * @return property value of usedNum
     */
    public Integer getUsedNum() {
        return usedNum;
    }

    /**
     * Setter method for property <tt>usedNum</tt>.
     * 
     * @param usedNum value to be assigned to property usedNum
     */
    public void setUsedNum(Integer usedNum) {
        this.usedNum = usedNum;
    }

    /**
     * Getter method for property <tt>issuedNum</tt>.
     * 
     * @return property value of issuedNum
     */
    public Integer getIssuedNum() {
        return issuedNum;
    }

    /**
     * Setter method for property <tt>issuedNum</tt>.
     * 
     * @param issuedNum value to be assigned to property issuedNum
     */
    public void setIssuedNum(Integer issuedNum) {
        this.issuedNum = issuedNum;
    }

    /**
     * Getter method for property <tt>expiredNum</tt>.
     * 
     * @return property value of expiredNum
     */
    public Integer getExpiredNum() {
        return expiredNum;
    }

    /**
     * Setter method for property <tt>expiredNum</tt>.
     * 
     * @param expiredNum value to be assigned to property expiredNum
     */
    public void setExpiredNum(Integer expiredNum) {
        this.expiredNum = expiredNum;
    }

    /**
     * Getter method for property <tt>gmtUsedFrom</tt>.
     * 
     * @return property value of gmtUsedFrom
     */
    public Date getGmtUsedFrom() {
        return gmtUsedFrom;
    }

    /**
     * Setter method for property <tt>gmtUsedFrom</tt>.
     * 
     * @param gmtUsedFrom value to be assigned to property gmtUsedFrom
     */
    public void setGmtUsedFrom(Date gmtUsedFrom) {
        this.gmtUsedFrom = gmtUsedFrom;
    }

    /**
     * Getter method for property <tt>gmtUsedTo</tt>.
     * 
     * @return property value of gmtUsedTo
     */
    public Date getGmtUsedTo() {
        return gmtUsedTo;
    }

    /**
     * Setter method for property <tt>gmtUsedTo</tt>.
     * 
     * @param gmtUsedTo value to be assigned to property gmtUsedTo
     */
    public void setGmtUsedTo(Date gmtUsedTo) {
        this.gmtUsedTo = gmtUsedTo;
    }
    
}
