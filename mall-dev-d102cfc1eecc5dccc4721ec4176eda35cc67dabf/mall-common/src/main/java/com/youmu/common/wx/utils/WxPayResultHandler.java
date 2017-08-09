/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.common.wx.utils;

/**
 * 微信支付结果处理
 * @author zh
 * @version $Id: WxPayResultHandler.java, v 0.1 2017年5月16日 上午11:36:38 zh Exp $
 */
public interface WxPayResultHandler {
    
    boolean handlePayResult(String tradeNo, String outTradeNo);
    
}
