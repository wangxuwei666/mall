/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.sys.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.youmu.mall.sys.domain.SysMenu;
import com.youmu.mall.sys.vo.SysMenuListVo;

/**
 * 系统菜单工具
 * @author zh
 * @version $Id: SysMenuUtil.java, v 0.1 2017年3月21日 上午11:41:54 zh Exp $
 */
public class SysMenuUtil {
    
    /**
     * 将菜单列表构成菜单树
     * @param allMenus
     * @return
     */
    public static List<SysMenuListVo> convertMenusToTree(List<SysMenuListVo> allMenus) {
        List<SysMenuListVo> menus = new ArrayList<>();
        // 找出一级菜单
        Iterator<SysMenuListVo> iterator = allMenus.iterator();
        while (iterator.hasNext()) {
            SysMenuListVo sysMenuListVo = iterator.next();
            if(sysMenuListVo.getParentId() == null) {
                menus.add(sysMenuListVo);
                iterator.remove();
            }
        }
        // 将子菜单添加到父菜单
        for (SysMenuListVo sysMenuListVo : menus) {
            for (SysMenuListVo menuListVo : allMenus) {
                Long id = sysMenuListVo.getId();
                if(id != null && id.equals(menuListVo.getParentId())) {
                    sysMenuListVo.getChildren().add(menuListVo);
                }
            }
        }
        return  menus;
    }
    
    /**
     * 将菜单列表构成菜单树
     * @param allMenus
     * @return
     */
    public static List<SysMenu> convertSysMenusToTree(List<SysMenu> allMenus) {
        List<SysMenu> menus = new ArrayList<>();
        // 找出一级菜单
        Iterator<SysMenu> iterator = allMenus.iterator();
        while (iterator.hasNext()) {
            SysMenu sysMenu = iterator.next();
            if(sysMenu.getParent() == null) {
                menus.add(sysMenu);
                iterator.remove();
            }
        }
        // 将子菜单添加到父菜单
        for (SysMenu m : menus) {
            for (SysMenu menu : allMenus) {
                Long id = m.getId();
                Long parentId = menu.getParent().getId();
                if(id != null && id.equals(parentId)) {
                    m.getChildren().add(menu);
                }
            }
        }
        return  menus;
    }
}
