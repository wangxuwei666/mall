/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.permission.service;

import java.util.List;

import com.youmu.mall.sys.domain.SysMenu;
import com.youmu.mall.sys.vo.SysMenuListVo;

/**
 * 菜单服务接口.
 * @author zh
 * @version $Id: IMenuService.java, v 0.1 2017年3月15日 上午11:32:28 zh Exp $
 */
public interface IMenuService {

    /**
     * 列出所有的菜单
     * @return
     */
    List<SysMenuListVo> listAll();

    /**
     * 添加一个菜单
     * @param menu
     * @return
     */
    void addSysMenu(SysMenu menu);

    /**
     * 删除一个菜单
     * @param id
     */
    void deleteSysMenu(Long id);

    /**
     * 修改一个菜单
     * @param menu
     */
    void updateSysMenu(SysMenu menu);

    /**
     * 
     * @param roleId
     * @return
     */
    List<SysMenuListVo> listRoleMenus(Long roleId);

}
