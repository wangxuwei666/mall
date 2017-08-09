/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.common.wx.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.ConcurrencyThrottleInterceptor;

import com.alibaba.fastjson.JSON;
import com.youmu.common.cofig.utils.ConfigUtils;
import com.youmu.common.global.constant.StatusConstant;
import com.youmu.common.http.utils.HttpUtils;
import com.youmu.mall.exception.BusinessException;

/**
 * 微信工具类。
 * @author zh
 * @version $Id: WxTools.java, v 0.1 2017年3月9日 上午10:23:40 zh Exp $
 */
public class WxPublicNumberUtil {
    
    public static String CHAR_SET = "UTF-8";
    
    public static String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential";
    
    public static String JS_TICKET_URL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?type=jsapi";
    
    public static String AUTH_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token";
    
    public static String REFRESH_AUTH_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/refresh_token";
    
    public static String GET_USER_INFO_URL = "https://api.weixin.qq.com/sns/userinfo";
    
    public static String GET_ORDERQUERY = "https://api.mch.weixin.qq.com/pay/orderquery";
    
    public static String AUTH_BASE_SCOPE = "snsapi_base";
    
    public static String AUTH_USERINFO_SCOPE = "snsapi_userinfo";
    
    private static Logger logger = LoggerFactory.getLogger(WxPublicNumberUtil.class);
    
    /** 公账号普通token */
    private static WxAccessToken accessToken = null;
    
    /** jsTicket */
    private static  WxJsTicket jsTicket = null;
    
    private static Map<String, WxAuthAccessToken> authTokenMap = new HashMap<>();
    
    /** 微信证服务器  */
    public static boolean checkSignature(String signature,String timestamp,String nonce){  
        String[] strs=new String[] {ConfigUtils.getWxToken(), timestamp, nonce};  
        Arrays.sort(strs);  
        StringBuilder content= new StringBuilder();  
        for (int i = 0; i < strs.length; i++) {  
            content.append(strs[i]);  
        }  
        MessageDigest md = null;    
        String tmpStr = null;    
        try {    
            md = MessageDigest.getInstance("SHA-1");
            // 将三个参数字符串拼接成一个字符串进行sha1加密    
            byte[] digest = md.digest(content.toString().getBytes());    
            tmpStr = new String(digest, CHAR_SET);
        } catch (Exception e) {    
              logger.error("微信签名错误");
        }
        logger.info("sig:{}, create sig:{}.", signature, tmpStr);
        return signature !=null ? StringUtils.equalsIgnoreCase(signature, tmpStr) : false;  
    }
    
    /** 获取accseeToken  */
    public static WxAccessToken getAccessToken(){
        if(accessToken == null || System.currentTimeMillis() > accessToken.getExpireTimeStamp()) {
            forceUpdateAccessToken();
        }
        return accessToken;
    }

    /**
     * 强制刷新accessToken
     */
    private static void forceUpdateAccessToken() {
        Map<String, String> params = new HashMap<>();
        params.put("appid", ConfigUtils.getWxAppId());
        params.put("secret", ConfigUtils.getWxAppSecret());
        String resp = HttpUtils.get(WxPublicNumberUtil.ACCESS_TOKEN_URL, params);
        accessToken = JSON.parseObject(resp, WxAccessToken.class);
    }
    
    /** 获取wx jsticket  */
    public static WxJsTicket getJsTicket(){
        if(jsTicket == null || System.currentTimeMillis() > jsTicket.getExpireTimeStamp()) {
            Map<String, String> params = new HashMap<>();
            params.put("access_token", getAccessToken().getAccess_token());
            String resp = HttpUtils.get(WxPublicNumberUtil.JS_TICKET_URL, params);
            jsTicket = JSON.parseObject(resp, WxJsTicket.class);
        }
        return jsTicket;
    }
    
    /** 获取sign wx jsticket  */
    public static Map<String, String> getSignedJsTicket(String url){
        WxJsTicket ticket = getJsTicket();
        // 如果获取js ticket 是空 那么 access token 已经过期了 要刷新access token 从新获取
        if(StringUtils.isBlank(jsTicket.getTicket())) {
            logger.error("use access token {} get js tiket is fail, try force update access token retry.", accessToken);
            accessToken = null;
            forceUpdateAccessToken();
            ticket = getJsTicket();
        }
        logger.info("now js ticket is {}.", jsTicket);
        Map<String, String> sign = WxTools.sign(ticket.getTicket(), url);
        sign.put("appId", ConfigUtils.getWxAppId());
        return sign;
    }
    
    /** 获取网页授权连接 */
    public static String getWebAuthUrl(String url, String scope, String state) {
        try {
            return "https://open.weixin.qq.com/connect/oauth2/authorize"
                    + "?appid=" + ConfigUtils.getWxAppId()
                    + "&redirect_uri=" + URLEncoder.encode(url, CHAR_SET)
                    + "&response_type=code"
                    + "&scope=" + scope
                    + "&state=" + state
                    + "#wechat_redirect";
        } catch (UnsupportedEncodingException e) {
        }
        return null;
    }
    
    /** 获取 网页授权的token  */
    public static WxAuthAccessToken getAuthAccessToken(String code){
        Map<String, String> params = new HashMap<>();
        params.put("appid", ConfigUtils.getWxAppId());
        params.put("secret", ConfigUtils.getWxAppSecret());
        params.put("code", code);
        params.put("grant_type", "authorization_code");
        String res = HttpUtils.get(AUTH_ACCESS_TOKEN_URL, params);
        WxAuthAccessToken token =  JSON.parseObject(res, WxAuthAccessToken.class);
        authTokenMap.put(token.getOpenid(), token);
        return token;
    }
    
    /** 获取 网页授权的token  */
    public static WxAuthAccessToken getAuthAccessTokenByOpenId(String openId){
        return authTokenMap.get(openId);
    }
    
//    /** 获取 获取token通过refresh token  */
//    public static WxAuthAccessToken getAuthAccessToken(){
//        if(authAccessToken != null && System.currentTimeMillis() > authAccessToken.getExpireTimeStamp()) {
//            // 使用refreshtoken 获取
//            Map<String, String> params = new HashMap<>();
//            params.put("appid", ConfigUtils.getWxAppId());
//            params.put("grant_type", "refresh_token");
//            params.put("refresh_token", authAccessToken.getRefresh_token());
//            String res = HttpUtils.get(REFRESH_AUTH_ACCESS_TOKEN_URL, params);
//            if(res.contains("errcode")) {
//                throw new BusinessException("需要用户重新授权");
//            }
//            authAccessToken = JSON.parseObject(res, WxAuthAccessToken.class);
//            return authAccessToken;
//        } else {
//            throw new BusinessException("需要用户授权");
//        }
//    }
    
    /** 获取用户信息  */
    public static WxUserInfo getWxUserInfo(WxAuthAccessToken token){
        Map<String, String> params = new HashMap<>();
        params.put("access_token", token.getAccess_token());
        params.put("openid", token.getOpenid());
        params.put("lang", "zh_CN");
        String res = HttpUtils.get(GET_USER_INFO_URL, params);
        if(res.contains("errcode")) {
            throw new BusinessException("需要用户重新授权");
        }
        return  JSON.parseObject(res, WxUserInfo.class);
    }
    
    /** 查询订单信息 */
    public static WxPayInfo getWxOrderStatus(String outTradeNumber){
        WxPayInfo info = new WxPayInfo();
        Integer orderStatus = null;
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("appid", ConfigUtils.getWxAppId());
        params.put("mch_id", ConfigUtils.getWeixinPartnerId());
        params.put("out_trade_no", outTradeNumber);
        params.put("nonce_str", UUID.randomUUID().toString().replaceAll("-",""));
        String sign = WxTools.getSign(params);
        params.put("sign", sign);
        String body = HttpUtils.postXmlBody(GET_ORDERQUERY, 10000 , params, "UTF-8");
        Map xmlMap = WxTools.getMapByXml(body);
        logger.info("query wx order pay info : {}.", xmlMap);
        if(xmlMap.get("trade_state").equals("NOTPAY")){
            //订单未支付
            orderStatus = StatusConstant.ZERO;
        }
        if(xmlMap.get("trade_state").equals("CLOSED")){
            //订单已关闭
            orderStatus = StatusConstant.FOUR;
        }
        if(xmlMap.get("trade_state").equals("SUCCESS")){
            //订单已支付
            info.setTransactionId(xmlMap.get("transaction_id").toString());
            orderStatus = StatusConstant.ONE;
        }
        info.setOutTradeNo(outTradeNumber);
        info.setStatus(orderStatus);
        return info;
    }

    /**
     * 删除缓存的authAccessToken
     * @param openid
     */
    public static void removeAuthAccessTokenByOpenId(String openid) {
        authTokenMap.remove(openid);
    }
}
