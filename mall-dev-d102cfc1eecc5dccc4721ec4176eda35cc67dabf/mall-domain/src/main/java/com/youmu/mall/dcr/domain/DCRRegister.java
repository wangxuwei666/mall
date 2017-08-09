package com.youmu.mall.dcr.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * 家装登记
 * @author wxw
 */
public class DCRRegister {

	private String name;
   
    private String mobile;
    
    private String address;
    
    private String category;
    
    private String dcr_package;
    
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date gmt_create;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDcr_package() {
		return dcr_package;
	}

	public void setDcr_package(String dcr_package) {
		this.dcr_package = dcr_package;
	}
	
	public Date getGmt_create() {
		return gmt_create;
	}

	public void setGmt_create(Date gmt_create) {
		this.gmt_create = gmt_create;
	}


	public DCRRegister() {
		super();
	}

	@Override
	public String toString() {
		return "DCRRegister [name=" + name + ", mobile=" + mobile + ", address=" + address + ", category=" + category
				+ ", dcr_package=" + dcr_package + "]";
	}

	public DCRRegister(String name, String mobile, String address, String category, String dcr_package, Date gmt_create) {
		super();
		this.name = name;
		this.mobile = mobile;
		this.address = address;
		this.category = category;
		this.dcr_package = dcr_package;
	}
	
    
}