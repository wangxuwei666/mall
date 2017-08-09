/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.common.alipay.utils;

/**
 * 支付宝支付结果状态
 * @author zh
 * @version $Id: AliPayStatus.java, v 0.1 2017年4月20日 上午9:53:20 zh Exp $
 */
public enum AliPayStatus {
    
 TRADE_FINISHED, // 交易完成    false（不触发通知）
 
 TRADE_SUCCESS , //支付成功    true（触发通知）
 
 WAIT_BUYER_PAY, //交易创建    false（不触发通知）
 
 TRADE_CLOSED  , //交易关闭    true（触发通知）
 
 TRADE_NOT_FOUND  , //不存在 
 
}
