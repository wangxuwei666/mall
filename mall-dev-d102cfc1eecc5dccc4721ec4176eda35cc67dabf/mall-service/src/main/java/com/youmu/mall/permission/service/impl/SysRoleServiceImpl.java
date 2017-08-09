/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.permission.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.youmu.common.page.utils.Page;
import com.youmu.mall.base.query.PageQuery;
import com.youmu.mall.exception.BusinessException;
import com.youmu.mall.permission.dao.SysRoleDao;
import com.youmu.mall.permission.service.ISysRoleService;
import com.youmu.mall.sys.domain.SysRole;
import com.youmu.mall.sys.utils.SysMenuUtil;
import com.youmu.mall.sys.vo.SysRoleListVo;
import com.youmu.mall.sys.vo.SysRoleUpdateVo;

/**
 * 系统角色服务实现类.
 * @author zh
 * @version $Id: SysRoleServiceImpl.java, v 0.1 2017年3月15日 下午12:38:28 zh Exp $
 */
@Service
public class SysRoleServiceImpl implements ISysRoleService {
    
    @Resource
    private SysRoleDao sysRoleDao;

    /** 
     * @see com.youmu.mall.permission.service.ISysRoleService#addSysRole(com.youmu.mall.sys.domain.SysRole)
     */
    @Transactional
    @Override
    public void addSysRole(SysRole role) {
        // 判断名称是否重复
        long  count = sysRoleDao.selectRepeatCount(role);
        if(count > 0) {
            throw new BusinessException("该角色已经存在");
        }
        // 添加角色获取id
        sysRoleDao.insertSysRole(role);
        // 如果有菜单 建立菜单和角色的关联关系
        if(role.getMenus() != null && !role.getMenus().isEmpty()) {
            sysRoleDao.insertRoleMenuRelationship(role);
        }
    }

    /** 
     * @see com.youmu.mall.permission.service.ISysRoleService#updateSysRole(com.youmu.mall.sys.domain.SysRole)
     */
    @Transactional
    @Override
    public void updateSysRole(SysRole role) {
        // 判断名称是否重复
        long  count = sysRoleDao.selectRepeatCount(role);
        if(count > 0) {
            throw new BusinessException("该角色已经存在");
        }
        sysRoleDao.updateById(role);
        // 如果菜单不为空
        if(role.getMenus() != null && !role.getMenus().isEmpty()) {
            sysRoleDao.deleteRoleMenuRelationship(role.getId());
            sysRoleDao.insertRoleMenuRelationship(role);
        }
    }

    /** 
     * @see com.youmu.mall.permission.service.ISysRoleService#deleteSysRole(java.lang.Long)
     */
    @Transactional
    @Override
    public void deleteSysRole(Long id) {
        sysRoleDao.deleteById(id);
        // 删除角色和菜单的关联关系.
        sysRoleDao.deleteRoleMenuRelationship(id);
    }

    /** 
     * @see com.youmu.mall.permission.service.ISysRoleService#listSysRole(com.youmu.mall.base.query.PageQuery)
     */
    @Override
    public Page<SysRoleListVo> listSysRole(PageQuery query) {
        Page<SysRoleListVo> page = new Page<>(query.getPageNum(), query.getPageSize());
        page.setTotal(sysRoleDao.selectSysRoleCount(query));
        if(page.getTotal() > 0) {
            page.setData(sysRoleDao.selectSysRoleList(query));
        }
        return page;
    }

    /** 
     * @see com.youmu.mall.permission.service.ISysRoleService#listAll()
     */
    @Override
    public List<SysRoleListVo> listAll() {
        return sysRoleDao.selectAllSysRole();
    }

    /** 
     * @see com.youmu.mall.permission.service.ISysRoleService#getUpdateRoleInfo(java.lang.Long)
     */
    @Override
    public SysRoleUpdateVo getUpdateRoleInfo(Long roleId) {
        SysRoleUpdateVo info = sysRoleDao.selectUpdateRoleInfo(roleId);
        info.setMenus(SysMenuUtil.convertMenusToTree(info.getMenus()));
        return info;
    }

}
