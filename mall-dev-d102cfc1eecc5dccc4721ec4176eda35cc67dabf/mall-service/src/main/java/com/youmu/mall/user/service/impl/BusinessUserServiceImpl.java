/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.user.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.youmu.common.context.GlobalConstant;
import com.youmu.common.context.TokenContext;
import com.youmu.common.context.UserContext;
import com.youmu.common.digest.utils.MD5Util;
import com.youmu.common.page.utils.Page;
import com.youmu.common.token.utils.JwtUtils;
import com.youmu.common.token.utils.TokenUtils;
import com.youmu.mall.bus.dao.BusinessDao;
import com.youmu.mall.bus.dao.BusinessUserDao;
import com.youmu.mall.bus.domain.Business;
import com.youmu.mall.exception.BusinessException;
import com.youmu.mall.exception.ParamException;
import com.youmu.mall.redis.login.dao.RedisLoginDao;
import com.youmu.mall.user.domain.BusinessUser;
import com.youmu.mall.user.query.BusinessUserQuery;
import com.youmu.mall.user.service.IBusinessUserService;
import com.youmu.mall.user.vo.BusinessUserListVo;
import com.youmu.mall.user.vo.LoggedBusinessUserVo;

/**
 * 商户账户服务实现类.
 * @author zh
 * @version $Id: BusinessUserServiceImpl.java, v 0.1 2017年2月28日 上午11:06:25 zh Exp $
 */
@Service
public class BusinessUserServiceImpl implements IBusinessUserService {
    
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    @Resource
    private BusinessUserDao businessUserDao;
    
    @Resource
    private BusinessDao businessDao;
    
    @Resource
    private RedisLoginDao redisLoginDao;

    /** 
     * @see com.youmu.mall.user.service.IBusinessUserService#addBusinessUser(com.youmu.mall.user.domain.BusinessUser)
     */
    @Override
    public void addBusinessUser(BusinessUser businessUser) {
        // 密码加密
        businessUser.setPassword(MD5Util.MD5(businessUser.getPassword()));
        // 查询商户账户是否重复
        Long count = businessUserDao.selectBusinessUserRepeatCount(businessUser);
        if(count > 0) {
            throw new BusinessException("商户账号重复");
        }
        // 判断是否是装修公司的商户
        businessUser.setIsDCR(Long.valueOf(GlobalConstant.DCR_BUSINESS_TYPE).equals(businessDao.getTypeIdById(businessUser.getBusiness().getId())));
        // 如果是银行账号当时公司不是装修贷款公司 则报错
        if(Integer.valueOf(GlobalConstant.BusinessUserType.BANK).equals(businessUser.getType()) && !businessUser.getIsDCR()) {
            throw new BusinessException("银行商户账户所属的商户不是装修类型的商户");
        }
        // 保存账号
        businessUserDao.insertBusinessUser(businessUser);
    }

    /** 
     * @see com.youmu.mall.user.service.IBusinessUserService#updateBusinessUser(com.youmu.mall.user.domain.BusinessUser)
     */
    @Override
    public void updateBusinessUser(BusinessUser businessUser) {
        String password = businessUser.getPassword();
        if(StringUtils.isNotBlank(password))
            businessUser.setPassword(MD5Util.MD5(password));
        // 查询商户账户是否重复
        Long count = businessUserDao.selectBusinessUserRepeatCount(businessUser);
        if(count > 0) {
            throw new BusinessException("商户账号重复");
        }
        LoggedBusinessUserVo info = businessUserDao.selectLoggedBusinessInfo(businessUser.getId());
        businessUser.setIsDCR(info.getIsDCR());
        // 修改商户的id 是否是装修公司
        if(businessUser.getBusiness() != null && businessUser.getBusiness().getId() != null) {
            businessUser.setIsDCR(Long.valueOf(GlobalConstant.DCR_BUSINESS_TYPE).equals(businessDao.getTypeIdById(businessUser.getBusiness().getId())));
        }
        // 获取修改后台的类型 或者当前的类型
        Integer type = businessUser.getType() == null ? info.getType() : businessUser.getType();
        // 如果是银行账号当时公司不是装修贷款公司 则报错
        if(Integer.valueOf(GlobalConstant.BusinessUserType.BANK).equals(type) && !businessUser.getIsDCR()) {
            throw new BusinessException("银行商户账号所属的商户不是装修类型的商户");
        }
        businessUserDao.updateBusinessUser(businessUser);
    }

    /** 
     * @see com.youmu.mall.user.service.IBusinessUserService#deleteBusinessUser(java.lang.Long)
     */
    @Override
    public void deleteBusinessUser(Long id) {
        businessUserDao.deleteBusinessUser(id);
    }

    /** 
     * @see com.youmu.mall.user.service.IBusinessUserService#listBusinessUser(com.youmu.mall.user.query.BusinessUserQuery)
     */
    @Override
    public Page<BusinessUserListVo> listBusinessUser(BusinessUserQuery query) {
        Page<BusinessUserListVo> page = new Page<>(query.getPageNum(), query.getPageSize());
        Long count = businessUserDao.selectBusinessUserCount(query);
        page.setTotal(count);
        if(page.getTotal() > 0) {
            page.setData(businessUserDao.selectBusinessUserList(query));
        }
        return page;
    }

    /** 
     * @see com.youmu.mall.user.service.IBusinessUserService#loginFront(java.lang.String, java.lang.String, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public BusinessUser loginFront(String account, String password, HttpServletResponse response) {
        //  查询系统用户的登录信息
        BusinessUser u = businessUserDao.selectBusinessUserLoginInfo(account);
        if(u == null) {
            throw new BusinessException("账号不存在");
        }
        if(!StringUtils.equals(u.getPassword(), MD5Util.MD5(password))) {
            throw new BusinessException("密码错误");
        }
        String token = JwtUtils.createToken(u.getId().toString(), TokenContext.TOKEN_EXPIRE);
        // 将用户登录信息放在redis中
        redisLoginDao.login(u.getId().toString(), token, TokenContext.SESSION_EXPIRE,  u);
        // 将token放入cookie
        TokenUtils.setTokenInCookie(response, TokenUtils.BUS_FRONT_TOKEN_SUFFIX, token);
        if(u.getType() == null || (u.getType().intValue() != GlobalConstant.BusinessUserType.NORMAL && u.getType() != GlobalConstant.BusinessUserType.BANK)) {
            throw new BusinessException("你无权登录");
        }
        u.setPassword(null);
        return u;
    }

    /** 
     * @see com.youmu.mall.user.service.IBusinessUserService#getLoggedBusinessInfo()
     */
    @Override
    public LoggedBusinessUserVo getLoggedBusinessInfo() {
        return businessUserDao.selectLoggedBusinessInfo(UserContext.getLongUserId());
    }

    /** 
     * @see com.youmu.mall.user.service.IBusinessUserService#updateBusinessLicense(java.lang.String, java.util.List)
     */
    @Override
    public void updateBusinessLicense(String licensePath, List<String> storePaths) {
        // 获取商户的business_id
        BusinessUser userInfo = redisLoginDao.getLoggedUserInfo(UserContext.getUserId(), BusinessUser.class);
        Long id = userInfo.getBusiness().getId();
        Business business = new Business();
        business.setId(id);
        business.setBusLicense(licensePath);
        business.setBusStoreImgs(org.springframework.util.StringUtils.collectionToDelimitedString(storePaths, ","));
        business.setAuthPass(true);
        // 修改商户的license
        businessDao.updateBusiness(business);
    }

    /** 
     * @see com.youmu.mall.user.service.IBusinessUserService#updateBusinessPassword(java.lang.String, java.lang.String)
     */
    @Override
    public void updateBusinessPassword(String oldPassword, String newPassword) {
        Long userId = UserContext.getLongUserId();
        int row = businessUserDao.updateBusinessUserPassword(userId, MD5Util.MD5(oldPassword), MD5Util.MD5(newPassword));
        if(row != 1) {
            throw new ParamException("旧密码错误");
        }
    }

    /** 
     * @see com.youmu.mall.user.service.IBusinessUserService#loginBack(java.lang.String, java.lang.String, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public BusinessUser loginBack(String account, String password, HttpServletResponse response) {
        //  查询系统用户的登录信息
        BusinessUser u = businessUserDao.selectBusinessUserLoginInfo(account);
        if(u == null) {
            throw new BusinessException("账号不存在");
        }
        if(!StringUtils.equals(u.getPassword(), MD5Util.MD5(password))) {
            throw new BusinessException("密码错误");
        }
        String token = JwtUtils.createToken(u.getId().toString(), TokenContext.TOKEN_EXPIRE);
        // 将用户登录信息放在redis中
        redisLoginDao.login(u.getId().toString(), token, TokenContext.SESSION_EXPIRE,  u);
        // 将token放入cookie
        TokenUtils.setTokenInCookie(response, TokenUtils.BUS_TOKEN_SUFFIX, token);
        if(u.getType() == null || !u.getType().equals(GlobalConstant.BusinessUserType.ADMIN)) {
            throw new BusinessException("你无权登录");
        }
        u.setPassword(null);
        return u;
    }

    /** 
     * @see com.youmu.mall.user.service.IBusinessUserService#loginout(javax.servlet.http.HttpServletResponse)
     */
    @Override
    public void loginoutFront(HttpServletResponse response) {
        redisLoginDao.logout(UserContext.getUserId());
        TokenUtils.removeTokenInCookie(response, TokenUtils.BUS_FRONT_TOKEN_SUFFIX);
    }

    /** 
     * @see com.youmu.mall.user.service.IBusinessUserService#loginoutBack(javax.servlet.http.HttpServletResponse)
     */
    @Override
    public void loginoutBack(HttpServletResponse response) {
        redisLoginDao.logout(UserContext.getUserId());
        TokenUtils.removeTokenInCookie(response, TokenUtils.BUS_TOKEN_SUFFIX);
    }

    /** 
     * @see com.youmu.mall.user.service.IBusinessUserService#getBusinessUserById(java.lang.Long)
     */
    @Override
    public BusinessUser getBusinessUserById(Long id) {
        return businessUserDao.getBusinessUserById(id);
    }
}
