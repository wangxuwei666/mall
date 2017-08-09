package com.youmu.mall.web.shop.dcr.controller;

import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.youmu.common.result.utils.JsonResult;
import com.youmu.common.result.utils.JsonResultUtils;
import com.youmu.mall.dcr.domain.DCRRegister;
import com.youmu.mall.dcr.service.DcrRegisterService;


@RestController
@RequestMapping(value="/register/")

public class DCRRegisterController {
	
	@Resource
	private DcrRegisterService rgService;
	
	//添加
	@RequestMapping(value="djService" , method=RequestMethod.POST)
	
	public JsonResult insertUserD(DCRRegister userd){
		rgService.insertUserD(userd);
		return JsonResultUtils.ok();
		
		
	}
	
}