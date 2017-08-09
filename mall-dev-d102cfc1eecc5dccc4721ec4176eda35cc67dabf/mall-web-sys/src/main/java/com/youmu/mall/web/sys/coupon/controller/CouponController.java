/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.web.sys.coupon.controller;

import java.math.BigDecimal;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.youmu.common.date.utils.DateUtils;
import com.youmu.common.result.utils.JsonResult;
import com.youmu.common.result.utils.JsonResultUtils;
import com.youmu.common.validate.utils.ValidateUtils;
import com.youmu.mall.coupon.domain.Coupon;
import com.youmu.mall.coupon.query.CouponStatisticsInfoQuery;
import com.youmu.mall.coupon.query.CouponSysQuery;
import com.youmu.mall.coupon.service.ICouponService;
import com.youmu.mall.exception.ParamException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 优惠券控制器 主要是优惠券的添加  优惠券的修改 优惠券的查询等.
 * @author zh
 * @version $Id: CouponController.java, v 0.1 2017年2月26日 下午3:05:44 zh Exp $
 */
@RestController
@Api(value = "系统优惠券控制器")
@RequestMapping("/coupon")
public class CouponController {
    
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    @Resource
    private ICouponService couponService;
    
    @ApiOperation(value = "添加优惠券", httpMethod="POST")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "name", value = "优惠券名称", required = true, dataType="string",  paramType = "query"),
        @ApiImplicitParam(name = "gmtIssueStart", value = "领取开始时间", required = true, dataType="string", paramType = "query"),
        @ApiImplicitParam(name = "gmtIssueEnd", value = "领取结束时间", required = true, dataType="string", paramType = "query"),
        @ApiImplicitParam(name = "businessType.id", value = "优惠券的id", required = true, dataType="long",  paramType = "query"),
        @ApiImplicitParam(name = "gmtStart", value = "优惠券的有效期开始日期", required = true, dataType="string",   paramType = "query"),
        @ApiImplicitParam(name = "gmtEnd", value = "优惠券的有效期结束日期", required = true, dataType="string",  paramType = "query"),
        @ApiImplicitParam(name = "issueLimit", value = "领取限制同一用户可以领取多少张", required = true, dataType="int",  defaultValue="1", paramType = "query"),
        @ApiImplicitParam(name = "business.id", value = "优惠券的商户id,不传为该商户类型的通用优惠券", required = false,  dataType="int",  paramType = "query"),
        @ApiImplicitParam(name = "total", value = "优惠券的总数", required = true, dataType="int",  paramType = "query"),
        @ApiImplicitParam(name = "useIntro", value = "使用描述", required = false, dataType="string",  paramType = "query"),
        @ApiImplicitParam(name = "amount", value = "优惠金额", required = true, dataType="decimal",  paramType = "query"),
        @ApiImplicitParam(name = "useAmount", value = "满多少金额可用， 备用", required = false, dataType="decimal",  paramType = "query"),
    })
    @RequestMapping(value ="/addCoupon", method=RequestMethod.POST)
    public JsonResult addCoupon(Coupon coupon){
        ValidateUtils.checkNotNull("含有空参数", coupon.getName(), coupon.getGmtIssueStart(), 
            coupon.getGmtIssueEnd(), coupon.getGmtStart(), coupon.getGmtEnd(),
            coupon.getBusinessType().getId(), coupon.getTotal(), 
            coupon.getIssueLimit());
        
        if(coupon.getIssueLimit() <= 0 || coupon.getTotal() <= 0) {
            throw new ParamException("含有异常参数");
        }
        
        if(DateUtils.compareDate(coupon.getGmtIssueEnd(), coupon.getGmtEnd()) > 0) {
            throw new ParamException("领取时间不能大于使用时间");
        }
        
        couponService.addCoupon(coupon);
        return JsonResultUtils.ok();
    }
    
    @ApiOperation(value = "后台优惠券查询", httpMethod="POST")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "pageNum", value = "页码", required = false, dataType="number",  paramType = "query"),
        @ApiImplicitParam(name = "pageSize", value = "每页的长度", required = false, dataType="number",  paramType = "query"),
        @ApiImplicitParam(name = "keywords", value = "优惠券名称", required = false, dataType="string",  paramType = "query"),
        @ApiImplicitParam(name = "businessTypeId", value = "优惠券的类型id", required = false, dataType="number",  paramType = "query"),
        @ApiImplicitParam(name = "issueStatus", value = "1 领取时间未过期  2 领取时间已经过期 ", required = false,  dataType="number",  paramType = "query"),
        @ApiImplicitParam(name = "status", value = "1 未过期  2 已经过期 ", required = false,  dataType="number",  paramType = "query"),
        @ApiImplicitParam(name = "businessId", value = "商户的ID 不传则是通用", required = false, dataType="number",  paramType = "query"),
    })
    @RequestMapping(value ="/listCoupon", method=RequestMethod.POST)
    public JsonResult listCoupon(CouponSysQuery query){      
        return JsonResultUtils.ok(couponService.listSysCoupon(query));
    }
    
    @ApiOperation(value="列出优惠券使用的统计信息", httpMethod="POST")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "pageNum", value = "页码", required = false, dataType="number",  paramType = "query"),
        @ApiImplicitParam(name = "pageSize", value = "每页的长度", required = false, dataType="number",  paramType = "query"),
        @ApiImplicitParam(name = "gmtUsedStart", value = "使用开始时间", required = false, dataType="number",  paramType = "query"),
        @ApiImplicitParam(name = "gmtUsedEnd", value = "使用结束时间", required = false, dataType="number",  paramType = "query"),
    })
    @RequestMapping(value = "/listCouponUsedStatisticsInfo", method=RequestMethod.POST)
    public JsonResult listCouponUsedStatisticsInfo(CouponStatisticsInfoQuery query) {
        return JsonResultUtils.ok(couponService.listCouponUsedStatisticsInfo(query));
    }
    
}
