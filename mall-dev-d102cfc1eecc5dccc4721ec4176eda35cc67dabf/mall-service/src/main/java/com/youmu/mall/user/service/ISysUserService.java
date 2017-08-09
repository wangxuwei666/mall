/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.user.service;

import javax.servlet.http.HttpServletResponse;

import com.youmu.common.page.utils.Page;
import com.youmu.mall.base.query.PageQuery;
import com.youmu.mall.user.domain.SysUser;
import com.youmu.mall.user.vo.SysUserListVo;

/**
 * 系统用户服务接口
 * @author zh
 * @version $Id: ISysUserService.java, v 0.1 2017年2月27日 下午7:41:45 zh Exp $
 */
public interface ISysUserService {

    /**
     * 用户登录
     * @param user 用户
     * @return 用户token
     */
    SysUser login(String account,  String password, HttpServletResponse response);

    /**
     * 后台用户退出登录
     */
    void logout();

    /**
     * 添加一个系统用户
     * @param user
     */
    void addSysUser(SysUser user);

    /**
     * 查询用户列表
     * @param query
     * @return
     */
    Page<SysUserListVo> listSysUser(PageQuery query);

    /**
     * 禁用后台用户.
     * @param id
     */
    void disableSysUser(Long id);
    
    /***
     * 启用某个后台用户.
     */
    void enableSysUser(Long id);

    /**
     * 修改用户
     * @param user
     */
    void updateSysUser(SysUser user);

    /**
     * 操作人员重置用户userId的密码为password
     * @param operatorUseId
     * @param userId
     * @param password
     */
    void resetSysUserPassword(Long operatorUseId, String userId, String password);

    /**
     * 删除系统用户
     * @param longUserId
     * @param id
     */
    void deleteSysUser(Long operator, Long id);

    /**
     * 获取更新的用户的信息
     * @param id
     * @return 
     */
    SysUserListVo getUpdateSysUser(Long id);

    /**
     * 修改用户密码
     * @param userId 当前用户id
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     */
    void resetPassword(Long userId, String oldPassword, String newPassword);
}
