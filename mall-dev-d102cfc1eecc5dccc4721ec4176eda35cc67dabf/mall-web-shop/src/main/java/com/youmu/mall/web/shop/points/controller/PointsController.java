package com.youmu.mall.web.shop.points.controller;

import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.youmu.common.result.utils.JsonResult;
import com.youmu.common.result.utils.JsonResultUtils;
import com.youmu.mall.points.service.PointsService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

/**
 * 积分shop控制层
 * @author Mr.s
 *
 */
@RestController
@RequestMapping("/Points/")
public class PointsController {

	@Resource
	private PointsService pointsService;
	
	
	@RequestMapping(value="getPointsById",method= RequestMethod.POST)
	public JsonResult getPointsById(){
		return JsonResultUtils.ok(pointsService.getPointsById());
	}
	
}
