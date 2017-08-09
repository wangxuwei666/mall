/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.web.shop.balance.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.youmu.common.alipay.utils.AliPayResultHandler;
import com.youmu.common.alipay.utils.AliPayUtils;
import com.youmu.common.alipay.utils.AlipayResult;
import com.youmu.common.context.UserContext;
import com.youmu.common.result.utils.JsonResult;
import com.youmu.common.result.utils.JsonResultUtils;
import com.youmu.common.validate.utils.ValidateUtils;
import com.youmu.common.wx.utils.WxPayResultHandler;
import com.youmu.common.wx.utils.WxPayUtil;
import com.youmu.mall.balance.domain.BalanceChargeBill;
import com.youmu.mall.balance.domain.PayChannel;
import com.youmu.mall.balance.service.IBalanceChargeBillService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 余额充值控制器
 * @author zh
 * @version $Id: BalanceChargeBillController.java, v 0.1 2017年5月16日 上午10:42:59 zh Exp $
 */
@RequestMapping("/balance/charge")
public class BalanceChargeBillController implements WxPayResultHandler, AliPayResultHandler {
    
    @Resource
    private IBalanceChargeBillService balanceChargeBillService;
    
    @ApiOperation("生成充值订单")
    @ApiImplicitParams({
        @ApiImplicitParam(name="amount", value="充值金额", dataType="query"),
        @ApiImplicitParam(name="remark", value="充值备注", dataType="query"),
    })
    @PostMapping("/bills")
    public JsonResult create(BigDecimal amount, String remark) {
        // 处理参数
        ValidateUtils.checkNotNull("充值金额不能为空", amount);
        // 处理业务
        BalanceChargeBill bill = balanceChargeBillService.create(UserContext.getLongUserId(), amount, remark);
        // 返回结果
        return JsonResultUtils.ok(bill);
    }
    
    @ApiOperation("查询我的订单")
    @GetMapping("/bills")
    public JsonResult findMyBalanceChargeBills() {
        // 处理业务
        List<BalanceChargeBill> bills = balanceChargeBillService.findByUserId(UserContext.getLongUserId());
        // 返回结果
        return JsonResultUtils.ok(bills);
    }
    
    @ApiOperation("支付充值订单")
    @ApiImplicitParams({
        @ApiImplicitParam(name="tradeNo", value="充值订单编号", dataType="query"),
        @ApiImplicitParam(name="payChannel", value="支付渠道 WX_PAY, ALI_PAY", dataType="query"),
    })
    @PostMapping("/pay")
    public JsonResult pay(String tradeNo, String payChannel) {
        // 处理参数
        ValidateUtils.checkNotBlank(tradeNo, "订单号不能为空");
        ValidateUtils.checkNotBlank(payChannel, "支付方式不能为空");
        // 处理业务
        balanceChargeBillService.pay(UserContext.getLongUserId(), tradeNo, payChannel);
        // 返回结果
        return JsonResultUtils.ok();
    }
    
    @PostMapping("/notifyWxPay")
    public void notifyWxPay(HttpServletRequest request, HttpServletResponse response) {
        WxPayUtil.handlePayNotify(request, response, this);
    }
    
    @PostMapping("/notifyAliPay")
    public void notifyAliPay(HttpServletRequest request, HttpServletResponse response) {
        AliPayUtils.handlePayNotify(request, response, this);
    }

    /** 
     * @see com.youmu.common.wx.utils.WxPayResultHandler#handlePayResult(java.util.Map)
     */
    @Override
    public boolean handlePayResult(String tradeNo, String outTradeNo) {
        return balanceChargeBillService.handlePayResult(UserContext.getLongUserId(), tradeNo, outTradeNo, PayChannel.WX_PAY);
    }

    /** 
     * @see com.youmu.common.alipay.utils.AliPayResultHandler#handlePayResult(com.youmu.common.alipay.utils.AlipayResult)
     */
    @Override
    public String handlePayResult(AlipayResult result) {
        boolean success = balanceChargeBillService.handlePayResult(UserContext.getLongUserId(), result.getOutTradeNo(), result.getTradeNo(), PayChannel.ALI_PAY);
        return success ? AliPayUtils.SUCCESS : AliPayUtils.FAILURE;
    }

}
