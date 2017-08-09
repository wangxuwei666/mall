/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.receiver.domain;

import com.youmu.mall.base.domain.BaseDomain;

import io.swagger.annotations.ApiModelProperty;

/**
 * 用户端-收货人地址
 * @author yujiahao
 * @version $Id: Receiver.java, v 0.1 2017年3月3日 上午10:24:10 yujiahao Exp $
 */
public class Receiver extends BaseDomain{
    @ApiModelProperty(value = "用户id")
    private Long userId;
    
    @ApiModelProperty(value = "收货人")
    private String consignee;
    
    @ApiModelProperty(value = "电话")
    private String phone;
    
    @ApiModelProperty(value = "省")
    private Integer provinceCode;
    
    @ApiModelProperty(value = "市")
    private Integer cityCode;
    
    @ApiModelProperty(value = "区")
    private Integer districtCode;
    
    @ApiModelProperty(value = "详细地址")
    private String detailAddress;
    
    @ApiModelProperty(value = "是否默认")
    private Integer isDefault;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(Integer provinceCode) {
        this.provinceCode = provinceCode;
    }

    public Integer getCityCode() {
        return cityCode;
    }

    public void setCityCode(Integer cityCode) {
        this.cityCode = cityCode;
    }

    public Integer getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(Integer districtCode) {
        this.districtCode = districtCode;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    public Integer getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
    }

    
    
}
