/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.wx.service;

import javax.servlet.http.HttpServletResponse;

import com.youmu.mall.user.domain.User;

/**
 * 微信操作服务层接口.
 * @author zh
 * @version $Id: IWxService.java, v 0.1 2017年3月15日 下午7:44:57 zh Exp $
 */
public interface IWxAuthService {

    /**
     * 获取微信用户授权的url
     * @return 
     */
    String getWxUserAuthUrl(String url);

    /**
     * 处理微信用户认证回掉.
     * @param code
     * @param state
     * @param response
     */
    void handleWxAuthCallBack(String code, String state, HttpServletResponse response);

    /**
     * 绑定用户微信账号
     */
    void bindWxAccount(User user, String openid, HttpServletResponse response);
    
    /**
     * 获取微信分享用户的url
     * @param url
     * @return
     */
    String getShareWxUserAuthUrl(String url);
    
    /**
     * 获取微信注册的url
     * @return
     */
    String getRegistWxUserAuthUrl();
    
    /**
     * 获取微信支付时所需要的code的url
     * @return
     */
    String getWxPayCodeUrl();
   
}
