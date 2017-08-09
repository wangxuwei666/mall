/**  
   * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.coupon.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.youmu.common.page.utils.Page;
import com.youmu.mall.coupon.domain.Coupon;
import com.youmu.mall.coupon.domain.CouponVerificationInfo;
import com.youmu.mall.coupon.domain.UserCoupon;
import com.youmu.mall.coupon.domain.UserCouponIssueInfo;
import com.youmu.mall.coupon.query.CouponShopQuery;
import com.youmu.mall.coupon.query.CouponStatisticsInfoQuery;
import com.youmu.mall.coupon.query.CouponSysQuery;
import com.youmu.mall.coupon.query.CouponVerifiHistoryPageQuery;
import com.youmu.mall.coupon.vo.UserCouponShopListVo;
import com.youmu.mall.coupon.vo.CouponShopDetailVo;
import com.youmu.mall.coupon.vo.CouponShopListVo;
import com.youmu.mall.coupon.vo.CouponSysListVo;
import com.youmu.mall.coupon.vo.CouponUsedStatisticsVo;
import com.youmu.mall.coupon.vo.CouponVerifiHistoryListVo;

/**
 *  优惠券数据库数据访问接口。
 * @author zh
 * @version $Id: CouponDao.java, v 0.1 2017年2月26日 下午4:31:05 zh Exp $
 */
public interface CouponDao {

    /**
     * 添加一种优惠券
     * @param coupon
     */
    void insertCoupon(Coupon coupon);

    /**
     * 查询后台系统的优惠券列表.
     * @param query
     * @return
     */
    List<CouponSysListVo> selectSysCouponList(CouponSysQuery query);

    /**
     * 查询优惠券数量
     * @param query
     * @return
     */
    Long selectSysCouponCount(CouponSysQuery query);
    
    /**
     * 查询商城的优惠券列表.
     * @param query 查询对象.
     * @return 优惠券shop列表.
     */
    List<UserCouponShopListVo> selectShopUserCouponList(CouponShopQuery query);
    
    /**
     * 查询商户的优惠券详情.
     * @param query 查询对象.
     * @return 优惠券详情
     */
    CouponShopDetailVo selectShopCouponDetail(Long id);

    /**
     * 查询用户领取的信息.
     * @param userId 用户id
     * @param couponId 优惠券id
     * @return 用户优惠券统计信息.
     */
    UserCouponIssueInfo selectUserCouponIssueInfo(@Param("userId") Long userId, @Param("couponId") Long couponId);

    /**
     * 领取优惠券.
     * @param userId 用户id
     * @param couponId 优惠券id
     * @param couponNo 
     * @param isDCR 
     */
    void issueCoupon(UserCoupon coupon);

    /**
     * 修改优惠券已经领取的总数
     * @param id 优惠券的id
     */
    void updateCouponIssueCount(Long id);

    /**
     * 查询商城优惠券列表信息
     * @param query
     * @return
     */
    List<CouponShopListVo> selectShopCouponList(CouponShopQuery query);

    /**
     * 查询优惠券核销信息
     * @param couponNo
     * @return
     */
    CouponVerificationInfo selectCouponVerificationInfo(String couponNo);

    /**
     * 修改优惠券信息.
     * @param businessId 
     * @param couponNo
     */
    int updateCouponUseInfo(@Param("businessId") Long businessId, @Param("userId") Long userId,  @Param("couponNo") String couponNo);

    /**
     * 查询核销历史优惠券
     * @param query
     * @return
     */
    List<CouponVerifiHistoryListVo> selectVerifiHistoryCouponList(CouponVerifiHistoryPageQuery query);

    /**
     * 查询优惠券统计使用信息数量.
     * @param query
     * @return
     */
    Long selectCouponUsedStatisticsInfoCount(CouponStatisticsInfoQuery query);

    /**
     * 查询优惠券使用信息列表
     * @param query
     * @return
     */
    List<CouponUsedStatisticsVo> selectCouponUsedStatisticsInfoList(CouponStatisticsInfoQuery query);

}
