/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.user.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.youmu.common.page.utils.Page;
import com.youmu.mall.user.domain.BusinessUser;
import com.youmu.mall.user.query.BusinessUserQuery;
import com.youmu.mall.user.vo.BusinessUserListVo;
import com.youmu.mall.user.vo.LoggedBusinessUserVo;

/**
 * 商户用户服务接口.
 * @author zh
 * @version $Id: IBusinessUserService.java, v 0.1 2017年2月28日 上午11:05:58 zh Exp $
 */
public interface IBusinessUserService {

    /**
     * 添加一个商户用户
     * @param businessUser
     */
    void addBusinessUser(BusinessUser businessUser);

    /**
     * 修改商户用户密码
     * @param businessUser
     */
    void updateBusinessUser(BusinessUser businessUser);

    /**
     * 删除用户
     * @param id
     */
    void deleteBusinessUser(Long id);

    /**
     * 
     * @param query
     * @return
     */
    Page<BusinessUserListVo> listBusinessUser(BusinessUserQuery query);

    /**
     * 商户前台登录
     * @param account 账号
     * @param password 密码
     * @param response 响应用来设置cookie
     * @return
     */
    BusinessUser loginFront(String account, String password, HttpServletResponse response);

    /**
     * 获取已经登录的商户账户信息.
     * @return
     */
    LoggedBusinessUserVo getLoggedBusinessInfo();

    /**
     * 修改商户的营业执照.
     * @param licensePath 执照路径
     * @param storePaths 门店图片的相对路径.
     */
    void updateBusinessLicense(String licensePath, List<String> storePaths);

    /**
     * 修改密码
     * @param oldPassword 原密码
     * @param newPassword 新密码
     */
    void updateBusinessPassword(String oldPassword, String newPassword);

    /**
     * 
     * @param account
     * @param password
     * @param response
     * @return
     */
    BusinessUser loginBack(String account, String password, HttpServletResponse response);

    /**
     * 商户退出登录
     * @param response 
     */
    void loginoutFront(HttpServletResponse response);
    
    /**
     * 商户后台退出登录
     * @param response 
     */
    void loginoutBack(HttpServletResponse response);

    /**
     * 获取一个商户通过id
     * @param id
     * @return
     */
    BusinessUser getBusinessUserById(Long id);

}
