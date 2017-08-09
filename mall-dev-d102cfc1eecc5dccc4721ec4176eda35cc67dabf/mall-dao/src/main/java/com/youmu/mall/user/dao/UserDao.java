/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.youmu.mall.user.domain.User;
import com.youmu.mall.user.query.UserQuery;
import com.youmu.mall.user.vo.UserInfoVo;
import com.youmu.mall.user.vo.UserListVo;

/**
 * 用户数据访问接口.
 * @author zh
 * @version $Id: UserDao.java, v 0.1 2017年2月25日 下午4:39:35 zh Exp $
 */
public interface UserDao {

    /**
     * 根据手机号码查询用户是否存在.
     * @param mobile 手机号码
     * @return 用户信息
     */
    User selectLoginUserInfo(String mobile);

    /**
     * 查询该手机用户的数量
     * @param mobile 手机号码
     */
    int selectUserCountByMobile(String mobile);

    /**
     * 保存一个用户
     * @param user 用户对象
     */
    void saveUser(User user);

    /**
     * 修改用户的密码.
     * @param id 用户编号
     * @param newpassword 新密码
     * @param oldPassword 原密码 
     * @return 
     */
    int updateUserPassword(@Param("id") Long id, @Param("oldPassword") String oldPassword, @Param("newPassword")  String newPassword);

    /**
     * 修改用户的手机号码
     * @param id 用户的id
     * @param newMobile 用户的手机号码
     */
    void updateMobile(@Param("id") String id, @Param("mobile") String newMobile);

    /**
     * 查询分页查询的数量.
     * @param query
     * @return
     */
    Long selectUserCount(UserQuery query);

    /**
     * 查询用户的列表 后台展示
     * @param query
     * @return
     */
    List<UserListVo> selectUserList(UserQuery query);

    /**
     * 根据id获取用户信息
     * @param userId
     * @return
     */
    User getById(@Param("userId")Long userId);

    /**
     * 
     * @return
     */
    UserInfoVo getUserInfo(@Param("userId")Long userId);

    /**
     * 用户信息
     * @param openid
     */
    User selectLoginUserInfoByWxOpenId(String openid);

    /**
     * 绑定微信的用户信息到账户
     * @param user
     */
    void updateUserWxAccountInfo(User user);
    
    /**
     * 确认收货
     * @param orderId
     */
    void confirmRecevied(@Param("orderId")Long orderId,@Param("userId")Long userId);

    /**
     * 修改用户的头像
     * @param userId
     * @param image
     */
    void updateUserHeadIcom(@Param("id") Long userId, @Param("headIcon") String image);

    /**
     * 修改商户的密码
     */
    void updatePassword(@Param("mobile") String mobile, @Param("password")  String password);

    /**
     * 获取用户openId
     * @param userId
     * @return
     */
    String getUserOpenId(@Param("userId")Long userId);
    
    
}
