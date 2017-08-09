package com.youmu.mall.web.shop.points.controller;

import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.youmu.common.result.utils.JsonResult;
import com.youmu.common.result.utils.JsonResultUtils;
import com.youmu.common.validate.utils.ValidateUtils;
import com.youmu.mall.points.service.PointsRecordService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

/**
 * 积分记录控制层
 * @author Mr.s
 *
 */
@RestController
@RequestMapping("/PointsRecord/")
public class PointsRecordController {
	
	@Resource
	private PointsRecordService pointsRecordService;
	
	@ApiImplicitParams({
        @ApiImplicitParam(name = "sn", value = "订单sn", required = true, dataType = "String", paramType = "query"),
    })
	@RequestMapping(value="updateRecordProgress",method= RequestMethod.POST)
	public JsonResult getPointsById(String sn){
		ValidateUtils.checkNotNull("订单sn不能为空", sn);	
		pointsRecordService.updateRecordProgress(sn);
		return JsonResultUtils.ok();
	}

	@ApiImplicitParams({
        @ApiImplicitParam(name = "sn", value = "订单sn", required = true, dataType = "String", paramType = "query"),
    })
	@RequestMapping(value="getProgressBySn",method= RequestMethod.POST)
	public JsonResult getProgressBySn(String sn){
		ValidateUtils.checkNotNull("订单sn不能为空", sn);
		return JsonResultUtils.ok(pointsRecordService.getProgressBySn(sn));	
	}
}
