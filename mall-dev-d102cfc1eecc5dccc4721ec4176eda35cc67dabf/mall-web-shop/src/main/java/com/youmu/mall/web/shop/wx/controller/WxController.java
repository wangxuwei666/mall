/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.web.shop.wx.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.youmu.common.result.utils.JsonResult;
import com.youmu.common.result.utils.JsonResultUtils;
import com.youmu.common.validate.utils.ValidateUtils;
import com.youmu.common.wx.utils.WxPublicNumberUtil;
import com.youmu.mall.user.domain.User;
import com.youmu.mall.wx.service.IWxAuthService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 微信统一的控制器
 * @author zh
 * @version $Id: WxController.java, v 0.1 2017年3月9日 上午11:57:45 zh Exp $
 */
@RestController
@Api(description="微信统用控制器")
@RequestMapping("/wx")
public class WxController {
    
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    /** 用户数据访问接口  */
    @Resource
    private IWxAuthService wxService;
    
    @ApiOperation(value="微信服务器认证接口", httpMethod="GET")
    @RequestMapping(value = "/auth", method={RequestMethod.GET, RequestMethod.POST})
    public void auth(String signature, String timestamp, String nonce, String echostr, HttpServletResponse response) {
        logger.info("wx auth signature:{}, timestamp:{}, nonce:{}, echostr:{}.", signature, timestamp, nonce, echostr);
        if(WxPublicNumberUtil.checkSignature(signature, timestamp, nonce)) {
            logger.error("validate wx signature fail ...");
        }
        try {
            response.getWriter().println(echostr);
        } catch (IOException e) {
            logger.error("微信服务器认证异常", e);
        }
    }
    
    @ApiOperation(value="获取微信用户openid的url", httpMethod="GET")
    @RequestMapping(value = "/getWxPayCodeUrl", method=RequestMethod.GET)
    public JsonResult getWxPayCodeUrl() {
       return JsonResultUtils.ok(wxService.getWxPayCodeUrl());
    }
    
    @ApiOperation(value="获取微信用户授权的url", httpMethod="GET")
    @RequestMapping(value = "/getWxUserAuthUrl", method=RequestMethod.GET)
    public JsonResult getWxUserAuthUrl(String url) {
       return JsonResultUtils.ok(wxService.getWxUserAuthUrl(url));
    }
    
    @ApiOperation(value="获取微信分享用户授权的url", httpMethod="GET")
    @RequestMapping(value = "/getShareWxUserAuthUrl", method=RequestMethod.GET)
    public JsonResult getShareWxUserAuthUrl(String url){
       return JsonResultUtils.ok(wxService.getShareWxUserAuthUrl(url));
    }
    
    @ApiOperation(value="获取微信注册用户授权的url", httpMethod="GET")
    @RequestMapping(value = "/getRegistWxUserAuthUrl", method=RequestMethod.GET)
    public JsonResult getRegistWxUserAuthUrl() {
       return JsonResultUtils.ok(wxService.getRegistWxUserAuthUrl());
    }
    
    @ApiOperation(value="微信自动登录回掉接口", httpMethod="GET")
    @RequestMapping(value = "/authCallBack", method=RequestMethod.GET)
    public void authCallBack(String code, String state, HttpServletResponse response) {
        logger.debug("code : {} state {}.", code, state);
        wxService.handleWxAuthCallBack(code, state, response);
    }
    
    @ApiOperation(value="绑定微信用户", httpMethod="POST")
    @RequestMapping(value = "/bindWxAccount", method=RequestMethod.POST)
    @ApiImplicitParams({
        @ApiImplicitParam(value="wx公众号的openid", name="openid", dataType="string", paramType="query"),
        @ApiImplicitParam(value="手机号码", name="mobile", dataType="string", paramType="query"),
        @ApiImplicitParam(value="密码", name="password", dataType="string", paramType="query"),
    })
    public JsonResult bindWxAccount(User user, String openid, HttpServletResponse response) {
        ValidateUtils.checkNotBlank(openid, "微信openid不能为空");
        ValidateUtils.checkMobile(user.getMobile(), "手机号码有误");
        ValidateUtils.checkPassword(user.getPassword(), "密码必须包含字母和数字，长度六位以上");
        wxService.bindWxAccount(user, openid, response);
        return JsonResultUtils.ok();
    }
    
    @ApiOperation(value="获取jsTicket和签名", httpMethod="GET")
    @RequestMapping(value = "/getSignedJsTicket", method=RequestMethod.GET)
    @ApiImplicitParam(value="当前页码的url 不需要#号", name="url", dataType="string", paramType="query")
    public JsonResult getSignedJsTicket(String url) {
        return JsonResultUtils.ok(WxPublicNumberUtil.getSignedJsTicket(url));
    }
}
