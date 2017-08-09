/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.balance.dao;

import com.youmu.mall.balance.domain.BalanceChargeBill;

/**
 * 余额充值dao
 * @author zh
 * @version $Id: BalanceChargeBillDao.java, v 0.1 2017年5月16日 下午2:23:16 zh Exp $
 */
public interface BalanceChargeBillDao {

    /**
     * 保存一个充值订单
     * @param bill
     * @return
     */
    BalanceChargeBill save(BalanceChargeBill bill);

    /**
     * 通过支付订单编号查询充值账单
     * @param tradeNo
     * @return
     */
    BalanceChargeBill findByTradeNo(String tradeNo);

}
