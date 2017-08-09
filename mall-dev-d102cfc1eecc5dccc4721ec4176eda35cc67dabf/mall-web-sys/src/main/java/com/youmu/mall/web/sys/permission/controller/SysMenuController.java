/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.web.sys.permission.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.youmu.common.result.utils.JsonResult;
import com.youmu.common.result.utils.JsonResultUtils;
import com.youmu.common.validate.utils.ValidateUtils;
import com.youmu.mall.permission.service.IMenuService;
import com.youmu.mall.sys.domain.SysMenu;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 菜单控制器.
 * @author zh
 * @version $Id: MenuController.java, v 0.1 2017年3月15日 上午11:29:35 zh Exp $
 */
@Api(description="菜单控制器")
@RestController("/sysMenu")
public class SysMenuController {
    
    @Resource
    private IMenuService menuService;
    
    @ApiOperation(value="添加一个菜单", httpMethod="GET")
    @RequestMapping("/addSysMenu")
    @ApiImplicitParams({
        @ApiImplicitParam(value="菜单名称", name="name", paramType="query", dataType="string", required=true),
        @ApiImplicitParam(value="菜单url", name="url", paramType="query", dataType="string", required=true),
        @ApiImplicitParam(value="父菜单id", name="parent.id", paramType="query", dataType="long", required=false),
    })
    public JsonResult addSysMenu(SysMenu menu) {
        ValidateUtils.checkNotBlank(menu.getName(), "菜单名称不能为空");
        ValidateUtils.checkNotBlank(menu.getUrl(), "菜单url不能为空");
        menuService.addSysMenu(menu);
        return JsonResultUtils.ok();
    }
    
    @RequestMapping("/deleteSysMenu")
    @ApiOperation(value="删除一个菜单", httpMethod="GET")
    @ApiImplicitParams({
        @ApiImplicitParam(value="菜单id", name="id", paramType="query", dataType="int", required=true),
    })
    public JsonResult deleteSysMenu(Long id) {
        ValidateUtils.checkNotNull("id不能为空", id);
        menuService.deleteSysMenu(id);
        return JsonResultUtils.ok();
    }
    
    @ApiOperation(value="修改一个菜单", httpMethod="POST")
    @RequestMapping(value = "/updateSysMenu", method=RequestMethod.POST)
    @ApiImplicitParams({
        @ApiImplicitParam(value="菜单id", name="id", paramType="query", dataType="long", required=true),
        @ApiImplicitParam(value="菜单名称", name="name", paramType="query", dataType="string",  required=false),
        @ApiImplicitParam(value="菜单url", name="url", paramType="query", dataType="string", required=false),
        @ApiImplicitParam(value="父菜单id", name="parent.id", paramType="query", dataType="long", required=false),
    })
    public JsonResult updateSysMenu(SysMenu menu) {
        menuService.updateSysMenu(menu);
        return JsonResultUtils.ok();
    }
    
    @ApiOperation(value="列出所有的可用菜单", httpMethod="GET")
    @RequestMapping("/listAll")
    public JsonResult listAll() {
        return JsonResultUtils.ok(menuService.listAll());
    }
}
