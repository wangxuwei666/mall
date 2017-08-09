/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.test.sms;

import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;
import com.youmu.common.cofig.utils.ConfigUtils;
import com.youmu.common.token.utils.AuthCodeUtils;
import com.youmu.mall.test.BaseTest;

/**
 * 短信测试.
 * @author zh
 * @version $Id: SmsTest.java, v 0.1 2017年3月3日 下午3:21:11 zh Exp $
 */
public class SmsTest extends BaseTest {
    
    @Test
    public void sendValidateCodeSms() {
        String authCode = String.valueOf(AuthCodeUtils.getCommonAuthCode());
        Map<String, Object> params = new HashedMap<>(1);
        params.put("code", authCode);
        params.put("product", "宝宝余");
        // 调用短信发送接口发送短信
        try {
            TaobaoClient client = new DefaultTaobaoClient(ConfigUtils.getAlidayuUrl(), "23703211",  "c39ba77940ffe78810b21aa94c75d7fb");
            AlibabaAliqinFcSmsNumSendRequest request = new AlibabaAliqinFcSmsNumSendRequest();
            // 设置公共回传参数
            // req.setExtend("13547899969"); 
            // 设置短信类型，传入值请填写normal
            request.setSmsType("normal");
            // 设置短信签名
            request.setSmsFreeSignName("宝宝余");
            // 设置短信接收号码。支持单个或多个手机号码，传入号码为11位手机号码，不能加0或+86。群发短信需传入多个号码，以英文逗号分隔，一次调用最多传入200个号码。
            request.setRecNum("15708437406");
            request.setSmsParam(JSON.toJSONString(params));
            // 设置短信模板ID，传入的模板必须是在阿里大鱼“管理中心-短信模板管理”中的可用模板。
            request.setSmsTemplateCode("SMS_56040098");
            AlibabaAliqinFcSmsNumSendResponse response = client.execute(request);
            System.err.println(response.getBody());
          } catch (ApiException e) {
              e.printStackTrace();
          }
    }
    
}
