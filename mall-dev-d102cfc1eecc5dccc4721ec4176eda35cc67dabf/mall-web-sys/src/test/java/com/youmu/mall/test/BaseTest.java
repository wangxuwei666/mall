/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.test;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 测试基类.
 * @author zh
 * @version $Id: BaseTest.java, v 0.1 2017年2月25日 下午4:11:17 zh Exp $
 */
@ContextConfiguration("classpath:applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class BaseTest {
    
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    
    
    
}
