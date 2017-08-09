/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.common.alipay.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayResponse;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeCancelRequest;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.alipay.api.response.AlipayTradeCancelResponse;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.youmu.common.cofig.utils.ConfigUtils;
import com.youmu.mall.exception.BusinessException;
import com.youmu.mall.exception.SystemInnerException;

/**
 * 支付宝手机网站支付工具
 * @author zh
 * @version $Id: AlipayUtils.java, v 0.1 2017年3月1日 下午3:09:07 zh Exp $
 */
public class AliPayUtils {
    
    /** 回调状态 失败 */
    public static final String FAILURE = "failure";

    /** 回调状态 成功 */
    public static final String SUCCESS = "success";

    /** 请求编码 */
    private static final String CHARSET = "utf-8";

    private static final String SIGN_TYPE = "RSA2";
    
    private static AlipayClient alipayClient;
    
    private static Logger logger = LoggerFactory.getLogger(AliPayUtils.class);
    
    // 实例化支付宝支付客户端
    static {
        alipayClient = new DefaultAlipayClient(ConfigUtils.getAlipayUrl(), ConfigUtils.getAlipayAppId(), ConfigUtils.getAlipayAppPrivateKey(), "json", CHARSET, ConfigUtils.getAlipayPublicKey(), SIGN_TYPE);
    }
    
    public static void wapPay(WapPayParams params, String returnUrl, String notifyUrl, HttpServletResponse response) {
        try {
            AlipayTradeWapPayRequest alipayRequest = new AlipayTradeWapPayRequest();//创建API对应的request
            alipayRequest.setReturnUrl(returnUrl);
            alipayRequest.setNotifyUrl(notifyUrl);//在公共参数中设置回跳和通知地址
            alipayRequest.setBizContent(JSON.toJSONString(params));
            String form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
            response.setContentType("text/html;charset=" + CHARSET);
            response.getWriter().write(form);//直接将完整的表单html输出到页面
            response.getWriter().flush();
        } catch (AlipayApiException e) {
            logger.error("支付宝支付异常", e);
            throw new BusinessException("支付宝支付异常", e);
        } catch (IOException e) {
            throw new SystemInnerException(e);
        }
    }
    
    /**
     * 解析支付宝支付的结果信息
     */
    public static void handlePayNotify(HttpServletRequest request, HttpServletResponse response, AliPayResultHandler handler) {
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            Map<String,String> params = new HashMap<String,String>();
            Map<String, String[]> requestParams = request.getParameterMap();
            for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
                String name = (String) iter.next();
                String[] values = (String[]) requestParams.get(name);
                String valueStr = "";
                for (int i = 0; i < values.length; i++) {
                    valueStr = (i == values.length - 1) ? valueStr + values[i]
                                : valueStr + values[i] + ",";
              }
              params.put(name, valueStr);
             }
            boolean signVerified = AlipaySignature.rsaCheckV1(params, ConfigUtils.getAlipayPublicKey(), CHARSET, SIGN_TYPE); //调用SDK验证签名
            if(signVerified){
                AlipayResult result = new AlipayResult();
                result.setOutTradeNo(params.get("out_trade_no"));
                result.setTotalAmount(params.get("total_amount"));
                result.setTradeNo(params.get("trade_no"));
                result.setTradeStatus(params.get("trade_status"));
                result.setSellerId(params.get("seller_id"));
                result.setAppId(params.get("app_id"));
                writer.write(handler.handlePayResult(result));
            }else{
                writer.write(FAILURE);
            }
        } catch (Exception e) {
            logger.error("支付宝回调异常", e);
            if(writer != null)
                writer.write(FAILURE);
        }
    }
    
    /**
     * 生成支付宝 支付二维码 让用户扫码支付.
     * @param params 生成支付宝支付款码的参数.
     * @param notifyUrl 支付宝回调地址
     * @return
     */
    public static AliPayQRCode generateQrCode(QRCodePayParams params, String notifyUrl) {
        AlipayTradePrecreateRequest request = new AlipayTradePrecreateRequest();//创建API对应的request类
        request.setNotifyUrl(notifyUrl);
        request.setBizContent(JSON.toJSONString(params));
        AlipayTradePrecreateResponse response;
        try {
            response = alipayClient.execute(request);
        } catch (AlipayApiException e) {
            logger.error("支付异常", e);
            throw new BusinessException(e.getMessage());
        }
        
        if(response == null) {
            return  null;
        }
        
        AliPayQRCode aliPayQRCode = new AliPayQRCode();
        if(response.isSuccess()) {
            aliPayQRCode.setOutTradeNo(response.getOutTradeNo());
            aliPayQRCode.setQrCode(response.getQrCode());
            return aliPayQRCode;
        } else {
            throw new BusinessException(response.getSubMsg());
        }
    }
    
    /**
     * 查询支付宝支付结果.
     * 
     * @param outTradeNo 支付宝外部订单号
     * @param tradeNo 支付宝订单编号
     * @return
     */
    public static AlipayResult queryAliPayResult(String outTradeNo, String tradeNo) {
        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("out_trade_no", outTradeNo);
        jsonObject.put("trade_no", tradeNo);
        request.setBizContent(jsonObject.toJSONString());
        AlipayTradeQueryResponse response;
        try {
            response = alipayClient.execute(request);
            return parseQueryTradeAlipayResponse(response);
        } catch (AlipayApiException e) {
            logger.error("支付结果查询失败", e);
            AlipayResult result = new AlipayResult();
            result.setPayStatus(AliPayStatus.TRADE_NOT_FOUND);
            return result;
        }
    }

    /**
     * 处理支付宝支付结果
     * @param response
     * @return
     */
    private static AlipayResult parseQueryTradeAlipayResponse(AlipayResponse response) {
        if(response == null) {
            return null;
        }
        AlipayResult result = new AlipayResult();
        JSONObject jsonObject = JSON.parseObject(response.getBody()).getJSONObject("alipay_trade_query_response");
        logger.debug("parse alipay trade query {}.", jsonObject);
        result.setOutTradeNo(jsonObject.getString("out_trade_no"));
        result.setTotalAmount(jsonObject.getString("total_amount"));
        result.setTradeNo(jsonObject.getString("trade_no"));
        result.setTradeStatus(jsonObject.getString("trade_status"));
        return result;
    }
    
    /**
     * 取消支付宝交易
     * 
     * @param outTradeNo 系统订单号
     * @param tradeNo 支付宝订单编号
     */
    public static void cancelTrade(String outTradeNo, String tradeNo) {
        AlipayTradeCancelRequest  request = new AlipayTradeCancelRequest ();//创建API对应的request类
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("out_trade_no", outTradeNo);
        jsonObject.put("trade_no", tradeNo);
        request.setBizContent(jsonObject.toJSONString());//设置业务参数
        try {
            AlipayTradeCancelResponse response = null;
            response = alipayClient.execute(request);
            if(!response.isSuccess()) {
                throw new BusinessException(response.getSubMsg());
            }
        } catch (AlipayApiException e) {
            logger.error("支付宝支付异常", e);
            throw new BusinessException(e.getMessage());
        }
    }
    
    
}
