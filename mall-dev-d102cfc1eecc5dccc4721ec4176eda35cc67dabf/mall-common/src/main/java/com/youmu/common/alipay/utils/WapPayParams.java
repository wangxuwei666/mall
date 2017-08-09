/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.common.alipay.utils;

import java.math.BigDecimal;

/**
 * 支付宝wpa支付订单
 * @author zh
 * @version $Id: WpaPayOrder.java, v 0.1 2017年3月1日 下午3:24:17 zh Exp $
 */
public class WapPayParams {
    
    /** 订单编号 */
    private String out_trade_no;
    
    /** 总金额  */
    private BigDecimal total_amount;
    
    /** 订单的主体 */
    private String subject;
    
    /** 支付类型  */
    private String product_code = "QUICK_WAP_PAY";

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public BigDecimal getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(BigDecimal total_amount) {
        this.total_amount = total_amount;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getProduct_code() {
        return product_code;
    }

    public void setProduct_code(String product_code) {
        this.product_code = product_code;
    }
}
