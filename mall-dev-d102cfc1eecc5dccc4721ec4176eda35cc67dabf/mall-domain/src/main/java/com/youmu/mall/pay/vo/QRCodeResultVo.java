/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.pay.vo;

/**
 * 支付统一返回对象
 * @author yujiahao
 * @version $Id: PayVo.java, v 0.1 2017年4月21日 下午5:34:23 yujiahao Exp $
 */
public class QRCodeResultVo {
    /**外部订单号*/
    private String outTradeNumber;
    
    /**订单支付总金额*/
    private String totalAmount;
    
    /**二维码*/
    private String QRCode;
    
    
    public QRCodeResultVo() {
        super();
    }

    
    public QRCodeResultVo(String outTradeNumber, String totalAmount, String qRCode) {
        super();
        this.outTradeNumber = outTradeNumber;
        this.totalAmount = totalAmount;
        this.QRCode = qRCode;
    }



    public String getOutTradeNumber() {
        return outTradeNumber;
    }
    public void setOutTradeNumber(String outTradeNumber) {
        this.outTradeNumber = outTradeNumber;
    }
    public String getTotalAmount() {
        return totalAmount;
    }
    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }
    public String getQRCode() {
        return QRCode;
    }
    public void setQRCode(String qRCode) {
        this.QRCode = qRCode;
    }
    
    
}
