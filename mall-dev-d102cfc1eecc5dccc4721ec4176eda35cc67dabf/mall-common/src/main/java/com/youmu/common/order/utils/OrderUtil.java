/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.common.order.utils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.youmu.common.token.utils.AuthCodeUtils;

/**
 * 
 * @author yujiahao
 * @version $Id: OrderUtil.java, v 0.1 2017年2月28日 上午11:10:14 yujiahao Exp $
 */
public class OrderUtil {
    private static Logger log                     = LoggerFactory.getLogger(OrderUtil.class);

    private static int    ORDER_NUMBER_MAX_LENGTH = 19;

    public static String createOrderNumber(Long userId,Long businessId) {
        StringBuffer buffer = new StringBuffer();
        buffer.append(userId).append(getDate()).append(businessId);
        return buffer.substring(0, buffer.length() > 32 ? 32 : buffer.length()).toString();
    }

    /** 创建装修贷款 */
    public static String createDCROrderNumber(Long userId) {
        StringBuffer buffer = new StringBuffer();
        buffer.append(getTimeStamp()).append(userId);
        return buffer.substring(0,
            buffer.length() > ORDER_NUMBER_MAX_LENGTH ? ORDER_NUMBER_MAX_LENGTH : buffer.length());
    }

    private static String getTimeStamp() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        Date date = new Date();
        return simpleDateFormat.format(date);
    }

    private static String getDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date();
        return simpleDateFormat.format(date);
    }

    /**
     * 
     * @param uid
     * @param coupoId 
     * @return
     */
    public static String createCouponNumber(Long uid, Long coupoId) {
        StringBuffer buffer = new StringBuffer();
        buffer.append(getTimeStamp()).append(uid).append(coupoId);
        return buffer.substring(0,
            buffer.length() > ORDER_NUMBER_MAX_LENGTH ? ORDER_NUMBER_MAX_LENGTH : buffer.length());
    }

    public static String createProductSn(Long uid) {
        StringBuffer buffer = new StringBuffer();
        buffer.append(uid).append(getDate());
        return buffer.substring(0, buffer.length() > 32 ? 32 : buffer.length());
    }
}
