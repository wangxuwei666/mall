/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.web.sys.banner.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.youmu.common.global.constant.StatusConstant;
import com.youmu.common.page.utils.Page;
import com.youmu.common.result.utils.JsonResult;
import com.youmu.common.result.utils.JsonResultUtils;
import com.youmu.common.upload.utils.ImageUploadUtils;
import com.youmu.common.validate.utils.ValidateUtils;
import com.youmu.mall.banner.domain.Banner;
import com.youmu.mall.banner.query.BannerQuery;
import com.youmu.mall.banner.service.IBannerService;
import com.youmu.mall.banner.vo.BannerShopVo;
import com.youmu.mall.banner.vo.BannerSysVo;
import com.youmu.mall.exception.ParamException;
import com.youmu.mall.order.query.ProductOrderQuery;
import com.youmu.mall.order.service.IProductOrderService;
import com.youmu.mall.order.vo.ProductOrderShopVo;

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
    
    @ApiOperation(value = "管理后台-Banner管理", httpMethod="POST")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "bannerType", value = "banner类型", required = false, dataType = "Integer", paramType = "query",defaultValue="0"),
        @ApiImplicitParam(name = "pageNum", value = "当前页", required = true, dataType = "Integer", paramType = "query"),
        @ApiImplicitParam(name = "pageSize", value = "页码尺寸", required = true, dataType = "Integer", paramType = "query")
    })
    @RequestMapping(value ="getBannerSysList", method=RequestMethod.POST)
    public JsonResult getBannerSysList(BannerQuery query) {
        Page<BannerSysVo> banners = bannerService.getBannerSysList(query);
        return JsonResultUtils.ok(banners);
    }
    
    @ApiOperation(value = "管理后台-添加Banner", httpMethod="POST")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "name", value = "banner名称", required = false, paramType = "form"),
        @ApiImplicitParam(name = "type", value = "banner类型（默认为0，此字段为保留字段，暂时都为0）", required = true, paramType = "form",defaultValue="0"),
        @ApiImplicitParam(name = "image", value = "图片地址", required = true ,paramType="form"),
        @ApiImplicitParam(name = "linkurl", value = "链接地址", required = false, paramType = "form"),
        @ApiImplicitParam(name = "orders", value = "图片排序", required = false, paramType = "form"),
    })
    @RequestMapping(value ="saveBanner", method=RequestMethod.POST, consumes="application/x-www-form-urlencoded")
    public JsonResult saveBanner(Banner banner) {
        ValidateUtils.checkNotNull("参数不能为空", banner.getImage());
        if(banner.getType() == StatusConstant.ONE){
            //pc端尺寸
            banner.setImage(ImageUploadUtils.uploadImageDefault(ImageUploadUtils.BANNER_IMG ,banner.getImage()));
        }else if (banner.getType() == StatusConstant.ZERO) {
            //手机端尺寸
            banner.setImage(ImageUploadUtils.uploadImage(banner.getImage(), 750, 350, false, ImageUploadUtils.BANNER_IMG));
        }else {
            throw new ParamException("banner类型错误");
        }
        bannerService.saveBanner(banner);
        return JsonResultUtils.ok();
    }
    
    @ApiOperation(value = "管理后台-修改Banner", httpMethod="POST")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "banner的id", required = true, paramType = "query"),
        @ApiImplicitParam(name = "name", value = "banner名称", required = false, paramType = "query"),
        @ApiImplicitParam(name = "type", value = "banner类型", required = false, paramType = "query"),
        @ApiImplicitParam(name = "image", value = "图片地址", required = false,paramType="form"),
        @ApiImplicitParam(name = "linkurl", value = "链接地址", required = false, paramType = "query"),
        @ApiImplicitParam(name = "orders", value = "图片排序", required = false, paramType = "query"),
    })
    @RequestMapping(value ="updateBanner", method=RequestMethod.POST, consumes="application/x-www-form-urlencoded")
    public JsonResult updateBanner(Banner banner) {
        ValidateUtils.checkNotNull("参数不能为空", banner.getId());
        if(banner.getImage() != null){
            banner.setImage(ImageUploadUtils.uploadImage(banner.getImage(), 750, 350, false, ImageUploadUtils.BANNER_IMG));
        }
        bannerService.updateBanner(banner);
        return JsonResultUtils.ok();
    }
    
    @ApiOperation(value = "管理后台-删除Banner", httpMethod="POST")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "bannerId", required = true, dataType = "Long", paramType = "query"),
    })
    @RequestMapping(value ="deleteBannerById", method=RequestMethod.POST)
    public JsonResult deleteBannerById(Long id) {
        bannerService.deleteBannerById(id);
        return JsonResultUtils.ok();
    }
    
    @ApiOperation(value = "管理后台-根据id获取banner", httpMethod="GET")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "bannerId", value = "bannerId", required = true, dataType = "Long", paramType = "query"),
    })
    @RequestMapping(value ="getById", method=RequestMethod.GET)
    public JsonResult getById(Long bannerId) {
        BannerSysVo vo = bannerService.getById(bannerId);
        return JsonResultUtils.ok(vo);
    }
}
