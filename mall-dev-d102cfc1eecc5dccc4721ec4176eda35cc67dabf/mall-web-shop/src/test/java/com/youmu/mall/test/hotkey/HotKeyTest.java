/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.test.hotkey;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.youmu.mall.ks.service.IKSService;
import com.youmu.mall.test.BaseTest;

/**
 * 热词使用测试.
 * @author zh
 * @version $Id: HotKeyTest.java, v 0.1 2017年4月26日 下午3:29:08 zh Exp $
 */
public class HotKeyTest extends BaseTest  {
    
    @Resource
    IKSService ksService;
    
    @Test
    public void testAddHotKey() throws Exception {
        String key = "product-search";
        for (int i=0; i<3000; i++) {
            ksService.saveSearchKeywords(key, "search" + (i % 30));
        }
    }
    
    @Test
    public void testGetHotKey() throws Exception {
        String key = "product-search";
        List<String> set = ksService.findHotSearchKeywords(key, 10);
        for (String string : set) {
            System.err.println(string);
        }
    }
    
}
