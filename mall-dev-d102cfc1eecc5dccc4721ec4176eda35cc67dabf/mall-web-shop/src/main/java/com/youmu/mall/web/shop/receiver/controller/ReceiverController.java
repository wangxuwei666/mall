package com.youmu.mall.web.shop.receiver.controller;


import java.util.List;

import javax.annotation.Resource;
import javax.security.auth.login.LoginException;

import org.apache.poi.ss.formula.functions.Address;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.youmu.common.result.utils.JsonResult;
import com.youmu.common.result.utils.JsonResultUtils;
import com.youmu.mall.exception.ParamException;
import com.youmu.mall.receiver.domain.Receiver;
import com.youmu.mall.receiver.service.IReceiverService;
import com.youmu.mall.receiver.vo.AddressVo;
import com.youmu.mall.receiver.vo.ReceiverVO;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping(value = "/receiver/remote/")
public class ReceiverController {

    
	@Resource
    private IReceiverService receiverService;
    
	@ApiOperation(value = "获取收货地址")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "isDefault", value = "是否默认(0不默认，1默认，为空则查询全部)", required = false, dataType = "int", paramType = "query"),
		@ApiImplicitParam(name = "receiveId", value = "用户地址id", required = false, dataType = "int", paramType = "query")
	})
   	@RequestMapping(value = "findReceiver",method=RequestMethod.GET)
   	public JsonResult findReceiver(Integer isDefault,Long receiveId) {
       	List<ReceiverVO>  receiverVOs = receiverService.findReceiver(isDefault,receiveId);
       	return JsonResultUtils.ok(receiverVOs);
   	}
	
	@ApiOperation(value = "保存收件人")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "consignee", value = "收件人",required = true, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "phone", value = "电话",required = true, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "provinceCode", value = "省",required = true, dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "cityCode", value = "市",required = true, dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "districtCode", value = "区",required = true, dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "detailAddress", value = "详细地址",required = true, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "isDefault", value = "是否默认地址(0不默认，1默认)", required = true,dataType = "int", paramType = "query")
	})
   	@RequestMapping(value = "saveReceiver",method=RequestMethod.POST)
   	public JsonResult saveReceiver(Receiver receiver) {
       	long receiverId = receiverService.saveReceiver(receiver);
       	return JsonResultUtils.ok(receiverId);
   	}
	
	@ApiOperation(value = "修改收件人")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "id", required = true, dataType = "long", paramType = "query"),
			@ApiImplicitParam(name = "consignee", value = "收件人", dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "phone", value = "电话", dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "provinceCode", value = "省", dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "cityCode", value = "市", dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "districtCode", value = "区", dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "detailAddress", value = "详细地址", dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "isDefault", value = "是否默认地址(0不默认，1默认)",required = true, dataType = "int", paramType = "query")
	})
   	@RequestMapping(value = "updateReceiver",method=RequestMethod.POST)
   	public JsonResult updateReceiver(Receiver receiver) {
		if(receiver == null || receiver.getId()== null){
			throw new ParamException("id不能为空");
		}
       	receiverService.updateReceiver(receiver);
       	return JsonResultUtils.ok();
   	}
	
	@ApiOperation(value = "删除收件人")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "id", required = true, dataType = "long", paramType = "query"),
	})
   	@RequestMapping(value = "deleteReceiver",method=RequestMethod.POST)
   	public JsonResult deleteReceiver(Long id) {
		if(id== null){
			throw new ParamException("id不能为空");
		}
       	receiverService.deleteReceiver(id);
       	return JsonResultUtils.ok();
   	}
	
	@ApiOperation(value = "获取所有省code和省名称")
   	@RequestMapping(value = "getProvince",method=RequestMethod.POST)
   	public JsonResult getProvince() {
       	List<AddressVo> addressVos = receiverService.getProvince();
       	return JsonResultUtils.ok(addressVos);
   	}
	
	@ApiOperation(value = "根据省code获取市和市的code")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "provinceCode", value = "省Code", required = true, dataType = "Integer", paramType = "query"),
	})
   	@RequestMapping(value = "getCity",method=RequestMethod.POST)
   	public JsonResult getCity(Integer provinceCode) {
		if(provinceCode== null){
			throw new ParamException("省Code不能为空");
		}
       	List<AddressVo> addressVos = receiverService.getCity(provinceCode);
       	return JsonResultUtils.ok(addressVos);
   	}
	
	@ApiOperation(value = "根据市code获取区和区的code")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "cityCode", value = "市Code", required = true, dataType = "Integer", paramType = "query"),
	})
   	@RequestMapping(value = "getDistrict", method=RequestMethod.POST)
   	public JsonResult getDistrict(Integer cityCode) {
		if(cityCode== null){
			throw new ParamException("省Code不能为空");
		}
       	List<AddressVo> addressVos = receiverService.getDistrict(cityCode);
       	return JsonResultUtils.ok(addressVos);
   	}
	
}