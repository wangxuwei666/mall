/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.balance.domain;

/**
 * 充值交易状态.
 * @author zh
 * @version $Id: ChargeTradeStatus.java, v 0.1 2017年5月16日 上午10:51:17 zh Exp $
 */
public enum ChargeTradeStatus {
    
     /** 交易待支付 */
    TRADE_WAIT_PAY("待支付"),
    
    /** 交易成功 */
    TRADE_SUCCESS("支付成功"),
    
    /** 交易超时 */
    TRADE_TIMEOUT("已超时"),
    
    /** 交易取消 */
    TRADE_CANCEL("已取消");
    
    private ChargeTradeStatus(String intro) {
        this.intro = intro;
    }

    private String intro;

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }
}
