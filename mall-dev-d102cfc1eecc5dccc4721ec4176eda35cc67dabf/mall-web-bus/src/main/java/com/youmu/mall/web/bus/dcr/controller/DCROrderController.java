/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.web.bus.dcr.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.youmu.common.result.utils.JsonResult;
import com.youmu.common.result.utils.JsonResultUtils;
import com.youmu.mall.dcr.domain.DCPhase;
import com.youmu.mall.dcr.query.DcrOrderQuery;
import com.youmu.mall.dcr.service.IDcrOrderService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 装修贷款管理控制器.
 * @author zh
 * @version $Id: DCROrderController.java, v 0.1 2017年3月3日 下午6:07:39 zh Exp $
 */
@Api("装修贷款订单管理控制器")
@RestController
@RequestMapping("/dcrOrder")
public class DCROrderController {
    
    @Resource
    private IDcrOrderService dcrOrderService;
    
    @ApiOperation("列出商户进行中的装修贷款订单")
    @RequestMapping(value = "/listBusInProgerssDcrOrder", method=RequestMethod.POST)
    @ApiImplicitParams({
        @ApiImplicitParam(name="keywords", value="关键词", dataType="int", paramType="query"),
        @ApiImplicitParam(name="pageNum", value="页码", dataType="int", paramType="query"),
        @ApiImplicitParam(name="pageSize", value="每页多少条数据", dataType="int", paramType="query"),
        @ApiImplicitParam(name="mobile", value="手机号码", dataType="string", paramType="query"),
        @ApiImplicitParam(name="orderNo", value="订单号", dataType="string", paramType="query"),
    })
    private JsonResult listBusInProgressDcrOrder(DcrOrderQuery query) {
        return JsonResultUtils.ok(dcrOrderService.listBusInProgressDcrOrder(query));
    }
    
    @ApiOperation("列出一个商户进行中的装修贷款订单")
    @RequestMapping(value = "/getDcrDcOrderPhases", method=RequestMethod.GET)
    @ApiImplicitParams({
        @ApiImplicitParam(name="id", value="订单id", dataType="int", paramType="query"),
    })
    private JsonResult getDcrDcOrderPhases(Long id) {
        return JsonResultUtils.ok(dcrOrderService.getDcrDcOrderPhases(id));
    }
    
    @ApiOperation("列出商户已经完成的装修贷款订单")
    @RequestMapping(value = "/listBusCompleteDcrOrder", method=RequestMethod.POST)
    @ApiImplicitParams({
        @ApiImplicitParam(name="keywords", value="关键词", dataType="int", paramType="query"),
        @ApiImplicitParam(name="pageNum", value="页码", dataType="int", paramType="query"),
        @ApiImplicitParam(name="pageSize", value="每页多少条数据", dataType="int", paramType="query"),
        @ApiImplicitParam(name="mobile", value="手机号码", dataType="string", paramType="query"),
        @ApiImplicitParam(name="orderNo", value="订单号", dataType="string", paramType="query"),
    })
    private JsonResult listBusCompleteDcrOrder(DcrOrderQuery query) {
        return JsonResultUtils.ok(dcrOrderService.listBusCompleteDcrOrder(query));
    }
    
    @ApiOperation("商户提交装修进度")
    @RequestMapping(value = "/busSubmitDcProgress", method=RequestMethod.GET)
    @ApiImplicitParams({
        @ApiImplicitParam(name="id", value="装修进度的id", dataType="string", paramType="query"),
    })
    private JsonResult busSubmitDcProgress(DCPhase phase) {
        dcrOrderService.busSubmitDcProgress(phase);
        return JsonResultUtils.ok();
    }
}
