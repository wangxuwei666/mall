/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.web.sys.salelog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.youmu.common.page.utils.Page;
import com.youmu.common.result.utils.JsonResult;
import com.youmu.common.result.utils.JsonResultUtils;
import com.youmu.mall.exception.ParamException;
import com.youmu.mall.product.query.ProductQuery;
import com.youmu.mall.product.vo.ProductSysVo;
import com.youmu.mall.salelog.query.SalelogQuery;
import com.youmu.mall.salelog.service.ISalelogService;
import com.youmu.mall.salelog.vo.SalelogSysVo;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * @author yujiahao
 * @version $Id: SalelogController.java, v 0.1 2017年3月15日 下午6:04:31 yujiahao Exp $
 */
@RestController
@RequestMapping("/salelog/")
public class SalelogController {
    @Autowired
    private ISalelogService salelogService;
    
    @ApiOperation(value = "商品累计销售")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "beginDate", value = "订单支付日期-开始时间", required = false, dataType = "String", paramType = "query"),
        @ApiImplicitParam(name = "endDate", value = "订单支付日期-结束时间", required = false, dataType = "String", paramType = "query"),
        @ApiImplicitParam(name = "pageNum", value = "当前页", required = true, dataType = "Integer", paramType = "query"),
        @ApiImplicitParam(name = "pageSize", value = "页码尺寸", required = true, dataType = "Integer", paramType = "query")
    })
    @RequestMapping(value ="getSalelogSysList",method=RequestMethod.POST)
    public JsonResult getSalelogSysList(SalelogQuery query){
        Page<SalelogSysVo> page = salelogService.getSalelogSysList(query);
        return JsonResultUtils.ok(page);
    }
}
