/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.web.bus.front.coupon.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.youmu.common.result.utils.JsonResult;
import com.youmu.common.result.utils.JsonResultUtils;
import com.youmu.common.validate.utils.ValidateUtils;
import com.youmu.mall.coupon.query.CouponVerifiHistoryPageQuery;
import com.youmu.mall.coupon.service.ICouponService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 二维码核销 二维码核销查询 核销历史查询.
 * @author zh
 * @version $Id: CouponController.java, v 0.1 2017年3月3日 上午10:02:41 zh Exp $
 */
@RestController
@RequestMapping("/coupon")
public class CouponController {
    
    @Resource
    private ICouponService couponService;
    
    @RequestMapping(value = "/verificationCoupon", method=RequestMethod.GET)
    @ApiOperation(value="核销二维码接口", httpMethod="GET")
    @ApiImplicitParams({
        @ApiImplicitParam(name="couponNo", value="从二维码中读取的优惠券的编号", dataType="string", paramType="query")
    })
    public JsonResult verificationCoupon(String couponNo) {
        ValidateUtils.checkNotBlank(couponNo, "二维码数据为空");
        return JsonResultUtils.ok(couponService.verificationCoupon(couponNo));
    }
    
    @RequestMapping(value = "/listVerifiHistory", method=RequestMethod.POST)
    @ApiOperation(value="核销二维码的历史", httpMethod="POST")
    @ApiImplicitParams({
        @ApiImplicitParam(name="pageNum", value="页码", dataType="string", paramType="query"),
        @ApiImplicitParam(name="pageSize", value="每页多少条", dataType="string", paramType="query"),
        @ApiImplicitParam(name="status", value="只有idDcr 为true时有效 0 全部 1 未提交数据  2 已经完成", dataType="int", paramType="query"),
        @ApiImplicitParam(name="isDcr", value="是否是装修贷款的优惠券", dataType="boolean", paramType="query"),
    })
    public JsonResult listVerifiHistory(CouponVerifiHistoryPageQuery query) {
        return JsonResultUtils.ok(couponService.listVerifiHistory(query));
    }
}
