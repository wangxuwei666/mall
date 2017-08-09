package com.youmu.mall.web.sys.dcr.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.youmu.common.page.utils.Page;
import com.youmu.common.result.utils.JsonResult;
import com.youmu.common.result.utils.JsonResultUtils;
import com.youmu.mall.dcr.domain.DCRRegister;
import com.youmu.mall.dcr.query.DcrRegisterQuery;
import com.youmu.mall.dcr.service.DcrRegisterService;
import com.youmu.mall.exception.ParamException;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
/**
 * 家装登记的控制器
 * @author wxw
 */
@RestController
@RequestMapping("/Register")
public class DCRRegisterController {
	@Resource
	private DcrRegisterService registerService;
	
	@RequestMapping(value="/dcrRegister",method=RequestMethod.POST)
	@ApiOperation(value = "展示登记信息")
	@ApiImplicitParams({ 
		@ApiImplicitParam(name="name", value="姓名", dataType="String", paramType="query"),
		@ApiImplicitParam(name="mobile", value="电话", dataType="String", paramType="query"),
		@ApiImplicitParam(name="address", value="地址", dataType="String", paramType="query"),
		@ApiImplicitParam(name="category", value="装修类型", dataType="String", paramType="query"),
		@ApiImplicitParam(name="dcr_package", value="备注", dataType="String", paramType="query"),
		@ApiImplicitParam(name="gmt_create", value="登记时间", dataType="datetime", paramType="query"),
	})
	public JsonResult getDCRRegister(DcrRegisterQuery dcrRegisterQuery){
		
		if(dcrRegisterQuery == null){
            throw new ParamException("查询对象为空！");
        }
        Page<DCRRegister> page = registerService.getDCRRegister(dcrRegisterQuery);
        return JsonResultUtils.ok(page);
		
	}
}


