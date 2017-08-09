/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.permission.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.youmu.mall.exception.BusinessException;
import com.youmu.mall.permission.dao.SysMenuDao;
import com.youmu.mall.permission.service.IMenuService;
import com.youmu.mall.sys.domain.SysMenu;
import com.youmu.mall.sys.utils.SysMenuUtil;
import com.youmu.mall.sys.vo.SysMenuListVo;

/**
 * 菜单服务实现类
 * @author zh
 * @version $Id: MenuServiceImpl.java, v 0.1 2017年3月15日 上午11:33:51 zh Exp $
 */
@Service
public class MenuServiceImpl implements IMenuService {

    @Resource
    private SysMenuDao sysMenuDao;
    
    /** 
     * @see com.youmu.mall.permission.service.IMenuService#listAll()
     */
    @Override
    public List<SysMenuListVo> listAll() {
        List<SysMenuListVo> allMenus =  sysMenuDao.selectAllSysMenu();
        return SysMenuUtil.convertMenusToTree(allMenus);
    }


    /** 
     * @see com.youmu.mall.permission.service.IMenuService#addSysMenu(com.youmu.mall.sys.domain.SysMenu)
     */
    @Override
    public void addSysMenu(SysMenu menu) {
        long count = sysMenuDao.selectRepeatCount(menu);
        if(count > 0) {
            throw new BusinessException("菜单名称已经存在");
        }
        sysMenuDao.insertSysMenu(menu);
    }

    /** 
     * @see com.youmu.mall.permission.service.IMenuService#deleteSysMenu(java.lang.Long)
     */
    @Override
    public void deleteSysMenu(Long id) {
        sysMenuDao.deleteById(id);
    }

    /** 
     * @see com.youmu.mall.permission.service.IMenuService#updateSysMenu(com.youmu.mall.sys.domain.SysMenu)
     */
    @Override
    public void updateSysMenu(SysMenu menu) {
        long count = sysMenuDao.selectRepeatCount(menu);
        if(count > 0) {
            throw new BusinessException("菜单名称已经存在");
        }
        sysMenuDao.updateById(menu);
    }

    /** 
     * @see com.youmu.mall.permission.service.IMenuService#listRoleMenus(java.lang.Long)
     */
    @Override
    public List<SysMenuListVo> listRoleMenus(Long roleId) {
        return SysMenuUtil.convertMenusToTree(sysMenuDao.selectMenuListByRoleId(roleId));
    }
    
}
