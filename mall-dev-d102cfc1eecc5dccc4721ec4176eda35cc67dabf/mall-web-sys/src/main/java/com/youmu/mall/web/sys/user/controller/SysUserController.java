/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.web.sys.user.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.youmu.common.context.UserContext;
import com.youmu.common.result.utils.JsonResult;
import com.youmu.common.result.utils.JsonResultUtils;
import com.youmu.common.token.utils.TokenUtils;
import com.youmu.common.validate.utils.ValidateUtils;
import com.youmu.mall.base.query.PageQuery;
import com.youmu.mall.user.domain.SysUser;
import com.youmu.mall.user.service.ISysUserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 后台用户管理控制器
 * 
 * @author zh
 * @version $Id: UserController.java, v 0.1 2017年2月25日 下午4:27:25 zh Exp $
 */
@RestController
@Api(value="系统用户控制器", description="后台用户的一些操作")
@RequestMapping("/sysuser")
public class SysUserController {
    
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    @Resource
    private ISysUserService sysUserService;
    
    /**
     * 
     * @param user
     * @return
     */
    @ApiOperation(value="登录", httpMethod="POST")
    @ApiImplicitParams({
        @ApiImplicitParam(name="account",value="账号",paramType="query",dataType="string"),
        @ApiImplicitParam(name="password",value="密码",paramType="query",dataType="string"),
    })
    @RequestMapping(value = "/login")
    public JsonResult login(String account, String password, HttpServletResponse response) {
        ValidateUtils.checkNotBlank(account, "账号不能为空");
        ValidateUtils.checkNotBlank(password, "密码不能为空");
        return JsonResultUtils.ok(sysUserService.login(account, password, response));
    }
    
    /**
     * 退出登录
     */
    @ApiOperation(value="退出登录", httpMethod="GET")
    @RequestMapping("/logout")
    public JsonResult logout(HttpServletResponse response) {
        sysUserService.logout();
        TokenUtils.removeTokenInCookie(response, TokenUtils.SYS_TOKEN_SUFFIX);
        return JsonResultUtils.ok();
    }
    
    @ApiOperation(value="添加一个系统管理员", httpMethod="POST")
    @RequestMapping(value = "/addSysUser", method=RequestMethod.POST)
    @ApiImplicitParams({
        @ApiImplicitParam(value="账号", name="account", paramType="query", dataType="string", required=true),
        @ApiImplicitParam(value="用户名", name="username", paramType="query", dataType="string", required=true),
        @ApiImplicitParam(value="密码 请在前端判断两次密码是否一致", name="password", paramType="query", dataType="string", required=true),
        @ApiImplicitParam(value="角色id", name="role.id", paramType="query", dataType="long", required=true),
    })
    public JsonResult addSysUser(SysUser user) {
        ValidateUtils.checkNotBlank(user.getAccount(), "账号不能为空");
        ValidateUtils.checkPassword(user.getPassword(), "密码必须包含字母和数字，长度六位以上");
        sysUserService.addSysUser(user);
        return JsonResultUtils.ok();
    }
    
    @ApiOperation(value="修改一个系统管理员", httpMethod="POST")
    @RequestMapping(value = "/updateSysUser", method=RequestMethod.POST)
    @ApiImplicitParams({
        @ApiImplicitParam(value="系统用户的id", name="id", paramType="query", dataType="string", required=true),
        @ApiImplicitParam(value="账号", name="account", paramType="query", dataType="string", required=false),
        @ApiImplicitParam(value="用户名", name="username", paramType="query", dataType="string", required=false),
        @ApiImplicitParam(value="角色id", name="role.id", paramType="query", dataType="long", required=false),
    })
    public JsonResult updateSysUser(SysUser user) {
        ValidateUtils.checkNotNull("id不能为空", user.getId());
        sysUserService.updateSysUser(user);
        return JsonResultUtils.ok();
    }
    
    @ApiOperation(value="获取修改系统管理人员的信息", httpMethod="POST")
    @RequestMapping(value = "/getUpdateSysUser", method=RequestMethod.POST)
    @ApiImplicitParams({
        @ApiImplicitParam(value="系统用户的id", name="id", paramType="query", dataType="string", required=true),
    })
    public JsonResult getUpdateSysUser(Long id) {
        ValidateUtils.checkNotNull("id不能为空", id);
        return JsonResultUtils.ok(sysUserService.getUpdateSysUser(id));
    }
    
    @ApiOperation(value="列出所有的系统管理员", httpMethod="POST")
    @RequestMapping(value = "/listSysUser", method=RequestMethod.POST)
    @ApiImplicitParams({
        @ApiImplicitParam(value="页码", name="pageNum", paramType="query", dataType="int", required=true),
        @ApiImplicitParam(value="每页多少条", name="pageSize", paramType="query", dataType="int",  required=true),
    })
    public JsonResult listSysUser(PageQuery query) {
        return JsonResultUtils.ok(sysUserService.listSysUser(query));
    }
    
    @ApiOperation(value="禁用系统管理员", httpMethod="GET")
    @RequestMapping(value = "/disableSysUser", method=RequestMethod.GET)
    @ApiImplicitParams({
        @ApiImplicitParam(value="用户id", name="id", paramType="query", dataType="int", required=true),
    })
    public JsonResult disableSysUser(Long id) {
        ValidateUtils.checkNotNull("id不能为空", id);
        sysUserService.disableSysUser(id);
        return JsonResultUtils.ok();
    }
    
    @ApiOperation(value="启用系统管理员", httpMethod="GET")
    @RequestMapping(value = "/enableSysUser", method=RequestMethod.GET)
    @ApiImplicitParams({
        @ApiImplicitParam(value="用户id", name="id", paramType="query", dataType="int", required=true),
    })
    public JsonResult enableSysUser(Long id) {
        ValidateUtils.checkNotNull("id不能为空", id);
        sysUserService.enableSysUser(id);
        return JsonResultUtils.ok();
    }
    
    @ApiOperation(value="禁用系统管理员", httpMethod="GET")
    @RequestMapping(value = "/deleteSysUser", method=RequestMethod.GET)
    @ApiImplicitParams({
        @ApiImplicitParam(value="用户id", name="id", paramType="query", dataType="int", required=true),
    })
    public JsonResult deleteSysUser(Long id) {
        ValidateUtils.checkNotNull("id不能为空", id);
        sysUserService.deleteSysUser(UserContext.getLongUserId(), id);
        return JsonResultUtils.ok();
    }
    
    @ApiOperation(value="重置系统管理员密码", httpMethod="POST")
    @RequestMapping(value = "/resetSysUserPassword", method=RequestMethod.POST)
    @ApiImplicitParams({
        @ApiImplicitParam(value="用户id", name="userId", paramType="query", dataType="long", required=true),
        @ApiImplicitParam(value="新密码", name="password", paramType="query", dataType="string", required=true),
    })
    public JsonResult resetSysUserPassword(String userId, String password) {
        ValidateUtils.checkNotBlank(userId, "用户id不能为空");
        ValidateUtils.checkPassword(password, "密码必须包含字母和数字，长度六位以上");
        sysUserService.resetSysUserPassword(UserContext.getLongUserId(), userId, password);
        return JsonResultUtils.ok();
    }
    
    @ApiOperation(value="修改用户密码", httpMethod="POST")
    @RequestMapping(value = "/changePassword", method=RequestMethod.POST)
    @ApiImplicitParams({
        @ApiImplicitParam(value="旧密码", name="oldPassword", paramType="query", dataType="string", required=true),
        @ApiImplicitParam(value="新密码", name="newPassword", paramType="query", dataType="string", required=true),
    })
    public JsonResult changePassword(String oldPassword, String newPassword) {
        ValidateUtils.checkPassword(newPassword, "密码必须包含字母和数字，长度六位以上");
        ValidateUtils.checkNotEquals(oldPassword, newPassword, "新密码不能和原密码相同");
        sysUserService.resetPassword(UserContext.getLongUserId(), oldPassword, newPassword);
        return JsonResultUtils.ok();
    }
}
