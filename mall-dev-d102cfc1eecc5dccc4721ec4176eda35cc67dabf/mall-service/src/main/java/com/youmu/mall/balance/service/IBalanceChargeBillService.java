/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.balance.service;

import java.math.BigDecimal;
import java.util.List;
import com.youmu.mall.balance.domain.BalanceChargeBill;
import com.youmu.mall.balance.domain.PayChannel;

/**
 * 余额充值订单服务.
 * @author zh
 * @version $Id: IBalanceChargeBillService.java, v 0.1 2017年5月16日 上午10:57:49 zh Exp $
 */
public interface IBalanceChargeBillService {

    /**
     * 创建充值账单
     * @param userId 充值用户id
     * @param amount 充值金额
     * @param remark 备注
     * @return
     */
    BalanceChargeBill create(Long userId, BigDecimal amount, String remark);

    /**
     * 支付充值订单
     * @param userId 用户id
     * @param tradeNo 支付订单编号
     * @param payChannel 支付渠道
     */
    void pay(Long userId, String tradeNo, String payChannel);

    /**
     * 查询我的充值账单
     * @param userId
     * @return
     */
    List<BalanceChargeBill> findByUserId(Long userId);

    /**
     * 处理支付结果
     * @param result
     * @return
     */
    boolean handlePayResult(Long userId, String tradeNo, String outTradeNo, PayChannel payChannel);

}
