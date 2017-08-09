package com.youmu.mall.receiver.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "用户端-收货人地址")
public class ReceiverVO {

	@ApiModelProperty(value = "id")
	private long id;
	  
	@ApiModelProperty(value = "省")
	private String provinceName;
	
	@ApiModelProperty(value = "市")
	private String cityName;
	
	@ApiModelProperty(value = "区")
	private String districtName;
	
	@ApiModelProperty(value = "详细地址")
	private String detailAddress;
	
	@ApiModelProperty(value = "省")
    private int provinceCode;
    
    @ApiModelProperty(value = "市")
    private int cityCode;
    
    @ApiModelProperty(value = "区")
    private int districtCode;
	
	@ApiModelProperty(value = "电话")
	private String phone;
	
	@ApiModelProperty(value = "是否默认")
	private Integer isDefault;
	
	@ApiModelProperty(value = "收件人")
	private String consignee;
	
	@ApiModelProperty(value = "是否删除")
	private boolean deleteFlag;
	
	

    public int getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(int provinceCode) {
        this.provinceCode = provinceCode;
    }

    public int getCityCode() {
        return cityCode;
    }

    public void setCityCode(int cityCode) {
        this.cityCode = cityCode;
    }

    public int getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(int districtCode) {
        this.districtCode = districtCode;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public boolean isDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
	
	
	
	
	
}