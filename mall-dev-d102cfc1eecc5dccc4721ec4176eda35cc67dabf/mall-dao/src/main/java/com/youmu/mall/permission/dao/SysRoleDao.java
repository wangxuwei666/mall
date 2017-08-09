/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.permission.dao;

import java.util.List;

import com.youmu.mall.base.query.PageQuery;
import com.youmu.mall.sys.domain.SysRole;
import com.youmu.mall.sys.vo.SysRoleListVo;
import com.youmu.mall.sys.vo.SysRoleUpdateVo;

/**
 * 系统角色数据访问接口.
 * @author zh
 * @version $Id: SysRoleDao.java, v 0.1 2017年3月14日 下午5:16:51 zh Exp $
 */
public interface SysRoleDao {

    /**
     * 添加
     */
    void insertSysRole(SysRole role);

    /***
     * 删除
     */
    void deleteById(Long id);
    
    /**
     * 修改
     */
    void updateById(SysRole role);
    
    /***
     * 查询
     */
    SysRole selectById(Long id);

    /**
     * 添加角色菜单的关联关系
     * @param role
     */
    void insertRoleMenuRelationship(SysRole role);

    /**
     * 删除角色菜单的关联关系
     * @param id
     */
    void deleteRoleMenuRelationship(Long id);

    /**
     * 查询添加时重复的角色数量
     * @param role
     * @return 
     */
    long selectRepeatCount(SysRole role);

    /**
     * 查询系统角色的数量.
     * @param query
     * @return
     */
    Long selectSysRoleCount(PageQuery query);

    /**
     * 查询系统角色的列表.
     * @param query
     * @return
     */
    List<SysRoleListVo> selectSysRoleList(PageQuery query);

    /**
     * 查询所有的系统角色.
     * @return
     */
    List<SysRoleListVo> selectAllSysRole();

    /**
     * 查询修改的角色的信息.
     * @param roleId
     * @return
     */
    SysRoleUpdateVo selectUpdateRoleInfo(Long roleId);
    
}
