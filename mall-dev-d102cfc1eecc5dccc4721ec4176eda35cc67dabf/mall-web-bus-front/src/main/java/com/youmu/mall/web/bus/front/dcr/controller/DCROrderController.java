/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.web.bus.front.dcr.controller;

import java.math.BigDecimal;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.youmu.common.result.utils.JsonResult;
import com.youmu.common.result.utils.JsonResultUtils;
import com.youmu.common.upload.utils.ImageUploadUtils;
import com.youmu.common.validate.utils.ValidateUtils;
import com.youmu.mall.dcr.domain.DCRCompact;
import com.youmu.mall.dcr.query.DcrOrderBusBankQuery;
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
    
    @RequestMapping(value = "/updateDcrCompactData", method=RequestMethod.POST, consumes="application/x-www-form-urlencoded")
    @ApiOperation(value="更新装修贷款的合同数据", httpMethod="POST")
    @ApiImplicitParams({
        @ApiImplicitParam(name="orderNo", value="订单编号", dataType="string", paramType="form"),
        @ApiImplicitParam(name="totalAmount", value="总金额 万元", dataType="decimal", paramType="form"),
        @ApiImplicitParam(name="idCardImages[]", value="身份证图片,数组逗号隔开", dataType="string[]", paramType="form"),
        @ApiImplicitParam(name="licenseImages[]", value="身份证图片,数组逗号隔开", dataType="string[]", paramType="form"),
        @ApiImplicitParam(name="compactImages[]", value="合同照片,数组逗号隔开", dataType="string[]", paramType="form"),
    })
    public JsonResult updateDcrCompactData(@RequestParam(value = "idCardImages[]", required=true) String[] idCardImages, @RequestParam(value = "licenseImages[]", required=true) String[] licenseImages, @RequestParam(value = "compactImages[]", required=true) String[] compactImages, DCRCompact compact) {
        ValidateUtils.checkNotBlank(compact.getOrderNo(), "订单编号不能为空");
        BigDecimal totalAmount = compact.getTotalAmount();
        ValidateUtils.checkNotNull("订单金额不能为空", totalAmount);
        if(totalAmount.compareTo(new BigDecimal(0)) <= 0 || totalAmount.compareTo(new BigDecimal(2000)) >= 0) {
            throw new ParamException("金额有误,请检查");
        }
        if(idCardImages.length < 1) {
            throw new ParamException("请上传身份证照片");
        }
        if(idCardImages.length > 10) {
            throw new ParamException("身份证照片过多");
        }
        if(licenseImages.length < 1) {
            throw new ParamException("请上传营业执照照片");
        }
        if(licenseImages.length > 10) {
            throw new ParamException("营业执照照片过多");
        }
        if(compactImages.length < 1) {
            throw new ParamException("请上传合同照片");
        }
        if(compactImages.length > 10) {
            throw new ParamException("合同照片过多");
        }
        logger.debug("id cards: {} \n license imgs: {} \n compact Images: {}", idCardImages, licenseImages, compactImages);
        logger.debug("compact imgs idcards {}, licenses {}, compacts {}.", idCardImages.length, licenseImages.length, compactImages.length);
        compact.setIdCardImgs(StringUtils.collectionToDelimitedString(ImageUploadUtils.uploadImageDefault(ImageUploadUtils.DCR_ORDER_ID_CARTD_IMGS, idCardImages), ","));
        compact.setLicenseImgs(StringUtils.collectionToDelimitedString(ImageUploadUtils.uploadImageDefault(ImageUploadUtils.DCR_ORDER_LICENSE_IMGS, licenseImages), ","));
        compact.setCompactImgs(StringUtils.collectionToDelimitedString(ImageUploadUtils.uploadImageDefault(ImageUploadUtils.DCR_ORDER_COMPACT_IMGS, compactImages), ","));
        dcrOrderService.updateDcrCompactData(compact);
        return JsonResultUtils.ok();
    }
    
    @RequestMapping(value = "/listBusBankDcrOrder", method=RequestMethod.POST)
    @ApiOperation(value="银行业务人员查询装修贷款申请订单")
    @ApiImplicitParams({
        @ApiImplicitParam(name="pageNum", value="页码", dataType="int", paramType="query"),
        @ApiImplicitParam(name="pageSize", value="每页多少条数据", dataType="int", paramType="query"),
        @ApiImplicitParam(name="status", value="0 待审核  1 已经审核 2 审核未通过 3 审核已经通过", dataType="int", paramType="query"),
    })
    public JsonResult listBusBankDcrOrder(DcrOrderBusBankQuery query) {
        return JsonResultUtils.ok(dcrOrderService.listBusBankDcrOrder(query));
    }
    
    @RequestMapping(value = "/getCompactDetail", method=RequestMethod.GET)
    @ApiOperation(value="装修订单合同详情")
    @ApiImplicitParams({
        @ApiImplicitParam(name="id", value="订单的id", dataType="long", paramType="query"),
    })
    public JsonResult getCompactDetail(Long id)  {
        ValidateUtils.checkNotNull("订单的id不能为空", id);
        return JsonResultUtils.ok(dcrOrderService.dcrCompactDetial(id));
    }
    
    @RequestMapping(value = "/verifiCompact", method=RequestMethod.POST)
    @ApiOperation(value="装修订单合同银行商户审核")
    @ApiImplicitParams({
        @ApiImplicitParam(name="id", value="订单的id", dataType="long", paramType="query"),
        @ApiImplicitParam(name="isPass", value="是否通过", dataType="boolean", paramType="query"),
        @ApiImplicitParam(name="remark", value="审核备注", dataType="long", paramType="query"),
    })
    public JsonResult verifiCompact(Long id, Boolean isPass, String remark)  {
        ValidateUtils.checkNotNull("订单的id不能为空", id);
        ValidateUtils.checkNotNull("是否通过状态位不能为空", isPass);
        ValidateUtils.checkFalse(!isPass && org.apache.commons.lang3.StringUtils.isBlank(remark), "不通过,备注不能为空");
        dcrOrderService.bankVerifiCompact(id, isPass, remark);
        return JsonResultUtils.ok();
    }
}
