/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.coupon.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.youmu.common.context.GlobalConstant;
import com.youmu.common.context.UserContext;
import com.youmu.common.order.utils.OrderUtil;
import com.youmu.common.page.utils.Page;
import com.youmu.mall.coupon.dao.CouponDao;
import com.youmu.mall.coupon.domain.Coupon;
import com.youmu.mall.coupon.domain.CouponVerificationInfo;
import com.youmu.mall.coupon.domain.UserCoupon;
import com.youmu.mall.coupon.domain.UserCouponIssueInfo;
import com.youmu.mall.coupon.query.CouponShopQuery;
import com.youmu.mall.coupon.query.CouponStatisticsInfoQuery;
import com.youmu.mall.coupon.query.CouponSysQuery;
import com.youmu.mall.coupon.query.CouponVerifiHistoryPageQuery;
import com.youmu.mall.coupon.service.ICouponService;
import com.youmu.mall.coupon.vo.CouponShopDetailVo;
import com.youmu.mall.coupon.vo.CouponShopListVo;
import com.youmu.mall.coupon.vo.CouponSysListVo;
import com.youmu.mall.coupon.vo.CouponUsedStatisticsVo;
import com.youmu.mall.coupon.vo.CouponVerifiHistoryListVo;
import com.youmu.mall.coupon.vo.UserCouponShopListVo;
import com.youmu.mall.dcr.dao.DcrOrderDao;
import com.youmu.mall.dcr.domain.DCROrder;
import com.youmu.mall.exception.BusinessException;
import com.youmu.mall.exception.ParamException;
import com.youmu.mall.exception.SystemInnerException;
import com.youmu.mall.redis.login.dao.RedisLoginDao;
import com.youmu.mall.user.domain.BusinessUser;
import com.youmu.mall.user.domain.User;

/**
 * 优惠券服务实现类.
 * @author zh
 * @version $Id: CouponServiceImpl.java, v 0.1 2017年2月26日 下午4:29:14 zh Exp $
 */
@Service
public class CouponServiceImpl implements ICouponService {
    
    @Resource
    private CouponDao couponDao;
    
    @Resource
    private DcrOrderDao dcrOrderDao;
   
    @Resource
    private RedisLoginDao redisLoginDao;

    /** 
     * @see com.youmu.mall.coupon.service.ICouponService#addCoupon(com.youmu.mall.coupon.domain.Coupon)
     */
    @Transactional
    @Override
    public void addCoupon(Coupon coupon) {
        if(coupon.getBusinessType() != null && coupon.getBusinessType().getId() !=null && coupon.getBusinessType().getId().equals(Long.valueOf(GlobalConstant.DCR_BUSINESS_TYPE))) {
            coupon.setIsDCR(true);
        }
        couponDao.insertCoupon(coupon);
    }

    /** 
     * @see com.youmu.mall.coupon.service.ICouponService#listSysCoupon(com.youmu.mall.coupon.query.CouponSysQuery)
     */
    @Override
    public Page<CouponSysListVo> listSysCoupon(CouponSysQuery query) {
        Page<CouponSysListVo> page = new Page<>(query.getPageNum(), query.getPageSize());
        Long count = couponDao.selectSysCouponCount(query);
        page.setTotal(count);
        if(page.getTotal() > 0) {
            page.setData(couponDao.selectSysCouponList(query));
        }
        return page;
    }

    /** 
     * @see com.youmu.mall.coupon.service.ICouponService#listUserCoupon(com.youmu.mall.coupon.query.CouponShopQuery)
     */
    @Override
    public List<UserCouponShopListVo> listUserCoupon(CouponShopQuery query) {
        query.setUid(UserContext.getLongUserId());
        return couponDao.selectShopUserCouponList(query);
    }

    /** 
     * @see com.youmu.mall.coupon.service.ICouponService#getCouponDetail(java.lang.Long)
     */
    @Override
    public CouponShopDetailVo getCouponDetail(Long id) {
        return couponDao.selectShopCouponDetail(id);
    }

    /** 
     * @return 
     * @see com.youmu.mall.coupon.service.ICouponService#issueCoupon(java.lang.Long)
     */
    @Transactional
    @Override
    public Long issueCoupon(Long id) {
        // 获取当前用户的id
        Long uid = UserContext.getLongUserId();
        // 判断当前用户是否能领取该优惠券
        UserCouponIssueInfo info = couponDao.selectUserCouponIssueInfo(uid, id);
        if(info.getIssueTotal() >= info.getTotal()) {
            throw new BusinessException("对不起，你来晚了!");
        }
        if(info.getIssueCount() >= info.getIssueLimit()) {
            throw new BusinessException("你已经领取完了!");
        }
        if(info.getIsDCR()) {
            throw new SystemInnerException("是装修优惠券");
        }
        // 领取优惠券
        UserCoupon userCoupon = new UserCoupon(uid, id, OrderUtil.createCouponNumber(uid, id), false);
        couponDao.issueCoupon(userCoupon);
        couponDao.updateCouponIssueCount(id);
        // 返回优惠券的id 用于查询详情
        return userCoupon.getId();
    }

    /** 
     * @see com.youmu.mall.coupon.service.ICouponService#issueDCRCoupon(com.youmu.mall.dcr.domain.DCROrder)
     */
    @Transactional
    @Override
    public Long issueDCRCoupon(DCROrder order) {
        // 获取当前用户的id
        Long uid = UserContext.getLongUserId();
        Long couponId = order.getCoupon().getId();
        // 判断当前用户是否能领取该优惠券
        UserCouponIssueInfo info = couponDao.selectUserCouponIssueInfo(uid, couponId);
        if(info.getIssueTotal() >= info.getTotal()) {
            throw new BusinessException("对不起，你来晚了!");
        }
        if(info.getIssueCount() >= info.getIssueLimit()) {
            throw new BusinessException("你已经领取完了!");
        }
        if(!info.getIsDCR()) {
            throw new SystemInnerException("不是装修优惠券");
        }
        String couponNumber = OrderUtil.createCouponNumber(uid, couponId);
        UserCoupon userCoupon = new UserCoupon(uid, couponId, couponNumber, true);
        // 设置领取创建优惠券和用户之间的关联
        couponDao.issueCoupon(userCoupon);
        // 修改优惠券的领取数量 用以判断是否已经领取完了
        couponDao.updateCouponIssueCount(couponId);
        // 添加用户的装修订单 设置订单编号 用户 和 初始状态
        order.setOrderNo(OrderUtil.createDCROrderNumber(uid));
        order.setOrderStatus(GlobalConstant.DCROrderStatus.USER_APPLY);
        order.setUser(new User(uid));
        order.setCouponNumber(couponNumber);
        dcrOrderDao.insertDCROrder(order);
        return userCoupon.getId();
    }

    /** 
     * @see com.youmu.mall.coupon.service.ICouponService#listShopCoupon(com.youmu.mall.coupon.query.CouponShopQuery)
     */
    /** 
     * @see com.youmu.mall.coupon.service.ICouponService#listShopCoupon(com.youmu.mall.coupon.query.CouponShopQuery)
     */
    @Override
    public List<CouponShopListVo> listShopCoupon(CouponShopQuery query) {
        // 分页查询 未过期 
        // 可领取的优惠券列表
         query.setUid(UserContext.getLongUserId());
         return couponDao.selectShopCouponList(query);
    }

    /** 
     * @see com.youmu.mall.coupon.service.ICouponService#verificationCoupon(java.lang.String)
     */
    @Override
    public int verificationCoupon(String couponNo) {
        /***
         * 核销二维码的业务
         * 1. 传入扫描的二维码编号
         * 2. 从数据库里面查询优惠券相关的东西
         * 3. 如果当前优惠券存在，且没有被使用，获取当前二维码的过期时间 和 优惠券是否是装修的优惠券 和 商户的类型 和 商户的id(判断商户的类型和商户的id是否正确)
         * 4. 普通优惠券处理 
         *    1. 过期 直接提示优惠券已经过期 核销失败
         *    2. 正常 获取优惠券的商户id与当前用户的商户id比较是否相同 相同则可以核销否则 不能核销提示不是当前商户的优惠券 如果是通用的优惠券则直接使用
         *    3. 核销流程  直接修改使用状态 修改 使用时间 修改使用商户的id等参数 最后提示核销成功
         * 5. 装修优惠券的处理
         *    1. 过期 直接提示优惠券已经过期 核销失败
         *    2. 正常 获取优惠券的商户id与当前用户的商户id比较是否相同 相同则可以核销否则 不能核销提示不是当前商户的优惠券 装修优惠券必须制定商家
         *    3. 核销流程  直接修改使用状态 修改 使用时间 修改使用商户的id等参数 最后提示核销成功
         *    4. 修改订单的状态修改为二维码核销
         */
        CouponVerificationInfo info = couponDao.selectCouponVerificationInfo(couponNo);
        if(info == null) {
            throw new BusinessException("该优惠券不存在");
        }
        if(info.getIsUsed() != null && info.getIsUsed()) {
            throw new ParamException("该优惠券已经使用");
        }
        // 获取用户的信息
        BusinessUser user = redisLoginDao.getLoggedUserInfo(UserContext.getUserId(), BusinessUser.class);
        if(info.getIsExpire()) {
            // 修改订单的状态为核销过期
            DCROrder order = new DCROrder();
            order.setCouponNumber(couponNo);
            order.setOrderStatus(GlobalConstant.DCROrderStatus.USER_QRCODE_EXPIRE);
            order.setGmtVerifi(new Date());
            order.setVerifiUser(user);
            order.setVerifiBusiness(user.getBusiness());
            dcrOrderDao.updateDCROrderByCouponNo(order);
            throw new ParamException("该优惠券已经过期");
        }
        // 如果不能使用
        if(info.getIsStart() != null && !info.getIsStart()) {
            throw new BusinessException("该优惠券还未到使用日期");
        }
        // 处理通用优惠券 该优惠券不是哪一个商户的优惠券
        if(info.getBusinessId() == null) {
            // 如果优惠券的类型和当前商户的类型相同则可以核销
            if(info.getBusinessType() != null && info.getBusinessType().equals(user.getBusiness().getType().getId())) {
                // 核销优惠券
                return ((CouponServiceImpl)AopContext.currentProxy()).verifiCoupon(info, user, couponNo);
            } else {
                throw new BusinessException("优惠券类型有误");
            }
        } else {
            // 如果发券的商户就是该商户 那么可以核销优惠券
            if(info.getBusinessId() != null && info.getBusinessId().equals(user.getBusiness().getId())) {
                // 核销优惠券
               return ((CouponServiceImpl)AopContext.currentProxy()).verifiCoupon(info, user, couponNo);
            } else {
                throw new BusinessException("不是该商户的优惠券");
            }
        }
    }

    /**
     * 验证
     * @param info
     * @param couponNo
     * @return 
     */
    @Transactional
    public int verifiCoupon(CouponVerificationInfo info, BusinessUser user, String couponNo) {
        // 修改优惠券核销的状态
        couponDao.updateCouponUseInfo(user.getBusiness().getId(), user.getId(), couponNo);
        if(info.getIsDCR() != null && info.getIsDCR()) {
            // 是装修优惠券 修改订单的状态
            DCROrder order = new DCROrder();
            order.setCouponNumber(couponNo);
            order.setOrderStatus(GlobalConstant.DCROrderStatus.USER_USE_QRCODE);
            order.setGmtVerifi(new Date());
            order.setVerifiUser(user);
            order.setVerifiBusiness(user.getBusiness());
            int row = dcrOrderDao.updateDCROrderByCouponNo(order);
            if(row != 1) {
                throw new BusinessException("订单状态修改失败请稍后再试");
            }
            return GlobalConstant.DC_TYPE;
        }
        return GlobalConstant.NOT_DC_TYPE;
    }

    /** 
     * @see com.youmu.mall.coupon.service.ICouponService#listVerifiHistory(com.youmu.mall.coupon.query.CouponVerifiHistoryPageQuery)
     */
    @Override
    public List<CouponVerifiHistoryListVo> listVerifiHistory(CouponVerifiHistoryPageQuery query) {
        query.setVerifiUserId(UserContext.getLongUserId());
        return couponDao.selectVerifiHistoryCouponList(query);
    }

    /** 
     * @see com.youmu.mall.coupon.service.ICouponService#listCouponUsedStatisticsInfo(com.youmu.mall.coupon.query.CouponStatisticsInfoQuery)
     */
    @Override
    public Page<CouponUsedStatisticsVo> listCouponUsedStatisticsInfo(CouponStatisticsInfoQuery query) {
        Page<CouponUsedStatisticsVo> page = new Page<>(query.getPageNum(), query.getPageSize());
        page.setTotal(couponDao.selectCouponUsedStatisticsInfoCount(query));
        if(page.getTotal() > 0) {
            page.setData(couponDao.selectCouponUsedStatisticsInfoList(query));
        }
        return page;
    }

}
