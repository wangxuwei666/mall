/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.web.shop.banner.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.youmu.common.result.utils.JsonResult;
import com.youmu.common.result.utils.JsonResultUtils;
import com.youmu.common.validate.utils.ValidateUtils;
import com.youmu.mall.banner.service.IBannerService;
import com.youmu.mall.banner.vo.BannerShopVo;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * @author yujiahao
 * @version $Id: BannerController.java, v 0.1 2017年3月6日 下午7:20:40 yujiahao Exp $
 */
@RestController
@RequestMapping("/banner/")
public class BannerController {
    @Resource
    private IBannerService bannerService;
    
    @ApiOperation(value = "用户端-首页Banner", httpMethod="GET")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "bannerType", value = "banner类型", required = true, dataType = "Integer", paramType = "query")
    })
    @RequestMapping(value ="getBannerShopVo", method=RequestMethod.GET)
    public JsonResult getBannerShopVo(Integer bannerType) {
        ValidateUtils.checkNotNull("banner类型不能为空",bannerType);
        List<BannerShopVo> banners = bannerService.getBannerShopVo(bannerType);
        return JsonResultUtils.ok(banners);
    }
}
