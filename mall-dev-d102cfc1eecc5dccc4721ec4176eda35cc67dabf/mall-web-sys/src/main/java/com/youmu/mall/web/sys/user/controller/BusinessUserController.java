/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.web.sys.user.controller;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.youmu.common.result.utils.JsonResult;
import com.youmu.common.result.utils.JsonResultUtils;
import com.youmu.common.validate.utils.ValidateUtils;
import com.youmu.mall.user.domain.BusinessUser;
import com.youmu.mall.user.query.BusinessUserQuery;
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
    
    @ApiOperation(value="添加商户用户", httpMethod="POST")
    @ApiImplicitParams({
        @ApiImplicitParam(name="account", value="商户账户名", paramType="query", dataType="string"),
        @ApiImplicitParam(name="business.id", value="商户id", paramType="query", dataType="number"),
        @ApiImplicitParam(name="type", value="用户的类型 1 普通商户管理人员只能等录商户前台 核销二维码等  2 银行审核人员 只能审核装修贷款的订单 3 系统的超级管理员暂时不分配权限", paramType="query", dataType="number"),
        @ApiImplicitParam(name="password", value="登录密码", paramType="query", dataType="string"),
    })
    @RequestMapping(value="/addBusinessUser", method=RequestMethod.POST)
    public JsonResult addBusinessUser(BusinessUser businessUser) {
        ValidateUtils.checkNotBlank(businessUser.getAccount(), "账户的信息不能为空");
        ValidateUtils.checkPassword(businessUser.getPassword(), "密码必须包含字母和数字，长度六位以上");
        ValidateUtils.checkNotNull("商户id不能为空", businessUser.getBusiness(), businessUser.getBusiness().getId());
        businessUserService.addBusinessUser(businessUser);
        return JsonResultUtils.ok();
    }
    
    @ApiOperation(value="编辑商户用户", httpMethod="POST")
    @ApiImplicitParams({
        @ApiImplicitParam(name="id", value="账户id", paramType="query", dataType="string"),
        @ApiImplicitParam(name="account", value="商户账户名", paramType="query", dataType="string"),
        @ApiImplicitParam(name="business.id", value="商户id", paramType="query", dataType="number"),
        @ApiImplicitParam(name="type", value="用户的类型 1 普通商户管理人员只能等录商户前台 核销二维码等  2 银行审核人员 只能审核装修贷款的订单 3 系统的超级管理员暂时不分配权限", paramType="query", dataType="number"),
        @ApiImplicitParam(name="password", value="登录密码", paramType="query", dataType="string"),
    })
    @RequestMapping(value="/updateBusinessUser", method=RequestMethod.POST)
    public JsonResult updateBusinessUser(BusinessUser businessUser) {
        ValidateUtils.checkNotNull("商户的id不能为空", businessUser.getId());
        if(StringUtils.isNotBlank(businessUser.getPassword())) {
            ValidateUtils.checkPassword(businessUser.getPassword(), "密码必须包含字母和数字，长度六位以上");
        }
        businessUserService.updateBusinessUser(businessUser);
        return JsonResultUtils.ok();
    }
    
    @ApiOperation(value="删除商户用户", httpMethod="GET")
    @ApiImplicitParams({
        @ApiImplicitParam(name="id", value="商户的id", paramType="query", dataType="string"),
    })
    @RequestMapping(value="/deleteBusinessUser", method=RequestMethod.GET)
    public JsonResult deleteBusinessUser(Long id) {
        ValidateUtils.checkNotNull("商户的id不能为空", id);
        businessUserService.deleteBusinessUser(id);
        return JsonResultUtils.ok();
    }
    
    @ApiOperation(value="商户用户列表", httpMethod="GET")
    @ApiImplicitParams({
        @ApiImplicitParam(name="pageNum", value="页码", paramType="query", dataType="number"),
        @ApiImplicitParam(name="pageSize", value="页码", paramType="query", dataType="number"),
        @ApiImplicitParam(name="typeId", value="商户用户的类型 1 普通商户 2 装修公司商户 3 银行账户 4 后台账户", paramType="query", dataType="number"),
    })
    @RequestMapping(value="/listBusinessUser", method=RequestMethod.GET)
    public JsonResult listBusinessUser(BusinessUserQuery query) {
        return JsonResultUtils.ok(businessUserService.listBusinessUser(query));
    }
    
    @ApiOperation(value="获取一个商户用户", httpMethod="GET")
    @ApiImplicitParams({
        @ApiImplicitParam(name="id", value="商户用户id", paramType="query", dataType="long"),
    })
    @RequestMapping(value="/getBusinessUserById", method=RequestMethod.GET)
    public JsonResult getBusinessUserById(Long id) {
        ValidateUtils.checkNotNull("id不能为空", id);
        return JsonResultUtils.ok(businessUserService.getBusinessUserById(id));
    }

}
