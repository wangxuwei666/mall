/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.common.alipay.utils;

/**
 * 保存支付宝包回调的信息.
 * @author zh
 * @version $Id: WapPayResultHandler.java, v 0.1 2017年3月1日 下午4:07:49 zh Exp $
 */
public interface AliPayResultHandler {
    
    /**
     * 处理支付结果.
     * <ul>
     * <li>
     *  1、商户需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
     * </li>
     * <li>
     * 2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
     *   通过sql查询订单是否存在
     * </li>
     * <li>
     * 3、校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email），
     *   校验sellerId是否正确
     * </li>
     * <li>
     * 4、验证app_id是否为该商户本身。
     *   校验app_id是否正确
     *   <br />
     * 上述1、2、3、4有任何一个验证不通过，则表明本次通知是异常通知，务必忽略。在上述验证通过后商户必须根据支付宝不同类型的业务通知，正确的进行不同的业务处理，并且过滤重复的通知结果数据。在支付宝的业务通知中，只有交易通知状态为TRADE_SUCCESS或TRADE_FINISHED时，支付宝才会认定为买家付款成功。
     * </li>
     * </ul>
     * @param result
     */
    String handlePayResult(AlipayResult result);
    
}
