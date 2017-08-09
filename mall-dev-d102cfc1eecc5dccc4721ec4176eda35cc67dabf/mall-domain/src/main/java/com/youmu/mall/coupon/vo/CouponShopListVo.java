/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.coupon.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.youmu.common.upload.utils.ImageUploadUtils;

/**
 * 优惠券商城列表显示对象.
 * @author zh
 * @version $Id: CouponShopListVo.java, v 0.1 2017年2月27日 上午10:16:36 zh Exp $
 */
public class CouponShopListVo {
    
    /** 优惠券id */
    private Long id;
    
    /** 优惠券名称 */
    private String couponName;
    
    /** 类型名称 */
    private String typeName;
    
    /** 类型的图标 */
    private String typeIcon;
    
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
    
    /** 是否可以使用  */
    private Integer status;
    
    /** 我的优惠券详情的ID 当状态为 0 可使用时 可以使用该ID来查询优惠券的详情 */
    private Integer detailId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getTypeIcon() {
        return ImageUploadUtils.fillPath(typeIcon);
    }

    public void setTypeIcon(String typeIcon) {
        this.typeIcon = typeIcon;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getDetailId() {
        return detailId;
    }

    public void setDetailId(Integer detailId) {
        this.detailId = detailId;
    }
}
