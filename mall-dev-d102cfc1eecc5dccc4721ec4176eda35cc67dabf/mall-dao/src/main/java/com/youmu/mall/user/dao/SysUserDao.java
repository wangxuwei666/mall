/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.youmu.mall.base.query.PageQuery;
import com.youmu.mall.user.domain.SysUser;
import com.youmu.mall.user.vo.SysUserListVo;

/**
 * 系统用户数据访问接口.
 * @author zh
 * @version $Id: SysUserDao.java, v 0.1 2017年2月27日 下午7:43:52 zh Exp $
 */
public interface SysUserDao {

    /**
     * 查询用户的登录信息
     * @param user
     * @return
     */
    SysUser selectSysUserLoginInfo(String account);

    /**
     * 添加一个系统用户.
     * @param user
     */
    void insertSysUser(SysUser user);

    /**
     * 查询系统用户的数量.
     * @param query
     * @return
     */
    Long selectSysUserCount(PageQuery query);

    /**
     * 查询系统用户的列表.
     * @param query
     * @return
     */
    List<SysUserListVo> selectSysUserList(PageQuery query);

    /**
     * 查询系统用户重复的数量.
     * @param user
     * @return
     */
    long selectSysUserRepeatCount(SysUser user);

    /**
     * 修改系统用户的状态.
     * @param status
     */
    void updateSysUserStatus(@Param("id") Long userId, @Param("status") int status);

    /**
     * 修改一个系统用户
     * @param user
     */
    void updateSysUser(SysUser user);

    /**
     * 重置用户的密码
     * @param userId 系统用户的id
     * @param password 新的密码
     */
    void updateSysUserPassword(@Param("userId") String userId, @Param("password") String password);

    /**
     * 删除一个系统用户通过该用户的id
     * @param id 被删除用户的id
     */
    void deleteSysUserById(Long id);

    /**
     * 获取用户更新时用户的信息
     * @param id
     */
    SysUserListVo selectUpdateSysUser(Long id);

    /**
     * @param userId 用户id
     * @return 
     */
    int changePassowrd(@Param("userId") Long userId, @Param("oldPassword") String oldPassword, @Param("newPassword") String newPassword);
}
