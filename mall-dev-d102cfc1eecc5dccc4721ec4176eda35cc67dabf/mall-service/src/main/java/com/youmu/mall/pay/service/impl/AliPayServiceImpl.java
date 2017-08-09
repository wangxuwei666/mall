/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.pay.service.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.youmu.common.alipay.utils.AliPayQRCode;
import com.youmu.common.alipay.utils.AliPayResultHandler;
import com.youmu.common.alipay.utils.AliPayUtils;
import com.youmu.common.alipay.utils.AlipayResult;
import com.youmu.common.alipay.utils.QRCodePayParams;
import com.youmu.common.alipay.utils.WapPayParams;
import com.youmu.mall.pay.service.IAliPayService;

/**
 * 支付宝支付服务实现类.
 * @author zh
 * @version $Id: AliPayServiceImpl.java, v 0.1 2017年4月19日 下午3:03:50 zh Exp $
 */
@Service
public class AliPayServiceImpl implements IAliPayService {

    /** 
     * @see com.youmu.mall.pay.service.IAliPayService#wapPay(com.youmu.common.alipay.utils.WapPayParams, java.lang.String, java.lang.String, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public void wapPay(WapPayParams params, String returnUrl, String notifyUrl, HttpServletResponse response) {
        // 调起支付宝支付
        AliPayUtils.wapPay(params, returnUrl, notifyUrl, response);
    }

    /** 
     * @see com.youmu.mall.pay.service.IAliPayService#handlePayNotify(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, com.youmu.common.alipay.utils.AliPayResultHandler)
     */
    @Override
    public void handlePayNotify(HttpServletRequest request, HttpServletResponse response,
                                   AliPayResultHandler handler) {
        // 回调支付
        AliPayUtils.handlePayNotify(request, response, handler);
    }

    /** 
     * @param notifyUrl 
     * @see com.youmu.mall.pay.service.IAliPayService#generateAliPayQRCode(com.youmu.common.alipay.utils.QRCodePayParams)
     */
    @Override
    public AliPayQRCode generateAliPayQRCode(QRCodePayParams params, String notifyUrl) {
        return AliPayUtils.generateQrCode(params, notifyUrl);
    }

    /** 
     * @see com.youmu.mall.pay.service.IAliPayService#queryAliPayTrade(java.lang.String, java.lang.String)
     */
    @Override
    public AlipayResult queryAliPayTrade(String outTradeNo, String tradeNo) {
        return AliPayUtils.queryAliPayResult(outTradeNo, tradeNo);
    }

    /** 
     * @see com.youmu.mall.pay.service.IAliPayService#cancelAliPayTrade(java.lang.String, java.lang.String)
     */
    @Override
    public void cancelAliPayTrade(String outTradeNo, String tradeNo) {
        AliPayUtils.cancelTrade(outTradeNo, tradeNo);
    }

}
