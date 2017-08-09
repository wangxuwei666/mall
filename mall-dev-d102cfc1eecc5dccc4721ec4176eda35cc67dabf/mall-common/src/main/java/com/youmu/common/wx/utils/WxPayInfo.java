/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.common.wx.utils;

/**
 * 微信支付状态查询
 * @author zh
 * @version $Id: WxPayStatus.java, v 0.1 2017年4月22日 下午4:39:30 zh Exp $
 */
public class WxPayInfo {
    
    private Integer status;
    
    private String outTradeNo;
    
    private String transactionId;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
    
}
