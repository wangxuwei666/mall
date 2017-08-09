/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.web.shop.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.youmu.common.page.utils.Page;
import com.youmu.common.result.utils.JsonResult;
import com.youmu.common.result.utils.JsonResultUtils;
import com.youmu.common.validate.utils.ValidateUtils;
import com.youmu.mall.product.domain.ProductGroup;
import com.youmu.mall.product.query.ProductGroupQuery;
import com.youmu.mall.product.service.IProductGroupService;
import com.youmu.mall.product.vo.GroupDateVo;
import com.youmu.mall.product.vo.ProductGroupDetailShopVo;
import com.youmu.mall.product.vo.ProductGroupShopVo;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


/**
 * 用户端-活动商品组（特价，秒杀）
 * @author yujiahao
 * @version $Id: ProductGroupController.java, v 0.1 2017年3月21日 下午4:38:32 yujiahao Exp $
 */
@RestController
@RequestMapping("/productgroup/")
public class ProductGroupController {
    
    @Autowired
    private IProductGroupService productGroupService;
    
    @ApiOperation(value = "用户端-秒杀&特价（商品组列表）")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "status", value = "商品组活动状态 (0未开始   1秒杀中  2已结束)", required = false, paramType = "query"),
        @ApiImplicitParam(name = "groupType", value = "活动类型：1-秒杀，2-特价，3-推荐，4-积分", required = true, paramType = "query"),
        @ApiImplicitParam(name = "pageNum", value = "当前页", required = false, dataType = "Integer", paramType = "query"),
        @ApiImplicitParam(name = "pageSize", value = "页码尺寸", required = false, dataType = "Integer", paramType = "query")
    })
    @RequestMapping(value ="getProductGroupShopVo",method=RequestMethod.POST)
    public JsonResult getProductGroupShopVo(ProductGroupQuery query){
        Page<ProductGroupShopVo> page = productGroupService.getProductGroupShopVo(query);
        return JsonResultUtils.ok(page);
    }
    
    @ApiOperation(value = "用户端-(秒杀组&积分组)早十点&晚八点&明日预告")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "status", value = "商品组活动状态 (0未开始   1秒杀中  2已结束)", required = false, paramType = "query"),
        @ApiImplicitParam(name = "groupType", value = "活动类型：1-秒杀 4-积分", required = true, paramType = "query"),
        @ApiImplicitParam(name = "pageNum", value = "当前页", required = false, dataType = "Integer", paramType = "query"),
        @ApiImplicitParam(name = "pageSize", value = "页码尺寸", required = false, dataType = "Integer", paramType = "query")
    })
    @RequestMapping(value ="getProductGroupGetShopVo",method=RequestMethod.POST)
    public JsonResult getProductGroupGetShopVo(ProductGroupQuery query){
        Page<ProductGroupShopVo> page = productGroupService.getProductGroupGetShopVo(query);
        return JsonResultUtils.ok(page);
    }
    
    @ApiOperation(value = "用户端-(秒杀组&积分组)早十点&晚八点&明日预告,不传status")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "groupType", value = "活动类型：1-秒杀 4-积分", required = true, paramType = "query"),
        @ApiImplicitParam(name = "pageNum", value = "当前页", required = false, dataType = "Integer", paramType = "query"),
        @ApiImplicitParam(name = "pageSize", value = "页码尺寸", required = false, dataType = "Integer", paramType = "query")
    })
    @RequestMapping(value ="getProductGroupPostShopVo",method=RequestMethod.POST)
    public JsonResult getProductGroupPostShopVo(ProductGroupQuery query){
        Page<ProductGroupShopVo> page = productGroupService.getProductGroupPostShopVo(query);
        return JsonResultUtils.ok(page);
    }
    
    //Get和Post的差异是Get传了status，获得的数据是区分未开始，秒杀中，已结束
    @ApiOperation(value = "用户端- 秒杀&特价  根据活动组id查询商品组明细")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "groupId", value = "活动商品组id", required = true, paramType = "query"),
    })
    @RequestMapping(value ="getProductGroupDetailShopVoById",method=RequestMethod.POST)
    public JsonResult getProductGroupDetailShopVoById(Long groupId){
        ValidateUtils.checkNotNull("商品组id不能为空", groupId);
        ProductGroupShopVo vo = productGroupService.getProductGroupDetailShopVoById(groupId);
        return JsonResultUtils.ok(vo);
    }
    
    @ApiOperation(value = "秒杀页面banner(查询当天参加活动的时间段)")
    @RequestMapping(value ="getGroupBannerDate",method=RequestMethod.GET)
    public JsonResult getGroupBannerDate(){
        List<GroupDateVo> vo = productGroupService.getGroupBannerDate();
        return JsonResultUtils.ok(vo);
    }
    
}
