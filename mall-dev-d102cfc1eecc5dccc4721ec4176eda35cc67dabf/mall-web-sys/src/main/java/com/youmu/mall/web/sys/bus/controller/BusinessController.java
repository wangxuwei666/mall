/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.web.sys.bus.controller;

import java.math.BigDecimal;
import java.math.MathContext;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alipay.api.domain.BankPayApplyResult;
import com.youmu.common.page.utils.Page;
import com.youmu.common.result.utils.JsonResult;
import com.youmu.common.result.utils.JsonResultUtils;
import com.youmu.common.validate.utils.ValidateUtils;
import com.youmu.mall.bus.domain.Business;
import com.youmu.mall.bus.query.BusinessQuery;
import com.youmu.mall.bus.service.IBusinessService;
import com.youmu.mall.product.query.ProductQuery;
import com.youmu.mall.product.vo.ProductSysVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 商户账号后台管理控制器.
 * 
 * @author zh
 * @version $Id: BusinessController.java, v 0.1 2017年2月27日 下午2:24:53 zh Exp $
 */
@RestController
@Api("商户业务")
@RequestMapping("/business")
public class BusinessController {
    
    @Resource
    private IBusinessService businessService;
    
    @ApiOperation(value="添加商户", httpMethod="POST")
    @ApiImplicitParams({
        @ApiImplicitParam(name="name", value="商户名", paramType="query", dataType="string"),
//        @ApiImplicitParam(name="type.id", value="商户类型的id", paramType="query", dataType="string"),
        @ApiImplicitParam(name="businessTypeIds", value="商户类型的id数组", paramType="query", dataType="string"),
        @ApiImplicitParam(name="gmtStart", value="协议开始日期 yyyy-MM-dd", paramType="query", dataType="string"),
        @ApiImplicitParam(name="gmtEnd", value="协议结束日期 yyyy-MM-dd", paramType="query", dataType="string"),
        @ApiImplicitParam(name="address", value="商户地址", paramType="query", dataType="string"),
        @ApiImplicitParam(name="bankType", value="1 对公 2 对私", paramType="query", dataType="string"),
        @ApiImplicitParam(name="bankAccount", value="银行账户", paramType="query", dataType="string"),
        @ApiImplicitParam(name="bankUsername", value="银行开户名", paramType="query", dataType="string"),
        @ApiImplicitParam(name="bankName", value="银行的名称", paramType="query", dataType="string"),
        @ApiImplicitParam(name="remark", value="商户备注", paramType="query", dataType="string"),
    })
    @RequestMapping(value="/addBusiness")
    public JsonResult addBusiness(Business business,Integer[] businessTypeIds) {
        ValidateUtils.checkNotBlank(business.getName(), "名称不能为空");
        ValidateUtils.checkNotBlank(business.getAddress(), "商户地址不能为空");
        //ValidateUtils.checkNotNull("商户类型不为空", business.getType(), business.getType().getId());
        ValidateUtils.checkNotNull("商户类型不为空", businessTypeIds,businessTypeIds.length>0?new Integer(1):null);
        ValidateUtils.checkNotNull("协议时间不能为空", business.getGmtStart(), business.getGmtEnd());
        ValidateUtils.checkNotNull("银行信息不能为空", business.getBankType());
        ValidateUtils.checkNotBlank(business.getBankName(), "银行名称不能为空");
        ValidateUtils.checkNotBlank(business.getBankUsername(), "开户名不能为空");
        ValidateUtils.checkBankAccount(business.getBankAccount(), "银行卡号有误");
        businessService.addBusiness(business,businessTypeIds);
        return JsonResultUtils.ok();
    }
    
    @ApiOperation(value="修改商户", httpMethod="POST")
    @ApiImplicitParams({
        @ApiImplicitParam(name="id", value="商户id", paramType="query", dataType="string"),
        @ApiImplicitParam(name="name", value="商户名", paramType="query", dataType="string"),
       // @ApiImplicitParam(name="type.id", value="商户类型的id", paramType="query", dataType="string"),
        @ApiImplicitParam(name="gmtStart", value="协议开始日期 yyyy-MM-dd", paramType="query", dataType="string"),
        @ApiImplicitParam(name="gmtEnd", value="协议结束日期 yyyy-MM-dd", paramType="query", dataType="string"),
        @ApiImplicitParam(name="address", value="商户地址", paramType="query", dataType="string"),
        @ApiImplicitParam(name="bankType", value="1 对公 2 对私", paramType="query", dataType="string"),
        @ApiImplicitParam(name="bankAccount", value="银行账户", paramType="query", dataType="string"),
        @ApiImplicitParam(name="bankUsername", value="银行开户名", paramType="query", dataType="string"),
        @ApiImplicitParam(name="bankName", value="银行的名称", paramType="query", dataType="string"),
        @ApiImplicitParam(name="remark", value="商户备注", paramType="query", dataType="string"),
    })
    @RequestMapping(value="/updateBusiness")
    public JsonResult updateBusiness(Business business) {
        ValidateUtils.checkNotNull("id不能为空", business.getId());
        if(StringUtils.isNotBlank(business.getBankAccount())) {
            ValidateUtils.checkBankAccount(business.getBankAccount(), "银行卡号信息有误");
        }
        businessService.updateBusiness(business);
        return JsonResultUtils.ok();
    }
    
    @ApiOperation(value="获取商户", httpMethod="GET")
    @ApiImplicitParams({
        @ApiImplicitParam(name="id", value="商户类型id", paramType="query", dataType="long"),
    })
    @RequestMapping(value="/getBusinessById", method=RequestMethod.GET)
    public JsonResult getBusinessById(Long id) {
        ValidateUtils.checkNotNull("id不能为空", id);
        return JsonResultUtils.ok(businessService.getBusinessById(id));
    }
    
    @ApiOperation(value="商户列表", httpMethod="POST")
    @ApiImplicitParams({
        @ApiImplicitParam(name="pageNum", value="页码", paramType="query", dataType="number"),
        @ApiImplicitParam(name="pageSize", value="数据条数", paramType="query", dataType="number"),
        @ApiImplicitParam(name="keywords", value="商户名称等", paramType="query", dataType="string"),
        @ApiImplicitParam(name="typeId", value="商户类型的id", paramType="query", dataType="number"),
        @ApiImplicitParam(name="expired", value="是否过期", paramType="query", dataType="boolean"),
        @ApiImplicitParam(name="gmtEnd", value="协议结束日期 yyyy-MM-dd", paramType="query", dataType="string"),
    })
    @RequestMapping(value="/listBusiness")
    public JsonResult listBusiness(BusinessQuery query) {
        return JsonResultUtils.ok(businessService.listBusiness(query));
    }
    
    @ApiOperation(value="查看商户的信息", httpMethod="GET")
    @ApiImplicitParams({
        @ApiImplicitParam(name="id", value="商户id", paramType="query", dataType="long"),
    })
    @RequestMapping(value="/getBusinessDetail", method=RequestMethod.GET)
    public JsonResult getBusinessDetail(Long id) {
        ValidateUtils.checkNotNull("id不能为空", id);
        return JsonResultUtils.ok(businessService.getBusinessDetail(id));
    }
    
    @ApiOperation(value="修改商户的类型信息", httpMethod="POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name="bussnessId", value="商户id", paramType="query", dataType="int"),
            @ApiImplicitParam(name="bussnessTpyeIdOld", value="旧的商户类型id", paramType="query", dataType="int"),
            @ApiImplicitParam(name="bussnessTpyeIdNew", value="新的商户类型id", paramType="query", dataType="int")
    })
    @RequestMapping(value="/updatetBusinessType", method=RequestMethod.POST)
    public JsonResult updateBusinessDetail(Long bussnessId,Integer bussnessTpyeIdNew,Integer bussnessTpyeIdOld) {
        ValidateUtils.checkNotNull("bussnessId不能为空", bussnessId);
        ValidateUtils.checkNotNull("bussnessTpyeIdOld不能为空", bussnessTpyeIdOld);
        ValidateUtils.checkNotNull("bussnessTpyeIdNew不能为空", bussnessTpyeIdOld);
        return JsonResultUtils.ok(businessService.updatetBusinessType(bussnessId,bussnessTpyeIdOld,bussnessTpyeIdNew));
    }

  @ApiOperation(value="删除商户的类型信息", httpMethod="POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name="bussnessId", value="商户id", paramType="query", dataType="int"),
            @ApiImplicitParam(name="bussnessTpyeId", value="商户类型id", paramType="query", dataType="int")
    })
    @RequestMapping(value="/delBusinessType", method=RequestMethod.POST)
    public JsonResult delBusinessDetail(Long bussnessId,Integer bussnessTpyeId) {
        ValidateUtils.checkNotNull("bussnessId不能为空", bussnessId);
        ValidateUtils.checkNotNull("bussnessTpyeId不能为空", bussnessTpyeId);
        return JsonResultUtils.ok(businessService.delBusinessType(bussnessId,bussnessTpyeId));
    }
    
   

    @ApiOperation(value="添加一个商户的类型信息", httpMethod="POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name="bussnessId", value="商户id", paramType="query", dataType="int"),
            @ApiImplicitParam(name="bussnessTpyeId", value="商户类型id", paramType="query", dataType="int")
    })
    @RequestMapping(value="/addBusinessType", method=RequestMethod.POST)
    public JsonResult addBusinessDetail(Integer bussnessId,Integer bussnessTpyeId) {
        ValidateUtils.checkNotNull("bussnessId不能为空", bussnessId);
        ValidateUtils.checkNotNull("bussnessTpyeId不能为空", bussnessTpyeId);
        return JsonResultUtils.ok(businessService.addBusinessType(bussnessId,bussnessTpyeId));
    }
    
    @ApiOperation(value = "对应商户的商品列表", httpMethod="POST")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "keywords", value = "关键词", required = false, dataType = "Long", paramType = "query"),
        @ApiImplicitParam(name = "businessId", value = "商户id", required = true, dataType = "Long", paramType = "query"),
        @ApiImplicitParam(name = "status", value = "状态", required = false, dataType = "Integer", paramType = "query"),
        @ApiImplicitParam(name = "stock", value = "库存量(默认值为5，表示小于等于5的库存对应的商品)", required = false, dataType = "Long", paramType = "query"),
        @ApiImplicitParam(name = "pageNum", value = "当前页", required = true, dataType = "Integer", paramType = "query"),
        @ApiImplicitParam(name = "pageSize", value = "页码尺寸", required = true, dataType = "Integer", paramType = "query")
    })
    @RequestMapping(value="/getBusinessProducts",method=RequestMethod.POST)
    public JsonResult getBussinessProducts(ProductQuery query){
        Page<ProductSysVo> page =businessService.listBusinessProducts(query);
    	return JsonResultUtils.ok(page);
    }
}
