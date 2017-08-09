/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.balance.domain;

/**
 * 支付交易状态
 * @author zh
 * @version $Id: PayTradeStatus.java, v 0.1 2017年5月16日 上午10:53:30 zh Exp $
 */
public enum PayTradeStatus {
    
    /** 交易待支付 */
    TRADE_WAIT_PAY,
    
    /** 交易成功 */
    TRADE_SUCCESS,
    
    /** 交易超时 */
    TRADE_TIMEOUT
}
