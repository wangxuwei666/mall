/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.balance.domain;

import java.math.BigDecimal;

import com.youmu.mall.base.domain.BaseDomain;

/**
 * 余额支付订单编号
 * @author zh
 * @version $Id: BalancePayBill.java, v 0.1 2017年5月16日 上午10:23:30 zh Exp $
 */
public class BalancePayBill extends BaseDomain {

    private static final long serialVersionUID = -4262110332498999901L;

    /** 订单编号 */
    private String            tradeNo;

    /** 总金额 */
    private BigDecimal        totalAomunt;

    /** 交易状态 */
    private String            tradeStatus;

    /** 用户id  */
    private String            userId;

    /** 用户手机号码 */
    private String            userMobile;

    /** 用户用户名 */
    private String            userUsername;

    /** 外部订单编号  */
    private String            outTradeNO;

    /** 订单标题 */
    private String            orderTitle;

    /** 订单描述  */
    private String            orderDesc;

    /** 订单备注 */
    private String            remark;

    /** 订单过期时间 */
    private String            gmtExpire;

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public BigDecimal getTotalAomunt() {
        return totalAomunt;
    }

    public void setTotalAomunt(BigDecimal totalAomunt) {
        this.totalAomunt = totalAomunt;
    }

    public String getTradeStatus() {
        return tradeStatus;
    }

    public void setTradeStatus(String tradeStatus) {
        this.tradeStatus = tradeStatus;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
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

    public String getOutTradeNO() {
        return outTradeNO;
    }

    public void setOutTradeNO(String outTradeNO) {
        this.outTradeNO = outTradeNO;
    }

    public String getOrderTitle() {
        return orderTitle;
    }

    public void setOrderTitle(String orderTitle) {
        this.orderTitle = orderTitle;
    }

    public String getOrderDesc() {
        return orderDesc;
    }

    public void setOrderDesc(String orderDesc) {
        this.orderDesc = orderDesc;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getGmtExpire() {
        return gmtExpire;
    }

    public void setGmtExpire(String gmtExpire) {
        this.gmtExpire = gmtExpire;
    }

}
