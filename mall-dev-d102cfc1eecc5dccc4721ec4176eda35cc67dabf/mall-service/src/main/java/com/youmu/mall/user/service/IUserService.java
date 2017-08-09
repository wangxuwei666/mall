/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.user.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.youmu.common.page.utils.Page;
import com.youmu.mall.user.domain.User;
import com.youmu.mall.user.query.UserQuery;
import com.youmu.mall.user.vo.UserInfoVo;
import com.youmu.mall.user.vo.UserListVo;

/**
 * 
 * @author zh
 * @version $Id: IUserService.java, v 0.1 2017年2月25日 下午4:33:50 zh Exp $
 */
public interface IUserService {

    /**
     * 用户登录服务.
     * @param mobile 手机号码
     * @param password 密码
     * @param response 相应
     * @return
     */
    public String login(String mobile, String password, HttpServletResponse response);

    /**
     * 用户注册
     * @param mobile 手机号码
     * @param password 密码
     * @param authCode 验证码
     * @param response 
     * @return
     */
    public User regist(User user, String authCode, HttpServletResponse response);

    /**
     * 获取注册的验证码.
     * @param mobile 手机号码
     * @return 验证码字符串
     */
    public void sendRegistAuthCode(String mobile);

    /**
     * 用户发送重置密码的验证码.
     * @param mobile
     */
    public void sendResetPasswordAuthCode(String mobile);

    /**
     * 发送重置手机号码的验证码
     * @param newMobile
     */
    public void sendResetMobileAuthCode(String newMobile);

    /**
     * 重置用户手机号码
     * @param newMobile 新的手机号码
     * @param authCode 验证码
     */
    public void restMobile(String newMobile, String authCode);

    /**
     * 用户条件查询
     * @param query
     * @return
     */
    public Page<UserListVo> listUser(UserQuery query);

    /**
     * 退出登录
     */
    public void logout();

    /**
     * 检查重置密码的验证码是否有效.
     * @param authCode
     * @param authCode2 
     * @return
     */
    public boolean checkResetPasswordAuthCode(String mobile, String authCode);

    /**
     * 向原来手机发送一条重置手机号码的验证码
     */
    public void sendOldResetMobileAuthCode();

    /**
     * 检查重置手机号码的原手机的验证码是否正确
     * @param authCode
     */
    public boolean checkOldRestMobileAuthCode(String authCode);

    /**
     * 我的
     * @return
     */
    public UserInfoVo getUserInfo();
    
    /**
     * 获取当前用户的手机号码
     * @return
     */
    public String getUserMobile();

    /**
     * 确认收货
     * @param orderId
     */
    public void confirmRecevied(Long orderId);

    /**
     * 修改用户的头像
     * @param longUserId
     * @param image
     */
    public void updateUserHeadIcon(Long longUserId, String image);

    /**
     * 是否登录
     * @param request
     * @return
     */
    public boolean isLogged(HttpServletRequest request);

    /**
     * 根据手机号码修改密码
     * @param mobile
     * @param password
     */
    public void resetPassword(String mobile, String password);

    /**
     * 修改用户的密码
     * @param userId 用户id
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     */
    public void changePassword(Long userId, String oldPassword, String newPassword);

    /**
     * 
     * 在微信公众号里面注册
     * @param mobile 手机号码
     * @param password
     * @param authCode
     * @param openId
     * @param response
     * @return
     */
    public User registInWx(User user, String authCode, String openId,
                             HttpServletResponse response);
    
    /**
     * 获取当前用户的uid
     * @return
     */
    public String getUid();
    
    /**
     * 获取带userid的url
     * @return
     */
    String getUrlPlusUid(String url,Integer groupType,HttpServletResponse response);
}
