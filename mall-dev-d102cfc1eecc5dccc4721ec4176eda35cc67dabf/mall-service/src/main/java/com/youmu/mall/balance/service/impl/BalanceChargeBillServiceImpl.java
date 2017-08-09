/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.balance.service.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.youmu.common.alipay.utils.AliPayQRCode;
import com.youmu.common.alipay.utils.AliPayUtils;
import com.youmu.common.alipay.utils.QRCodePayParams;
import com.youmu.common.wx.utils.WxPayUtil;
import com.youmu.mall.balance.dao.BalanceChargeBillDao;
import com.youmu.mall.balance.domain.BalanceChargeBill;
import com.youmu.mall.balance.domain.ChargeTradeStatus;
import com.youmu.mall.balance.domain.PayChannel;
import com.youmu.mall.balance.domain.PayMethod;
import com.youmu.mall.balance.service.IBalanceChargeBillService;
import com.youmu.mall.exception.BusinessException;
import com.youmu.mall.redis.login.dao.RedisLoginDao;
import com.youmu.mall.user.domain.User;

/**
 * 余额充值服务.
 * @author zh
 * @version $Id: BalanceChargeBillServiceImpl.java, v 0.1 2017年5月16日 下午2:18:41 zh Exp $
 */
@Service
public class BalanceChargeBillServiceImpl implements IBalanceChargeBillService {
    
    @Resource
    private  RedisLoginDao redisLoginDao;
    
    @Resource
    private BalanceChargeBillDao balanceChargeBillDao;
    
    private User getUser(String userId) {
        return redisLoginDao.getLoggedUserInfo(userId, User.class);
    }

    /** 
     * @see com.youmu.mall.balance.service.IBalanceChargeBillService#create(java.lang.Long, java.math.BigDecimal, java.lang.String)
     */
    @Override
    public BalanceChargeBill create(Long userId, BigDecimal amount, String remark) {
        BalanceChargeBill bill = new BalanceChargeBill();
        bill.setUserId(userId);
        bill.setAmount(amount);
        bill.setRemark(remark);
        bill.setUserId(userId);
        User user = getUser(userId.toString());
        bill.setUserMobile(user.getMobile());
        bill.setUserUsername(user.getUsername());
        return balanceChargeBillDao.save(bill);
    }

    /** 
     * @see com.youmu.mall.balance.service.IBalanceChargeBillService#pay(java.lang.Long, java.lang.String, java.lang.String)
     */
    @Override
    public void pay(Long userId, String tradeNo, String payChannel) {
        BalanceChargeBill bill = balanceChargeBillDao.findByTradeNo(tradeNo);
        String status = bill.getTradeStatus();
        ChargeTradeStatus billStatus = ChargeTradeStatus.valueOf(status);
        if (ChargeTradeStatus.TRADE_WAIT_PAY.equals(billStatus)) {
            PayMethod method = PayMethod.valueOf(payChannel);
            User user = getUser(userId.toString());
            switch (method) {
                case WX_JS_PAY:
                    payByWxJs(user, bill);
                    break;
                case WX_QRCODE_PAY:
                    payByWxQrCode(user, bill);
                    break;
                case ALI_QRCODE_PAY:
                    payByAliQrCode(user, bill);
                    break;
                default:
                    throw new BusinessException("不支持的支付方式 " + method.name());
            }
        } else {
            throw new BusinessException(billStatus.name()+ " 订单不可支付");
        }
    }

    /**
     * 支付宝扫码支付
     * @param user
     * @param bill
     */
    private void payByAliQrCode(User user, BalanceChargeBill bill) {
        QRCodePayParams params = new QRCodePayParams();
        params.setOut_trade_no(bill.getTradeNo());
        params.setSubject("账户充值");
        params.setTimeout_express("5m");
        params.setTotal_amount(bill.getAmount().toPlainString());
        AliPayQRCode code = AliPayUtils.generateQrCode(params , "");
    }

    /**
     * 微信扫码支付
     * @param user
     * @param bill
     */
    private void payByWxQrCode(User user, BalanceChargeBill bill) {
        WxPayUtil.payUnifiedorder("账户充值", bill.getTradeNo(), bill.getAmount(), "", user.getWxOpenid(), "");
    }

    /**
     * 微信公众号支付
     * @param user
     * @param bill
     */
    private void payByWxJs(User user, BalanceChargeBill bill) {
        
    }

    /** 
     * @see com.youmu.mall.balance.service.IBalanceChargeBillService#findByUserId(java.lang.Long)
     */
    @Override
    public List<BalanceChargeBill> findByUserId(Long userId) {
        return null;
    }

    /** 
     * @see com.youmu.mall.balance.service.IBalanceChargeBillService#handlePayResult(java.lang.Long, java.lang.String, java.lang.String, com.youmu.mall.balance.domain.PayChannel)
     */
    @Override
    public boolean handlePayResult(Long userId, String tradeNo, String outTradeNo, PayChannel payChannel) {
        return false;
    }

}
