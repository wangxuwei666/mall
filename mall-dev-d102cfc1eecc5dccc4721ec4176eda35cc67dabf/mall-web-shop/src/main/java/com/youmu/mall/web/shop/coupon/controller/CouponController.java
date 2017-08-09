/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.web.shop.coupon.controller;

import javax.annotation.Resource;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.youmu.common.context.UserContext;
import com.youmu.common.result.utils.JsonResult;
import com.youmu.common.result.utils.JsonResultUtils;
import com.youmu.common.validate.utils.ValidateUtils;
import com.youmu.mall.coupon.query.CouponShopQuery;
import com.youmu.mall.coupon.service.ICouponService;
import com.youmu.mall.dcr.domain.DCROrder;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 优惠券控制器 主要管理用户的优惠券 用户领券 我的优惠券查询
 * @author zh
 * @version $Id: CouponController.java, v 0.1 2017年2月26日 下午3:05:44 zh Exp $
 */
@Api("用户优惠券控制器")
@RestController
@RequestMapping("/coupon")
public class CouponController {
    
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    @Resource
    private ICouponService couponService;
    
    @ApiOperation(value = "商城优惠券列表", httpMethod="GET")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "pageNum", value = "页码", required = false, dataType="number",  paramType = "query"),
        @ApiImplicitParam(name = "pageSize", value = "每页条数", required = false, dataType="number",  paramType = "query"),
        @ApiImplicitParam(name = "onlyDcr", value = "只查询装修优惠券", required = false, dataType="boolean",  paramType = "query"),
    })
    @RequestMapping(value ="/listShopCoupon", method=RequestMethod.GET)
    public JsonResult listShopCoupon(CouponShopQuery query) {
        return JsonResultUtils.ok(couponService.listShopCoupon(query));
    }
    
    @ApiOperation(value = "商城我的优惠券查询", httpMethod="GET")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "pageNum", value = "页码", required = false, dataType="number",  paramType = "query"),
        @ApiImplicitParam(name = "pageSize", value = "每页多少条", required = false, dataType="number",  paramType = "query"),
        @ApiImplicitParam(name = "status", value = "0 未使用 1 已使用  2 已经过期", required = false, dataType="String",  paramType = "query"),
    })
    @RequestMapping(value ="/listUserCoupon", method=RequestMethod.GET)
    public JsonResult listUserCoupon(CouponShopQuery query) {
        return JsonResultUtils.ok(couponService.listUserCoupon(query));
    }
    
    @ApiOperation(value = "商城我的优惠券详情", httpMethod="GET")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "优惠券的id", required = false, dataType="java.lang.Long",  paramType = "query"),
    })
    @RequestMapping(value ="/getCouponDetail", method=RequestMethod.GET)
    public JsonResult getCouponDetail(Long id) {
        return JsonResultUtils.ok(couponService.getCouponDetail(id));
    }
    
    @ApiOperation(value = "商城领取普通优惠券", httpMethod="GET")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "优惠券的id", required = false, dataType="java.lang.Long",  paramType = "query"),
    })
    @RequestMapping(value ="/issueCoupon", method=RequestMethod.GET)
    public JsonResult issueCoupon(Long id) {
        return JsonResultUtils.ok(couponService.issueCoupon(id));
    }
    
    @ApiOperation(value = "商城领取装修优惠券, 当优惠券的isDecorate=true时可以使用该接口来领取装修优惠券", httpMethod="POST")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "coupon.id", value = "优惠券的id", required = false, dataType="long",  paramType = "query"),
        @ApiImplicitParam(name = "realName", value = "装修用户的真实姓名", required = false, dataType="string",  paramType = "query"),
        @ApiImplicitParam(name = "mobile", value = "装修用户的手机号码", required = false, dataType="string",  paramType = "query"),
        @ApiImplicitParam(name = "dcDistrict", value = "装修的区域编码", required = false, dataType="string",  paramType = "query"),
        @ApiImplicitParam(name = "dcArea", value = "区域名称 省+市+区", required = false, dataType="string",  paramType = "query"),
        @ApiImplicitParam(name = "dcAddress", value = "装修的详细地址", required = false, dataType="string",  paramType = "query"),
    })
    @RequestMapping(value ="/issueDCRCoupon", method=RequestMethod.POST)
    public JsonResult issueDCRCoupon(DCROrder order) {
        ValidateUtils.checkMobile(order.getMobile(), "手机号码输入有误");
        ValidateUtils.checkNotBlank(order.getRealName(), "姓名不能为空");
        ValidateUtils.checkNotBlank(order.getDcAddress(), "装修地址不能为空");
        ValidateUtils.checkNotNull("区域编码不能为空", order.getDcDistrict());
        ValidateUtils.checkNotBlank(order.getDcAddress(), "装修地址不能为空");
        ValidateUtils.checkNotBlank(order.getDcArea(), "装修区域不能为空");
        ValidateUtils.checkNotBlank(order.getDcAddress(), "装修地址不能为空");
        return JsonResultUtils.ok(couponService.issueDCRCoupon(order));
    }
}
