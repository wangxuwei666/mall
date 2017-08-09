/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.balance.domain;

/**
 * 支付方式
 * @author zh
 * @version $Id: PayMethod.java, v 0.1 2017年5月16日 下午2:56:26 zh Exp $
 */
public enum PayMethod {
    
    WX_JS_PAY("微信公众号支付"),
    
    WX_QRCODE_PAY("微信公众号支付"),
    
    ALI_QRCODE_PAY("支付宝扫码支付");
    
    private String intro;
    
    private PayMethod(String intro) {
        this.intro = intro;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }
    
}
