/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.balance.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.youmu.mall.base.domain.BaseDomain;

/**
 * 余额充值账单
 * @author zh
 * @version $Id: BalanceChargeBill.java, v 0.1 2017年5月16日 上午10:13:31 zh Exp $
 */
public class BalanceChargeBill extends BaseDomain {

    private static final long serialVersionUID = -6458086129256390464L;

    /** 充值订编号 (年月日 YYYYMMDD 加 订单编号) */
    private String            tradeNo;

    /** 充值金额 */
    private BigDecimal            amount;

    /** 支付渠道 */
    private String            payChannel;

    /** 支付渠道名称 */
    private String            payChannelName;

    /** 交易状态  */
    private String            tradeStatus;

    /** 支付渠道订单编号 */
    private String            outTradeNo;

    /** 用户id */
    private Long              userId;

    /** 用户手机号 */
    private String            userMobile;

    /** 用户名 */
    private String            userUsername;

    /** 备注 */
    private String            remark;

    /** 过期时间 */
    private Date            gmtExpire;

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getPayChannel() {
        return payChannel;
    }

    public void setPayChannel(String payChannel) {
        this.payChannel = payChannel;
    }

    public String getPayChannelName() {
        return payChannelName;
    }

    public void setPayChannelName(String payChannelName) {
        this.payChannelName = payChannelName;
    }

    public String getTradeStatus() {
        return tradeStatus;
    }

    public void setTradeStatus(String tradeStatus) {
        this.tradeStatus = tradeStatus;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public String getUserUsername() {
        return userUsername;
    }

    public void setUserUsername(String userUsername) {
        this.userUsername = userUsername;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getGmtExpire() {
        return gmtExpire;
    }

    public void setGmtExpire(Date gmtExpire) {
        this.gmtExpire = gmtExpire;
    }

        /**
         * Getter method for property <tt>amount</tt>.
         * 
         * @return property value of amount
         */
    public BigDecimal getAmount() {
        return amount;
    }

        /**
         * Setter method for property <tt>amount</tt>.
         * 
         * @param amount value to be assigned to property amount
         */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

}
