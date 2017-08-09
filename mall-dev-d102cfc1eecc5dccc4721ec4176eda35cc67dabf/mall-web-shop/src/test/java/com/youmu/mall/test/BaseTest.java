/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.test;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.youmu.mall.user.dao.UserDao;
import com.youmu.mall.user.domain.User;

/**
 * 测试基类.
 * @author zh
 * @version $Id: BaseTest.java, v 0.1 2017年2月25日 下午4:11:17 zh Exp $
 */
@ContextConfiguration({"classpath:applicationContext.xml","classpath:applicationContext-mvc.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class BaseTest {
    
    @Resource
    private UserDao userDao;
    
    protected Logger logger = LoggerFactory.getLogger(getClass());
    
    @Test
    public void testGetUser() throws Exception {
        User user = userDao.getById(180L);
        System.err.println(JSON.toJSONString(user));
    }
    
}
