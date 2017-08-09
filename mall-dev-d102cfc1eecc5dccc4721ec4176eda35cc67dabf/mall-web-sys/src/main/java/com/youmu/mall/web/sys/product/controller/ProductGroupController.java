/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.web.sys.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.youmu.common.date.utils.DateUtils;
import com.youmu.common.global.constant.StatusConstant;
import com.youmu.common.page.utils.Page;
import com.youmu.common.result.utils.JsonResult;
import com.youmu.common.result.utils.JsonResultUtils;
import com.youmu.common.upload.utils.ImageUploadUtils;
import com.youmu.common.validate.utils.ValidateUtils;
import com.youmu.mall.base.query.PageQuery;
import com.youmu.mall.exception.ParamException;
import com.youmu.mall.product.domain.ProductGroup;
import com.youmu.mall.product.query.ProductGroupQuery;
import com.youmu.mall.product.service.IProductGroupService;
import com.youmu.mall.product.vo.ProductGroupDetailSysVo;
import com.youmu.mall.product.vo.ProductGroupEditVo;
import com.youmu.mall.product.vo.ProductGroupSysVo;
import com.youmu.mall.product.vo.ProductSysVo;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * @author yujiahao
 * @version $Id: ProductGroupController.java, v 0.1 2017年3月7日 上午11:17:23 yujiahao Exp $
 */
@RestController
@RequestMapping("/productgroup/")
public class ProductGroupController {
    
    @Autowired
    private IProductGroupService productGroupService;
    
    @ApiOperation(value = "管理后台-促销管理（商品组列表）")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "keywords", value = "活动商品组名称", required = false, paramType = "query"),
        @ApiImplicitParam(name = "status", value = "商品组活动状态 (0未开始   1秒杀中  2已结束)", required = false, paramType = "query"),
        @ApiImplicitParam(name = "groupType", value = "活动类型：1-秒杀，2-特价，3-推荐，4-积分", required = true, paramType = "query"),
        @ApiImplicitParam(name = "pageNum", value = "当前页", required = true, dataType = "Integer", paramType = "query"),
        @ApiImplicitParam(name = "pageSize", value = "页码尺寸", required = true, dataType = "Integer", paramType = "query")
    })
    @RequestMapping(value ="getProductGroupSysVo",method=RequestMethod.POST)
    public JsonResult getProductGroupSysVo(ProductGroupQuery query){
        Page<ProductGroupSysVo> page = productGroupService.getProductGroupSysVo(query);
        return JsonResultUtils.ok(page);
    }
    
    
    @ApiOperation(value = "管理后台-添加商品组列表")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "name", value = "活动组名称", required = true, paramType = "form"),
        @ApiImplicitParam(name = "groupImg", value = "活动组Banner图片（仅限特价列表）", required = false, paramType = "form"),
        @ApiImplicitParam(name = "gmtStart", value = "活动开始时间（仅限秒杀列表）", required = false, paramType = "form"),
        @ApiImplicitParam(name = "gmtEnd", value = "活动结束时间（仅限秒杀列表）", required = false, paramType = "form"),
        @ApiImplicitParam(name = "totalQuantity", value = "活动商品总数量", required = true, paramType = "form"),
        @ApiImplicitParam(name = "limitQuantity", value = "用户限购数量", required = true, paramType = "form"),
        @ApiImplicitParam(name = "groupType", value = "活动组类别（1秒杀，2特价）", required = true, paramType = "form"),
        @ApiImplicitParam(name = "items[0].productId", value = "活动组商品项id", required = true, paramType = "form"),
        @ApiImplicitParam(name = "items[0].price", value = "活动价格", required = true, paramType = "form"),
        @ApiImplicitParam(name = "items[0].quantity", value = "活动组商品项数量", required = true, paramType = "form"),
        
        @ApiImplicitParam(name = "items[1].productId", value = "活动组商品项id", required = true, paramType = "form"),
        @ApiImplicitParam(name = "items[1].price", value = "活动价格", required = true, paramType = "form"),
        @ApiImplicitParam(name = "items[1].quantity", value = "活动组商品项数量", required = true, paramType = "form"),
    })
    @RequestMapping(value ="createProductGroupSysVo",method=RequestMethod.POST, consumes="application/x-www-form-urlencoded")
    public JsonResult createProductGroup(ProductGroup productGroup){
        productGroupService.createProductGroup(productGroup);
        return JsonResultUtils.ok();
    }
    
    
    @ApiOperation(value = "管理后台-编辑商品组列表")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "活动组id", required = true, paramType = "form"),
        @ApiImplicitParam(name = "name", value = "活动组名称", required = true, paramType = "form"),
        @ApiImplicitParam(name = "groupImg", value = "活动组Banner图片（仅限特价列表）", required = false, paramType = "form"),
        @ApiImplicitParam(name = "gmtStart", value = "活动开始时间（仅限秒杀列表）", required = false, paramType = "form"),
        @ApiImplicitParam(name = "gmtEnd", value = "活动结束时间（仅限秒杀列表）", required = false, paramType = "form"),
        @ApiImplicitParam(name = "totalQuantity", value = "活动商品总数量（前端计算）", required = true, paramType = "form"),
        @ApiImplicitParam(name = "limitQuantity", value = "用户限购数量", required = true, paramType = "form"),
        @ApiImplicitParam(name = "groupType", value = "活动组类别（1秒杀，2特价）", required = true, paramType = "form"),
        @ApiImplicitParam(name = "items[0].productId", value = "活动组商品项id", required = true, paramType = "form"),
        @ApiImplicitParam(name = "items[0].price", value = "活动价格", required = true, paramType = "form"),
        @ApiImplicitParam(name = "items[0].quantity", value = "活动组商品项数量", required = true, paramType = "form"),
        
        @ApiImplicitParam(name = "items[1].productId", value = "活动组商品项id", required = true, paramType = "form"),
        @ApiImplicitParam(name = "items[1].price", value = "活动价格", required = true, paramType = "form"),
        @ApiImplicitParam(name = "items[1].quantity", value = "活动组商品项数量", required = true, paramType = "form"),
    })
    @RequestMapping(value ="updateProductGroup",method=RequestMethod.POST, consumes="application/x-www-form-urlencoded")
    public JsonResult updateProductGroup(ProductGroup productGroup){
        productGroupService.updateProductGroup(productGroup);
        return JsonResultUtils.ok();
    }
    
    @ApiOperation(value = "管理后台-促销管理（商品组明细）")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "groupId", value = "活动商品组id", required = true, paramType = "query"),
        @ApiImplicitParam(name = "status", value = "秒杀活动状态", required = true, paramType = "query"),
    })
    @RequestMapping(value ="getProductGroupDetailSysVo",method=RequestMethod.POST)
    public JsonResult getProductGroupDetailSysVoByGroupId(Long groupId,Integer status){
        ValidateUtils.checkNotNull("商品组id不能为空", groupId);
        ProductGroupEditVo vo = productGroupService.getProductGroupDetailSysVoByGroupId(groupId,status);
        return JsonResultUtils.ok(vo);
    }
    
    @ApiOperation(value = "管理后台-商品组明细(查询可参加活动的商品项)")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "keywords", value = "活动商品组名称", required = false, paramType = "query"),
        @ApiImplicitParam(name = "pageNum", value = "当前页", required = true, dataType = "Integer", paramType = "query"),
        @ApiImplicitParam(name = "pageSize", value = "页码尺寸", required = true, dataType = "Integer", paramType = "query")
    })
    @RequestMapping(value ="getNotReduceProduct",method=RequestMethod.POST)
    public JsonResult getNotReduceProduct(PageQuery query){
        Page<ProductSysVo> list = productGroupService.getNotReduceProduct(query);
        return JsonResultUtils.ok(list);
    }
    
    @ApiOperation(value = "删除当前商品活动组")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "活动商品组id", required = true, paramType = "query"),
    })
    @RequestMapping(value ="deleteProductGroup",method=RequestMethod.POST)
    public JsonResult deleteProductGroup(Long id){
        productGroupService.deleteProductGroup(id);
        return JsonResultUtils.ok();
    }
}
