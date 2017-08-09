/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.common.wx.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import com.youmu.common.cofig.utils.ConfigUtils;
import com.youmu.common.global.constant.StatusConstant;
import com.youmu.common.http.utils.HttpUtils;

/**
 * 
 * @author yujiahao
 * @version $Id: WeixinUtil.java, v 0.1 2017年3月9日 下午4:59:07 yujiahao Exp $
 */
public class WxPayUtil {
    
    private static final String TRADE_TYPE="JSAPI";
    
    private static final String TRADE_TYPE_NATIVE="NATIVE";
    
    private static final String UNIFIEDORDER_URL="https://api.mch.weixin.qq.com/pay/unifiedorder";
    
    private static final String REFUND_URL="https://api.mch.weixin.qq.com/secapi/pay/refund";
    
    /**
     * 微信下单
     * 
     * @param name
     * @param outOrderNumber
     * @param amount
     * @param ip
     * @return
     */
    public static String payUnifiedorder(String name,String outOrderNumber,BigDecimal amount,String ip,String openid,String productId){
        String result = "";
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("appid", ConfigUtils.getWeixinAppId());
        params.put("mch_id", ConfigUtils.getWeixinPartnerId());
        params.put("nonce_str", UUID.randomUUID().toString().replaceAll("-",""));
        params.put("body", name);
        //订单sn
        params.put("out_trade_no", outOrderNumber);
        
        int totalFee = (int) (amount.multiply(new BigDecimal("100")).setScale(2, BigDecimal.ROUND_HALF_EVEN).intValue());
        params.put("total_fee", totalFee);
        
        //测试金额
//        params.put("total_fee",1);
        
        params.put("spbill_create_ip", ip);
        params.put("notify_url",ConfigUtils.getWeixinNotifyUrl());
        if(!StringUtils.isBlank(openid) && StringUtils.isBlank(productId)){
            params.put("trade_type", TRADE_TYPE);
            params.put("openid", openid);
        }else if(StringUtils.isBlank(openid) && !StringUtils.isBlank(productId)){
            params.put("trade_type", TRADE_TYPE_NATIVE);
            params.put("product_id", productId);
        }
        String sign = WxTools.getSign(params);
        params.put("sign", sign);
        String xml = HttpUtils.postXmlBody(UNIFIEDORDER_URL, 10000, params, "UTF-8");
        Map<String,String> resultMap = null;
        resultMap =  WxTools.getMapByXml(xml);
        if(resultMap == null){
            return null;
        }
        String returnCode = resultMap.get("return_code");
        if(returnCode ==null ||!returnCode.equals("SUCCESS")){
            return null;
        }
        String tradeType = resultMap.get("trade_type");
        if(tradeType.equals(TRADE_TYPE)){
            result = resultMap.get("prepay_id");
        }
        else if(tradeType.equals(TRADE_TYPE_NATIVE)){
            result = resultMap.get("code_url");
        }
        return result;
    }
    
    /**
     * 处理支付回调
     * @param request
     * @param response
     * @param handler
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static void handlePayNotify(HttpServletRequest request, HttpServletResponse response, WxPayResultHandler handler) {
        PrintWriter writer = null;
        Map<String,Object> result = new HashMap<String, Object>();
        try {
            writer = response.getWriter();
            Map params = WxTools.getMapByXml(IOUtils.toString(request.getInputStream()));
            if(params == null){
                result.put("return_code", "false");
                result.put("return_msg", "参数错误");
            } else {
                
                String sign =WxTools.getSign(params);
                String paramSign = (String) params.get("sign");
                if(paramSign == null ||!paramSign.equals(sign)){
                    result.put("return_code", "false");
                    result.put("return_msg", "签名错误");
                } else {
                    //得到外部订单号
                    String outOrderNumber = (String) params.get("out_trade_no");
                    String transactionId = (String) params.get("transaction_id");
                    
                    if(!handler.handlePayResult(outOrderNumber, transactionId)){
                        result.put("return_code", "false");
                    } else {
                        result.put("return_code", "success");
                    }
                }
            }
        } catch (IOException e) {
            result.put("return_code", "false");
            result.put("return_msg", "参数错误");
        }
        if(writer != null)
            writer.write(WxTools.getXml(result));
    }
}
