/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.test.pay;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.youmu.common.alipay.utils.AliPayQRCode;
import com.youmu.common.alipay.utils.AliPayUtils;
import com.youmu.common.alipay.utils.QRCodePayParams;
import com.youmu.mall.test.BaseTest;

/**
 * 测试支付宝支付.
 * @author zh
 * @version $Id: TestAliPay.java, v 0.1 2017年4月19日 下午6:15:18 zh Exp $
 */
public class TestAliPay extends BaseTest {
    
    @Test
    public void testGeneratePayQrCode() throws Exception {
        QRCodePayParams params = new QRCodePayParams();
        String out_trade_no = "1493000535192" + "";
        System.err.println(out_trade_no);
        params.setOut_trade_no(out_trade_no);
        params.setTotal_amount("11.11");
        params.setSubject("测试商品");
        AliPayQRCode code = AliPayUtils.generateQrCode(params, "http://zh.tunnel.qydev.com/mall-web-shop/pay/aliPayNotify");
        System.err.println(JSON.toJSON(code));
    }
    
    @Test
    public void testQueryAlipayResult() throws Exception {
        String out_trade_no = "1492760906103";
        System.err.println(JSON.toJSONString(AliPayUtils.queryAliPayResult(out_trade_no, null)));
    }
    
    @Test
    public void testCancelTrade() throws Exception {
        String out_trade_no = "1492676712408";
        AliPayUtils.cancelTrade(out_trade_no, null);
    }
}
