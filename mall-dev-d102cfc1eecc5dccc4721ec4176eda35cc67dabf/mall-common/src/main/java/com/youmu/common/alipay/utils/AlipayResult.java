/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.common.alipay.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * 支付宝支付的结果.
 * @author zh
 * @version $Id: AlipayResult.java, v 0.1 2017年3月1日 下午3:59:01 zh Exp $
 */
public class AlipayResult {
    
    /** 支付宝订单 */
    private String tradeNo;
    
    /** 交易状态 */
    // TRADE_FINISHED   交易完成    false（不触发通知）
    // TRADE_SUCCESS   支付成功    true（触发通知）
    // WAIT_BUYER_PAY  交易创建    false（不触发通知）
    // TRADE_CLOSED    交易关闭    true（触发通知）
    private String tradeStatus;
    
    /** 总金额 */
    private String totalAmount;
    
    /** 平台的订单 */
    private String outTradeNo;
    
    /** 支付状态  */
    // 1 支付成功
    // 2 交易超时、交易结束、交易退款完成
    private AliPayStatus  payStatus;
    
    /** 支付宝商户id */
    private String sellerId;
    
    /** 支付宝商户的appid */
    private String appId;
    
    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getTradeStatus() {
        return tradeStatus;
    }

    public void setTradeStatus(String tradeStatus) {
        this.tradeStatus = tradeStatus;
        if(StringUtils.isNotBlank(tradeStatus)) {
           payStatus = AliPayStatus.valueOf(tradeStatus);
        } else {
           payStatus = AliPayStatus.TRADE_NOT_FOUND;
        }
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public AliPayStatus getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(AliPayStatus payStatus) {
        this.payStatus = payStatus;
    }

        /**
         * Getter method for property <tt>sellerId</tt>.
         * 
         * @return property value of sellerId
         */
    public String getSellerId() {
        return sellerId;
    }

        /**
         * Setter method for property <tt>sellerId</tt>.
         * 
         * @param sellerId value to be assigned to property sellerId
         */
    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

            /**
             * Getter method for property <tt>appId</tt>.
             * 
             * @return property value of appId
             */
        public String getAppId() {
            return appId;
        }

            /**
             * Setter method for property <tt>appId</tt>.
             * 
             * @param appId value to be assigned to property appId
             */
        public void setAppId(String appId) {
            this.appId = appId;
        }
}
