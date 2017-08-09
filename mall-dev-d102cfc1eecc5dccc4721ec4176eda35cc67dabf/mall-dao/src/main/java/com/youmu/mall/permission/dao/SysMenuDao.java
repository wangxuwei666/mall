/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.permission.dao;

import java.util.List;

import com.youmu.mall.sys.domain.SysMenu;
import com.youmu.mall.sys.vo.SysMenuListVo;

/**
 * 系统菜单数据访问接口.
 * @author zh
 * @version $Id: SysMenuDao.java, v 0.1 2017年3月14日 下午2:19:21 zh Exp $
 */
public interface SysMenuDao {
    
    /**
     * 添加一个系统菜单   
     */
    void insertSysMenu(SysMenu menu);
    
    /***
     * 修改一个系统菜单
     */
    void updateById(SysMenu menu);
    
    /**
     *  删除一个菜单 
     */
    void deleteById(Long menu);
    
    /**
     * 查询菜单通过id
     */
    SysMenu selectById(Long id);

    /**
     * 查询所有的菜单
     * @return
     */
    List<SysMenuListVo> selectAllSysMenu();

    /**
     * 查询重复的数量.
     * @param menu
     * @return
     */
    long selectRepeatCount(SysMenu menu);

    /**
     * 查询菜单通过角色的id
     * @param roleId
     * @return
     */
    List<SysMenuListVo> selectMenuListByRoleId(Long roleId);
    
}
