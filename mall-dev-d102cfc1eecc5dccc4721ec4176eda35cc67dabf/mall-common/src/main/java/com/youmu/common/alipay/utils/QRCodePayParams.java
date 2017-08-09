/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.common.alipay.utils;

/**
 * 二维码支付参数.
 * @author zh
 * @version $Id: QRCodePayParams.java, v 0.1 2017年4月19日 下午6:54:29 zh Exp $
 */
public class QRCodePayParams {
    
    /** 商品订单号 */
    private String out_trade_no;
    
    /** 总金额 */
    private String total_amount;
    
    /** 支付主题  */
    private String subject;
    
    /** 支付门店的id */
    private String store_id = "pc";
    
    /** 支付超时时间 */
    private String timeout_express = "5m";
    
    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(String total_amount) {
        this.total_amount = total_amount;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }

    public String getTimeout_express() {
        return timeout_express;
    }

    public void setTimeout_express(String timeout_express) {
        this.timeout_express = timeout_express;
    }
}
