/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.test.wx;

import static org.junit.Assert.*;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.youmu.common.cofig.utils.ConfigUtils;
import com.youmu.common.upload.utils.ImageUploadUtils;
import com.youmu.common.wx.utils.WxAccessToken;
import com.youmu.common.wx.utils.WxJsTicket;
import com.youmu.common.wx.utils.WxPublicNumberUtil;
import com.youmu.common.wx.utils.WxTools;

/**
 * 微信测试
 * @author zh
 * @version $Id: WxTest.java, v 0.1 2017年3月9日 上午10:54:28 zh Exp $
 */
public class WxTest {
    
    @Test
    public void testGetAccessToken() throws Exception {
            System.err.println(JSON.toJSONString(WxPublicNumberUtil.getSignedJsTicket("http://www.wdlend.com")));
    }
    
    @Test
    public void testName() throws Exception {
        System.err.println(WxPublicNumberUtil.getWebAuthUrl("http://www.bbyumall.com/#/goPay?id=5", WxPublicNumberUtil.AUTH_BASE_SCOPE, ""));
    }
}
