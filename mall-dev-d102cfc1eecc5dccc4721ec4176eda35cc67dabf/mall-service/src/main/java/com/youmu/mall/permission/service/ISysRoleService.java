/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.permission.service;

import java.util.List;

import com.youmu.common.page.utils.Page;
import com.youmu.mall.base.query.PageQuery;
import com.youmu.mall.sys.domain.SysRole;
import com.youmu.mall.sys.vo.SysRoleListVo;
import com.youmu.mall.sys.vo.SysRoleUpdateVo;

/**
 * 系统角色服务.
 * @author zh
 * @version $Id: ISysRoleService.java, v 0.1 2017年3月15日 下午12:38:07 zh Exp $
 */
public interface ISysRoleService {

    /**
     * 添加一个系统角色
     * @param role
     * @return
     */
    void addSysRole(SysRole role);

    /**
     * 修改一个系统角色
     * @param role
     */
    void updateSysRole(SysRole role);

    /**
     * 删除一个系统角色
     * @param id
     */
    void deleteSysRole(Long id);

    /**
     * 分页列出系统角色.
     * @param query
     */
    Page<SysRoleListVo> listSysRole(PageQuery query);

    /**
     * 列出所有的系统角色.
     * @return
     */
    List<SysRoleListVo> listAll();

    /**
     * 系统角色修改信息
     * @param roleId
     * @return
     */
    SysRoleUpdateVo getUpdateRoleInfo(Long roleId);
    
}
