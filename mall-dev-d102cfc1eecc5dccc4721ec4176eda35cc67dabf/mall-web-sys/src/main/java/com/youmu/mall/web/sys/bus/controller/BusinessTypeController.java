/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.web.sys.bus.controller;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.youmu.common.result.utils.JsonResult;
import com.youmu.common.result.utils.JsonResultUtils;
import com.youmu.common.upload.utils.ImageUploadUtils;
import com.youmu.common.validate.utils.ValidateUtils;
import com.youmu.mall.base.query.PageQuery;
import com.youmu.mall.bus.domain.BusinessType;
import com.youmu.mall.bus.service.IBusinessTypeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 商户类型的控制器.
 * 
 * @author zh
 * @version $Id: BusinessTypeController.java, v 0.1 2017年2月26日 下午3:58:31 zh Exp $
 */
@Api("商户类型控制器")
@RestController
@RequestMapping("/busType")
public class BusinessTypeController {
    
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    @Resource
    private IBusinessTypeService businessTypeService;
    
    @ApiOperation(value="列出所有的商户类型", httpMethod="GET")
    @RequestMapping(value = "/listAll", method=RequestMethod.GET)
    public JsonResult listAll() {
        return JsonResultUtils.ok(businessTypeService.listAll(null));
    }
    
    @ApiOperation(value="列出商户类型分页", httpMethod="POST")
    @ApiImplicitParams({
        @ApiImplicitParam(name="keywords", value="关键词", paramType="query", dataType="string", required=false),
        @ApiImplicitParam(name="pageNum", value="页码", paramType="query", dataType="number", required=false),
        @ApiImplicitParam(name="pageSize", value="每页多少条数据", paramType="query", dataType="number", required=false),
    })
    @RequestMapping(value = "/listBusinessType", method=RequestMethod.POST)
    public JsonResult listBusinessType(PageQuery pageQuery) {
        return JsonResultUtils.ok(businessTypeService.listBusinessType(pageQuery));
    }
    
    @ApiOperation(value="添加一个商户类型", httpMethod="POST", consumes="application/x-www-form-urlencoded")
    @ApiImplicitParams({
        @ApiImplicitParam(name="name", value="名称", paramType="form", dataType="java.lang.String", required=true),
        @ApiImplicitParam(name="icon", value="类别图标0", paramType="form", dataType="string", required=true),
        @ApiImplicitParam(name="icon1", value="类别图标1", paramType="form", dataType="string", required=true),
        @ApiImplicitParam(name="icon2", value="类别图标2", paramType="form", dataType="string", required=true),
    })
    @RequestMapping(value = "/addBusinessType", method=RequestMethod.POST)
    public JsonResult addBusinessType(String  icon,String icon1,String icon2, BusinessType type) {
    	ValidateUtils.checkNotBlank(icon, "商户类别图标0不能为空");
        String path = ImageUploadUtils.uploadImage(icon, "png", 155, 174, false, ImageUploadUtils.BUSINESS_TYPE_ICON);
        type.setIcon(path);
        ValidateUtils.checkNotBlank(icon1, "商户类别图标1不能为空");
        String path1 = ImageUploadUtils.uploadImage(icon1, "png", 155, 174, false, ImageUploadUtils.BUSINESS_TYPE_ICON);
        type.setIcon1(path1);
        ValidateUtils.checkNotBlank(icon2, "商户类别图标2不能为空");
        String path2 = ImageUploadUtils.uploadImage(icon2, "png", 155, 174, false, ImageUploadUtils.BUSINESS_TYPE_ICON);
        type.setIcon2(path2);
        ValidateUtils.checkNotNull("名称不为空", type.getName());
        ValidateUtils.checkNotBlank(type.getIcon(), "图标解析0无效");
        ValidateUtils.checkNotBlank(type.getIcon1(), "图标解析1无效");
        ValidateUtils.checkNotBlank(type.getIcon2(), "图标解析2无效");
        businessTypeService.addBusinessType(type);
        return JsonResultUtils.ok();
    }
    
    @ApiOperation(value="修改一个商户类型", httpMethod="POST", consumes="application/x-www-form-urlencoded")
    @ApiImplicitParams({
        @ApiImplicitParam(name="id", value="商户类型主键", paramType="form", dataType="lang", required=true),
        @ApiImplicitParam(name="name", value="名称", paramType="form", dataType="java.lang.String", required=true),
        @ApiImplicitParam(name="icon", value="类别图标0", paramType="form", dataType="string", required=true),
        @ApiImplicitParam(name="icon1", value="类别图标1", paramType="form", dataType="string", required=true),
        @ApiImplicitParam(name="icon2", value="类别图标2", paramType="form", dataType="string", required=true),
    })
    @RequestMapping(value = "/updateBusinessType", method=RequestMethod.POST)
    public JsonResult updateBusinessType(String icon,String icon1,String icon2, BusinessType type) {
        ValidateUtils.checkNotNull("id不为空", type.getId());
        if(StringUtils.isNotBlank(icon)){
        	String path = ImageUploadUtils.uploadImage(icon, "png", 155, 174, false, ImageUploadUtils.BUSINESS_TYPE_ICON);
        	type.setIcon(path);
        	ValidateUtils.checkNotBlank(type.getIcon(), "图标0解析无效");
        }
        if(StringUtils.isNotBlank(icon1)){
        	String path1 = ImageUploadUtils.uploadImage(icon1, "png", 155, 174, false, ImageUploadUtils.BUSINESS_TYPE_ICON);
        	type.setIcon1(path1);
        	ValidateUtils.checkNotBlank(type.getIcon1(), "图标1解析无效");
        }
        if(StringUtils.isNotBlank(icon2)){
        	String path2 = ImageUploadUtils.uploadImage(icon2, "png", 155, 174, false, ImageUploadUtils.BUSINESS_TYPE_ICON);
        	type.setIcon2(path2);
        	ValidateUtils.checkNotBlank(type.getIcon2(), "图标2解析无效");
        }
        businessTypeService.updateBusinessType(type);
        return JsonResultUtils.ok();
    }

    @ApiOperation(value="获取一个商户类型信息", httpMethod="GET")
    @ApiImplicitParams({
        @ApiImplicitParam(name="id", value="商户类型主键", paramType="query", dataType="lang", required=true),
    })
    @RequestMapping(value = "/getBusinessType", method=RequestMethod.GET)
    public JsonResult getBusinessType(Long id) {
        ValidateUtils.checkNotNull("id不为空", id);
        return JsonResultUtils.ok(businessTypeService.getBusinessType(id));
    }
        
    @ApiOperation(value="删除一个商户类型", httpMethod="POST")
    @ApiImplicitParams({
        @ApiImplicitParam(name="id", value="商户id", paramType="form", dataType="java.lang.String", required=true),
    })
    @RequestMapping(value = "/deleteBusinessType", method=RequestMethod.POST)
    public JsonResult deleteBusinessType(Long id) {
        ValidateUtils.checkNotNull("id不能为空", id);
        businessTypeService.deleteBusinessType(id);
        return JsonResultUtils.ok();
    }

}
