package com.youmu.mall.web.sys.category.controller;

import com.youmu.common.result.utils.JsonResult;
import com.youmu.common.result.utils.JsonResultUtils;
import com.youmu.common.validate.utils.ValidateUtils;
import com.youmu.mall.base.query.PageQuery;
import com.youmu.mall.category.service.ICategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/7/1.
 */
@Api(value = "商品分类控制器",description = "商品分类")
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Resource
    private ICategoryService iCategoryService;

    @ApiOperation(value="根据一个分类id列出分类", httpMethod="POST")
    @ApiImplicitParams({
            @ApiImplicitParam(value="分类id", name="categoryId", paramType="query", dataType="int", required=true),
    })
    @RequestMapping(value = "/getCategoryById", method= RequestMethod.POST)
    public JsonResult listSysUser(Integer categoryId) {
        ValidateUtils.checkNotNull("分类id不能为空",categoryId);
        return JsonResultUtils.ok(iCategoryService.getCategoryVo(categoryId));
    }

    @ApiOperation(value="添加一个分类", httpMethod="POST")
    @RequestMapping(value = "/addCategory", method=RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(value="分类名字", name="categoryName", paramType="query", dataType="string", required=true),
    })
    public JsonResult addCategory(String categoryName) {
        ValidateUtils.checkNotBlank(categoryName, "分类名字不能为空");
    		Integer integer = iCategoryService.addCategory(categoryName);
    		return JsonResultUtils.ok(integer);
    }

    @ApiOperation(value="添加一个分类详情", httpMethod="POST")
    @RequestMapping(value = "/addSpecification", method=RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(value="详情名字", name="spfiName", paramType="query", dataType="string", required=true),
            @ApiImplicitParam(value="详情分类id", name="categoryId", paramType="query", dataType="int", required=true),
    })
    public JsonResult addSysUser(Integer categoryId,String spfiName) {
        ValidateUtils.checkNotBlank(spfiName, "分类详情名字不能为空");
        ValidateUtils.checkNotNull("分类详情id不能为空", categoryId);
    	Integer integer = iCategoryService.addSpecification(categoryId,spfiName);
    	return JsonResultUtils.ok(integer);
      
    }

    @ApiOperation(value="删除一个详情", httpMethod="POST")
    @RequestMapping(value = "/delSpecification", method=RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(value="详情id", name="specificationId", paramType="query", dataType="int", required=true),
    })
    public JsonResult specificationId(Integer specificationId) {
        ValidateUtils.checkNotNull("分类详情id不能为空", specificationId);
        Integer integer = iCategoryService.delSpecification(specificationId);
        return JsonResultUtils.ok(integer);
    }


}
