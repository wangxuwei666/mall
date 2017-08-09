/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.web.shop.dcr.controller;

import javax.annotation.Resource;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.youmu.common.result.utils.JsonResult;
import com.youmu.common.result.utils.JsonResultUtils;
import com.youmu.common.upload.utils.ImageUploadUtils;
import com.youmu.common.validate.utils.ValidateUtils;
import com.youmu.mall.dcr.domain.DCRCompact;
import com.youmu.mall.dcr.query.DcrOrderBusBankQuery;
import com.youmu.mall.dcr.query.DcrOrderQuery;
import com.youmu.mall.dcr.service.IDcrOrderService;
import com.youmu.mall.exception.ParamException;

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
    
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    @RequestMapping(value = "/listDcrOrder", method=RequestMethod.POST)
    @ApiOperation(value="用户查询查询装修贷款申请订单")
    @ApiImplicitParams({
        @ApiImplicitParam(name="pageNum", value="页码", dataType="int", paramType="query"),
        @ApiImplicitParam(name="pageSize", value="每页多少条数据", dataType="int", paramType="query"),
        @ApiImplicitParam(name="completeStatus", value="0 全部  1 进行中  2 已经完成 ", dataType="int", paramType="query"),
    })
    public JsonResult listDcrOrder(DcrOrderQuery query) {
        return JsonResultUtils.ok(dcrOrderService.listUserDcrOrder(query));
    }
    
    @RequestMapping(value = "/verifiDcrOrder", method=RequestMethod.POST)
    @ApiOperation(value="用户审核装修进程")
    @ApiImplicitParams({
        @ApiImplicitParam(name="id", value="装修进程的id", dataType="long", paramType="query"),
        @ApiImplicitParam(name="isPass", value="是否通过", dataType="boolean", paramType="query"),
        @ApiImplicitParam(name="remark", value="意见 ", dataType="string", paramType="query"),
    })
    public JsonResult verifiDcrOrder(Long id,  Boolean isPass, String remark) {
        ValidateUtils.checkNotNull("参数不为空", id, isPass);
        ValidateUtils.checkFalse(!isPass && StringUtils.hasText(remark), "不通过,备注不能为空");
        dcrOrderService.userVerifiDcProgress(id, isPass, remark);
        return JsonResultUtils.ok();
    }
}
