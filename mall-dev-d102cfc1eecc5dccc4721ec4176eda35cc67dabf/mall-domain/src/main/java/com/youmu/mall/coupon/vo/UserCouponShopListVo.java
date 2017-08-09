/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.coupon.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 优惠券商城用户列表显示对象.
 * @author zh
 * @version $Id: CouponShopListVo.java, v 0.1 2017年2月27日 上午10:16:36 zh Exp $
 */
public class UserCouponShopListVo {
    
    /** 我的优惠券id */
    private Long id;
    
    /** 优惠券名称 */
    private String couponName;
    
    /** 优惠券id */
    private Long couponId;
    
    /** 类型名称 */
    private String typeName;
    
    /** 是否是装修优惠券 */
    private Boolean isDecorate;
    
    /** 使用描述 */
    private String useIntro;
    
    /** 有效期开始时间 */
    @JsonFormat(pattern="yyyy.MM.dd", timezone="GMT+8")
    private Date gmtStart;
    
    /** 有效期结束日期 */
    @JsonFormat(pattern="yyyy.MM.dd", timezone="GMT+8")
    private Date gmtEnd;
    
    /** 修改 */
    private Integer status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCouponId() {
        return couponId;
    }

    public void setCouponId(Long couponId) {
        this.couponId = couponId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getUseIntro() {
        return useIntro;
    }

    public void setUseIntro(String useIntro) {
        this.useIntro = useIntro;
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

    public Boolean getIsDecorate() {
        return isDecorate;
    }

    public void setIsDecorate(Boolean isDecorate) {
        this.isDecorate = isDecorate;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
