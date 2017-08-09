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

import com.alibaba.fastjson.JSON;
import com.youmu.common.result.utils.JsonResult;
import com.youmu.common.result.utils.JsonResultUtils;
import com.youmu.common.validate.utils.ValidateUtils;
import com.youmu.mall.base.query.PageQuery;
import com.youmu.mall.exception.ParamException;
import com.youmu.mall.permission.service.ISysRoleService;
import com.youmu.mall.sys.domain.SysRole;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 系统角色的控制器.
 * @author zh
 * @version $Id: SysRoleController.java, v 0.1 2017年3月15日 上午10:19:46 zh Exp $
 */
@Api(description="系统角色控制器")
@RestController
@RequestMapping("/sysRole")
public class SysRoleController {
    
    @Resource
    private ISysRoleService sysRoleService;
    
    @ApiOperation(value="添加一个角色")
    @RequestMapping(value = "/addSysRole", method=RequestMethod.POST)
    @ApiImplicitParams({
        @ApiImplicitParam(value="角色name", name="name", paramType="query", dataType="string",  required=true),
        @ApiImplicitParam(value="菜单id", name="menus[0].id", paramType="query", dataType="long", required=false),
        @ApiImplicitParam(value="菜单id", name="menus[1].id", paramType="query", dataType="long", required=false),
        @ApiImplicitParam(value="菜单id", name="menus[2].id", paramType="query", dataType="long", required=false),
    })
    public JsonResult addSysRole(SysRole role) {
        ValidateUtils.checkNotBlank(role.getName(), "名称不能为空");
        // 角色的菜单不能为空
        if(role.getMenus() == null || role.getMenus().isEmpty()) {
            throw new ParamException("角色必须拥有菜单");
        }
        sysRoleService.addSysRole(role);
        return JsonResultUtils.ok();
    }
    
    @ApiOperation(value="修改一个角色")
    @RequestMapping(value = "/updateSysRole", method=RequestMethod.POST)
    @ApiImplicitParams({
        @ApiImplicitParam(value="角色id", name="id", paramType="query", dataType="long", required=true),
        @ApiImplicitParam(value="角色name", name="name", paramType="query", dataType="string", required=false),
        @ApiImplicitParam(value="菜单id", name="menus[0].id", paramType="query", dataType="long", required=false),
        @ApiImplicitParam(value="菜单id", name="menus[1].id", paramType="query", dataType="long", required=false),
        @ApiImplicitParam(value="菜单id", name="menus[2].id", paramType="query", dataType="long", required=false),
    })
    public JsonResult updateSysRole(SysRole role) {
        ValidateUtils.checkNotNull("id不能为空", role.getId());
        sysRoleService.updateSysRole(role);
        return JsonResultUtils.ok();
    }
    
    @ApiOperation(value="删除一个角色")
    @RequestMapping(value = "/deleteSysRole", method = RequestMethod.GET)
    @ApiImplicitParams({
        @ApiImplicitParam(value="角色id", name="id", required=true, paramType="query", dataType="long"),
    })
    public JsonResult deleteSysRole(Long id) {
        ValidateUtils.checkNotNull("id不能为空", id);
        sysRoleService.deleteSysRole(id);
        return JsonResultUtils.ok();
    }
    
    @ApiOperation(value="列出角色")
    @RequestMapping(value = "/listSysRole", method=RequestMethod.POST)
    @ApiImplicitParams({
        @ApiImplicitParam(value="页码", name="pageNum", paramType="query", dataType="int", required=true),
        @ApiImplicitParam(value="每页多少条", name="pageSize", paramType="query", dataType="int", required=true),
    })
    public JsonResult listSysRole(PageQuery query) {
        return JsonResultUtils.ok(sysRoleService.listSysRole(query));
    }
    
    @ApiOperation(value="列出所有的角色")
    @RequestMapping(value = "/listAll", method=RequestMethod.GET)
    public JsonResult listAll() {
        return JsonResultUtils.ok(sysRoleService.listAll());
    }
    
    @ApiOperation(value="修改角色回显", httpMethod="GET")
    @RequestMapping("/getUpdateRoleInfo")
    @ApiImplicitParams({
        @ApiImplicitParam(value="角色id", name="roleId", paramType="query", dataType="long", required=true),
    })
    public JsonResult getUpdateRoleInfo(Long roleId) {
        ValidateUtils.checkNotNull("角色id不能为空", roleId);
        return JsonResultUtils.ok(sysRoleService.getUpdateRoleInfo(roleId));
    }
    
}
