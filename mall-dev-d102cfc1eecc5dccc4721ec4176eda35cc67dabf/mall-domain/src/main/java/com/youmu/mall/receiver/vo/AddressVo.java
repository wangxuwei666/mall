package com.youmu.mall.receiver.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "地址对象")
public class AddressVo {
	
	@ApiModelProperty(value = "code")
	private int code;
	
	@ApiModelProperty(value = "name")
	private String name;
	
	@ApiModelProperty(value = "up_code")
	private int upCode;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getUpCode() {
		return upCode;
	}

	public void setUpCode(int upCode) {
		this.upCode = upCode;
	}
	
	
}
