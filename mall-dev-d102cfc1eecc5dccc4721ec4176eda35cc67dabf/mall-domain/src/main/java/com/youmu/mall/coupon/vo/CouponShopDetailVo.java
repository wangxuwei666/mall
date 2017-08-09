/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.coupon.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 优惠券商城列表显示对象.
 * @author zh
 * @version $Id: CouponShopListVo.java, v 0.1 2017年2月27日 上午10:16:36 zh Exp $
 */
public class CouponShopDetailVo {
    
    /** 我的优惠券id */
    private Long id;
    
    /** 状态 */
    private Integer status;
    
    /** 优惠券id */
    private Long couponId;
    
    /** 优惠券编号 */
    private String couponNo;
    
    /** 类型名称 */
    private String typeName;
    
    /** 使用描述 */
    private String useIntro;
    
    /** 有效期开始时间 */
    @JsonFormat(pattern="yyyy.MM.dd", timezone="GMT+8")
    private Date gmtStart;
    
    /** 有效期结束日期 */
    @JsonFormat(pattern="yyyy.MM.dd", timezone="GMT+8")
    private Date gmtEnd;

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

    public String getCouponNo() {
        return couponNo;
    }

    public void setCouponNo(String couponNo) {
        this.couponNo = couponNo;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}
