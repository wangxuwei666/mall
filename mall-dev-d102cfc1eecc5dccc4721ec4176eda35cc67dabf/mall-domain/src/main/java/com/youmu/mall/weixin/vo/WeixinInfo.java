package com.youmu.mall.weixin.vo;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "微信支付信息")
public class WeixinInfo {
	
	@ApiModelProperty(value="应用ID")
    private String appid ;
	
	@ApiModelProperty(value="商户号")
    private String partnerid;
	
	@ApiModelProperty(value="预支付交易会话ID")
    private String prepayid;
	
	@ApiModelProperty(value="随机字符串")
    private String noncestr =UUID.randomUUID().toString().replaceAll("-","");
    
    @ApiModelProperty(value="时间戳")
    private String timestamp = String.valueOf(System.currentTimeMillis()/1000);
    
    @ApiModelProperty(value="签名")
    private String paySign;
    
    public WeixinInfo() {
    }
    
    public WeixinInfo(String appid,String partnerid,String prepayid) {
        this.appid = appid;
        this.partnerid = partnerid;
        this.prepayid =prepayid;
    }

    

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getPartnerid() {
        return partnerid;
    }

    public void setPartnerid(String partnerid) {
        this.partnerid = partnerid;
    }

    public String getPrepayid() {
        return prepayid;
    }

    public void setPrepayid(String prepayid) {
        this.prepayid = prepayid;
    }
    
    public String getNoncestr() {
        return noncestr;
    }

    public void setNoncestr(String noncestr) {
        this.noncestr = noncestr;
    }
    

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getPaySign() {
        return paySign;
    }

    public void setPaySign(String paySign) {
        this.paySign = paySign;
    }

    
    

	
    


}