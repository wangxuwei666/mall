/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.test.permission;

import javax.annotation.Resource;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.youmu.mall.permission.dao.SysMenuDao;
import com.youmu.mall.sys.domain.SysMenu;
import com.youmu.mall.test.BaseTest;

/**
 * 系统菜单操作服务.
 * @author zh
 * @version $Id: SysMenuDaoTest.java, v 0.1 2017年3月14日 下午4:39:19 zh Exp $
 */
public class SysMenuDaoTest extends BaseTest {
    
    @Resource
    private SysMenuDao sysMenuDao;

    @Test
    public void testInsert() {
        SysMenu menu = new SysMenu();
        menu.setName("测试菜单一");
        menu.setUrl("http://www.baidu.com");
        sysMenuDao.insertSysMenu(menu);
    }
    
    @Test
    public void testUpdate() {
        SysMenu menu = new SysMenu();
        menu.setId(1L);
        menu.setName("测试菜单二");
        menu.setUrl("http://www.baidu.com/ssss");
        sysMenuDao.updateById(menu);
    }
    
    @Test
    public void testDelete() {
        sysMenuDao.deleteById(1L);
    }
    
    @Test
    public void testSelectById() {
        System.err.println(JSON.toJSONString(sysMenuDao.selectById(1L)));
    }
    
}
