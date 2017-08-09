/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.wx.service.impl;

import java.io.IOException;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.youmu.common.cofig.utils.ConfigUtils;
import com.youmu.common.context.GlobalConstant;
import com.youmu.common.context.TokenContext;
import com.youmu.common.context.UserContext;
import com.youmu.common.digest.utils.MD5Util;
import com.youmu.common.token.utils.JwtUtils;
import com.youmu.common.token.utils.TokenUtils;
import com.youmu.common.wx.utils.WxAuthAccessToken;
import com.youmu.common.wx.utils.WxPublicNumberUtil;
import com.youmu.common.wx.utils.WxUserInfo;
import com.youmu.mall.exception.BusinessException;
import com.youmu.mall.product.dao.ProductGroupDao;
import com.youmu.mall.product.domain.ProductGroupDetail;
import com.youmu.mall.redis.login.dao.RedisLoginDao;
import com.youmu.mall.share.dao.ShareRecordDao;
import com.youmu.mall.share.domain.ShareRecord;
import com.youmu.mall.user.dao.UserDao;
import com.youmu.mall.user.dao.UserShareDao;
import com.youmu.mall.user.domain.User;
import com.youmu.mall.user.domain.UserShare;
import com.youmu.mall.wx.service.IWxAuthService;

/**
 * 微信服务实现类
 * @author zh
 * @version $Id: WxServiceImpl.java, v 0.1 2017年3月15日 下午7:46:50 zh Exp $
 */
@Service
public class WxAuthServiceImpl implements IWxAuthService {

    
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    @Resource
    private UserDao userDao;
    
    @Resource
    private RedisLoginDao redisLoginDao;
    
    @Resource
    private ShareRecordDao shareRecordDao;
    
    @Resource
    private UserShareDao userShareDao;
    
    @Resource
    private ProductGroupDao productGroupDao;

    /** 
     * @see com.youmu.mall.wx.service.IWxAuthService#autoLogin()
     */
    /** 
     * @see com.youmu.mall.wx.service.IWxAuthService#getWxUserAuthUrl(java.lang.String)
     */
    @Override
    public String getWxUserAuthUrl(String url) {
       UserContext.setAttrInSession(UserContext.TO_URL_IN_SESSION, url);
       return WxPublicNumberUtil.getWebAuthUrl(ConfigUtils.getWxAutoLoginRedirectUrl(), WxPublicNumberUtil.AUTH_USERINFO_SCOPE, GlobalConstant.WX_AUTO_LOGIN + "");
    }
    
    @Override
    public String getShareWxUserAuthUrl(String url) {
       UserContext.setAttrInSession(UserContext.TO_URL_IN_SESSION, url);
       return WxPublicNumberUtil.getWebAuthUrl(ConfigUtils.getWxAutoLoginRedirectUrl(), WxPublicNumberUtil.AUTH_BASE_SCOPE, GlobalConstant.WX_Share + "");
    }
    
    @Override  
    public String getWxPayCodeUrl() {
           return WxPublicNumberUtil.getWebAuthUrl(ConfigUtils.getWxAutoLoginRedirectUrl(), WxPublicNumberUtil.AUTH_BASE_SCOPE, GlobalConstant.WX_PAY_OPENID + "");
    }
    
    /** 
     * @see com.youmu.mall.wx.service.IWxAuthService#handleWxAuthCallBack(java.lang.String, 

java.lang.String, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public void handleWxAuthCallBack(String code, String state, HttpServletResponse response) {
        String redirectUrl = "";
        logger.info("11111111");
        if(StringUtils.equals(GlobalConstant.WX_AUTO_LOGIN + "", state)) {
            // 处理自动登录
            WxAuthAccessToken authToken = WxPublicNumberUtil.getAuthAccessToken(code);
            String openId = authToken.getOpenid();
            User user = userDao.selectLoginUserInfoByWxOpenId(openId);
            // 用户存在 登录
            if(user != null) {
                autoLogin(response, user);
                Object toUrlObject = UserContext.getAttrInSession(UserContext.TO_URL_IN_SESSION);
                String toUrl = toUrlObject == null ? null : toUrlObject.toString();
                if(StringUtils.isNotBlank(toUrl)) {
                   redirectUrl = toUrl; 
                } else {
                    // 跳转到首页
                    redirectUrl = ConfigUtils.getLoginSuccessUrl();
                }
            } else {
              // 跳转到绑定页面  
               redirectUrl = ConfigUtils.getBindWxAccountUrl() + "?openid=" + openId;
            }
        } else if(StringUtils.equals(GlobalConstant.REGIST_IN_WX + "", state)) {
            WxAuthAccessToken authToken = WxPublicNumberUtil.getAuthAccessToken(code);
            String openId = authToken.getOpenid();
            redirectUrl = ConfigUtils.getRegistUrl() + "?openid=" + openId ;
        } else if(StringUtils.equals(GlobalConstant.WX_PAY_OPENID + "", state)) {
            WxAuthAccessToken authToken = WxPublicNumberUtil.getAuthAccessToken(code);
            String openId = authToken.getOpenid();
            redirectUrl = ConfigUtils.getGoPayUrl() + "?openid=" + openId ;
        } else if(StringUtils.equals(GlobalConstant.WX_Share + "", state)){
        	Object toUrlObject = UserContext.getAttrInSession(UserContext.TO_URL_IN_SESSION);
            String toUrl = toUrlObject == null ? null : toUrlObject.toString();
        	// 获取访问者openId
            WxAuthAccessToken authToken = WxPublicNumberUtil.getAuthAccessToken(code);
            String openId = authToken.getOpenid();
            logger.debug("账号 openid{}.", openId);
			Long productId = null;
			Long uid = null;
            int flag1 = toUrl.indexOf("?uid="); 
            int flag2 = toUrl.indexOf("goodsDetail/");
            
            if(flag2 == -1){
            	throw new BusinessException("未获取Url中商品信息");
            }else{
            	if(flag1 != -1){
            		//截取toUrl去掉uid
            		productId = Long.valueOf(toUrl.substring(flag2+12, flag1));
            		uid = Long.valueOf(toUrl.substring(flag1+5,toUrl.length()));
            		toUrl = toUrl.substring(0,flag1);
            	  
            ProductGroupDetail productGroupDetail = productGroupDao.getDetailInfoByProductId(productId);
            Long groupId = productGroupDetail.getGroupId();
            if(productGroupDao.getById(groupId).getGroupType().intValue() == 4&&userDao.selectLoginUserInfoByWxOpenId(openId)==null){
            	if(shareRecordDao.getShareRecordByUserId(uid, productId, openId)==null){
            		ShareRecord shareRecord = new ShareRecord(productId, uid, openId);
            		shareRecordDao.insertShareRecord(shareRecord);
            		UserShare userShare = new UserShare(uid,1); 
            		userShareDao.updateShareTimes(userShare);
            	}else{
	            	UserShare userShare = new UserShare(uid,1); 
	        		userShareDao.updateShareTimes(userShare);
            }
            	}
            /*if(productGroupDao.getById(groupId).getGroupType().intValue() == 4){
            	if(userDao.selectLoginUserInfoByWxOpenId(openId)==null && shareRecordDao.getShareRecordByNewOpenId(openId)==null){
            		ShareRecord shareRecord = new ShareRecord(productId, uid, openId);
            		shareRecordDao.insertShareRecord(shareRecord);
            		UserShare userShare = new UserShare(uid,1); 
            		userShareDao.updateShareTimes(userShare);
            	}
            }*/
            	if(StringUtils.isNotBlank(toUrl)) {
            		redirectUrl = toUrl; 
            		}
            	}
            }
        }
        else {
           redirectUrl = ConfigUtils.getLoginUrl();
        }
        try {
            response.sendRedirect(redirectUrl);
        } catch (IOException e) {
            logger .error("重定向错误", e);
        }
    }

    /**
     * 自动登陆
     * @param response
     * @param user
     * @return
     */
    private String autoLogin(HttpServletResponse response, User user) {
        // 登录
        String token = JwtUtils.createToken(user.getId().toString(), TokenContext.TOKEN_EXPIRE);
        // 将用户登录信息放在redis中
        redisLoginDao.login(user.getId().toString(), token, TokenContext.SESSION_EXPIRE, user);
        // 将token放入cookie
        TokenUtils.setTokenInCookie(response, TokenUtils.SHOP_TOKEN_SUFFIX, token);
        return token;
    }

    /** 
     * @see com.youmu.mall.wx.service.IWxAuthService#bindWxAccount(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public void bindWxAccount(User user, String openid, HttpServletResponse response) {
        // 查询用户是否存在 密码是否正确  
        // 如果用户存在 且没有绑定微信账号 绑定微信账号并且 自动登录
        User findedUser = userDao.selectLoginUserInfo(user.getMobile());
        if(findedUser == null) {
            throw new BusinessException("账号不存在");
        }
        if(!StringUtils.equals(MD5Util.MD5(user.getPassword()), findedUser.getPassword())){
            throw new BusinessException("账号密码错误");
        }
        if(findedUser.getWxOpenid() != null) {
            if(StringUtils.equals(openid, findedUser.getWxOpenid())) {
                logger.debug("账号已经绑定 openid{}, userid{}, 自动登录.", openid, findedUser.getId());
                autoLogin(response, findedUser);
                return;
            } else {
                throw new BusinessException("该账号已经绑定,请直接登录.");
            }
        }
        WxAuthAccessToken wxAuthToken = WxPublicNumberUtil.getAuthAccessTokenByOpenId(openid);
        if(wxAuthToken == null || wxAuthToken.getExpireTimeStamp() < System.currentTimeMillis()) {
            throw new BusinessException("授权超时,请返回重试!");
        }
        // 删除map里的token
        WxPublicNumberUtil.removeAuthAccessTokenByOpenId(openid);
        WxUserInfo info = WxPublicNumberUtil.getWxUserInfo(wxAuthToken);
        findedUser.setPassword(null);
        findedUser.setUsername(info.getNickname());
        findedUser.setHeadIcon(info.getHeadimgurl());
        findedUser.setWxOpenid(info.getOpenid());
        logger.debug("bind user{}, wx info{}.", findedUser.getId(), info);
        userDao.updateUserWxAccountInfo(findedUser);
        autoLogin(response, findedUser);
    }

    /** 
     * @see com.youmu.mall.wx.service.IWxAuthService#getRegistWxUserAuthUrl()
     */
    @Override
    public String getRegistWxUserAuthUrl() {
        return WxPublicNumberUtil.getWebAuthUrl(ConfigUtils.getWxAutoLoginRedirectUrl(), WxPublicNumberUtil.AUTH_USERINFO_SCOPE, GlobalConstant.REGIST_IN_WX + "");
    }

}
