/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.web.bus.front.wx.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.youmu.common.result.utils.JsonResult;
import com.youmu.common.result.utils.JsonResultUtils;
import com.youmu.common.wx.utils.WxPublicNumberUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * 微信统一的控制器
 * @author zh
 * @version $Id: WxController.java, v 0.1 2017年3月9日 上午11:57:45 zh Exp $
 */
@RestController
@Api(value="微信统用控制器")
@RequestMapping("/wx")
public class WxController {
    
    @ApiOperation(value="获取jsTicket和签名", httpMethod="GET")
    @RequestMapping(value = "/getSignedJsTicket", method=RequestMethod.GET)
    @ApiImplicitParam(value="当前页码的url 不需要#号", name="url", dataType="string", paramType="query")
    public JsonResult getUserMobile(String url) {
        return JsonResultUtils.ok(WxPublicNumberUtil.getSignedJsTicket(url));
    }
}
