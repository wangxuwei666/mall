/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.common.alipay.utils;

/**
 * 支付二维码.
 * @author zh
 * @version $Id: QRCodePayOrder.java, v 0.1 2017年4月19日 下午7:14:09 zh Exp $
 */
public class AliPayQRCode {
    
    /** 平台的订单 */
    private String outTradeNo;
    
    /** 生成的支付二维码 */
    private String qrCode;

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }
    
}
