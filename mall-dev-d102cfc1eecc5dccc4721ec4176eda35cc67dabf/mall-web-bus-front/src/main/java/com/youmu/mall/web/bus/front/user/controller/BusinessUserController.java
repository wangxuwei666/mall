/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.web.bus.front.user.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.youmu.common.result.utils.JsonResult;
import com.youmu.common.result.utils.JsonResultUtils;
import com.youmu.common.upload.utils.ImageUploadUtils;
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
     * 商户前台登录
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
        return JsonResultUtils.ok(businessUserService.loginFront(account, password, response));
    }
    
    /**
     * 商户前台退出登录
     * @param user
     * @return
     */
    @ApiOperation(value="退出登录", httpMethod="GET")
    @RequestMapping(value = "/logout", method=RequestMethod.GET)
    public JsonResult logout(HttpServletResponse response) {
        businessUserService.loginoutFront(response);
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
    
    /**
     * 上传商户营业执照和门店图片
     * 
     * @return
     */
    @ApiOperation(value="上传商户营业执照和门店图片", httpMethod="POST", consumes="application/x-www-form-urlencoded")
    @ApiImplicitParams({
        @ApiImplicitParam(name="licenseImg", value="营业执照图片", dataType="string", paramType="form"),
        @ApiImplicitParam(name="storeImgs[]", value="门店图片,至少三张", dataType="string[]", paramType="form"),
    })
    @RequestMapping(value = "/uploadBusinessLicense", method=RequestMethod.POST)
    public JsonResult uploadBusinessLicense(String licenseImg, @RequestParam(name="storeImgs[]") String[] storeImgs) {
        if(storeImgs == null || storeImgs.length < 3 || storeImgs.length > 5) {
            throw new ParamException("商户门店图片至少三张,至多五张");
        }
        if(licenseImg == null || licenseImg.isEmpty()) {
            throw new ParamException("商户营业执照不能为空");
        }
        String licensePath = ImageUploadUtils.uploadImageDefault(ImageUploadUtils.BUSINESS_LICENSE_IMG, licenseImg);
        List<String> storePaths = ImageUploadUtils.uploadImageDefault(ImageUploadUtils.BUSINESS_STORE_IMG, storeImgs);
        businessUserService.updateBusinessLicense(licensePath, storePaths);
        return JsonResultUtils.ok();
    }
    
   /**
    * 修改商户的登录密码
    * 
    * @return
    */
    @ApiOperation(value="修改商户用户的密码", httpMethod="POST", produces="multpart/form-data")
    @ApiImplicitParams({
        @ApiImplicitParam(name="oldPassword", value="原始密码", dataType="string", paramType="query"),
        @ApiImplicitParam(name="newPassword", value="新密码,新密码两次输入的判断在前端处理", dataType="string", paramType="query"),
    })
    @RequestMapping(value = "/updateBusinessUserPassword", method=RequestMethod.POST)
    public JsonResult updateBusinessUserPassword(String oldPassword, String newPassword) {
        if(StringUtils.equals(oldPassword, newPassword)) {
            throw new ParamException("新密码不能和原密码一致");
        }
        ValidateUtils.checkPassword(newPassword, "密码必须包含字母和数字，长度六位以上");
        businessUserService.updateBusinessPassword(oldPassword, newPassword);
        return JsonResultUtils.ok();
    }
    

}
