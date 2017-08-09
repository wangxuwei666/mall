/**
 * youmu Service Inc
 * AllRight Reserved @2017
 */
package com.youmu.common.alidayu.utils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.alibaba.fastjson.JSON;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;
import com.youmu.common.cofig.utils.ConfigUtils;

/**
 * 短信
 * 
 * @author zh
 * @version $Id: SmsUtil.java, v 0.1 2016年12月2日 下午2:30:51 Administrator Exp $
 */
public class SmsUtil {

    private static Logger log = LoggerFactory.getLogger(SmsUtil.class);
    
    /** 同一号码发送频率限制  */
    private static final int MAX_SNED_TIME = 5;
    
    /** 锁定时间 */
    private static final long LOCK_HOURS = 12;
    
    /** 发送短信客户端  */
    private static TaobaoClient client;
    
    /**
     * 发送次数统计
     */
    private static ConcurrentMap<String, Map<String, Integer>> sendedCountMap = new ConcurrentHashMap<>();
    
    /**
     * 发送次数过多的锁定手机号码
     */
    private static ConcurrentMap<String, Map<String, Long>> lockedMap = new ConcurrentHashMap<>();
    
    static {
      client = new DefaultTaobaoClient(ConfigUtils.getAlidayuUrl(), ConfigUtils.getAlidayuAppkey(),  ConfigUtils.getAlidayuSecret());
    }

    /**
     * 获取客户端
     * 
     * @return
     */
    public static TaobaoClient getSmsClient() {
        return client;
    }
    
    public static void sendSms(String mobiles, String smsTemplateCode) {
       sendSms(mobiles, Collections.<String, Object> emptyMap(), smsTemplateCode);
    }
    
    public static void sendSms(String mobiles, Map<String, Object> params, String smsTemplateCode) {
      try {
        beforeSend(mobiles, smsTemplateCode);
        AlibabaAliqinFcSmsNumSendRequest request = new AlibabaAliqinFcSmsNumSendRequest();
        // 设置公共回传参数
        // req.setExtend("13547899969"); 
        // 设置短信类型，传入值请填写normal
        request.setSmsType(ConfigUtils.getAlidayuSMSType());
        // 设置短信签名
        request.setSmsFreeSignName(ConfigUtils.getAlidayuSMSSign());
        // 设置短信接收号码。支持单个或多个手机号码，传入号码为11位手机号码，不能加0或+86。群发短信需传入多个号码，以英文逗号分隔，一次调用最多传入200个号码。
        request.setRecNum(mobiles);
        request.setSmsParam(JSON.toJSONString(params));
        // 设置短信模板ID，传入的模板必须是在阿里大鱼“管理中心-短信模板管理”中的可用模板。
        request.setSmsTemplateCode(smsTemplateCode);
        AlibabaAliqinFcSmsNumSendResponse response = client.execute(request);
        afterSend(mobiles, smsTemplateCode, response.isSuccess());
        if(!response.isSuccess()) {
            String code = response.getSubCode();
            if(StringUtils.equals(code, "isv.BUSINESS_LIMIT_CONTROL")) {
                throw new RuntimeException("您短信发送过于频繁，请稍后再试");
            } else if(StringUtils.equals(code, "isv.AMOUNT_NOT_ENOUGH")) {
                throw new RuntimeException("短信平台余额不足");
            } else {
                throw new RuntimeException("短信发送失败,请稍后再试");
            }
        }
      } catch (ApiException e) {
        log.error("短信返送失败： ", e);
        throw new RuntimeException("短信发送失败,请稍后再试");
      }
    }

    /**
     * 发送之前
     * @param mobiles
     * @param params
     * @param smsTemplateCode
     * @throws ApiException 
     */
    private static void beforeSend(String mobiles, String smsTemplateCode) throws ApiException {
        String[] strs = mobiles.split(",");
        for (String mobile : strs) {
            Map<String, Long> map = lockedMap.get(mobile);
            if(map != null) {
                Long lockMillis = map.get(smsTemplateCode);
                if(lockMillis != null) {
                    Long currentMillis = System.currentTimeMillis();
                    if(currentMillis < lockMillis) {
                        throw new RuntimeException("短信验证码次数请求超限,请"+ LOCK_HOURS +"小时后重试");
                    } else {
                        Map<String, Integer> sendedCount = sendedCountMap.get(mobile);
                        if(sendedCount != null) {
                            sendedCount.remove(smsTemplateCode);
                            if(sendedCount.isEmpty()) {
                                sendedCountMap.remove(mobile);
                            }
                        }
                        map.remove(smsTemplateCode);
                        if(map.isEmpty()) {
                            lockedMap.remove(mobile);
                        }
                    }
                }
            }
        }
    }

    /**
     * 发送之后
     * @param mobiles 手机号码
     * @param params 参数
     * @param smsTemplateCode 短信模板
     */
    private static void afterSend(String mobiles, String smsTemplateCode, boolean isSuccess) {
        if(mobiles != null && isSuccess) {
            String[] strs = mobiles.split(",");
            for (String mobile : strs) {
                Map<String, Integer> sendInfo = sendedCountMap.get(mobile);
                if(sendInfo == null) {
                    sendInfo = new HashMap<>();
                    sendedCountMap.put(mobile, sendInfo);
                }
                
                Integer count = sendInfo.get(smsTemplateCode);
                count = (count == null ? 1 : count + 1);
                sendInfo.put(smsTemplateCode, count);
                if(count >= MAX_SNED_TIME) {
                    Map<String, Long> lockInfo = lockedMap.get(mobile);
                    if(lockInfo == null) {
                        lockInfo = new HashMap<>();
                        lockedMap.put(mobile, lockInfo);
                    }
                    lockInfo.put(smsTemplateCode, System.currentTimeMillis() + LOCK_HOURS * 60 * 60 * 1000);
                }
            }
        }
    }
}


