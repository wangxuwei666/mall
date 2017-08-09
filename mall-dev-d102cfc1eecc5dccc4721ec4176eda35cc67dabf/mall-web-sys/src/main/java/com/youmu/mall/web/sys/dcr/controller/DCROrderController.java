/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.web.sys.dcr.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.youmu.common.result.utils.JsonResult;
import com.youmu.common.result.utils.JsonResultUtils;
import com.youmu.common.validate.utils.ValidateUtils;
import com.youmu.mall.dcr.domain.DCPhase;
import com.youmu.mall.dcr.domain.DCROrder;
import com.youmu.mall.dcr.query.DcrOrderDcProgressQuery;
import com.youmu.mall.dcr.query.DcrOrderQuery;
import com.youmu.mall.dcr.service.IDcrOrderService;
import com.youmu.mall.dcr.vo.DCProcessVo;
import com.youmu.mall.exception.BusinessException;
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
    
    @RequestMapping(value = "/listDcrOrder", method=RequestMethod.POST)
    @ApiOperation(value="查询列表展示装修贷款订单")
    @ApiImplicitParams({
        @ApiImplicitParam(name="pageNum", value="页码", dataType="int", paramType="query"),
        @ApiImplicitParam(name="pageSize", value="每页多少条数据", dataType="int", paramType="query"),
        @ApiImplicitParam(name="gmtCreateStart", value="申请的开始日期", dataType="date", paramType="query"),
        @ApiImplicitParam(name="gmtCreateEnd", value="申请的结束日期", dataType="date", paramType="query"),
        @ApiImplicitParam(name="gmtSubCompactStart", value="提交合同数据开始时间", dataType="date", paramType="query"),
        @ApiImplicitParam(name="gmtSubCompactEnd", value="提交合同数据结束时间", dataType="date", paramType="query"),
        @ApiImplicitParam(name="gmtCheckedStart", value="审核日期开始", dataType="date", paramType="query"),
        @ApiImplicitParam(name="gmtCheckedEnd", value="审核日期结束时间", dataType="date", paramType="query"),
        @ApiImplicitParam(name="mobile", value="申请用户的手机号码", dataType="string", paramType="query"),
        @ApiImplicitParam(name="sysContactStatus", value="0 所有申请 已经量房 和 未量房 1 未联系  2 已经联系", dataType="int", paramType="query"),
        @ApiImplicitParam(name="sysCompactCheckStatus", value="0 进入审核流程的订单  1 未审核 2 已经审核 3 审核通过 4审核不通过", dataType="int", paramType="query"),
    })
    public JsonResult listDCROrder(DcrOrderQuery query) {
        return JsonResultUtils.ok(dcrOrderService.listDcrOrder(query));
    }
    
    @RequestMapping(value = "/handleDCRRequest", method=RequestMethod.POST)
    @ApiOperation(value="处理装修申请")
    @ApiImplicitParams({
        @ApiImplicitParam(name="id", value="订单的id", dataType="long", paramType="query"),
    })
    public JsonResult handleDCRRequest(DCROrder order) {
        dcrOrderService.handleDCRRequest(order);
        return JsonResultUtils.ok();
    }
    
    @RequestMapping(value = "/dcrCompactDetial", method=RequestMethod.POST)
    @ApiOperation(value="装修订单的合同详情查看")
    @ApiImplicitParams({
        @ApiImplicitParam(name="id", value="订单的id", dataType="long", paramType="query"),
    })
    public JsonResult dcrCompactDetial(Long id) {
        ValidateUtils.checkNotNull("订单的id不能为空", id);
        return JsonResultUtils.ok(dcrOrderService.dcrCompactDetial(id));
    }
    
    @RequestMapping(value = "/verifiCompact", method=RequestMethod.POST)
    @ApiOperation(value="装修订单合同后台人员审核")
    @ApiImplicitParams({
        @ApiImplicitParam(name="id", value="订单的id", dataType="long", paramType="query"),
        @ApiImplicitParam(name="isPass", value="是否通过", dataType="boolean", paramType="query"),
        @ApiImplicitParam(name="remark", value="审核备注", dataType="long", paramType="query"),
    })
    public JsonResult verifiCompact(Long id, Boolean isPass, String remark)  {
        ValidateUtils.checkNotNull("订单的id不能为空", id);
        ValidateUtils.checkNotNull("是否通过状态位不能为空", isPass);
        ValidateUtils.checkFalse(!isPass && StringUtils.isBlank(remark), "不通过,备注不能为空");
        dcrOrderService.verifiCompact(id, isPass, remark);
        return JsonResultUtils.ok();
    }
    
    @RequestMapping(value = "/sysVerifiDcProgress", method=RequestMethod.POST)
    @ApiOperation(value="装修进度后台人员审核")
    @ApiImplicitParams({
        @ApiImplicitParam(name="id", value="进度的id", dataType="long", paramType="query"),
        @ApiImplicitParam(name="isPass", value="是否通过", dataType="boolean", paramType="query"),
        @ApiImplicitParam(name="remark", value="审核备注", dataType="long", paramType="query"),
    })
    public JsonResult sysVerifiDcProgress(Long id, Boolean isPass, String remark)  {
        ValidateUtils.checkNotNull("订单的id不能为空", id);
        ValidateUtils.checkNotNull("是否通过状态位不能为空", isPass);
        ValidateUtils.checkFalse(!isPass && StringUtils.isBlank(remark), "不通过,备注不能为空");
        dcrOrderService.sysVerifiDcProgress(id, isPass, remark);
        return JsonResultUtils.ok();
    }
    
    @RequestMapping(value = "/listDcOrderOperated", method=RequestMethod.POST)
    @ApiOperation(value="列出已经操作过的装修流程   已经审核/已经打款")
    @ApiImplicitParams({
        @ApiImplicitParam(name="pageNum", value="页码", dataType="int", paramType="query"),
        @ApiImplicitParam(name="pageSize", value="每页多少条数据", dataType="int", paramType="query"),
        @ApiImplicitParam(name="mobile", value="申请用户的手机号码", dataType="string", paramType="query"),
        @ApiImplicitParam(name="orderNo", value="订单编号", dataType="string", paramType="query"),
        @ApiImplicitParam(name="operator", value="1 审核  2 银行打款", dataType="int", paramType="query"),
        @ApiImplicitParam(name="verifiStatus", value="系统审核状态 0 全部 默认  1 审核通过 2 审核不通过", dataType="int", paramType="query"),
        @ApiImplicitParam(name="gmtSubmitStart", value="进度提交开始时间", dataType="string", paramType="query"),
        @ApiImplicitParam(name="gmtSubmitEnd", value="进度提交结束时间", dataType="string", paramType="query"),
    })
    public JsonResult listDcOrderOperated(DcrOrderDcProgressQuery query) {
        return JsonResultUtils.ok(dcrOrderService.listDcOrderOperated(query));
    }
    
    @RequestMapping(value = "/listDcOrderInOperator", method=RequestMethod.POST)
    @ApiOperation(value="查询需要操作的装修 需要审核/打款")
    @ApiImplicitParams({
        @ApiImplicitParam(name="pageNum", value="页码", dataType="int", paramType="query"),
        @ApiImplicitParam(name="pageSize", value="每页多少条数据", dataType="int", paramType="query"),
        @ApiImplicitParam(name="mobile", value="申请用户的手机号码", dataType="string", paramType="query"),
        @ApiImplicitParam(name="orderNo", value="订单编号", dataType="string", paramType="query"),
        @ApiImplicitParam(name="operator", value="1 审核  2 银行打款", dataType="int", paramType="query"),
        @ApiImplicitParam(name="gmtSubmitStart", value="进度提交开始时间", dataType="string", paramType="query"),
        @ApiImplicitParam(name="gmtSubmitEnd", value="进度提交结束时间", dataType="string", paramType="query"),
    })
    public JsonResult listDcOrderInOperator(DcrOrderDcProgressQuery query) {
        return JsonResultUtils.ok(dcrOrderService.listDcOrderInOperator(query));
    }
    
    @RequestMapping(value = "/downLoadDcOrder", method=RequestMethod.GET)
    @ApiOperation(value="下载装修打款信息")
    @ApiImplicitParams({
        @ApiImplicitParam(name="pageNum", value="页码", dataType="int", paramType="query"),
        @ApiImplicitParam(name="pageSize", value="每页多少条数据", dataType="int", paramType="query"),
        @ApiImplicitParam(name="mobile", value="申请用户的手机号码", dataType="string", paramType="query"),
        @ApiImplicitParam(name="orderNo", value="订单编号", dataType="string", paramType="query"),
        @ApiImplicitParam(name="gmtSubmitStart", value="进度提交开始时间", dataType="string", paramType="query"),
        @ApiImplicitParam(name="gmtSubmitEnd", value="进度提交结束时间", dataType="string", paramType="query"),
    })
    public void downLoadDcOrder(DcrOrderDcProgressQuery query, HttpServletResponse response) {
        query.setOperator(2);
        query.setPageNum(1);
        query.setPageSize(Integer.MAX_VALUE -1);
        dcrOrderService.downLoadDcOrder(query, response);
    }
    
    @RequestMapping(value = "/updateDcProgressRemitInfo", method=RequestMethod.POST)
    @ApiOperation(value="更新装修流程中的打款信息")
    @ApiImplicitParams({
        @ApiImplicitParam(name="id", value="进度的id progressId", dataType="long", paramType="query"),
    })
    public JsonResult updateDcProgressRemitInfo(Long id) {
        ValidateUtils.checkNotNull("订单的id不能为空", id);
        dcrOrderService.updateDcProgressRemitInfo(id);
        return JsonResultUtils.ok();
    }
    
    @RequestMapping(value = "/listDcProcessInfo", method=RequestMethod.GET)
    @ApiOperation(value="列出装修流程里面的信息")
    public JsonResult listDcProcessInfo() {
        return JsonResultUtils.ok(dcrOrderService.listDcProcessInfo());
    }
    
    @RequestMapping(value = "/updateDcProcessInfo", method=RequestMethod.POST)
    @ApiOperation(value="更新装修流程中的打款信息  使用json参数[{'id':1,'proportion:0.03....}]", notes="[{\"id\":1, \"proportion\":0.2},{\"id\":2, \"proportion\":0.2},{\"id\":3, \"proportion\":0.2},{\"id\":4, \"proportion\":0.2},{\"id\":5, \"proportion\":0.3}]")
    public JsonResult updateDcProcessInfo(@RequestBody List<DCPhase> process) {
        if(process== null || process.isEmpty()) {
            throw new ParamException("参数不能为空");
        }
        BigDecimal sumProportion = new BigDecimal(0);
        for (DCPhase dcPhase : process) {
            sumProportion = sumProportion.add(dcPhase.getProportion());
        }
        if(sumProportion.compareTo(new BigDecimal(1)) != 0){
            throw new BusinessException("装修流程金额总比例超过1");
        }
        dcrOrderService.updateDcProcessInfo(process);
        return JsonResultUtils.ok();
    }
    
    @RequestMapping(value = "/getDcDcrOrderInfo", method=RequestMethod.GET)
    @ApiOperation(value="审核装修流程和银行打款时获取订单的信息")
    @ApiImplicitParam(name="id", value="progressId", dataType="string", paramType="query")
    public JsonResult getDcDcrOrderInfo(Long id) {
        ValidateUtils.checkNotNull("id不能为空", id);
        return JsonResultUtils.ok(dcrOrderService.getDcDcrOrderInfo(id));
    }
}
