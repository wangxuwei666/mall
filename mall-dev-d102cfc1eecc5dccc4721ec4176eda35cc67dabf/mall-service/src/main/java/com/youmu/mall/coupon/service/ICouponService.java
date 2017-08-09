/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.coupon.service;

import java.util.List;

import com.youmu.common.page.utils.Page;
import com.youmu.mall.coupon.domain.Coupon;
import com.youmu.mall.coupon.query.CouponShopQuery;
import com.youmu.mall.coupon.query.CouponStatisticsInfoQuery;
import com.youmu.mall.coupon.query.CouponSysQuery;
import com.youmu.mall.coupon.query.CouponVerifiHistoryPageQuery;
import com.youmu.mall.coupon.vo.CouponShopDetailVo;
import com.youmu.mall.coupon.vo.CouponShopListVo;
import com.youmu.mall.coupon.vo.CouponSysListVo;
import com.youmu.mall.coupon.vo.CouponUsedStatisticsVo;
import com.youmu.mall.coupon.vo.CouponVerifiHistoryListVo;
import com.youmu.mall.coupon.vo.UserCouponShopListVo;
import com.youmu.mall.dcr.domain.DCROrder;

/**
 * 优惠券接口服务.
 * 
 * @author zh
 * @version $Id: ICouponService.java, v 0.1 2017年2月26日 下午4:27:46 zh Exp $
 */
public interface ICouponService {

    /**
     * 添加一种优惠券
     * @param coupon
     */
    void addCoupon(Coupon coupon);

    /**
     * 查询后台系统优惠券列表
     * @param query
     * @return
     */
    Page<CouponSysListVo> listSysCoupon(CouponSysQuery query);

    /**
     * 查询商城的优惠券列表.
     * @param query 优惠券列表查询对象.
     * @return
     */
    List<UserCouponShopListVo> listUserCoupon(CouponShopQuery query);

    /**
     * 优惠券商品详情对象.
     * @param id 优惠券详情用户关联表id
     */
    CouponShopDetailVo getCouponDetail(Long id);

    /**
     * 领取优惠券.
     * @param id 优惠券的id.
     * @return 
     */
    Long issueCoupon(Long id);

    /**
     * 领取装修的优惠券
     * @param order
     * @return 
     */
    Long issueDCRCoupon(DCROrder order);

    /**
     * 
     * @param query
     * @return
     */
    List<CouponShopListVo> listShopCoupon(CouponShopQuery query);

    /**
     * 和销优惠券二维码
     * @param couponNo
     * @return 
     */
    int verificationCoupon(String couponNo);

    /**
     * 查询
     * @param query
     * @return
     */
    List<CouponVerifiHistoryListVo> listVerifiHistory(CouponVerifiHistoryPageQuery query);

    /**
     * 优惠券使用统计信息.
     * @param query
     * @return
     */
    Page<CouponUsedStatisticsVo> listCouponUsedStatisticsInfo(CouponStatisticsInfoQuery query);

}
