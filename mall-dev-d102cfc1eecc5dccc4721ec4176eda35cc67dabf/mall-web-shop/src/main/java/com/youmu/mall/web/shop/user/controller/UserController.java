/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.web.shop.user.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
import com.youmu.common.upload.utils.ImageUploadUtils;
import com.youmu.common.validate.utils.ValidateUtils;
import com.youmu.mall.user.domain.User;
import com.youmu.mall.user.service.IUserService;
import com.youmu.mall.user.vo.UserInfoVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 用户控制器.
 * 
 * @author zh
 * @version $Id: UserController.java, v 0.1 2017年2月25日 下午4:27:25 zh Exp $
 */
@RestController
@Api(value="商城用户账户")
@RequestMapping("/user")
public class UserController {
    
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private IUserService userService;
    
    @ApiOperation(value="查询当前用户的手机号码", httpMethod="GET")
    @RequestMapping(value = "/getUserMobile", method=RequestMethod.GET)
    public JsonResult getUserMobile() {
        return JsonResultUtils.ok(userService.getUserMobile());
    }
    
    @ApiOperation(value="商城用户登录", httpMethod="POST")
    @ApiImplicitParams({
        @ApiImplicitParam(value="11位手机号码", name="mobile", paramType="query", required=true),
        @ApiImplicitParam(value="密码,最好不要是明文", name="password", paramType="query", required=true),
    })
    @RequestMapping(value = "/login", method=RequestMethod.POST)
    public JsonResult login(String mobile, String password, HttpServletResponse response) {
        ValidateUtils.checkMobile(mobile, "手机号码有误");
        ValidateUtils.checkNotBlank(password, "密码不能为空");
        return JsonResultUtils.ok(userService.login(mobile, password, response));
    }
    
    @ApiOperation(value="是否已经登录", httpMethod="GET")
    @RequestMapping(value = "/isLogged", method=RequestMethod.GET)
    public JsonResult isLogged(HttpServletRequest request) {
        return JsonResultUtils.ok(userService.isLogged(request));
    }
    
    @ApiOperation(value="商城用户退出登录", httpMethod="POST")
    @RequestMapping(value = "/logout", method=RequestMethod.POST)
    public JsonResult logout(HttpServletResponse response) {
        userService.logout();
        TokenUtils.removeTokenInCookie(response, TokenUtils.SHOP_TOKEN_SUFFIX);
        return JsonResultUtils.ok();
    }
    
    @ApiOperation(value="发送商城用户注册验证码", httpMethod="POST")
    @ApiImplicitParams({
        @ApiImplicitParam(value="11位手机号码", name="mobile", paramType="query", required=true),
    })
    @RequestMapping(value = "/sendRegistAuthCode", method=RequestMethod.POST)
    public JsonResult sendRegistAuthCode(String mobile) {
        ValidateUtils.checkMobile(mobile, "手机号码有误");
        userService.sendRegistAuthCode(mobile);
        return JsonResultUtils.ok();
    }
    
    @ApiOperation(value="商城用户注册", httpMethod="POST")
    @ApiImplicitParams({
        @ApiImplicitParam(value="11位手机号码", name="mobile", paramType="query", required=true),
        @ApiImplicitParam(value="验证码", name="authCode", paramType="query", required=true),
        @ApiImplicitParam(value="密码,最好不要是明文", name="password", paramType="query", required=true),
    })
    @RequestMapping(value = "/regist", method=RequestMethod.POST)
    public JsonResult regist(User user, String authCode, HttpServletResponse response) {
        ValidateUtils.checkMobile(user.getMobile(), "手机号码有误");
        ValidateUtils.checkPassword(user.getPassword(), "密码必须包含字母和数字，长度六位以上");
        ValidateUtils.checkNotBlank(authCode, "验证码不能为空");
        return JsonResultUtils.ok(userService.regist(user, authCode, response));
    }
    
    @ApiOperation(value="在微信里面注册", httpMethod="POST")
    @ApiImplicitParams({
        @ApiImplicitParam(value="11位手机号码", name="mobile", paramType="query", required=true),
        @ApiImplicitParam(value="验证码", name="authCode", paramType="query", required=true),
        @ApiImplicitParam(value="密码,最好不要是明文", name="password", paramType="query", required=true),
        @ApiImplicitParam(value="微信openid", name="openId", paramType="query", required=true),
    })
    @RequestMapping(value = "/registInWx", method=RequestMethod.POST)
    public JsonResult registInWx(User user, String authCode,  String openId, HttpServletResponse response) {
        ValidateUtils.checkMobile(user.getMobile(), "手机号码有误");
        ValidateUtils.checkPassword(user.getPassword(), "密码必须包含字母和数字，长度六位以上");
        ValidateUtils.checkNotBlank(authCode, "验证码不能为空");
        ValidateUtils.checkNotBlank(openId, "微信openId不能为空");
        return JsonResultUtils.ok(userService.registInWx(user, authCode, openId, response));
    }
    
    @ApiOperation(value="商城用户重置密码验证码发送", httpMethod="POST")
    @ApiImplicitParams({
        @ApiImplicitParam(value="11位手机号码", name="mobile", paramType="query", required=true),
    })
    @RequestMapping(value = "/sendRestPasswordAuthCode", method=RequestMethod.POST)
    public JsonResult sendRestPasswordAuthCode(String mobile) {
        ValidateUtils.checkMobile(mobile, "手机号码格式有误");
        userService.sendResetPasswordAuthCode(mobile);
        return JsonResultUtils.ok();
    }
    
    @ApiOperation(value="检查重置密码的验证码是否有效", httpMethod="GET")
    @ApiImplicitParams({
        @ApiImplicitParam(value="验证码", name="authCode", paramType="query", required=true),
    })
    @RequestMapping(value = "/checkResetPasswordAuthCode", method=RequestMethod.GET)
    public JsonResult checkResetPasswordAuthCode(String mobile, String authCode) {
        ValidateUtils.checkMobile(mobile, "手机号码格式有误");
        ValidateUtils.checkNotBlank(authCode, "验证码不能为空");
        return JsonResultUtils.ok(userService.checkResetPasswordAuthCode(mobile, authCode));
    }
    
    @ApiOperation(value="商城用户重置密码", httpMethod="POST")
    @ApiImplicitParams({
        @ApiImplicitParam(value="11位手机号码", name="mobile", paramType="query", required=true),
        @ApiImplicitParam(value="密码,最好不要是明文", name="password", paramType="query", required=true),
    })
    @RequestMapping(value = "/forgetResetPassword", method=RequestMethod.POST)
    public JsonResult resetPassword(String mobile, String password) {
        ValidateUtils.checkMobile(mobile, "手机号码不符合规范");
        ValidateUtils.checkPassword(password, "密码必须包含字母和数字，长度六位以上");
        userService.resetPassword(mobile, password);
        return JsonResultUtils.ok();
    }
    
    @ApiOperation(value="商城用户重置密码", httpMethod="POST")
    @ApiImplicitParams({
        @ApiImplicitParam(value="密码,最好不要是明文", name="oldPassword", paramType="query", required=true),
        @ApiImplicitParam(value="密码,最好不要是明文", name="newPassword", paramType="query", required=true),
    })
    @RequestMapping(value = "/resetPassword", method=RequestMethod.POST)
    public JsonResult changePassword(String oldPassword, String newPassword) {
        ValidateUtils.checkNotEquals(oldPassword, newPassword, "新密码和旧密码相同");
        ValidateUtils.checkPassword(newPassword, "密码必须包含字母和数字，长度六位以上");
        userService.changePassword(UserContext.getLongUserId(), oldPassword,  newPassword);
        return JsonResultUtils.ok();
    }
    
    
    @ApiOperation(value="向原来的手机号码发送验证码", httpMethod="GET")
    @RequestMapping(value = "/sendOldRestMobileAuthCode", method=RequestMethod.GET)
    public JsonResult sendOldRestMobileAuthCode() {
        userService.sendOldResetMobileAuthCode();
        return JsonResultUtils.ok();
    }
    
    @ApiOperation(value="检查原手机号的验证码是否正确", httpMethod="GET")
    @RequestMapping(value = "/checkOldRestMobileAuthCode", method=RequestMethod.GET)
    public JsonResult checkOldRestMobileAuthCode(String authCode) {
        return JsonResultUtils.ok(userService.checkOldRestMobileAuthCode(authCode));
    }
    
    @ApiOperation(value="商城用户重置手机号码验证码发送", httpMethod="GET")
    @ApiImplicitParams({
        @ApiImplicitParam(value="新的手机号码", name="newMobile", paramType="query", required=true),
    })
    @RequestMapping(value = "/sendRestMobileAuthCode", method=RequestMethod.GET)
    public JsonResult sendRestMobileAuthCode(String newMobile) {
        ValidateUtils.checkMobile(newMobile, "新手机号码有误");
        userService.sendResetMobileAuthCode(newMobile);
        return JsonResultUtils.ok();
    }
    
    @ApiOperation(value="商城用户重置手机号码", httpMethod="POST")
    @ApiImplicitParams({
        @ApiImplicitParam(value="11位手机号码", name="newMobile", paramType="query", required=true),
        @ApiImplicitParam(value="验证码", name="authCode", paramType="query", required=true),
    })
    @RequestMapping(value = "/resetMobile", method=RequestMethod.POST)
    public JsonResult resetMobile(String newMobile, String authCode) {
        ValidateUtils.checkMobile(newMobile, "新手机号码有误");
        ValidateUtils.checkNotBlank(authCode, "验证码不能为空");
        userService.restMobile(newMobile, authCode);
        return JsonResultUtils.ok();
    }
    
    @ApiOperation(value="修改用户头像", httpMethod="POST", consumes="application/x-www-form-urlencoded")
    @ApiImplicitParams({
        @ApiImplicitParam(value="头像图片", name="headImage", paramType="form", required=true),
    })
    @RequestMapping(value = "/updateHeadIcon", method=RequestMethod.POST)
    public JsonResult updateHeadIcon(String headImage) {
        ValidateUtils.checkNotBlank(headImage, "头像不能为空");
        String image = ImageUploadUtils.uploadImage(headImage, 150, 150, false, ImageUploadUtils.USER_HEAD_IMAGE);
        userService.updateUserHeadIcon(UserContext.getLongUserId(), image);
        return JsonResultUtils.ok();
    }
    
    @ApiOperation(value="我的", httpMethod="GET")
    @RequestMapping(value = "/getUserInfo", method=RequestMethod.GET)
    public JsonResult getUserInfo() {
        UserInfoVo vo = userService.getUserInfo();
        return JsonResultUtils.ok(vo);
    }
    
    @ApiOperation(value="确认收货", httpMethod="POST")
    @ApiImplicitParams({
        @ApiImplicitParam(value="订单id", name="orderId", paramType="query", required=true),
    })
    @RequestMapping(value = "/confirmRecevied", method=RequestMethod.POST)
    public JsonResult confirmRecevied(Long orderId) {
        userService.confirmRecevied(orderId);
        return JsonResultUtils.ok();
    }

    @RequestMapping(value = "/getUid", method=RequestMethod.GET)
    public JsonResult getUid(){
    	return JsonResultUtils.ok(userService.getUid());
    }
    
    @ApiOperation(value="判断分享用户是否登录，若登录url中接上uid", httpMethod="GET")
    @ApiImplicitParams({
        @ApiImplicitParam(value="商品详情页的url", name="url", paramType="form", dataType="String", required=true),
        @ApiImplicitParam(value="商品组别", name="groupType", paramType="form", dataType="String", required=false),
    })
    @RequestMapping(value = "/getShareUserPlusUid", method={RequestMethod.GET, RequestMethod.POST})
    public JsonResult getShareUserPlusUid(String url,Integer groupType,HttpServletResponse response){
    	return JsonResultUtils.ok(userService.getUrlPlusUid(url, groupType,response)); 
    }
    
}
