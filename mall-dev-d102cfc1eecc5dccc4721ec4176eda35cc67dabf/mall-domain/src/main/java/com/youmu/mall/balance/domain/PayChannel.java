/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.balance.domain;

/**
 * 
 * @author zh
 * @version $Id: PayMothod.java, v 0.1 2017年5月16日 下午2:13:38 zh Exp $
 */
public enum PayChannel {
    
    WX_PAY("微信支付"),
    
    ALI_PAY("支付支付");
    
    private String intro;
    
    private PayChannel(String intro) {
        this.intro = intro;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }
}
