/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.user.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.collections4.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.youmu.common.alidayu.utils.SmsUtil;
import com.youmu.common.cofig.utils.ConfigUtils;
import com.youmu.common.context.GlobalConstant;
import com.youmu.common.context.TokenContext;
import com.youmu.common.context.UserContext;
import com.youmu.common.digest.utils.MD5Util;
import com.youmu.common.global.constant.StatusConstant;
import com.youmu.common.page.utils.Page;
import com.youmu.common.token.utils.AuthCodeUtils;
import com.youmu.common.token.utils.JwtUtils;
import com.youmu.common.token.utils.TokenUtils;
import com.youmu.common.validate.utils.ValidateUtils;
import com.youmu.common.wx.utils.WxAuthAccessToken;
import com.youmu.common.wx.utils.WxPublicNumberUtil;
import com.youmu.common.wx.utils.WxUserInfo;
import com.youmu.mall.exception.BusinessException;
import com.youmu.mall.exception.ParamException;
import com.youmu.mall.order.dao.ProductOrderDao;
import com.youmu.mall.order.domain.ProductOrder;
import com.youmu.mall.order.dto.OrderQuantityDto;
import com.youmu.mall.points.dao.PointsRecordDao;
import com.youmu.mall.product.dao.ProductGroupDao;
import com.youmu.mall.product.domain.ProductGroup;
import com.youmu.mall.product.dto.GroupProductIngoDto;
import com.youmu.mall.redis.login.dao.RedisLoginDao;
import com.youmu.mall.user.dao.UserDao;
import com.youmu.mall.user.dao.UserShareDao;
import com.youmu.mall.user.domain.User;
import com.youmu.mall.user.domain.UserShare;
import com.youmu.mall.user.query.UserQuery;
import com.youmu.mall.user.service.IUserService;
import com.youmu.mall.user.vo.UserInfoVo;
import com.youmu.mall.user.vo.UserListVo;

/**
 * 普通用户服务.
 * @author zh
 * @version $Id: UserServiceImpl.java, v 0.1 2017年2月25日 下午4:36:48 zh Exp $
 */
@Service
public class UserServiceImpl implements IUserService {
    
    private final Logger logger = LoggerFactory.getLogger(getClass());
    
    @Resource
    RedisLoginDao redisLoginDao;

    @Resource
    private UserDao userDao;
    
    @Resource
    private UserShareDao UserShareDao;
    
    @Resource
    private ProductOrderDao orderDao;
    
    @Resource
    private ProductGroupDao productGroupDao;
    
    @Resource
    private PointsRecordDao pointsRecordDao;
    
    /** 
     * @see com.youmu.mall.user.service.IUserService#login(java.lang.String, java.lang.String, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public String login(String mobile, String password, HttpServletResponse response) {
        User user = userDao.selectLoginUserInfo(mobile);
        if(user == null) {
            throw new BusinessException("账号不存在");
        } else if(!StringUtils.equals(MD5Util.MD5(password), user.getPassword())){
            throw new BusinessException("账号密码错误");
        }
        String token = JwtUtils.createToken(user.getId().toString(), TokenContext.TOKEN_EXPIRE);
        // 将用户登录信息放在redis中
        redisLoginDao.login(user.getId().toString(), token, TokenContext.SESSION_EXPIRE, user);
        // 将token放入cookie
        TokenUtils.setTokenInCookie(response, TokenUtils.SHOP_TOKEN_SUFFIX, token);
        return token;
    }
    
    /** 
     * @see com.youmu.mall.user.service.IUserService#sendRegistAuthCode(java.lang.String)
     */
    @Override
    public void sendRegistAuthCode(String mobile) {
        // 查询手机号码是否存在
        int count = userDao.selectUserCountByMobile(mobile);
        if(count > 0) {
            throw new BusinessException("该手机号码已经注册,请直接登录");
        }
        // 生成验证码
        String authCode = String.valueOf(AuthCodeUtils.getCommonAuthCode());
        Map<String, Object> params = new HashedMap<>(2);
        params.put("code", authCode);
        params.put("product", ConfigUtils.getProductName());
        // 调用短信发送接口发送短信
        SmsUtil.sendSms(mobile, params, ConfigUtils.getRegistSmsCode());
        // 将验证码放入缓存中
        redisLoginDao.saveRegistAuthCode(mobile, authCode, GlobalConstant.AUTH_CODE_TIME_OUT);
    }

    /** 
     * @return 
     * @see com.youmu.mall.user.service.IUserService#regist(java.lang.String, java.lang.String, java.lang.String)
     */
    @Transactional
    @Override
    public User regist(User user, String authCode, HttpServletResponse response) {
        // 1. 校验验证码是否正确
        registInternal(user, authCode);
        // 自动登录 添加
        autoLogin(user, response);
        return user;
    }

    /**
     * 
     * @param user
     * @param response
     */
    private void autoLogin(User user, HttpServletResponse response) {
        // 登录
        String uid = user.getId().toString();
        String token = JwtUtils.createToken(uid, TokenContext.TOKEN_EXPIRE);
        // 将用户登录信息放在redis中
        user.setPassword(null);
        redisLoginDao.login(uid, token, TokenContext.SESSION_EXPIRE, user);
        // 将token放入cookie
        TokenUtils.setTokenInCookie(response, TokenUtils.SHOP_TOKEN_SUFFIX, token);
    }

    /**
     * 
     * @param user
     * @param authCode
     */
    private void registInternal(User user, String authCode) {
        String mobile = user.getMobile();
        String savedCode = redisLoginDao.getRegistAuthCode(user.getMobile());
        ValidateUtils.checkEquals(authCode, savedCode, "验证码有误，请稍后再试!");
        // 2. 删除用户的验证码
        redisLoginDao.deleteRegistAuthCode(mobile);
        // 3. 查询用户是否已经存在
        int existCount = userDao.selectUserCountByMobile(mobile);
        if(existCount > 0) {
            throw new BusinessException("用户已经存在");
        }
        // 4. 写入用户的信息到数据库
        user.setMobile(mobile);
        user.setPassword(MD5Util.MD5(user.getPassword()));
        userDao.saveUser(user);
    }

    @Override
    public boolean checkResetPasswordAuthCode(String mobile, String authCode) {
        // 2. 校验验证码是否正确
        String savedCode = redisLoginDao.getResetPasswordAuthCode(mobile);
        return StringUtils.equals(authCode, savedCode);
    }
    
    /** 
     * @see com.youmu.mall.user.service.IUserService#sendResetPasswordAuthCode(java.lang.String)
     */
    @Override
    public void sendResetPasswordAuthCode(String mobile) {
        
        long count = userDao.selectUserCountByMobile(mobile);
        if(count == 0) {
            throw new BusinessException("手机号未注册");
        }
        // 生成验证码
        String authCode = String.valueOf(AuthCodeUtils.getCommonAuthCode());
        Map<String, Object> params = new HashedMap<>(2);
        params.put("code", authCode);
        params.put("product", ConfigUtils.getProductName());
        // 获取当前用户的手机号码
        // 调用短信发送接口发送短信
        SmsUtil.sendSms(mobile, params, ConfigUtils.getResetPWDSmsCode());
        logger.debug("send rest password auth code response ok.");
        // 将验证码放入缓存中
        logger.debug("save auth code...");
        redisLoginDao.saveResetPasswordAuthCode(mobile, authCode, GlobalConstant.AUTH_CODE_TIME_OUT);
    }
    
    /** 
     * @see com.youmu.mall.user.service.IUserService#sendResetMobileAuthCode(java.lang.String)
     */
    @Override
    public void sendResetMobileAuthCode(String newMobile) {
        // 获取当前用户
        User user = redisLoginDao.getLoggedUserInfo(UserContext.getUserId(), User.class);
        // 比较原手机号码和旧手机号码
        ValidateUtils.checkNotEquals(user.getMobile(), newMobile, "新手机号码和原手机号码相同.");
        
        // 判断新手机号码是否已经注册
        int count = userDao.selectUserCountByMobile(newMobile);
        if(count > 0) {
            throw new BusinessException("该手机号码已经注册,请检查后再试");
        }
        
        // 发送验证码
        String authCode = String.valueOf(AuthCodeUtils.getCommonAuthCode()); 
        Map<String, Object> params = new HashedMap<>(2);
        params.put("code", authCode);
        params.put("product", ConfigUtils.getProductName());
        // 调用短信发送接口发送短信
        SmsUtil.sendSms(newMobile, params, ConfigUtils.getResetMobileSmsCode());
        // 将验证码放入缓存中
        redisLoginDao.saveResetMobileAuthCode(newMobile, authCode, GlobalConstant.AUTH_CODE_TIME_OUT);
    }
    
    /** 
     * @see com.youmu.mall.user.service.IUserService#restMobile(java.lang.String, java.lang.String)
     */
    @Transactional
    @Override
    public void restMobile(String newMobile, String authCode) {
        String id = UserContext.getUserId();
        String savedCode = redisLoginDao.getResetMobileAuthCode(newMobile);
        ValidateUtils.checkEquals(savedCode, authCode, "验证码有误，请稍后再试!");
        redisLoginDao.deleteResetMobileAuthCode(newMobile);
        userDao.updateMobile(id, newMobile);
    }
    

    /** 
     * @see com.youmu.mall.user.service.IUserService#listUser(com.youmu.mall.user.query.UserQuery)
     */
    @Override
    public Page<UserListVo> listUser(UserQuery query) {
        Page<UserListVo> page = new Page<>(query.getPageNum(), query.getPageSize());
        Long count = userDao.selectUserCount(query);
        page.setTotal(count);
        if(page.getTotal() > 0) {
            page.setData(userDao.selectUserList(query));
        }
        return page;
    }

    /** 
     * @see com.youmu.mall.user.service.IUserService#logout()
     */
    @Override
    public void logout() {
        redisLoginDao.logout(UserContext.getUserId());
    }

    /** 
     * @see com.youmu.mall.user.service.IUserService#sendOldResetMobileAuthCode()
     */
    @Override
    public void sendOldResetMobileAuthCode() {
        User user = redisLoginDao.getLoggedUserInfo(UserContext.getUserId(), User.class);
        String authCode = String.valueOf(AuthCodeUtils.getCommonAuthCode()); 
        Map<String, Object> params = new HashedMap<>(2);
        params.put("code", authCode);
        params.put("product", ConfigUtils.getProductName());
        // 获取当前用户的手机号码
        // 调用短信发送接口发送短信
        SmsUtil.sendSms(user.getMobile(), params, ConfigUtils.getResetMobileSmsCode());
        // 将验证码放入缓存中
        redisLoginDao.saveOldResetMobileAuthCode(user.getMobile(), authCode, GlobalConstant.AUTH_CODE_TIME_OUT);
    }

    /** 
     * @see com.youmu.mall.user.service.IUserService#checkOldRestMobileAuthCode(java.lang.String)
     */
    @Override
    public boolean checkOldRestMobileAuthCode(String authCode) {
        User user = redisLoginDao.getLoggedUserInfo(UserContext.getUserId(), User.class);
        String savedCode = redisLoginDao.getOldResetMobileAuthCode(user.getMobile());
        redisLoginDao.deleteOldResetMobileAuthCode(user.getMobile());
        return StringUtils.equals(savedCode, authCode);
    }

    /** 
     * @see com.youmu.mall.user.service.IUserService#getUserInfo()
     */
    @Override
    public UserInfoVo getUserInfo() {
        return userDao.getUserInfo(UserContext.getLongUserId());
    }
    
    /**
     * @see com.youmu.user.service.IUservice#getUid()
     */
    @Override
    public String getUid(){
    	String uid = UserContext.getUserId();
    	if(uid == null){
    		uid = "";
    	}
    	return UserContext.getUserId();
    }
    
    /**
     * @see com.youmu.mall.user.service.IUserService#getUserMobile()
     */
    @Override
    public String getUserMobile() {
        return redisLoginDao.getLoggedUserInfo(UserContext.getUserId(), User.class).getMobile();
    }

    /** 
     * @see com.youmu.mall.user.service.IUserService#confirmRecevied(java.lang.Long)
     */
    @Override
    public void confirmRecevied(Long orderId) {
        long userId = UserContext.getLongUserId();
        ProductOrder order = orderDao.getById(orderId, userId);
        if(order == null){
            throw new BusinessException("订单为空");
        }
        if(order.getStatus() != null && order.getStatus() == StatusConstant.TWO){
        	
        	//用户确认订单 如包含积分商品更新积分记录进度
        	
        	List<OrderQuantityDto> Dtos =orderDao.getQuantityDtoById(orderId);
        	if(Dtos != null && Dtos.size()>0){
            for (OrderQuantityDto orderQuantityDto : Dtos) {
            	
                GroupProductIngoDto groupDto = productGroupDao.getGroupInfoByProductId(orderQuantityDto.getProductId());
                if(groupDto != null){
                	ProductGroup productGroup = productGroupDao.getById(groupDto.getGroupId());
                	 
                    if( productGroup.getGroupType() == 4){
           	 			pointsRecordDao.updateRecordProgress(order.getSn(), 3);
                    }
                }
            }
        	}
            userDao.confirmRecevied(orderId,userId);
        }else {
            throw new BusinessException("该订单不为待收货");
        }
    }

    /** 
     * @see com.youmu.mall.user.service.IUserService#updateUserHeadIcon(java.lang.Long, java.lang.String)
     */
    @Override
    public void updateUserHeadIcon(Long userId, String image) {
        userDao.updateUserHeadIcom(userId, image);
    }

    /** 
     * @see com.youmu.mall.user.service.IUserService#isLogged(javax.servlet.http.HttpServletRequest)
     */
    @Override
    public boolean isLogged(HttpServletRequest request) {
        String token = TokenUtils.getTokenInHeader(request);
        token = token == null ? TokenUtils.getTokenInCookie(request, TokenUtils.SHOP_TOKEN_SUFFIX) : token;
        if (token == null) {
            return false;
        } else {
            try {
                String uid = TokenUtils.getUidInToken(token);
                if (redisLoginDao.validateToken(uid, token)) {
                    redisLoginDao.resetLoginExpireTime(uid, TokenContext.SESSION_EXPIRE);
                    return true;
                } else {
                    return false;
                }
            } catch (Exception e) {
                logger.error("token校验出错", e);
                return false;
            }
        }
    }

    /** 
     * @see com.youmu.mall.user.service.IUserService#resetPassword(java.lang.String, java.lang.String)
     */
    @Override
    public void resetPassword(String mobile, String password) {
        userDao.updatePassword(mobile, MD5Util.MD5(password));
    }

    /** 
     * @see com.youmu.mall.user.service.IUserService#changePassword(java.lang.Long, java.lang.String, java.lang.String)
     */
    @Transactional
    @Override
    public void changePassword(Long userId, String oldPassword, String newPassword) {
        int row = userDao.updateUserPassword(userId, MD5Util.MD5(oldPassword), MD5Util.MD5(newPassword));
        if(row != 1) {
            throw new BusinessException("原密码有误");
        }
    }

    /** 
     * @see com.youmu.mall.user.service.IUserService#registInWx(java.lang.String, java.lang.String, java.lang.String, java.lang.String, javax.servlet.http.HttpServletResponse)
     */
    @Transactional
    @Override
    public User registInWx(User user, String authCode, String openId,
                           HttpServletResponse response) {
        // 注册并自动登录
        registInternal(user, authCode);
        
        // 判断openid是否已经绑定
        User userInfo = userDao.selectLoginUserInfoByWxOpenId(openId);
        if(userInfo == null) {
            bindWxUserInfo(user, openId);
            // 自动登录
            autoLogin(user, response);
            return user;
        } 
        throw new BusinessException("该账号已经绑定,请直接登录");
    }

    /**
     * 绑定微信信息
     * @param user
     * @param openId
     */
    private void bindWxUserInfo(User user, String openId) {
        WxAuthAccessToken wxAuthToken = WxPublicNumberUtil.getAuthAccessTokenByOpenId(openId);
        if(wxAuthToken == null || wxAuthToken.getExpireTimeStamp() < System.currentTimeMillis()) {
            throw new BusinessException("授权超时,请返回重试!");
        }
        // 删除map里的token
        WxPublicNumberUtil.removeAuthAccessTokenByOpenId(openId);
        WxUserInfo info = WxPublicNumberUtil.getWxUserInfo(wxAuthToken);
        user.setUsername(info.getNickname());
        user.setHeadIcon(info.getHeadimgurl());
        user.setWxOpenid(info.getOpenid());
        logger.debug("bind user{}, wx info{}.", user.getId(), info);
        userDao.updateUserWxAccountInfo(user);
    }
    
    @Override
	public String getUrlPlusUid(String url,Integer groupType,HttpServletResponse response) {
		Long userid=UserContext.getLongUserId();
		String newUrl=null;
		if(userid==null){
			newUrl = ConfigUtils.getLoginUrl();
			try {
				response.sendRedirect(newUrl);
			} catch (IOException e) {
				throw new ParamException("请重新登录");
			}
		}
		if(groupType==4){
			groupType=1;
		}
		UserShare userShare=new UserShare(userid,groupType);
		if(UserShareDao.getUserShare(userid, groupType)==null){
			UserShareDao.insertUserShare(userShare);
		}
		if(url.indexOf("?uid=")==-1){
			newUrl=url+"?uid="+userid;
		}else{
			newUrl=url;
		}
		UserContext.setAttrInSession(UserContext.TO_URL_IN_SESSION, newUrl);
	    return newUrl;
	}
}
