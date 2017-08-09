/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.web.bus.user.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.youmu.common.result.utils.JsonResult;
import com.youmu.common.result.utils.JsonResultUtils;
import com.youmu.common.validate.utils.ValidateUtils;
import com.youmu.mall.exception.ParamException;
import com.youmu.mall.user.service.IBusinessUserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 后台用户控制器
 * 
 * @author zh
 * @version $Id: UserController.java, v 0.1 2017年2月25日 下午4:27:25 zh Exp $
 */
@RestController
@Api(value="后台用户账户管理控制器")
@RequestMapping("/bususer")
public class BusinessUserController {
    
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    @Resource
    private IBusinessUserService businessUserService;
    
    /**
     * 商户后台登录
     * @param user
     * @return
     */
    @ApiOperation(value="登录", httpMethod="POST")
    @ApiImplicitParams({
        @ApiImplicitParam(name="account",value="账号",paramType="query",dataType="string"),
        @ApiImplicitParam(name="password",value="密码",paramType="query",dataType="string"),
    })
    @RequestMapping(value = "/login", method=RequestMethod.POST)
    public JsonResult login(String account, String password, HttpServletResponse response) {
        ValidateUtils.checkNotBlank(account, "账号不能为空");
        ValidateUtils.checkNotBlank(password, "密码不能为空");
        return JsonResultUtils.ok(businessUserService.loginBack(account, password, response));
    }
    
    /**
     * 商户后台推出登录
     * @param user
     * @return
     */
    @ApiOperation(value="退出登录", httpMethod="POST")
    @RequestMapping(value = "/logout", method=RequestMethod.POST)
    public JsonResult logout(HttpServletResponse response) {
        businessUserService.loginoutBack(response);
        return JsonResultUtils.ok();
    }
    
    /**
     * 商户前台登录
     * @param user
     * @return
     */
    @ApiOperation(value="重置密码", httpMethod="POST")
    @ApiImplicitParams({
        @ApiImplicitParam(name="oldPassword",value="原密码",paramType="query",dataType="string"),
        @ApiImplicitParam(name="newPassword",value="新密码",paramType="query",dataType="string"),
    })
    @RequestMapping(value = "/restPassword", method=RequestMethod.POST)
    public JsonResult restPassword(String oldPassword, String newPassword) {
        if(StringUtils.equals(oldPassword, newPassword)) {
            throw new ParamException("新旧密码不能相同");
        }
        ValidateUtils.checkPassword(newPassword, "密码必须由字母和数字组成");
        businessUserService.updateBusinessPassword(oldPassword, newPassword);
        return JsonResultUtils.ok();
    }
    
    /**
     * 商户信息获取
     * @return
     */
    @ApiOperation(value="获取商户的基本信息，在我的页面", httpMethod="GET")
    @RequestMapping(value = "/getLoggedBusinessInfo", method=RequestMethod.GET)
    public JsonResult getLoggedBusinessInfo() {
        return JsonResultUtils.ok(businessUserService.getLoggedBusinessInfo());
    }

}
