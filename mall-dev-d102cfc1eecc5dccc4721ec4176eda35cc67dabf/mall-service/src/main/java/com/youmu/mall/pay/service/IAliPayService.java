/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.pay.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.youmu.common.alipay.utils.AliPayQRCode;
import com.youmu.common.alipay.utils.AliPayResultHandler;
import com.youmu.common.alipay.utils.AlipayResult;
import com.youmu.common.alipay.utils.QRCodePayParams;
import com.youmu.common.alipay.utils.WapPayParams;

/**
 * 支付宝支付服务.
 * @author zh
 * @version $Id: IAliPayService.java, v 0.1 2017年4月19日 下午2:53:17 zh Exp $
 */
public interface IAliPayService {
    
    /**
     * 支付宝h5支付接口.
     * 
     * @param params 支付宝支付必须参数
     * @param returnUrl 支付宝支付后跳转的页面
     * @param notifyUrl 支付宝支付成功后回调的界面
     * @param response 响应
     */
    public void wapPay(WapPayParams params, String returnUrl, String notifyUrl, HttpServletResponse response);
    
    /**
     * 处理支付宝支付回调.
     * @param request 回调请求
     * @param response 回调响应
     * @param handler 处理回调的类
     */
    public void handlePayNotify(HttpServletRequest request, HttpServletResponse response, AliPayResultHandler handler);
    
    /**
     * 生成支付宝支付qr_code
     * @param params 生成支付宝扫码支付的必要参数
     * @return
     */
    public AliPayQRCode generateAliPayQRCode(QRCodePayParams params, String notifyUrl);
    
    /**
     * 查询支付宝交易结果.
     * @param outTradeNo 业务订单号
     * @param tradeNo 支付宝订单编号
     * @return
     */
    public AlipayResult queryAliPayTrade(String outTradeNo, String tradeNo);
    
    /**
     * 取消支付宝交易 只有在客户等待支付和交易超时调用
     * @param outTradeNo 业务订单号
     * @param tradeNo 支付宝订单编号
     * @return
     */
    public void cancelAliPayTrade(String outTradeNo, String tradeNo);
}   
